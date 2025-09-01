# game_controller.py
import time
import threading
import sys
import os

import corba_connection

# Game state
game_id = None
round_running = True
game_ended = False
health_points = 5
current_word = ""
progress = None
winner = "@none"
win_counts = {}

# Constants
POLLING_INTERVAL = 1.0  # Check game status every second


def start_game(username):
    """Start or join a game"""
    global game_id, game_ended

    try:
        # Reset game-ended flag
        game_ended = False

        start_game_service = corba_connection.get_start_game_service()

        # Check if a game exists
        is_game_exist = start_game_service.checkGameExist("WAITING")

        if not is_game_exist:
            # Create a new game
            game_id = start_game_service.createGame(username)
            print(f"Created a new game with ID: {game_id}")
            wait_for_players(username)
        else:
            # Join existing game
            game_id = start_game_service.joinGame(username)
            print(f"Joined existing game with ID: {game_id}")
            wait_for_players(username)

        # If we get here, the game has completed
        print("\nGame finished. Returning to main menu...")
        time.sleep(1)
        return  # Return to the main menu

    except Exception as e:
        print(f"Error starting game: {e}")
        print("\nReturning to main menu...")
        time.sleep(2)
        return  # Return to the main menu on error as well


def wait_for_players(username):
    """Wait for players to join before starting the game"""
    print("Waiting for players to join...")

    start_game_service = corba_connection.get_start_game_service()
    guess_word_service = corba_connection.get_guess_word_service()

    remaining_time = start_game_service.get_remaining_time()
    last_player_list = ""
    last_time_display = remaining_time

    while remaining_time > 0:
        try:
            # Update the remaining time
            remaining_time = start_game_service.get_remaining_time()

            # Get current player list and display changes
            current_player_list = start_game_service.get_user_list()
            if current_player_list != last_player_list:
                players = current_player_list.split(", ")
                print("\nPlayers in game:")
                for player in players:
                    print(f"  - {player}")
                last_player_list = current_player_list

            # Display countdown on the same line
            if (remaining_time != last_time_display and
                    (remaining_time % 5 == 0 or remaining_time <= 5)):
                print(f"Game starting in {remaining_time} seconds...", end="\r")
                sys.stdout.flush()
                last_time_display = remaining_time

            time.sleep(1)

        except Exception as e:
            print(f"Error while waiting: {e}")
            return

    # When time expires, check if there are enough players
    players = start_game_service.get_user_list().split(", ")
    if len(players) <= 1:
        print("\nNot enough players joined. Game canceled.")
        start_game_service.setCancelGame("CANCELED")
        return

    # Start the game
    print("\nStarting the game!")
    guess_word_service.startGame(username)
    start_match(username)


def display_game_state():
    """Display the current game state"""
    # Clear screen (works on Windows and Unix/Linux/Mac)
    os.system('cls' if os.name == 'nt' else 'clear')

    print("\n=============================")
    print("🎮 WORD GUESSING GAME")
    print("=============================")

    # Show word with masked letters
    masked_word = ' '.join(progress)
    print(f"Word: {masked_word}")
    print(f"Health: {get_health_bar(health_points)}")

    # Show player stats
    display_player_stats()

    print("\nEnter a letter to guess: ")


def start_match(username):
    """Start a new round of the word guessing game"""
    global round_running, health_points, current_word, progress, winner, game_ended

    # Reset game state
    round_running = True
    health_points = 5
    winner = "@none"

    # Get the word to guess - try multiple times with delay to ensure we get the stable word
    guess_word_service = corba_connection.get_guess_word_service()

    # First attempt
    current_word = guess_word_service.getWords(game_id)
    time.sleep(0.5)

    # Second attempt - if different, this is probably more accurate
    latest_word = guess_word_service.getWords(game_id)
    if latest_word != current_word:
        print(f"[DEBUG] Server word changed from '{current_word}' to '{latest_word}'")
        current_word = latest_word

    print(f"[DEBUG] Using word: {current_word}")

    # Create masked version with underscores
    progress = ['_'] * len(current_word)

    # Display initial game state
    display_game_state()

    # Start game monitor thread
    monitor_thread = threading.Thread(target=monitor_game_status, args=(username,), daemon=True)
    monitor_thread.start()

    # Main game loop
    handle_player_guesses(username)


def handle_player_guesses(username):
    """Process player guesses using direct input"""
    global round_running, health_points, progress, game_ended

    while round_running and health_points > 0 and not game_ended:
        try:
            # Get user input
            guess = input().strip().lower()

            # Check if round still running
            if not round_running or game_ended:
                print("Round has ended.")
                break

            # Process the guess
            process_guess(guess, username)

        except Exception as e:
            print(f"\nError processing input: {e}")
            time.sleep(0.1)

    # If we exited the loop because game is over
    if game_ended:
        print("\nGame has ended. Press Enter to return to menu...")
        input()


def process_guess(guess, username):
    """Process a single letter guess"""
    global round_running, health_points, progress, winner, current_word

    if not round_running:
        return

    # Validate input
    if not guess or len(guess) != 1 or not guess.isalpha():
        print("Please enter a single letter.")
        return

    print(f"\nYou guessed: {guess}")

    guess_word_service = corba_connection.get_guess_word_service()
    leaderboard_service = corba_connection.get_leaderboard_service()

    # Check if letter is correct
    is_correct = guess_word_service.checkInput(guess, game_id)

    if is_correct:
        letter_found = False

        # Update progress with the guessed letter
        for i in range(len(current_word)):
            if current_word[i].lower() == guess:
                progress[i] = current_word[i]
                letter_found = True

        if letter_found:
            print(f"✅ Correct guess! The letter '{guess}' is in the word.")

            # Show updated game state
            display_game_state()

            # Check if word is complete
            if '_' not in progress:
                completed_word = ''.join(progress)

                # Before checking win status, verify the current word matches server's word
                server_word = guess_word_service.getWords(game_id)
                if server_word != current_word:
                    print(f"[WARNING] Server word has changed from '{current_word}' to '{server_word}'")
                    # If the word changed, we shouldn't proceed with win detection
                    return

                # Important: Make sure we send the complete word to the server
                # This is critical for proper winner detection
                is_winner = guess_word_service.isWinner(username, completed_word, game_id)

                print(f"[DEBUG] isWinner result: {is_winner}")

                # Immediately check who the server recognizes as the winner
                round_winner = guess_word_service.getWinner(game_id)
                print(f"[DEBUG] Server reported winner: {round_winner}")

                # CRITICAL: The server's winner determination is authoritative
                # Update our local winner value if the server reports one
                if round_winner != "@none":
                    winner = round_winner
                    print(f"[DEBUG] Setting winner to: {winner}")

                    # Ensure we explicitly set the winner in the server
                    if winner == username:
                        guess_word_service.setWinner(username, game_id)
                        leaderboard_service.setRoundWinner(username)
                        print("\n🎉🎉🎉 YOU WON THIS ROUND! 🎉🎉🎉")
                    else:
                        print(f"\n🏆 {winner} WON THIS ROUND!")

                    print(f"The word was: {current_word}")

                    # End the round for all clients
                    end_round(username, 2)  # SOMEONE WON
                else:
                    # If no winner yet, but we completed the word, try explicitly
                    # setting ourselves as the winner
                    if is_winner:
                        guess_word_service.setWinner(username, game_id)
                        leaderboard_service.setRoundWinner(username)
                        print("\n🎉🎉🎉 YOU WON THIS ROUND! 🎉🎉🎉")
                        print(f"The word was: {current_word}")

                        # End the round for all clients
                        end_round(username, 2)  # SOMEONE WON
                    else:
                        print("\nWord complete! Waiting for server confirmation...")

        else:
            print("\n⚠️ Strange... the server says the letter is correct, but it doesn't appear to be in the word.")
            time.sleep(1)
            display_game_state()

    else:
        # Wrong guess, reduce health
        health_points -= 1
        print(f"❌ Wrong guess! Health: {get_health_bar(health_points)}")
        time.sleep(1)
        display_game_state()

        if health_points <= 0:
            print("\n💔 YOU LOST ALL HEALTH!")
            end_round(username, 3)  # HEALTH LOST


def monitor_game_status(username):
    """Thread to monitor game status and time"""
    global round_running, game_ended, winner, current_word, progress

    guess_word_service = corba_connection.get_guess_word_service()
    leaderboard_service = corba_connection.get_leaderboard_service()

    # Get initial time
    initial_time = guess_word_service.time_configuration(game_id)
    last_time_display = initial_time
    last_word_check = time.time()
    last_winner_check = time.time()

    while round_running and not game_ended:
        try:
            # Check for game winner
            final_winner = guess_word_service.finalWinner(game_id)

            # Get remaining time - this is important as it can tell us if the round has ended
            remaining_time = guess_word_service.time_configuration(game_id)

            # Frequently check for winner updates - CRITICAL for synchronization
            current_time = time.time()
            if current_time - last_winner_check >= 0.5:  # Check every half second
                # Get current winner status - CRITICAL for proper winner detection
                current_winner = guess_word_service.getWinner(game_id)

                # IMPORTANT: Update winner if server reports one
                # This is how we ensure we're consistent with the server
                if current_winner != "@none" and current_winner != winner:
                    print(f"\n[SERVER] Winner updated to: {current_winner}")
                    winner = current_winner

                    # If there's a winner now, announce it
                    if winner == username:
                        print("\n🎉🎉🎉 YOU WON THIS ROUND! 🎉🎉🎉")
                    else:
                        print(f"\n🏆 {winner} WON THIS ROUND!")

                    print(f"The word was: {current_word}")

                    # End round for all clients if winner is detected
                    end_round(username, 2)
                    break  # Exit the monitor loop

                last_winner_check = current_time

            # Periodically check if the word has changed on the server
            if current_time - last_word_check >= 5:  # Every 5 seconds
                try:
                    server_word = guess_word_service.getWords(game_id)
                    if server_word != current_word:
                        print(f"\n[WARNING] Word changed on server from '{current_word}' to '{server_word}'")
                        current_word = server_word
                        if round_running:
                            progress = ['_'] * len(current_word)
                            display_game_state()
                except Exception as e:
                    print(f"[ERROR] Failed to check server word: {e}")
                last_word_check = current_time

            # Display time on the same line
            if (remaining_time != last_time_display and
                    (remaining_time % 10 == 0 or remaining_time <= 5)):
                print(f"⏱️ Time remaining: {remaining_time} seconds", end="\r")
                sys.stdout.flush()
                last_time_display = remaining_time

            # Game is over with final winner
            if final_winner != "NO_FINAL_WINNER_YET":
                game_ended = True
                round_running = False  # Make sure the round is marked as not running
                print()  # New line after countdown

                if final_winner == username:
                    leaderboard_service.setGameWinner(username)
                    print("\n🏆🏆🏆 CONGRATULATIONS! YOU WON THE GAME! 🏆🏆🏆")
                else:
                    print(f"\n🎮 GAME OVER! {final_winner} WON THE GAME!")

                guess_word_service.setGameDone(game_id)

                # Let the player know the game has ended
                print("\nGame completed! Press Enter to return to menu...")
                return  # Exit the monitor thread

            # Time's up
            elif remaining_time <= 0:
                print()  # New line after countdown
                print("\n⏰ TIME'S UP!")
                end_round(username, 1)  # TIME'S UP
                break  # Exit the monitor loop

            time.sleep(POLLING_INTERVAL)

        except Exception as e:
            print(f"\nError in game monitor: {e}")
            time.sleep(POLLING_INTERVAL)


def end_round(username, reason):
    """End the current round and prepare for next"""
    global round_running

    # Only process if we haven't already ended the round
    if not round_running:
        return

    round_running = False

    # Don't start round result for game over
    if reason == -1:
        return

    # Handle round result
    round_result(username, reason)


def round_result(username, reason):
    """Handle end of round and countdown to next round"""
    global win_counts

    guess_word_service = corba_connection.get_guess_word_service()

    # Determine countdown seconds based on reason
    if reason == 1:  # TIME'S UP
        countdown_seconds = 5
        print(f"Time's up! Next round starting in {countdown_seconds} seconds...")

    elif reason == 2:  # SOMEONE WON
        # IMPORTANT: Use nextGameTime to ensure we're synchronized with the server
        countdown_seconds = 5 + guess_word_service.time_configuration(game_id)
        print(f"Round complete! Next round in {countdown_seconds} seconds")

        # Update win counts
        if winner != "@none":
            win_counts[winner] = win_counts.get(winner, 0) + 1

        display_player_stats()

    else:  # HEALTH LOST
        countdown_seconds = 5 + guess_word_service.time_configuration(game_id)
        print(f"You lost all health! Next round in {countdown_seconds} seconds")

    # Ensure positive countdown
    if countdown_seconds <= 0:
        countdown_seconds = 5
        print(f"[WARNING] Invalid countdown time from server, using {countdown_seconds} seconds")

    # Countdown to next round - inline version
    print("Next Round in: ", end="")
    for i in range(countdown_seconds, 0, -1):
        print(f"{i}s", end="")
        sys.stdout.flush()
        time.sleep(1)
        # Erase the current number and reposition cursor
        print("\b\b", end="")
        if i > 9:  # Double-digit number cleanup
            print("\b", end="")
        sys.stdout.flush()
    print("Starting now!")

    try:
        # Make sure everyone resets at the same time by calling resetRound
        print("Resetting round on server...")
        guess_word_service.resetRound(game_id)

        # Allow time for the server to reset and propagate to all clients
        time.sleep(2)

        # Start new match
        start_match(username)

    except Exception as e:
        print(f"[ERROR] Failed to start new round: {e}")


def display_player_stats():
    """Display current player win statistics"""
    try:
        guess_word_service = corba_connection.get_guess_word_service()
        join_users = guess_word_service.get_players()
        players = join_users.split(", ")

        print("----- PLAYER STATS -----")
        for player in players:
            count = win_counts.get(player, 0)
            s = f"{player}: "

            if count > 0:
                s += "👑 " * count
            else:
                s += "No wins yet"

            print(s)

        print("-----------------------")

    except Exception as e:
        print(f"[WARNING] Could not display player stats: {e}")


def get_health_bar(health):
    """Create a visual health bar"""
    hearts = "❤️ " * health
    skulls = "💀 " * (5 - health)
    return hearts + skulls
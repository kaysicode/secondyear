import threading
import time

from finProject.temp.JoinGame import game_ID
from finProject.temp.Main import guess_word_service

game_ID = None
user_name = None

class MatchPy:
    winner = "@none"
    countdown_timer = None
    game_running = True
    POLLING_INTERVAL = 0.5  # Check status every 500ms (0.5 seconds)
    current_word = ""
    masked = None

    @staticmethod
    def match_game(guess_word_service, game_id, username):

        game_ID = game_id
        user_name = username
        guess_word_service = guess_word_service


        # Initialize game state
        MatchPy.current_word = guess_word_service.getWords(game_ID.get_game_id())
        MatchPy.masked = []
        MatchPy.winner = "@none"
        MatchPy.game_running = True

        # Create masked word
        for _ in range(len(MatchPy.current_word)):
            MatchPy.masked.append('_')

        health = 5

        print("=============================")
        print("🎮 WORD GUESSING GAME - ROUND STARTED")
        print("=============================")

        # Start the status monitoring timer first
        MatchPy.start_game_monitor()

        # Input thread (non-blocking)
        def input_thread_func():
            nonlocal health
            while MatchPy.game_running and health > 0:
                print(f"\nWord: {MatchPy.format_masked_word(MatchPy.masked)}")
                print(f"Health: {MatchPy.get_health_bar(health)}")
                print("Enter your guess (single letter): ", end="")
                input_text = input().strip().lower()

                # Check if game is still running before processing input
                if not MatchPy.game_running:
                    print("Round has ended, waiting for next round...")
                    break

                # Validate input
                if not input_text:
                    continue

                if len(input_text) != 1 or not input_text.isalpha():
                    print("❗ Please enter a single letter.")
                    continue

                # Check game status before processing guess
                MatchPy.check_game_status()
                if not MatchPy.game_running:
                    print("Round has ended while processing your input.")
                    break

                # Process guess and update health if needed
                is_correct = guess_word_service.checkInput(input_text, game_ID)

                if is_correct:
                    letter_found = False

                    # Update masked word with the correctly guessed letter
                    for i in range(len(MatchPy.current_word)):
                        if MatchPy.current_word[i].lower() == input_text:
                            MatchPy.masked[i] = MatchPy.current_word[i]
                            letter_found = True

                    if letter_found:
                        print(f"✅ Correct guess! The letter '{input_text}' is in the word.")

                        # Check if word is completely guessed
                        if '_' not in MatchPy.masked:
                            is_winner = guess_word_service.isWinner(user_name, ''.join(MatchPy.masked),
                                                              game_ID)
                            round_winner = guess_word_service.getWinner(game_ID, user_name)

                            # Update winner if valid
                            if round_winner != "@none":
                                MatchPy.winner = round_winner

                            if is_winner or (round_winner != "@none" and round_winner == user_name):
                                # Set this user as winner on server
                                guess_word_service.setWinner(user_name, game_ID)
                                guess_word_service.setRoundWinner(user_name)

                                print("\n🎉🎉🎉 YOU WON THIS ROUND! 🎉🎉🎉")
                                print(f"The word was: {MatchPy.current_word}")

                                # End the round
                                MatchPy.end_current_round(2)  # SOMEONE WON
                            elif round_winner != "@none":
                                print(f"\n🏆 {round_winner} WON THIS ROUND!")
                                print(f"The word was: {MatchPy.current_word}")

                                # End the round
                                MatchPy.end_current_round(2)  # SOMEONE WON
                    else:
                        print(
                            "⚠️ Strange... the server says the letter is correct, but it doesn't appear to be in the word.")
                else:
                    # Wrong guess
                    health -= 1
                    print(f"❌ Wrong guess! Health: {MatchPy.get_health_bar(health)}")

                    # Check if health is depleted
                    if health <= 0:
                        print("\n💔 YOU LOST ALL HEALTH!")
                        MatchPy.end_current_round(3)  # HEALTH LOST

        # Start input thread
        input_thread = threading.Thread(target=input_thread_func)
        input_thread.daemon = True
        input_thread.start()

    @staticmethod
    def process_guess(input_text, health):
        # This functionality is now integrated into the input_thread_func in match_game
        pass

    @staticmethod
    def start_game_monitor():
        # Get initial time remaining
        initial_remaining = guess_word_service.time_configuration(game_ID)
        if initial_remaining % 5 == 0:
            print(f"⏱️ Time remaining: {initial_remaining} seconds")

        # Create a function for the timer to call
        def check_status_periodically():
            while MatchPy.game_running:
                try:
                    MatchPy.check_game_status()
                except Exception as ex:
                    print(f"[ERROR] Exception in game monitor: {str(ex)}")
                time.sleep(MatchPy.POLLING_INTERVAL)

        # Start the timer in a separate thread
        MatchPy.countdown_timer = threading.Thread(target=check_status_periodically)
        MatchPy.countdown_timer.daemon = True
        MatchPy.countdown_timer.start()

    @staticmethod
    def check_game_status():
        try:
            # If game already ended, don't check
            if not MatchPy.game_running:
                return

            # Check for final game winner
            final_winner = guess_word_service.finalWinner(game_ID)

            # Get latest winner status from server
            current_winner = guess_word_service.getWinner(game_ID)

            # Get remaining time
            remaining = guess_word_service.time_configuration(game_ID)

            # Update time display at reasonable intervals
            if remaining % 5 == 0 or remaining <= 5:
                print(f"⏱️ Time remaining: {remaining} seconds")

            # Update winner if valid
            if current_winner != "@none":
                winner = current_winner

            # Game is over with a final winner
            if final_winner != "NO_FINAL_WINNER_YET":
                MatchPy.game_running = False

                if final_winner == user_name:
                    print("\n🏆🏆🏆 CONGRATULATIONS! YOU WON THE GAME! 🏆🏆🏆")
                else:
                    print(f"\n🎮 GAME OVER! {final_winner} WON THE GAME!")

                guess_word_service.setGameDone(guess_word_service)

                from finProject.temp.Main import menu

                menu()
            # Time's up
            elif remaining <= 0:
                print("\n⏰ TIME'S UP!")
                MatchPy.end_current_round(1)  # TIME'S UP
            # Someone won the round
            elif MatchPy.winner != "@none":
                if MatchPy.winner == user_name:
                    print("\n🎉🎉🎉 YOU WON THIS ROUND! 🎉🎉🎉")
                else:
                    print(f"\n🏆 {MatchPy.winner} WON THIS ROUND!")
                print(f"The word was: {MatchPy.current_word}")
                MatchPy.end_current_round(2)  # SOMEONE WON
        except Exception as ex:
            print(f"[ERROR] Exception in game status check: {str(ex)}")

    @staticmethod
    def end_current_round(reason=0):
        # Only process if we haven't already ended the game
        if not MatchPy.game_running:
            return

        # Set game as not running
        MatchPy.game_running = False

        # Start the round result sequence
        #RoundPy.round_result(reason)

    @staticmethod
    def format_masked_word(masked):
        result = []
        for c in masked:
            result.append("_ " if c == '_' else f"{c} ")
        return ''.join(result).strip()

    @staticmethod
    def get_health_bar(health):
        health_bar = ""
        for i in range(health):
            health_bar += "❤️ "
        for i in range(health, 5):
            health_bar += "💀 "
        return health_bar
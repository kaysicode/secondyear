import sys
from sqlite3 import Time

import CORBA
import CosNaming

from finProject.corba.LoginService_idl import _0_LoginService
from finProject.corba.StartGameService_idl import _0_StartGameService
from finProject.corba.GuessWordService_idl import _0_GuessWordService
from finProject.temp.Main import GuessWord

# Get the Login interface from generated module
Login = _0_LoginService.Login
StartGame = _0_StartGameService.StartGame
guessWordService = _0_GuessWordService.GuessWord


import time
from threading import Thread, Event

class MatchCLI:
    def __init__(self, gs, username, game_id):
        self.gs = gs
        self.username = username
        self.game_id = game_id

        self.round = 0
        self.hp_max = 5
        self.win_counts = {}             # track wins per player
        self.match_over = Event()        # to stop timers

    def start_round_timer(self):
        """Runs in background to show remaining time each second."""
        while not self.match_over.is_set():
            rem = self.gs.time_configuration(self.game_id)
            print(f"\r[Timer] {rem:2d}s remaining  ", end="", flush=True)
            if rem <= 0:
                break
            time.sleep(1)
        print()  # newline when timer ends

    def play_match(self):
        match_wins = 0

        while match_wins < 3:
            self.round += 1
            print(f"\n=== Round {self.round} ===")

            # Fetch word and initialize
            secret = self.gs.getWords(self.game_id).lower()
            progress = ["_"] * len(secret)
            hp = self.hp_max

            # Start timer thread
            timer_thread = Thread(target=self.start_round_timer, daemon=True)
            timer_thread.start()

            # Round loop
            while hp > 0 and "_" in progress:
                guess = input(f"Word: {' '.join(progress)}   HP: {hp}  Guess> ").strip().lower()
                if len(guess) != 1 or not guess.isalpha():
                    print("  ↳ Enter a single letter.")
                    continue

                correct = self.gs.checkInput(guess, self.game_id)
                if correct:
                    # reveal all positions
                    for i, ch in enumerate(secret):
                        if ch == guess:
                            progress[i] = guess
                    print(f"  ↳ Correct! '{guess}'")

                    # did we finish?
                    if "_" not in progress:
                        won = self.gs.isWinner(self.username, "".join(progress), self.game_id)
                        round_winner = self.gs.getWinner(self.game_id)
                        print(f"→ Round winner: {round_winner}")
                        self.win_counts[round_winner] = self.win_counts.get(round_winner, 0) + 1
                        if round_winner == self.username and won:
                            match_wins += 1
                        break
                else:
                    hp -= 1
                    print(f"  ↳ Wrong. '{guess}' not in word.")

                # check if someone else already won by remote call
                remote_winner = self.gs.getWinner(self.game_id)
                if remote_winner != "@none":
                    print(f"→ Round winner: {remote_winner}")
                    self.win_counts[remote_winner] = self.win_counts.get(remote_winner, 0) + 1
                    if remote_winner == self.username:
                        match_wins += 1
                    break

            # if HP ran out
            if hp == 0 and "_" in progress:
                print(f"❌ You ran out of HP. Word was: {secret}")

            # stop timer
            self.match_over.set()
            timer_thread.join()
            self.match_over.clear()

            # prepare next round if needed
            if match_wins < 3:
                print(f"Score: you {match_wins} – opponent {self.win_counts.get('opponent',0)}")
                print("Resetting round...\n")
                self.gs.resetRound(self.game_id)
                time.sleep(1)

        # match complete
        final = self.gs.finalWinner(self.game_id)
        print(f"\n🏆 MATCH OVER! Winner: {final}")
        self.gs.setGameDone(self.game_id)


class LoginClient:
    def __init__(self):
        self.gs = None
        self.remTime = None
        self.loginIDL = None
        self.userName = None
        self.initialize_orb()

    def initialize_orb(self):
        try:
            # Initialize ORB with naming service reference
            orb_args = sys.argv + [
                '-ORBInitRef', 'NameService=corbaloc::localhost:1050/NameService'
            ]
            orb = CORBA.ORB_init(orb_args, CORBA.ORB_ID)

            # Get naming service reference
            obj = orb.resolve_initial_references("NameService")
            ncRef = obj._narrow(CosNaming.NamingContext)

            if ncRef is None:
                print("Failed to narrow NamingContext")
                sys.exit(1)

            # Resolve LoginService reference
            name = [CosNaming.NameComponent("LoginService", "")]
            objRef = ncRef.resolve(name)
            self.loginIDL = objRef._narrow(Login)
            if self.loginIDL is None:
                print("Failed to narrow Login interface")
                sys.exit(1)

            name = [CosNaming.NameComponent("StartGameService", "")]
            objRef = ncRef.resolve(name)
            self.startGame = objRef._narrow(StartGame)
            if self.startGame is None:
                print("Failed to narrow StartGame interface")
                sys.exit(1)

            name = [CosNaming.NameComponent("GuessWordService", "")]
            objRef = ncRef.resolve(name)
            self.guessWordService = objRef._narrow(guessWordService)
            if self.guessWordService is None:
                print("Failed to narrow StartGame interface")
                sys.exit(1)

        except Exception as e:
            print("ORB initialization failed:", e)
            sys.exit(1)

    def show_main_menu(self):
        print("\n<1> LOGIN")
        print("<2> SIGN UP")
        print("<3> EXIT")

        try:
            option = int(input("Enter your option here: "))

            if option == 1:
                self.login()
            elif option == 2:
                self.signup()
            elif option == 3:
                sys.exit(0)
            else:
                print("Wrong input, try again")
                self.show_main_menu()

        except ValueError:
            print("Please enter a valid number")
            self.show_main_menu()

    def login(self):
        username = input("Enter your username: ")
        password = input("Enter your password: ")

        try:
            isAccExist = self.loginIDL.checkExist(username, password)
            if isAccExist:
                isAccActive = self.loginIDL.checkActive(username)
                if isAccActive:
                    self.userName = username
                    self.menu()
                else:
                    print("Someone is already logged in")
            else:
                print(f"Account {username} not found")

        except Exception as e:
            print("Login failed:", e)

        self.show_main_menu()

    def signup(self):
        username = input("Enter your username: ")
        password = input("Enter your password: ")
        rePassword = input("Enter your re-password: ")

        if password == rePassword:
            try:
                isAccGood = self.loginIDL.checkUser(username, password, "USER")
                if not isAccGood:
                    print("Signup successful!")
                    self.menu()
                else:
                    print("Username is already taken")
            except Exception as e:
                print("Signup failed:", e)
        else:
            print(f"{password} and {rePassword} don't match")

        self.show_main_menu()

    def menu(self):
        print("\n<1> START GAME")
        print("<2> LEADERBOARDS")
        print("<3> LOGOUT")

        try:
            option = int(input("Enter your option here: "))

            if option == 1:
                print("You joined a game")
                self.waitingGame(self.userName)
            elif option == 2:
                print("No leaderboards yet")
            elif option == 3:
                try:
                    self.loginIDL.logout(self.userName)
                    print("Logged out successfully")
                except Exception as e:
                    print("Logout failed:", e)
                self.show_main_menu()
                return
            else:
                print("Wrong input, try again")

        except ValueError:
            print("Please enter a valid number")

        self.menu()

    def waitingGame(self, userName):
        import time

        # 1) join or create
        if not self.startGame.checkGameExist("WAITING"):
            self.startGame.createGame()
        else:
            self.startGame.joinGame(userName)

        game_id = int(self.startGame.getGameID())

        # 2) countdown
        rem = self.startGame.get_remaining_time()
        while rem > 0:
            time.sleep(1)
            print(f"Game starts in {rem}s…", end="\n")
            rem = self.startGame.get_remaining_time()
        print("\n*** GAME START ***\n")

        # 3) fetch the full word once
        secret = self.guessWordService.getWords(game_id).lower()
        progress = ["_"] * len(secret)
        hp = 5

        # 4) guessing loop
        while hp > 0 and "_" in progress:
            print("Word: " + " ".join(progress) + f"   HP: {hp}")
            letter = input("Guess a letter: ").strip().lower()
            if len(letter) != 1 or not letter.isalpha():
                print("  ↳ Enter exactly one alphabetic character.")
                continue

            hit = self.guessWordService.checkInput(letter, game_id)
            if hit:
                # reveal all positions of that letter
                for i, ch in enumerate(secret):
                    if ch == letter:
                        progress[i] = letter
                print(f"  ↳ Good! '{letter}' is in the word.\n")
            else:
                hp -= 1
                print(f"  ↳ Nope. '{letter}' is not in the word.\n")

        # 5) announce result
        if "_" not in progress:
            print("You’ve guessed it: " + "".join(progress))
        else:
            print("Out of guesses. The word was: " + secret)

        match = MatchCLI(self.gs, self.userName, game_id)
        match.play_match()
        self.menu()



if __name__ == "__main__":
    client = LoginClient()
    client.show_main_menu()
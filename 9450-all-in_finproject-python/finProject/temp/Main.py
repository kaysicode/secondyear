#!/usr/bin/env python3

import sys
import time
import threading
import CORBA
import CosNaming

# Import your generated Python IDL stubs
from finProject.corba.LoginService_idl import _0_LoginService
from finProject.corba.LeaderboardService_idl import _0_LeaderboardService
from finProject.corba.StartGameService_idl import _0_StartGameService
from finProject.corba.HeartbeatService_idl import _0_HeartbeatService
from finProject.corba.GuessWordService_idl import _0_GuessWordService

# Get the interfaces from generated modules
Login = _0_LoginService.Login
Leaderboard = _0_LeaderboardService.Leaderboard
StartGame = _0_StartGameService.StartGame
Heartbeat = _0_HeartbeatService.Heartbeat
GuessWord = _0_GuessWordService.GuessWord

# Global variables
login_service = None
leaderboard_service = None
start_game_service = None
heartbeat_service = None
guess_word_service = None
user_name = None
stop_heartbeat = False
services_initialized = False


def start_heartbeat():
    """Start sending heartbeat signals to the server"""

    def heartbeat_task():
        while not stop_heartbeat:
            try:
                if heartbeat_service is not None:
                    heartbeat_service.ping()
                time.sleep(5)  # Send heartbeat every 5 seconds
            except Exception as e:
                print("Server is off")
                sys.exit(0)

    heartbeat_thread = threading.Thread(target=heartbeat_task)
    heartbeat_thread.daemon = True
    heartbeat_thread.start()


def login():
    """Handle user login"""
    global user_name, login_service

    username = input("Enter your username: ")
    password = input("Enter your password: ")

    is_acc_exist = login_service.checkExist(username, password)
    if is_acc_exist:
        is_acc_active = login_service.checkActive(username)
        if is_acc_active:
            user_name = username
            menu()
        else:
            print("Someone is already login")
    else:
        print(f"{username} {password} is not found")


def signup():
    """Handle user registration"""
    global login_service, user_name

    username = input("Enter your username: ")
    password = input("Enter your password: ")
    re_password = input("Enter your re-password: ")

    if password == re_password:
        is_acc_good = login_service.checkUser(username, password, "USER")
        if not is_acc_good:
            user_name = username
            menu()
        else:
            print("User name is already taken")
    else:
        print(f"{password} and {re_password} is not the same")


def menu():
    """Display and handle main menu options"""
    global user_name, login_service

    print("=====WELCOME PAGE=====")
    print(f"WELCOME : {user_name}")
    print("<1> START GAME")
    print("<2> LEADERBOARDS")
    print("<3> LOGOUT")

    option = int(input("Enter your option here: "))
    print("======================")

    if option == 1:
        from finProject.temp.JoinGame import start_game
        start_game(user_name, start_game_service, guess_word_service)
    elif option == 2:
        from finProject.temp.Leaderboard import get_winners, display_current_leaderboard
        get_winners(leaderboard_service)

    elif option == 3:
        login_service.logout(user_name)
        login()
    else:
        print("Wrong input try again")
        menu()


def join_game(username):
    """Start the game for user"""
    global start_game_service, guess_word_service
    # This would implement the game logic as in the Java version
    print(f"Starting game for {username}")
    # Additional game logic would go here


def get_winners():
    """Get the leaderboard winners"""
    global leaderboard_service
    winners = leaderboard_service.getWinner()
    print("===== LEADERBOARDS =====")
    for winner in winners:
        print(f"{winner.name} - {winner.score}")


def initialize_orb(host, port):
    """Initialize the CORBA ORB and resolve all services"""
    global login_service, leaderboard_service, start_game_service
    global heartbeat_service, guess_word_service, services_initialized

    try:
        # Initialize ORB with naming service reference
        orb_args = [
            '-ORBInitRef',
            f'NameService=corbaloc::{host}:{port}/NameService'
        ]
        orb = CORBA.ORB_init(orb_args, CORBA.ORB_ID)

        # Get naming service reference
        obj = orb.resolve_initial_references("NameService")
        nc_ref = obj._narrow(CosNaming.NamingContext)

        if nc_ref is None:
            print("Failed to narrow NamingContext")
            return False

        # Resolve LoginService
        try:
            login_name = [CosNaming.NameComponent("LoginService", "")]
            login_obj_ref = nc_ref.resolve(login_name)
            login_service = login_obj_ref._narrow(Login)
            print("Login service initialized:", login_service is not None)
            if login_service is None:
                print("Failed to narrow Login interface")
                return False
        except Exception as e:
            print(f"Error resolving LoginService: {e}")
            return False

        # Resolve LeaderboardService
        try:
            leaderboard_name = [CosNaming.NameComponent("LeaderboardService", "")]
            leaderboard_obj_ref = nc_ref.resolve(leaderboard_name)
            leaderboard_service = leaderboard_obj_ref._narrow(Leaderboard)
        except Exception as e:
            print(f"Error resolving LeaderboardService: {e}")
            # Continue even if this service fails

        # Resolve StartGameService
        try:
            start_game_name = [CosNaming.NameComponent("StartGameService", "")]
            start_game_obj_ref = nc_ref.resolve(start_game_name)
            start_game_service = start_game_obj_ref._narrow(StartGame)
        except Exception as e:
            print(f"Error resolving StartGameService: {e}")
            # Continue even if this service fails

        # Resolve HeartbeatService
        try:
            heartbeat_name = [CosNaming.NameComponent("Heartbeat", "")]
            heartbeat_obj_ref = nc_ref.resolve(heartbeat_name)
            heartbeat_service = heartbeat_obj_ref._narrow(Heartbeat)
        except Exception as e:
            print(f"Error resolving HeartbeatService: {e}")
            # Continue even if this service fails

        # Resolve GuessWordService
        try:
            guess_word_name = [CosNaming.NameComponent("GuessWordService", "")]
            guess_word_obj_ref = nc_ref.resolve(guess_word_name)
            guess_word_service = guess_word_obj_ref._narrow(GuessWord)
        except Exception as e:
            print(f"Error resolving GuessWordService: {e}")
            # Continue even if this service fails

        # Test connection - verify login service is working
        if login_service is not None:
            try:
                is_exist = login_service.checkExist("KennethMayo", "km123")
                print("DEBUG 1 - Test Login:", is_exist)
            except Exception as login_test_error:
                print(f"Test login failed: {login_test_error}")
                return False
        else:
            print("Login service is None after narrowing - service initialization failed")
            return False

        # Mark services as initialized
        services_initialized = True

        # Start heartbeat monitoring
        start_heartbeat()

        return True

    except Exception as e:
        print("ORB initialization failed:", e)
        return False


def main():
    """Main function to initialize ORB and run the client"""
    print("WELCOME TO PYTHON CORBA")

    host = input("Enter -ORBInitialHost: ")
    port = input("Enter -ORBInitialPort: ")

    if not initialize_orb(host, port):
        print("Failed to initialize services. Exiting...")
        sys.exit(1)

    # Main menu loop
    while True:
        print("=====LOGIN PAGE=====")
        print("<1> LOGIN")
        print("<2> SIGN UP")
        print("<3> EXIT")

        option = int(input("Enter your option here: "))
        print("====================")

        if option == 1:
            login()
        elif option == 2:
            signup()
        elif option == 3:
            global stop_heartbeat
            stop_heartbeat = True
            sys.exit(0)
        else:
            print("Wrong input try again")


if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\nExiting...")
        sys.exit(0)
    except Exception as e:
        print(f"Unexpected error: {e}")
        sys.exit(1)
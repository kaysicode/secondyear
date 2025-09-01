# main.py
import sys
import time
import threading
import os
import signal
import traceback

# Import our modules
import corba_connection
import login_controller
import main_menu_controller
import game_controller
import leaderboard_controller

# Global variables
username = None


def start_heartbeat():
    """Start a thread to periodically ping the server"""

    def heartbeat_thread():
        heartbeat_service = corba_connection.get_heartbeat_service()
        while True:
            try:
                heartbeat_service.ping()
                time.sleep(5)  # Send ping every 5 seconds
            except Exception as e:
                print(f"Lost connection to server: {e}")
                print("Exiting...")
                os._exit(1)  # Force exit

    thread = threading.Thread(target=heartbeat_thread, daemon=True)
    thread.start()


def main():
    """Main program entry point"""
    global username

    print("==============================")
    print("WELCOME TO PYTHON CORBA CLIENT")
    print("==============================")

    host = input("Enter -ORBInitialHost: ")
    port = input("Enter -ORBInitialPort: ")

    # Initialize CORBA connection
    if corba_connection.initialize_orb(host, port):
        print("Successfully connected to the server!")

        # Start heartbeat
        try:
            # Try to ping the heartbeat service before starting the thread
            heartbeat_service = corba_connection.get_heartbeat_service()
            heartbeat_service.ping()
            print("Heartbeat final successful. Starting heartbeat monitor...")
            start_heartbeat()
        except Exception as e:
            print(f"Warning: Heartbeat final failed: {e}")
            print("Continuing without heartbeat monitoring.")

        # Main application loop
        while True:
            print("\n===== LOGIN PAGE =====")
            print("<1> LOGIN")
            print("<2> SIGN UP")
            print("<3> EXIT")
            print("=====================")

            try:
                option = int(input("Enter your option here: "))

                if option == 1:
                    # Login
                    username = login_controller.login()
                    if username:
                        main_menu_controller.main_menu(username)

                elif option == 2:
                    # Sign up
                    username = login_controller.signup()
                    if username:
                        main_menu_controller.main_menu(username)

                elif option == 3:
                    print("Goodbye!")
                    sys.exit(0)

                else:
                    print("Invalid option. Try again.")

            except ValueError:
                print("Please enter a valid number.")
            except Exception as e:
                print(f"Error in main loop: {e}")
                traceback.print_exc()

    else:
        print("Failed to connect to server. Exiting...")
        sys.exit(1)


if __name__ == "__main__":
    # Setup signal handler to gracefully exit
    def signal_handler(sig, frame):
        print("\nExiting...")
        if username:
            try:
                corba_connection.get_login_service().logout(username)
                print("Logged out successfully")
            except:
                pass
        sys.exit(0)


    signal.signal(signal.SIGINT, signal_handler)

    # Start the program
    main()
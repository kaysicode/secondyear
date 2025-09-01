# main_menu_controller.py
import corba_connection
import login_controller
import game_controller
import leaderboard_controller


def main_menu(username):
    """Display the main menu and handle user choices"""
    while True:
        print("\n===== WELCOME PAGE =====")
        print(f"WELCOME: {username}")
        print("<1> START GAME")
        print("<2> LEADERBOARDS")
        print("<3> LOGOUT")
        print("=======================")

        try:
            option = int(input("Enter your option here: "))

            if option == 1:
                game_controller.start_game(username)

            elif option == 2:
                leaderboard_controller.display_leaderboard()

            elif option == 3:
                if login_controller.logout(username):
                    return

            else:
                print("Invalid option. Try again.")

        except ValueError:
            print("Please enter a valid number.")
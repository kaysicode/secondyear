import tkinter as tk
from tkinter import messagebox

# Import the login_service directly
from finProject.client.UserMain import login_service

# Import the User class properly
from finProject.client.model.User import User

# Create a global current_user variable to store the active user
current_user = None


def show_error_message(message, error_level):
    """Display error messages with different levels of severity."""
    if error_level == 1:  # Success
        messagebox.showinfo("Success", message)
    elif error_level == 2:  # Warning
        messagebox.showwarning("Warning", message)
    elif error_level == 3:  # Error
        messagebox.showerror("Error", message)
    elif error_level == 4:  # Critical
        messagebox.showerror("Critical Error", message)


def login(username, password, login_service, leaderboard_service, guess_word_service, login_window=None):
    """
    Authenticate user login with optional window reference for closing.

    Args:
        username: User's username
        password: User's password
        login_window: Reference to the login window (optional)
    """
    global current_user

    current_user = username

    if not username or not password:
        show_error_message("Username and password cannot be empty", 3)
        return

    try:
        print("Debug: login_service in controller is:", login_service)
        is_exist = login_service.checkExist(username, password)
        print("DEBUG 2: ", is_exist)

        if is_exist:
            is_active = login_service.checkActive(username)
            print("DEBUG 3: ", is_active)

            if is_active:
                current_user = username

                # Close login window if reference was passed
                if login_window:
                    login_window.destroy_window()

                from finProject.client.view import MainMenuView
                MainMenuView.MainMenu(leaderboard_service, guess_word_service)
            else:
                show_error_message(f"{username} is already logged in!", 4)
        else:
            show_error_message(f"{username} - User not found", 2)

    except Exception as e:
        show_error_message(f"Login error: {str(e)}", 3)
        print(f"Error during login: {e}")


def signup(username, password, re_password, user_type):
    """
    Register a new user.

    Args:
        username: Desired username
        password: Desired password
        re_password: Password confirmation
        user_type: Type of user account
    """
    global current_user

    if not username or not password or not re_password:
        show_error_message("All fields must be filled", 3)
        return

    try:
        # Check if passwords match and username is not too long
        if password == re_password and len(username) <= 11:
            # Check if username is already taken
            is_exist = login_service.checkUser(username, password, user_type)

            if not is_exist:
                # Create a new User instance with all required parameters
                current_user = User(
                    username=username,
                    password=password,
                    user_type=user_type
                )

                # Show success message
                show_error_message("Successfully created account!", 1)

                # Import here to avoid circular imports
                from finProject.client.view import MainMenuView

                # Destroy the signup window
                # We need to find the current window
                for widget in tk._default_root.winfo_children():
                    if isinstance(widget, tk.Toplevel):
                        widget.destroy()

                # Open the main menu
                MainMenuView.MainMenu()
            else:
                show_error_message(f"{username} might be already taken!", 3)
        else:
            show_error_message("Username must not exceed 11 characters! Or check your password", 4)

    except Exception as e:
        show_error_message(f"Signup error: {str(e)}", 3)
        print(f"Error during signup: {e}")


# Function to get the current user from other modules
def get_current_user():
    global current_user
    return current_user
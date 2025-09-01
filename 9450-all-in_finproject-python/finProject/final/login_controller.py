# login_controller.py
import corba_connection


def login():
    """Handle user login"""
    username = input("Enter your username: ")
    password = input("Enter your password: ")

    login_service = corba_connection.get_login_service()
    is_exist = login_service.checkExist(username, password)

    if is_exist:
        is_active = login_service.checkActive(username)
        if is_active:
            print(f"Welcome, {username}!")
            return username
        else:
            print(f"{username} is already logged in!")
    else:
        print(f"Username or password not found")

    return None


def signup():
    """Handle user signup"""
    username = input("Enter your username: ")
    password = input("Enter your password: ")
    re_password = input("Enter your password again: ")

    if password == re_password:
        if len(username) <= 11:
            # User type is hardcoded as "USER"
            login_service = corba_connection.get_login_service()
            is_exist = login_service.checkUser(username, password, "USER")

            if not is_exist:
                print("Successfully created account!")
                return username
            else:
                print(f"{username} might already be taken!")
        else:
            print("Username must not exceed 11 characters!")
    else:
        print("Passwords do not match!")

    return None


def logout(username):
    """Log out the current user"""
    try:
        login_service = corba_connection.get_login_service()
        login_service.logout(username)
        print("Logged out successfully")
        return True
    except Exception as e:
        print(f"Error logging out: {e}")
        return False
# Modified UserMain.py

import tkinter as tk
from tkinter import messagebox
import sys
import threading
import CORBA
import CosNaming

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

# Global service references
login_service = None
leaderboard_service = None
start_game_service = None
heartbeat_service = None
guess_word_service = None
heartbeat_scheduler = None

# Flag to track if services are initialized
services_initialized = False


class LoginClient:
    def __init__(self, host, port):
        self.host = host
        self.port = port
        self.orb = None
        self.connected = self.initialize_orb()

    def initialize_orb(self):
        global login_service, leaderboard_service, start_game_service, heartbeat_service, guess_word_service, services_initialized

        try:
            # Initialize ORB with naming service reference
            orb_args = [
                '-ORBInitRef',
                f'NameService=corbaloc::{self.host}:{self.port}/NameService'
            ]
            self.orb = CORBA.ORB_init(orb_args, CORBA.ORB_ID)

            # Get naming service reference
            obj = self.orb.resolve_initial_references("NameService")
            nc_ref = obj._narrow(CosNaming.NamingContext)

            if nc_ref is None:
                print("Failed to narrow NamingContext")
                return False

            # Resolve service references
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


def start_heartbeat():
    global heartbeat_scheduler

    def heartbeat_task():
        try:
            if heartbeat_service is not None:
                heartbeat_service.ping()  # Must be implemented in the Heartbeat service
        except Exception as e:
            print(f"Heartbeat failed: {e}")
            if heartbeat_scheduler:
                heartbeat_scheduler.cancel()

            # Execute in the main thread
            root = tk.Tk()
            root.withdraw()  # Hide the empty window
            messagebox.showerror(
                "Server Offline",
                "Connection to the server has been lost.\nThe application will now shut down."
            )
            sys.exit(0)

    def schedule_next():
        global heartbeat_scheduler
        heartbeat_task()
        heartbeat_scheduler = threading.Timer(5.0, schedule_next)
        heartbeat_scheduler.daemon = True
        heartbeat_scheduler.start()

    # Start the first timer
    heartbeat_scheduler = threading.Timer(5.0, schedule_next)
    heartbeat_scheduler.daemon = True
    heartbeat_scheduler.start()


class ConnectView:
    def __init__(self):
        self.root = tk.Tk()
        self.root.title("Connect to Server")
        self.root.geometry("300x200")
        self.root.resizable(False, False)

        # Host Label and Entry
        tk.Label(self.root, text="Host:").pack(pady=(20, 0))
        self.host_entry = tk.Entry(self.root, width=30)
        self.host_entry.insert(0, "localhost")  # Default value
        self.host_entry.pack(pady=(5, 0))

        # Port Label and Entry
        tk.Label(self.root, text="Port:").pack(pady=(10, 0))
        self.port_entry = tk.Entry(self.root, width=30)
        self.port_entry.insert(0, "1050")  # Default value
        self.port_entry.pack(pady=(5, 0))

        # Connect Button
        connect_button = tk.Button(self.root, text="Connect", command=self.on_connect)
        connect_button.pack(pady=20)

        self.root.mainloop()

    def on_connect(self):
        host = self.host_entry.get()
        port = self.port_entry.get()

        if not host or not port:
            messagebox.showerror("Error", "Please fill in both host and port fields.")
            return

        # Try to establish connection
        client = LoginClient(host, port)

        if client.connected:
            print(f"Successfully connected to server at {host}:{port}")

            # Verify services are initialized before proceeding
            if not services_initialized or login_service is None:
                messagebox.showerror(
                    "Service Error",
                    "Login service was not properly initialized. Please try again."
                )
                return

            self.root.destroy()  # Close connect window

            # Import the login view here after services are initialized
            from finProject.client.view import LoginView

            # Start login view
            LoginView.LoginApp(login_service, leaderboard_service, guess_word_service)
        else:
            messagebox.showerror(
                "Connection Error",
                f"Failed to connect to server at {host}:{port}\nPlease check your settings and try again."
            )


def main():
    # Start with connection screen
    ConnectView()


if __name__ == "__main__":
    main()
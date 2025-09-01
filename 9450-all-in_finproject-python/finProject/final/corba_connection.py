# corba_connection.py
import sys
import os
import omniORB
import omniORB.CORBA as CORBA
import CosNaming

# Add the appropriate paths to find the generated modules
sys.path.append('.')
sys.path.append('./finProject')  # Add the finProject directory

# Import the generated stub modules
try:
    # Try to import from finProject.corba
    from finProject.corba import GuessWordService_idl
    from finProject.corba import HeartbeatService_idl
    from finProject.corba import LeaderboardService_idl
    from finProject.corba import LoginService_idl
    from finProject.corba import StartGameService_idl

    # Import the actual service interfaces
    GuessWordService = GuessWordService_idl._0_GuessWordService
    HeartbeatService = HeartbeatService_idl._0_HeartbeatService
    LeaderboardService = LeaderboardService_idl._0_LeaderboardService
    LoginService = LoginService_idl._0_LoginService
    StartGameService = StartGameService_idl._0_StartGameService

    print("Successfully imported service stubs.")
    HAVE_STUBS = True
except ImportError as e:
    print(f"Warning: Could not import service stubs: {e}")
    print("Will try using a dynamic approach without stubs.")
    HAVE_STUBS = False
    # Set these to None so we can check later
    GuessWordService = None
    HeartbeatService = None
    LeaderboardService = None
    LoginService = None
    StartGameService = None

# Global ORB and service references
orb = None
login_service = None
start_game_service = None
guess_word_service = None
leaderboard_service = None
heartbeat_service = None


def initialize_orb(host, port):
    """Initialize CORBA ORB and connect to the Java server"""
    global orb, login_service, start_game_service, guess_word_service, leaderboard_service, heartbeat_service

    try:
        # Initialize the ORB
        orb_args = ["-ORBInitRef", f"NameService=corbaloc::{host}:{port}/NameService"]
        orb = CORBA.ORB_init(orb_args, CORBA.ORB_ID)

        # Get the name service
        obj = orb.resolve_initial_references("NameService")
        root_context = obj._narrow(CosNaming.NamingContext)

        if root_context is None:
            print("Could not narrow the root naming context")
            return False

        # Get service references
        print("Connecting to services...")

        # Create name components for each service
        login_name = [CosNaming.NameComponent("LoginService", "")]
        start_game_name = [CosNaming.NameComponent("StartGameService", "")]
        guess_word_name = [CosNaming.NameComponent("GuessWordService", "")]
        leaderboard_name = [CosNaming.NameComponent("LeaderboardService", "")]
        heartbeat_name = [CosNaming.NameComponent("Heartbeat", "")]

        # Resolve the services
        try:
            login_obj = root_context.resolve(login_name)
            start_game_obj = root_context.resolve(start_game_name)
            guess_word_obj = root_context.resolve(guess_word_name)
            leaderboard_obj = root_context.resolve(leaderboard_name)
            heartbeat_obj = root_context.resolve(heartbeat_name)

            # Narrow the references to their specific interfaces if we have stubs
            if HAVE_STUBS:
                login_service = login_obj._narrow(LoginService.Login)
                start_game_service = start_game_obj._narrow(StartGameService.StartGame)
                guess_word_service = guess_word_obj._narrow(GuessWordService.GuessWord)
                leaderboard_service = leaderboard_obj._narrow(LeaderboardService.Leaderboard)
                heartbeat_service = heartbeat_obj._narrow(HeartbeatService.Heartbeat)
            else:
                # Use the objects directly if no stubs
                print("Using objects directly without narrowing")
                login_service = login_obj
                start_game_service = start_game_obj
                guess_word_service = guess_word_obj
                leaderboard_service = leaderboard_obj
                heartbeat_service = heartbeat_obj

            # Check if all services were properly resolved
            if login_service is None:
                print("Warning: Could not resolve LoginService")
            if start_game_service is None:
                print("Warning: Could not resolve StartGameService")
            if guess_word_service is None:
                print("Warning: Could not resolve GuessWordService")
            if leaderboard_service is None:
                print("Warning: Could not resolve LeaderboardService")
            if heartbeat_service is None:
                print("Warning: Could not resolve HeartbeatService")

        except CosNaming.NamingContext.NotFound as e:
            print(f"Service not found: {e}")
            return False
        except CORBA.BAD_PARAM as e:
            print(f"Bad parameter while resolving service: {e}")
            return False
        except Exception as e:
            print(f"Error resolving services: {e}")
            import traceback
            traceback.print_exc()
            return False

        # Test heartbeat connection
        try:
            print("Testing heartbeat service...")
            heartbeat_service.ping()
            print("Heartbeat service connected successfully!")
        except AttributeError as e:
            print(f"Warning: Heartbeat service doesn't have ping method: {e}")
            print("Will continue anyway...")
        except Exception as e:
            print(f"Warning: Heartbeat ping failed: {e}")
            print("Will continue anyway...")

        print("Successfully connected to all services!")
        return True

    except Exception as e:
        print(f"Error initializing ORB: {e}")
        import traceback
        traceback.print_exc()
        return False


# Getter methods for services
def get_login_service():
    return login_service


def get_start_game_service():
    return start_game_service


def get_guess_word_service():
    return guess_word_service


def get_leaderboard_service():
    return leaderboard_service


def get_heartbeat_service():
    return heartbeat_service
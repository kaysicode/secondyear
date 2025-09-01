# leaderboard_controller.py
import corba_connection


def display_leaderboard():
    """Display the game leaderboard"""
    try:
        print("\n[Leaderboard] Fetching leaderboard data...")

        # Get winners from server
        leaderboard_service = corba_connection.get_leaderboard_service()
        winners = leaderboard_service.getWinners()

        if not winners or len(winners) == 0:
            print("[Leaderboard] No winners data available")
            print("\n=== CURRENT LEADERBOARD ===")
            print("No data available")
            print("=========================\n")
            input("Press Enter to return to menu...")
            return

        print(f"[Leaderboard] Received {len(winners)} winner entries")

        # Display leaderboard
        print("\n=== CURRENT LEADERBOARD ===")
        for i, entry in enumerate(winners):
            if entry.username and entry.username.strip():
                print(f"{i + 1}. {entry.username} - {entry.value} wins")
            else:
                print(f"{i + 1}. (Empty)")

        print("=========================\n")
        input("Press Enter to return to menu...")

    except Exception as e:
        print(f"Error displaying leaderboard: {e}")
        input("Press Enter to return to menu...")
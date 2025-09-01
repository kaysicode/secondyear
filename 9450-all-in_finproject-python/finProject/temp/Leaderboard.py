# !/usr/bin/env python3

# Global variables to store leaderboard data
# Player names
first_name = ""
second_name = ""
third_name = ""
fourth_name = ""
fifth_name = ""

# Win counts
first_won = 0
second_won = 0
third_won = 0
fourth_won = 0
fifth_won = 0


def get_winners(leaderboard_service):
    """Fetch and store leaderboard winners"""
    global first_name, second_name, third_name, fourth_name, fifth_name
    global first_won, second_won, third_won, fourth_won, fifth_won

    print("\n[LeaderboardControl] getWinners() called - Fetching leaderboard data...")

    # Reset values
    print("[LeaderboardControl] Resetting previous leaderboard values...")
    reset_values()

    winners = leaderboard_service.getWinners()

    if winners is None or len(winners) == 0:
        print("[LeaderboardControl] No winners data available or empty response")
        return

    print(f"[LeaderboardControl] Received {len(winners)} winner entries")

    # Store winners in the static variables
    if len(winners) > 0:
        first_name = winners[0].username
        first_won = winners[0].value
        print(f"[LeaderboardControl] 1st place: {first_name} with {first_won} wins")

    if len(winners) > 1:
        second_name = winners[1].username
        second_won = winners[1].value
        print(f"[LeaderboardControl] 2nd place: {second_name} with {second_won} wins")

    if len(winners) > 2:
        third_name = winners[2].username
        third_won = winners[2].value
        print(f"[LeaderboardControl] 3rd place: {third_name} with {third_won} wins")

    if len(winners) > 3:
        fourth_name = winners[3].username
        fourth_won = winners[3].value
        print(f"[LeaderboardControl] 4th place: {fourth_name} with {fourth_won} wins")

    if len(winners) > 4:
        fifth_name = winners[4].username
        fifth_won = winners[4].value
        print(f"[LeaderboardControl] 5th place: {fifth_name} with {fifth_won} wins")

    print("[LeaderboardControl] Current leaderboard state:")
    display_current_leaderboard()


def reset_values():
    """Reset all leaderboard values to empty/zero"""
    global first_name, second_name, third_name, fourth_name, fifth_name
    global first_won, second_won, third_won, fourth_won, fifth_won

    print("[LeaderboardControl] Resetting all leaderboard values to empty/zero")
    first_name = second_name = third_name = fourth_name = fifth_name = ""
    first_won = second_won = third_won = fourth_won = fifth_won = 0


def display_current_leaderboard():
    """Display the current leaderboard data"""
    print("\n=== CURRENT LEADERBOARD ===")

    if first_name:
        print(f"1. {first_name} - {first_won} wins")
    else:
        print("1. (Empty)")

    if second_name:
        print(f"2. {second_name} - {second_won} wins")
    else:
        print("2. (Empty)")

    if third_name:
        print(f"3. {third_name} - {third_won} wins")
    else:
        print("3. (Empty)")

    if fourth_name:
        print(f"4. {fourth_name} - {fourth_won} wins")
    else:
        print("4. (Empty)")

    if fifth_name:
        print(f"5. {fifth_name} - {fifth_won} wins")
    else:
        print("5. (Empty)")

    print("=========================\n")

    choice = int(input("<1> BACK TO HOME: "))

    if choice == 1:
        from finProject.temp.Main import menu  # Import here to avoid circular import
        menu()
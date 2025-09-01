import tkinter as tk
from tkinter import PhotoImage
import os


def show_leaderboard(leaderboard_service, guess_word_service=None, on_close=None):
    """
    Displays a leaderboard window with top 5 players from CORBA service

    Args:
        leaderboard_service: Service to fetch leaderboard data
        guess_word_service: Optional service for word guessing game
        on_close: Optional callback function to execute when leaderboard is closed
    """
    # Main Frame
    root = tk.Tk()
    root.title("Top 5 Players")
    root.geometry("1920x1009+-8+-8")

    # Store image references in a dictionary to prevent garbage collection
    images = {}

    # Dynamic path setup
    script_dir = os.path.dirname(os.path.abspath(__file__))
    project_root = os.path.abspath(os.path.join(script_dir, "..", ".."))

    # Load and set background
    bg_path = os.path.join(project_root, "res", "Background.png")
    images['bg'] = PhotoImage(file=bg_path)  # Store in images dictionary
    canvas = tk.Canvas(root, width=1920, height=1009)
    canvas.pack(fill="both", expand=True)
    canvas.create_image(0, 0, anchor="nw", image=images['bg'])

    # Title
    canvas.create_text(960, 120, text="TOP 5 PLAYERS", fill="white", font=("Inter", 48, "bold"))

    try:
        # Get winners from CORBA service
        winners = leaderboard_service.getWinners()

        # Convert CORBA StatEntry sequence to Python list
        players = []
        for i, winner in enumerate(winners[:5]):  # Get top 5 only
            players.append({
                "name": winner.username,
                "wins": winner.value
            })

        # Fill remaining slots if less than 5 players
        while len(players) < 5:
            players.append({
                "name": f"Player {len(players) + 1}",
                "wins": 0
            })

    except Exception as e:
        print(f"Error fetching leaderboard: {e}")
        # Fallback to dummy data if service fails
        players = [
            {"name": "Player 2", "wins": 69},
            {"name": "Player 1", "wins": 50},
            {"name": "Player 4", "wins": 40},
            {"name": "Player 5", "wins": 10},
            {"name": "Player 3", "wins": 5}
        ]

    # Load placement images
    placement_images = []
    for i in range(1, 6):
        place_path = os.path.join(project_root, "res",
                                  f"{['First', 'Second', 'Third', 'Fourth', 'Fifth'][i - 1]}PlaceWins.png")
        try:
            img = PhotoImage(file=place_path)
            # Store each image in our dictionary to prevent garbage collection
            images[f'place_{i}'] = img
            placement_images.append(img)
        except Exception as e:
            # Fallback if image fails to load
            print(f"Failed to load placement image for position {i}: {e}")
            placement_images.append(None)

    # Position and display leaderboard
    total_columns = 5
    column_width_spacing = 300
    total_width = (total_columns - 1) * column_width_spacing
    start_x = 960 - (total_width // 2)
    start_y = 350

    for i, player in enumerate(players):
        x = start_x + i * column_width_spacing

        # Placement image (if loaded)
        if i < len(placement_images) and placement_images[i]:
            canvas.create_image(x, start_y + 130, image=placement_images[i], anchor="center")

        # Player name
        canvas.create_text(x, start_y - 50, text=player["name"], fill="white",
                           font=("Inter", 24, "bold"))

        # Wins
        canvas.create_text(x, start_y + 230, text=f"{player['wins']} wins",
                           fill="white", font=("Inter", 24, "bold"))

    # Custom handler for window close
    def handle_close():
        root.destroy()
        if on_close and callable(on_close):
            on_close()

    # Back Button
    btn = tk.Button(
        root,
        text="BACK",
        font=("Inter", 24, "bold"),
        bg="#B22222",
        fg="white",
        activebackground="darkred",
        activeforeground="white",
        width=12,
        height=2,
        command=handle_close
    )
    btn.place(x=810, y=850)

    # Override window close button (X)
    root.protocol("WM_DELETE_WINDOW", handle_close)

    # Save images reference to the root to prevent garbage collection
    root.images = images

    root.mainloop()


# Example usage
if __name__ == "__main__":
    # You would normally get these from your main application
    try:
        from finProject.client.UserMain import leaderboard_service

        show_leaderboard(leaderboard_service)
    except ImportError:
        print("Running in standalone mode with mock data")
        show_leaderboard(None)
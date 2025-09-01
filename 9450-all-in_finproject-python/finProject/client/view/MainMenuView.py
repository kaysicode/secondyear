from tkinter import *
from PIL import ImageTk, Image
from finProject.client.view.StartGameView import StartGameView
import os


def resource_path(relative_path):
    return os.path.join(os.path.dirname(__file__), relative_path)


class MainMenu:
    def __init__(self, leaderboard_service, guess_word_service):
        self.window = Tk()
        self.window.title("Main Menu")
        self.window.geometry("1920x1080")

        # Save references to services
        self.leaderboard_service = leaderboard_service
        self.guess_word_service = guess_word_service

        # Keep references to all images
        self.images = {}  # Dictionary to store image references

        # Background image
        try:
            bg_path = resource_path("....res/Background.png")
            img = Image.open(bg_path)
            self.images['bg'] = ImageTk.PhotoImage(img)  # Store reference
            self.canvas = Canvas(self.window, width=1920, height=1009)
            self.canvas.place(x=0, y=0, relwidth=1, relheight=1)
            self.canvas.create_image(0, 0, image=self.images['bg'], anchor="nw")
        except Exception as e:
            print(f"Error loading background image: {e}")
            # Fallback if image fails to load
            self.canvas = Canvas(self.window, width=1920, height=1009, bg='white')
            self.canvas.place(x=0, y=0, relwidth=1, relheight=1)

        canvas_width = 1920

        from finProject.client.controller.LoginController import current_user
        print(current_user)


        self.usenarme = current_user or "Guest"

        self.username_label = self.canvas.create_text(
            canvas_width // 2, 150,
            text=f"Welcome, {self.usenarme}!",
            font=("Arial", 48, "bold"),
            fill="black",
            anchor="center"
        )

        x = 0
        bar_height = 30
        bar_width = 80
        bar_spacing = 5
        start_x = (canvas_width - 400) // 2
        start_y = 200

        while x < 5:
            self.canvas.create_rectangle(
                start_x + x * (bar_width + bar_spacing),
                start_y,
                start_x + x * (bar_width + bar_spacing) + bar_width,
                start_y + bar_height,
                fill="limegreen",
                outline="black",
                width=1
            )
            x += 1

        # "START GAME" button
        self.start_game_btn = Button(self.window, text="START GAME", font=("Arial", 28, "bold"),
                                     bg="lime green", fg="white", command=self.start_game)
        self.start_game_btn.place(x=760, y=350, width=400, height=100)

        # "LEADERBOARDS" button - Fixed the command to properly pass as a callback
        self.leaderboards_btn = Button(self.window, text="LEADERBOARDS", font=("Arial", 28, "bold"),
                                       bg="#d4a017", fg="white", command=self.show_leaderboards)
        self.leaderboards_btn.place(x=760, y=500, width=400, height=100)

        # "LOG OUT" button
        self.logout_btn = Button(self.window, text="LOG OUT", font=("Arial", 28, "bold"),
                                 bg="#d64541", fg="white", command=self.logout)
        self.logout_btn.place(x=760, y=650, width=400, height=100)

        self.window.mainloop()

    def start_game(self):
        StartGameView(self.usenarme)
        self.window.quit()

    def show_leaderboards(self):
        # First withdraw the current window (hide but don't destroy)
        self.window.withdraw()

        # Import here to avoid circular imports
        from finProject.client.view import WinsLeaderboardView

        # Create a callback function to show this window when leaderboard closes
        def on_leaderboard_close():
            self.window.deiconify()  # Show the main menu again

        # Pass the callback to the leaderboard view
        WinsLeaderboardView.show_leaderboard(self.leaderboard_service, on_close=on_leaderboard_close)

    def logout(self):
        # Add any logout logic here if needed
        self.window.destroy()

    def destroy_window(self):
        """Properly destroy the window and all its children"""
        self.window.destroy()


if __name__ == '__main__':
    MainMenu(None, None)  # Passing None for services when testing directly
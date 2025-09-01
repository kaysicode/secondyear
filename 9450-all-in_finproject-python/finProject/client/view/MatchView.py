import tkinter as tk
from tkinter import messagebox, PhotoImage
import random
from finProject.client.controller.MatchController import MatchController
import os


class HangmanGame:
    def __init__(self, root):
        self.root = root
        self.root.title("Hangman Game")
        self.root.geometry("1920x1080")
        self.controller = MatchController()

        # Game variables
        self.current_word = self.controller.getWord().upper()  # normalize to uppercase
        self.guessed_letters = self.controller.getGuessedLetters()
        self.remaining_attempts = self.controller.getRemainingAttempts()

        # Timer variables
        self.timer_running = False
        self.countdown_seconds = self.controller.getRemTimeSec()
        self.remaining_time = self.countdown_seconds

        # Create UI elements with background
        self.create_ui()

        # Start the timer
        self.start_timer()

    def create_ui(self):
        try:
            script_dir = os.path.dirname(os.path.abspath(__file__))
            project_root = os.path.abspath(os.path.join(script_dir, "..", ".."))
            bg_path = os.path.join(project_root, "res", "Background.png")
            self.bg_image = PhotoImage(file=bg_path)
            self.canvas = tk.Canvas(self.root, width=1920, height=1009)
            self.canvas.pack(fill="both", expand=True)
            self.canvas.create_image(0, 0, anchor="nw", image=self.bg_image)
        except Exception as e:
            print(f"Error loading background: {e}")
            self.canvas = tk.Canvas(self.root, width=1920, height=1009, bg="navy")
            self.canvas.pack(fill="both", expand=True)

        self.canvas.create_text(960, 120, text=self.controller.getUsername(), fill="white", font=("Inter", 35, "bold"))

        self.segment_count = 5
        segment_width = 100
        segment_height = 30
        spacing = 2
        start_x = 710
        start_y = 170
        self.hp_segments = []

        for i in range(self.segment_count):
            x1 = start_x + i * (segment_width + spacing)
            y1 = start_y
            x2 = x1 + segment_width
            y2 = y1 + segment_height
            rect = self.canvas.create_rectangle(x1, y1, x2, y2, fill="green", outline="black")
            self.hp_segments.append(rect)

        self.canvas.create_text(960, 250, text="HANGMAN GAME", fill="white", font=("Inter", 48, "bold"))

        timer_box_width = 120
        timer_box_height = 60
        timer_box_x = 1600
        timer_box_y = 120

        self.timer_box = self.canvas.create_rectangle(
            timer_box_x - timer_box_width / 2, timer_box_y - timer_box_height / 2,
            timer_box_x + timer_box_width / 2, timer_box_y + timer_box_height / 2,
            fill="white", outline="black", width=3
        )

        self.timer_text = self.canvas.create_text(
            timer_box_x, timer_box_y,
            text=str(self.countdown_seconds),
            fill="black",
            font=("Inter", 30, "bold")
        )

        self.create_letter_boxes()

        input_frame = tk.Frame(self.root, bg="lightblue")
        self.canvas.create_window(960, 550, window=input_frame)

        instruction_label = tk.Label(input_frame, text="Enter a letter:", font=("Inter", 20), bg="lightblue")
        instruction_label.grid(row=0, column=0, padx=10, pady=15)

        self.letter_entry = tk.Entry(input_frame, width=5, font=("Inter", 20), justify="center")
        self.letter_entry.grid(row=0, column=1, padx=10, pady=15)

        enter_button = tk.Button(input_frame, text="Enter", font=("Inter", 20, "bold"),
                                 command=self.process_guess, bg="#4CAF50", fg="white")
        enter_button.grid(row=0, column=2, padx=10, pady=15)

        self.guessed_letters_text = self.canvas.create_text(
            960, 650,
            text="Guessed letters: ",
            font=("Inter", 18),
            fill="white"
        )

        self.update_display()
        self.letter_entry.focus()
        self.root.bind('<Return>', lambda event: self.process_guess())

    def create_letter_boxes(self):
        self.letter_boxes = []
        box_size = 60
        box_spacing = 15
        total_width = len(self.controller.getWord().upper()) * (box_size + box_spacing) - box_spacing
        start_x = 960 - total_width / 2
        y_position = 380

        for i in range(len(self.controller.getWord().upper())):
            x_position = start_x + i * (box_size + box_spacing)
            box = self.canvas.create_rectangle(
                x_position, y_position,
                x_position + box_size, y_position + box_size,
                fill="white", outline="black", width=3
            )
            text = self.canvas.create_text(
                x_position + box_size / 2, y_position + box_size / 2,
                text="",
                font=("Arial", 30, "bold"),
                fill="black"
            )
            self.letter_boxes.append((box, text))

    def start_timer(self):
        self.timer_running = True
        self.remaining_time = self.countdown_seconds
        self.update_timer()

    def update_timer(self):
        if self.timer_running:
            self.canvas.itemconfig(self.timer_text, text=str(self.remaining_time))
            if self.remaining_time <= 0:
                self.timer_running = False
                messagebox.showinfo("Time's Up!", f"Out of time! The word was '{self.controller.getWord().upper()}'.")
                self.reset_game()
            else:
                self.remaining_time -= 1
                self.root.after(1000, self.update_timer)

    def reset_timer(self):
        self.timer_running = False
        self.remaining_time = self.countdown_seconds
        self.canvas.itemconfig(self.timer_text, text=str(self.remaining_time))
        self.start_timer()

    def update_hp_bar(self):
        for i in range(self.segment_count):
            color = "limegreen" if i < self.controller.getRemainingAttempts() else "red"
            self.canvas.itemconfig(self.hp_segments[i], fill=color)

    def update_display(self):
        for i in range(len(self.controller.getWord().upper())):
            letter = self.controller.getWord().upper()[i]
            box, text = self.letter_boxes[i]
            if letter in self.guessed_letters:
                self.canvas.itemconfig(text, text=letter)
            else:
                self.canvas.itemconfig(text, text="")

        self.canvas.itemconfig(self.guessed_letters_text,
                               text=f"Guessed letters: {', '.join(self.guessed_letters)}")
        self.update_hp_bar()

    def process_guess(self):
        guess = self.letter_entry.get().strip().upper()
        self.letter_entry.delete(0, tk.END)

        if not guess:
            messagebox.showwarning("Invalid Input", "Please enter a letter.")
            self.letter_entry.focus()
            return
        if len(guess) != 1:
            messagebox.showwarning("Invalid Input", "Please enter only one letter.")
            self.letter_entry.focus()
            return
        if not guess.isalpha():
            messagebox.showwarning("Invalid Input", "Please enter a valid letter (A-Z).")
            self.letter_entry.focus()
            return
        if guess in self.guessed_letters:
            messagebox.showinfo("Already Guessed", f"You've already guessed '{guess}'.")
            self.letter_entry.focus()
            return

        self.controller.addToGuessedLetters(guess)
        self.guessed_letters = self.controller.getGuessedLetters()  # <-- This line is critical_

        if guess not in self.controller.getWord().upper():
            self.controller.reduceHp()

        self.update_display()
        self.check_game_status()
        self.letter_entry.focus()

    def check_game_status(self):
        won = all(letter in self.guessed_letters for letter in self.controller.getWord().upper())
        if won:
            self.timer_running = False
            self.show_countdown_popup()  # This must be a method of HangmanGame
        elif self.controller.getRemainingAttempts() <= 0:
            self.timer_running = False
            messagebox.showinfo("Game Over", f"You've lost! The word was '{self.controller.getWord().upper()}'.")
            self.reset_game()

    def reset_game(self):
        self.current_word = self.controller.getWord().upper()  # Get new word, uppercase
        self.controller.resetGuessedLetters()
        self.guessed_letters = []
        self.controller.resetHp()
        self.reset_timer()
        self.controller.setWordToGuess()
        for box, text in self.letter_boxes:
            self.canvas.delete(box)
            self.canvas.delete(text)

        self.create_letter_boxes()
        self.update_display()

    def show_countdown_popup(self):
        self.popup = tk.Toplevel(self.root)
        self.popup.title("Congratulations!")
        self.popup.geometry("300x150")
        self.popup.grab_set()  # Focus on this popup
        label = tk.Label(self.popup, text="You won!\nStarting new game in 3...", font=("Inter", 16))
        label.pack(expand=True)

        self.countdown_popup_seconds = 3

        def countdown():
            if self.countdown_popup_seconds > 0:
                label.config(text=f"You won!\nStarting new game in {self.countdown_popup_seconds}...")
                self.countdown_popup_seconds -= 1
                self.popup.after(1000, countdown)
            else:
                self.popup.destroy()
                self.reset_game()

        countdown()  # start countdown immediately

if __name__ == "__main__":
    root = tk.Tk()
    game = HangmanGame(root)
    root.mainloop()

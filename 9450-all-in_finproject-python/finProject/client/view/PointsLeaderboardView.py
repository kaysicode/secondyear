import tkinter as tk
from tkinter import PhotoImage
import os

# Main Frame
root = tk.Tk()
root.title("Leaderboard")
root.geometry("1920x1080")  # Fixed: was self.window.geometry


# Dynamic path
script_dir = os.path.dirname(os.path.abspath(__file__))
project_root = os.path.abspath(os.path.join(script_dir, "..", ".."))

# Background - resized to match 1920x1009
bg_path = os.path.join(project_root, "res", "Background.png")
bg_image = PhotoImage(file=bg_path)
canvas = tk.Canvas(root, width=1920, height=1009)
canvas.pack(fill="both", expand=True)
canvas.create_image(0, 0, anchor="nw", image=bg_image)

# User's Name - repositioned for larger canvas
canvas.create_text(960, 120, text="Player 1", fill="white", font=("Inter", 35, "bold"))

# HP bar configuration - repositioned and resized
segment_count = 5
segment_width = 100
segment_height = 30
spacing = 2  # space between segments
start_x = 710  # Centered around 960
start_y = 170
hp_segments = []
current_hp = 5  # The HP

# Create and store the segments (rectangles)
for i in range(segment_count):
    x1 = start_x + i * (segment_width + spacing)
    y1 = start_y
    x2 = x1 + segment_width
    y2 = y1 + segment_height
    rect = canvas.create_rectangle(x1, y1, x2, y2, fill="green", outline="black")
    hp_segments.append(rect)


# Updates the hp bar
def update_hp_bar(hp):
    for i in range(segment_count):
        color = "green" if i < hp else "red"
        canvas.itemconfig(hp_segments[i], fill=color)


# Update HP bar to show current HP
update_hp_bar(current_hp)

# Placement Images - adjusted subsample values for larger canvas
first_place_path = os.path.join(project_root, "res", "first place.png")
first_place_img = PhotoImage(file=first_place_path)
first_place_img = first_place_img.subsample(10, 10)  # Less subsample for bigger images

second_place_path = os.path.join(project_root, "res", "second place.png")
second_place_img = PhotoImage(file=second_place_path)
second_place_img = second_place_img.subsample(12, 12)

third_place_path = os.path.join(project_root, "res", "third place.png")
third_place_img = PhotoImage(file=third_place_path)
third_place_img = third_place_img.subsample(15, 15)

# Leaderboard Title
canvas.create_text(960, 280, text="LEADERBOARD", fill="white", font=("Inter", 48, "bold"))

# Player data
players = [
    {"name": "Player 2", "points": 1200},
    {"name": "Player 1", "points": 900},
    {"name": "Player 4", "points": 850},
    {"name": "Player 5", "points": 825},
    {"name": "Player 3", "points": 750}
]

# Starting position of the leaderboard - properly centered
table_width = 450  # Total width of the leaderboard table
start_x = 960 - (table_width // 2)  # Center the table around x=960
start_y = 380
row_height_spacing = 80

for i, player in enumerate(players):
    y = start_y + i * row_height_spacing

    # Column 1: Placement Img/Number
    if i == 0:
        canvas.create_image(start_x + 40, y + 15, image=first_place_img, anchor="center")
    elif i == 1:
        canvas.create_image(start_x + 40, y + 15, image=second_place_img, anchor="center")
    elif i == 2:
        canvas.create_image(start_x + 40, y + 15, image=third_place_img, anchor="center")
    else:
        canvas.create_text(start_x + 40, y + 10, text=str(i + 1), anchor="center",
                           font=("Inter", 28, "bold"), fill="white")

    # Column 2: Player name - increased font size and spacing
    canvas.create_text(start_x + 200, y + 30, text=player["name"], fill="white",
                       font=("Inter", 24, "bold"))

    # Column 3: Points - increased font size and spacing
    canvas.create_text(start_x + 500, y + 30, text=str(player["points"]), fill="gold",
                       font=("Inter", 24, "bold"))

    # Horizontal lines between rows (except after last row)
    if i < 4:
        canvas.create_line(start_x, y + 55, start_x + 450, y + 55, fill="white", width=4)

    # Vertical line after points column
    line_x = start_x + 450
    canvas.create_line(line_x, y - 15, line_x, y + row_height_spacing - 15, fill="white", width=4)

# Back Button - repositioned and resized for larger canvas
btn = tk.Button(
    root,
    text="BACK",
    font=("Inter", 24, "bold"),
    bg="#B22222",
    fg="white",
    activebackground="darkred",
    activeforeground="white",
    width=12,
    height=2
)
btn.place(x=810, y=850)  # Centered horizontally

root.mainloop()
import tkinter as tk
from PIL import Image, ImageTk

class RoundResultView(tk.Tk):
    def __init__(self, file_path, text, option):
        super().__init__()
        self.option = option

        # Window setup
        self.title("ROUND RESULT")
        self.geometry("724x459")
        self.configure(bg='#D3D3D3')
        self.resizable(False, False)
        self.overrideredirect(True)  # remove window decorations

        # center on screen
        self.update_idletasks()
        w, h = 724, 459
        ws, hs = self.winfo_screenwidth(), self.winfo_screenheight()
        x, y = (ws - w)//2, (hs - h)//2
        self.geometry(f"{w}x{h}+{x}+{y}")

        # Image (250×250)
        raw = Image.open(file_path)
        img = raw.resize((250, 250), Image.LANCZOS)
        self.photo = ImageTk.PhotoImage(img)
        self.image_label = tk.Label(self, image=self.photo, bg='#D3D3D3')
        self.image_label.place(x=237, y=h - 250 - 120)

        # Title message
        self.message_label = tk.Label(
            self,
            text=text,
            font=("Inter", 55, "bold"),
            fg='#FDEEEE',
            bg='#D3D3D3'
        )
        self.message_label.place(x=149, y=10, width=426, height=160)

        # Countdown
        self.count = 10
        self.round_label = tk.Label(
            self,
            text=f"Next Round in {self.count}s...",
            font=("Inter", 48, "bold"),
            fg='black',
            bg='#D3D3D3'
        )
        self.round_label.place(x=70, y=322)

        # start countdown
        self.after(1000, self._countdown)

    def _countdown(self):
        self.count -= 1
        if self.count <= 0:
            self.destroy()
            # Call controller logic here if needed
        else:
            self.round_label.config(text=f"Next Round in {self.count}s...")
            self.after(1000, self._countdown)


if __name__ == '__main__':
    app = RoundResultView("../../res/round_won.png", "YOU WON!", option=1)
    app.mainloop()

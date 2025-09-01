from tkinter import *
from tkinter import messagebox
from PIL import ImageTk, Image
import os

def resource_path(relative_path):
    return os.path.join(os.path.dirname(__file__), relative_path)


class signUp:
    def __init__(self):
        self.window = Tk()
        self.window.title("Sign Up")
        self.window.geometry("1920x1080")

        # Set window icon
        logo_path = resource_path("../../res/guess it logo.jpg")
        self.logo = ImageTk.PhotoImage(Image.open(logo_path))
        self.window.iconphoto(True, self.logo)

        # Set up canvas
        self.canvas = Canvas(self.window, width=1920, height=1009)
        self.canvas.place(x=0, y=0, relwidth=1, relheight=1)

        # Set background image
        bg_path = resource_path("../../res/Background.png")
        self.bg_image = ImageTk.PhotoImage(Image.open(bg_path))
        self.canvas.create_image(0, 0, image=self.bg_image, anchor="nw")

        # Create title text
        self.temp_toplabel = Label(self.window, text="WELCOME TO \n GUESS IT", font=("Arial", 66))
        self.temp_toplabel.update_idletasks()
        top_req_width = self.temp_toplabel.winfo_reqwidth()
        self.canvas.create_text((self.window.winfo_width() - top_req_width - 200) / 2, 15,
                                text="WELCOME TO \n GUESS IT",
                                font=("Arial", 66),
                                fill="black",
                                anchor="nw")
        self.temp_toplabel.destroy()

        # Add logo image without background
        logoWoBg_path = resource_path("../../res/guess it wobg.png")
        self.logoWoBg = ImageTk.PhotoImage(Image.open(logoWoBg_path).resize((200, 200)))
        logo_x = (self.window.winfo_width() - top_req_width - 200) / 2 + top_req_width
        logo_y = 15
        self.canvas.create_image(logo_x, logo_y, image=self.logoWoBg, anchor="nw")

        # Username field
        self.usernamefield = Entry(self.window, font=("Arial", 60))
        self.window.update_idletasks()
        self.usernamefield.place(x=int((self.window.winfo_width() - self.usernamefield.winfo_reqwidth()) / 2),
                                 y=320)
        self.window.update_idletasks()

        # Username label
        self.canvas.create_text(self.usernamefield.winfo_x(), self.usernamefield.winfo_y() - 40,
                                text="username",
                                font=("Arial", 20),
                                fill="black",
                                anchor="nw")

        # Password field
        self.passwordField = Entry(self.window, font=("Arial", 60), show='*')
        self.window.update_idletasks()
        self.passwordField.place(x=self.usernamefield.winfo_x(),
                                 y=self.usernamefield.winfo_y() + self.usernamefield.winfo_height() + 75)
        self.window.update_idletasks()

        # Password label
        self.canvas.create_text(self.passwordField.winfo_x(), self.passwordField.winfo_y() - 40,
                                text="password",
                                font=("Arial", 20),
                                fill="black",
                                anchor="nw")

        # reEnterPassword field
        self.reEnterPasswordField = Entry(self.window, font=("Arial", 60), show='*')
        self.window.update_idletasks()
        self.reEnterPasswordField.place(x=self.passwordField.winfo_x(),
                                        y=self.passwordField.winfo_y() + self.passwordField.winfo_height() + 75)
        self.window.update_idletasks()

        # reEnterPassword label
        self.canvas.create_text(self.reEnterPasswordField.winfo_x(), self.reEnterPasswordField.winfo_y() - 40,
                                text="re-enter password",
                                font=("Arial", 20),
                                fill="black",
                                anchor="nw")

        # Sign up button action
        def signUpFunc():
            username = self.usernamefield.get()
            password = self.passwordField.get()
            re_password = self.reEnterPasswordField.get()

        # Sign up button
        self.signBtn = Button(self.window, text="Sign up", command=signUpFunc, font=("Arial", 20),
                              fg="black", bg="white")
        self.signBtn.place(x=self.reEnterPasswordField.winfo_x(),
                           y=self.reEnterPasswordField.winfo_y() + self.reEnterPasswordField.winfo_height() + 50,
                           width=self.reEnterPasswordField.winfo_width() / 3,
                           height=self.reEnterPasswordField.winfo_height())

        # Log in button (actionless placeholder)
        self.window.update_idletasks()
        self.logInBtn = Button(self.window, text="log in",
                               font=("Arial", 20), fg="black", bg="light green")
        self.logInBtn.place(x=self.reEnterPasswordField.winfo_x() + self.signBtn.winfo_width(),
                            y=self.reEnterPasswordField.winfo_y() + self.reEnterPasswordField.winfo_height() + 50,
                            width=self.reEnterPasswordField.winfo_width() / 3,
                            height=self.reEnterPasswordField.winfo_height())

        # Exit button
        self.window.update_idletasks()
        self.exitBtn = Button(self.window, text="exit", command=self.window.quit, font=("Arial", 20),
                              fg="black", bg="white")
        self.exitBtn.place(x=self.logInBtn.winfo_x() + self.logInBtn.winfo_width(),
                           y=self.reEnterPasswordField.winfo_y() + self.reEnterPasswordField.winfo_height() + 50,
                           width=self.reEnterPasswordField.winfo_width() / 3,
                           height=self.reEnterPasswordField.winfo_height())

        self.window.mainloop()


if __name__ == '__main__':
    app = signUp()

class User:
    def __init__(self, user_id=0, username="", password="", user_type="", round_wins=0, game_wins=0, place=0):
        self.user_id = user_id
        self.username = username
        self.password = password
        self.user_type = user_type
        self.round_wins = round_wins
        self.game_wins = game_wins
        self.place = place

    # Getters and setters (optional in Python, but shown for clarity)

    def get_user_id(self):
        return self.user_id

    def set_user_id(self, user_id):
        self.user_id = user_id

    @staticmethod
    def get_username(self):
        return self.username

    @staticmethod
    def set_username(self, username):
        self.username = username

    def get_password(self):
        return self.password

    def set_password(self, password):
        self.password = password

    def get_user_type(self):
        return self.user_type

    def set_user_type(self, user_type):
        self.user_type = user_type

    def get_round_wins(self):
        return self.round_wins

    def set_round_wins(self, round_wins):
        self.round_wins = round_wins

    def get_game_wins(self):
        return self.game_wins

    def set_game_wins(self, game_wins):
        self.game_wins = game_wins

    def get_place(self):
        return self.place

    def set_place(self, place):
        self.place = place

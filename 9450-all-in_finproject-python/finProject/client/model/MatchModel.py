class MatchModel:
    def __init__(self):
        self.username = "Kenny"
        self.remTime = 25
        self.wordToGuess = "KennethBai"
        self.guessKey = ""
        self.hp = 5
        self.guessedLetters = []

    def getUsername(self):
        return self.username

    def getWordToGuess(self):
        return self.wordToGuess

    def getUsername(self):
        return self.username

    def getRemTime(self):
        return self.remTime

    def getGuessedLetters(self):
        return self.guessedLetters

    def addToGuessedLetters(self, guessedLetters):
        self.guessedLetters.append(guessedLetters)

    def getHp(self):
        return self.hp

    def reduceHp(self):
        self.hp = self.hp - 1

    def resetHp(self):
        self.hp = 5

    def resetGuessedLetters(self):
        self.guessedLetters = []

    def setWordToGuess(self,wordToGuess):
        self.wordToGuess = wordToGuess
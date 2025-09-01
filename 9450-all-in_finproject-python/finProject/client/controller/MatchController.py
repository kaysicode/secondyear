from finProject.client.model.MatchModel import MatchModel

class MatchController:
    def __init__(self):

        self.model = MatchModel()

    def getWord(self):
        return self.model.getWordToGuess()

    def getUsername(self):
        return self.model.getUsername()

    def getRemTimeSec(self):
        return self.model.getRemTime()

    def getGuessedLetters(self):
        return self.model.getGuessedLetters()

    def getRemainingAttempts(self):
        return self.model.getHp()

    def reduceHp(self):
        self.model.reduceHp()

    def resetHp(self):
        self.model.resetHp()

    def addToGuessedLetters(self, guess):
        self.model.addToGuessedLetters(guess)

    def resetGuessedLetters(self):
        self.model.resetGuessedLetters()

    def setWordToGuess(self):
        self.model.setWordToGuess("inputWordToGuessHere")
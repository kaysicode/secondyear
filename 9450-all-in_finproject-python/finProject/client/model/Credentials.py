class credentials:
    def __init__(self):
        self.credId = ""
        self.usernameStatic = ""
        self.username = ""
        self.password = ""
        self.type =""

    def credentials(self,credId,username,password,type):
        self.credId = credId
        self.usernameStatic = username
        self.username = username
        self.password = password
        self.type = type

    def getCredId(self):
        return self.credId

    def getUsernameStatic(self):
        return self.usernameStatic

    def setUserNameStatic(self,usernameStatic):
        self.usernameStatic = usernameStatic

    def getUsername(self):
        return self.username

    def setUsername(self,username):
        self.username = username

    def getPassword(self):
        return self.password
    def setPassword(self,password):
        self.password = password
    def getType(self):
        return self.type
    def setType(self,type):
        self.type = type

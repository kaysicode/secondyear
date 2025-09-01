
user_name = None
game_ID = None

def start_game(username, start_game_service, guess_word_service):
    """
    Starts or joins a game for the specified username.

    Args:
        username (str): The username of the player joining the game
    """
    is_game_exist = start_game_service.check_game_exist("WAITING")

    if not is_game_exist:
        game_id =start_game_service.create_game(username)
        global game_ID, user_name
        game_ID = game_id
        user_name = username

        print(f"DEBUG: game_id + createGame {game_id}")

        from finProject.temp.Start import Start
        Start.waiting_game(start_game_service, guess_word_service, game_ID, user_name)


    else:
        game_id = start_game_service.join_game(username)
        global game_ID, user_name
        game_ID = game_id
        user_name = username

        print(f"DEBUG: game_id + joinGame {game_id}")

        from finProject.temp.Start import Start
        Start.waiting_game(start_game_service, guess_word_service, game_ID, user_name)

    print(f"DEBUG: game_id + createGame 2 {game_ID}")


# If this file is run directly (not imported)
if __name__ == "__main__":
    # Example usage
    start_game("player1")
import threading
import time


class Start:
    @staticmethod
    def waiting_game(start_game_service, guess_word_service, game_ID, user_name):
        # Create a flag to control the timer
        timer_active = True

        def timer_tick():
            while timer_active:
                remaining = start_game_service.get_remaining_time()
                print(f"WAITING: {remaining}")

                if remaining <= 0:
                    # Stop the timer by breaking out of the loop
                    check_user = start_game_service.get_user_list()
                    arr = check_user.split(",")

                    if len(arr) == 1:  # aside from the creator / host
                        start_game_service.setCancelGame("CANCELED")

                        from finProject.temp.Main import main
                        main()

                    else:
                        guess_word_service.startGame()

                        from finProject.temp.Match import MatchPy

                        MatchPy.match_game(guess_word_service, game_ID, user_name)


                    break

                # Sleep for 1 second (1000 milliseconds)
                time.sleep(1)

        # Start the timer in a separate thread
        timer_thread = threading.Thread(target=timer_tick)
        timer_thread.daemon = True  # Allow the thread to be terminated when the main program exits
        timer_thread.start()

        # If you need to stop the timer from outside
        def stop_timer():
            nonlocal timer_active
            timer_active = False

        # Return the stop function in case it's needed elsewhere
        return stop_timer
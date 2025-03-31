package driver;


import controller.GameController;
import impl.Game;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameController gameController = GameController.getInstance();
        Game game = gameController.createGame(2, 2, 100);
        gameController.startGame(game);
    }
}

/*
SAMPLE OUTPUT:

Turn of player_0
Moved 0 -> 5
Turn of player_1
Moved 0 -> 5
Turn of player_0
Moved 5 -> 10
Turn of player_1
Moved 5 -> 10
Turn of player_0
Moved 10 -> 13
Turn of player_1
Moved 10 -> 19
Turn of player_0
Moved 13 -> 20
Turn of player_1
Moved 19 -> 26
Turn of player_0
Moved 20 -> 28
Turn of player_1
Moved 26 -> 34
Turn of player_0
Moved 28 -> 35
Turn of player_1
Moved 34 -> 42
Turn of player_0
Moved 35 -> 40
Turn of player_1
Moved 42 -> 48
Turn of player_0
Moved 40 -> 51
Turn of player_1
Moved 48 -> 54
Turn of player_0
Moved 51 -> 61
Turn of player_1
Moved 54 -> 60
Turn of player_0
Moved 61 -> 72
Turn of player_1
Moved 60 -> 66
Turn of player_0
Moved 72 -> 76
Turn of player_1
Moved 66 -> 73
Turn of player_0
Moved 76 -> 81
Turn of player_1
Moved 73 -> 80
Turn of player_0
Moved 81 -> 91
Turn of player_1
Moved 80 -> 86
Turn of player_0
Moved 91 -> 99
Turn of player_1
Moved 86 -> 90
Turn of player_0
Moved 99 -> 109
=====================
WON: player_0
=====================
 */
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
Wow...promoted 🪜
Moved 0 -> 16
Turn of player_1
Moved 0 -> 6
Turn of player_0
Moved 16 -> 22
Turn of player_1
Moved 6 -> 14
Turn of player_0
Moved 22 -> 30
Turn of player_1
Moved 14 -> 21
Turn of player_0
Moved 30 -> 37
Turn of player_1
Moved 21 -> 33
Turn of player_0
Moved 37 -> 43
Turn of player_1
Moved 33 -> 45
Turn of player_0
Moved 43 -> 54
Turn of player_1
Moved 45 -> 55
Turn of player_0
Moved 54 -> 62
Turn of player_1
Moved 55 -> 59
Turn of player_0
Moved 62 -> 69
Turn of player_1
Moved 59 -> 62
Turn of player_0
Ouch...got bitten 🐍
Moved 69 -> 60
Turn of player_1
Moved 62 -> 65
Turn of player_0
Moved 60 -> 67
Turn of player_1
Moved 65 -> 71
Turn of player_0
Moved 67 -> 75
Turn of player_1
Moved 71 -> 76
Turn of player_0
Moved 75 -> 81
Turn of player_1
Moved 76 -> 85
Turn of player_0
Moved 81 -> 86
Turn of player_1
Moved 85 -> 93
Turn of player_0
Moved 86 -> 97
Turn of player_1
Moved 93 -> 105
=====================
WON: player_1
=====================
 */
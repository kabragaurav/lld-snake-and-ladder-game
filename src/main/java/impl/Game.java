package impl;

import controller.DiceController;
import controller.PlayerController;

public class Game {
    private PlayerController playerController;
    private DiceController diceController;
    private Board board;

    public Game(PlayerController playerController, DiceController diceController, Board board) {
        this.playerController = playerController;
        this.diceController = diceController;
        this.board = board;
    }

    public void start() throws InterruptedException {
        while (true) {
            Player player = playerController.play(diceController, board);
            if (player.getCurrPos() >= board.getDim()) {
                System.out.println("=====================");
                System.out.println("WON: " + player.getName());
                System.out.println("=====================");
                return;
            }
        }
    }
}

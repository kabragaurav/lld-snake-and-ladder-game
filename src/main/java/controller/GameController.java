package controller;

import impl.*;
import interfaces.Jumper;

import java.util.*;

public class GameController {
    private static GameController gameController;
    private List<Game> games;
    private Random random;      // to randomly generate snakes and ladders

    // SINGLETON that manages multiple game sessions
    private GameController() {
        games = new ArrayList<>();
        random = new Random();
    }

    // TODO: Bill-Pugh Solution
    public static synchronized GameController getInstance() {
        if (gameController == null) {
            gameController = new GameController();
        }
        return gameController;
    }

    public Game createGame(int numberOfPlayers, int numberOfDices, int boardDim) {
        PlayerController playerController = new PlayerController(numberOfPlayers);
        DiceController diceController = new DiceController(numberOfDices);
        List<Player> players = playerController.getPlayers();
        Map<Integer, Integer> playerIdToPos = new HashMap<>();
        for (Player player : players) {
            playerIdToPos.put(player.getId(), 0);
        }
        List<Jumper> snakes = getSnakes();
        List<Jumper> ladders = getLadders();
        Board board = new Board(boardDim, snakes, ladders, playerIdToPos);
        Game game = new Game(playerController, diceController, board);
        games.add(game);
        return game;
    }

    private List<Jumper> getLadders() {
        int numberOfLadders = random.nextInt(10) + 1; // 1 to 10 ladders
        List<Jumper> ladders = new ArrayList<>();
        for (int i=0; i<numberOfLadders; i++) {
            // start between 6 and 15
            int start = random.nextInt(10) + 6;
            // end between 7 and 95
            ladders.add(new Ladder(start, start+random.nextInt(80) + 1));
        }
        return ladders;
    }

    private List<Jumper> getSnakes() {
        int numberOfSnakes = random.nextInt(10) + 1; // 1 to 10 snakes
        List<Jumper> snakes = new ArrayList<>();
        for (int i=0; i<numberOfSnakes; i++) {
            // start between 7 and 95
            int start = random.nextInt(80) + 1;
            // end between 6 and 15
            int delta = Math.max(1, random.nextInt(10) + 6);
            snakes.add(new Snake(start, start-delta));
        }
        return snakes;
    }

    // Each game starts in a separate thread to allow concurrent game sessions
    public void startGame(Game game) throws InterruptedException {
        Thread tGame = new Thread(() -> {
            try {
                game.start();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        tGame.start();
        tGame.join();
        games.remove(game);
    }
}

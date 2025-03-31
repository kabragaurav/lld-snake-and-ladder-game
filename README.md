# Snake & Ladder Game
by Gaurav Kabra

---

## Requirements
- Multiplayer game
- Customizable in terms of size, position of snakes and ladders and number of dices
- If a player lands on a cell with the head of a snake, they should slide down to the cell with the tail of the snake.
- If a player lands on a cell with the base of a ladder, they should climb up to the cell at the top of the ladder.
- If multiple ladder or snakes are coming on climbed or sliding down, only first one is considered.
- The game should continue until one of the players reaches on or beyond the final cell on the board.
- The game should handle multiple game sessions concurrently, allowing different groups of players to play independently.
- Snakes cannot start and end at same point. Same for ladders.
- If a cell has both ladder and snake, snake is preferred.
---


## Interfaces And Abstract Classes

```java
package interfaces;

import enums.JumperType;

public abstract class Jumper {
    protected JumperType type;
    protected int start;
    protected int end;

    public Jumper(JumperType type, int start, int end) {
        validate(type, start, end);
        this.type = type;
        this.start = start;
        this.end = end;
    }

    private void validate(JumperType type, int start, int end) {
        if (type == JumperType.LADDER) {
            assert start < end;
        } else if (type == JumperType.SNAKE) {
            assert start > end;
        }
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
```

## Enums

```java
package enums;

public enum JumperType {
    SNAKE,
    LADDER,
    ;
}
```

## Impl

```java
package impl;

import java.util.Random;

public class Dice {
    private int minNumberInclusive;
    private int maxNumberInclusive;
    private Random random;

    public Dice(int minNumberInclusive, int maxNumberInclusive) {
        this.minNumberInclusive = minNumberInclusive;
        this.maxNumberInclusive = maxNumberInclusive;
        random = new Random();
    }

    public int roll() {
        return random.nextInt(6) + 1;
    }
}
```

```java
package impl;

public class Player {
    private int id;
    private String name;
    private int currPos;
    // and other details like memberSince, lastModified, email, address, age, gender etc.

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCurrPos() {
        return currPos;
    }

    public void setCurrPos(int currPos) {
        this.currPos = currPos;
    }
}
```

```java
package impl;

import enums.JumperType;
import interfaces.Jumper;

public class Snake extends Jumper {
    public Snake(int start, int end) {
        super(JumperType.SNAKE, start, end);
    }
}
```

```java
package impl;

import enums.JumperType;
import interfaces.Jumper;

public class Ladder extends Jumper {
    public Ladder(int start, int end) {
        super(JumperType.LADDER, start, end);
    }
}
```

```java
package impl;

import interfaces.Jumper;

import java.util.List;

public class Board {
    private int dim;
    private List<Jumper> snakes;
    private List<Jumper> ladders;

    public Board(int dim, List<Jumper> snakes, List<Jumper> ladders) {
        this.dim = dim;
        this.snakes = snakes;
        this.ladders = ladders;
    }

    public int getDim() {
        return dim;
    }

    public List<Jumper> getSnakes() {
        return snakes;
    }

    public List<Jumper> getLadders() {
        return ladders;
    }
}
```

```java
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
```

## Controllers

```java
package controller;

import impl.Dice;

import java.util.ArrayList;
import java.util.List;

public class DiceController {
    private List<Dice> dices;

    public DiceController(int numberOfDices) {
        dices = new ArrayList<>();
        for (int i=0; i<numberOfDices; i++) {
            dices.add(new Dice(1, 6));
        }
    }

    public int rollDices() {
        int sum = 0;
        for (Dice dice : dices) {
            sum += dice.roll();
        }
        return sum;
    }
}
```

```java
package controller;

import impl.Board;
import impl.Player;
import interfaces.Jumper;

import java.util.ArrayList;
import java.util.List;

public class PlayerController {
    private List<Player> players;
    private int currPlayerNumber;

    public PlayerController(int numberOfPlayers) {
        players = new ArrayList<>();
        for (int i=0; i<numberOfPlayers; i++) {
            players.add(new Player(i, "player_" + i));
        }
    }

    public Player play(DiceController diceController, Board board) throws InterruptedException {
        Player currPlayer = players.get(currPlayerNumber);
        System.out.println("Turn of " + currPlayer.getName());
        int start = currPlayer.getCurrPos();
        int delta = diceController.rollDices();
        int end = start + delta;

        // logic to move to end and see if ladder and snake there
        // if so move ahead one more time
        List<Jumper> snakes = board.getSnakes();
        boolean gotBitten = false;
        for (Jumper snake : snakes) {
            if (snake.getStart() == end) {
                System.out.println("Ouch...got bitten 🐍");
                end = snake.getEnd();
                gotBitten = true;
                break;
            }
        }
        end = Math.max(0, end);

        List<Jumper> ladders = board.getLadders();
        // if player came here after being bitten
        // or a cell has both ladder and snake, snake is preferred
        if (!gotBitten) {
            for (Jumper ladder : ladders) {
                if (ladder.getStart() == end) {
                    System.out.println("Wow...promoted 🪜");
                    end = ladder.getEnd();
                    break;
                }
            }
        }

        System.out.println("Moved " + start + " -> " + end);
        currPlayer.setCurrPos(end);
        currPlayerNumber = (currPlayerNumber+1) % players.size();
        Thread.sleep(1000);     // simulation manual rolling of dice and moving of pieces
        return currPlayer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
```

```java
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
        List<Jumper> snakes = getSnakes();
        List<Jumper> ladders = getLadders();
        Board board = new Board(boardDim, snakes, ladders);
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
```

## Driver

```java
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
```


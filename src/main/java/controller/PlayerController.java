package controller;

import impl.Board;
import impl.Player;

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
        // update board
        // return end position
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

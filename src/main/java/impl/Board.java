package impl;

import interfaces.Jumper;
import java.util.Map;

import java.util.List;

public class Board {
    private int dim;
    private List<Jumper> snakes;
    private List<Jumper> ladders;
    private Map<Integer, Integer> playerIdToPos;

    public Board(int dim, List<Jumper> snakes, List<Jumper> ladders, Map<Integer, Integer> playerIdToPos) {
        this.dim = dim;
        this.snakes = snakes;
        this.ladders = ladders;
        this.playerIdToPos = playerIdToPos;
    }

    public int getDim() {
        return dim;
    }
}

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

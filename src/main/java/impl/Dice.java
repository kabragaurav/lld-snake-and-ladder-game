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

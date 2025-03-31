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

package impl;

import enums.JumperType;
import interfaces.Jumper;

public class Ladder extends Jumper {
    public Ladder(int start, int end) {
        super(JumperType.LADDER, start, end);
    }
}

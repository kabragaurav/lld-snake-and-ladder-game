package impl;

import enums.JumperType;
import interfaces.Jumper;

public class Snake extends Jumper {
    public Snake(int start, int end) {
        super(JumperType.SNAKE, start, end);
    }
}

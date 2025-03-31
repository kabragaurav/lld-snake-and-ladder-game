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
}

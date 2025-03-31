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

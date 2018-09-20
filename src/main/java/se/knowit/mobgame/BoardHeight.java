package se.knowit.mobgame;

public class BoardHeight {
    private int value;

    private BoardHeight(int value) {
        this.value = value;
    }

    public static BoardHeight of(int value) {
        return new BoardHeight(value);
    }

    public int getValue() {
        return value;
    }
}

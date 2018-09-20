package se.knowit.mobgame;

public class BoardWidth {
    private int value;

    private BoardWidth(int value) {
        this.value = value;
    }

    public static BoardWidth of(int value) {
        return new BoardWidth(value);
    }

    public int getValue() {
        return value;
    }
}

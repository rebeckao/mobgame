package se.knowit.mobgame;

public class YCoordinate {
    private int value;

    private YCoordinate(int value) {
        this.value = value;
    }

    public static YCoordinate of(int value) {
        return new YCoordinate(value);
    }

    public int getValue() {
        return value;
    }
}

package se.knowit.mobgame;

public class XCoordinate {
    private int value;

    private XCoordinate(int value) {
        this.value = value;
    }

    public static XCoordinate of(int value) {
        return new XCoordinate(value);
    }

    public int getValue() {
        return value;
    }
}

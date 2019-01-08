package se.knowit.mobgame;

import lombok.ToString;

@ToString
public class Position {

    private XCoordinate x;
    private YCoordinate y;

    public Position(XCoordinate x, YCoordinate y) {
        this.x = x;
        this.y = y;
    }

    Position toLeft() {
        return new Position(XCoordinate.of(x.getValue() - 1), y);
    }

    public XCoordinate getX() {
        return x;
    }

    public YCoordinate getY() {
        return y;
    }

    public Position toRight() {
        return new Position(XCoordinate.of(x.getValue() + 1), y);
    }
}

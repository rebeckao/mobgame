package se.knowit.mobgame;

import lombok.ToString;

@ToString
public class Position {

    private XCoordinate x;
    private YCoordinate y;

    Position(XCoordinate x, YCoordinate y) {
        this.x = x;
        this.y = y;
    }

    Position toLeft() {
        return new Position(XCoordinate.of(x.getValue() - 1), y);
    }

    Position toRight() {
        return new Position(XCoordinate.of(x.getValue() + 1), y);
    }

    Position above() {
        return new Position(x, YCoordinate.of(y.getValue() - 1));
    }

    Position below() {
        return new Position(x, YCoordinate.of(y.getValue() + 1));
    }

    XCoordinate getX() {
        return x;
    }

    YCoordinate getY() {
        return y;
    }
}

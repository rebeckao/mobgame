package se.knowit.mobgame;

public class Position {

    private XCoordinate x;
    private YCoordinate y;


    public Position(XCoordinate x, YCoordinate y) {
        this.x = x;
        this.y = y;
    }

    public XCoordinate getX() {
        return x;
    }

    public YCoordinate getY() {
        return y;
    }
}

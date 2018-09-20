package se.knowit.mobgame;

public interface Board {

    int[][] getRawBoard();

    boolean isMoveValid(Position position);
}

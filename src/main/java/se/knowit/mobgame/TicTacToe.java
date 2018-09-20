package se.knowit.mobgame;

public class TicTacToe implements Board {
    private int[][] rawBoard;

    public TicTacToe(BoardHeight height, BoardWidth width) {
        rawBoard = new int[height.getValue()][];
        for (int i = 0; i < rawBoard.length; i++) {
            rawBoard[i] = new int[width.getValue()];
        }
    }

    @Override
    public int[][] getRawBoard() {
        return rawBoard.clone();
    }

    @Override
    public boolean isMoveValid(Position position) {
        return getValueAt(position) == 0;
    }

    private int getValueAt(Position position) {
        return rawBoard[position.getY().getValue()][position.getX().getValue()];
    }
}

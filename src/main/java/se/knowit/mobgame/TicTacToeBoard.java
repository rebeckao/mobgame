package se.knowit.mobgame;

public class TicTacToeBoard implements Board {
    private int[][] rawBoard;

    TicTacToeBoard(BoardHeight height, BoardWidth width) {
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

    int getValueAt(Position position) {

        if (!isInsideBoard(position)) {
            return -1;
        }

        return rawBoard[position.getY().getValue()][position.getX().getValue()];
    }

    void update(Position position, int playerId) {
        rawBoard[position.getY().getValue()][position.getX().getValue()] = playerId;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int[] row : rawBoard) {
            for (int aRow : row) {
                s.append(aRow);
            }
            s.append("\n");
        }
        return s.toString();
    }

    private boolean isInsideBoard(Position position) {
        return position.getX().getValue() < this.rawBoard[0].length
                && position.getY().getValue() < this.rawBoard.length
                && position.getY().getValue() >= 0
                && position.getX().getValue() >= 0;
    }

    boolean isFull() {
        for (int[] row : rawBoard) {
            for (int cell : row) {
                if (cell == 0)
                    return false;
            }
        }
        return true;
    }
}

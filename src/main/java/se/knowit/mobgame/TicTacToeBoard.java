package se.knowit.mobgame;

public class TicTacToeBoard implements Board {
    private int[][] rawBoard;

    public TicTacToeBoard(BoardHeight height, BoardWidth width) {
        rawBoard = new int[height.getValue() + 1][];
        for (int i = 0; i < rawBoard.length; i++) {
            rawBoard[i] = new int[width.getValue() + 1];
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
        return rawBoard[position.getY().getValue()][position.getX().getValue()];
    }

    void update(Position position, int playerId) {
        rawBoard[position.getY().getValue()][position.getX().getValue()] = playerId;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < rawBoard.length; i++) {
            int[] row = rawBoard[i];
            for (int j = 1; j < row.length; j++) {
                s.append(row[j]);
            }
            s.append("\n");
        }
        return s.toString();
    }
}

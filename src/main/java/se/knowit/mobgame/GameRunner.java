package se.knowit.mobgame;

import java.util.List;

public class GameRunner {
    private List<Player> players;
    private TicTacToeBoard board;

    public void playOneTurn() {
        players.forEach(this::superMegaDance);
    }

    private void superMegaDance(Player player) {
        Position position = player.getBot().makeMove(board);
        boolean isValid = board.isMoveValid(position);
        if (isValid) {
            board.update(position, player.getPlayerId());
        }
    }

    private void createBoard(BoardWidth width, BoardHeight height) {
        board = new TicTacToeBoard(height, width);
    }

}

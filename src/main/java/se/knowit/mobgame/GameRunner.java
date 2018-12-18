package se.knowit.mobgame;

import java.util.List;

public class GameRunner {
    private List<Player> players;
    private TicTacToeBoard board;
    private boolean gameIsWon;

    public GameRunner(TicTacToeBoard board, List<Player> players)  {
        this.board = board;
        this.players = players;
    }

    public void run() {
        while (!this.gameIsWon) {
            playOneTurn();
        }
    }

    public void playOneTurn() {
        players.forEach(this::makeAMove);
    }

    private void makeAMove(Player player) {
        Position position = player.getBot().makeMove(board);
        boolean isValid = board.isMoveValid(position);
        if (isValid) {
            board.update(position, player.getPlayerId());
            gameIsWon = superMegaDance(position, player);
        }
    }

    private boolean superMegaDance(Position position, Player player){
        System.out.println(board);
        Position left = position.toLeft();
        int playerId = player.getPlayerId();
        if (board.getValueAt(left) == playerId) {
            Position leftLeft = left.toLeft();
            if (board.getValueAt(leftLeft) == playerId) {
                return true;
            }
            Position right = position.toRight();
            if (board.getValueAt(right) == playerId) {
                return true;
            }
        }

        return false;
    }

    public boolean isGameWon() {
        return gameIsWon;
    }
}

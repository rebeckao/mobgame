package se.knowit.mobgame;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
class GameRunner {
    private List<Player> players;
    private TicTacToeBoard board;
    private boolean gameIsWon;

    GameRunner(TicTacToeBoard board, List<Player> players)  {
        this.board = board;
        this.players = players;
    }

    void run() {
        while (!this.gameIsWon) {
            playOneTurn();
        }
    }

    private void playOneTurn() {
        players.forEach(this::makeAMove);
    }

    private void makeAMove(Player player) {
        Position position = player.getBot().makeMove(board);
        boolean isValid = board.isMoveValid(position);
        if (isValid) {
            board.update(position, player.getPlayerId());
            gameIsWon = gameIsWon(position, player);
        } else {
            log.warn("move {} is invalid", position);
        }
    }

    private boolean gameIsWon(Position position, Player player){
        System.out.println(board);
        int playerId = player.getPlayerId();
        return hasWonHorizontally(position, playerId) || hasWonVertically(position, playerId);

    }

    private boolean hasWonVertically(Position position, int playerId) {
        Position above = position.above();
        if (board.getValueAt(above) == playerId) {
            Position aboveAbove = above.above();
            if (board.getValueAt(aboveAbove) == playerId) {
                return true;
            }
            Position below = position.below();
            if (board.getValueAt(below) == playerId) {
                return true;
            }
        }

        Position below = position.below();
        if (board.getValueAt(below) == playerId) {
            Position belowBelow = below.below();
            if (board.getValueAt(belowBelow) == playerId) {
                return true;
            }
        }
        return false;


    }

    private boolean hasWonHorizontally(Position position, int playerId) {
        Position left = position.toLeft();
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

        Position right = position.toRight();
        if (board.getValueAt(right) == playerId) {
            Position rightRight = right.toRight();
            return board.getValueAt(rightRight) == playerId;
        }
        return false;
    }

    boolean isGameWon() {
        return gameIsWon;
    }
}

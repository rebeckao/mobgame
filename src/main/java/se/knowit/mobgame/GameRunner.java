package se.knowit.mobgame;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static se.knowit.mobgame.PlayerStatus.EXPELLED;
import static se.knowit.mobgame.PlayerStatus.PLAYING;
import static se.knowit.mobgame.PlayerStatus.WINNER;

@Slf4j
class GameRunner {
    private List<Player> players;
    private TicTacToeBoard board;
    private boolean gameIsWon;

    GameRunner(TicTacToeBoard board, List<Player> players) {
        this.board = board;
        this.players = players;
    }

    void run() {
        while (!this.gameIsWon && hasPlayersLeft()) {
            playOneTurn();
        }
    }

    private void playOneTurn() {
        for (Player player : players) {
            if (this.gameIsWon) break;
            makeAMove(player);
        }
    }

    private boolean hasPlayersLeft() {
        return players.stream()
                .map(Player::getStatus)
                .anyMatch(status -> status.equals(PLAYING));
    }

    private void makeAMove(Player player) {
        Position position = player.getBot().makeMove(board);
        boolean isValid = board.isMoveValid(position);
        if (isValid) {
            board.update(position, player.getPlayerId());
            gameIsWon = gameIsWon(position, player);
            if (gameIsWon) {
                player.setStatus(WINNER);
            }
        } else {
            log.warn("Player {} is status due to invalid move {}", player.getPlayerId(), position);
            player.setStatus(EXPELLED);
        }
    }

    private boolean gameIsWon(Position position, Player player) {
        System.out.println(board);
        int playerId = player.getPlayerId();
        return hasWonHorizontally(position, playerId)
                || hasWonVertically(position, playerId)
                || hasWonSlopingDiagonally(position, playerId)
                || hasWonRisingDiagonally(position, playerId);

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
            return board.getValueAt(belowBelow) == playerId;
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

    private boolean hasWonSlopingDiagonally(Position position, int playerId) {
        Position aboveLeft = position.toLeft().above();
        if (board.getValueAt(aboveLeft) == playerId) {
            Position aboveLeftAboveLeft = aboveLeft.toLeft().above();
            if (board.getValueAt(aboveLeftAboveLeft) == playerId) {
                return true;
            }
            Position belowRight = position.toRight().below();
            if (board.getValueAt(belowRight) == playerId) {
                return true;
            }
        }

        Position belowRight = position.toRight().below();
        if (board.getValueAt(belowRight) == playerId) {
            Position belowRightBelowRight = belowRight.toRight().below();
            return board.getValueAt(belowRightBelowRight) == playerId;
        }
        return false;
    }

    private boolean hasWonRisingDiagonally(Position position, int playerId) {
        Position belowLeft = position.toLeft().below();
        if (board.getValueAt(belowLeft) == playerId) {
            Position belowLeftBelowLeft = belowLeft.toLeft().below();
            if (board.getValueAt(belowLeftBelowLeft) == playerId) {
                return true;
            }
            Position aboveRight = position.toRight().above();
            if (board.getValueAt(aboveRight) == playerId) {
                return true;
            }
        }

        Position aboveRight = position.toRight().above();
        if (board.getValueAt(aboveRight) == playerId) {
            Position aboveRightAboveRight = aboveRight.toRight().above();
            return board.getValueAt(aboveRightAboveRight) == playerId;
        }
        return false;
    }

    boolean isGameWon() {
        return gameIsWon;
    }
}

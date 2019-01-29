package se.knowit.mobgame;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;

import static se.knowit.mobgame.PlayerStatus.EXPELLED;
import static se.knowit.mobgame.PlayerStatus.PLAYING;
import static se.knowit.mobgame.PlayerStatus.WINNER;

@Slf4j
class GameRunner {
    private List<Player> players;
    private int winCondition;
    private TicTacToeBoard board;
    private boolean gameIsOver;

    GameRunner(TicTacToeBoard board, List<Player> players, int winCondition) {
        this.board = board;
        this.players = players;
        this.winCondition = winCondition;
    }

    void run() {
        while (!this.gameIsOver && hasPlayersLeft()) {
            playOneTurn();
        }
    }

    private void playOneTurn() {
        for (Player player : players) {
            if (this.gameIsOver) break;
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
            if (gameIsWon(position, player)) {
                gameIsOver = true;
                player.setStatus(WINNER);
            }
        } else {
            log.warn("Player {} is status due to invalid move {}", player.getPlayerId(), position);
            player.setStatus(EXPELLED);
        }
        if (board.isFull()) {
            gameIsOver = true;
        }
    }

    private boolean gameIsWon(Position position, Player player) {
        System.out.println(board);
        int playerId = player.getPlayerId();
        return hasWon(position, playerId, Position::toLeft, Position::toRight)
                || hasWon(position, playerId, Position::below, Position::above)
                || hasWon(position, playerId, p -> p.toLeft().above(), p -> p.toRight().below())
                || hasWon(position, playerId, p -> p.toLeft().below(), p -> p.toRight().above());
    }

    private boolean hasWon(Position position, int playerId,
                           Function<Position, Position> positiveOffset,
                           Function<Position, Position> negativeOffset) {
        int connectedDots = 1;
        Position next = positiveOffset.apply(position);
        while (board.getValueAt(next) == playerId) {
            connectedDots++;
            next = positiveOffset.apply(next);
        }

        Position previous = negativeOffset.apply(position);
        while (board.getValueAt(previous) == playerId) {
            connectedDots++;
            previous = negativeOffset.apply(previous);
        }
        return connectedDots >= winCondition;
    }

    boolean isGameOver() {
        return gameIsOver;
    }
}

package se.knowit.mobgame;

import se.knowit.mobgame.bot.Bot;

import java.util.List;

public class GameRunner {
    private List<Bot> bots;
    private Board board;

    public void playOneTurn(){
        bots.forEach(this::superMegaDance);
    }

    private void superMegaDance(Bot bot) {
        Position position = bot.makeMove(board);
        board.isMoveValid(position);
    }

    private void createBoard(BoardWidth width, BoardHeight height) {
         board = new TicTacToe(height, width);
    }

}

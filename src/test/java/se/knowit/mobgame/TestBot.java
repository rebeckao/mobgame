package se.knowit.mobgame;

import se.knowit.mobgame.bot.Bot;

import java.util.Queue;

public class TestBot implements Bot {

    private final Queue<Position> positions;

    TestBot(Queue<Position> positions) {
        this.positions = positions;
    }

    @Override
    public Position makeMove(Board state) {
        return positions.remove();
    }
}

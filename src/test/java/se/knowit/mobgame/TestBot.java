package se.knowit.mobgame;

import se.knowit.mobgame.bot.Bot;

import java.util.ArrayList;
import java.util.List;

public class TestBot implements Bot {

    private final List<Position> positions;

    TestBot(List<Position> positions) {
        this.positions = new ArrayList<>(positions);
    }

    @Override
    public Position makeMove(Board state) {
        return positions.get(0);
    }
}

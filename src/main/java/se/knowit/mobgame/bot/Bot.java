package se.knowit.mobgame.bot;

import se.knowit.mobgame.Position;
import se.knowit.mobgame.Board;

public interface Bot {
    Position makeMove(Board state);
}

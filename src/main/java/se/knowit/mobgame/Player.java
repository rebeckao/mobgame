package se.knowit.mobgame;

import lombok.Getter;
import lombok.Setter;
import se.knowit.mobgame.bot.Bot;

@Getter
@Setter
public class Player {

    private Bot bot;
    private int playerId;
    private boolean expelled;

    public Player(Bot bot, int playerId) {
        this.bot = bot;
        this.playerId = playerId;
        this.expelled = false;
    }

}

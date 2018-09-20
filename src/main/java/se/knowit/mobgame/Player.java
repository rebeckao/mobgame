package se.knowit.mobgame;

import se.knowit.mobgame.bot.Bot;

public class Player {

    private Bot bot;
    private int playerId;

    public Player(Bot bot, int playerId) {
        this.bot = bot;
        this.playerId = playerId;
    }

    public Bot getBot() {
        return bot;
    }

    public int getPlayerId() {
        return playerId;
    }
}

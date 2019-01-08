package se.knowit.mobgame;

import lombok.Getter;
import lombok.Setter;
import se.knowit.mobgame.bot.Bot;

@Getter
@Setter
class Player {

    private Bot bot;
    private int playerId;
    private PlayerStatus status;

    Player(Bot bot, int playerId) {
        this.bot = bot;
        this.playerId = playerId;
        this.status = PlayerStatus.PLAYING;
    }

}

package Controller;

import Entity.Card;
import UseCase.PlayerManager;

public class PlayerManagerData {

    private PlayerManager playerManager;

    public PlayerManagerData(int numberOfPlayers, Card c) {
        this.playerManager = new PlayerManager(numberOfPlayers, c);
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}

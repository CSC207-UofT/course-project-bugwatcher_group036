package Controller;

import Entity.Card;
import Entity.Player;
import UseCase.DeckManager;
import UseCase.PlayerManager;

import java.util.Scanner;

public class ControllerBuilder implements Builder {

    private PlayerManager playerManager;
    private DeckManager cardManager;
    private final int numberOfPlayers;

    public ControllerBuilder(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
    }

    public void buildPlayerManager(){
        playerManager = new PlayerManager(numberOfPlayers);

        for (int i = 0; i < numberOfPlayers; i++) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("enter a player name for player " + (i+1) + ":");
            String playerID = keyboard.nextLine();
            playerManager.createPlayer(playerID, i);
        }
    }

    public void buildDeckManager(){
        cardManager = new DeckManager();
    }

    public Controller buildController(){
        for (int i = 0; i < 7; i++) {
            for (Player p: playerManager) {
                Card c = cardManager.drawCardFromUnusedDeck();
                p.drawCard(c);
            }
        }
        Controller temp = new Controller();
        temp.setCardManager(cardManager);
        temp.setPlayerManager(playerManager);
        return temp;
    }

    public Controller buildUnoController(){
        this.buildPlayerManager();
        this.buildDeckManager();
        return this.buildController();
    }
}

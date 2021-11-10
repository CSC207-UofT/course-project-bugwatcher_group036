package Controller;

import Entity.Card;
import Entity.Player;
import UseCase.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ControllerBuilder implements Builder {

    private PlayerManager playerManager;
    private DeckManager cardManager;
    private final int numberOfPlayers;
    private ArrayList<String> colors;
    private Dealer dealer;
    private EachRound eachRound;
    private BasicOperations basicOperations;

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

    public void cardDeal(){
        for (int i = 0; i < 7; i++) {
            for (Player p: playerManager) {
                Card c = cardManager.drawCardFromUnusedDeck();
                p.drawCard(c);
            }
        }
    }

    public void buildColors(){
        Readfile readfile = new cardreadfile();
        this.colors = readfile.readFromFile("src/main/resources/numbercards.txt",
                "src/main/resources/functioncards.txt", cardManager);
    }

    public void buildDealer(){
        this.dealer = new Dealer(playerManager, cardManager);
    }

    public void buildBasicOperations(){
        Status status = new Status(numberOfPlayers);
        GameBoard gameBoard = new GameBoard(numberOfPlayers);
        this.basicOperations = new BasicOperations(status, gameBoard);
    }

    public void buildEachRound(){
        this.eachRound = new EachRound(playerManager, cardManager, dealer, basicOperations);
    }

    public Controller buildUnoController(){
        this.buildPlayerManager();
        this.buildDeckManager();
        this.buildColors();
        this.cardDeal();
        this.buildDealer();
        this.buildBasicOperations();
        this.buildEachRound();
        Controller temp = new Controller();
        temp.setBasicOperations(basicOperations);
        temp.setEachRound(eachRound);

        return temp;
    }
}

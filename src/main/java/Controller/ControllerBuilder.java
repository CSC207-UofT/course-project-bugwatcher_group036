package Controller;

import Entity.Card;
import Entity.Player;
import UseCase.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerBuilder implements Builder {

//    private PlayerManager playerManager;
//    private DeckManager deckManager;
    private PlayerManagerData playerManagerData;
    private DeckManagerData deckManagerData;
    private final int numberOfPlayers;
    private ArrayList<String> colors;
    private Dealer dealer;
    private EachRound eachRound;
//    private BasicOperations basicOperations;
    private BasicOperationsData basicOperationsData;

    public ControllerBuilder(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
    }

    public void buildPlayerManager(){
        playerManagerData = new PlayerManagerData(numberOfPlayers, deckManagerData.getDeckManager().createNullCard());

        for (int i = 0; i < numberOfPlayers; i++) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("enter a player name for player " + (i+1) + ":");
//            String playerID = keyboard.nextLine();
            String playerID = JOptionPane.showInputDialog("enter a player name for player " + (i+1) + ":");
            playerManagerData.getPlayerManager().createPlayer(playerID, i);
        }
    }

    public void buildPlayerManagerForComputer(){
        playerManagerData = new PlayerManagerData(numberOfPlayers, deckManagerData.getDeckManager().createNullCard());
        Scanner keyboard = new Scanner(System.in);
        System.out.println("enter a player name for you:");
        String playerID = keyboard.nextLine();
        playerManagerData.getPlayerManager().createPlayer(playerID, 0);

        for (int i = 1; i < numberOfPlayers; i++) {
            // Just use Computer1, 2, 3 to call AI players
            playerManagerData.getPlayerManager().createPlayer
                    ("Computer " + i, i);
        }
    }

    public void buildDeckManager(){
        deckManagerData = new DeckManagerData();
    }

    public void cardDeal(){
        for (int i = 0; i < 7; i++) {
            for (Player p: playerManagerData.getPlayerManager()) {
                Card c = deckManagerData.getDeckManager().drawCardFromUnusedDeck();
                p.drawCard(c);
            }
        }
    }

    public void buildColors(){
        Readfile readfile = new Cardreadfile();
        this.colors = readfile.readFromFile("src/main/resources/numbercards.txt",
                "src/main/resources/functioncards.txt", deckManagerData);
    }

    public void buildDealer(){
        this.dealer = new Dealer(playerManagerData, deckManagerData);
    }

    public void buildBasicOperations(){
        StatusData statusData = new StatusData(numberOfPlayers);
        Status status = statusData.getStatus();
        GameBoard gameBoard = new GameBoard(numberOfPlayers, deckManagerData.getDeckManager());
        this.basicOperationsData = new BasicOperationsData(status, gameBoard,
                deckManagerData.getDeckManager(), playerManagerData.getPlayerManager());
    }

    public void buildEachRound(){
        this.eachRound = new EachRound(playerManagerData,
                deckManagerData, dealer, basicOperationsData);
    }

    public Controller buildUnoController(){
        this.buildDeckManager();
        this.buildPlayerManager();
        this.buildColors();
        this.cardDeal();
        this.buildDealer();
        this.buildBasicOperations();
        this.buildEachRound();
        Controller temp = new Controller();
        temp.setBasicOperationsData(basicOperationsData);
        temp.setEachRound(eachRound);

        return temp;
    }

    public Controller buildUnoControllerForComputer(){
        this.buildDeckManager();
        this.buildPlayerManagerForComputer();
        this.buildColors();
        this.cardDeal();
        this.buildDealer();
        this.buildBasicOperations();
        this.buildEachRound();
        Controller temp = new Controller();
        temp.setBasicOperationsData(basicOperationsData);
        temp.setEachRound(eachRound);

        return temp;

}

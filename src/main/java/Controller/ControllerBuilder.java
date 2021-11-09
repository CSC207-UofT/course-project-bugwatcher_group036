package Controller;

import Entity.Card;
import Entity.Player;
import UseCase.DeckManager;
import UseCase.PlayerManager;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ControllerBuilder implements Builder {

    private PlayerManager playerManager;
    private DeckManager cardManager;
    private final int numberOfPlayers;
    private Random rand;
    private ArrayList<String> num;
    private ArrayList<String> colors;
    private EachRound eachRound;
    private FunctionPlayed functionPlayed;

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

    public void buildRand(){
        this.rand = new Random();
    }

    public void buildNum(){
        this.num = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            num.add(Integer.toString(i));
        }
    }

    public void buildColors(){
        Readfile readfile = new Cardreadfile();
        this.colors = readfile.readFromFile("src/main/resources/numbercards.txt",
                "src/main/resources/functioncards.txt", cardManager);
    }

    public void buildEachRound() {
        this.eachRound = new EachRound(playerManager, cardManager, num, colors);
    }

    public void buildFunctionPlayed() {
        this.functionPlayed = new FunctionPlayed(playerManager, cardManager, num, colors);
    }

    public Controller buildUnoController(){
        this.buildPlayerManager();
        this.buildDeckManager();
        this.buildRand();
        this.buildNum();
        this.buildColors();
        this.cardDeal();
        this.buildEachRound();
        this.buildFunctionPlayed();
        Controller temp = new Controller();
        temp.setCardManager(cardManager);
        temp.setPlayerManager(playerManager);
        temp.setRand(rand);
        temp.setNum(num);
        temp.setColors(colors);
        temp.setEachRound(eachRound);
        temp.setFunctionPlayed(functionPlayed);

        return temp;
    }
}

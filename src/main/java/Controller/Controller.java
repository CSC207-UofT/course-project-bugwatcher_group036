package Controller;

import Entity.CardChecker;
import Entity.Deck;
import Entity.HandCard;
import UseCase.Dealer;
import UseCase.GameBoard;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    private ArrayList<String> ids;
    private int numberOfPlayers;
    private IEachRound iEachRound;

    public Controller(){
        this.ids = inputIDs();
        this.numberOfPlayers = ids.size();
    }

    public void buildIEachRound(){
        this.iEachRound = new EachRound(new GameBoard(numberOfPlayers),
                new Dealer(new Deck()), new CardChecker());
    }

    public String runGame(){
        int currentPlayerIndex = -1;
        boolean winFlag = iEachRound.getGameBoard().getStatus().isWinFlag();
        iEachRound.cardDeal(numberOfPlayers);

        while (!winFlag){
            currentPlayerIndex = iEachRound.getGameBoard().getStatus().getCurrentPlayerIndex();
            iEachRound.getTerminal().beginStage(currentPlayerIndex, ids);
            HandCard playableCards = iEachRound.beginStage();

            iEachRound.getTerminal().playStage(iEachRound.getGameBoard(), iEachRound.getCardChecker(), playableCards);
            String toPlay = iEachRound.playStage(playableCards, currentPlayerIndex);

            iEachRound.endStage(toPlay);

            winFlag = iEachRound.getGameBoard().getStatus().isWinFlag();
        }
        return ids.get(currentPlayerIndex);
    }

    public ArrayList<String> inputIDs(){
        Scanner input = new Scanner(System.in);
        ArrayList<String> ids = new ArrayList<>();
        System.out.println("How many players here? ");
        int numberOfPlayer = input.nextInt();
        input.nextLine();

        for (int i = 0; i < numberOfPlayer; i++){
            System.out.println("Please enter id for player" + (i + 1));
            String id = input.nextLine();
            ids.add(id);
        }

        return ids;
    }
}

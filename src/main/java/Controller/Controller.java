package Controller;

import UseCase.CardChecker;
import Entity.Deck;
import Entity.HandCard;
import UseCase.Dealer;
import UseCase.GameBoard;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Core of the program
 */
public class Controller {

    private final ArrayList<String> ids;
    private final int numberOfPlayers;
    private IEachRound iEachRound; // interface of eachRound, use dependency injection for clean architecture

    public Controller(){
        this.ids = inputIDs(); // get player ids and number of players
        this.numberOfPlayers = ids.size();
    }

    public Controller(boolean computer){
        this.ids = inputIDsForComputer(); // get player ids and number of players
        this.numberOfPlayers = ids.size();
    }

    public void buildIEachRound(){
        this.iEachRound = new EachRound(new GameBoard(numberOfPlayers),
                new Dealer(new Deck()), new CardChecker());
    }

    public String runGame(){
        int currentPlayerIndex = -1; // just initialize with a value, will be updates once enter the loop
        boolean winFlag = iEachRound.getGameBoard().getStatus().isWinFlag(); // initialize win flag
        iEachRound.cardDeal(numberOfPlayers); // let players draw cards

        while (!winFlag){
            // update current position
            currentPlayerIndex = iEachRound.getGameBoard().getStatus().getCurrentPlayerIndex();

            // system output and checking for begin stage, get playable cards for currentPlayer
            iEachRound.getTerminal().beginStage(currentPlayerIndex, ids);
            HandCard playableCards = iEachRound.beginStage();

            // system output and card-playing or punish based on status info and input
            // get cards currentPlayer will play, only invalid playing would return null
            iEachRound.getTerminal().playStage(iEachRound.getGameBoard(), iEachRound.getCardChecker(), playableCards);
            String toPlay = iEachRound.playStage(playableCards, currentPlayerIndex);

            // final check and preparation for next loop for end stage
            iEachRound.endStage(toPlay);

            // update winFlag after each round
            winFlag = iEachRound.getGameBoard().getStatus().isWinFlag();
        }



        // Case the choice is quit
        if (iEachRound.getGameBoard().getStatus().getCurrentPlayerIndex() < 0) {
            return null;
        }
        return ids.get(currentPlayerIndex); // return winner's id from ids
    }


    public String runGameForPVE() throws InterruptedException {
        int currentPlayerIndex = -1; // just initialize with a value, will be updates once enter the loop
        boolean winFlag = iEachRound.getGameBoard().getStatus().isWinFlag(); // initialize win flag
        iEachRound.cardDeal(numberOfPlayers); // let players draw cards

        while (!winFlag){
            // update current position
            currentPlayerIndex = iEachRound.getGameBoard().getStatus().getCurrentPlayerIndex();

            // system output and checking for begin stage, get playable cards for currentPlayer
            iEachRound.getTerminal().beginStage(currentPlayerIndex, ids);
            HandCard playableCards = iEachRound.beginStage();

            // system output and card-playing or punish based on status info and input
            // get cards currentPlayer will play, only invalid playing would return null
            iEachRound.getTerminal().playStage(iEachRound.getGameBoard(), iEachRound.getCardChecker(), playableCards);
            String toPlay = iEachRound.playStageForComputer(playableCards, currentPlayerIndex);

            // final check and preparation for next loop for end stage
            iEachRound.endStageForComputer(toPlay, currentPlayerIndex);

            // update winFlag after each round
            winFlag = iEachRound.getGameBoard().getStatus().isWinFlag();
        }

        // Case the choice is quit
        if (iEachRound.getGameBoard().getStatus().getCurrentPlayerIndex() < 0) {
            return null;
        }
        return ids.get(currentPlayerIndex); // return winner's id from ids
    }

    /**
     * Get player count and their ids
     * @return players' ids in a arrayList
     */
    public ArrayList<String> inputIDs(){
        Scanner input = new Scanner(System.in);
        ArrayList<String> ids = new ArrayList<>();
        System.out.println("How many players here? ");
        int numberOfPlayer = input.nextInt();

        if (numberOfPlayer < 1 || numberOfPlayer > 6) {
            System.out.println("Sorry, we only support 1 player - 6 players, please re-enter player count.");
            numberOfPlayer = input.nextInt();
        }
        input.nextLine(); // equals to "\n", otherwise would lead to bug for nextLine.

        for (int i = 0; i < numberOfPlayer; i++){
            System.out.println("Please enter id for player" + (i + 1));
            String id = input.nextLine();
            ids.add(id);
        }

        return ids;
    }

    public ArrayList<String> inputIDsForComputer(){
        Scanner input = new Scanner(System.in);
        ArrayList<String> ids = new ArrayList<>();
        System.out.println("How many players here? ");
        int numberOfPlayer = input.nextInt();

        if (numberOfPlayer < 1 || numberOfPlayer > 6) {
            System.out.println("Sorry, we only support 1 player - 6 players, please re-enter player count.");
            numberOfPlayer = input.nextInt();
        }
        input.nextLine(); // equals to "\n", otherwise would lead to bug for nextLine.

        System.out.println("Please enter your player id:");
        String playerID = input.nextLine();
        ids.add(playerID);

        for (int i = 1; i < numberOfPlayer; i++){
            ids.add("Computer " + Integer.toString(i));
        }

        return ids;
    }
}

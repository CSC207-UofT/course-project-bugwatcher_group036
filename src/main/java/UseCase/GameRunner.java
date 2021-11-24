package UseCase;

import Entity.CardHolder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Core of the program
 */
public class GameRunner implements IGameInput {

    private final ArrayList<String> ids;
    private final int numberOfPlayers;
    private EachRound eachRound; // interface of eachRound, use dependency injection for clean architecture
    private GameResponse gameResponse;
    private GameRequest gameRequest;

    public GameRunner(boolean computer, IPresenter iPresenter, GameRequest gameRequest, GameResponse gameResponse){
        this.gameRequest = gameRequest;
        this.gameResponse = gameResponse;
        if (computer) {
            iPresenter.inputIDsForComputer();
            this.ids = gameRequest.getIds(); // get player ids and number of players
        } else {
            iPresenter.inputIDs();
            this.ids = gameRequest.getIds(); // get player ids and number of players
        }
        this.numberOfPlayers = ids.size();
        this.gameResponse.setIds(ids);
        gameResponse.setGameBoard(new GameBoard(numberOfPlayers));
    }

    public void setGameResponse(GameResponse gameResponse) {
        this.gameResponse = gameResponse;
    }

    public GameResponse getGameResponse() {
        return gameResponse;
    }

    public void setGameRequest(GameRequest gameRequest) {
        this.gameRequest = gameRequest;
    }

    public GameRequest getGameRequest() {
        return gameRequest;
    }

    public void buildIEachRound(GameBoard gameBoard, IPresenter iPresenter, GameRequest gameRequest){
        this.eachRound = new EachRound(gameBoard, iPresenter, gameRequest);
    }

    public String runGame(){
        int currentPlayerIndex = -1; // just initialize with a value, will be updates once enter the loop
        boolean winFlag = eachRound.getGameBoard().getGameStatus().isWinFlag(); // initialize win flag
        eachRound.cardDeal(numberOfPlayers); // let players draw cards

        while (!winFlag){
            // update current position
            currentPlayerIndex = eachRound.getGameBoard().getGameStatus().getCurrentPlayerIndex();

            // system output and checking for begin stage, get playable cards for currentPlayer
            eachRound.getTerminal().beginStage();
            CardHolder playableCards = eachRound.beginStage();

            // system output and card-playing or punish based on status info and input
            // get cards currentPlayer will play, only invalid playing would return null
            gameResponse.setCardHolder(playableCards);
            eachRound.getTerminal().playStage();
            String toPlay = eachRound.playStage(playableCards, currentPlayerIndex);

            // final check and preparation for next loop for end stage
            eachRound.endStage(toPlay);

            // update winFlag after each round
            winFlag = eachRound.getGameBoard().getGameStatus().isWinFlag();
        }



        // Case the choice is quit
        if (eachRound.getGameBoard().getGameStatus().getCurrentPlayerIndex() < 0) {
            return null;
        }
        return ids.get(currentPlayerIndex); // return winner's id from ids
    }

    public String runGameForPVE() throws InterruptedException {
        int currentPlayerIndex = -1; // just initialize with a value, will be updates once enter the loop
        boolean winFlag = eachRound.getGameBoard().getGameStatus().isWinFlag(); // initialize win flag
        eachRound.cardDeal(numberOfPlayers); // let players draw cards

        while (!winFlag){
            // update current position
            currentPlayerIndex = eachRound.getGameBoard().getGameStatus().getCurrentPlayerIndex();

            // system output and checking for begin stage, get playable cards for currentPlayer

            eachRound.getTerminal().beginStage();
            CardHolder playableCards = eachRound.beginStage();

            // system output and card-playing or punish based on status info and input
            // get cards currentPlayer will play, only invalid playing would return null
            gameResponse.setCardHolder(playableCards);
            eachRound.getTerminal().playStage();
            String toPlay = eachRound.playStageForComputer(playableCards, currentPlayerIndex);

            // final check and preparation for next loop for end stage
            eachRound.endStageForComputer(toPlay, currentPlayerIndex);

            // update winFlag after each round
            winFlag = eachRound.getGameBoard().getGameStatus().isWinFlag();
        }

        // Case the choice is quit
        if (eachRound.getGameBoard().getGameStatus().getCurrentPlayerIndex() < 0) {
            return null;
        }
        return ids.get(currentPlayerIndex); // return winner's id from ids
    }

}

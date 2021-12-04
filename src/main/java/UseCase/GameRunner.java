package UseCase;

import Entity.CardHolder;
import LogIn.LogInEntity.UserList;
import LogIn.LogInEntity.UserStatistics;
import LogIn.LoginUseCase.LoginUseCase;

import java.util.ArrayList;

/**
 * Core of the program
 */
public class GameRunner implements IGameInput {

    private final int numberOfPlayers;
    private EachRound eachRound; // interface of eachRound, use dependency injection for clean architecture
    private final GameResponse gameResponse;
    private GameRequest gameRequest;

    /**
     * initialize GameRunner
     */
    public GameRunner(GameRequest gameRequest, ArrayList<String> ids) {

        this.gameRequest = gameRequest;
        this.gameResponse = new GameResponse();// get player ids and number of players

        this.numberOfPlayers = ids.size();
        this.gameResponse.setIds(ids);
        gameResponse.setGameBoard(new GameBoard(numberOfPlayers));
    }
    public GameResponse getGameResponse() {
        return gameResponse;
    }
    /**
     * setter method for gameResponse
     */
    public void setGameRequest(GameRequest gameRequest) {
        this.gameRequest = gameRequest;
    }
    /**
     * getter method for gameRequest
     */
    public GameRequest getGameRequest() {
        return gameRequest;
    }
    /**
     * getter method for eachRound
     */
    public EachRound getEachRound() {
        return eachRound;
    }
    /**
     * initialize the game
     */
    public void buildIEachRound(GameBoard gameBoard, IPresenter iPresenter, GameRequest gameRequest) {
        this.eachRound = new EachRound(gameBoard, iPresenter, gameRequest);
        eachRound.cardDeal(numberOfPlayers);
    }
    /**
     * initialize the gui of the game
     */
    public void runGameforGUI(String toPlay, UserStatistics stats) {
        // update current position
        int currentPlayerIndex = eachRound.getGameBoard().getStatus().getCurrentPlayerIndex();

        // system output and checking for begin stage, get playable cards for currentPlayer
        CardHolder playableCards = eachRound.beginStage();

        // system output and card-playing or punish based on status info and input
        // get cards currentPlayer will play, only invalid playing would return null
        gameResponse.setCardHolder(playableCards);
//        eachRound.getTerminal().playStage();
        eachRound.playStageGUI(playableCards, currentPlayerIndex, toPlay, stats);

        // final check and preparation for next loop for end stage
        eachRound.endStageGUI(toPlay, stats);
        saveUserStatistics(stats);
    }
    /**
            * initialize the gui of the game in pve mode
     */

    public void runGameforGUIComputer() {
        // update current position
        int currentPlayerIndex = eachRound.getGameBoard().getStatus().getCurrentPlayerIndex();

        // system output and checking for begin stage, get playable cards for currentPlayer
        CardHolder playableCards = eachRound.beginStage();
        gameResponse.setCardHolder(playableCards);
        // system output and card-playing or punish based on status info and input
        // get cards currentPlayer will play, only invalid playing would return null
//        eachRound.getTerminal().playStage();
        String toPlay = eachRound.playStageGUIPVE(playableCards, currentPlayerIndex);

        // final check and preparation for next loop for end stage
        eachRound.endStageGUIPVE(toPlay, currentPlayerIndex);
    }
    /**
         save Users game statistics
     */

    private void saveUserStatistics(UserStatistics stats) {
        LoginUseCase saver = new LoginUseCase(false);
        UserList users = saver.getUsers();
        users.getUser(stats.getPlayerId()).setUserStatistics(stats);
        new LoginUseCase(users);
    }
}

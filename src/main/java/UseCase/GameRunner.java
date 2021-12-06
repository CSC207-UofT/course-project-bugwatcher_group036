package UseCase;

import Entity.CardHolder;
import LogIn.LogInEntity.UserList;
import LogIn.LogInEntity.UserStatistics;
import LogIn.LoginUseCase.LoginUseCase;

import java.util.ArrayList;

/**
 * Core of the program.
 * The GameRunner.
 */
public class GameRunner {

    private final int numberOfPlayers;
    private EachRound eachRound; // interface of eachRound, use dependency injection for clean architecture
    private final GameResponse gameResponse;
    private GameRequest gameRequest;

    /**
     * initialize GameRunner
     * @param gameRequest Gamerequest of the game
     * @param ids list of all players ids
     */
    public GameRunner(GameRequest gameRequest, ArrayList<String> ids) {

        this.gameRequest = gameRequest;
        this.gameResponse = new GameResponse();// get player ids and number of players

        this.numberOfPlayers = ids.size();
        this.gameResponse.setIds(ids);
    }

    /**
     * getter method for gameRespond
     * @return gameRespond of the game
     */
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
     * @return the gamerequest of the the game
     */
    public GameRequest getGameRequest() {
        return gameRequest;
    }

    /**
     * getter method for eachRound
     * @return eachRound of the game
     */
    public EachRound getEachRound() {
        return eachRound;
    }

    /**
     * initialize the game
     * @param iPresenter Interface for presenter class
     * @param gameRequest Gamerequest of the game
     * @param gateway Gateway interface for reading file
     */
    public void buildIEachRound(IPresenter iPresenter, GameRequest gameRequest, ReadFile gateway) {
        this.eachRound = new EachRound(numberOfPlayers, iPresenter, gameRequest, gateway);
        eachRound.cardDeal(numberOfPlayers);
    }

    /**
     * initialize the gui of the game
     * @param toPlay the string of the card player wants to play
     * @param stats stats of user
     */
    public void runGameforGUI(String toPlay, UserStatistics stats) {
        // update current position
        int currentPlayerIndex = eachRound.getCurrentPlayerIndex();

        // system output and checking for begin stage, get playable cards for currentPlayer
        CardHolder playableCards = eachRound.beginStage();

        // system output and card-playing or punish based on status info and input
        // get cards currentPlayer will play, only invalid playing would return null
        gameResponse.setCardHolder(playableCards);
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
        int currentPlayerIndex = eachRound.getCurrentPlayerIndex();

        // system output and checking for begin stage, get playable cards for currentPlayer
        CardHolder playableCards = eachRound.beginStage();
        gameResponse.setCardHolder(playableCards);
        // system output and card-playing or punish based on status info and input
        // get cards currentPlayer will play, only invalid playing would return null
        String toPlay = eachRound.playStageGUIPVE(playableCards, currentPlayerIndex);

        // final check and preparation for next loop for end stage
        eachRound.endStageGUIPVE(toPlay, currentPlayerIndex);
    }

    /**
     *Save Users game statistics
     @param stats stats of user
     */
    private void saveUserStatistics(UserStatistics stats) {
        LoginUseCase saver = new LoginUseCase(false);
        UserList users = saver.getUsers();
        if (users.getUser(stats.getPlayerId()) == null) {
            return;
        }
        users.getUser(stats.getPlayerId()).setUserStatistics(stats);
        new LoginUseCase(users);
    }
}

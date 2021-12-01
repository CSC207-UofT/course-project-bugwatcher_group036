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

    private final ArrayList<String> ids;
    private final int numberOfPlayers;
    private EachRound eachRound; // interface of eachRound, use dependency injection for clean architecture
    private GameResponse gameResponse;
    private GameRequest gameRequest;
    private IPresenter iPresenter;


    public GameRunner(IPresenter iPresenter, GameRequest gameRequest, ArrayList<String> ids) {

        this.gameRequest = gameRequest;
        this.gameResponse = new GameResponse();// get player ids and number of players
        this.iPresenter = iPresenter;
        this.ids = gameRequest.getIds(); // get player ids and number of players

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

    public EachRound getEachRound() {
        return eachRound;
    }

    public void buildIEachRound(GameBoard gameBoard, IPresenter iPresenter, GameRequest gameRequest) {
        this.eachRound = new EachRound(gameBoard, iPresenter, gameRequest);
        eachRound.cardDeal(numberOfPlayers);
    }

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

    public void runGameforGUIComputer() {
        // update current position
        int currentPlayerIndex = eachRound.getGameBoard().getStatus().getCurrentPlayerIndex();

        // system output and checking for begin stage, get playable cards for currentPlayer
        eachRound.getTerminal().beginStage();
        CardHolder playableCards = eachRound.beginStage();
        gameResponse.setCardHolder(playableCards);
        eachRound.getTerminal().playStage();
        // system output and card-playing or punish based on status info and input
        // get cards currentPlayer will play, only invalid playing would return null
//        eachRound.getTerminal().playStage();
        String toPlay = eachRound.playStageGUIPVE(playableCards, currentPlayerIndex);

        // final check and preparation for next loop for end stage
        eachRound.endStageGUIPVE(toPlay, currentPlayerIndex);
    }
///////////////////////////////////////////////
    //Command Line Methods

    public GameRunner(boolean computer, IPresenter iPresenter, GameRequest gameRequest) {
        this.gameRequest = gameRequest;
        this.gameResponse = new GameResponse();
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

    private void saveUserStatistics(UserStatistics stats) {
        LoginUseCase saver = new LoginUseCase(false);
        UserList users = saver.getUsers();
        users.getUser(stats.getPlayerId()).setUserStatistics(stats);
        new LoginUseCase(users);
    }
}

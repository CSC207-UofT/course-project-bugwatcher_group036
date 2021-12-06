package Controller;

import UseCase.ReadFile;
import UseCase.*;

import java.util.ArrayList;

public class Controller {

    private final GameRunner gameRunner;
    private final GameRequest gameRequest;

    /**
     * initialize Controller for Unogame
     * @param iPresenter the presenter for the card
     * @param ids list for all player ids
     */
    public Controller(Presenter iPresenter, ArrayList<String> ids) {
        this.gameRequest = new GameRequest();
        this.gameRunner = new GameRunner(gameRequest, ids);

        ReadFile gateway = new Gateway();
        gameRunner.buildIEachRound(iPresenter, gameRequest, gateway);
        iPresenter.setGameRequest(gameRequest);
        iPresenter.setController(this);

    }

    /**
     * getter method for gameRequest
     * @return the gameRequest of the game
     */
    public GameRequest getGameRequest(){return gameRequest;}
    /**
     * getter method for gameRrunner
     * @return the gameRunner of the game
     */
    public GameRunner getGameRunner() {
        return gameRunner;
    }
}
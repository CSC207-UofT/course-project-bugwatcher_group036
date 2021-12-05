package Controller;

import UseCase.*;

import java.util.ArrayList;

public class Controller {

    private final GameRunner gameRunner;
    private final GameRequest gameRequest;
    /**
     * initialize Controller
     */
    public Controller(Presenter iPresenter, ArrayList<String> ids) {
        this.gameRequest = new GameRequest(ids);
        this.gameRunner = new GameRunner(gameRequest, ids);

        gameRunner.buildIEachRound(iPresenter, gameRequest);
//        gameRequest.setIds(ids);
        iPresenter.setGameRequest(gameRequest);
        iPresenter.setGameRunner(gameRunner);

    }
    /**
     * getter method for gameRequest
     */
    public GameRequest getGameRequest(){return gameRequest;}
    /**
     * getter method for gameRrunner
     */
    public GameRunner getGameRunner() {
        return gameRunner;
    }
}
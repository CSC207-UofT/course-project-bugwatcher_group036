package Controller;

import UseCase.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Controller {

    private GameRunner gameRunner;
    private final GameRequest gameRequest;
    /**
     * initialize Controller
     */
    public Controller(Presenter iPresenter, ArrayList<String> ids) {
        this.gameRequest = new GameRequest();
        this.gameRunner = new GameRunner(gameRequest, ids);
        gameRunner.buildIEachRound(gameRunner.getGameResponse().getGameBoard(), iPresenter, gameRequest);
        gameRunner.setGameResponse(gameRunner.getGameResponse());
        gameRequest.setIds(ids);
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

    /**
     * setter method for IGameInput
     */
    public void setiGameInput(GameRunner iGameInput) {
        this.gameRunner = iGameInput;
    }
}
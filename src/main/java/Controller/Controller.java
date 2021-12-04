package Controller;

import UseCase.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Controller {

    private GameRunner gameRunner;
    private final GameRequest gameRequest;

    public Controller(Presenter iPresenter, ArrayList<String> ids) {
        this.gameRequest = new GameRequest();
        this.gameRunner = new GameRunner(gameRequest, ids);
        gameRunner.buildIEachRound(gameRunner.getGameResponse().getGameBoard(), iPresenter, gameRequest);
        gameRunner.setGameResponse(gameRunner.getGameResponse());
        gameRequest.setIds(ids);
        iPresenter.setGameRequest(gameRequest);
        iPresenter.setGameRunner(gameRunner);

    }
    public GameRequest getGameRequest(){return gameRequest;}

    public GameRunner getGameRunner() {
        return gameRunner;
    }
}
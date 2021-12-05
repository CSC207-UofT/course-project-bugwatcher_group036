package Controller;

import UseCase.*;

import java.util.ArrayList;

public class Controller {

    private final GameRunner gameRunner;
    private final GameRequest gameRequest;

    /**
     * initialize Controller for Unogame
     */
    public Controller(Presenter iPresenter, ArrayList<String> ids) {
        this.gameRequest = new GameRequest();
        this.gameRunner = new GameRunner(gameRequest, ids);
        readcard();

        gameRunner.buildIEachRound(iPresenter, gameRequest);
        iPresenter.setGameRequest(gameRequest);
        iPresenter.setController(this);

    }

    private void readcard() {
        Readfile readfile = new Cardreadfile();
        ArrayList<String> card = readfile.readFromFile();
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
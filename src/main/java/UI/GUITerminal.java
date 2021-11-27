package UI;

import Controller.Controller;
import UseCase.GameRequest;
import UseCase.GameResponse;
import UseCase.GameRunner;
import UseCase.IPresenter;

import java.util.ArrayList;

public class GUITerminal {
    private final IPresenter presenter;
    private final GameResponse gameResponse;
    private final Controller controller;
    private final ArrayList<String> ids;

    public GUITerminal(IPresenter presenter, Controller controller,
                        GameResponse gameResponse, ArrayList<String> ids){
        presenter.setController(controller);
        presenter.setGameResponse(controller.getGameRunner().getGameResponse());
        controller.getGameRequest().setIds(ids);

        this.ids = ids;
        this.presenter = presenter;
        this.controller = controller;
        this.gameResponse = gameResponse;
        GameRunner gameRunner = new GameRunner(false, presenter, controller.getGameRequest(), ids);
        controller.setiGameInput(gameRunner);
        gameRunner.buildIEachRound(gameRunner.getGameResponse().getGameBoard(), presenter, controller.getGameRequest());
        gameRunner.setGameResponse(gameResponse);
    }
}

import Controller.Controller;
import Controller.Presenter;
import UseCase.*;
import LogIn.LoginStarter;

import java.util.Scanner;

public class GameStarterTerminal {

    public static String runPVP(IPresenter iPresenter, Controller controller,
                                GameRequest gameRequest, GameResponse gameResponse) {

        GameRunner unoGameRunner = new GameRunner(false, iPresenter, gameRequest, gameResponse);

        controller.setiGameInput(unoGameRunner);

        unoGameRunner.buildIEachRound(unoGameRunner.getGameResponse().getGameBoard(), iPresenter, gameRequest);

        return unoGameRunner.runGame();
    }

    public static String runPVE(
            IPresenter iPresenter, Controller controller,
            GameRequest gameRequest, GameResponse gameResponse) throws InterruptedException {

        GameRunner unoGameRunner = new GameRunner(true, iPresenter, gameRequest, gameResponse);

        controller.setiGameInput(unoGameRunner);

        unoGameRunner.buildIEachRound(unoGameRunner.getGameResponse().getGameBoard(), iPresenter, gameRequest);

        return unoGameRunner.runGameForPVE();
    }


    public static void main(String[] args) throws InterruptedException {
        String loginResult = LoginStarter.login();
        while (loginResult.equals("F") || loginResult.equals("Q")) {

            if (loginResult.equals("Q")) return;

            System.out.println("Login Failed, please enter again or register an account");
            System.out.println();
            loginResult = LoginStarter.login();
        }
        System.out.println("Login Success, game will start soon.");

        Scanner scanner = new Scanner(System.in);
        String winner;
        System.out.println("type PVE for PVE mode, otherwise PVP mode");
        String mode = scanner.nextLine();

        Presenter presenter = new Presenter();
        GameRequest gameRequest = new GameRequest();
        GameResponse gameResponse = new GameResponse();
        Controller controller = new Controller(presenter, gameRequest);
        presenter.setController(controller);
        presenter.setGameResponse(gameResponse);

        if (mode.equals("PVE")) {

            winner = runPVE(presenter, controller, gameRequest, gameResponse);

        } else {

            winner = runPVP(presenter, controller, gameRequest, gameResponse);
        }
        if (winner != null) {
            System.out.println("Player \"" + winner + "\" wins!");
        }

    }
}
import Controller.Controller;
import LogIn.LoginStarter;

import java.util.Scanner;

public class GameStarter {
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
        if (mode.equals("PVE")) {
            Controller UnoController = new Controller(true);
            UnoController.buildIEachRound();
            winner = UnoController.runGameForPVE();
        } else {
            Controller UnoController = new Controller();
            UnoController.buildIEachRound();
            winner = UnoController.runGame();
        }
        if (winner != null) {
            System.out.println("Player \"" + winner + "\" wins!");
        }

    }
}
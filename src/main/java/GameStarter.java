import Controller.Controller;
import LogIn.LoginStarter;

public class GameStarter {
    public static void main(String[] args) {
        String loginResult = LoginStarter.login();
        while (loginResult.equals("F") || loginResult.equals("Q")) {

            if (loginResult.equals("Q")) return;

            System.out.println("Login Failed, please enter again or register an account");
            System.out.println();
            loginResult = LoginStarter.login();
        }
        System.out.println("Login Success, game will start soon.");
        Controller UnoController = new Controller();
        UnoController.buildIEachRound();
        String winner = UnoController.runGame();
        if (winner != null) {
            System.out.println("Player \"" + winner + "\" wins!");
        }

    }
}
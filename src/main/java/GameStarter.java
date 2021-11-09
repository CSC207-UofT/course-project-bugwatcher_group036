import Controller.Controller;
import Controller.ControllerBuilder;
import Entity.Player;

import java.util.Scanner;

public class GameStarter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("How many players here? ");
        ControllerBuilder unoBuilder = new ControllerBuilder(input.nextInt());
        Controller newGameController = unoBuilder.buildUnoController();
        Player playerWins = newGameController.runGame();
        System.out.println(playerWins.getId() + " wins!");
    }
}

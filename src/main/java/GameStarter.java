import Controller.Controller;
import Controller.ControllerBuilder;
import Entity.Player;

import java.util.Scanner;

public class GameStarter {
    public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            boolean modeFlag = true;
            do {
                System.out.println("Choose\n1.PVE\n2.PVP");
                int mode = input.nextInt();

                if (mode == 1) {
                    System.out.println("How many players here? ");
                    ControllerBuilder unoBuilder = new ControllerBuilder(input.nextInt());
                    Controller newGameController = unoBuilder.buildUnoControllerForComputer();
                    Player playerWins = newGameController.runGame(false);
                    if (playerWins.getPosition() != 0) {
                        System.out.println("Sorry, you lose.");
                    }
                    else {
                        System.out.println("Congratulations! You win!");
                    }
                    modeFlag = false;
                }
                else if (mode == 2) {
                    System.out.println("How many players here? ");
                    ControllerBuilder unoBuilder = new ControllerBuilder(input.nextInt());
                    Controller newGameController = unoBuilder.buildUnoController();
                    Player playerWins = newGameController.runGame(true);
                    System.out.println(playerWins.getId() + " wins!");
                    modeFlag = false;
                }
                else{
                    System.out.println("Please input a number, either 1 or 2");
                }
            }
            while (modeFlag);
    }
}

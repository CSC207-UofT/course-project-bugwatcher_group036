import Controller.Controller;
import Controller.ControllerBuilder;
import Entity.Player;

public class GameStarter {
    public static void main(String[] args) {
        ControllerBuilder unoBuilder = new ControllerBuilder(4);
        Controller newGameController = unoBuilder.buildUnoController();
        Player playerWins = newGameController.runGame();
        System.out.println(playerWins.getId() + " wins!");
    }
}

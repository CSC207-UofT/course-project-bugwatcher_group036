import Controller.Controller;

public class GameStarter {
    public static void main(String[] args) {
        Controller UnoController = new Controller();
        UnoController.buildIEachRound();
        System.out.println("Player \"" + UnoController.runGame() + "\" wins!");
    }
}

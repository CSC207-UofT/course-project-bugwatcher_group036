package Controller;

import UseCase.*;

public class BasicOperationsData {

    private BasicOperations basicOperations;

    public BasicOperationsData(StatusData vars, GameBoard gameBoard, DeckManager deckManager,
                               PlayerManager playerManager) {
        this.basicOperations = new BasicOperations(vars, gameBoard, playerManager, deckManager);
    }

    public BasicOperations getBasicOperations() {
        return basicOperations;
    }
}

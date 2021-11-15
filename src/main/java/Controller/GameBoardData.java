package Controller;

import UseCase.GameBoard;

public class GameBoardData {

    private GameBoard gameBoard;

    public GameBoardData(int numberOfPlayers) {
        this.gameBoard = new GameBoard(numberOfPlayers);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}

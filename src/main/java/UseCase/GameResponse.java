package UseCase;

import Entity.CardHolder;

import java.util.ArrayList;

public class GameResponse {

    private GameBoard gameBoard;
    private CardHolder cardHolder;
    private ArrayList<String> ids;
    private String setColor;

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public CardHolder getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

    public void setIds(ArrayList<String> ids) {
        this.ids = ids;
    }

    public ArrayList<String> getIds() {
        return ids;
    }
}

package UseCase;

import Entity.CardHolder;

import java.util.ArrayList;

public class GameResponse {

    private GameBoard gameBoard;
    private CardHolder cardHolder;
    private ArrayList<String> ids;
    /**
     * setter method for gameBoard
     */
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    /**
     * getter method for gameBoard
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }
    /**
     * getter method for cardHolder
     */
    public CardHolder getCardHolder() {
        return cardHolder;
    }
    /**
     * setter method for cardHolder
     */
    public void setCardHolder(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }
    /**
     * setter method for ids
     */
    public void setIds(ArrayList<String> ids) {
        this.ids = ids;
    }
    /**
     * getter method for ids
     */
    public ArrayList<String> getIds() {
        return ids;
    }
}

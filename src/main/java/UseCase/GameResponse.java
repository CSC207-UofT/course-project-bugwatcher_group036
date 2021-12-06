package UseCase;

import Entity.CardHolder;

import java.util.ArrayList;

/**
 * The GameResponse.
 */
public class GameResponse {

    private CardHolder cardHolder;
    private ArrayList<String> ids;

    /**
     * getter method for cardHolder
     * @return the cardHolder of the game
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
     * @return all the ids of player in the game
     */
    public ArrayList<String> getIds() {
        return ids;
    }
}

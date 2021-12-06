package Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Basically ArrayList of String, which represents cards
 * and customize str method
 */
public class CardHolder implements Iterable<String>{

    private final ArrayList<String> handcard;

    /**
     * The Cardholder for the player.
     */
    public CardHolder() {
        this.handcard = new ArrayList<>();
    }

    /**
     * The Iterator Design Pattern for CardHolder.
     * @return The string of cardHolder.
     */
    @Override
    public Iterator<String> iterator(){
        return new cardIterator();
    }

    private class cardIterator implements Iterator<String> {
        int current = 0;

        @Override
        public boolean hasNext(){
            return current < handcard.size();
        }

        @Override
        public String next(){
            String res;

            try {
                res = handcard.get(current);
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
            current++;
            return res;
        }
    }

    /**
     * return the card played and remove it.
     * @param toPlay the string of the card player wants to play
     * @return return the hand card remove the played card
     */
    public boolean playCard(String toPlay){
        return handcard.remove(toPlay);
    }

    /**
     * play the card with given index and remove it
     *@param toPlay the string of the card player wants to play
     *@return the hand card remove the played card
     */
    public String playCardWithIndex(int toPlay){
        return handcard.remove(toPlay);
    }

    /**
     * add card to handcard
     * @param card a string of card
     */
    public void addCard(String card){
        handcard.add(card);
    }

    /**
     * add several cards to handcard
     * @param card a arraylist of card
     */
    public void addCards(ArrayList<String> card) {
        handcard.addAll(card);
    }

    /**
     * check it handcard is empty
     * Ture for empty flase for not
     */
    public boolean isEmpty(){
        return handcard.isEmpty();
    }

    @Override
    public String toString(){
        if (handcard.size() == 0){
            return "{}";
        }
        StringBuilder res = new StringBuilder("{");
        for (String card: handcard){
            res.append(card).append(", ");
        }
        return res.substring(0, res.length() - 2) + "}";
    }

    /**
     * getter method for the size of handcard
     * @return size of hand card
     */
    public int getSize(){
        return this.handcard.size();
    }
}

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

    public CardHolder() {
        this.handcard = new ArrayList<>();
    }


    

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
     */
    public boolean playCard(String toPlay){
        return handcard.remove(toPlay);
    }
    /**
     * play the card with given index and remove it
     */
    public String playCardWithIndex(int toPlay){
        return handcard.remove(toPlay);
    }
    /**
     * add card to handcard
     */
    public void addCard(String card){
        handcard.add(card);
    }
    /**
     * add several cards to handcard
     */
    public void addCards(ArrayList<String> card) { handcard.addAll(card); }
    /**
     * check it handcard is empty
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
     */
    public int getSize(){
        return this.handcard.size();
    }
}

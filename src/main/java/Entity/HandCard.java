package Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HandCard implements Iterable<String>{

    private ArrayList<String> handcard;

    public HandCard(ArrayList<String> handcard){
        this.handcard = handcard;
    }

    public HandCard() {
        this.handcard = new ArrayList<String>();
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

    public boolean playCard(String toPlay){
        if (!handcard.contains(toPlay)){
            return false;
        }
        return handcard.remove(toPlay);
    }

    public void addCard(String card){
        handcard.add(card);
    }

    public boolean isEmpty(){
        return handcard.isEmpty();
    }

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
}

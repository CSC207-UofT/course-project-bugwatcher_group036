package Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import Entity.Card;

public class Player implements Iterable<Card>{

    private String id;
    private int position;
    private ArrayList<Card> handCard;

    public Player(String id, int position){
        this.id = id;
        this.position = position;
        this.handCard = new ArrayList<Card>();
    }

    public void drawCard(Card c){
        handCard.add(c);
    }

    public Card playCard(Card c){
            return handCard.remove(handCard.indexOf(c));
    }

    public int getCardNum(){
        return handCard.size();
    }

    public ArrayList<Card> getHandCard(){
        return handCard;
    }

    public String getId(){
        return id;
    }

    public int getPosition(){
        return position;
    }

    @Override
    public Iterator<Card> iterator() {
        return new PlayerHandCardIterator();
    }

    private class PlayerHandCardIterator implements Iterator<Card>{
        private int current = 0;

        @Override
        public boolean hasNext(){
            return current < handCard.size();
        }

        @Override
        public Card next(){
            Card res;

            try{
                res = handCard.get(current);
            } catch (IndexOutOfBoundsException e){
                throw new NoSuchElementException();
            }
            current += 1;
            return res;
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                '}';
    }
}

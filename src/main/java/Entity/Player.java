package Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import Entity.Card;

public class  Player implements Iterable<Card>{

    private final String id;
    private final int position;
    private final ArrayList<Card> handCard;

    /**
     * Construct a player with certain id and position.
     *
     * @param id The id of the player.
     * @param position The position of the player in this round.
     */
    public Player(String id, int position){
        this.id = id;
        this.position = position;
        this.handCard = new ArrayList<Card>();
    }

    /**
     * Draw a card from deck.
     * @param card Drawn card.
     */
    public void drawCard(Card card){
        handCard.add(card);
    }

    /**
     *  Play this card, after that, the card should be removed from our hand.
     *
     * @param card The card we tried to play.
     * @return The card which is removed from our hand.
     */
    public Card playCard(Card card){
        return handCard.remove(handCard.indexOf(card));
    }

    /**
     * Get how many card the Player holds.
     *
     * @return An int which represent the number of cards in the players hand.
     */
    public int getCardNum(){
        return handCard.size();
    }

    /**
     * Get cards in the players hand.
     *
     * @return An arraylist which include all cards in the players hand.
     */
    public ArrayList<Card> getHandCard(){
        return handCard;
    }

    /**
     * Get the id of the player.
     *
     * @return A string which is the id of the player.
     */
    public String getId(){
        return id;
    }

    /**
     * Get the position of the player.
     *
     * @return A string which is the position of the player.
     */
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

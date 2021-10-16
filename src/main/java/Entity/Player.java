package Entity;

import java.util.ArrayList;
import Entity.Card;

public class Player {

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
    public String toString() {
        return "Player{" +
                "id=" + id +
                '}';
    }
}

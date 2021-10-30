package UseCase;

import Controller.*;
import Entity.Card;
import Entity.Deck;
import Entity.FunctionCard;
import Entity.NumberCard;

import java.io.IOException;
import java.util.ArrayList;

public class DeckManager {

    private Deck d;
    Readfile readfile = new Cardreadfile();
    public DeckManager(){
        this.d = readfile.readFromFile("src/main/resources/numberCards.txt",
                "src/main/resources/functionCards.txt"); //wise: I am not sure whether the directory should be in the Use Case or in the Gateway Class.
        //wise: From the clean architecture login demo, the directory was in the Use Case Class but I think we can directly put it in the gateway class.
//        String[] colors = {"red", "green", "blue", "yellow"};
//        for (String color : colors) {
//            for (int i = 0; i < 10; i++) {
//                Card newCard = new NumberCard(color, i, color + i);
//                d.getUnusedCardDeck().add(newCard);
//            }
//        }
    }

    public int[] returnDeckInfo() {
        int[] res = new int[2];
        res[0] = d.getUsedCardDeck().size();
        res[1] = d.getUnusedCardDeck().size();
        return res;
    }

    public void shuffleFromUsedToUnused() {
        d.shuffleFromUsedToUnused();
    }

    public void initializeCard(ArrayList<Card> cards) {
        d.setUnusedCardDeck(cards);
    }

    public void putCardToUsedDeck(Card c) {
        d.putCardToUsedDeck(c);
    }

    public Card drawCardFromUnusedDeck() {
        if (d.getUnusedCardDeck().size() == 0) {
            boolean noCard = d.shuffleFromUsedToUnused();
            if  (!noCard) {
                return new Card(); // shouldn't we throw exception here?
            }
        }
        return d.drawCardFromUnusedDeck();
    }

    public boolean checkShuffle() {
        return d.getUnusedCardDeck().size() == 0;
    }

    public Card extractCard(ArrayList<Card> cards, String id) {
        for (Card c : cards) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return new Card();
    }

    public boolean compareNew(Card c) {
        return c.getColor().equals("black");
    }

    public String color(Card c){
        return c.getColor();
    }

    public String function(FunctionCard c){
        return c.getFunction();
    }

    public int num(NumberCard c){
        return c.getNumber();
    }

}

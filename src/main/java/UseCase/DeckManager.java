package UseCase;

import Controller.*;
import Entity.*;

import java.io.IOException;
import java.util.ArrayList;

public class DeckManager {

    private Deck d;
//    Readfile readfile = new Cardreadfile();

    //    public DeckManager(){
//        this.d = readfile.readFromFile("src/main/resources/numbercards.txt",
//                "src/main/resources/functioncards.txt"); //wise: I am not sure whether the directory should be in the Use Case or in the Gateway Class.
//        //wise: From the clean architecture login demo, the directory was in the Use Case Class but I think we can directly put it in the gateway class.
////        String[] colors = {"red", "green", "blue", "yellow"};
////        for (String color : colors) {
////            for (int i = 0; i < 10; i++) {
////                Card newCard = new NumberCard(color, i, color + i);
////                d.getUnusedCardDeck().add(newCard);
////            }
////        }
//    }
    public DeckManager() {
        this.d = new Deck();
    }

    public DeckManager(Deck d) {
        this.d = d;
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
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getId().equals(id)) {
                return cards.get(i);
            }
        }
        return new Card();
    }

    public boolean whetherNull(Card c) {
        return c.getId().equals("nullid");
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

    public boolean compareTwoCardsHaveSameFeature(Card lastCard, Card c2) {
        if (lastCard.getColor().equals("black")){
            return lastCard.getFeature().equals(c2.getFeature());
        }
        return lastCard.getFeature().equals(c2.getFeature())||lastCard.getColor().equals(c2.getColor());
    }

    public boolean compareTwoCardsHaveSameColor(Card c1, Card c2) {
        return c1.getColor().equals(c2.getColor());
    }

    public ArrayList<Card> cardsPlayerCanPlay(ArrayList<Card> cards, Card lastCard) {
        ArrayList<Card> cardsCanPlay = new ArrayList<Card>();

        if (whetherNull(lastCard)) {
            return (ArrayList<Card>) cards.clone();
        }
        for (Card c: cards) {
            if (compareTwoCardsHaveSameFeature(lastCard, c)||c.getFeature().equals("switch")||
                    c.getFeature().equals("plusfour")) {
                cardsCanPlay.add(c);
            }
        }
        return cardsCanPlay;
    }

    public Card createNullCard() {
        return new Card();
    }

    public Card createColorCard(String color) {
        return new Card(color, color);
    }

    public String feature(Card c) {
        return c.getFeature();
    }

    public void addCard(Card c) {
        d.addcard(c);
    }

    public ArrayList<Card> skipsPlayerCanPlay(ArrayList<Card> cards) {
        ArrayList<Card> skips = new ArrayList<>();
        for (Card c: cards) {
            if (c.getFeature().equals("skip")) {
                skips.add(c);
            }
        }
        return skips;
    }

    public ArrayList<Card> plustwoPlayerCanPlay(ArrayList<Card> cards) {
        ArrayList<Card> skips = new ArrayList<>();
        for (Card c: cards) {
            if (c.getFeature().equals("plustwo")||c.getFeature().equals("plusfour")) {
                skips.add(c);
            }
        }
        return skips;
    }

    public ArrayList<Card> plusfourPlayerCanPlay(ArrayList<Card> cards) {
        ArrayList<Card> skips = new ArrayList<>();
        for (Card c: cards) {
            if (c.getFeature().equals("plusfour")) {
                skips.add(c);
            }
        }
        return skips;
    }

    public Deck getDeck(){
        return d;
    }

}

package Entity;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {


    @Test
    public void testIsEmpty() {
        Deck d1 = new Deck();
        assertTrue(d1.isEmpty());
        assertTrue(d1.getUsedCardDeck().isEmpty());

    }

//    @Test
//    public void testNumOfCards() {
//        Deck d1 = new Deck();
//        assertEquals(108, d1.numOfCards(d1.getUnusedCardDeck()));
//    }

    @Test
    public void testGetUsedCardDeck() {
        Deck d1 = new Deck();
        ArrayList<Card> a1 = new ArrayList<>();
        assertEquals(a1, d1.getUsedCardDeck());
    }

    @Test
    public void testSetUsedCardDeck() {
        Deck d1 = new Deck();
        ArrayList<Card> a1 = new ArrayList<>();
        Deck d2 = new Deck();
        d1.setUsedCardDeck(a1);
        assertEquals(a1, d1.getUsedCardDeck());
    }

    @Test
    public void testGetUnusedCardDeck() {
        Deck d1 = new Deck();
        ArrayList<Card> a1 = d1.getUnusedCardDeck();
        assertEquals(a1, d1.getUnusedCardDeck());
    // Need to implement ToCompare
    }

    @Test
    public void testSetUnusedCardDeck() {
        Deck d1 = new Deck();
        ArrayList<Card> a1 = new ArrayList<>();
        d1.setUnusedCardDeck(a1);
        assertEquals(a1, d1.getUnusedCardDeck());
    }

    @Test
    public void testDrawCardFromUnusedDeck() {
        Deck d1 = new Deck();
        String[] colors = {"red", "green", "blue", "yellow"};
        for (String color : colors) {
            for (int i = 0; i < 10; i++) {
                Card newCard = new NumberCard(color, i, color + i);
                d1.getUnusedCardDeck().add(newCard);
            }
        }
        Card c1 = d1.drawCardFromUnusedDeck();
        assertFalse(d1.getUnusedCardDeck().contains(c1));
    }

    @Test
    public void testShuffleFromUsedToUnused() {
        Deck d1 = new Deck();
        Card c1 = new Card("yellow", "25");
        Deck d2 = new Deck();
        d2.putCardToUsedDeck(c1);
        assertFalse(d1.shuffleFromUsedToUnused());
        assertTrue(d2.shuffleFromUsedToUnused());
    }

    @Test
    public void testPutCardToUsedDeck() {
        Deck d1 = new Deck();
        String[] colors = {"red", "green", "blue", "yellow"};
        for (String color : colors) {
            for (int i = 0; i < 10; i++) {
                Card newCard = new NumberCard(color, i, color + i);
                d1.getUnusedCardDeck().add(newCard);
            }
        }
        Card c1 = d1.drawCardFromUnusedDeck();
        d1.putCardToUsedDeck(c1);
        assertTrue(d1.getUsedCardDeck().contains(c1));
    }
}
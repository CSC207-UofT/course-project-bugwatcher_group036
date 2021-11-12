package Entity;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    private static Deck d1 = new Deck();
    private static ArrayList<Card> a1 = new ArrayList<>();


    @Test
    public void testIsEmpty() {
        assertTrue(d1.isEmpty());
        assertTrue(d1.getUsedCardDeck().isEmpty());

    }

    @Test
    public void testNumOfCards() {
        Deck d1 = new Deck();
        assertEquals(0, d1.numOfCards(d1.getUnusedCardDeck()));
    }

    @Test
    public void testGetUsedCardDeck() {
        assertTrue(d1.getUsedCardDeck().isEmpty());
    }

    @Test
    public void testSetUsedCardDeck() {
        d1.setUsedCardDeck(a1);
        assertTrue(d1.getUsedCardDeck().isEmpty());
    }

    @Test
    public void testGetUnusedCardDeck() {
        assertTrue(d1.getUnusedCardDeck().isEmpty());
    // Need to implement ToCompare
    }

    @Test
    public void testSetUnusedCardDeck() {
        d1.setUnusedCardDeck(a1);
        assertTrue(d1.getUnusedCardDeck().isEmpty());
    }

    @Test
    public void testDrawCardFromUnusedDeck() {
        String[] colors = {"red", "green", "blue", "yellow"};
        for (String color : colors) {
            for (int i = 0; i < 10; i++) {
                Card newCard = new NumberCard(color, i, color + i);
                d1.getUnusedCardDeck().add(newCard);
            }
        }
        Card c1 = d1.drawCardFromUnusedDeck();
        assertFalse(d1.getUnusedCardDeck().contains(c1));
        //Need further changing.
    }

    @Test
    public void testShuffleFromUsedToUnused() {
        assertFalse(d1.shuffleFromUsedToUnused());
    }

    @Test
    public void testPutCardToUsedDeck() {
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
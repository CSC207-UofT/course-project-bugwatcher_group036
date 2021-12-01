package Entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    public void testInitializationAndGetUnused(){
        Deck deck = new Deck();
        assertEquals(deck.getUnusedCardDeck().size(), 108);
    }

    @Test
    public void testDrawCardFromUnusedDeck(){
        Deck deck = new Deck();
        String card = deck.drawCardFromUnusedDeck();
        ArrayList<String> colors = new ArrayList<>();
        Collections.addAll(colors, "red", "green", "yellow", "blue", "black");
        assertTrue(colors.contains(card.split(" ")[0]));
    }

    @Test
    public void testIsEmptyAndShuffle(){
        Deck deck = new Deck();
        String drawn = deck.drawCardFromUnusedDeck();
        while (drawn != null) {
            deck.putCardToUsedDeck(drawn);
            drawn = deck.drawCardFromUnusedDeck();
        }
        assertTrue(deck.isEmpty());
        deck.shuffleFromUsedToUnused();
        assertFalse(deck.isEmpty());
    }
}

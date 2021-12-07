package Entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    Deck deck;

    @BeforeEach
    public void initialize() {
        this.deck = new Deck();
        String[] colors = {"red", "green", "blue", "yellow"};
        for (String color : colors) {
            for (int i = 0; i < 10; i++) {
                deck.getUnusedCardDeck().add(color + " " + i);
            }
        }
    }

    @Test
    public void testInitializationAndGetUnused(){
        assertEquals(deck.getUnusedCardDeck().size(), 40);
    }

    @Test
    public void testDrawCardFromUnusedDeck(){
        String card = deck.drawCardFromUnusedDeck();
        ArrayList<String> colors = new ArrayList<>();
        Collections.addAll(colors, "red", "green", "yellow", "blue");
        assertTrue(colors.contains(card.split(" ")[0]));
    }

    @Test
    public void testIsEmptyAndShuffle(){
        String drawn = deck.drawCardFromUnusedDeck();
        while (drawn != null) {
            deck.putCardToUsedDeck(drawn);
            drawn = deck.drawCardFromUnusedDeck();
        }
        assertTrue(deck.unusedIsEmpty());
        deck.shuffleFromUsedToUnused();
        assertFalse(deck.unusedIsEmpty());
    }
}

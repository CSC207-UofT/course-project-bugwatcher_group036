package Entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    public void testInitialization(){
        Deck deck = new Deck();
        assertEquals(deck.getUnusedCardDeck().size(), 108);
    }

    @Test
    public void testDrawCardFromUnusedDeck(){
        Deck deck = new Deck();
        String card = deck.drawCardFromUnusedDeck();
        ArrayList<String> colors = new ArrayList<>();
        Collections.addAll(colors, "red", "green", "yellow", "blue");
        assertTrue(colors.contains(card.split(" ")[0]));
    }

    @Test
    public void testNull(){
    }
}

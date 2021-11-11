package UseCase;
import Entity.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DeckManagerTest {
    DeckManager deckManager0 = new DeckManager();
    Deck deck0 = new Deck();
    ArrayList<Card> cards = deck0.setDefaultDeck();
    Deck deck = new Deck(cards);
    DeckManager deckManager1 = new DeckManager(deck);


    @Test
    public void testGetDeck(){
        Deck d1 = deckManager0.getDeck();
        Deck d2 = deckManager1.getDeck();
        assertTrue(d1.isEmpty());
        assertEquals(deck, d2);

    }

    @Test
    public void testShuffleFromUsedToUnused(){
        Deck d1 = deckManager0.getDeck();
        Deck d2 = deckManager1.getDeck();
        assertFalse(d1.shuffleFromUsedToUnused());
        assertTrue(d2.shuffleFromUsedToUnused());
    }

    @Test
    public void testInitializeCard(){
        deckManager0.initializeCard(deck.getUnusedCardDeck());
        assertEquals(deckManager0.getDeck().getUnusedCardDeck(), deck.getUsedCardDeck());
    }


}

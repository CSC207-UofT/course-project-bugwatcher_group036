package UseCase;

import Controller.Gateway;
import Entity.CardHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class GameDeckTest {

    ReadFile gateway = new Gateway();

    GameDeck gameDeck;


    @BeforeEach
    public void Intialization() {
        this.gameDeck = new GameDeck(gateway);
    }

    @Test
    public void testisEmpty() {
        assertFalse(gameDeck.isEmpty());
        for (int i = 0; i < 112; i++){
            gameDeck.drawCardFromUnusedDeck();
        }
        assertTrue(gameDeck.isEmpty());
    }

    @Test
    public void testgetUnusedCardDeck() {
        ArrayList<String> unused = gameDeck.getUnusedCardDeck();
        assertFalse(gameDeck.getUnusedCardDeck().isEmpty());
        for (int i = 0; i < 112; i++){
            gameDeck.drawCardFromUnusedDeck();
        }
        assertTrue(gameDeck.getUnusedCardDeck().isEmpty());
        assertEquals(unused, gameDeck.getUnusedCardDeck());
    }

    @Test
    public void testdrawCardFromUnusedDeck() {
        assertFalse(gameDeck.getUnusedCardDeck().isEmpty());
        for (int i = 0; i < 108; i++){
            gameDeck.drawCardFromUnusedDeck();
            assertEquals(111 - i, gameDeck.getUnusedCardDeck().size());
        }

    }
    @Test
    public void testshuffleFromUsedToUnused() {
        assertFalse(gameDeck.getUnusedCardDeck().isEmpty());
        for (int i = 0; i < 112; i++){
            gameDeck.putCardToUsedDeck(gameDeck.drawCardFromUnusedDeck());
        }
        assertEquals(0, gameDeck.getUnusedCardDeck().size());
        assertTrue(gameDeck.getUnusedCardDeck().isEmpty());
        gameDeck.shuffleFromUsedToUnused();
        assertFalse(gameDeck.getUnusedCardDeck().isEmpty());
        assertEquals(112, gameDeck.getUnusedCardDeck().size());
    }

    @Test
    public void putCardToUsedDeck() {
        assertFalse(gameDeck.getUnusedCardDeck().isEmpty());
        for (int i = 0; i < 108; i++){
            gameDeck.putCardToUsedDeck(gameDeck.drawCardFromUnusedDeck());
            assertEquals(111 - i, gameDeck.getUnusedCardDeck().size());
        }
    }
}

package UseCase;

import Entity.Deck;
import Entity.CardHolder;
import Entity.Status;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameCardHoldersTest {
    GameCardHolders gameCardHolders = new GameCardHolders(2);
    GameCardHolders gameCardHolders0 = new GameCardHolders(0);
    CardHolder cardHolder0 = gameCardHolders.getHandCards(0);
    CardHolder cardHolder1 = gameCardHolders.getHandCards(1);

    public ArrayList<String> getHandCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add("red 5");
        cards.add("blue +2");
        cards.add("yellow +4");
        cards.add("green skip");
        return cards;
    }

    @Test
    public void testInitialization() {
        GameCardHolders gameCardHolders0 = new GameCardHolders(3);
        assertEquals(gameCardHolders0.getHolderNumber(), 3);
        GameCardHolders gameCardHolders1 = new GameCardHolders(4);
        assertEquals(gameCardHolders1.getHolderNumber(), 4);
    }

    @Test
    public void testGetHandCards() {
        assertEquals(cardHolder0.getSize(), 0);
        ArrayList<String> cards = getHandCards();
        cardHolder0.addCards(cards);
        CardHolder cardHolder2 = gameCardHolders.getHandCards(0);
        assertEquals(cardHolder2.getSize(), 4);
        assertEquals(cardHolder1.getSize(), 0);
    }

    @Test
    public void testPlayCard() {
        ArrayList<String> cards = getHandCards();
        cardHolder0.addCards(cards);
        assertFalse(gameCardHolders.playCard("red 9", cardHolder0));
        assertEquals(gameCardHolders.getNumbersOfCardHolds(0), 4);
        assertTrue(gameCardHolders.playCard("red 5", cardHolder0));
        assertEquals(gameCardHolders.getNumbersOfCardHolds((0)), 3);
    }

    @Test
    public void testGetHolderNumber() {
        assertEquals(gameCardHolders.getHolderNumber(), 2);
        assertEquals(gameCardHolders0.getHolderNumber(), 0);
    }

    @Test
    public void testGetNumberOfCardHolds() {
        ArrayList<String> cards = getHandCards();
        cardHolder0.addCards(cards);
        assertEquals(gameCardHolders.getNumbersOfCardHolds(0), 4);
        assertEquals(gameCardHolders.getNumbersOfCardHolds(1), 0);
    }

    @Test
    public void testPlayCardWithIndex() {
        ArrayList<String> cards = getHandCards();
        cardHolder0.addCards(cards);
        String card0 = gameCardHolders.playCardWithIndex(1, 0);
        assertEquals(card0, "blue +2");
        String card1 = gameCardHolders.playCardWithIndex(0, 0);
        assertEquals(card1, "red 5");
    }

    @Test
    public void testAddCard() {
        assertEquals(cardHolder0.getSize(), 0);
        gameCardHolders.addCard("123", 0);
        assertEquals(cardHolder0.getSize(), 1);
        gameCardHolders.addCard("abc", 0);
        assertEquals(cardHolder0.getSize(), 2);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(gameCardHolders.isEmpty(0));
        ArrayList<String> cards = getHandCards();
        cardHolder0.addCards(cards);
        assertFalse(gameCardHolders.isEmpty(0));
        assertTrue(gameCardHolders.isEmpty(1));

    }

    @Test
    public void testCheckWithState() {
        assertTrue(gameCardHolders.isEmpty(0));
        ArrayList<String> cards = getHandCards();
        cardHolder0.addCards(cards);
        assertTrue(gameCardHolders.checkWinState());
        cardHolder1.addCard("blue 7");
        assertFalse(gameCardHolders.checkWinState());
    }

    @Test
    public void testCreateNewCardHolder() {
        CardHolder cardHolder = gameCardHolders.createNewCardHolder();
        assertEquals(cardHolder.getSize(), 0);
    }
}

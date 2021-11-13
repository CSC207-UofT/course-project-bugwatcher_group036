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
        assertFalse(d2.shuffleFromUsedToUnused());
    }

    @Test
    public void testInitializeCard(){
        deckManager0.initializeCard(deck.getUnusedCardDeck());
        assertEquals(deckManager0.getDeck().getUnusedCardDeck(), deckManager1.getDeck().getUnusedCardDeck());
    }

    @Test
    public void testPutCardToUsedDeck(){
        Card c1 = new Card("yellow", "25");
        deckManager0.putCardToUsedDeck(c1);
        Deck curDeck = deckManager0.getDeck();
        ArrayList<Card> used = curDeck.getUsedCardDeck();
        assertEquals(c1, used.get(0));
    }

    @Test
    public void testDrawCardFromUnusedDeck(){
        Card card = deckManager0.drawCardFromUnusedDeck();
        Card card1 = deckManager1.drawCardFromUnusedDeck();
        assertEquals(card.getId(), "nullid");
        assertNotEquals("nullid", card1.getId());

    }

    @Test
    public void testCheckShuffle(){
        assertTrue(deckManager0.checkShuffle());
        assertFalse(deckManager1.checkShuffle());

    }

    @Test
    public void testExtractCard(){
        Card card = deckManager1.extractCard(cards, "red2");
        assertEquals(card.getId(), "red2");

    }

    @Test
    public void testWhetherNull(){
        Card card = deckManager0.drawCardFromUnusedDeck();
        Card card1 = deckManager1.drawCardFromUnusedDeck();
        assertTrue(deckManager0.whetherNull(card));
        assertFalse(deckManager1.whetherNull(card1));

    }

    @Test
    public void testColor(){
        Card card1 = deckManager1.drawCardFromUnusedDeck();
        String color = card1.getColor();
        assertEquals(deckManager1.color(card1), color);

    }

    @Test
    public void testFunction(){


    }

    @Test
    public void testNum(){
        NumberCard card = (NumberCard) deckManager1.drawCardFromUnusedDeck();
        int number = card.getNumber();
        assertEquals(deckManager1.num(card), number);

    }

    @Test
    public void testCompareTwoCardsHaveSameFeature(){

    }

    @Test
    public void testCompareTwoCardsHaveSameColor(){
        Card c1 = new Card("yellow", "25");
        Card c2 = new Card("yellow", "30");
        Card c3 = new Card("blue", "25");
        assertTrue(deckManager1.compareTwoCardsHaveSameColor(c1, c2));
        assertFalse(deckManager1.compareTwoCardsHaveSameColor(c1, c3));

    }

    @Test
    public void testCreateNullCard(){
        Card card = deckManager1.createNullCard();
        assertEquals(card.getId(), "nullid");

    }

    @Test
    public void testCreateColorCard(){
        Card card = deckManager1.createColorCard("red");
        assertEquals(card.getColor(), "red");

    }

    @Test
    public void testFeature(){
        Card card = deckManager1.extractCard(cards, "red3");
        String feature = deckManager1.feature(card);
        assertEquals(feature, card.getFeature());

    }

    @Test
    public void testAddCard(){
        Card card0 = deckManager1.extractCard(deckManager1.getDeck().getUnusedCardDeck(), "yellow25");
        assertEquals(card0.getId(), "nullid");
        Card card = new Card("yellow", "25");
        deckManager1.addCard(card);
        Card card1 = deckManager1.extractCard(deckManager1.getDeck().getUnusedCardDeck(), "25");
        assertEquals(card1.getId(), "25");

    }


}

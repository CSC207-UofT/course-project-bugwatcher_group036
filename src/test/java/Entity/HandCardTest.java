package Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HandCardTest {

    @Test
    public void testInitialization(){
        HandCard cards = new HandCard();
        assertEquals("{}", cards.toString());
    }

    @Test
    public void testAddCard(){
        HandCard cards = new HandCard();
        cards.addCard("test");
        assertEquals(cards.toString(), "{test}");
        cards.addCard("wow!");
        assertEquals(cards.toString(), "{test, wow!}");
    }

    @Test
    public void testIsEmpty(){
        HandCard cards = new HandCard();
        assertTrue(cards.isEmpty());
        cards.addCard("test");
        assertFalse(cards.isEmpty());
    }

    @Test
    public void testIterator(){
        HandCard cards = new HandCard();
        for (int i = 0; i < 10; i++) {
            cards.addCard(Integer.toString(i));
        }
        int count = 0;
        for (String card: cards){
            assertEquals(Integer.toString(count), card);
            count++;
        }
    }

    @Test
    public void testPlayCard(){
        HandCard cards = new HandCard();
        for (int i = 0; i < 10; i++) {
            cards.addCard(Integer.toString(i));
        }
        int rand = (int)(Math.random() * 10);
        String toPlay = Integer.toString(rand);
        assertTrue(cards.playCard(toPlay));
    }
}

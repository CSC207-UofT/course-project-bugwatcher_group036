package Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardHolderTest {

    @Test
    public void testInitialization(){
        CardHolder cards = new CardHolder();
        assertEquals("{}", cards.toString());
    }

    @Test
    public void testAddCard(){
        CardHolder cards = new CardHolder();
        cards.addCard("test");
        assertEquals(cards.toString(), "{test}");
        cards.addCard("wow!");
        assertEquals(cards.toString(), "{test, wow!}");
    }

    @Test
    public void testIsEmpty(){
        CardHolder cards = new CardHolder();
        assertTrue(cards.isEmpty());
        cards.addCard("test");
        assertFalse(cards.isEmpty());
    }

    @Test
    public void testIterator(){
        CardHolder cards = new CardHolder();
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
        CardHolder cards = new CardHolder();
        for (int i = 0; i < 10; i++) {
            cards.addCard(Integer.toString(i));
        }
        int rand = (int)(Math.random() * 10);
        String toPlay = Integer.toString(rand);
        assertTrue(cards.playCard(toPlay));
    }
}

package test;

import Entity.Card;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CardTest {
    Card c1 = new Card("red", 7, "noFunc", "red7");;
    Card c2 = new Card();
//    Card c1;
//    Card c2;

    @Test
    public void TestGetColor() {
        assertEquals("red", c1.getColor());
        assertEquals("black", c2.getColor());
    }

    @Test
    public void TestGetNumber(){
        assertEquals(7, c1.getNumber());
        assertNull(c2.getNumber());
    }


}
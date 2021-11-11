package Entity;

import Entity.NumberCard;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NumberCardTest {
    private static NumberCard c1 = new NumberCard("red", 7, "red7");
    private static NumberCard c2 = new NumberCard();

    @Test
    public void testGetColor() {
        assertEquals("red", c1.getColor());
        assertEquals("black", c2.getColor());
    }

    @Test
    public void testGetNumber(){
        assertEquals(7, c1.getNumber());
        assertEquals(-1, c2.getNumber());
    }

    @Test
    public void testToString(){
        assertEquals("red7", c1.toString());
        assertEquals("black-1", c2.toString());
    }


}
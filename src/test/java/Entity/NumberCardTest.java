package Entity;

import Entity.NumberCard;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NumberCardTest {
    NumberCard c1 = new NumberCard("red", 7, "red7");
    NumberCard c2 = new NumberCard();
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
        assertEquals(-1, c2.getNumber());
    }


}
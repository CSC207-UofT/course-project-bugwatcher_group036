package Entity;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionCardTest {
    private static FunctionCard c1 = new FunctionCard("yellow", "switch", "id");
    private static FunctionCard c2 = new FunctionCard();

    @Test
    public void testGetColor() {
        assertEquals("yellow", c1.getColor());
        assertEquals("black", c2.getColor());
    }

    @Test
    public void testGetFunction() {
        assertEquals("switch", c1.getFunction());
        assertEquals("null", c2.getFunction());
    }

    @Test
    public void testToString() {
        assertEquals("yellow switch", c1.toString());
        assertEquals("black null", c2.toString());
    }
}

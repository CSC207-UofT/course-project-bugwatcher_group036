package Entity;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionCardTest {
    Player p1 = new Player("player1", 1);
    FunctionCard c1 = new FunctionCard("yellow", "switch", "id");

    @Test
    public void testGetColor() {
        assertEquals("yellow", c1.getColor());
    }

    @Test
    public void testGetFunction() {
        assertEquals("switch", c1.getFunction());
    }

    @Test
    public void testToString() {
        assertEquals("yellow switch", c1.toString());
    }
}

package Entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class CardTest {
    private static Card c1 = new Card("yellow", "25");
    private static Card c2 = new Card();

    @Test
    public void testGetColor(){
        assertEquals("yellow", c1.getColor());
        assertEquals("black", c2.getColor());
    }

    @Test
    public void testGetID(){
        assertEquals("25", c1.getId());
        assertEquals("null", c2.getId());
    }

    @Test
    public void testToString(){
        assertEquals("Id:25", c1.toString());
        assertEquals("Id:null", c2.toString());
    }
}
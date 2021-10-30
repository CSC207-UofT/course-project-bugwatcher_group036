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
    public void TestgetColor(){
        assertEquals("yellow", c1.getColor());
        assertEquals("black", c2.getColor());
    }

    @Test
    public void TestgetID(){
        assertEquals("25", c1.getId());
        assertEquals("nullid", c2.getId());
    }

    @Test
    public void TesttoString(){
        assertEquals("Id:25", c1.toString());
        assertEquals("Id:nullid", c2.toString());
    }
}

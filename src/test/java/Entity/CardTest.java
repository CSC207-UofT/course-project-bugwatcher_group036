package Entity;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class CardTest {

    @Test
    public void TestgetColor(){
        Card c1 = new Card("yellow", "25");
        Card c2 = new Card();
        assertEquals("yellow", c1.getColor());
        assertEquals("black", c2.getColor());
    }

    @Test
    public void TestgetID(){
        Card c1 = new Card("yellow", "25");
        Card c2 = new Card();
        assertEquals("25", c1.getId());
        assertEquals("nullid", c2.getId());
    }


}

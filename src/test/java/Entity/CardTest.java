package Entity;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
public class CardTest {

    Card c1 = new Card("yellow", "25");
    Card c2 = new Card();


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



}

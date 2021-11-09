package Entity;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionCardTest {
    Player p1 = new Player("player1", 1);
    FunctionCard c1 = new FunctionCard("yellow", "switch", "id");
    FunctionCard c2 = new FunctionCard("red", "reverse", "id");
    FunctionCard c3 = new FunctionCard("green", "plustwo", "id");
    FunctionCard c4 = new FunctionCard("blue", "skip", "id");
    FunctionCard c5 = new FunctionCard("black", "switch", "id");
    FunctionCard c6 = new FunctionCard("black", "plusfour", "id");

    @Test
    public void TestGetColor_switch() {
        assertEquals("yellow", c1.getColor());
    }

    @Test
    public void TestgetFunction_switch() {
        assertEquals("switch", c1.getFunction());
    }

    @Test
    public void TestgetFeature_switch() {assertEquals("switch", c1.getFeature());}

    @Test
    public void TesttoString_switch() {
        assertEquals("yellow switch", c1.toString());
    }

    @Test
    public void Testdraw_switch() {
        p1.drawCard(c1);
        assertTrue(p1.getHandCard().contains(c1));
    }

    @Test
    public void Testplay_switch() {
        p1.drawCard(c1);
        assertEquals(c1, p1.playCard(c1));
    }

    @Test
    public void TestGetColor_reverse() {
        assertEquals("red", c2.getColor());
    }

    @Test
    public void TestgetFunction_reverse() {
        assertEquals("reverse", c2.getFunction());
    }

    @Test
    public void TestgetFeature_reverse() {assertEquals("reverse", c2.getFeature());}

    @Test
    public void TesttoString_reverse() {
        assertEquals("red reverse", c2.toString());
    }

    @Test
    public void Testdraw_reverse() {
        p1.drawCard(c2);
        assertTrue(p1.getHandCard().contains(c2));
    }

    @Test
    public void Testplay_reverse() {
        p1.drawCard(c2);
        assertEquals(c2, p1.playCard(c2));
    }

    @Test
    public void TestGetColor_plustwo() {
        assertEquals("green", c3.getColor());
    }

    @Test
    public void TestgetFunction_plustwo() {
        assertEquals("plustwo", c3.getFunction());
    }

    @Test
    public void TestgetFeature_plustwo() {assertEquals("plustwo", c3.getFeature());}

    @Test
    public void TesttoString_plustwo() {
        assertEquals("green plustwo", c3.toString());
    }

    @Test
    public void Testdraw_plustwo() {
        p1.drawCard(c3);
        assertTrue(p1.getHandCard().contains(c3));
    }

    @Test
    public void Testplay_plustwo() {
        p1.drawCard(c3);
        assertEquals(c3, p1.playCard(c3));
    }

    @Test
    public void TestGetColor_skip() {
        assertEquals("blue", c4.getColor());
    }

    @Test
    public void TestgetFunction_skip() {
        assertEquals("skip", c4.getFunction());
    }

    @Test
    public void TestgetFeature_skip() {assertEquals("skip", c4.getFeature());}

    @Test
    public void TesttoString_skip() {
        assertEquals("blue skip", c4.toString());
    }

    @Test
    public void Testdraw_skip() {
        p1.drawCard(c4);
        assertTrue(p1.getHandCard().contains(c4));
    }

    @Test
    public void Testplay_skip() {
        p1.drawCard(c4);
        assertEquals(c4, p1.playCard(c4));
    }

    @Test
    public void TestGetColor_blackswitch() {
        assertEquals("black", c5.getColor());
    }

    @Test
    public void TestgetFunction_blackswitch() {
        assertEquals("switch", c5.getFunction());
    }

    @Test
    public void TestgetFeature_blackswitch() {assertEquals("switch", c5.getFeature());}

    @Test
    public void TesttoString_blackswitch() {
        assertEquals("black switch", c5.toString());
    }

    @Test
    public void Testdraw_blackswitch() {
        p1.drawCard(c5);
        assertTrue(p1.getHandCard().contains(c5));
    }

    @Test
    public void Testplay_blackswitch() {
        p1.drawCard(c5);
        assertEquals(c5, p1.playCard(c5));
    }

    @Test
    public void TestGetColor_plusfour() {
        assertEquals("black", c6.getColor());
    }

    @Test
    public void TestgetFunction_plusfour() {
        assertEquals("plusfour", c6.getFunction());
    }

    @Test
    public void TestgetFeature_plusfour() {assertEquals("plusfour", c6.getFeature());}

    @Test
    public void TesttoString_plusfour() {
        assertEquals("black plusfour", c6.toString());
    }

    @Test
    public void Testdraw_plusfour() {
        p1.drawCard(c6);
        assertTrue(p1.getHandCard().contains(c6));
    }

    @Test
    public void Testplay_plusfour() {
        p1.drawCard(c6);
        assertEquals(c6, p1.playCard(c6));
    }
}

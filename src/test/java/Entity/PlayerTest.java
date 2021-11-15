package Entity;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void TestdrawCard() {
        Player p1 = new Player("player1", 1);
        Card c1 = new NumberCard("yellow", 6, "1");
        p1.drawCard(c1);
        assertTrue(p1.getHandCard().contains(c1));
    }

    @Test
    public void TestplayCard() {
        Player p1 = new Player("player1", 1);
        Card c1 = new NumberCard("yellow", 6, "1");
        p1.drawCard(c1);
        assertEquals(c1, p1.playCard(c1));
    }

    @Test
    public void TestgetCardNum() {
        Player p1 = new Player("player1", 1);
        Card c1 = new NumberCard("yellow", 6, "1");
        assertEquals(0, p1.getCardNum());
        p1.drawCard(c1);
        assertEquals(1, p1.getCardNum());
        p1.playCard(c1);
        assertEquals(0, p1.getCardNum());
    }

    @Test
    public void TestgetHandCard() {
        Player p1 = new Player("player1", 1);
        assertEquals(0, p1.getHandCard().size());
        assertTrue(p1.getHandCard().isEmpty());
    }

    @Test
    public void TestgetID() {
        Player p1 = new Player("player1", 1);
        assertEquals("player1", p1.getId());
    }

    @Test
    public void TestgetPosition() {
        Player p1 = new Player("player1", 1);
        assertEquals(1, p1.getPosition());
    }

    @Test
    public void TestIterator(){
        Player p1 = new Player("player1", 1);
        ArrayList<Card> expected = new ArrayList<>();

        Card c1 = new NumberCard("yellow", 6, "1"); p1.drawCard(c1); expected.add(c1);
        Card c2 = new FunctionCard("black", "plustwo", "2"); p1.drawCard(c2); expected.add(c2);
        Card c3 = new FunctionCard("red", "reverse", "3"); p1.drawCard(c3); expected.add(c3);

        int counter = 0;
        for (Card c: p1) {
            assertSame(c, expected.get(counter));
            counter++;
        }
    }

    @Test
    public void testToString(){
        Player p1 = new Player("player1", 1);
        assertEquals("Player{id=player1}", p1.toString());
    }

}
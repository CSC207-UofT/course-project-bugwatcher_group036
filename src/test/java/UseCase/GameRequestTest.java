package UseCase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameRequestTest {
    GameRequest gameRequest = new GameRequest();
    @Test

    public void testInitialization() {
        GameRequest gameRequest1 = new GameRequest();
        assertNull(gameRequest1.getSetColor());
        assertNull(gameRequest1.getSetColorForComputer());
    }

    @Test
    public void testsetSetColor() {
        assertNull(gameRequest.getSetColor());
        gameRequest.setSetColor("blue");
        assertEquals("blue", gameRequest.getSetColor());
        gameRequest.setSetColor("yellow");
        assertEquals("yellow", gameRequest.getSetColor());
        gameRequest.setSetColor("green");
        assertEquals("green", gameRequest.getSetColor());
        gameRequest.setSetColor("red");
        assertEquals("red", gameRequest.getSetColor());
        gameRequest.setSetColor("black");
        assertEquals("black", gameRequest.getSetColor());

    }

    @Test
    public void testgetSetColor() {
        assertNull(gameRequest.getSetColor());
        gameRequest.setSetColor("red");
        assertEquals("red", gameRequest.getSetColor());
        gameRequest.setSetColor("yellow");
        assertEquals("yellow", gameRequest.getSetColor());
        gameRequest.setSetColor("green");
        assertEquals("green", gameRequest.getSetColor());
        gameRequest.setSetColor("blue");
        assertEquals("blue", gameRequest.getSetColor());
        gameRequest.setSetColor("black");
        assertEquals("black", gameRequest.getSetColor());

    }

    @Test
    public void testsetSetColorForComputer() {
        assertNull(gameRequest.getSetColor());
        gameRequest.setSetColorForComputer("yellow");
        assertEquals("yellow", gameRequest.getSetColorForComputer());
        gameRequest.setSetColorForComputer("red");
        assertEquals("red", gameRequest.getSetColorForComputer());
        gameRequest.setSetColorForComputer("black");
        assertEquals("black", gameRequest.getSetColorForComputer());
        gameRequest.setSetColorForComputer("green");
        assertEquals("green", gameRequest.getSetColorForComputer());
        gameRequest.setSetColorForComputer("blue");
        assertEquals("blue", gameRequest.getSetColorForComputer());
    }

    @Test
    public void testgetSetColorForComputer() {
        assertNull(gameRequest.getSetColor());
        gameRequest.setSetColorForComputer("green");
        assertEquals("green", gameRequest.getSetColorForComputer());
        gameRequest.setSetColorForComputer("red");
        assertEquals("red", gameRequest.getSetColorForComputer());
        gameRequest.setSetColorForComputer("black");
        assertEquals("black", gameRequest.getSetColorForComputer());
        gameRequest.setSetColorForComputer("yellow");
        assertEquals("yellow", gameRequest.getSetColorForComputer());
        gameRequest.setSetColorForComputer("blue");
        assertEquals("blue", gameRequest.getSetColorForComputer());

    }

}

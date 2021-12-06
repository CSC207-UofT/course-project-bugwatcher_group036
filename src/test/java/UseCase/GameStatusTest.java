package UseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameStatusTest {
    GameStatus gameStatus;

    @BeforeEach
    public void Intialization() {
        this.gameStatus = new GameStatus(5);
    }

    @Test
    public void testisWinFlag() {
        assertFalse(gameStatus.isWinFlag());
        gameStatus.setWinFlag(true);
        assertTrue(gameStatus.isWinFlag());
    }

    @Test
    public void testsetWinFlag() {
        assertFalse(gameStatus.isWinFlag());
        gameStatus.setWinFlag(false);
        assertFalse(gameStatus.isWinFlag());
        gameStatus.setWinFlag(true);
        assertTrue(gameStatus.isWinFlag());
    }

    @Test
    public void testgetCurrentPlayerIndex() {
        gameStatus.setCurrentPlayerIndex(0);
        assertEquals(0, gameStatus.getCurrentPlayerIndex());
        gameStatus.setCurrentPlayerIndex(3);
        assertEquals(3, gameStatus.getCurrentPlayerIndex());
    }

    @Test
    public void testsetCurrentPlayerIndex() {
        int index = gameStatus.getCurrentPlayerIndex();
        gameStatus.setCurrentPlayerIndex(index);
        assertEquals(index, gameStatus.getCurrentPlayerIndex());
        gameStatus.setCurrentPlayerIndex(0);
        assertEquals(0, gameStatus.getCurrentPlayerIndex());
        gameStatus.setCurrentPlayerIndex(3);
        assertEquals(3, gameStatus.getCurrentPlayerIndex());
        gameStatus.setCurrentPlayerIndex(2);
        assertEquals(2, gameStatus.getCurrentPlayerIndex());
    }

    @Test
    public void testsetSkip() {
        assertFalse(gameStatus.isSkip());
        gameStatus.setSkip(true);
        assertTrue(gameStatus.isSkip());
        gameStatus.setSkip(false);
        assertFalse(gameStatus.isSkip());
    }

    @Test
    public void testisSkip() {
        gameStatus.setSkip(true);
        assertTrue(gameStatus.isSkip());
        gameStatus.setSkip(false);
        assertFalse(gameStatus.isSkip());
    }

    @Test
    public void testgetPlus() {
        assertEquals(0, gameStatus.getPlus());
        gameStatus.setPlus(1);
        assertEquals(1, gameStatus.getPlus());
        gameStatus.setPlus(4);
        assertEquals(4, gameStatus.getPlus());
        gameStatus.setPlus(10);
        assertEquals(10, gameStatus.getPlus());
    }

    @Test
    public void testsetPlus() {
        gameStatus.setPlus(0);
        assertEquals(0, gameStatus.getPlus());
        gameStatus.setPlus(3);
        assertEquals(3, gameStatus.getPlus());
        gameStatus.setPlus(10);
        assertEquals(10, gameStatus.getPlus());
    }

    @Test
    public void testfunctionCardResponse() {
        gameStatus.functionCardResponse("skip");
        assertTrue(gameStatus.isSkip());
        gameStatus.functionCardResponse("+2");
        assertEquals(2, gameStatus.getPlus());
        gameStatus.functionCardResponse("+4");
        assertEquals(6, gameStatus.getPlus());
        gameStatus.setPlus(0);
        gameStatus.functionCardResponse("+4");
        assertEquals(4, gameStatus.getPlus());
    }

    @Test
    public void testmoveToNextPlayer() {
        gameStatus.setCurrentPlayerIndex(0);
        assertEquals(1, gameStatus.moveToNextPlayer());
        gameStatus.functionCardResponse("reverse");
        assertEquals(0, gameStatus.moveToNextPlayer());
        assertEquals(4, gameStatus.moveToNextPlayer());
        assertEquals(3, gameStatus.moveToNextPlayer());
        gameStatus.functionCardResponse("reverse");
        assertEquals(4, gameStatus.moveToNextPlayer());
    }
}

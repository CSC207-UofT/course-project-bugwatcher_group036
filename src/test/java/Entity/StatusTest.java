package Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StatusTest {

    @Test
    public void testInitialization(){
        Status s = new Status(4);
        assertTrue(s.getCurrentPlayerIndex() < 4 &&
                s.getCurrentPlayerIndex() > -1);
    }

    @Test
    public void testChangeReverseAndMoveAndGetIndex() {
        Status s = new Status(4);
        int curr = s.getCurrentPlayerIndex();
        s.setCurrentPlayerIndex(s.moveToNextPlayer());
        assertTrue(curr + 1 == s.getCurrentPlayerIndex() ||
                s.getCurrentPlayerIndex() == 0);
        s.changeReverseState();
        s.setCurrentPlayerIndex(s.moveToNextPlayer());
        assertEquals(curr, s.getCurrentPlayerIndex());
    }

    @Test
    public void testWinFlag(){
        Status s = new Status(4);
        assertFalse(s.isWinFlag());
        s.setWinFlag(true);
        assertTrue(s.isWinFlag());
    }

    @Test
    public void testSkip(){
        Status s = new Status(4);
        assertFalse(s.isSkip());
        s.setSkip(true);
        assertTrue(s.isSkip());
    }

    @Test
    public void testPlus(){
        Status s = new Status(4);
        assertEquals(0, s.getPlus());
        s.setPlus(2);
        assertEquals(2, s.getPlus());
    }

    @Test
    public void testFunctionResponse(){
        Status s = new Status(4);
        s.functionCardResponse("skip");
        s.functionCardResponse("reverse");
        s.functionCardResponse("+2");
        s.functionCardResponse("+4");
        int curr = s.getCurrentPlayerIndex();
        assertEquals(6, s.getPlus());
        assertTrue(s.isSkip());
        s.setCurrentPlayerIndex(s.moveToNextPlayer());
        assertTrue(curr - 1 == s.getCurrentPlayerIndex() ||
                s.getCurrentPlayerIndex() == 3);
    }

    @Test
    public void testSetQuit() {
        Status s = new Status(4);
        s.setQuit();
        assertEquals(s.getCurrentPlayerIndex(), -10);
    }
}

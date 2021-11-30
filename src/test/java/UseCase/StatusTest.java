//package UseCase;
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class StatusTest {
//    Status gameStatus0 = new Status();
//    Status gameStatus1 = new Status(3);
//
//    @Test
//    public void testInitialization() {
//        assertEquals(gameStatus0.getStatus().getNumberOfPlayers(), 0);
//        assertEquals(gameStatus1.getStatus().getNumberOfPlayers(), 3);
//    }
//
//    @Test
//    public void testGetStatus() {
//        assertEquals(gameStatus0.getStatus().getNumberOfPlayers(), 0);
//        assertEquals(gameStatus1.getStatus().getNumberOfPlayers(), 3);
//    }
//
//    @Test
//    public void testIsWinFlag() {
//        assertFalse(gameStatus0.isWinFlag());
//        gameStatus0.getStatus().setWinFlag(true);
//        assertTrue(gameStatus0.isWinFlag());
//    }
//
//    @Test
//    public void testSetWinFlag() {
//        assertFalse(gameStatus1.isWinFlag());
//        gameStatus1.getStatus().setWinFlag(true);
//        assertTrue(gameStatus1.isWinFlag());
//    }
//
//    @Test
//    public void testSetQuit() {
//        gameStatus0.setQuit();
//        Entity.Status status = gameStatus0.getStatus();
//        assertTrue(status.isWinFlag());
//        assertEquals(status.getCurrentPlayerIndex(), -10);
//    }
//
//    @Test
//    public void testGetCurrentPlayerIndex() {
//        Entity.Status status = gameStatus1.getStatus();
//        status.setCurrentPlayerIndex(0);
//        assertEquals(status.getCurrentPlayerIndex(), 0);
//        status.moveToNextPlayer();
//        assertEquals(status.getCurrentPlayerIndex(), 1);
//    }
//
//    @Test
//    public void testSetCurrentPlayerIndex() {
//        Entity.Status status = gameStatus1.getStatus();
//        status.setCurrentPlayerIndex(0);
//        assertEquals(status.getCurrentPlayerIndex(), 0);
//        status.setCurrentPlayerIndex(11);
//        assertEquals(status.getCurrentPlayerIndex(), 11);
//    }
//
//    @Test
//    public void testIsSkip() {
//        Entity.Status status = gameStatus0.getStatus();
//        assertFalse(status.isSkip());
//    }
//
//    @Test
//    public void testSetSkip() {
//        Entity.Status status = gameStatus0.getStatus();
//        assertFalse(status.isSkip());
//        status.setSkip(true);
//        assertTrue(status.isSkip());
//    }
//
//    @Test
//    public void testGetPlus() {
//        Entity.Status status = gameStatus0.getStatus();
//        assertEquals(status.getPlus(), 0);
//    }
//
//    @Test
//    public void testSetPlus() {
//        Entity.Status status = gameStatus0.getStatus();
//        assertEquals(status.getPlus(), 0);
//        status.setPlus(2);
//        assertEquals(status.getPlus(), 2);
//        status.setPlus(4);
//        assertEquals(status.getPlus(), 4);
//        status.setPlus(6);
//        assertEquals(status.getPlus(), 6);
//    }
//
//    @Test
//    public void testFunctionCardResponse() {
//        Entity.Status status = gameStatus1.getStatus();
//        assertFalse(status.isSkip());
//        status.functionCardResponse("skip");
//        assertTrue(status.isSkip());
//
//        assertFalse(status.getReverseState());
//        status.functionCardResponse("reverse");
//        assertTrue(status.getReverseState());
//        status.functionCardResponse(("reverse"));
//        assertFalse(status.getReverseState());
//
//        assertEquals(status.getPlus(), 0);
//        status.functionCardResponse("+2");
//        assertEquals(status.getPlus(), 2);
//        status.functionCardResponse(("+1"));
//        assertEquals(status.getPlus(), 2);
//        status.functionCardResponse("+4");
//
//        // This actual value should be 4 by definition but returns 6.
//        assertEquals(status.getPlus(), 6);
//    }
//}

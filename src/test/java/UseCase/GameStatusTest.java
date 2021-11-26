package UseCase;

import Entity.Deck;
import Entity.CardHolder;
import Entity.Status;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameStatusTest {
    GameStatus gameStatus = new GameStatus();

    @Test
    public void testInitialization() {
        GameStatus gameStatus1 = new GameStatus(3);
        assertEquals(gameStatus.getStatus().getNumberOfPlayers(), 0);
        assertEquals(gameStatus1.getStatus().getNumberOfPlayers(), 3);
    }

    @Test
    public void testGetStatus() {
        GameStatus gameStatus1 = new GameStatus(3);
        assertEquals(gameStatus.getStatus().getNumberOfPlayers(), 0);
        assertEquals(gameStatus1.getStatus().getNumberOfPlayers(), 3);
    }
}

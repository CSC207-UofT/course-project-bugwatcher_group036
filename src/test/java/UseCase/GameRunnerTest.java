package UseCase;

import Controller.Presenter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameRunnerTest {
    GameRequest gameRequest = new GameRequest();
    ArrayList<String> IDS = new ArrayList<>();
    GameRunner gameRunner;
    GameResponse gameResponse;
    @BeforeEach
    public void generateIds() {
        for (int i = 0; i < 7; i++) {
            IDS.add("Computer " + i);
        }
        gameRunner = new GameRunner(gameRequest, IDS);
        gameResponse = gameRunner.getGameResponse();
    }

    @Test
    public void testgetGameResponse() {
        assertEquals(gameResponse, gameRunner.getGameResponse());

    }
    @Test
    public void testsetGameRequest() {
        GameRequest gameRequest1 = new GameRequest();
        gameRunner.setGameRequest(gameRequest1);
        assertEquals(gameRequest1, gameRunner.getGameRequest());

    }
    @Test
    public void testgetGameRequest() {
        assertEquals(gameRequest, gameRunner.getGameRequest());

    }

}

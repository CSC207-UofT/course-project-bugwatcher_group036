package UseCase;

import Controller.Presenter;
import Entity.CardHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CardCheckerTest {
    GameRequest gameRequest = new GameRequest();
    ArrayList<String> IDS = new ArrayList<>();
    GameRunner gameRunner;
    GameResponse gameResponse;
    IPresenter iPresenter;
    EachRound eachRound;


    @BeforeEach
    public void generateIds() {
        for (int i = 0; i < 2; i++) {
            IDS.add("Computer " + i);
        }
        gameRunner = new GameRunner(gameRequest, IDS);
        gameResponse = gameRunner.getGameResponse();
        iPresenter = new Presenter();
        gameRunner.buildIEachRound(iPresenter, gameRequest);
        eachRound = gameRunner.getEachRound();
        iPresenter.setGameRequest(gameRequest);
    }

    @Test
    public void testInitialization(){
        CardChecker checker = new CardChecker();
        assertNull(checker.getLastCard());
    }
    @Test
    public void testgetLastCard(){
        CardChecker checker = new CardChecker();
        assertNull(checker.getLastCard());
        checker.setLastCard("blue 4");
        assertEquals("blue 4", checker.getLastCard());
        checker.setLastCard("black +4");
        assertEquals("black +4", checker.getLastCard());
    }
    @Test
    public void testsetLastCard(){
        CardChecker checker = new CardChecker();
        checker.setLastCard("yellow 6");
        assertEquals("yellow 6", checker.getLastCard());
        checker.setLastCard("red +2");
        assertEquals("red +2", checker.getLastCard());
    }
}

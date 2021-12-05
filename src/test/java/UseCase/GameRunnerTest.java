package UseCase;

import Entity.CardHolder;
import LogIn.LogInEntity.User;
import LogIn.LogInEntity.UserList;
import LogIn.LogInEntity.UserStatistics;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameRunnerTest {
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
        iPresenter = new IPresenter() {
            @Override
            public void drawCardNotification(String drawn, boolean noCard, boolean computer) {
            }

            @Override
            public void setGameRunner(GameRunner gameRunner) {
            }

            @Override
            public void setGameRequest(GameRequest gameRequest) {
            }

            @Override
            public void setColorForComputer(String color) {
            }

            @Override
            public void lastcard(String cardname) {
            }

            @Override
            public String RemainingCards() {
                return null;
            }

            @Override
            public CardHolder allhandcards() {
                return null;
            }

            @Override
            public void setColorGUI() {
            }

            @Override
            public void drawManyCard(int numToDraw, StringBuilder drawnCardName, boolean computer) {
            }
        };
        gameRunner.buildIEachRound(iPresenter, gameRequest);
        eachRound = gameRunner.getEachRound();
        iPresenter.setGameRunner(gameRunner);
        iPresenter.setGameRequest(gameRequest);

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
        GameRequest gameRequest1 = new GameRequest();
        gameRunner.setGameRequest(gameRequest1);
        assertEquals(gameRequest1, gameRunner.getGameRequest());

    }

    @Test
    public void testgetEachRound() {
        assertEquals(eachRound, gameRunner.getEachRound());
        gameRunner.buildIEachRound(iPresenter, gameRequest);
        EachRound eachRound1 = gameRunner.getEachRound();
        assertEquals(eachRound1, gameRunner.getEachRound());

    }

    @Test
    public void testbuildIEachRound() {
        gameRunner.buildIEachRound(iPresenter, gameRequest);
        EachRound eachRound1 = gameRunner.getEachRound();
        assertEquals(eachRound1, gameRunner.getEachRound());

    }

    @Test
    public void testrunGameforGUI() {
        UserList users = new UserList();
        users.add(new User("a", "a"));
        UserStatistics stats = new UserStatistics("a");
        CardHolder playableCards = eachRound.beginStage();
        if (!eachRound.getGameBoard().getGameCardHolders().isEmpty(playableCards)) {
            int before = playableCards.getSize();
            gameResponse.setCardHolder(playableCards);
            String toPlay = playableCards.playCardWithIndex(2);
            playableCards.addCard(toPlay);
            gameRunner.runGameforGUI(toPlay, stats);
            int played = playableCards.getSize();
            assertEquals(before, played + 1);
        }
    }

    @Test
    public void testrunGameforGUI2() {
        UserList users = new UserList();
        users.add(new User("a", "a"));
        UserStatistics stats = new UserStatistics("a");
        CardHolder playableCards = eachRound.beginStage();
        playableCards.addCard("black switch");
        if (!eachRound.getGameBoard().getGameCardHolders().isEmpty(playableCards)) {
            int before = playableCards.getSize();
            gameResponse.setCardHolder(playableCards);
            String toPlay = playableCards.playCardWithIndex(7);
            playableCards.addCard(toPlay);
            gameRunner.runGameforGUI(toPlay, stats);
            int played = playableCards.getSize();
            assertEquals(before, played + 1);
        }
    }

    @Test
    public void testrunGameforGUIComputer() {
        CardHolder playableCards = eachRound.beginStage();
        int before = playableCards.getSize();
        gameRunner.runGameforGUIComputer();
        int played = playableCards.getSize();
        assertEquals(before, played + 1);

    }

    @Test
    public void testrunGameforGUIComputer2() {
        CardHolder playableCards = eachRound.beginStage();
        for (int i = 6; -1 < i; i--) {
            playableCards.playCardWithIndex(i);
        }
        playableCards.addCard("black switch");
        playableCards.addCard("red 4");
        int before = playableCards.getSize();
        gameRunner.runGameforGUIComputer();
        int played = playableCards.getSize();
        assertEquals(before, played + 1);

    }

    @Test
    public void testrunGameforGUIComputer3() {
        CardHolder playableCards = eachRound.beginStage();
        for (int i = 6; -1 < i; i--) {
            playableCards.playCardWithIndex(i);
        }
        playableCards.addCard("red 4");

        gameRunner.runGameforGUIComputer();
        CardHolder playableCards2 = eachRound.beginStage();
        for (int i = playableCards2.getSize() - 1; -1 < i; i--) {
            playableCards2.playCardWithIndex(i);
        }
        playableCards2.addCard("yellow 7");
        int before = playableCards2.getSize();
        gameRunner.runGameforGUIComputer();
        int played = playableCards2.getSize();
        assertEquals(before, played);
    }

    @Test
    public void testrunGameforGUIComputer4() {
        CardHolder playableCards = eachRound.beginStage();
        for (int i = 6; -1 < i; i--) {
            playableCards.playCardWithIndex(i);
        }
        playableCards.addCard("red +2");

        gameRunner.runGameforGUIComputer();
        CardHolder playableCards2 = eachRound.beginStage();
        for (int i = playableCards2.getSize() - 1; -1 < i; i--) {
            playableCards2.playCardWithIndex(i);
        }
        playableCards2.addCard("yellow 7");
        int before = playableCards2.getSize();
        gameRunner.runGameforGUIComputer();
        int played = playableCards2.getSize();
        assertEquals(before, played);
    }

    @Test
    public void testrunGameforGUIComputer5() {
        CardHolder playableCards = eachRound.beginStage();
        for (int i = 6; -1 < i; i--) {
            playableCards.playCardWithIndex(i);
        }
        playableCards.addCard("black switch");
        int before = playableCards.getSize();
        gameRunner.runGameforGUIComputer();
        int played = playableCards.getSize();
        assertEquals(before, played);
    }
}

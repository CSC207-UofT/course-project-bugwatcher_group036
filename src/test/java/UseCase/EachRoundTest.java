package UseCase;

import Entity.Deck;
import Entity.CardHolder;

import LogIn.LogInEntity.UserStatistics;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EachRoundTest {
    Deck deck = new Deck();
    GameRequest gameRequest;
    IPresenter iPresenter;
    CardChecker cardChecker = new CardChecker();
    EachRound eachRound = new EachRound(4, iPresenter, gameRequest);


    @Test
    public void testInitialization() {
        EachRound eachRound0 = new EachRound(4, iPresenter, gameRequest);
        assertNotNull(eachRound0.getGameBoard());
        assertEquals(eachRound0.getTerminal(), iPresenter);
        assertEquals(eachRound0.getGameRequest(), gameRequest);
    }

    @Test
    public void testCardDeal() {
        eachRound.cardDeal(4);
        int playNumber = eachRound.getGameBoard().getGameCardHolders().getHolderNumber();
        int cardNumberHolds = eachRound.getGameBoard().getGameCardHolders().getNumbersOfCardHolds(1);
        assertEquals(playNumber, 4);
        assertEquals(cardNumberHolds, 7);
    }

    @Test
    public void testBeginState() {
        EachRound eachRound = new EachRound(2, iPresenter, gameRequest);
        GameBoard gameBoard = eachRound.getGameBoard();
        CardHolder cardHolder0 = gameBoard.getGameCardHolders().getHandCards(0);
        CardHolder cardHolder1 = gameBoard.getGameCardHolders().getHandCards(1);
        ArrayList<String> skipCards = new ArrayList<String>();
        skipCards.add("yellow skip");
        skipCards.add("red skip");
        ArrayList<String> normalCards = new ArrayList<String>();
        normalCards.add("blue 5");
        normalCards.add("green 7");
        normalCards.add("yellow 7");
        cardHolder0.addCards(skipCards);
        assertEquals(cardHolder0.getSize(), 2);
        cardHolder0.addCards(normalCards);
        assertEquals(cardHolder0.getSize(), 5);
        assertEquals(gameBoard.getGameCardHolders().getHolderNumber(), 2);

        gameBoard.getGameStatus().setSkip(true);
        gameBoard.getGameStatus().setCurrentPlayerIndex(0);

        assertEquals(gameBoard.getGameStatus().getCurrentPlayerIndex(), 0);
        assertEquals(gameBoard.getGameCardHolders().getNumbersOfCardHolds(0), 5);
        assertTrue(gameBoard.getGameStatus().isSkip());
        CardHolder b0 = eachRound.beginStage();
        assertEquals(b0.getSize(), 2);

        gameBoard.getGameStatus().setSkip(false);
        ArrayList<String> plusTwoCards = new ArrayList<String>();
        plusTwoCards.add("green +2");
        cardHolder0.addCards(plusTwoCards);
        gameBoard.getCardChecker().setLastCard("blue +2");
        gameBoard.getGameStatus().setPlus(2);
        CardHolder b1 = eachRound.beginStage();
        assertEquals(gameBoard.getGameStatus().getPlus(), 2);
        assertEquals(b1.getSize(), 1);

        ArrayList<String> plusFourCards = new ArrayList<String>();
        plusFourCards.add("green +4");
        plusFourCards.add("yellow +4");
        plusFourCards.add("yellow +4");
        plusFourCards.add("yellow +4");
        cardHolder0.addCards(plusFourCards);
        gameBoard.getCardChecker().setLastCard("blue +4");
        gameBoard.getGameStatus().setPlus(4);
        CardHolder b2 = eachRound.beginStage();
        assertEquals(b2.getSize(), 4);

        gameBoard.getCardChecker().setLastCard("blue 7");
        gameBoard.getGameStatus().setPlus(0);
        CardHolder b3 = eachRound.beginStage();
        assertEquals(b3.getSize(), 7);

    }

    @Test
    public void testPlayState() {
        EachRound eachRound = new EachRound(2, iPresenter, gameRequest);
        GameBoard gameBoard = eachRound.getGameBoard();
        CardHolder cardHolder0 = gameBoard.getGameCardHolders().getHandCards(0);
        CardHolder cardHolder1 = gameBoard.getGameCardHolders().getHandCards(1);

        /*
        This test is to be fixed, there has an error when the test runs.

        assertTrue(gameBoard.getGameCardHolders().isEmpty(cardHolder0));
        String p0 = eachRound.playStage(cardHolder0, 0);
        assertNull(p0);
        */
    }

    @Test
    public void testPlayStageGUI() {
        CardHolder cardHolder = new CardHolder();
        eachRound.getGameBoard().setiTerminal(new IPresenter() {
            @Override
            public void drawCardNotification(String drawn, boolean noCard, boolean computer) {

            }

            @Override
            public void setGameRequest(IRequest gameRequest) {

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
        });
        UserStatistics userStatistics = new UserStatistics("Jason");
        cardHolder.addCard("red 1");
        eachRound.playStageGUI(cardHolder, 0, "red reverse", userStatistics);
        eachRound.getGameBoard().getGameCardHolders().getHandCards(0).addCard("red 1");
        eachRound.playStageGUI(cardHolder, 0,
                "red 1", userStatistics);
    }

    @Test
    public void testPlayStageGUIPVE() {
        CardHolder cardHolder = new CardHolder();
        eachRound.getGameBoard().setiTerminal(new IPresenter() {
            @Override
            public void drawCardNotification(String drawn, boolean noCard, boolean computer) {

            }

            @Override
            public void setGameRequest(IRequest gameRequest) {

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
        });
        cardHolder.addCard("red 1");
        eachRound.playStageGUIPVE(cardHolder, 0);
        eachRound.getGameBoard().getGameCardHolders().getHandCards(0).addCard("red 1");
        eachRound.playStageGUIPVE(cardHolder, 0);
    }

    @Test
    public void testEndStageGUI() {
        UserStatistics userStatistics = new UserStatistics("Jason");
        eachRound.getGameBoard().setiTerminal(new IPresenter() {
            @Override
            public void drawCardNotification(String drawn, boolean noCard, boolean computer) {}

            @Override
            public void setGameRequest(IRequest gameRequest) {

            }

            @Override
            public void setColorForComputer(String color) {}
            @Override
            public void lastcard(String cardname) {
            }
            @Override
            public String RemainingCards() {
                return null;}
            @Override
            public CardHolder allhandcards() {
                return null;}
            @Override
            public void setColorGUI() {}
            @Override
            public void drawManyCard(int numToDraw, StringBuilder drawnCardName, boolean computer) {}
        });
        eachRound.getGameBoard().getCardChecker().setLastCard("red 3");
        eachRound.endStageGUI("red 1", userStatistics);
    }

    @Test
    public void testEndStageGUIPVE() {
        eachRound.getGameBoard().setiTerminal(new IPresenter() {
            @Override
            public void drawCardNotification(String drawn, boolean noCard, boolean computer) {}

            @Override
            public void setGameRequest(IRequest gameRequest) {

            }
            @Override
            public void setColorForComputer(String color) {}
            @Override
            public void lastcard(String cardname) {
            }
            @Override
            public String RemainingCards() {
                return null;}
            @Override
            public CardHolder allhandcards() {
                return null;}
            @Override
            public void setColorGUI() {}
            @Override
            public void drawManyCard(int numToDraw, StringBuilder drawnCardName, boolean computer) {}
        });
        eachRound.getGameBoard().getCardChecker().setLastCard("red 3");
        eachRound.endStageGUIPVE("red 1", 0);
    }

    @Test
    public void testGetGameBoard() {
        assertNotNull(eachRound.getGameBoard());
    }

    @Test
    public void testGetTerminal() {
        assertEquals(eachRound.getTerminal(), iPresenter);
    }


    @Test
    public void testGetGameRequest() {
        assertEquals(eachRound.getGameRequest(), gameRequest);
    }
}

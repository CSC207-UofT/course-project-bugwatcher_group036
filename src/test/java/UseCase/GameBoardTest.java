package UseCase;

import Entity.CardHolder;
import Entity.Deck;
import Entity.Status;
import LogIn.LogInEntity.UserStatistics;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {

    @Test
    public void testInitialization() {
        GameBoard gameBoard = new GameBoard(4);
    }

    @Test
    public void testGetMethods() {
        GameBoard gameBoard = new GameBoard(4);
        Status status = gameBoard.getStatus();
        CardChecker cardChecker = gameBoard.getCardChecker();
        Deck deck = gameBoard.getDeck();
        GameCardHolders gameCardHolders = gameBoard.getGameCardHolders();
    }

    @Test
    public void testDrawCard() {
        GameBoard gameBoard = new GameBoard(4);
        gameBoard.setiTerminal(new IPresenter() {
            @Override
            public void drawCardNotification(String drawn, boolean noCard, boolean computer) {}
            @Override
            public void setGameRunner(GameRunner gameRunner) {}
            @Override
            public void setGameRequest(GameRequest gameRequest) {}
            @Override
            public void setColorForComputer(String color) {}
            @Override
            public void beginStage() {}
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
        String drawnCard = gameBoard.drawCard();
        String drawnCard2 = gameBoard.drawCardWithNotification(true, false);
        String drawnCard3 = gameBoard.drawCardWithNotification(false, false);
        String drawnCard4 = gameBoard.drawCardWithNotification(true, true);
        String drawnCard5 = gameBoard.drawCardWithNotification(false, false);
        ArrayList<String> colors = new ArrayList<>();
        Collections.addAll(colors, "red", "green", "blue", "yellow", "black");
        assertTrue(colors.contains(drawnCard.split(" ")[0]));
    }

    @Test
    public void testPlusManyNextPlayer() {
        GameBoard gameBoard = new GameBoard(4);
        gameBoard.setiTerminal(new IPresenter() {
            @Override
            public void drawCardNotification(String drawn, boolean noCard, boolean computer) {}
            @Override
            public void setGameRunner(GameRunner gameRunner) {}
            @Override
            public void setGameRequest(GameRequest gameRequest) {}
            @Override
            public void setColorForComputer(String color) {}
            @Override
            public void beginStage() {}
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
        gameBoard.getStatus().setPlus(4);
        gameBoard.plusManyNextPlayer(new CardHolder(), false);
        gameBoard.plusManyNextPlayer(new CardHolder(), true);
    }

    @Test
    public void testOperationWhenNoCard() {
        GameBoard gameBoard = new GameBoard(4);
        gameBoard.setiTerminal(new IPresenter() {
            @Override
            public void drawCardNotification(String drawn, boolean noCard, boolean computer) {}
            @Override
            public void setGameRunner(GameRunner gameRunner) {}
            @Override
            public void setGameRequest(GameRequest gameRequest) {}
            @Override
            public void setColorForComputer(String color) {}
            @Override
            public void beginStage() {}
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
        gameBoard.getCardChecker().setLastCard("red 1");
        UserStatistics userStatistics = new UserStatistics("Jason");
        gameBoard.operationWhenNoPlayableCard(false, userStatistics);
    }

    @Test
    public void testPunishOrPlayCard() {
        GameBoard gameBoard = new GameBoard(4);
        gameBoard.setiTerminal(new IPresenter() {
            @Override
            public void drawCardNotification(String drawn, boolean noCard, boolean computer) {}
            @Override
            public void setGameRunner(GameRunner gameRunner) {}
            @Override
            public void setGameRequest(GameRequest gameRequest) {}
            @Override
            public void setColorForComputer(String color) {}
            @Override
            public void beginStage() {}
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
        gameBoard.punishOrPlayCard(null, false);
        gameBoard.punishOrPlayCard(null, true);
        gameBoard.punishOrPlayCard("red 1", false);
    }

    @Test
    public void testCheckLastCard() {
        GameBoard gameBoard = new GameBoard(4);
        GameRequest gameRequest = new GameRequest();
        gameBoard.setiTerminal(new IPresenter() {
            @Override
            public void drawCardNotification(String drawn, boolean noCard, boolean computer) {}
            @Override
            public void setGameRunner(GameRunner gameRunner) {}
            @Override
            public void setGameRequest(GameRequest gameRequest) {}
            @Override
            public void setColorForComputer(String color) {}
            @Override
            public void beginStage() {}
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
        UserStatistics userStatistics = new UserStatistics("Jason");
        gameBoard.checkLastCard("red 1", gameRequest, userStatistics);
        gameBoard.checkLastCard("red reverse", gameRequest, userStatistics);
        gameBoard.checkLastCardForComputer("red 1", gameRequest);
        gameBoard.checkLastCardForComputer("red reverse", gameRequest);
    }
}

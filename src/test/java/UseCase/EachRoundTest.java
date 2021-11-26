package UseCase;

import Entity.Deck;
import Entity.CardHolder;
import Entity.Status;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EachRoundTest {
    Deck deck = new Deck();
    GameBoard gameBoard = new GameBoard(4);
    GameRequest gameRequest;
    IPresenter iPresenter;
    CardChecker cardChecker = new CardChecker();
    EachRound eachRound = new EachRound(gameBoard, iPresenter, gameRequest);


    @Test
    public void testInitialization() {
        EachRound eachRound0 = new EachRound(gameBoard, iPresenter, gameRequest);
        assertEquals(eachRound0.getGameBoard(), gameBoard);
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

    }

    @Test
    public void testGetGameBoard() {
        assertEquals(eachRound.getGameBoard(), gameBoard);
    }

    @Test
    public void testGetTerminal() {
        assertEquals(eachRound.getTerminal(), iPresenter);
    }

    @Test
    public void testGetCardChecker() {
        assertEquals(eachRound.getCardChecker().getClass(), cardChecker.getClass());
    }

    @Test
    public void testGetGameRequest() {
        assertEquals(eachRound.getGameRequest(), gameRequest);
    }
}

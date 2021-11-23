package Controller;

import Entity.Deck;
import Entity.HandCard;
import Entity.Status;
import UseCase.CardChecker;
import UseCase.Dealer;
import UseCase.GameBoard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EachRoundTest {
    Deck deck = new Deck();
    GameBoard gameBoard = new GameBoard(4);
    Dealer dealer = new Dealer(deck);
    CardChecker cardChecker;
    ITerminal iTerminal;

    EachRound eachRound = new EachRound(gameBoard, dealer, cardChecker);


    @Test
    public void testInitialization(){
        EachRound eachRound0 = new EachRound(gameBoard, dealer, cardChecker);
        assertEquals(eachRound0.getCardChecker(), cardChecker);
        assertEquals(eachRound0.getGameBoard(), gameBoard);
        assertEquals(eachRound0.getTerminal(), iTerminal);
    }

    @Test
    public void testGetGameBoard() {
        assertEquals(eachRound.getGameBoard(), gameBoard);
    }

    @Test
    public void testGetTerminal() {
        assertEquals(eachRound.getTerminal(), iTerminal);
    }

    @Test
    public void testGetCardChecker() {
        assertEquals(eachRound.getCardChecker(), cardChecker);
    }
}

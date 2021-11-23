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


    @Test
    public void testInitialization(){
        EachRound eachRound = new EachRound(gameBoard, dealer, cardChecker);
        assertEquals(eachRound.getCardChecker(), cardChecker);

    }

    @Test
    public void testGetGameBoard() {
    }

    @Test
    public void testGetTerminal() {
    }

    @Test
    public void testGetCardChecker() {
    }
}

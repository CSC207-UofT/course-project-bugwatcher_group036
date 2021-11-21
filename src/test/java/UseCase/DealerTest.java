package UseCase;

import Controller.ITerminal;
import Entity.Deck;
import Entity.HandCard;
import Entity.Status;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class DealerTest {

    @Test
    public void testInitialization(){
        Deck table = new Deck();
        Dealer deal = new Dealer(table);
        assertEquals(table, deal.getDeck());
    }

    @Test
    public void testDrawCard(){
        Deck table = new Deck();
        ArrayList<String> temp = new ArrayList<>();
        temp.add("blue reverse");
        table.setUnused(temp);
        ArrayList<String> temp1 = table.getUnusedCardDeck();
        String tempCard = temp1.get(0);
        Dealer deal = new Dealer(table);
        String card = deal.drawCard();
        assertEquals(tempCard, card);
    }

    @Test
    public void testDrawCardWithNotification(){
        Deck table = new Deck();
        ArrayList<String> temp = new ArrayList<>();
        temp.add("blue reverse");
        table.setUnused(temp);
        ArrayList<String> temp1 = table.getUnusedCardDeck();
        String tempCard = temp1.get(0);
        Dealer deal = new Dealer(table);
        ITerminal ter = new ITerminal() {
            @Override
            public void printString(String message) {}

            @Override
            public void drawCardNotification(String drawn, boolean noCard) {}

            @Override
            public String typeSetColor() {return null;}

            @Override
            public void beginStage(int currentPlayerIndex, ArrayList<String> ids) {}

            @Override
            public void playStage(GameBoard gameBoard, CardChecker cardChecker, HandCard playableCards) {}

            @Override
            public String getCardToPlay() {return null;}
        };
        deal.setiTerminal(ter);
        String card = deal.drawCardWithNotification(true);
        String card1 = deal.drawCardWithNotification(false);
        assertEquals(tempCard, card);
        assertNull(card1);
    }

    @Test
    public void testPlusManyNextPlayer(){
        Deck table = new Deck();
        Dealer deal = new Dealer(table);
        ITerminal ter = new ITerminal() {
            @Override
            public void printString(String message) {}

            @Override
            public void drawCardNotification(String drawn, boolean noCard) {}

            @Override
            public String typeSetColor() {return null;}

            @Override
            public void beginStage(int currentPlayerIndex, ArrayList<String> ids) {}

            @Override
            public void playStage(GameBoard gameBoard, CardChecker cardChecker, HandCard playableCards) {}

            @Override
            public String getCardToPlay() {return null;}
        };
        deal.setiTerminal(ter);
        Status var = new Status(4);
        HandCard toTest1 = new HandCard();
        toTest1.addCard("red 1");
        toTest1.addCard("yellow 3");
        toTest1.addCard("blue skip");
        deal.plusManyNextPlayer(var, toTest1);
        Status var1 = new Status(4);
        var1.setPlus(6);
        HandCard toTest2 = new HandCard();
        toTest2.addCard("red 1");
        toTest2.addCard("yellow 3");
        toTest2.addCard("blue skip");
        deal.plusManyNextPlayer(var1, toTest2);
        assertEquals(3, toTest1.getSize());
        assertEquals(9, toTest2.getSize());
    }

    @Test
    public void testOperationWhenNoPlayableCard(){
        Deck table = new Deck();
        Dealer deal = new Dealer(table);
        ITerminal ter = new ITerminal() {
            @Override
            public void printString(String message) {}

            @Override
            public void drawCardNotification(String drawn, boolean noCard) {}

            @Override
            public String typeSetColor() {return null;}

            @Override
            public void beginStage(int currentPlayerIndex, ArrayList<String> ids) {}

            @Override
            public void playStage(GameBoard gameBoard, CardChecker cardChecker, HandCard playableCards) {}

            @Override
            public String getCardToPlay() {return null;}
        };
        deal.setiTerminal(ter);
        Status var = new Status(4);
        var.setSkip(true);
        HandCard toTest1 = new HandCard();
        toTest1.addCard("red 1");
        toTest1.addCard("yellow 3");
        toTest1.addCard("blue reverse");
        CardChecker checker = new CardChecker();
        checker.setLastCard("green skip");
        deal.operationWhenNoPlayableCard(var, toTest1, checker);
        Status var1 = new Status(4);
        var1.setPlus(2);
        HandCard toTest2 = new HandCard();
        toTest2.addCard("red 1");
        toTest2.addCard("yellow 3");
        toTest2.addCard("blue 2");
        CardChecker checker1 = new CardChecker();
        checker.setLastCard("green skip");
        deal.operationWhenNoPlayableCard(var1, toTest2, checker1);
        assertEquals(3, toTest1.getSize());
        assertEquals(5, toTest2.getSize());
    }

    @Test
    public void testPunishOrPlayCard(){
        Deck table = new Deck();
        ArrayList<String> temp = new ArrayList<>();
        temp.add("blue reverse");
        table.setUnused(temp);
        ArrayList<String> temp1 = table.getUnusedCardDeck();
        String tempCard = temp1.get(0);
        Dealer deal = new Dealer(table);
        ITerminal ter = new ITerminal() {
            @Override
            public void printString(String message) {}

            @Override
            public void drawCardNotification(String drawn, boolean noCard) {}

            @Override
            public String typeSetColor() {return null;}

            @Override
            public void beginStage(int currentPlayerIndex, ArrayList<String> ids) {}

            @Override
            public void playStage(GameBoard gameBoard, CardChecker cardChecker, HandCard playableCards) {}

            @Override
            public String getCardToPlay() {return null;}
        };
        deal.setiTerminal(ter);
        HandCard toTest1 = new HandCard();
        toTest1.addCard("red 1");
        toTest1.addCard("yellow 3");
        toTest1.addCard("green +2");
        String card1 = deal.punishOrPlayCard(null);
        String card2 = deal.punishOrPlayCard("green 3");
        String card3 = deal.punishOrPlayCard("white -1");
        String card4 = deal.punishOrPlayCard("quit");
        assertEquals(tempCard, card1);
        assertNull(card2);
        assertNull(card3);
        assertNull(card4);
    }

    @Test
    public void testCheckLastCard(){
        Deck table = new Deck();
        Dealer deal = new Dealer(table);
        ITerminal ter = new ITerminal() {
            @Override
            public void printString(String message) {}

            @Override
            public void drawCardNotification(String drawn, boolean noCard) {}

            @Override
            public String typeSetColor() {return null;}

            @Override
            public void beginStage(int currentPlayerIndex, ArrayList<String> ids) {}

            @Override
            public void playStage(GameBoard gameBoard, CardChecker cardChecker, HandCard playableCards) {}

            @Override
            public String getCardToPlay() {return null;}
        };
        deal.setiTerminal(ter);
        Status var = new Status(4);
        HandCard toTest1 = new HandCard();
        toTest1.addCard("red 1");
        toTest1.addCard("yellow 3");
        toTest1.addCard("blue skip");
        CardChecker checker = new CardChecker();
        deal.checkLastCard(null, toTest1, var, checker);
        Status var1 = new Status(4);
        HandCard toTest2 = new HandCard();
        toTest2.addCard("red 1");
        toTest2.addCard("green 3");
        toTest2.addCard("blue skip");
        CardChecker checker1 = new CardChecker();
        deal.checkLastCard("green +2", toTest2, var1, checker1);
        Status var2 = new Status(4);
        HandCard toTest3 = new HandCard();
        toTest3.addCard("red 1");
        toTest3.addCard("green 3");
        toTest3.addCard("blue skip");
        CardChecker checker2 = new CardChecker();
        deal.checkLastCard("black +4", toTest3, var2, checker2);
        assertEquals(3, toTest1.getSize());
        assertEquals(2, var1.getPlus());
        assertEquals(4, var2.getPlus());
    }

    @Test
    public void TestGetDeck(){
        Deck table = new Deck();
        Dealer deal = new Dealer(table);
        assertEquals(table, deal.getDeck());
    }
}

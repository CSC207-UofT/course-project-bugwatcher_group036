package UseCase;

import Controller.ITerminal;
import Entity.HandCard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class CardCheckerTest {

    @Test
    public void testInitialization1(){
        CardChecker checker = new CardChecker();
        assertNull(checker.getLastCard());
    }

    @Test
    public void testInitialization2(){
        CardChecker checker = new CardChecker();
        assertNull(checker.getCurrentColor());
    }

    @Test
    public void testFunctionCardResponse(){
        CardChecker checker1 = new CardChecker();
        CardChecker checker2 = new CardChecker();
        CardChecker checker3 = new CardChecker();
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
        checker1.functionCardResponse("+4", ter);
        checker2.functionCardResponse("switch", ter);
        checker3.setLastCard("red +2");
        checker3.functionCardResponse("+2", ter);
        assertNull(checker1.getCurrentColor());
        assertNull(checker2.getCurrentColor());
        assertEquals("red", checker3.getCurrentColor());
    }
    @Test
    public void TestSkipsPlayerCanPlay(){
        HandCard toTest1 = new HandCard();
        toTest1.addCard("red 1");
        toTest1.addCard("yellow 3");
        toTest1.addCard("blue +2");
        CardChecker checker1 = new CardChecker();
        HandCard res1 = checker1.skipsPlayerCanPlay(toTest1);
        HandCard toTest2 = new HandCard();
        toTest2.addCard("red skip");
        toTest2.addCard("yellow skip");
        toTest2.addCard("black +4");
        CardChecker checker2 = new CardChecker();
        HandCard res2 = checker2.skipsPlayerCanPlay(toTest2);
        assertEquals("{}", res1.toString());
        assertEquals("{red skip, yellow skip}", res2.toString());
    }

    @Test
    public void TestPlusTwoPlayerCanPlay(){
        HandCard toTest1 = new HandCard();
        toTest1.addCard("red 1");
        toTest1.addCard("yellow 3");
        toTest1.addCard("blue skip");
        CardChecker checker1 = new CardChecker();
        HandCard res1 = checker1.plusTwoPlayerCanPlay(toTest1);
        HandCard toTest2 = new HandCard();
        toTest2.addCard("red +2");
        toTest2.addCard("yellow +2");
        toTest2.addCard("blue reverse");
        CardChecker checker2 = new CardChecker();
        HandCard res2 = checker2.plusTwoPlayerCanPlay(toTest2);
        HandCard toTest3 = new HandCard();
        toTest3.addCard("green +2");
        toTest3.addCard("black switch");
        toTest3.addCard("black +4");
        CardChecker checker3 = new CardChecker();
        HandCard res3 = checker3.plusTwoPlayerCanPlay(toTest3);
        assertEquals("{}", res1.toString());
        assertEquals("{red +2, yellow +2}", res2.toString());
        assertEquals("{green +2, black +4}", res3.toString());
    }

    @Test
    public void TestPlusFourPlayerCanPlay(){
        HandCard toTest1 = new HandCard();
        toTest1.addCard("red 1");
        toTest1.addCard("yellow 3");
        toTest1.addCard("blue skip");
        CardChecker checker1 = new CardChecker();
        HandCard res1 = checker1.plusFourPlayerCanPlay(toTest1);
        HandCard toTest2 = new HandCard();
        toTest2.addCard("red +2");
        toTest2.addCard("yellow +2");
        toTest2.addCard("blue reverse");
        CardChecker checker2 = new CardChecker();
        HandCard res2 = checker2.plusFourPlayerCanPlay(toTest2);
        HandCard toTest3 = new HandCard();
        toTest3.addCard("green +2");
        toTest3.addCard("black switch");
        toTest3.addCard("black +4");
        CardChecker checker3 = new CardChecker();
        HandCard res3 = checker3.plusFourPlayerCanPlay(toTest3);
        assertEquals("{}", res1.toString());
        assertEquals("{}", res2.toString());
        assertEquals("{black +4}", res3.toString());
    }

    @Test
    public void TestCardsPlayerCanPlay(){
        HandCard toTest1 = new HandCard();
        toTest1.addCard("red 1");
        toTest1.addCard("yellow 3");
        toTest1.addCard("blue skip");
        CardChecker checker1 = new CardChecker();
        HandCard res1 = checker1.cardsPlayerCanPlay(toTest1);
        HandCard toTest2 = new HandCard();
        toTest2.addCard("green 0");
        toTest2.addCard("yellow reverse");
        toTest2.addCard("red skip");
        CardChecker checker2 = new CardChecker();
        checker2.setLastCard("yellow 0");
        HandCard res2 = checker2.cardsPlayerCanPlay(toTest2);
        HandCard toTest3 = new HandCard();
        toTest3.addCard("green 0");
        toTest3.addCard("yellow reverse");
        toTest3.addCard("red skip");
        CardChecker checker3 = new CardChecker();
        checker3.setLastCard("black switch");
        HandCard res3 = checker3.cardsPlayerCanPlay(toTest3);
        HandCard toTest4 = new HandCard();
        toTest4.addCard("green 0");
        toTest4.addCard("black switch");
        toTest4.addCard("black +4");
        CardChecker checker4 = new CardChecker();
        checker4.setLastCard("black +4");
        HandCard res4 = checker4.cardsPlayerCanPlay(toTest4);
        assertEquals("{red 1, yellow 3, blue skip}", res1.toString());
        assertEquals("{green 0, yellow reverse}", res2.toString());
        assertEquals("{black switch, black +4}", res4.toString());
    }

    @Test
    public void TestSingleCompare1(){
        CardChecker checker1 = new CardChecker();
        checker1.setLastCard("green 1");
        assertTrue(checker1.singleCompare("green 0"));
        assertTrue(checker1.singleCompare("yellow 1"));
        assertTrue(checker1.singleCompare("green 1"));
        assertTrue(checker1.singleCompare("green +2"));
        assertTrue(checker1.singleCompare("green skip"));
        assertTrue(checker1.singleCompare("green reverse"));
        assertFalse(checker1.singleCompare("black +4"));
        assertFalse(checker1.singleCompare("black switch"));
        assertFalse(checker1.singleCompare("yellow 0"));
        assertFalse(checker1.singleCompare("yellow +2"));
    }

    @Test
    public void TestSingleCompare2(){
        CardChecker checker2 = new CardChecker();
        checker2.setLastCard("black switch");
        checker2.setCurrentColor("red");
        assertTrue(checker2.singleCompare("red 0"));
        assertTrue(checker2.singleCompare("red +2"));
        assertTrue(checker2.singleCompare("red skip"));
        assertTrue(checker2.singleCompare("red reverse"));
        assertTrue(checker2.singleCompare("black switch"));
        assertTrue(checker2.singleCompare("black +4"));
        assertFalse(checker2.singleCompare("green 0"));
        assertFalse(checker2.singleCompare("yellow 9"));
        assertFalse(checker2.singleCompare("blue +2"));
    }

    @Test
    public void TestGetLastCard(){
        CardChecker checker1 = new CardChecker();
        checker1.setLastCard("green 0");
        CardChecker checker2 = new CardChecker();
        checker2.setLastCard("black +4");
        assertEquals("green 0", checker1.getLastCard());
        assertEquals("black +4", checker2.getLastCard());
    }

    @Test
    public void TestGetCurrentColor(){
        CardChecker checker1 = new CardChecker();
        checker1.setLastCard("green 0");
        CardChecker checker2 = new CardChecker();
        checker2.setLastCard("black +4");
        assertEquals("green", checker1.getCurrentColor());
        assertEquals("black", checker2.getCurrentColor());
    }

}

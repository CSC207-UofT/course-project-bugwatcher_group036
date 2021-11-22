package Entity;

import UseCase.CardChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardCheckerTest {

    @Test
    public void testInitialization(){
        CardChecker checker = new CardChecker();
        assertNull(checker.getLastCard());
    }

    @Test
    public void testSkip(){
        CardChecker checker = new CardChecker();
        checker.setLastCard("red skip");
        HandCard toTest = new HandCard();
        toTest.addCard("red skip");
        toTest.addCard("?> skip");
        toTest.addCard("ski p");
        HandCard res = checker.skipsPlayerCanPlay(toTest);
        assertEquals("{red skip, ?> skip}", res.toString());
    }

    @Test
    public void testPlusTwo(){
        CardChecker checker = new CardChecker();
        checker.setLastCard("red +2");
        HandCard toTest = new HandCard();
        toTest.addCard("red +2");
        toTest.addCard("?> +4");
        toTest.addCard("red plustwo");
        HandCard res = checker.plusTwoPlayerCanPlay(toTest);
        assertEquals("{red +2, ?> +4}", res.toString());
    }

    @Test
    public void testPlusFour(){
        CardChecker checker = new CardChecker();
        checker.setLastCard("black +4");
        HandCard toTest = new HandCard();
        toTest.addCard("red +2");
        toTest.addCard("?> +4");
        toTest.addCard("red plustwo");
        HandCard res = checker.plusFourPlayerCanPlay(toTest);
        assertEquals("{?> +4}", res.toString());
    }

    @Test
    public void testNormalCanPlay(){
        CardChecker checker = new CardChecker();
        checker.setLastCard("red 0");
        HandCard toTest = new HandCard();
        toTest.addCard("red +2");
        toTest.addCard("?> +4");
        toTest.addCard("red plustwo");
        toTest.addCard("yellow 0");
        toTest.addCard("test test");
        HandCard res = checker.cardsPlayerCanPlay(toTest);
        assertEquals("{red +2, ?> +4, red plustwo, yellow 0}", res.toString());
    }

    @Test
    public void testNormalCanPlayNull(){
        CardChecker checker = new CardChecker();
        HandCard toTest = new HandCard();
        toTest.addCard("red +2");
        toTest.addCard("?> +4");
        toTest.addCard("red plustwo");
        toTest.addCard("yellow 0");
        toTest.addCard("test test");
        HandCard res = checker.cardsPlayerCanPlay(toTest);
        assertEquals("{red +2, ?> +4, red plustwo, yellow 0, test test}",
                res.toString());
    }
}

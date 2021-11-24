package Entity;

import UseCase.CardChecker;
import UseCase.GameCardHolders;
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
        CardHolder toTest = new CardHolder();
        toTest.addCard("red skip");
        toTest.addCard("?> skip");
        toTest.addCard("ski p");
        CardHolder res = checker.skipsPlayerCanPlay(toTest, new GameCardHolders(4));
        assertEquals("{red skip, ?> skip}", res.toString());
    }

    @Test
    public void testPlusTwo(){
        CardChecker checker = new CardChecker();
        checker.setLastCard("red +2");
        CardHolder toTest = new CardHolder();
        toTest.addCard("red +2");
        toTest.addCard("?> +4");
        toTest.addCard("red plustwo");
        CardHolder res = checker.plusTwoPlayerCanPlay(toTest, new GameCardHolders(2));
        assertEquals("{red +2, ?> +4}", res.toString());
    }

    @Test
    public void testPlusFour(){
        CardChecker checker = new CardChecker();
        checker.setLastCard("black +4");
        CardHolder toTest = new CardHolder();
        toTest.addCard("red +2");
        toTest.addCard("?> +4");
        toTest.addCard("red plustwo");
        CardHolder res = checker.plusFourPlayerCanPlay(toTest, new GameCardHolders(2));
        assertEquals("{?> +4}", res.toString());
    }

    @Test
    public void testNormalCanPlay(){
        CardChecker checker = new CardChecker();
        checker.setLastCard("red 0");
        CardHolder toTest = new CardHolder();
        toTest.addCard("red +2");
        toTest.addCard("?> +4");
        toTest.addCard("red plustwo");
        toTest.addCard("yellow 0");
        toTest.addCard("test test");
        CardHolder res = checker.cardsPlayerCanPlay(toTest, new GameCardHolders(2));
        assertEquals("{red +2, ?> +4, red plustwo, yellow 0}", res.toString());
    }

    @Test
    public void testNormalCanPlayNull(){
        CardChecker checker = new CardChecker();
        CardHolder toTest = new CardHolder();
        toTest.addCard("red +2");
        toTest.addCard("?> +4");
        toTest.addCard("red plustwo");
        toTest.addCard("yellow 0");
        toTest.addCard("test test");
        CardHolder res = checker.cardsPlayerCanPlay(toTest,new GameCardHolders(2));
        assertEquals("{red +2, ?> +4, red plustwo, yellow 0, test test}",
                res.toString());
    }
}

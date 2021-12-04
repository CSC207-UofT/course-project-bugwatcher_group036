package UseCase;

import Controller.Presenter;
import Entity.CardHolder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CardCheckerTest {

    @Test
    public void testInitialization(){
        CardChecker checker = new CardChecker();
        assertNull(checker.getLastCard());
    }
}

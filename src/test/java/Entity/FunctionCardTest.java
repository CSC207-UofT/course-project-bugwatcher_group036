package Entity;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionCardTest {

    @Test
    public void TestgetFunction() {
        Player p1 = new Player("player1", 1);
        FunctionCard c1 = new FunctionCard("yellow", "id", "switch");
        assertEquals("switch", c1.getFunction());
    }
}

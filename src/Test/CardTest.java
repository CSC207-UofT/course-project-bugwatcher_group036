import Entity.Card;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class CardTest {
    Card c1 = new Card("red", 7, null);;
    Card c2 = new Card("black", -1, "+4");
//    Card c1;
//    Card c2;

    @Before
    public void setUp() throws Exception {
        Card c1 = new Card("red", 7, null);
        Card c2 = new Card("black", -1, "+4");
    }

    @Test
    public void TestGetColor() {
        assertEquals("red", c1.getColor());
        assertEquals("black", c2.getColor());
    }

    @Test
    public void TestGetNumber(){
        assertEquals(7, c1.getNumber());
        assertEquals(null, c2.getNumber());
    }


}
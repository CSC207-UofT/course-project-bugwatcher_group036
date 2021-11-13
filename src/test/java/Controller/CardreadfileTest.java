package Controller;
import Entity.Card;
import Entity.NumberCard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CardreadfileTest {

    @Test
    public void testReadFromFile() {
        Readfile readfile = new Cardreadfile();
        DeckManagerData deckManagerData = new DeckManagerData();
        ArrayList<String> colors = readfile.readFromFile("src/main/resources/numberCardsForTest1",
                "src/main/resources/functionCardsForTest1", deckManagerData);

        assertEquals(5, colors.size());
        assertTrue(colors.contains("red"));
        assertTrue(colors.contains("green"));
        assertTrue(colors.contains("blue"));
        assertTrue(colors.contains("yellow"));
        assertTrue(colors.contains("black"));
        ArrayList<Card> newCards = deckManagerData.getDeckManager().getD().getUnusedCardDeck();
        assertEquals("red0", newCards.get(0).getId());
        assertEquals("red1", newCards.get(1).getId());
        assertEquals("green0", newCards.get(2).getId());
        assertEquals("blue1", newCards.get(3).getId());
        assertEquals("yellow3", newCards.get(4).getId());
        assertEquals("yellow4", newCards.get(5).getId());

        assertEquals("green skip", newCards.get(6).getId());
        assertEquals("green skip", newCards.get(7).getId());
        assertEquals("yellow skip", newCards.get(8).getId());
        assertEquals("red plustwo", newCards.get(9).getId());
        assertEquals("green plustwo", newCards.get(10).getId());
        assertEquals("black switch", newCards.get(11).getId());
        assertEquals("black switch", newCards.get(12).getId());
        assertEquals("black plusfour", newCards.get(13).getId());
        assertEquals("yellow reverse", newCards.get(14).getId());
        assertEquals("blue reverse", newCards.get(15).getId());


    }
}

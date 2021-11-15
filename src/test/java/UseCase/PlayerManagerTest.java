package UseCase;

import Entity.Card;
import Entity.FunctionCard;
import Entity.NumberCard;
import Entity.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerManagerTest {

    @Test
    public void testGetPlayers(){
        PlayerManager pManager = new PlayerManager(4, new Card());
        Player[] p = pManager.getPlayers();
        assertEquals(4, p.length);
    }

    @Test
    public void testGetLastCard(){
        PlayerManager pManager = new PlayerManager(4, new Card());
        Card c1 = new FunctionCard("red", "skip", "1");
        for (int i = 0; i < 4; i++){
            pManager.createPlayer(Integer.toString(i), i);
        }

        pManager.playerDrawCard(0, c1);
        pManager.playerPlayCard(0, c1);
        Card expected = pManager.getLastCard();
        assertSame(expected, c1);
    }

    @Test
    public void testCreatePlayer(){
        PlayerManager pManager = new PlayerManager(4, new Card());
        for (int i = 0; i < 4; i++) {
            pManager.createPlayer(Integer.toString(i), i);
        }
        assertEquals("3", pManager.getPlayers()[3].getId());
    }

    @Test
    public void testPlayerDrawCard(){
        PlayerManager pManager = new PlayerManager(4, new Card());
        Card c1 = new FunctionCard("red", "skip", "1");
        for (int i = 0; i < 4; i++){
            pManager.createPlayer(Integer.toString(i), i);
        }
        pManager.playerDrawCard(0, c1);
        assertSame(pManager.getPlayers()[0].getHandCard().get(0), c1);
    }

    @Test
    public void testPlayerPlayCard(){
        PlayerManager pManager = new PlayerManager(4, new Card());
        for (int i = 0; i < 4; i++){
            pManager.createPlayer(Integer.toString(i), i);
        }
        Card toPlayed = new Card("red", "red6");
        pManager.playerDrawCard(0, toPlayed);
        Card played = pManager.playerPlayCard(0, toPlayed);
        assertEquals(played, toPlayed);
    }

    @Test
    public void testWinOrNot(){
        PlayerManager pManager = new PlayerManager(4, new Card());
        Card toDraw = new Card();
        for (int i = 0; i < 4; i++){
            pManager.createPlayer(Integer.toString(i), i);
            toDraw = new NumberCard("red", i, Integer.toString(i));
            pManager.playerDrawCard(i, toDraw);
        }
        assertFalse(pManager.winOrNot());
        pManager.playerPlayCard(3, toDraw);
        assertFalse(pManager.winOrNot(0));
        assertTrue(pManager.winOrNot(3));
    }
}
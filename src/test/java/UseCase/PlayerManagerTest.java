package UseCase;

import Entity.Card;
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
    public void testCreatePlayer(){
        PlayerManager pManager = new PlayerManager(4, new Card());
        for (int i = 0; i < 4; i++){
            pManager.createPlayer(Integer.toString(i), i);
        }
        assertEquals("3", pManager.getPlayers()[3].getId());
    }

    @Test
    public void testPlayerDrawCard(){
        PlayerManager pManager = new PlayerManager(4, new Card());
        for (int i = 0; i < 4; i++){
            pManager.createPlayer(Integer.toString(i), i);
        }
        pManager.playerDrawCard(0, new Card("red", "red6"));
        assertEquals(pManager.getPlayers()[0].getHandCard().get(0).getId(), "red6");
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
}

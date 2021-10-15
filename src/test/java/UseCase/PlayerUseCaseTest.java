package UseCase;

import Entity.Card;
import Entity.Player;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerUseCaseTest {

    @Test
    public void testgetPlayers(){
        PlayerUseCase pManager = new PlayerUseCase(4);
        Player[] p = pManager.getPlayers();
        assertEquals(4, p.length);
    }

    @Test
    public void testCreatePlayer(){
        PlayerUseCase pManager = new PlayerUseCase(4);
        for (int i = 0; i < 4; i++){
            pManager.createPlayer(Integer.toString(i), i);
        }
        assertEquals("3", pManager.getPlayers()[3].getId());
    }

    @Test
    public void testPlayerDrawCard(){
        PlayerUseCase pManager = new PlayerUseCase(4);
        for (int i = 0; i < 4; i++){
            pManager.createPlayer(Integer.toString(i), i);
        }
        pManager.playerDrawCard(0, new Card("red", "red6"));
        assertEquals(pManager.getPlayers()[0].getHandCard().get(0).getId(), "red6");
    }

    @Test
    public void testPlayerPlayCard(){
        PlayerUseCase pManager = new PlayerUseCase(4);
        for (int i = 0; i < 4; i++){
            pManager.createPlayer(Integer.toString(i), i);
        }
        Card toPlayed = new Card("red", "red6");
        pManager.playerDrawCard(0, toPlayed);
        Card played = pManager.playerPlayCard(0, toPlayed);
        assertEquals(played, toPlayed);
    }
}

package UseCase;

import Controller.Gateway;
import Entity.CardHolder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameResponseTest {

    GameBoard gameBoard = new GameBoard(2, new Gateway());
    CardHolder cardHolder = new CardHolder();
    ArrayList<String> ids = new ArrayList<>();

    @Test
    public void testInitialization(){
        GameResponse gameResponse = new GameResponse();
        assertNull(gameResponse.getIds());
        assertNull(gameResponse.getCardHolder());
    }

    @Test
    public void testSetIds(){
        GameResponse gameResponse = new GameResponse();
        gameResponse.setIds(ids);
        assertNotNull(gameResponse.getIds());
    }

    @Test
    public void testGetIds(){
        GameResponse gameResponse = new GameResponse();
        gameResponse.setIds(ids);
        assertNotNull(gameResponse.getIds());
    }

    @Test
    public void testGetCardHolder(){
        GameResponse gameResponse = new GameResponse();
        gameResponse.setCardHolder(cardHolder);
        assertNotNull(gameResponse.getCardHolder());
    }

    @Test
    public void testSetCardHolders(){
        GameResponse gameResponse = new GameResponse();
        gameResponse.setCardHolder(cardHolder);
        assertNotNull(gameResponse.getCardHolder());
    }

}

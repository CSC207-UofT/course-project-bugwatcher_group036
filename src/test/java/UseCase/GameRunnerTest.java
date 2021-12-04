package UseCase;

import Controller.Presenter;
import Entity.CardHolder;
import LogIn.LogInEntity.User;
import LogIn.LogInEntity.UserList;
import LogIn.LogInEntity.UserStatistics;
import LogIn.LoginUseCase.LoginUseCase;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameRunnerTest {
    GameRequest gameRequest = new GameRequest();
    ArrayList<String> IDS = new ArrayList<>();
    GameRunner gameRunner;
    GameResponse gameResponse;
    IPresenter iPresenter;
    EachRound eachRound;


    @BeforeEach
    public void generateIds() {
        for (int i = 0; i < 2; i++) {
            IDS.add("Computer " + i);
        }
        gameRunner = new GameRunner(gameRequest, IDS);
        gameResponse = gameRunner.getGameResponse();
        iPresenter = new Presenter();
        gameRunner.buildIEachRound(gameResponse.getGameBoard(), iPresenter, gameRequest);
        eachRound = gameRunner.getEachRound();
        iPresenter.setGameRunner(gameRunner);
        iPresenter.setGameRequest(gameRequest);
    }


    @Test
    public void testgetGameResponse() {
        assertEquals(gameResponse, gameRunner.getGameResponse());

    }
    @Test
    public void testsetGameRequest() {
        GameRequest gameRequest1 = new GameRequest();
        gameRunner.setGameRequest(gameRequest1);
        assertEquals(gameRequest1, gameRunner.getGameRequest());

    }
    @Test
    public void testgetGameRequest() {
        assertEquals(gameRequest, gameRunner.getGameRequest());
        GameRequest gameRequest1 = new GameRequest();
        gameRunner.setGameRequest(gameRequest1);
        assertEquals(gameRequest1, gameRunner.getGameRequest());

    }

    @Test
    public void testgetEachRound() {
        assertEquals(eachRound, gameRunner.getEachRound());
        gameRunner.buildIEachRound(gameResponse.getGameBoard(), iPresenter, gameRequest);
        EachRound eachRound1 = gameRunner.getEachRound();
        assertEquals(eachRound1, gameRunner.getEachRound());

    }
    @Test
    public void testbuildIEachRound() {
        gameRunner.buildIEachRound(gameResponse.getGameBoard(), iPresenter, gameRequest);
        EachRound eachRound1 = gameRunner.getEachRound();
        assertEquals(eachRound1, gameRunner.getEachRound());

    }

    @Test
    public void testrunGameforGUI() {


        UserList users = new UserList();
        users.add(new User("a", "a"));
        UserStatistics stats = new UserStatistics("a");
        CardHolder playableCards = eachRound.beginStage();
        int currentPlayerIndex = eachRound.getGameBoard().getStatus().getCurrentPlayerIndex();
        if(!eachRound.getGameBoard().getGameCardHolders().isEmpty(playableCards)){
        int before = playableCards.getSize();
        gameResponse.setCardHolder(playableCards);
        String toPlay = playableCards.playCardWithIndex(2);
        playableCards.addCard(toPlay);
        gameRunner.runGameforGUI(toPlay, stats);
        int played = playableCards.getSize();
        assertEquals(before, played + 1);}

    }
    @Test
    public void testrunGameforGUIComputer() {
        gameRunner.buildIEachRound(gameResponse.getGameBoard(), iPresenter, gameRequest);
        CardHolder playableCards = eachRound.beginStage();
        int before = playableCards.getSize();
        gameRunner.runGameforGUIComputer();
        int played = playableCards.getSize();
        assertEquals(before, played+1);



    }

}

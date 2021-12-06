package Controller;

import LogIn.LogInEntity.User;
import LogIn.LogInEntity.UserList;
import LogIn.LogInEntity.UserStatistics;
import UseCase.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PresenterTest {
    GameRequest gameRequest = new GameRequest();
    GameRunner gameRunner;
    Presenter iPresenter = new Presenter();
    Controller controller;

    @BeforeEach
    public void generateIds() {
        ArrayList<String> IDS = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            IDS.add("Computer " + i);
        }
        controller = new Controller(iPresenter, IDS);
        iPresenter.setController(controller);
        gameRunner = new GameRunner(gameRequest, IDS);
    }
    @Test
    public void testRemainingCards(){
        UserList users = new UserList();
        users.add(new User("a", "a"));
        UserStatistics stats = new UserStatistics("a");
        assertEquals("94", iPresenter.RemainingCards());
    }

    @Test
    public void testdrawManyCard(){
        iPresenter.drawManyCard(1, new StringBuilder("black +4"), true);
        iPresenter.drawManyCard(2, new StringBuilder("black +4, green 3"), true);
    }

    @Test
    public void testlastcard(){
        iPresenter.lastcard("green 2");
        iPresenter.lastcard("black +4");
    }
    @Test
    public void testallhandcards(){
        assertEquals(iPresenter.allhandcards(), controller.getGameRunner().getEachRound().getGameBoard().getGameCardHolders().
                        getHandCards(controller.getGameRunner().getEachRound()
                                .getGameBoard().getGameStatus().getCurrentPlayerIndex()));

        assertEquals(iPresenter.allhandcards(1), controller.getGameRunner().getEachRound().getGameBoard().getGameCardHolders().
                        getHandCards(1));
        assertEquals(iPresenter.allhandcards(0), controller.getGameRunner().getEachRound().getGameBoard().getGameCardHolders().
                getHandCards(0));
    }

}

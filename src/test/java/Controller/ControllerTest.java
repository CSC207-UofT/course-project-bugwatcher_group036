package Controller;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    @Test
    public void testInitialization() {
        Presenter presenter = new Presenter();
        ArrayList<String> ids = new ArrayList<>();
        ids.add("a");
        Controller controller = new Controller(presenter, ids);
        assertNotNull(controller.getGameRunner());
        assertNotNull(controller.getGameRequest());
        assertNotNull(controller.getGameRunner().getEachRound());
        assertNotNull(controller.getGameRunner().getGameResponse());
        assertNotNull(controller.getGameRequest().getIds());
        assertNotNull(presenter.getGameRunner());
        assertNotNull(presenter.getGameRequest());
    }

    @Test
    public void testGetGameRequest() {
        Presenter presenter = new Presenter();
        ArrayList<String> ids = new ArrayList<>();
        ids.add("a");
        Controller controller = new Controller(presenter, ids);
        assertNotNull(controller.getGameRunner());
        assertNotNull(controller.getGameRequest());
    }

    @Test
    public void testGetGameRunner() {
        Presenter presenter = new Presenter();
        ArrayList<String> ids = new ArrayList<>();
        ids.add("a");
        Controller controller = new Controller(presenter, ids);
        assertNotNull(controller.getGameRunner());
        assertNotNull(controller.getGameRequest());
    }


}

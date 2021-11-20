package Entity;

import Controller.Terminal;
import org.junit.jupiter.api.Test;

public class EntityTerminalTest {

    @Test
    public void testOutput(){
        Terminal e = new Terminal();
        e.printString("test");
        e.drawCardNotification("test", false);
        e.drawCardNotification("test", true);
    }
}

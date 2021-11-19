package Entity;

import org.junit.jupiter.api.Test;

public class EntityTerminalTest {

    @Test
    public void testOutput(){
        EntityTerminal e = new EntityTerminal();
        e.printString("test");
        e.drawCardNotification("test", false);
        e.drawCardNotification("test", true);
    }
}

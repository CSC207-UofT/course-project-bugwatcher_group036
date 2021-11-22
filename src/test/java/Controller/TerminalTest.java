package Controller;

import org.junit.jupiter.api.Test;

public class TerminalTest {

    @Test
    public void testOutput(){
        Terminal e = new Terminal();
        e.printString("test");
        e.drawCardNotification("test", false);
        e.drawCardNotification("test", true);
    }
}

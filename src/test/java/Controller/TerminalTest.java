package Controller;

import org.junit.jupiter.api.Test;
import Controller.Terminal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TerminalTest {

    @Test
    public void testTypeSetColor(){
        Terminal terminal = new Terminal();
        String Color = "red";
        assertEquals("red", terminal.typeSetColor());
    }

    @Test
    public void testGetCardToPlay(){
        Terminal terminal = new Terminal();
        assertEquals("card", terminal.getCardToPlay());
    }
}

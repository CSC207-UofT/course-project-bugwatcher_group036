package Controller;

import org.junit.jupiter.api.Test;

public class PresenterTest {

    @Test
    public void testOutput(){
        Presenter e = new Presenter();
        e.printString("test");
        //e.drawCardNotification("test", false);
        //e.drawCardNotification("test", true);
    }
}

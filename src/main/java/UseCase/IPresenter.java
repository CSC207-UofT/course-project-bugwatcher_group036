package UseCase;

import Controller.Controller;
import Entity.CardHolder;

public interface IPresenter {

    void printString(String message);

    void drawCardNotification(String drawn, boolean noCard, boolean computer);

    void setGameRunner(GameRunner gameRunner);

    void setGameRequest(GameRequest gameRequest);

    void setColorForComputer(String color);

    void beginStage();

    void playStage();

    String RemainingCards();

    CardHolder allhandcards();

    void setColorGUI();

    void drawManyCard(int numToDraw, StringBuilder drawnCardName, boolean computer);

///////////////////////////////////////////////
    //Command Line Methods
    void wrongColor();

    void wrongThreeTimes();

    void colorIsSet(String setColor);

    void getCardToPlay();

    void setController(Controller controller);

    void inputIDsForComputer();

    void inputIDs();
}

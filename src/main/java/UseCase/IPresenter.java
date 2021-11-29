package UseCase;

import Controller.Controller;
import UseCase.CardChecker;
import Entity.CardHolder;
import UseCase.GameBoard;

import java.util.ArrayList;

public interface IPresenter {

    void printString(String message);

    void drawCardNotification(String drawn, boolean noCard);

    void setColor();
    void setGameRequest(GameRequest gameRequest);

    void setColorForComputer();

    void beginStage();

    void playStage();

    void getCardToPlay();

    void inputIDsGUI(boolean computer, ArrayList<String> ids);

    String RemainingCards();

    String PlayerID();

    CardHolder allhandcards();

    void setColorGUI();

    void drawManyCard(int numToDraw, String drawnCardName);

    void WinFrame();

    void inputIDsForComputer();

    void inputIDs();

    void setGameResponse(GameResponse gameResponse);

    void setController(Controller controller);


//    void getCardToPlayForComputer();
//
//    String typeSetColorForComputer();
}

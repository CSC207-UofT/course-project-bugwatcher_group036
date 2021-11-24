package UseCase;

import UseCase.CardChecker;
import Entity.CardHolder;
import UseCase.GameBoard;

import java.util.ArrayList;

public interface IPresenter {

    void printString(String message);

    void drawCardNotification(String drawn, boolean noCard);

    void setColor();

    void setColorForComputer();

    void beginStage();

    void playStage();

    void getCardToPlay();

    void inputIDs();

    void inputIDsForComputer();



//    void getCardToPlayForComputer();
//
//    String typeSetColorForComputer();
}

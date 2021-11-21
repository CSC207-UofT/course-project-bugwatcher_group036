package Controller;

import UseCase.CardChecker;
import Entity.HandCard;
import UseCase.GameBoard;

import java.util.ArrayList;

public interface ITerminal {

    void printString(String message);

    void drawCardNotification(String drawn, boolean noCard);

    String typeSetColor();

    String typeSetColorForComputer();

    void beginStage(int currentPlayerIndex, ArrayList<String> ids);

    void playStage(GameBoard gameBoard, CardChecker cardChecker, HandCard playableCards);

    String getCardToPlay();

    void getCardToPlayForComputer();
}

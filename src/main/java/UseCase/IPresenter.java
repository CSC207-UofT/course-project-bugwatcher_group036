package UseCase;

import Entity.CardHolder;

public interface IPresenter {

    void drawCardNotification(String drawn, boolean noCard, boolean computer);

    void setGameRequest(IRequest gameRequest);

    void setColorForComputer(String color);

    void lastcard(String cardname);

    String RemainingCards();

    CardHolder allhandcards();

    void setColorGUI();

    void drawManyCard(int numToDraw, StringBuilder drawnCardName, boolean computer);

}

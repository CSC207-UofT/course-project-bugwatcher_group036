package Controller;

import Entity.CardChecker;
import Entity.HandCard;
import UseCase.GameBoard;
import UseCase.UseCaseTerminal;

/**
 * Useed in controller, dependency injection purpose
 */
public interface IEachRound {

    void cardDeal(int numberOfPlayers);

    HandCard beginStage();

    String playStage(HandCard playableCards, int currentPlayeIndex);

    void endStage(String toPlay);

    String letPlayerPlayCard(HandCard playableCards, int currentPlayerIndex);

    GameBoard getGameBoard();

    UseCaseTerminal getTerminal();

    CardChecker getCardChecker();
}

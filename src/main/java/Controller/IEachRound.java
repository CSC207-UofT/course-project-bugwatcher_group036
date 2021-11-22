package Controller;

import UseCase.CardChecker;
import Entity.HandCard;
import UseCase.GameBoard;

/**
 * Useed in controller, dependency injection purpose
 */
public interface IEachRound {

    void cardDeal(int numberOfPlayers);

    HandCard beginStage();

    String playStage(HandCard playableCards, int currentPlayeIndex);

    String playStageForComputer(HandCard playableCards, int currentPlayeIndex) throws InterruptedException;

    void endStage(String toPlay);

    void endStageForComputer(String toPlay, int currentPlayerIndex) throws InterruptedException;

    String letPlayerPlayCard(HandCard playableCards, int currentPlayerIndex);

    GameBoard getGameBoard();

    ITerminal getTerminal();

    CardChecker getCardChecker();
}

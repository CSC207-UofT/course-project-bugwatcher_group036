package Controller;

import Entity.CardChecker;
import Entity.HandCard;
import UseCase.Dealer;
import UseCase.GameBoard;
import UseCase.UseCaseTerminal;

public interface IEachRound {

    void cardDeal(int numberOfPlayers);

    HandCard beginStage();

    String playStage(HandCard playableCards, int currentPlayeIndex);

    void endStage(String toPlay);

    String letPlayerPlayCard(HandCard playableCards, int currentPlayerIndex);

    GameBoard getGameBoard();

    UseCaseTerminal getTerminal();

    Dealer getDealer();

    CardChecker getCardChecker();
}

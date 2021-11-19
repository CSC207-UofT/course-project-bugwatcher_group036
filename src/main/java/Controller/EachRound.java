package Controller;

import Entity.HandCard;
import Entity.Status;
import Entity.CardChecker;
import UseCase.Dealer;
import UseCase.GameBoard;
import UseCase.UseCaseTerminal;

public class EachRound implements IEachRound {

    private final GameBoard gameBoard;
    private final Dealer dealer;
    private final CardChecker cardChecker;
    private final UseCaseTerminal useCaseTerminal;

    public EachRound(GameBoard gameBoard, Dealer dealer, CardChecker cardChecker){
        this.gameBoard = gameBoard;
        this.dealer = dealer;
        this.cardChecker = cardChecker;
        this.useCaseTerminal = new UseCaseTerminal();
    }

    public void cardDeal(int numberOfPlayers) {
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < numberOfPlayers; j++){
                gameBoard.getHandCards(j).addCard(dealer.drawCard());
            }
        }
    }

    public HandCard beginStage() {
        Status vars = gameBoard.getStatus();
        int currentPlayerIndex = vars.getCurrentPlayerIndex();
        // get the cards we need to check
        HandCard toCheck = gameBoard.getHandCards(currentPlayerIndex);
        if (vars.isSkip()) {
            return cardChecker.skipsPlayerCanPlay(toCheck);
        } else if (vars.getPlus() > 0){
            // if the last card is plus2, player can play plus2 or plus4.
            if (cardChecker.getLastCard().split(" ")[1].equals("+2")) {
                return cardChecker.plusTwoPlayerCanPlay(toCheck);
            } else {
                // if the last card is plus4, player can only play plus4.
                return cardChecker.plusFourPlayerCanPlay(toCheck);
            }
        } else {
            // get the cards that the current player can play normally
            return cardChecker.cardsPlayerCanPlay(
                    gameBoard.getHandCards(currentPlayerIndex));
        }
    }

    public String playStage(HandCard playableCards, int currentPlayeIndex) {
        String cardToPlay = null;
        if (playableCards.isEmpty()) {
            dealer.operationWhenNoPlayableCard(currentPlayeIndex, gameBoard, cardChecker);
        } else {
            // if there's playable card, call play card method
            cardToPlay = letPlayerPlayCard(playableCards, currentPlayeIndex);

            // return null if no punished card, and will pass the if statement
            String probablyDrawnCard = dealer.punishOrPlayCard(cardToPlay);
            if (probablyDrawnCard != null){
                gameBoard.getHandCards(currentPlayeIndex).addCard(probablyDrawnCard);
            }

            // if played card is valid, update last card
            if (cardToPlay != null && !cardToPlay.equals("white -1")){
                cardChecker.setLastCard(cardToPlay);
            }
            if (cardToPlay != null && cardToPlay.equals("quit")) {
                gameBoard.getStatus().setQuit();
            }
        }
        return cardToPlay;
    }

    public void endStage(String toPlay) {
        Status vars = gameBoard.getStatus();

        vars.setSkip(false);

        // last check for played card and update status
        dealer.checkLastCard(toPlay, gameBoard, cardChecker);

        // check whether any player has no hand card, which means that player wins
        if(gameBoard.checkWinState()){
            vars.setWinFlag(true);
        }
        // move to the next player
        vars.setCurrentPlayerIndex(vars.moveToNextPlayer());
    }

    public String letPlayerPlayCard(HandCard playableCards, int currentPlayerIndex) {
        String cardToPlay = null;

        // rightCard indicates whether the play type a right card to play.
        boolean rightCard;

        // The number of times that the player type a wrong card.
        int wrongTimes = 0;

        // Let the player type the card to play. If type a wrong card, type again,
        // with maximum 3 times.
        do {
            String cardToPlayID = useCaseTerminal.getCardToPlay();

            if (cardToPlayID.equals("quit")) {
                cardToPlay = "quit";
                return cardToPlay;
            }
            if (cardToPlayID.equals("draw")) {
                gameBoard.getHandCards(currentPlayerIndex).addCard(dealer.drawCardWithNotification(false));
                cardToPlay = "white -1";
                return cardToPlay;
            }
            if (!gameBoard.getHandCards(currentPlayerIndex).playCard(cardToPlayID)) {
                wrongTimes++;
                rightCard = false;
            } else {
                rightCard = true;
                cardToPlay = cardToPlayID;
            }
        } while (!rightCard && wrongTimes < 3);
        //exit when the player types the right class or wrong time exceed 3
        return cardToPlay;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public UseCaseTerminal getTerminal() {
        return useCaseTerminal;
    }

    public CardChecker getCardChecker() {
        return cardChecker;
    }
}

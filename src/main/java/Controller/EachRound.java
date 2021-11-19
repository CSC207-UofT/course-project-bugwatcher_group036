package Controller;

import Entity.HandCard;
import Entity.Status;
import UseCase.CardChecker;
import UseCase.Dealer;
import UseCase.GameBoard;

public class EachRound implements IEachRound {

    private final GameBoard gameBoard;
    private final Dealer dealer;
    private final CardChecker cardChecker;
    private final ITerminal iTerminal;

    public EachRound(GameBoard gameBoard, Dealer dealer, CardChecker cardChecker){
        this.gameBoard = gameBoard;
        this.dealer = dealer;
        this.cardChecker = cardChecker;
        this.iTerminal = new Terminal();
        dealer.setiTerminal(iTerminal);
    }

    public void cardDeal(int numberOfPlayers) {
        for (int i = 0; i < 1; i++){
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

    public String playStage(HandCard playableCards, int currentPlayerIndex) {
        String cardToPlay = null;
        if (playableCards.isEmpty()) {
            dealer.operationWhenNoPlayableCard(gameBoard.getStatus(),
                    gameBoard.getHandCards(currentPlayerIndex), cardChecker);
        } else {
            // if there's playable card, call play card method
            cardToPlay = letPlayerPlayCard(playableCards, currentPlayerIndex);

            // return null if no punished card, and will pass the if statement
            String probablyDrawnCard = dealer.punishOrPlayCard(cardToPlay);
            if (probablyDrawnCard != null){
                gameBoard.getHandCards(currentPlayerIndex).addCard(probablyDrawnCard);
            }

            // if played card is valid, update last card
            if (cardToPlay != null && !cardToPlay.equals("white -1")){
                cardChecker.setLastCard(cardToPlay);
            }
        }
        return cardToPlay;
    }

    public void endStage(String toPlay) {
        if (toPlay != null && toPlay.equals("quit")) {
            gameBoard.getStatus().setQuit();
            return;
        }
        Status vars = gameBoard.getStatus();

        vars.setSkip(false);

        // last check for played card and update status
        dealer.checkLastCard(toPlay,
                gameBoard.getHandCards(vars.getCurrentPlayerIndex()), vars, cardChecker);

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
            String cardToPlayID = iTerminal.getCardToPlay();

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

    public ITerminal getTerminal() {
        return iTerminal;
    }

    public CardChecker getCardChecker() {
        return cardChecker;
    }
}

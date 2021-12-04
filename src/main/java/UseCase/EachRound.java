package UseCase;

import Entity.CardHolder;
import LogIn.LogInEntity.UserStatistics;

public class EachRound {

    private final GameBoard gameBoard;
    private final IPresenter iPresenter;
    private final GameRequest gameRequest;

    public EachRound(GameBoard gameBoard, IPresenter iPresenter, GameRequest gameRequest){
        this.gameBoard = gameBoard;
        this.iPresenter = iPresenter;
        gameBoard.setiTerminal(iPresenter);
        this.gameRequest = gameRequest;
    }

    // Deal cards to players at the beginning of the game, each player should hold 7 cards after that.
    public void cardDeal(int numberOfPlayers) {
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < numberOfPlayers; j++){
                gameBoard.getGameCardHolders().addCard(gameBoard.drawCard(), j);
            }
        }
    }
    /**
     * Getter method for gameBoard
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }
    /**
     * Getter method for iPresenter
     */

    public IPresenter getTerminal() {
        return iPresenter;
    }
    /**
     * Getter method for gameRequest
     */
    public GameRequest getGameRequest() {return gameRequest;}
    /**
     *
     */
    public CardHolder beginStage() {
        int currentPlayerIndex = gameBoard.getStatus().getCurrentPlayerIndex();
        // get the cards we need to check
        CardHolder toCheck = gameBoard.getGameCardHolders().getHandCards(currentPlayerIndex);
        if (gameBoard.getStatus().isSkip()) {
            return gameBoard.getCardChecker().skipsPlayerCanPlay(toCheck, gameBoard.getGameCardHolders());
        } else if (gameBoard.getStatus().getPlus() > 0){
            // if the last card is plus2, player can play plus2 or plus4.
            if (gameBoard.getCardChecker().getLastCard().split(" ")[1].equals("+2")) {
                return gameBoard.getCardChecker().plusTwoPlayerCanPlay(toCheck, gameBoard.getGameCardHolders());
            } else {
                // if the last card is plus4, player can only play plus4.
                return gameBoard.getCardChecker().plusFourPlayerCanPlay(toCheck, gameBoard.getGameCardHolders());
            }
        } else {
            // get the cards that the current player can play normally
            return gameBoard.getCardChecker().cardsPlayerCanPlay(
                    gameBoard.getGameCardHolders().getHandCards(currentPlayerIndex), gameBoard.getGameCardHolders());
        }
    }

    public void playStageGUI(CardHolder playableCards, int currentPlayerIndex,
                             String cardToPlay, UserStatistics stats) {
        if (gameBoard.getGameCardHolders().isEmpty(playableCards)) {
            gameBoard.operationWhenNoPlayableCard(false, stats);
        } else {
            // if there's playable card, call play card method
            cardToPlay = letPlayerPlayCardGUI(currentPlayerIndex, cardToPlay);

            // return null if no punished card, and will pass the if statement
            String probablyDrawnCard = gameBoard.punishOrPlayCard(cardToPlay, false);
            if (probablyDrawnCard != null){
                gameBoard.getGameCardHolders().addCard(probablyDrawnCard, currentPlayerIndex);
                stats.drawCard(1);
            }

            // if played card is valid, update last card
            if (cardToPlay != null && !cardToPlay.equals("white -1")){
                gameBoard.getCardChecker().setLastCard(cardToPlay);
            }
        }
    }

    public String playStageGUIPVE(CardHolder playableCards, int currentPlayerIndex) {
        String cardToPlay = null;
        if (gameBoard.getGameCardHolders().isEmpty(playableCards)) {
            gameBoard.operationWhenNoPlayableCard(true, new UserStatistics("noSave"));
        } else {
            // if there's playable card, call play card method
            cardToPlay = letPlayerPlayCardForComputer(playableCards, currentPlayerIndex);

            // if played card is valid, update last card
            if (cardToPlay != null && !cardToPlay.equals("white -1")){
                gameBoard.getCardChecker().setLastCard(cardToPlay);
            }
        }
        return cardToPlay;
    }

    public void endStageGUI(String toPlay, UserStatistics stats) {

        gameBoard.getStatus().setSkip(false);

        gameBoard.checkLastCard(toPlay, gameRequest, stats);

        // check whether any player has no hand card, which means that player wins
        if(gameBoard.getGameCardHolders().checkWinState()){
            gameBoard.getStatus().setWinFlag(true);
        }
        // move to the next player
        gameBoard.getStatus().setCurrentPlayerIndex(gameBoard.getStatus().moveToNextPlayer());
    }

    public void endStageGUIPVE(String toPlay, int currentPlayerIndex) {
        if (currentPlayerIndex == 0) {
            endStageGUI(toPlay, new UserStatistics("noSave"));
        } else {

            gameBoard.getStatus().setSkip(false);

            // last check for played card and update status
            gameBoard.checkLastCardForComputer(toPlay, gameRequest);

            // check whether any player has no hand card, which means that player wins
            if(gameBoard.getGameCardHolders().checkWinState()){
                gameBoard.getStatus().setWinFlag(true);
            }
            // move to the next player
            gameBoard.getStatus().setCurrentPlayerIndex(
                    gameBoard.getStatus().moveToNextPlayer());
        }
    }

    public String letPlayerPlayCardGUI(int currentPlayerIndex, String cardToPlayID) {
        String cardToPlay = null;

        // rightCard indicates whether the play type a right card to play.
        boolean rightCard;

        // The number of times that the player type a wrong card.
        int wrongTimes = 0;

        // Let the player type the card to play. If type a wrong card, type again,
        // with maximum 3 times.
        do {
            if (cardToPlayID.equals("draw")) {
                gameBoard.getGameCardHolders().addCard(
                        gameBoard.drawCardWithNotification(false, false), currentPlayerIndex);
                cardToPlay = "white -1";
                return cardToPlay;
            }
            if (!gameBoard.getGameCardHolders().playCard(cardToPlayID, currentPlayerIndex)) {
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

    public String letPlayerPlayCardForComputer(CardHolder playableCards, int currentPlayerIndex) {
        String cardToPlay;

        cardToPlay = gameBoard.getGameCardHolders().playCardWithIndex(0, playableCards);
        gameBoard.getGameCardHolders().playCard(cardToPlay, currentPlayerIndex);

        return cardToPlay;
    }
}




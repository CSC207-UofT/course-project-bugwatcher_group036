package UseCase;

import Entity.CardHolder;
import LogIn.LogInEntity.UserStatistics;

public class EachRound {

    private final GameBoard gameBoard;
    private final IPresenter iPresenter;
    private final GameRequest gameRequest;

    /**
     * Initialize EachRound
     * @param numberOfPlayers the total number of player
     * @param iPresenter Interface for presenter class
     * @param gameRequest Gamerequest of the game
     * @param gateway Gateway interface for reading file
     */
    public EachRound(int numberOfPlayers, IPresenter iPresenter, GameRequest gameRequest, ReadFile gateway){
        this.gameBoard = new GameBoard(numberOfPlayers, gateway);
        this.iPresenter = iPresenter;
        gameBoard.setiTerminal(iPresenter);
        this.gameRequest = gameRequest;
    }

    /**
     * Deal cards to players at the beginning of the game, each player should hold 7 cards after that.
     */
    public void cardDeal(int numberOfPlayers) {
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < numberOfPlayers; j++){
                gameBoard.getGameCardHolders().addCard(gameBoard.drawCard(), j);
            }
        }
    }

    /**
     * Getter method for gameBoard
     * @return the GameBoard for the uno game
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Getter method for iPresenter
     * @return the iPresenter for the uno game
     */
    public IPresenter getTerminal() {
        return iPresenter;
    }

    /**
     * Getter method for gameRequest
     * @return the GameRequest for the uno game
     */
    public GameRequest getGameRequest() {return gameRequest;}

    /**
     * Start the round for current player. If last card is plus, then let the player draw cards or play plus card.
     * @return the cards that current player can play
     */
    public CardHolder beginStage() {
        int currentPlayerIndex = gameBoard.getGameStatus().getCurrentPlayerIndex();
        // get the cards we need to check
        CardHolder toCheck = gameBoard.getGameCardHolders().getHandCards(currentPlayerIndex);
        if (gameBoard.getGameStatus().isSkip()) {
            return gameBoard.getCardChecker().skipsPlayerCanPlay(toCheck, gameBoard.getGameCardHolders());
        } else if (gameBoard.getGameStatus().getPlus() > 0){
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

    /**
     * The playstage when a player played a card that is playable.
     * To check whether the player have any card or not.
     */
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

    /**
     * The playstage for the computer.
     * @param playableCards The cards that the computer player can play
     * @param currentPlayerIndex the index of the current player.
     * @return the card that the computer played
     */
    public String playStageGUIPVE(CardHolder playableCards, int currentPlayerIndex) {
        String cardToPlay = null;
        if (gameBoard.getGameCardHolders().isEmpty(playableCards)) {
            gameBoard.operationWhenNoPlayableCard(true, new UserStatistics("noSave"));
        } else {
            // if there's playable card, call play card method
            cardToPlay = letPlayerPlayCardForComputer(playableCards, currentPlayerIndex);

            String probablyDrawnCard = gameBoard.punishOrPlayCard(cardToPlay, true);
            if (probablyDrawnCard != null){
                gameBoard.getGameCardHolders().addCard(probablyDrawnCard, currentPlayerIndex);
            }

            // if played card is valid, update last card
            if (cardToPlay != null && !cardToPlay.equals("white -1")){
                gameBoard.getCardChecker().setLastCard(cardToPlay);
            }
        }
        return cardToPlay;
    }

    /**
     * The endstage when a player play a card. To set all the game and status to the correct type.
     * @param toPlay The card that played by last player.
     * @param stats The informaiton statistics of the player
     */
    public void endStageGUI(String toPlay, UserStatistics stats) {

        gameBoard.getGameStatus().setSkip(false);

        gameBoard.checkLastCard(toPlay, gameRequest, stats);

        // check whether any player has no hand card, which means that player wins
        if(gameBoard.getGameCardHolders().checkWinState()){
            gameBoard.getGameStatus().setWinFlag(true);
        }
        // move to the next player
        gameBoard.getGameStatus().setCurrentPlayerIndex(gameBoard.getGameStatus().moveToNextPlayer());
    }

    /**
     * The endstage when a computer play a card. To set all the game and status to the correct type.
     * @param toPlay The card that played by Computer.
     * @param currentPlayerIndex The computer player index
     */
    public void endStageGUIPVE(String toPlay, int currentPlayerIndex) {
        if (currentPlayerIndex == 0) {
            endStageGUI(toPlay, new UserStatistics("noSave"));
        } else {

            gameBoard.getGameStatus().setSkip(false);

            // last check for played card and update status
            gameBoard.checkLastCardForComputer(toPlay, gameRequest);

            // check whether any player has no hand card, which means that player wins
            if(gameBoard.getGameCardHolders().checkWinState()){
                gameBoard.getGameStatus().setWinFlag(true);
            }
            // move to the next player
            gameBoard.getGameStatus().setCurrentPlayerIndex(
                    gameBoard.getGameStatus().moveToNextPlayer());
        }
    }

    /**
     * For player to play a card when the card is playable or draw a card.
     * @param currentPlayerIndex the index of the current player
     * @param cardToPlayID the card that the player played or draw a card.
     * @return the card either the player draw or the card that player played.
     */
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

    /**
     * For computer to play a card when there is playable card
     * @param playableCards the cards that the computer can play
     * @param currentPlayerIndex the index of the current player
     * @return the card that the computer play
     */
    public String letPlayerPlayCardForComputer(CardHolder playableCards, int currentPlayerIndex) {
        String cardToPlay;

        cardToPlay = gameBoard.getGameCardHolders().playCardWithIndex(0, playableCards);
        gameBoard.getGameCardHolders().playCard(cardToPlay, currentPlayerIndex);

        return cardToPlay;
    }

    /**
     * Getter method for the current player index.
     * @return the current index of the player
     */
    public int getCurrentPlayerIndex() {
        return getGameBoard().getCurrentPlayerIndex();
    }



}




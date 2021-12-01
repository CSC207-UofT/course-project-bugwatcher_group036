package UseCase;

import Entity.CardHolder;

import static java.lang.Thread.sleep;

public class EachRound {

    private final GameBoard gameBoard;
    private final IPresenter iPresenter;
    private GameRequest gameRequest;

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

    public void playStageGUI(CardHolder playableCards, int currentPlayerIndex, String cardToPlay) {
        if (gameBoard.getGameCardHolders().isEmpty(playableCards)) {
            gameBoard.operationWhenNoPlayableCard();
        } else {
            // if there's playable card, call play card method
            cardToPlay = letPlayerPlayCardGUI(playableCards, currentPlayerIndex, cardToPlay);

            // return null if no punished card, and will pass the if statement
            String probablyDrawnCard = gameBoard.punishOrPlayCard(cardToPlay);
            if (probablyDrawnCard != null){
                gameBoard.getGameCardHolders().addCard(probablyDrawnCard, currentPlayerIndex);
            }

            // if played card is valid, update last card
            if (cardToPlay != null && !cardToPlay.equals("white -1")){
                gameBoard.getCardChecker().setLastCard(cardToPlay);
            }
        }
    }

    public String playStage(CardHolder playableCards, int currentPlayerIndex) {
        String cardToPlay = null;
        if (gameBoard.getGameCardHolders().isEmpty(playableCards)) {
            gameBoard.operationWhenNoPlayableCard();
        } else {
            // if there's playable card, call play card method
            cardToPlay = letPlayerPlayCard(playableCards, currentPlayerIndex);

            // return null if no punished card, and will pass the if statement
            String probablyDrawnCard = gameBoard.punishOrPlayCard(cardToPlay);
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

    public String playStageForComputer(CardHolder playableCards, int currentPlayerIndex) throws InterruptedException {
        if (currentPlayerIndex == 0) {
            return playStage(playableCards, currentPlayerIndex);
        }
        String cardToPlay = null;
        if (gameBoard.getGameCardHolders().isEmpty(playableCards)) {
            gameBoard.operationWhenNoPlayableCard();
            sleep(1300);
        } else {
            // if there's playable card, call play card method
            cardToPlay = letPlayerPlayCardForComputer(playableCards);

            // if played card is valid, update last card
            if (cardToPlay != null && !cardToPlay.equals("white -1")){
                gameBoard.getCardChecker().setLastCard(cardToPlay);
            }
            sleep(1300);
        }
        return cardToPlay;
    }
    public void endStageGUI(String toPlay) {
        if (toPlay != null && toPlay.equals("quit")) {
            gameBoard.getGameStatus().setQuit();
        }

        gameBoard.getGameStatus().setSkip(false);

        // last check for played card and update status
        assert toPlay != null;
        if (!toPlay.equals("draw") && !toPlay.equals("next")){
            gameBoard.checkLastCardGUI(toPlay, gameRequest);}

        // check whether any player has no hand card, which means that player wins
        if(gameBoard.getGameCardHolders().checkWinState()){
            gameBoard.getGameStatus().setWinFlag(true);
        }
        // move to the next player
        gameBoard.getGameStatus().setCurrentPlayerIndex(gameBoard.getGameStatus().moveToNextPlayer());
    }

    public void endStage(String toPlay) {
        if (toPlay != null && toPlay.equals("quit")) {
            gameBoard.getGameStatus().setQuit();
            return;
        }

        gameBoard.getGameStatus().setSkip(false);

        // last check for played card and update status
        gameBoard.checkLastCard(toPlay, gameRequest);

        // check whether any player has no hand card, which means that player wins
        if(gameBoard.getGameCardHolders().checkWinState()){
            gameBoard.getGameStatus().setWinFlag(true);
        }
        // move to the next player
        gameBoard.getGameStatus().setCurrentPlayerIndex(gameBoard.getGameStatus().moveToNextPlayer());
    }

    public void endStageForComputer(String toPlay, int currentPlayerIndex) throws InterruptedException {
        if (currentPlayerIndex == 0) {
            endStage(toPlay);
        } else {

            gameBoard.getGameStatus().setSkip(false);

            // last check for played card and update status
            gameBoard.checkLastCardForComputer(toPlay, gameRequest);

            // check whether any player has no hand card, which means that player wins
            if(gameBoard.getGameCardHolders().checkWinState()){
                gameBoard.getGameStatus().setWinFlag(true);
            }
            sleep(1300);
            // move to the next player
            gameBoard.getGameStatus().setCurrentPlayerIndex(
                    gameBoard.getGameStatus().moveToNextPlayer());
        }

    }

    public String letPlayerPlayCardGUI(CardHolder playableCards, int currentPlayerIndex, String cardToPlayID) {
        String cardToPlay = null;

        // rightCard indicates whether the play type a right card to play.
        boolean rightCard;

        // The number of times that the player type a wrong card.
        int wrongTimes = 0;

        // Let the player type the card to play. If type a wrong card, type again,
        // with maximum 3 times.
        do {
//            iPresenter.getCardToPlay();

            if (cardToPlayID.equals("quit")) {
                cardToPlay = "quit";
                return cardToPlay;
            }
            if (cardToPlayID.equals("draw")) {
                gameBoard.getGameCardHolders().addCard(
                        gameBoard.drawCardWithNotification(false), currentPlayerIndex);
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

    public String letPlayerPlayCard(CardHolder playableCards, int currentPlayerIndex) {
        String cardToPlay = null;

        // rightCard indicates whether the play type a right card to play.
        boolean rightCard;

        // The number of times that the player type a wrong card.
        int wrongTimes = 0;

        // Let the player type the card to play. If type a wrong card, type again,
        // with maximum 3 times.
        do {
            iPresenter.getCardToPlay();
            String cardToPlayID = gameRequest.getGetCardToPlay();

            if (cardToPlayID.equals("quit")) {
                cardToPlay = "quit";
                return cardToPlay;
            }
            if (cardToPlayID.equals("draw")) {
                gameBoard.getGameCardHolders().addCard(
                        gameBoard.drawCardWithNotification(false), currentPlayerIndex);
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

    public String letPlayerPlayCardForComputer(CardHolder playableCards) {
        String cardToPlay = null;

        cardToPlay = gameBoard.getGameCardHolders().playCardWithIndex(0, playableCards);

        return cardToPlay;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public IPresenter getTerminal() {
        return iPresenter;
    }

    public CardChecker getCardChecker() {
        return gameBoard.getCardChecker();
    }

    public GameRequest getGameRequest() { return gameRequest; }
}

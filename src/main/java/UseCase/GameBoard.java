package UseCase;

import Entity.CardHolder;

import LogIn.LogInEntity.UserStatistics;

import java.util.ArrayList;
import java.util.Collections;

public class GameBoard {

    private final GameCardHolders gameCardHolders;
    private final GameStatus gameStatus;
    private final CardChecker cardChecker;
    private final GameDeck gameDeck;
    private IPresenter iPresenter;

    /**
     * initialize GameBoard
     * @param numberOfPlayers number for player
     * @param gateway Gateway interface for reading file
     */
    public GameBoard(int numberOfPlayers, ReadFile gateway) {
        this.gameCardHolders = new GameCardHolders(numberOfPlayers);
        this.gameStatus = new GameStatus(numberOfPlayers);
        this.cardChecker = new CardChecker();
        this.gameDeck = new GameDeck(gateway);
    }

    /**
     * Getter method for cardChecker
     * @return cardChecker of this game
     */
    public CardChecker getCardChecker() {
        return cardChecker;
    }

    /**
     * Getter method for gameCardHolders
     * @return gameCardHolder of this game
     */
    public GameCardHolders getGameCardHolders() {
        return gameCardHolders;
    }

    /**
     * Getter method for Status
     * @return status of the game
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
     * if deck is empty draw card from unused deck
     * @return the card drawn
     */
    public String drawCard() {
        if (gameDeck.isEmpty()) { // Check whether the card deck is empty
            gameDeck.shuffleFromUsedToUnused();
        }
        return gameDeck.drawCardFromUnusedDeck(); // Return the card draw from the unused card deck
    }

    /**
     * notify the card current player draw
     * @param noCard noCard represents whether the card draw is due to no card playable
     * @param computer True for computer player False for not
     */
    public String drawCardWithNotification(boolean noCard, boolean computer) {
        // noCard represents whether the card draw is due to no card playable
        String drawn = drawCard(); // The card draw from the deck
        iPresenter.drawCardNotification(drawn, noCard, computer);
        return drawn;
    }

    /**
     * draw multiple cards for given player and notify
     * @param computer True for computer player False for not
     * @param cardHolder card holders of this game
     */
    public void plusManyNextPlayer(CardHolder cardHolder, boolean computer) {
        // draw multiple cards for given player and notify
        StringBuilder drawnCardName = new StringBuilder();
        int numToDraw = gameStatus.getPlus();
        for (int i = 0; i < numToDraw; i++) {
            String drawnCard = drawCard();
            if (drawnCard != null) {
                gameCardHolders.addCard(drawnCard, cardHolder);
                drawnCardName.append(drawnCard);
            }
            if (i != numToDraw - 1) {
                drawnCardName.append(", ");
            }
        }
        iPresenter.drawManyCard(numToDraw, drawnCardName, computer);
    }

    /**
     * draw card,then if really no card playable, let player draw card, with punish notification
     * @param stats stats of users
     * @param computer  True for computer player False for not
     */
    public void operationWhenNoPlayableCard(boolean computer, UserStatistics stats) {
        CardHolder currentCardHolder = gameCardHolders.getHandCards(gameStatus.getCurrentPlayerIndex());
        if (gameStatus.getPlus() > 0) {
            plusManyNextPlayer(currentCardHolder, computer);
            stats.drawCard(gameStatus.getPlus());
            gameStatus.setPlus(0); // reset plus to zero
        } else if (!cardChecker.getLastCard().split(" ")[1].equals("skip") ||
                (cardChecker.getLastCard().split(" ")[1].equals("skip") && !gameStatus.isSkip())) {
            // if really no card playable, let player draw card, with punish notification
            String drawnCardName = drawCardWithNotification(true, computer);
            stats.drawCard(1);
            gameCardHolders.addCard(drawnCardName, currentCardHolder);
        }
    }

    /**
     * if no card played draw a card with notification
     * @param computer True for computer player False for not
     * @param cardToPlay card player play
     */
    public String punishOrPlayCard(String cardToPlay, boolean computer) {
        if (cardToPlay == null) {
            return drawCardWithNotification(false, computer); // not because no card playable
        } else if (!cardToPlay.equals("white -1") && !cardToPlay.equals("quit")) { // if it is not voluntary draw
            gameDeck.putCardToUsedDeck(cardToPlay);
        }
        return null;
    }

    /**
     * check the last played card
     * @param gameRequest Gamerequest of the game
     * @param stats stats of users
     * @param toPlay the string of the card player wants to play
     */
    public void checkLastCard(String toPlay, GameRequest gameRequest, UserStatistics stats) {
        CardHolder currentCardHolder = gameCardHolders.getHandCards(gameStatus.getCurrentPlayerIndex());
        // create the arrayList for all possible number features
        ArrayList<String> num = new ArrayList<>();
        Collections.addAll(num, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9");

        if (toPlay != null && !toPlay.equals("white -1") && !toPlay.equals("quit") && !toPlay.equals("next")) {
            // if the player has played a card
            String feature = toPlay.split(" ")[1];
            iPresenter.lastcard(toPlay);
            stats.playCard(toPlay);
            if (!num.contains(feature)) { // if function card is played
                if (gameCardHolders.isEmpty(currentCardHolder)) {
                    String punishCard = drawCardWithNotification(false, false);
                    gameCardHolders.addCard(punishCard, currentCardHolder);
                }
                gameStatus.functionCardResponse(feature); // respond according to features
                cardChecker.functionCardResponseGUI(feature, iPresenter, gameRequest); // specific response for color change
            }
        }
    }

    /**
     * check the last played card in pve
     * @param gameRequest  Gamerequest of the game
     * @param toPlay the string of the card player wants to play
     */
    public void checkLastCardForComputer(String toPlay, GameRequest gameRequest) {
        CardHolder currentCardHolder = gameCardHolders.getHandCards(gameStatus.getCurrentPlayerIndex());
        // create the arrayList for all possible number features
        ArrayList<String> num = new ArrayList<>();
        Collections.addAll(num, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9");

        if (toPlay != null && !toPlay.equals("white -1") && !toPlay.equals("quit")) {
            // if the player has played a card
            String feature = toPlay.split(" ")[1];
            iPresenter.lastcard(toPlay);
            if (!num.contains(feature)) { // if function card is played
                if (gameCardHolders.isEmpty(currentCardHolder)) {
                    String punishCard = drawCardWithNotification(false, true);
                    gameCardHolders.addCard(punishCard, currentCardHolder);
                }
                gameStatus.functionCardResponse(feature); // respond according to features
                cardChecker.functionCardResponseForComputer(feature, iPresenter, gameRequest); // specific response for color change
            }
        }
    }

    /**
     * Getter method for deck
     * @return  the deck of this game
     */
    public GameDeck getGameDeck() {
        return this.gameDeck;
    }

    /**
     * setter method for iPresenter
     */
    public void setiTerminal(IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    /**
     * @return the index of the current player
     */
    public int getCurrentPlayerIndex() {
        return gameStatus.getCurrentPlayerIndex();
    }

}


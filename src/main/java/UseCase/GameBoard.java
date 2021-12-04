package UseCase;

import Entity.CardHolder;
import Entity.Deck;
import Entity.Status;
import LogIn.LogInEntity.UserStatistics;

import java.util.ArrayList;
import java.util.Collections;

public class GameBoard {

    private final GameCardHolders gameCardHolders;
    private final Status status;
    private final CardChecker cardChecker;
    private final Deck deck;
    private IPresenter iPresenter;

    public GameBoard(int numberOfPlayers) {
        this.gameCardHolders = new GameCardHolders(numberOfPlayers);
        this.status = new Status(numberOfPlayers);
        this.cardChecker = new CardChecker();
        this.deck = new Deck();
    }

    /**
     * Getter method for cardChecker
     */
    public CardChecker getCardChecker() {
        return cardChecker;
    }
    /**
     * Getter method for gameCardHolders
     */
    public GameCardHolders getGameCardHolders() {
        return gameCardHolders;
    }
    /**
     * Getter method for Status
     */
    public Status getStatus() {
        return status;
    }
    /**
     * if deck is empty draw card from unused deck
     */
    public String drawCard() {
        if (deck.isEmpty()) {
            deck.shuffleFromUsedToUnused();
        }
        return deck.drawCardFromUnusedDeck();
    }
    /**
     * notify the card current player draw
     */
    public String drawCardWithNotification(boolean noCard, boolean computer) {
        // noCard represents whether the card draw is due to no card playable
        String drawn = drawCard();
        iPresenter.drawCardNotification(drawn, noCard, computer);
        return drawn;
    }
    /**
     * draw multiple cards for given player and notify
     */

    public void plusManyNextPlayer(CardHolder cardHolder, boolean computer) {
        // draw multiple cards for given player and notify
        StringBuilder drawnCardName = new StringBuilder();
        int numToDraw = status.getPlus();
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
     */

    public void operationWhenNoPlayableCard(boolean computer, UserStatistics stats) {
        CardHolder currentCardHolder = gameCardHolders.getHandCards(status.getCurrentPlayerIndex());
        if (status.getPlus() > 0) {
            plusManyNextPlayer(currentCardHolder, computer);
            stats.drawCard(status.getPlus());
            status.setPlus(0); // reset plus to zero
        } else if (!cardChecker.getLastCard().split(" ")[1].equals("skip") ||
                (cardChecker.getLastCard().split(" ")[1].equals("skip") && !status.isSkip())) {
            // if really no card playable, let player draw card, with punish notification
            String drawnCardName = drawCardWithNotification(true, computer);
            stats.drawCard(1);
            gameCardHolders.addCard(drawnCardName, currentCardHolder);
        }
    }
    /**
     * if no card played draw a card with notification
     */

    public String punishOrPlayCard(String cardToPlay, boolean computer) {
        if (cardToPlay == null) {
            return drawCardWithNotification(false, computer); // not because no card playable
        } else if (!cardToPlay.equals("white -1") && !cardToPlay.equals("quit")) { // if it is not voluntary draw
            deck.putCardToUsedDeck(cardToPlay);
        }
        return null;
    }
    /**
     * check the last played card
     */

    public void checkLastCard(String toPlay, GameRequest gameRequest, UserStatistics stats) {
        CardHolder currentCardHolder = gameCardHolders.getHandCards(status.getCurrentPlayerIndex());
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
                status.functionCardResponse(feature); // respond according to features
                cardChecker.functionCardResponseGUI(feature, iPresenter, gameRequest); // specific response for color change
            }
        }
    }
    /**
     * check the last played card in pve
     */
    public void checkLastCardForComputer(String toPlay, GameRequest gameRequest) {
        CardHolder currentCardHolder = gameCardHolders.getHandCards(status.getCurrentPlayerIndex());
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
                status.functionCardResponse(feature); // respond according to features
                cardChecker.functionCardResponseForComputer(feature, iPresenter, gameRequest); // specific response for color change
            }
        }
    }
    /**
     * Getter method for deck
     */

    public Deck getDeck() {
        return this.deck;
    }
    /**
     * setter method for iPresenter
     */

    public void setiTerminal(IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

}


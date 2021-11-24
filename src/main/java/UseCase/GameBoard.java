package UseCase;

import Entity.CardHolder;
import Entity.Deck;

import java.util.ArrayList;
import java.util.Collections;

public class GameBoard {

    private GameCardHolders gameCardHolders;
    private GameStatus gameStatus;
    private CardChecker cardChecker;
    private Deck deck;
    private IPresenter iPresenter;

    public GameBoard(int numberOfPlayers) {
        this.gameCardHolders = new GameCardHolders(numberOfPlayers);
        this.gameStatus = new GameStatus(numberOfPlayers);
        this.cardChecker = new CardChecker();
        this.deck = new Deck();
    }

    public CardChecker getCardChecker() {
        return cardChecker;
    }

    public GameCardHolders getGameCardHolders() {
        return gameCardHolders;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public String drawCard(){
        if (deck.isEmpty()){
            deck.shuffleFromUsedToUnused();
        }
        return deck.drawCardFromUnusedDeck();
    }

    public String drawCardWithNotification(boolean noCard) {
        // noCard represents whether the card draw is due to no card playable
        String drawn = drawCard();
        iPresenter.drawCardNotification(drawn, noCard);
        return drawn;
    }

    public void plusManyNextPlayer(CardHolder cardHolder) {
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
        iPresenter.printString("You draw " + numToDraw + " cards. The cards you've drawn are " +
                drawnCardName + ".");
    }

    public void operationWhenNoPlayableCard() {
        CardHolder currentCardHolder = gameCardHolders.getHandCards(gameStatus.getCurrentPlayerIndex());
        if (gameStatus.getPlus() > 0){
            plusManyNextPlayer(currentCardHolder);
            gameStatus.setPlus(0); // reset plus to zero
        } else if (!cardChecker.getLastCard().split(" ")[1].equals("skip") ||
                (cardChecker.getLastCard().split(" ")[1].equals("skip") && !gameStatus.isSkip())) {
            // if really no card playable, let player draw card, with punish notification
            String drawnCardName = drawCardWithNotification(true);
            gameCardHolders.addCard(drawnCardName, currentCardHolder);
        }
    }

    public String punishOrPlayCard(String cardToPlay) {
        if (cardToPlay == null) {
            iPresenter.printString("Enter too many times wrong cards! Draw a card for punishment.");
            return drawCardWithNotification(false); // not because no card playable
        } else if (!cardToPlay.equals("white -1") && !cardToPlay.equals("quit")) { // if it is not voluntary draw
            deck.putCardToUsedDeck(cardToPlay);
        }
        return null;
    }

    public void checkLastCard(String toPlay, GameRequest gameRequest) {
        CardHolder currentCardHolder = gameCardHolders.getHandCards(gameStatus.getCurrentPlayerIndex());
        // create the arrayList for all possible number features
        ArrayList<String> num = new ArrayList<>();
        Collections.addAll(num, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9");

        if (toPlay != null && !toPlay.equals("white -1") && !toPlay.equals("quit")) {
            // if the player has played a card
            String feature = toPlay.split(" ")[1];
            iPresenter.printString("The card " + toPlay + " is played.");
            if (!num.contains(feature)) { // if function card is played
                if (gameCardHolders.isEmpty(currentCardHolder)){
                    iPresenter.printString("You played functioned card for last card, which is invalid.");
                    String punishCard = drawCardWithNotification(false);
                    gameCardHolders.addCard(punishCard, currentCardHolder);
                }
                gameStatus.functionCardResponse(feature); // respond according to features
                cardChecker.functionCardResponse(feature, iPresenter, gameRequest); // specific response for color change
            }
        }
    }

    public void checkLastCardForComputer(String toPlay, GameRequest gameRequest) {
        CardHolder currentCardHolder = gameCardHolders.getHandCards(gameStatus.getCurrentPlayerIndex());
        // create the arrayList for all possible number features
        ArrayList<String> num = new ArrayList<>();
        Collections.addAll(num, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9");

        if (toPlay != null && !toPlay.equals("white -1") && !toPlay.equals("quit")) {
            // if the player has played a card
            String feature = toPlay.split(" ")[1];
            iPresenter.printString("The card " + toPlay + " is played.");
            if (!num.contains(feature)) { // if function card is played
                if (gameCardHolders.isEmpty(currentCardHolder)){
                    iPresenter.printString("You played functioned card for last card, which is invalid.");
                    String punishCard = drawCardWithNotification(false);
                    gameCardHolders.addCard(punishCard, currentCardHolder);
                }
                gameStatus.functionCardResponse(feature); // respond according to features
                cardChecker.functionCardResponseForComputer(feature, iPresenter, gameRequest); // specific response for color change
            }
        }
    }

    public Deck getDeck() {
        return this.deck;
    }

    public void setiTerminal(IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }
}


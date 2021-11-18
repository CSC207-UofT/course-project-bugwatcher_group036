package UseCase;

import Entity.*;

import java.util.ArrayList;
import java.util.Collections;

public class Dealer {

    private Deck deck;
    private EntityTerminal entityTerminal;

    public Dealer(Deck deck){
        this.deck = deck;
        this.entityTerminal = new EntityTerminal();
    }

    public String drawCard(){
        if (deck.getUnusedCardDeck().size() == 0){
            deck.shuffleFromUsedToUnused();
        }
        return deck.drawCardFromUnusedDeck();
    }

    public String drawCardWithNotification(boolean noCard) {
        String drawn = drawCard();
        entityTerminal.drawCardNotification(drawn, noCard);
        return drawn;
    }

    public void plusManyNextPlayer(int currentPlayerIndex, GameBoard gameBoard) {
        StringBuilder drawnCardName = new StringBuilder();
        int numToDraw = gameBoard.getStatus().getPlus();
        for (int i = 0; i < numToDraw; i++) {
            String drawnCard = drawCard();
            if (drawnCard != null) {
                gameBoard.getHandCards(currentPlayerIndex).addCard(drawnCard);
                drawnCardName.append(drawnCard);
            }
            if (i != numToDraw - 1) {
                drawnCardName.append(", ");
            }
        }
        entityTerminal.printString("You draw " + numToDraw + " cards. The cards you've drawn are " +
                drawnCardName + ".");
    }

    public void operationWhenNoPlayableCard(int currentPlayerIndex, GameBoard gameBoard, CardChecker cardChecker) {
        Status vars = gameBoard.getStatus();
        if (vars.getPlus() > 0){
            plusManyNextPlayer(currentPlayerIndex, gameBoard);
            vars.setPlus(0);
        } else if (!cardChecker.getLastCard().split(" ")[1].equals("skip") ||
                (cardChecker.getLastCard().split(" ")[1].equals("skip") && !vars.isSkip())) {
            gameBoard.getHandCards(currentPlayerIndex).addCard(drawCardWithNotification(true));
        }
    }

    public String punishOrPlayCard(String cardToPlay, int currentPlayerIndex) {
        if (cardToPlay == null) {
            entityTerminal.printString("Enter too many times wrong cards! Draw a card for punishment.");
            return drawCardWithNotification(false);
        } else if (!cardToPlay.equals("white -1")) {
            deck.putCardToUsedDeck(cardToPlay);
        }
        return null;
    }

    public void checkLastCard(String toPlay, GameBoard gameBoard, CardChecker cardChecker) {
        Status vars = gameBoard.getStatus();

        // create the arrayList for all possible number features
        ArrayList<String> num = new ArrayList<>();
        Collections.addAll(num, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9");

        if (toPlay != null && !toPlay.equals("white -1")) {
            String feature = toPlay.split(" ")[1];
            entityTerminal.printString("The card " + toPlay + " is played.");
            if (!num.contains(feature)) {
                if (gameBoard.checkWinState()){
                    entityTerminal.printString("You played functioned card for last card, which is invalid.");
                    String punishCard = drawCardWithNotification(false);
                }
                gameBoard.getStatus().functionCardResponse(feature);
                cardChecker.functionCardResponse(feature, entityTerminal);
            }
        }
    }

    public Deck getDeck() {
        return deck;
    }
}

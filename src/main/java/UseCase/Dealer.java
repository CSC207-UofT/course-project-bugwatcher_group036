package UseCase;

import Controller.ITerminal;
import Entity.*;

import java.util.ArrayList;
import java.util.Collections;

public class Dealer {

    private final Deck deck;
    private ITerminal iTerminal;

    public Dealer(Deck deck){
        this.deck = deck;
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
        iTerminal.drawCardNotification(drawn, noCard);
        return drawn;
    }

    public void plusManyNextPlayer(Status vars, HandCard handCard) {
        // draw multiple cards for given player and notify
        StringBuilder drawnCardName = new StringBuilder();
        int numToDraw = vars.getPlus();
        for (int i = 0; i < numToDraw; i++) {
            String drawnCard = drawCard();
            if (drawnCard != null) {
                handCard.addCard(drawnCard);
                drawnCardName.append(drawnCard);
            }
            if (i != numToDraw - 1) {
                drawnCardName.append(", ");
            }
        }
        iTerminal.printString("You draw " + numToDraw + " cards. The cards you've drawn are " +
                drawnCardName + ".");
    }

    public void operationWhenNoPlayableCard(Status vars, HandCard handCard, CardChecker cardChecker) {
        if (vars.getPlus() > 0){
            plusManyNextPlayer(vars, handCard);
            vars.setPlus(0); // reset plus to zero
        } else if (!cardChecker.getLastCard().split(" ")[1].equals("skip") ||
                (cardChecker.getLastCard().split(" ")[1].equals("skip") && !vars.isSkip())) {
            // if really no card playable, let player draw card, with punish notification
            handCard.addCard(drawCardWithNotification(true));
        }
    }

    public String punishOrPlayCard(String cardToPlay) {
        if (cardToPlay == null) {
            iTerminal.printString("Enter too many times wrong cards! Draw a card for punishment.");
            return drawCardWithNotification(false); // not because no card playable
        } else if (!cardToPlay.equals("white -1") && !cardToPlay.equals("quit")) { // if it is not voluntary draw
            deck.putCardToUsedDeck(cardToPlay);
        }
        return null;
    }

    public void checkLastCard(String toPlay, HandCard handCard, Status vars, CardChecker cardChecker) {
        // create the arrayList for all possible number features
        ArrayList<String> num = new ArrayList<>();
        Collections.addAll(num, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9");

        if (toPlay != null && !toPlay.equals("white -1") && !toPlay.equals("quit")) {
            // if the player has played a card
            String feature = toPlay.split(" ")[1];
            iTerminal.printString("The card " + toPlay + " is played.");
            if (!num.contains(feature)) { // if function card is played
                if (handCard.isEmpty()){
                    iTerminal.printString("You played functioned card for last card, which is invalid.");
                    String punishCard = drawCardWithNotification(false);
                    handCard.addCard(punishCard);
                }
                vars.functionCardResponse(feature); // respond according to features
                cardChecker.functionCardResponse(feature, iTerminal); // specific response for color change
            }
        }
    }

    public void setiTerminal(ITerminal iTerminal) {
        this.iTerminal = iTerminal;
    }
}

package Controller;

import Entity.Card;
import UseCase.BasicOperations;
import UseCase.DeckManager;
import UseCase.PlayerManager;
import UseCase.Status;

import java.util.ArrayList;
import java.util.Collections;

public class Dealer {

//    private final PlayerManager playerManager;
//    private final DeckManager deckManager;
    private final PlayerManagerData playerManagerData;
    private final DeckManagerData deckManagerData;

    public Dealer(PlayerManagerData playerManagerData, DeckManagerData deckManagerData){
        this.playerManagerData = playerManagerData;
        this.deckManagerData = deckManagerData;
    }

    /**
     * Draw card when no card can be played
     * @param currentCardsPlayerCanPlay cards player can play
     * @param currentPlayerIndex indicate current position of the player
     */
    public void drawCardWhenNoCardToPlay(ArrayList<Card> currentCardsPlayerCanPlay, int currentPlayerIndex) {
        if (currentCardsPlayerCanPlay.isEmpty()) {
            System.out.println("You cannot play a card! You need to draw one more card");
            // draw a card from the deck
            drawCard(currentPlayerIndex);
        }
    }

    /**
     * Punish or play the card given the attribute of cardToPlay
     * @param cardToPlay the card player would play
     * @param currentPlayerIndex indicate current position of the player
     */
    public void punishOrPlayCard(Card cardToPlay, int currentPlayerIndex) {
        // return false for punishment, true for play a card
        // If the player types 3 times wrong card, draw a card, otherwise play the card.
        if (deckManagerData.getDeckManager().whetherNull(cardToPlay)) {
            System.out.println("Enter too many times wrong cards! Draw a card for punishment.");
            drawCard(currentPlayerIndex);
        } else if (!deckManagerData.getDeckManager().color(cardToPlay).equals("white")) {
            // if the played card is valid, play the card
            Card playedCard = playerManagerData.getPlayerManager().playerPlayCard(currentPlayerIndex, cardToPlay);
            // put the played into the used deck
            deckManagerData.getDeckManager().putCardToUsedDeck(playedCard);
        }
    }

    /**
     * let indicated player draw card
     * @param currentPlayerIndex indicate current position of the player
     */
    protected void drawCard(int currentPlayerIndex) {
        Card c = deckManagerData.getDeckManager().drawCardFromUnusedDeck();
        // if the drawn card is not null
        if (!deckManagerData.getDeckManager().whetherNull(c)){
            // give the card to the player
            playerManagerData.getPlayerManager().playerDrawCard(currentPlayerIndex, c);
            System.out.println("The card you drew is " + c);
        }
    }

    /**
     * Case plus > 0, let indicated player draw multiple cards
     * @param currentPlayerIndex indicate current position of the player
     * @param num the number of cards to draw
     */
    public void plusManyNextPlayer(int currentPlayerIndex, int num) {
        for (int i = 0; i < num; i++) {
            Card drawedCard = deckManagerData.getDeckManager().drawCardFromUnusedDeck();
            if (!deckManagerData.getDeckManager().whetherNull(drawedCard)) {
                playerManagerData.getPlayerManager().getPlayers()[currentPlayerIndex].drawCard(drawedCard);
            }
        }
    }

    /**
     * Check whether the last card played is valid
     * @param cardToPlay the card player would play
     * @param basicOperations variables and settings
     */
    public void checkLastCard(Card cardToPlay, BasicOperations basicOperations){
        Status vars = basicOperations.getVars();

        // create the arrayList for all possible number features
        ArrayList<String> num = new ArrayList<>();
        Collections.addAll(num, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9");

        // if the player successfully plays a card
        if (!deckManagerData.getDeckManager().whetherNull(cardToPlay) &&
                !deckManagerData.getDeckManager().color(cardToPlay).equals("white")) {
            String feature = deckManagerData.getDeckManager().feature(cardToPlay);
            // if the player plays a function card
            if (!num.contains(feature)) {
                // if it is the last card that the palyer plays is a function card, draw a card.
                if (playerManagerData.getPlayerManager().winOrNot(vars.getCurrentPlayerIndex())) {
                    Card c = deckManagerData.getDeckManager().drawCardFromUnusedDeck();
                    playerManagerData.getPlayerManager().getPlayers()[vars.getCurrentPlayerIndex()].drawCard(c);
                }
                basicOperations.functionCardResponse(vars, feature);
            }
        }
    }

    /**
     * Operations when current player has no card to play
     * @param currentCardsPlayerCanPlay cards can be played
     * @param basicOperations variables and settings
     */
    public void operationsWhenNoCardToPlay(ArrayList<Card> currentCardsPlayerCanPlay,
                                           BasicOperations basicOperations) {
        if (basicOperations.getVars().getPlus() > 0) {
            plusManyNextPlayer(basicOperations.getVars().getCurrentPlayerIndex(),
                    basicOperations.getVars().getPlus());
            basicOperations.getVars().setPlus(0);
        } else if (!deckManagerData.getDeckManager().feature(playerManagerData.getPlayerManager().getLastCard()).
                equals("skip") ||
                (deckManagerData.getDeckManager().feature(playerManagerData.getPlayerManager().getLastCard()).
                        equals("skip") &&
                        !basicOperations.getVars().isSkip())){
            // draw a card when there is no valid card can play
            drawCardWhenNoCardToPlay(currentCardsPlayerCanPlay,
                    basicOperations.getVars().getCurrentPlayerIndex());
        }
    }

}

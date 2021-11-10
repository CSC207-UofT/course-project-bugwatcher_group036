package Controller;

import Entity.Card;
import UseCase.BasicOperations;
import UseCase.DeckManager;
import UseCase.PlayerManager;
import UseCase.Status;

import java.util.ArrayList;
import java.util.Scanner;

public class EachRound {

    private final PlayerManager playerManager;
    private final DeckManager cardManager;
    private final Dealer dealer;
    private final BasicOperations basicOperations;

    public EachRound(PlayerManager playerManager, DeckManager cardManager,
                     Dealer dealer, BasicOperations basicOperations) {
        this.playerManager = playerManager;
        this.cardManager = cardManager;
        this.dealer = dealer;
        this.basicOperations = basicOperations;
    }

    public Card createNullCard(){
        return cardManager.createNullCard();
    }

    /**
     * core of player play card process
     * @param currentCardsPlayerCanPlay the cards current player can play
     * @param currentPlayerIndex indicate the position of current player
     * @return the card player would play
     */
    public Card letPlayerPlayCard(ArrayList<Card> currentCardsPlayerCanPlay,
                                  int currentPlayerIndex) {
        Scanner keyboard = new Scanner(System.in);

        // The card that the player wants to play
        Card cardToPlay;

        // rightCard indicates whether the play type a right card to play.
        boolean rightCard;

        // The number of times that the player type a wrong card.
        int wrongTimes = 0;

        // Let the player type the card to play. If type a wrong card, type again,
        // with maximum 3 times.
        do {
            System.out.println("Choose to play a card or type draw to draw a card:");

            // let the player type the card to play
            String cardToPlayID = keyboard.nextLine();

            // extract the card to play from the hand card
            cardToPlay = cardManager.extractCard(currentCardsPlayerCanPlay, cardToPlayID);

            if (cardToPlayID.equals("draw")) {
                dealer.drawCard(currentPlayerIndex);
                wrongTimes = 4;
                cardToPlay = cardManager.createColorCard("white");
            }
            // if the card chose is null, count the wrong time
            if (cardManager.whetherNull(cardToPlay)) {
                wrongTimes++;
                rightCard = false;
            } else {
                rightCard = true;
            }
        } while (!rightCard && wrongTimes < 3);
        //exit when the player types the right class or wrong time exceed 3
        return cardToPlay;
    }

    /**
     * Set up things before each player's stage.
     * @return Cards player can play in this round
     */
    public ArrayList<Card> beginStage(){
        Status vars = basicOperations.getVars();
        // show the current player
        System.out.println();
        System.out.println("Current player: " + playerManager.getPlayers()[vars.getCurrentPlayerIndex()]);

        // get cards player can play considering special cases of function cards

        return basicOperations.getCardsCurrentPlayerCanPlay
                (playerManager.getPlayers()[vars.getCurrentPlayerIndex()]);
    }

    /**
     * The stage where player play cards can do corresponding reactions
     * @param currentCardsPlayerCanPlay playable cards we get from begin stage
     * @param cardToPlay The card player would play
     * @return the updated cardToPlay player has played
     */
    public Card playStage(ArrayList<Card> currentCardsPlayerCanPlay, Card cardToPlay){
        Status vars = basicOperations.getVars();
        if (currentCardsPlayerCanPlay.isEmpty()) {
            dealer.operationsWhenNoCardToPlay(currentCardsPlayerCanPlay, basicOperations);
        }
        else {
            // print all the information
            System.out.println("Last card: " + playerManager.getLastCard());
            System.out.println("The cards you have: " + playerManager.getHandCard(vars.getCurrentPlayerIndex()));
            System.out.println("The cards you can play: " + currentCardsPlayerCanPlay);

            // Let the player type the card to play. If type a wrong card, type again with maximum 3 times.
            cardToPlay = letPlayerPlayCard(currentCardsPlayerCanPlay, vars.getCurrentPlayerIndex());

            // If the player types 3 times wrong card, draw a card, otherwise play the card.
            dealer.punishOrPlayCard(cardToPlay, vars.getCurrentPlayerIndex());
            //extract this part from PunishOrPlayCard to extract method to other class
            if (!cardManager.color(cardToPlay).equals("white") &&
                    !cardManager.whetherNull(cardToPlay)){
                // update the last card stored in gameBoard
                basicOperations.getGameBoard().setLastCard(cardToPlay);
            }
        }
        return cardToPlay;
    }

    /**
     * Corresponding response after card is played in each turn
     * @param cardToPlay the card player has played this turn
     */
    public void endStage(Card cardToPlay){
        Status vars = basicOperations.getVars();

        // set the skip to false since the function skip has passed.
        vars.setSkip(false);

        dealer.checkLastCard(cardToPlay, basicOperations);

        // Determine whether the player wins or not.
        if (playerManager.winOrNot(vars.getCurrentPlayerIndex())) {
            vars.setWinFlag(true);
            vars.setPlayerWins(playerManager.getPlayers()[vars.getCurrentPlayerIndex()]);
        }
    }
}

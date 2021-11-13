package Controller;

import Entity.Card;
import UI.UI;
import UseCase.BasicOperations;
import UseCase.DeckManager;
import UseCase.PlayerManager;
import UseCase.Status;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class EachRound {

    //    private final PlayerManager playerManager;
//    private final DeckManager deckManager;
    private final PlayerManagerData playerManagerData;
    private final DeckManagerData deckManagerData;
    private final Dealer dealer;
    public UI ui;
    //    private final BasicOperations basicOperations;
    private BasicOperationsData basicOperationsData;

    public EachRound(PlayerManagerData playerManagerData, DeckManagerData deckManagerData,
                     Dealer dealer, BasicOperationsData basicOperationsData) {
        this.playerManagerData = playerManagerData;
        this.deckManagerData = deckManagerData;
        this.dealer = dealer;
        this.basicOperationsData = basicOperationsData;
    }

    public Card createNullCard(){
        return deckManagerData.getDeckManager().createNullCard();
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
            // String cardToPlayID = keyboard.nextLine();
            String cardToPlayID;
            if (ui != null) {
                Object[] array = currentCardsPlayerCanPlay.stream().map(m->m.toString()).toArray();
                // show the dialog let player choose card to play
                UIManager.put("OptionPane.cancelButtonText", "draw");
                UIManager.put("OptionPane.okButtonText", "play");
                cardToPlayID = (String) JOptionPane.showInputDialog(null, "OK to play card or cancel to draw",
                        playerManagerData.getPlayerManager().getPlayers()[currentPlayerIndex] + " Choose", JOptionPane.INFORMATION_MESSAGE, null, array, null);
                if (cardToPlayID == null) {
                    cardToPlayID = "draw";
                }
            }else{
                cardToPlayID = keyboard.nextLine();
            }

            // extract the card to play from the hand card
            cardToPlay = deckManagerData.getDeckManager().extractCard(currentCardsPlayerCanPlay, cardToPlayID);

            if (cardToPlayID.equals("draw")) {
                dealer.drawCard(currentPlayerIndex);
                wrongTimes = 4;
                cardToPlay = deckManagerData.getDeckManager().createColorCard("white");
            }
            // if the card chose is null, count the wrong time
            if (deckManagerData.getDeckManager().whetherNull(cardToPlay)) {
                wrongTimes++;
                rightCard = false;
            } else {
                rightCard = true;
            }
        } while (!rightCard && wrongTimes < 3);
        //exit when the player types the right class or wrong time exceed 3
        return cardToPlay;
    }

    public Card letPlayerPlayCardForComputer(ArrayList<Card> currentCardsPlayerCanPlay,
                                             int currentPlayerIndex) {

        if (currentPlayerIndex == 0) {
            return letPlayerPlayCard( currentCardsPlayerCanPlay, currentPlayerIndex);
        }

        // The card that the player wants to play
        Card cardToPlay;

        // rightCard indicates whether the play type a right card to play.
        boolean rightCard;

        // The number of times that the player type a wrong card.
        int wrongTimes = 0;

        // Let the player type the card to play. If type a wrong card, type again,
        // with maximum 3 times.
        do {
            String cardToPlayID = currentCardsPlayerCanPlay.get(0).getId();

            // extract the card to play from the hand card
            cardToPlay = deckManagerData.getDeckManager().extractCard(currentCardsPlayerCanPlay, cardToPlayID);

            if (cardToPlayID.equals("draw")) {
                dealer.drawCard(currentPlayerIndex);
                wrongTimes = 4;
                cardToPlay = deckManagerData.getDeckManager().createColorCard("white");
            }
            // if the card chose is null, count the wrong time
            if (deckManagerData.getDeckManager().whetherNull(cardToPlay)) {
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
        StatusData varsData = new StatusData(basicOperationsData.getBasicOperations().getVars());
        // show the current player
        System.out.println();
        System.out.println("Current player: " +
                playerManagerData.getPlayerManager().getPlayers()[varsData.getStatus().getCurrentPlayerIndex()]);

        // get cards player can play considering special cases of function cards

        return basicOperationsData.getBasicOperations().getCardsCurrentPlayerCanPlay
                (playerManagerData.getPlayerManager().getPlayers()[varsData.getStatus().getCurrentPlayerIndex()]);
    }

    /**
     * The stage where player play cards can do corresponding reactions
     * @param currentCardsPlayerCanPlay playable cards we get from begin stage
     * @param cardToPlay The card player would play
     * @return the updated cardToPlay player has played
     */
    public Card playStage(ArrayList<Card> currentCardsPlayerCanPlay, Card cardToPlay){
        Status vars = basicOperationsData.getBasicOperations().getVars();
        ui.displayCard(playerManagerData.getPlayerManager().getLastCard(),playerManagerData.getPlayerManager().getHandCard(vars.getCurrentPlayerIndex()), deckManagerData.getDeckManager().getD().getUnusedCardDeck());
        if (currentCardsPlayerCanPlay.isEmpty()) {
            dealer.operationsWhenNoCardToPlay(currentCardsPlayerCanPlay, basicOperationsData.getBasicOperations());
        }
        else {
            // print all the information
            System.out.println("Last card: " + playerManagerData.getPlayerManager().getLastCard());
            System.out.println("The cards you have: " +
                    playerManagerData.getPlayerManager().getHandCard(vars.getCurrentPlayerIndex()));
            System.out.println("The cards you can play: " + currentCardsPlayerCanPlay);

            // Let the player type the card to play. If type a wrong card, type again with maximum 3 times.
            cardToPlay = letPlayerPlayCard(currentCardsPlayerCanPlay, vars.getCurrentPlayerIndex());

            // If the player types 3 times wrong card, draw a card, otherwise play the card.
            dealer.punishOrPlayCard(cardToPlay, vars.getCurrentPlayerIndex());
            //extract this part from PunishOrPlayCard to extract method to other class
            if (!deckManagerData.getDeckManager().color(cardToPlay).equals("white") &&
                    !deckManagerData.getDeckManager().whetherNull(cardToPlay)){
                // update the last card stored in gameBoard
                basicOperationsData.getBasicOperations().getGameBoard().setLastCard(cardToPlay);
            }
        }
        return cardToPlay;
    }

    public Card playStageForComputer(ArrayList<Card> currentCardsPlayerCanPlay, Card cardToPlay)
            throws InterruptedException {
        Status vars = basicOperationsData.getBasicOperations().getVars();
        // Normal play stage for human player
        if (vars.getCurrentPlayerIndex() == 0){
            return playStage(currentCardsPlayerCanPlay, cardToPlay);
        }
        if (currentCardsPlayerCanPlay.isEmpty()) {
            dealer.operationsWhenNoCardToPlayForComputer(currentCardsPlayerCanPlay, basicOperationsData.getBasicOperations());
        }
        else {
            // print all the information
            System.out.println("Last card: " + playerManagerData.getPlayerManager().getLastCard());

            // Let the player type the card to play. If type a wrong card, type again with maximum 3 times.
            cardToPlay = letPlayerPlayCardForComputer(currentCardsPlayerCanPlay, vars.getCurrentPlayerIndex());

            // If the player types 3 times wrong card, draw a card, otherwise play the card.
            dealer.punishOrPlayCardForComputer(cardToPlay, vars.getCurrentPlayerIndex());
            //extract this part from PunishOrPlayCard to extract method to other class
            if (!deckManagerData.getDeckManager().color(cardToPlay).equals("white") &&
                    !deckManagerData.getDeckManager().whetherNull(cardToPlay)){
                // update the last card stored in gameBoard
                basicOperationsData.getBasicOperations().getGameBoard().setLastCard(cardToPlay);
            }
        }
        // print how many card computer has at the end (after card draw)
        System.out.println("Computer " + (vars.getCurrentPlayerIndex()) + " has " +
                playerManagerData.getPlayerManager().getHandCard(vars.getCurrentPlayerIndex()).size() +
                " cards");

        // Sleep for one second to let player read infos
        sleep(1000);
        return cardToPlay;
    }

    /**
     * Corresponding response after card is played in each turn
     * @param cardToPlay the card player has played this turn
     */
    public void endStage(Card cardToPlay){
        Status vars = basicOperationsData.getBasicOperations().getVars();

        // set the skip to false since the function skip has passed.
        vars.setSkip(false);

        dealer.checkLastCard(cardToPlay, basicOperationsData.getBasicOperations());

        // Determine whether the player wins or not.
        if (playerManagerData.getPlayerManager().winOrNot(vars.getCurrentPlayerIndex())) {
            vars.setWinFlag(true);
            vars.setPlayerWins(playerManagerData.getPlayerManager().getPlayers()[vars.getCurrentPlayerIndex()]);
        }
    }

    public void endStageForComputer(Card cardToPlay) throws InterruptedException {
        Status vars = basicOperationsData.getBasicOperations().getVars();

        // set the skip to false since the function skip has passed.
        vars.setSkip(false);

        dealer.checkLastCardForComputer(cardToPlay, basicOperationsData.getBasicOperations());

        // Determine whether the player wins or not.
        if (playerManagerData.getPlayerManager().winOrNot(vars.getCurrentPlayerIndex())) {
            vars.setWinFlag(true);
            vars.setPlayerWins(playerManagerData.getPlayerManager().getPlayers()[vars.getCurrentPlayerIndex()]);
        }

        //sleep for one second to read what card computer has played
        sleep(2000);
    }
    public PlayerManagerData getPlayerManagerData(){return playerManagerData;}
}

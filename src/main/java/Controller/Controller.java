package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Entity.Card;
import Entity.Player;
import UseCase.BasicOperations;
import UseCase.PlayerManager;
import UseCase.DeckManager;
import UseCase.Status;

/**
 * The main.Controller to run a game.
 */
public class Controller {

    private PlayerManager playerManager;
    private DeckManager cardManager;
    private Dealer dealer;
    private BasicOperations basicOperations;

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

    public boolean operationsWhenNoCardToPlay(ArrayList<Card> currentCardsPlayerCanPlay) {
        boolean drawCard = false;
        if (basicOperations.getVars().getPlus() > 0) {
            dealer.plusManyNextPlayer(basicOperations.getVars().getCurrentPlayerIndex(),
                    basicOperations.getVars().getPlus());
            basicOperations.getVars().setPlus(0);
            drawCard = true;
        } else if (!cardManager.feature(playerManager.getLastCard()).equals("skip") ||
                (cardManager.feature(playerManager.getLastCard()).equals("skip") &&
                        !basicOperations.getVars().isSkip())){
            // draw a card when there is no valid card can play
            drawCard = dealer.drawCardWhenNoCardToPlay(currentCardsPlayerCanPlay,
                    basicOperations.getVars().getCurrentPlayerIndex());
        }
        return drawCard;
    }

    /**
     * run the game and return the player that wins.
     * @return the player that wins.
     */
    public Player runGame() {
        Status vars = basicOperations.getVars();
        // if winFlag is true, it means the winner appears and the while loop exits.
        while (!vars.isWinFlag()) {
            // cardToPlay is the card that the player wants to play.
            Card cardToPlay = cardManager.createNullCard();
            // show the current player
            System.out.println();
            System.out.println("Current player: " + playerManager.getPlayers()[vars.getCurrentPlayerIndex()]);

            // get cards player can play considering special cases of function cards
            ArrayList<Card> currentCardsPlayerCanPlay =
                    basicOperations.getCardsCurrentPlayerCanPlay
                            (playerManager.getPlayers()[vars.getCurrentPlayerIndex()]);

            // If no cards can play, draw a card, otherwise play a card. If the player type three times
            // wrong card to play, the player will be punished to draw a card automatically.
            boolean drawCardToPlay = false;
            if (currentCardsPlayerCanPlay.isEmpty()) {
                drawCardToPlay = operationsWhenNoCardToPlay(currentCardsPlayerCanPlay);
            }
            ArrayList<Card> playableCards = basicOperations.getCardsCurrentPlayerCanPlay
                    (playerManager.getPlayers()[vars.getCurrentPlayerIndex()]);
            if (!playableCards.isEmpty()) {

                if (!drawCardToPlay) {
                    // print all the information
                    System.out.println("Last card: " + playerManager.getLastCard());
                    System.out.println("The cards you have: " + playerManager.getHandCard(vars.getCurrentPlayerIndex()));
                    System.out.println("The cards you can play: " + playableCards);

                    // Let the player type the card to play. If type a wrong card, type again with maximum 3 times.
                    cardToPlay = letPlayerPlayCard(playableCards, vars.getCurrentPlayerIndex());

                    // If the player types 3 times wrong card, draw a card, otherwise play the card.
                    dealer.punishOrPlayCard(cardToPlay, vars.getCurrentPlayerIndex());
                    //extract this part from PunishOrPlayCard to extract method to other class
                    if (!cardManager.color(cardToPlay).equals("white") &&
                            !cardManager.whetherNull(cardToPlay)){
                        // update the last card stored in gameBoard
                        basicOperations.getGameBoard().setLastCard(cardToPlay);
                    }
                }
            }

            // set the skip to false since the function skip has passed.
            vars.setSkip(false);

            dealer.checkLastCard(cardToPlay, basicOperations);

            // Determine whether the player wins or not.
            if (playerManager.winOrNot(vars.getCurrentPlayerIndex())) {
                vars.setWinFlag(true);
                vars.setPlayerWins(playerManager.getPlayers()[vars.getCurrentPlayerIndex()]);
            }

            // Move to the next player
            vars.setCurrentPlayerIndex(
                    basicOperations.getGameBoard().moveToNextPlayer(vars.isReverse()));
        }
        return vars.getPlayerWins();
    }

    public void setCardManager(DeckManager cardManager) {
        this.cardManager = cardManager;
    }

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public void setBasicOperations(BasicOperations basicOperations) {
        this.basicOperations = basicOperations;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }
}
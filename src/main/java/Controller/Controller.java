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
    private ArrayList<String> num;
    private BasicOperations basicOperations;


    public boolean drawCardWhenNoCardToPlay(ArrayList<Card> currentCardsPlayerCanPlay, int currentPlayerIndex) {
        boolean drawCard = false;
        if (currentCardsPlayerCanPlay.isEmpty()) {
            System.out.println("You cannot play a card! You need to draw one more card");
            // draw a card from the deck
            drawCard = drawCard(currentPlayerIndex);
        }
        return drawCard;
    }

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
                drawCard(currentPlayerIndex);
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
            plusManyNextPlayer(basicOperations.getVars().getCurrentPlayerIndex(),
                    basicOperations.getVars().getPlus());
            basicOperations.getVars().setPlus(0);
            drawCard = true;
        } else if (!cardManager.feature(playerManager.getLastCard()).equals("skip") ||
                (cardManager.feature(playerManager.getLastCard()).equals("skip") &&
                        !basicOperations.getVars().isSkip())){
            // draw a card when there is no valid card can play
            drawCard = drawCardWhenNoCardToPlay(currentCardsPlayerCanPlay,
                    basicOperations.getVars().getCurrentPlayerIndex());
        }
        return drawCard;
    }

    public void punishOrPlayCard(Card cardToPlay, int currentPlayerIndex) {
        // return false for punishment, true for play a card
        // If the player types 3 times wrong card, draw a card, otherwise play the card.
        if (cardManager.whetherNull(cardToPlay)) {
            System.out.println("Enter too many times wrong cards! Draw a card for punishment.");
            drawCard(currentPlayerIndex);
        } else if (!cardManager.color(cardToPlay).equals("white")) {
            // if the played card is valid, play the card
            Card playedCard = playerManager.playerPlayCard(currentPlayerIndex, cardToPlay);
            basicOperations.getGameBoard().setLastCard(playedCard);
            // put the played into the used deck
            cardManager.putCardToUsedDeck(playedCard);
        }
    }

    private boolean drawCard(int currentPlayerIndex) {
        Card c = cardManager.drawCardFromUnusedDeck();
        // if the drawn card is not null
        if (!cardManager.whetherNull(c)){
            // give the card to the player
            playerManager.playerDrawCard(currentPlayerIndex, c);
            System.out.println("The card you drew is " + c);
            return true;
        }
        return false;
    }

    public int moveToNextPlayer(int currentPlayerIndex, boolean reverse) {
        // Move to the next player
        if (!reverse){
            currentPlayerIndex++;
            if (currentPlayerIndex == playerManager.getPlayerNum()) {
                currentPlayerIndex = 0;
            }
            return currentPlayerIndex;
        } else {
            currentPlayerIndex--;
            if (currentPlayerIndex == -1) {
                currentPlayerIndex = playerManager.getPlayerNum() - 1;
            }
            return currentPlayerIndex;
        }
    }

    public void plusManyNextPlayer(int currentPlayerIndex, int num) {
        for (int i = 0; i < num; i++) {
            Card drawedCard = cardManager.drawCardFromUnusedDeck();
            if (!cardManager.whetherNull(drawedCard)) {
                playerManager.getPlayers()[currentPlayerIndex].drawCard(drawedCard);
            }
        }
    }

    /**
     * run the game and return the player that wins.
     * @return the player that wins.
     */
    public Player runGame() {
        Status vars = basicOperations.getVars();
        // if winFlag is true, it means the winner appears and the while loop exits.
        while (!vars.isWinFlag()) {
//            // whether the player successfully plays a card
//            boolean whetherPlayCard;

            // whether skip the next player


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

                    // cardToPlay is the card that the player wants to play.
//                Card cardToPlay;

                    // Let the player type the card to play. If type a wrong card, type again with maximum 3 times.
                    cardToPlay = letPlayerPlayCard(playableCards, vars.getCurrentPlayerIndex());

                    // If the player types 3 times wrong card, draw a card, otherwise play the card.
                    punishOrPlayCard(cardToPlay, vars.getCurrentPlayerIndex());
                }
            }

            // set the skip to false since the function skip has passed.
            vars.setSkip(false);

            // if the player successfully play a card
            if (!cardManager.whetherNull(cardToPlay) && !cardManager.color(cardToPlay).equals("white")) {
                String feature = cardManager.feature(cardToPlay);
                // if the player plays a function card
                if (!num.contains(feature)) {
                    // if it is the last card that the palyer plays is a function card, draw a card.
                    if (playerManager.winOrNot(vars.getCurrentPlayerIndex())) {
                        Card c = cardManager.drawCardFromUnusedDeck();
                        playerManager.getPlayers()[vars.getCurrentPlayerIndex()].drawCard(c);
                    }

                    basicOperations.functionCardResponse(vars, feature);
                }
            }

            // Determine whether the player wins or not.
            if (playerManager.winOrNot(vars.getCurrentPlayerIndex())) {
                vars.setWinFlag(true);
                vars.setPlayerWins(playerManager.getPlayers()[vars.getCurrentPlayerIndex()]);
            }

            // Move to the next player
            vars.setCurrentPlayerIndex(moveToNextPlayer(vars.getCurrentPlayerIndex(), vars.isReverse()));
        }
        return vars.getPlayerWins();
    }

    public void setCardManager(DeckManager cardManager) {
        this.cardManager = cardManager;
    }

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public void setNum(ArrayList<String> num) {
        this.num = num;
    }

    public void setBasicOperations(BasicOperations basicOperations) {
        this.basicOperations = basicOperations;
    }
}
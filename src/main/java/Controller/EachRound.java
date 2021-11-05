package Controller;

import Entity.Card;
import UseCase.DeckManager;
import UseCase.PlayerManager;

import java.util.ArrayList;
import java.util.Scanner;

// This class contains methods for each round of game
public class EachRound {

    private PlayerManager playerManager;
    private DeckManager cardManager;
    private ArrayList<String> num;
    private ArrayList<String> colors;
    private FunctionPlayed functionCardPlayed;

    public EachRound(PlayerManager playerManager, DeckManager cardManager, ArrayList<String> num,
                          ArrayList<String> colors) {
        this.playerManager = playerManager;
        this.cardManager = cardManager;
        this.num = num;
        this.colors = colors;
        this.functionCardPlayed = new FunctionPlayed(playerManager, cardManager, num, colors);
    }

    public boolean drawCardWhenNoCardToPlay(ArrayList<Card> currentCardsPlayerCanPlay, int currentPlayerIndex,
                                         ControllerVariables vars) {
        boolean drawCard = false;
        if (currentCardsPlayerCanPlay.isEmpty()) {
            System.out.println("You cannot play a card! You need to draw one more card");
            // draw a card from the deck
            Card c = cardManager.drawCardFromUnusedDeck();
            // If the card drwan is not null
            if (!cardManager.whetherNull(c)) {
                //give the card to the player.
                playerManager.playerDrawCard(currentPlayerIndex, c);
                System.out.println("The card you drew is " + c);
                drawCard = true;
            }
        }
        return drawCard;
    }


    public Card letPlayerPlayCard(ArrayList<Card> currentCardsPlayerCanPlay,
                                  int currentPlayerIndex, ControllerVariables vars) {
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
                drawCard(vars);
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

    public void punishOrPlayCard(Card cardToPlay, int currentPlayerIndex) {
        // return false for punishment, true for play a card
        // If the player types 3 times wrong card, draw a card, otherwise play the card.
        if (cardManager.whetherNull(cardToPlay)) {
            System.out.println("Enter too many times wrong cards! Draw a card for punishment.");
            Card c = cardManager.drawCardFromUnusedDeck();
            // if the drawn card is not null
            if (!cardManager.whetherNull(c)){
                // give the card to the player
                playerManager.playerDrawCard(currentPlayerIndex, c);
                System.out.println("The card you drew is " + c);
            }
        } else if (!cardManager.color(cardToPlay).equals("white")) {
            // if the played card is valid, play the card
            Card playedCard = playerManager.playerPlayCard(currentPlayerIndex, cardToPlay);
            // put the played into the used deck
            cardManager.putCardToUsedDeck(playedCard);
        }
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

    public ArrayList<Card> getCurrentCardsPlayerCanPlayer(ControllerVariables vars){
        // Get the cards that the current player can play.
        // if the last card is skip, player only can play skip
        if (vars.isSkip()) {
            return cardManager.skipsPlayerCanPlay(playerManager.getHandCard(vars.getCurrentPlayerIndex()));
        } else if (vars.getPlus() > 0){
            // if the last card is plus2, player can play plus2 or plus4.
            if (cardManager.feature(playerManager.getLastCard()).equals("plustwo")) {
                return cardManager.plustwoPlayerCanPlay(playerManager.getHandCard(vars.getCurrentPlayerIndex()));
            } else {
                // if the last card is plus4, player can only play plus4.
                return cardManager.plusfourPlayerCanPlay(playerManager.getHandCard(vars.getCurrentPlayerIndex()));
            }
        } else {
            // get the cards that the current player can play normally
            return cardManager.cardsPlayerCanPlay(
                    playerManager.getHandCard(vars.getCurrentPlayerIndex()),
                    playerManager.getLastCard());
        }
    }

    public boolean operationsWhenNoCardToPlay(ControllerVariables vars, ArrayList<Card> currentCardsPlayerCanPlay) {
        boolean drawCard = false;
        if (vars.getPlus() > 0) {
            functionCardPlayed.plusManyNextPlayer(vars.getCurrentPlayerIndex(), vars.getPlus());
            vars.setPlus(0);
            drawCard = true;
        } else if (!cardManager.feature(playerManager.getLastCard()).equals("skip") ||
                (cardManager.feature(playerManager.getLastCard()).equals("skip") && !vars.isSkip())){
            // draw a card when there is no valid card can play
            drawCard = drawCardWhenNoCardToPlay(currentCardsPlayerCanPlay, vars.getCurrentPlayerIndex(), vars);
        }
        return drawCard;
    }

    public void winOrNotInThisRound(ControllerVariables vars) {
        if (playerManager.winOrNot(vars.getCurrentPlayerIndex())) {
            vars.setWinFlag(true);
            vars.setPlayerWins(playerManager.getPlayers()[vars.getCurrentPlayerIndex()]);
        }
    }

    public Card operationsForPlayer(ControllerVariables vars, Card cardToPlay,
                                    ArrayList<Card> currentCardsPlayerCanPlay) {
        boolean drawCardToPlay = false;
        if (currentCardsPlayerCanPlay.isEmpty()) {
            drawCardToPlay = operationsWhenNoCardToPlay(vars, currentCardsPlayerCanPlay);
        }
        if (!getCurrentCardsPlayerCanPlayer(vars).isEmpty()) {

            if (!drawCardToPlay) {
                // print all the information
                System.out.println("Last card: " + playerManager.getLastCard());
                System.out.println("The cards you have: " + playerManager.getHandCard(vars.getCurrentPlayerIndex()));
                System.out.println("The cards you can play: " + getCurrentCardsPlayerCanPlayer(vars));

                // cardToPlay is the card that the player wants to play.
//                Card cardToPlay;

                // Let the player type the card to play. If type a wrong card, type again with maximum 3 times.
                cardToPlay = letPlayerPlayCard(currentCardsPlayerCanPlay, vars.getCurrentPlayerIndex(), vars);

                // If the player types 3 times wrong card, draw a card, otherwise play the card.
                punishOrPlayCard(cardToPlay, vars.getCurrentPlayerIndex());


            }


//
        }
        return cardToPlay;
    }

    private void drawCard(ControllerVariables vars) {
        // draw a card from the deck
        Card c = cardManager.drawCardFromUnusedDeck();
        // If the card drwan is not null
        if (!cardManager.whetherNull(c)) {
            //give the card to the player.
            playerManager.playerDrawCard(vars.getCurrentPlayerIndex(), c);
            System.out.println("The card you drew is " + c);
        }
    }

    public void effectsAfterPunishOrPlayCard(ControllerVariables vars, Card cardToPlay) {
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

                functionCardPlayed.functionCardResponse(vars, feature);
            }
        }
    }

}

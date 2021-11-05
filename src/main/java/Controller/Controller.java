package Controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

import Entity.Card;
import Entity.Player;
import UseCase.PlayerManager;
import UseCase.DeckManager;

/**
 * The main.Controller to run a game.
 */
public class Controller {

    private PlayerManager playerManager;
    private DeckManager cardManager;
    private Random rand;
    private ArrayList<String> num;
    private ArrayList<String> colors;
    private EachRound eachRound;
    private FunctionPlayed functionPlayed;


    /**
     * run the game and return the player that wins.
     * @return the player that wins.
     */
    public Player runGame() {
        // create a new class that stores all the variables, and
        // we can use getter and setter methods to do corresponding operations.
        ControllerVariables vars = new ControllerVariables(this.playerManager.getPlayerNum());

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
            ArrayList<Card> currentCardsPlayerCanPlay = eachRound.getCurrentCardsPlayerCanPlayer(vars);

            // If no cards can play, draw a card, otherwise play a card. If the player type three times
            // wrong card to play, the player will be punished to draw a card automatically.
            cardToPlay = eachRound.operationsForPlayer(vars, cardToPlay, currentCardsPlayerCanPlay);

            // set the skip to false since the function skip has passed.
            vars.setSkip(false);

            // The outcome after the player plays a card or get punished.
            eachRound.effectsAfterPunishOrPlayCard(vars, cardToPlay);

            // Determine whether the player wins or not.
            eachRound.winOrNotInThisRound(vars);

            // Move to the next player
            vars.setCurrentPlayerIndex(eachRound.moveToNextPlayer(vars.getCurrentPlayerIndex(), vars.isReverse()));
        }
        return vars.getPlayerWins();
    }


    public void setCardManager(DeckManager cardManager) {
        this.cardManager = cardManager;
    }

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public void setNum(ArrayList<String> num) {
        this.num = num;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public void setEachRound(EachRound eachRound) {
        this.eachRound = eachRound;
    }

    public void setFunctionPlayed(FunctionPlayed functionPlayed) {
        this.functionPlayed = functionPlayed;
    }

    public static void main(String[] args) {
        ControllerBuilder unoBuilder = new ControllerBuilder(4);
        Controller newGameController = unoBuilder.buildUnoController();
        Player playerWins = newGameController.runGame();
        System.out.println(playerWins.getId() + " wins!");
    }

}

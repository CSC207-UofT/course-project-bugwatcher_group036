package Controller;

import java.util.ArrayList;

import Entity.Card;
import Entity.Player;
import UI.UI;
import UseCase.BasicOperations;
import UseCase.Status;

import javax.swing.*;

/**
 * The main.Controller to run a game.
 */
public class Controller {

    private BasicOperations basicOperations;
    private EachRound eachRound;
    private UI ui;

    /**
     * run the game and return the player that wins.
     * @return the player that wins.
     */
    public Player runGame() {
        Status vars = basicOperations.getVars();
        // if winFlag is true, it means the winner appears and the while loop exits.
        while (!vars.isWinFlag()) {
            // cardToPlay is the card that the player wants to play.
            Card cardToPlay = eachRound.createNullCard();

            // get cards player can play considering special cases of function cards
            ArrayList<Card> currentCardsPlayerCanPlay = eachRound.beginStage();

            // If no cards can play, draw a card, otherwise play a card. If the player type three times
            // wrong card to play, the player will be punished to draw a card automatically.
            cardToPlay = eachRound.playStage(currentCardsPlayerCanPlay, cardToPlay);

            eachRound.endStage(cardToPlay);

            // Move to the next player
            vars.setCurrentPlayerIndex(
                    basicOperations.getVars().moveToNextPlayer(vars.isReverse()));
        }
        JOptionPane.showMessageDialog(null, vars.getPlayerWins().getId() + " wins!");

        return vars.getPlayerWins();
    }

    public void setBasicOperations(BasicOperations basicOperations) {
        this.basicOperations = basicOperations;
    }

    public void setEachRound(EachRound eachRound) {
        this.eachRound = eachRound;
    }

    public void setUI(UI ui) {
        eachRound.ui = ui;
    }

    public EachRound getEachRound() {
        return eachRound;
    }
    public BasicOperations getBasicOperations() {return basicOperations;}
}
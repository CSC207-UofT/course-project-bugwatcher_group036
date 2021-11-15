package Controller;

import java.util.ArrayList;

import Entity.Card;
import Entity.Player;
import UI.UI;
import UseCase.BasicOperations;
import UseCase.Status;
import UI.DisplayCard;

import javax.swing.*;

/**
 * The main.Controller to run a game.
 */
public class Controller {

//    private BasicOperations basicOperations;
    private BasicOperationsData basicOperationsData;
    private EachRound eachRound;
    private UI ui;

    /**
     * run the game and return the player that wins.
     * @return the player that wins.
     */
    public Player runGame(boolean pvp) {
        StatusData varsData = new StatusData(basicOperationsData.getBasicOperations().getVars());


        // if winFlag is true, it means the winner appears and the while loop exits.
        while (!varsData.getStatus().isWinFlag()) {
            // cardToPlay is the card that the player wants to play.
            Card cardToPlay = eachRound.createNullCard();

            // get cards player can play considering special cases of function cards
            ArrayList<Card> currentCardsPlayerCanPlay = eachRound.beginStage();

            // If no cards can play, draw a card, otherwise play a card. If the player type three times
            // wrong card to play, the player will be punished to draw a card automatically.
            if (pvp) {
                cardToPlay = eachRound.playStage(currentCardsPlayerCanPlay, cardToPlay);
            } else {
                try {
                    cardToPlay = eachRound.playStageForComputer(currentCardsPlayerCanPlay, cardToPlay);
                } catch (Exception ignored){} // wait one sec here
            }

            if (pvp) {
                eachRound.endStage(cardToPlay);
            } else {
                if (varsData.getStatus().getCurrentPlayerIndex() != 0) {
                    try {
                        eachRound.endStageForComputer(cardToPlay);
                    } catch (Exception ignored){} // wait two sec here
                } else {
                    eachRound.endStage(cardToPlay);
                }
            }

            // Move to the next player
            varsData.getStatus().setCurrentPlayerIndex(
                    basicOperationsData.getBasicOperations().getVars().moveToNextPlayer(
                            varsData.getStatus().isReverse()));
        }
        JOptionPane.showMessageDialog(null, varsData.getStatus().getPlayerWins().getId() + " wins!");
        return varsData.getStatus().getPlayerWins();
    }

    public void setBasicOperationsData(BasicOperationsData basicOperationsData) {
        this.basicOperationsData = basicOperationsData;
    }

    public void setEachRound(EachRound eachRound) {
        this.eachRound = eachRound;
    }
    
    public EachRound getEachRound() {return eachRound;}

    public BasicOperationsData getBasicOperationsData(){return basicOperationsData;}

    public void setUI(DisplayCard ui) {
        eachRound.ui = ui;
    }
}

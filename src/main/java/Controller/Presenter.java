package Controller;

import Entity.CardHolder;

import UseCase.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Terminal used by dealer.
 * As dealer is in use case level,
 * we put this terminal in entity level for clean architecture.
 */
public class Presenter implements IPresenter {

    private GameRequest gameRequest;
    private GameRunner gameRunner;
    /**
     * setter method for gameRunner
     */
    public void setGameRunner(GameRunner gameRunner) {this.gameRunner = gameRunner;}
    /**
     * setter method for gameRequest
     */
    public void setGameRequest(GameRequest gameRequest) {
        this.gameRequest = gameRequest;
    }
    /**
     * getter method for gameRunner
     */
    public GameRunner getGameRunner(){return this.gameRunner;}
    /**
     * getter method for gameRequest
     */
    public GameRequest getGameRequest() {
        return gameRequest;
    }

    public CardHolder allhandcards(){

        return gameRunner.getGameResponse().getGameBoard().getGameCardHolders().getHandCards(gameRunner.getGameResponse().
                getGameBoard().getStatus().getCurrentPlayerIndex());

    }
    public CardHolder allhandcards(int id){
        return gameRunner.getGameResponse().getGameBoard().getGameCardHolders().getHandCards(id);
    }

    public void drawManyCard(int numToDraw, StringBuilder drawnCardName, boolean computer) {
        System.out.println(gameRunner.getGameResponse().getIds().get(
                gameRunner.getGameResponse().getGameBoard().getStatus().getCurrentPlayerIndex()) +
                " draw " + numToDraw + " cards.");
        if (!computer){
        JOptionPane.showMessageDialog(null, "You draw " + numToDraw +
                " cards. The cards you've drawn are " +
                drawnCardName + ".");}
    }
    /**
     * notification when draw card
     */
    public void drawCardNotification(String drawn, boolean noCard, boolean computer){
        // only print this sentence when drawing due to no card playable
        if (noCard) {
            if (!computer) {
                JOptionPane.showMessageDialog(null, "Cannot play a card! Draw one more card \n The card you draw is " + drawn, "Draw One Card", JOptionPane.PLAIN_MESSAGE);
            }
        }
        else {
            if (!computer) {
                JOptionPane.showMessageDialog(null, "The card you draw is " + drawn, "Draw One Card", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
    /**
     * the last card played
     */
    public void lastcard(String cardname){

        System.out.println(gameRunner.getGameResponse().getIds().get(gameRunner.getGameResponse().getGameBoard().getStatus().getCurrentPlayerIndex()) + " played " + cardname + ".");
    }
    /**
     * set  color for gui
     */

    public void setColorGUI() {
        ArrayList<String> colors = new ArrayList<>();
        Collections.addAll(colors, "red", "blue", "yellow", "green");

        String setColor = (String) JOptionPane.showInputDialog(null, "Choose color", "Choose color", JOptionPane.INFORMATION_MESSAGE, null, colors.toArray(), null);
        gameRequest.setSetColor(setColor);
        System.out.println("Color " + setColor + " is set.");
        JOptionPane.showMessageDialog(null, "Color " + setColor + " is set.");
    }
    /**
     * set color for gui in pve mode
     */
    public void setColorForComputer(String color) {
        System.out.println("Color " + color + " is set.");
        JOptionPane.showMessageDialog(null, gameRunner.getGameResponse().getIds().get(
                gameRunner.getGameResponse().getGameBoard().getStatus().getCurrentPlayerIndex()) +" switch color. Color " + color + " is set.");
    }
    /**
     * return the remaining card in deck
     */

    public String RemainingCards() {
        return String.valueOf(gameRunner.getGameResponse().getGameBoard().getDeck().getUnusedCardDeck().size());
    }

}

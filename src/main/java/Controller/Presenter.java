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

    private IRequest gameRequest; //The request by player

    private Controller controller; // The controller of the class

    /**
     * setter method for gameRequest
     */
    public void setGameRequest(IRequest gameRequest) {
        this.gameRequest = gameRequest;
    }

    /**
     * getter method for gameRunner
     * @return GameRunner of the game
     */
    public GameRunner getGameRunner(){return controller.getGameRunner();}

    /**
     * For getting all hand cards of the current player
     * @return A CardHolder that contains all cards the player has
     */
    public CardHolder allhandcards(){

        return controller.getGameRunner().getEachRound().getGameBoard().getGameCardHolders().
                getHandCards(controller.getGameRunner().getEachRound()
                        .getGameBoard().getGameStatus().getCurrentPlayerIndex());

    }

    /**
     * For getting all hand cards of a specific player
     * @param id the index of the specific player
     * @return A CardHolder that contains all cards the player has
     */
    public CardHolder allhandcards(int id){
        return controller.getGameRunner().getEachRound().getGameBoard().getGameCardHolders().getHandCards(id);
    }

    /**
     * Print the message for the cards that the player draw
     * @param numToDraw the number of card player needs to draw
     * @param drawnCardName the name of all cards the player draw
     * @param computer Check whether it is computer that draw the card
     */
    public void drawManyCard(int numToDraw, StringBuilder drawnCardName, boolean computer) {
        System.out.println(controller.getGameRunner().getGameResponse().getIds().get(
                controller.getGameRunner().getEachRound().getGameBoard().getGameStatus().getCurrentPlayerIndex()) +
                " draw " + numToDraw + " cards.");
        if (!computer){
        JOptionPane.showMessageDialog(null, "You draw " + numToDraw +
                " cards. The cards you've drawn are " +
                drawnCardName + ".");}
    }


    /**
     * notification when draw card
     * @param drawn string of drawn card
     * @param noCard true for no card false for has playable card
     * @param computer ture for computer player false for not computer
     */
    public void drawCardNotification(String drawn, boolean noCard, boolean computer){
        // only print this sentence when drawing due to no card playable
        if (noCard) {
            if (!computer) {
                JOptionPane.showMessageDialog(null,
                        "Cannot play a card! Draw one more card \n The card you draw is " +
                                drawn, "Draw One Card", JOptionPane.PLAIN_MESSAGE);
            }
        }
        else {
            if (!computer) {
                JOptionPane.showMessageDialog(null, "The card you draw is "
                        + drawn, "Draw One Card", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    /**
     * Print the card that was last played by player or computer
     */
    public void lastcard(String cardname){

        System.out.println(controller.getGameRunner().getGameResponse().getIds().get(
                controller.getGameRunner().getEachRound().getGameBoard().getGameStatus().
                        getCurrentPlayerIndex()) + " played " + cardname + ".");
    }

    /**
     * Message when a colour change by player
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
     * Message when a colour change by Computer
     */
    public void setColorForComputer(String color) {
        System.out.println("Color " + color + " is set.");
        JOptionPane.showMessageDialog(null, controller.getGameRunner().
                getGameResponse().getIds().get(controller.getGameRunner().getEachRound().
                        getGameBoard().getGameStatus().getCurrentPlayerIndex())
                +" switch color. Color " + color + " is set.");
    }

    /**
     * Print the message of the remaining cards in the unused deck
     * @return the number of cards in unused card deck
     */
    public String RemainingCards() {
        return String.valueOf(controller.getGameRunner().getEachRound().getGameBoard().
                getGameDeck().getUnusedCardDeck().size());
    }

    /**
     * Setter method for Controller
     * @param controller The controller need to be set
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }
}

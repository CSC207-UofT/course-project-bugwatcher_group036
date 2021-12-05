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

    private IRequest gameRequest;
//    private GameRunner gameRunner;
//    private GameResponse gameResponse;
    private Controller controller;

//    /**
//     * setter method for gameRunner
//     */
//    public void setGameRunner(GameRunner gameRunner) {this.gameRunner = gameRunner;}

    @Override
    public void setGameRequest(GameRequest gameRequest) {

    }

    /**
     * setter method for gameRequest
     */
    public void setGameRequest(IRequest gameRequest) {
        this.gameRequest = gameRequest;
    }

    /**
     * getter method for gameRunner
     */
    public GameRunner getGameRunner(){return controller.getGameRunner();}

    /**
     * getter method for gameRequest
     */
//    public GameRequest getGameRequest() {
//        return gameRequest;
//    }

    public CardHolder allhandcards(){

        return controller.getGameRunner().getEachRound().getGameBoard().getGameCardHolders().
                getHandCards(controller.getGameRunner().getEachRound()
                        .getGameBoard().getGameStatus().getCurrentPlayerIndex());

    }

    public CardHolder allhandcards(int id){
        return controller.getGameRunner().getEachRound().getGameBoard().getGameCardHolders().getHandCards(id);
    }

    public void drawManyCard(int numToDraw, StringBuilder drawnCardName, boolean computer) {
        System.out.println(controller.getGameRunner().getGameResponse().getIds().get(
                controller.getGameRunner().getEachRound().getGameBoard().getGameStatus().getCurrentPlayerIndex()) +
                " draw " + numToDraw + " cards.");
        if (!computer){
        JOptionPane.showMessageDialog(null, "You draw " + numToDraw +
                " cards. The cards you've drawn are " +
                drawnCardName + ".");}
    }

//    @Override
//    public void setGameResponse(GameResponse gameResponse) {
//        this.gameResponse = gameResponse;
//    }

    /**
     * notification when draw card
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
     * the last card played
     */
    public void lastcard(String cardname){

        System.out.println(controller.getGameRunner().getGameResponse().getIds().get(
                controller.getGameRunner().getEachRound().getGameBoard().getGameStatus().
                        getCurrentPlayerIndex()) + " played " + cardname + ".");
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
        JOptionPane.showMessageDialog(null, controller.getGameRunner().
                getGameResponse().getIds().get(controller.getGameRunner().getEachRound().
                        getGameBoard().getGameStatus().getCurrentPlayerIndex())
                +" switch color. Color " + color + " is set.");
    }

    /**
     * return the remaining card in deck
     */
    public String RemainingCards() {
        return String.valueOf(controller.getGameRunner().getEachRound().getGameBoard().
                getGameDeck().getUnusedCardDeck().size());
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}

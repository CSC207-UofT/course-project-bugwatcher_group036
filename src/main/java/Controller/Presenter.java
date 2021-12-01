package Controller;

import Entity.CardHolder;

import UI.WinFrame;
import UseCase.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Terminal used by dealer.
 * As dealer is in use case level,
 * we put this terminal in entity level for clean architecture.
 */
public class Presenter implements UseCase.IPresenter {

    private Controller controller;
    private GameResponse gameResponse;
    private GameRequest gameRequest;

    public void setGameRequest(GameRequest gameRequest) {
        this.gameRequest = gameRequest;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setGameResponse(GameResponse gameResponse) {
        this.gameResponse = gameResponse;
    }

    public GameResponse getGameResponse() {
        return gameResponse;
    }

    public void beginStage(){
        System.out.println();
        System.out.println("Current Player:" + gameResponse.getIds().get(
                gameResponse.getGameBoard().getGameStatus().getCurrentPlayerIndex()));
    }

    public void playStage(){
        GameBoard gameBoard = gameResponse.getGameBoard();
        CardHolder playableCards = gameResponse.getCardHolder();

        int currentPlayerIndex = gameBoard.getGameStatus().getCurrentPlayerIndex();
        System.out.println("Last Card: " + gameBoard.getCardChecker().getLastCard());
        System.out.println(
                "The cards you have: " + gameBoard.getGameCardHolders().getHandCards(currentPlayerIndex));
        System.out.println("The cards you can play: " + playableCards);
    }

    public CardHolder allhandcards(){
        return gameResponse.getGameBoard().getGameCardHolders().getHandCards(gameResponse.
                getGameBoard().getGameStatus().getCurrentPlayerIndex());
    }

    public void getCardToPlay(){
        System.out.println("play a card, type \"draw\" to draw a card, or type \"quit\" to leave:");
        controller.getCardToPlay();
    }

    public void printString(String message){
        System.out.println(message); // print sentence message
    }

    public void drawManyCard(int numToDraw, StringBuilder drawnCardName) {
        System.out.println("You draw " + numToDraw + " cards. The cards you've drawn are " +
                drawnCardName + ".");
        JOptionPane.showMessageDialog(null, "You draw " + numToDraw +
                " cards. The cards you've drawn are " +
                drawnCardName + ".");
    }

    public void drawCardNotification(String drawn, boolean noCard){
        // only print this sentence when drawing due to no card playable
        if (noCard) {System.out.println("Cannot play a card! Draw one more card");
            System.out.println("The card you draw is " + drawn);
            JOptionPane.showMessageDialog(null,"Cannot play a card! Draw one more card \n The card you draw is " + drawn, "Draw Multiple Cards", JOptionPane.PLAIN_MESSAGE);
        }
        else {
            System.out.println("The card you draw is " + drawn);
            JOptionPane.showMessageDialog(null,"The card you draw is " + drawn, "Draw 1 Card", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void setColor() {
        System.out.println("Type a color you want to set:");
        controller.typeSetColor();
    }
    public void setColorGUI() {
        System.out.println("Type a color you want to set:");
        ArrayList<String> colors = new ArrayList<>();
        Collections.addAll(colors, "red", "blue", "yellow", "green");

        String setColor = (String) JOptionPane.showInputDialog(null, "Choose color", "Choose color", JOptionPane.INFORMATION_MESSAGE, null, colors.toArray(), null);
        controller.typeSetColorGUI(setColor);
    }

    public void setColorForComputer() {
        controller.typeSetColorForComputer();
    }

    public void wrongColor() {
        System.out.println("Wrong color! Type again:");
    }
    public void wrongThreeTimes() {
        System.out.println("Wrong color 3 times! Color randomly chosen");
    }

    public void colorIsSet(String color) {
        System.out.println("Color " + color + " is set.");
        JOptionPane.showMessageDialog(null, "Color " + color + " is set.");
    }
    public void inputIDs() {
        controller.inputIDs();
    }

    public void inputIDsForComputer() {
        controller.inputIDsForComputer();
    }

    public void inputIDsGUI(boolean computer, ArrayList<String> ids) {
        controller.inputIDsGUI(computer, ids);
    }

    public void howManyPlayersGUI(boolean computer) {
        System.out.println("How many players here? ");
//        if (computer) {
//            NumofPlayerComputerFrame frame = new NumofPlayerComputerFrame(this, controller, gameRequest, gameResponse);
//        }
//        else{
//            NumofPlayerFrame frame = new NumofPlayerFrame(this, controller, gameRequest, gameResponse);
//
//        }
    }
    public void howManyPlayers() {
        System.out.println("How many players here? ");
    }

    public void oneToSix() {
        System.out.println("Sorry, we only support 1 player - 6 players, please re-enter player count.");
    }

    public void enterIDCom() {
        System.out.println("Please enter your player id:");
    }

    public void enterIDP(int i) {
        System.out.println("Please enter id for player" + (i + 1));
    }

    public String PlayerID() {
        return gameResponse.getIds().get(
                gameResponse.getGameBoard().getGameStatus().getCurrentPlayerIndex());
    }

    public String RemainingCards() {
        return String.valueOf(gameResponse.getGameBoard().getDeck().getUnusedCardDeck().size());
    }

    public void WinFrame() {
        WinFrame frame = new WinFrame();
    }
}

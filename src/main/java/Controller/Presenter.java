package Controller;

import Entity.CardHolder;

import LogIn.LogInEntity.User;
import LogIn.LoginUseCase.LoginUseCase;
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
    private GameRequest gameRequest;

    private GameRunner gameRunner;

    public void setGameRunner(GameRunner gameRunner) {this.gameRunner = gameRunner;}


    public void setGameRequest(GameRequest gameRequest) {
        this.gameRequest = gameRequest;
    }


    public GameRunner getGameRunner(){return this.gameRunner;}


    public GameRequest getGameRequest() {
        return gameRequest;
    }

    public void beginStage(){
        System.out.println();

        System.out.println("Current Player:" + gameRunner.getGameResponse().getIds().get(
                gameRunner.getGameResponse().getGameBoard().getStatus().getCurrentPlayerIndex()));

    }

    public void playStage(){
        GameBoard gameBoard = gameRunner.getGameResponse().getGameBoard();
        CardHolder playableCards = gameRunner.getGameResponse().getCardHolder();

        int currentPlayerIndex = gameBoard.getStatus().getCurrentPlayerIndex();
        System.out.println("Last Card: " + gameBoard.getCardChecker().getLastCard());
        System.out.println(
                "The cards you have: " + gameBoard.getGameCardHolders().getHandCards(currentPlayerIndex));
        System.out.println("The cards you can play: " + playableCards);
    }

    public CardHolder allhandcards(){

        return gameRunner.getGameResponse().getGameBoard().getGameCardHolders().getHandCards(gameRunner.getGameResponse().
                getGameBoard().getStatus().getCurrentPlayerIndex());

    }
    public CardHolder allhandcards(int id){
        return gameRunner.getGameResponse().getGameBoard().getGameCardHolders().getHandCards(id);
    }

    public void printString(String message){
        System.out.println(message); // print sentence message
    }

    public void drawManyCard(int numToDraw, StringBuilder drawnCardName, boolean computer) {
        System.out.println("You draw " + numToDraw + " cards. The cards you've drawn are " +
                drawnCardName + ".");
        if (!computer){
        JOptionPane.showMessageDialog(null, "You draw " + numToDraw +
                " cards. The cards you've drawn are " +
                drawnCardName + ".");}
    }

    public void drawCardNotification(String drawn, boolean noCard, boolean computer){
        // only print this sentence when drawing due to no card playable
        if (noCard) {
            System.out.println("Cannot play a card! Draw one more card");
            System.out.println("The card you draw is " + drawn);
            if (!computer) {
                JOptionPane.showMessageDialog(null, "Cannot play a card! Draw one more card \n The card you draw is " + drawn, "Draw One Card", JOptionPane.PLAIN_MESSAGE);
            }
        }
        else {
            System.out.println("The card you draw is " + drawn);
            if (!computer) {
                JOptionPane.showMessageDialog(null, "The card you draw is " + drawn, "Draw One Card", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    public void setColorGUI() {
        System.out.println("Type a color you want to set:");
        ArrayList<String> colors = new ArrayList<>();
        Collections.addAll(colors, "red", "blue", "yellow", "green");

        String setColor = (String) JOptionPane.showInputDialog(null, "Choose color", "Choose color", JOptionPane.INFORMATION_MESSAGE, null, colors.toArray(), null);
        gameRequest.setSetColor(setColor);
        System.out.println("Color " + setColor + " is set.");
        JOptionPane.showMessageDialog(null, "Color " + setColor + " is set.");
    }

    public void setColorForComputer(String color) {
        System.out.println("Color " + color + " is set.");
        JOptionPane.showMessageDialog(null, "Player: "+ gameRunner.getGameResponse().getIds().get(
                gameRunner.getGameResponse().getGameBoard().getStatus().getCurrentPlayerIndex()) +" switch color. Color " + color + " is set.");
    }

    public String RemainingCards() {
        return String.valueOf(gameRunner.getGameResponse().getGameBoard().getDeck().getUnusedCardDeck().size());
    }

}

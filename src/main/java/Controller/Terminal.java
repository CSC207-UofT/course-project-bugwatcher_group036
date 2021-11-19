package Controller;

import UseCase.CardChecker;
import Entity.HandCard;
import UseCase.GameBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Terminal used by dealer.
 * As dealer is in use case level,
 * we put this terminal in entity level for clean architecture.
 */
public class Terminal implements ITerminal {

    private Scanner input = new Scanner(System.in);

    public void beginStage(int currentPlayerIndex, ArrayList<String> ids){
        System.out.println();
        System.out.println("Current Player:" + ids.get(currentPlayerIndex));
    }

    public void playStage(GameBoard gameBoard, CardChecker cardChecker, HandCard playableCards){
        int currentPlayerIndex = gameBoard.getStatus().getCurrentPlayerIndex();
        System.out.println("Last Card: " + cardChecker.getLastCard());
        System.out.println("The cards you have: " + gameBoard.getHandCards(currentPlayerIndex));
        System.out.println("The cards you can play: " + playableCards);
    }

    public String getCardToPlay(){
        System.out.println("play a card, type \"draw\" to draw a card, or type \"quit\" to leave:");
        return input.nextLine();
    }

    public void printString(String message){
        System.out.println(message); // print sentence message
    }

    public void drawCardNotification(String drawn, boolean noCard){
        // only print this sentence when drawing due to no card playable
        if (noCard) System.out.println("Cannot play a card! Draw one more card");
        System.out.println("The card you draw is " + drawn);
    }

    public String typeSetColor() {
        ArrayList<String> colors = new ArrayList<>();
        Collections.addAll(colors, "red", "blue", "yellow", "green");

        System.out.println("Type a color you want to set:");
        String setColor = input.nextLine();
        int wrongTimeCounter = 0;

        while (wrongTimeCounter < 3){
            if (!colors.contains(setColor) && wrongTimeCounter < 2) {
                System.out.println("Wrong color! Type again:");
                setColor = input.nextLine();
            }
            else if (!colors.contains(setColor) && wrongTimeCounter == 2) {
                System.out.println("Wrong color 3 times! Color randomly chosen");
                setColor = colors.get((int)(Math.random() * colors.size()));
            } else {
                break;
            }
            wrongTimeCounter++;
        }
        System.out.println("Color " + setColor + " is set.");
        return setColor;
    }
}

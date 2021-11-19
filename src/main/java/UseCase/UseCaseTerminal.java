package UseCase;

import Entity.CardChecker;
import Entity.HandCard;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Terminal used by eachRound.
 * As eachRound is in controller level,
 * this terminal is in UseCaseLevel for clean architecture.
 */
public class UseCaseTerminal {

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
}

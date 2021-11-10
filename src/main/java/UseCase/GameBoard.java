package UseCase;

import Entity.Card;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Gameboard stores playCount and controls current position, color, and lastCard played.
 * This will be embedded in the controller.
 */
public class GameBoard {

    private final int numberOfPlayers;
    private String color;
    private int currentPlayer;
    private Card lastCard;
    private final Scanner keyBoard;
    private final ArrayList<String> colors = new ArrayList<String>();

    public GameBoard(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
        this.color = "black";
        this.currentPlayer = (int)(Math.random() * numberOfPlayers);
        this.lastCard = new Card();
        this.keyBoard = new Scanner(System.in);
        colors.add("red"); colors.add("blue"); colors.add("green"); colors.add("yellow");
    }

    public String getColor() {
        return color;
    }

    public void setColor(){
        this.color = color;
    }

    /**
     * Set color based on scanner input.
     */
    public void typeSetColor() {
        System.out.println("Type a color you want to set:");
        String setColor = keyBoard.nextLine();
        int wrongTimeCounter = 0;

        while (wrongTimeCounter < 3){

            if (!colors.contains(setColor) && wrongTimeCounter < 2) {
                System.out.println("Wrong color! Type again:");
                setColor = keyBoard.nextLine();
            }
            else if (!colors.contains(setColor) && wrongTimeCounter == 2) {
                System.out.println("Wrong color 3 times! Color randomly chosen");
                setColor = this.getRandColor();
            } else {
                break;
            }

            wrongTimeCounter++;
        }
        this.color = setColor;
    }

    public String getRandColor(){
        return colors.get((int)(Math.random() * colors.size()));
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public Card getLastCard() {
        return lastCard;
    }

    public void setLastCard(Card lastCard) {
        this.lastCard = lastCard;
    }
}

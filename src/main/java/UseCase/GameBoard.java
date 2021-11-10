package UseCase;

import Entity.Card;

import java.util.ArrayList;
import java.util.Scanner;

public class GameBoard {

    private int numberOfPlayers;
    private String color;
    private int currentPlayer;
    private Card lastCard;
    private Scanner keyBoard;
    private final ArrayList<String> colors = new ArrayList<String>();

    public GameBoard(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
        this.color = "black";
        this.currentPlayer = (int)(Math.random() * numberOfPlayers);
        this.lastCard = new Card();
        this.keyBoard = new Scanner(System.in);
        colors.add("red"); colors.add("blue"); colors.add("green"); colors.add("yellow");
    }

    public int moveToNextPlayer(boolean reverse) {
        // Move to the next player
        if (!reverse){
            currentPlayer++;
            if (currentPlayer == numberOfPlayers) {
                currentPlayer = 0;
            }
        } else {
            currentPlayer--;
            if (currentPlayer == -1) {
                currentPlayer = numberOfPlayers - 1;
            }
        }
        return currentPlayer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(){
        this.color = color;
    }

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

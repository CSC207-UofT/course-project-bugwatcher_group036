package UseCase;

import Entity.Card;
import UI.UI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
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
    private DeckManager deckManager;
    private UI ui;

    public GameBoard(int numberOfPlayers, DeckManager deckManager){
        this.numberOfPlayers = numberOfPlayers;
        this.color = "black";
        this.currentPlayer = (int)(Math.random() * numberOfPlayers);
        this.lastCard = deckManager.createNullCard();
        this.keyBoard = new Scanner(System.in);
        colors.add("red"); colors.add("blue"); colors.add("green"); colors.add("yellow");
        this.deckManager = deckManager;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color){
        this.color = color;
    }

    /**
     * Set color based on scanner input.
     */
    public void typeSetColor() {
        System.out.println("Type a color you want to set:");
//        String setColor = keyBoard.nextLine();
        String setColor;
            // show the dialog let player choose
        UIManager.put("OptionPane.okButtonText", "Choose color");
        setColor = (String) JOptionPane.showInputDialog(null, "Choose color", "Choose color", JOptionPane.INFORMATION_MESSAGE, null, colors.toArray(), null);


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
        System.out.println("Color " + setColor + "is set,");
        UIManager.put("OptionPane.okButtonText", "next");
        JOptionPane.showMessageDialog(null, "Color " + setColor + " is set.");

        setUI(ui);

        System.out.println("Color " + setColor + " is set.");
    }

    public void typeSetColorForComputer() {
        Random rand = new Random();
        int indx = rand.nextInt(4);
        ArrayList<String> co = new ArrayList<>();
        Collections.addAll(co, "red", "green", "blue", "yellow");
        String setColor = co.get(indx);
        this.color = setColor;
        System.out.println("Color " + setColor + " is set.");
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
    public void setUI(UI ui) {
        this.ui = ui;
    }
}

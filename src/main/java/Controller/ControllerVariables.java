package Controller;

import Entity.Player;

import java.util.Random;
import java.util.Scanner;

/**
 * This is the class that helps initialize and store all the variables in controller.
 *
 * With the help of this class, we can extract methods from controller easier and
 * also save the space of initializing and storing variables locally.
 */
public class ControllerVariables {

    private Scanner keyboard = new Scanner(System.in);

    // The winner player
    private Player playerWins = null;

    // whether reverse
    private boolean reverse = false;

    // winFlag is used to indicate whether a winner appears.
    private boolean winFlag = false;

    // Randomly select a player to play the first card.
    private final Random rand = new Random();
    private int currentPlayerIndex = -1;

    // whether the turn of player should be skipped
    private boolean skip = false;

    //whether there are cards that needed to drawed for the player
    private int plus = 0;

    public ControllerVariables(int numberOfPlayers){
        this.currentPlayerIndex = rand.nextInt(numberOfPlayers);
    }

    public Scanner getKeyboard() {
        return keyboard;
    }

    public Player getPlayerWins() {
        return playerWins;
    }

    public void setPlayerWins(Player playerWins) {
        this.playerWins = playerWins;
    }

    public boolean isReverse() {
        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public boolean isWinFlag() {
        return winFlag;
    }

    public void setWinFlag(boolean winFlag) {
        this.winFlag = winFlag;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        if (currentPlayerIndex <= 4) {
            this.currentPlayerIndex = currentPlayerIndex;
        }
        else {
            this.currentPlayerIndex = currentPlayerIndex % 4;
        }
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public int getPlus() {
        return plus;
    }

    public void setPlus(int plus) {
        this.plus = plus;
    }
}

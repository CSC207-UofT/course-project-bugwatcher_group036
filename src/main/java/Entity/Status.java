package Entity;

import java.util.Random;

/**
 * This class contains the variables used in each round of the game and methods that
 * are in charge of setting and changing these variables.
 */
public class Status {
    // The order of the game.
    private boolean reverse = false;
    // WinFlag is used to indicate whether a winner appears.
    private boolean winFlag = false;
    // The index or position of the player for a certain turn.
    private int currentPlayerIndex;
    // Whether the current player need to skip the turn.
    private boolean skip = false;
    // How many card the current player need to draw.
    private int plus = 0;
    // Number of players.
    private final int numberOfPlayers;

    /**
     * Construct the status of a new game with a random player started.
     *
     * @param numberOfPlayers Number of players.

     * Randomly select a player to play the first card.
     */
    public Status(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        // Randomly select a player to play the first card.
        Random rand = new Random();
        this.currentPlayerIndex = rand.nextInt(numberOfPlayers);
    }
    /**
     * change the order of the game
     */

    public void changeReverseState() {
        this.reverse = !this.reverse;
    }

    /**
     * return winFlag
     */
    public boolean isWinFlag() {
        return winFlag;
    }

    /**
     * setter method for winFlag
     */
    public void setWinFlag(boolean winFlag) {
        this.winFlag = winFlag;
    }

    /**
     * change the state to quit
     */
    public void setQuit() {
        winFlag = true;
        currentPlayerIndex = -10;
    }

    /**
     * getter method for  currentPlayerIndex
     * @return  currentPlayerIndex
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * setter method for currentPlayerIndex
     * @param currentPlayerIndex index of current player
     */
    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    /**
     * return skip
     */
    public boolean isSkip() {
        return skip;
    }

    /**
     * setter method for skip
     * true for skip next player false for not
     */
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    /**
     * getter method for plus
     * @return the number of plus
     */
    public int getPlus() {
        return plus;
    }

    /**
     * setter method for plus
     * @param plus number of plus
     */
    public void setPlus(int plus) {
        this.plus = plus;
    }

    /**
     * response in game while play a function card
     * @param  feature the feature of each card
     */
    public void functionCardResponse(String feature){
        switch (feature) {
            case "skip":
                setSkip(true);
                break;
            case "reverse":
                changeReverseState();
                break;
            case "+2":
                setPlus(plus + 2);
                break;
            case "+4":
                setPlus(plus + 4);
        }
    }

    /**
     * move current played player to next player by index
     * @return index of current player
     */
    public int moveToNextPlayer() {
        // Move to the next player
        if (!reverse){
            currentPlayerIndex++;
            if (currentPlayerIndex == numberOfPlayers) {
                currentPlayerIndex = 0;
            }
        } else {
            currentPlayerIndex--;
            if (currentPlayerIndex == -1) {
                currentPlayerIndex = numberOfPlayers - 1;
            }
        }
        return currentPlayerIndex;
    }
}

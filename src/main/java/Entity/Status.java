package Entity;

import java.util.Random;

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
     */

    public Status(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        // Randomly select a player to play the first card.
        Random rand = new Random();
        this.currentPlayerIndex = rand.nextInt(numberOfPlayers);
    }

    public void changeReverseState() {
        this.reverse = !this.reverse;
    }

    public boolean isWinFlag() {
        return winFlag;
    }

    public void setWinFlag(boolean winFlag) {
        this.winFlag = winFlag;
    }

    public void setQuit() {
        winFlag = true;
        currentPlayerIndex = -10;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
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

package UseCase;

import Entity.Player;

import java.util.Random;

public class Status {

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

    private final int numberOfPlayers;

    public Status(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
        this.currentPlayerIndex = rand.nextInt(numberOfPlayers);
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
        this.currentPlayerIndex = currentPlayerIndex;
    }

    /**
     * Extracted from controller, which control position change after each turn.
     * @param reverse whether reverse sequence or not
     * @return next corresponding position
     */
    public int moveToNextPlayer(boolean reverse) {
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

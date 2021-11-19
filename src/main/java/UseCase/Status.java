package UseCase;

import Entity.Player;

import java.util.Random;

/**
 *This class manages the status of a game, status contains six main attributes:
 * 1.Whether a winner appears.
 * 2.The current player.
 * 3.Whether the current player need to skip the turn.
 * 4.How many card the current player need to draw.
 * 5.Order of the game.
 * 6.Number of players.
 *Also, it contains two ability:
 * 1.Construct the status of a new game with a random player started.
 * 2.Control the next player by the order of the game.
 */
public class Status {
    // Winner.
    private Player Winner = null;
    // The order of the game.
    private boolean reverse = false;
    // WinFlag is used to indicate whether a winner appears.
    private boolean winFlag = false;
    // Randomly select a player to play the first card.
    private final Random rand = new Random();
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
    public Status(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
        this.currentPlayerIndex = rand.nextInt(numberOfPlayers);
    }

    /**
     *
     * @return Whether a winner appears. If no, return "null".
     */
    public Player getPlayerWins() {
        return Winner;
    }

    /**
     *
     * @param winner Set a winner.
     */
    public void setPlayerWins(Player winner) {
        this.Winner = winner;
    }

    /**
     *
     * @return Order of the game.
     */
    public boolean isReverse() {
        return reverse;
    }

    /**
     *
     * @param reverse Set the order of the game.
     */
    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    /**
     *
     * @return Whether a winner appears.
     */
    public boolean isWinFlag() {
        return winFlag;
    }

    /**
     *
     * @param winFlag Set whether a winner appears.
     */
    public void setWinFlag(boolean winFlag) {
        this.winFlag = winFlag;
    }

    /**
     *
     * @return Current player.
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     *
     * @param currentPlayerIndex Set the current player.
     */
    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    /**
     * Extracted from controller, which control the next player by the order of the game.
     * @param reverse Order of the game.
     *
     * @return The next player.
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

    /**
     *
     * @return Whether the current player need to skip the turn.
     */
    public boolean isSkip() {
        return skip;
    }

    /**
     *
     * @param skip Set whether the current player need to skip the turn.
     */
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    /**
     *
     * @return How many card the current player need to draw.
     */
    public int getPlus() {
        return plus;
    }

    /**
     *
     * @param plus Set how many card the current player need to draw.
     */
    public void setPlus(int plus) {
        this.plus = plus;
    }
}


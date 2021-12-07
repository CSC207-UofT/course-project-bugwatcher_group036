package UseCase;

import Entity.Status;
/**
 * The GameStatus.
 */
public class GameStatus {

    private final Status status;

    /**
     * Initialize GameStatus
     * @param numberOfPlayers number of player
     */
    public GameStatus(int numberOfPlayers) {
        this.status = new Status(numberOfPlayers);
    }

    /**
     * @return whether the game status is win
     */
    public boolean isWinFlag() {
        return status.isWinFlag();
    }

    /**
     * setter method for winFlag
     * @param winFlag Set the status of winflag
     */
    public void setWinFlag(boolean winFlag) {
        status.setWinFlag(winFlag);
    }

    /**
     * getter method for  currentPlayerIndex
     * @return  the index of current players
     */
    public int getCurrentPlayerIndex() {
        return status.getCurrentPlayerIndex();
    }

    /**
     * setter method for currentPlayerIndex
     * @param currentPlayerIndex index of current player
     */
    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        status.setCurrentPlayerIndex(currentPlayerIndex);
    }
    /**
     * To check whether the player is skipped
     * @return true if and only if the player is skipped
     */
    public boolean isSkip() {
        return status.isSkip();
    }

    /**
     * setter method for skip
     * @param skip whether the player is skip.
     */
    public void setSkip(boolean skip) {
        status.setSkip(skip);
    }

    /**
     * getter method for plus
     * @return the number of plus card for next player
     */
    public int getPlus() {
        return status.getPlus();
    }

    /**
     * setter method for plus card
     * @param plus the number of plus card for next player
     */
    public void setPlus(int plus) {
        status.setPlus(plus);
    }


    /**
     * The game response when a function card is played
     * @param feature the feature of each card
     */
    public void functionCardResponse(String feature){
        status.functionCardResponse(feature);
    }

    /**
     * move current played player to next player by index
     */
    public int moveToNextPlayer() {
        return status.moveToNextPlayer();
    }

    /**
     * change the value of twoDecksBothRunOut, i.e., true becomes false or false becomes true.
     */
    public void setTwoDecksRunOut(boolean b) {
        status.setTwoDecksRunOut(b);
    }

    /**
     * @return the value of twoDecksBothRunOut
     */
    public boolean isTwoDecksBothRunOut() {
        return status.isTwoDecksBothRunOut();
    }
}

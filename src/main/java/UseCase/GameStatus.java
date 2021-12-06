package UseCase;

import Entity.Status;

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
     * return winFlag
     */
    public boolean isWinFlag() {
        return status.isWinFlag();
    }

    /**
     * setter method for winFlag
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
     * return skip
     */
    public boolean isSkip() {
        return status.isSkip();
    }

    /**
     * setter method for skip
     * @para True for skip Flase for not
     */
    public void setSkip(boolean skip) {
        status.setSkip(skip);
    }

    /**
     * getter method for plus
     * @return the number of plus
     */
    public int getPlus() {
        return status.getPlus();
    }

    /**
     * setter method for plus
     * @param plus number of plus
     */
    public void setPlus(int plus) {
        status.setPlus(plus);
    }


    /**
     * response in game while play a function card
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
}

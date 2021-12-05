package UseCase;

import Entity.Status;

public class GameStatus {

    private final Status status;

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
     */
    public int getCurrentPlayerIndex() {
        return status.getCurrentPlayerIndex();
    }

    /**
     * setter method for currentPlayerIndex
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
     */
    public void setSkip(boolean skip) {
        status.setSkip(skip);
    }

    /**
     * getter method for plus
     */
    public int getPlus() {
        return status.getPlus();
    }

    /**
     * setter method for plus
     */
    public void setPlus(int plus) {
        status.setPlus(plus);
    }


    /**
     * response in game while play a function card
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

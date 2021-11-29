package UseCase;

import Entity.CardHolder;
import Entity.Status;

public class GameStatus {

    private final Status status;
    // The hand cards of the players with index is the corresponding position.

    public GameStatus() {this.status = new Status();}

    public GameStatus(int numberOfPlayers){
        this.status = new Status(numberOfPlayers);
    }

    public Status getStatus() {
        return status;
    }

    public boolean isWinFlag() {
        return status.isWinFlag();
    }

    public void setWinFlag(boolean winFlag) {
        status.setWinFlag(winFlag);
    }

    public void setQuit() {
        status.setQuit();
    }

    public int getCurrentPlayerIndex() {
        return status.getCurrentPlayerIndex();
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        status.setCurrentPlayerIndex(currentPlayerIndex);
    }

    public boolean isSkip() {
        return status.isSkip();
    }

    public void setSkip(boolean skip) {
        status.setSkip(skip);
    }

    public int getPlus() {
        return status.getPlus();
    }

    public void setPlus(int plus) {
        status.setPlus(plus);
    }

    public void functionCardResponse(String feature){
        status.functionCardResponse(feature);
    }

    public int moveToNextPlayer() {
        return status.moveToNextPlayer();
    }



}

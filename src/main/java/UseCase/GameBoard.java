package UseCase;

import Entity.HandCard;
import Entity.Status;

public class GameBoard {

    private final Status status;
    private final HandCard[] handCards;

    public GameBoard(int numberOfPlayers){
        this.status = new Status(numberOfPlayers);
        this.handCards = new HandCard[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++){
            handCards[i] = new HandCard();
        }
    }

    public Status getStatus() {
        return status;
    }

    public HandCard getHandCards(int currentPlayerIndex){
        return handCards[currentPlayerIndex];
    }

    public boolean checkWinState(){
        for (HandCard handcard: handCards){
            if (handcard.isEmpty()){
                return true;
            }
        }
        return false;
    }
}

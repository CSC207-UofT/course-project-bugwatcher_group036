package UseCase;

import Entity.CardHolder;

import java.util.HashMap;

public class GameCardHolders {

    private CardHolder[] cardHolders;

    public GameCardHolders(int numberOfPlayers) {
        this.cardHolders = new CardHolder[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++){
            cardHolders[i] = new CardHolder();
        }
    }

    public CardHolder getHandCards(int currentPlayerIndex){
        return cardHolders[currentPlayerIndex];
    }

    // Let player of given index play the given card.
    public boolean playCard(String toPlay, int index){
        return cardHolders[index].playCard(toPlay);
    }

    // Return how many players hold the card.
    public int getHolderNumber() { return cardHolders.length;}

    // Return the number of card a player holds.
    public int getNumbersOfCardHolds(int index) { return cardHolders[index].getSize();}

    // Remove the given card from player of given index.
    public String playCardWithIndex(int toPlay, int index){
        return cardHolders[index].playCardWithIndex(toPlay);
    }

    // add the card to player of given index.
    public void addCard(String card, int index){
        cardHolders[index].addCard(card);
    }

    // check player of given index whether holding hand cards.
    public boolean isEmpty(int index){
        return cardHolders[index].isEmpty();
    }


    public boolean playCard(String toPlay, CardHolder cardHolder){
        return cardHolder.playCard(toPlay);
    }

    public String playCardWithIndex(int toPlay, CardHolder cardHolder){
        return cardHolder.playCardWithIndex(toPlay);
    }

    public void addCard(String card, CardHolder cardHolder){
        cardHolder.addCard(card);
    }

    public boolean isEmpty(CardHolder cardHolder){
        return cardHolder.isEmpty();
    }

    public boolean checkWinState(){
        for (CardHolder handcard: cardHolders){
            if (handcard.isEmpty()){
                return true;
            }
        }
        return false;
    }

    public CardHolder createNewCardHolder() {
        return new CardHolder();
    }

}

package UseCase;

import Entity.CardHolder;

public class GameCardHolders {

    private final CardHolder[] cardHolders;

    public GameCardHolders(int numberOfPlayers) {
        this.cardHolders = new CardHolder[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++){
            cardHolders[i] = new CardHolder();
        }
    }
    /**
     * Getter method for current player HandCard
     */

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

    // add the card to player of given index.
    public void addCard(String card, int index){
        cardHolders[index].addCard(card);
    }

    // check player of given index whether holding hand cards.
    public boolean isEmpty(int index){
        return cardHolders[index].isEmpty();
    }

    /**
     * let a player play card
     */
    public boolean playCard(String toPlay, CardHolder cardHolder){
        return cardHolder.playCard(toPlay);
    }
    /**
     * return the card with given index
     */
    public String playCardWithIndex(int toPlay, CardHolder cardHolder){
        return cardHolder.playCardWithIndex(toPlay);
    }
    /**
     * add a card to a given player
     */
    public void addCard(String card, CardHolder cardHolder){
        cardHolder.addCard(card);
    }

    public boolean isEmpty(CardHolder cardHolder){
        return cardHolder.isEmpty();
    }
    /**
     * check if there is a player win this game
     */
    public boolean checkWinState(){
        for (CardHolder handcard: cardHolders){
            if (handcard.isEmpty()){
                return true;
            }
        }
        return false;
    }
    /**
     * create a new CardHolder
     */
    public CardHolder createNewCardHolder() {
        return new CardHolder();
    }

}

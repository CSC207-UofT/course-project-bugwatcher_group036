package UseCase;

import Entity.CardHolder;

/**
 * The GameCardHolders.
 */
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
     * @param currentPlayerIndex the index of current player
     */
    public CardHolder getHandCards(int currentPlayerIndex){
        return cardHolders[currentPlayerIndex];
    }

    /**
     * Let player of given index play the given card.
     * @param toPlay the string of the card player wants to play
     * @param index the index that the player want to play
     */
    public boolean playCard(String toPlay, int index){
        return cardHolders[index].playCard(toPlay);
    }

    /**
     * getter method for length of cardHolder
     * @return the total number of cardHolders.
     */
    public int getHolderNumber() { return cardHolders.length;}

    /**
     * Return the number of card a player holds.
     * @return how many cards the player hold.
     */
    public int getNumbersOfCardHolds(int index) { return cardHolders[index].getSize();}

    /**
     * add the card to player of given index.
     * @param card string of card
     * @param index number
     */
    public void addCard(String card, int index){
        cardHolders[index].addCard(card);
    }

    /**
     * check player of given index whether holding hand cards.
     * @param index number
     * @return True for have card false for not
     */
    public boolean isEmpty(int index){
        return cardHolders[index].isEmpty();
    }

    /**
     * let a player play card
     * @param toPlay the string of the card player wants to play
     * @param cardHolder cardHolder of this game
     */
    public boolean playCard(String toPlay, CardHolder cardHolder){
        return cardHolder.playCard(toPlay);
    }

    /**
     * return the card with given index
     * @param toPlay the string of the card player wants to play
     * @param cardHolder cardHolder of this game
     */
    public String playCardWithIndex(int toPlay, CardHolder cardHolder){
        return cardHolder.playCardWithIndex(toPlay);
    }

    /**
     * add a card to a given player
     * @param cardHolder cardHolder of this game
     */
    public void addCard(String card, CardHolder cardHolder){
        cardHolder.addCard(card);
    }

    /**
     * check if there is a player win this game
     * @param cardHolder cardHolder of the game
     */
    public boolean isEmpty(CardHolder cardHolder){
        return cardHolder.isEmpty();
    }

    /**
     * check if there is a player win this game
     * @return  true for win false for not
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
     * @return a new CardHolder
     */
    public CardHolder createNewCardHolder() {
        return new CardHolder();
    }

}

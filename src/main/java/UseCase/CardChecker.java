package UseCase;

import Entity.CardHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CardChecker {

    private String lastCard;
    private String currentColor;
    /**
     * initialize CardChecker
     */
    public CardChecker(){
        lastCard = null;
        currentColor = null;
    }

    /**
     * Function card respond for GUI
     * @param feature the feature of each card
     * @param iPresenter the presenter for the card
     * @param gameRequest the gamerequest of the the game
     */
    public void functionCardResponseGUI(String feature, IPresenter iPresenter, GameRequest gameRequest) {
        if (feature.equals("+4") || feature.equals("switch")) { // Check whether the card need to change color
            iPresenter.setColorGUI();
            currentColor = gameRequest.getSetColor();
        }
    }

    /**
     * Function card response in gui in PVE
     * @param feature the feature of each card
     * @param iPresenter the presenter for the card
     * @param gameRequest the gamerequest of the the game
     */

    public void functionCardResponseForComputer(String feature, IPresenter iPresenter, GameRequest gameRequest) {
        if (feature.equals("+4") || feature.equals("switch")) { // Check whether the card need to change color
            ArrayList<String> colors = new ArrayList<>();
            Collections.addAll(colors, "red", "blue", "yellow", "green");
            Random rand = new Random();
            String color = colors.get(rand.nextInt(4));
            gameRequest.setSetColorForComputer(color); // Randomly set color for when computer play change color
            currentColor = gameRequest.getSetColorForComputer();
            iPresenter.setColorForComputer(color); // display the current color to user interface
        }
    }

    /**
     *To return player cards when skip card is played.
     * @param toCheck the function card to check
     * @param gameCardHolders The cards for players that need to be skip
     * @return the cards that the player can play
     */

    public CardHolder skipsPlayerCanPlay(CardHolder toCheck, GameCardHolders gameCardHolders){
        CardHolder skips = gameCardHolders.createNewCardHolder();
        for (String card: toCheck){
            if (card.split(" ")[1].equals("skip")) { // Check whether each card is skip
                gameCardHolders.addCard(card, skips); // Add card to the cardholder when there is a skip card
            }
        }
        return skips;
    }
    /**
     *To return player card when plus two is played.
     * @param toCheck the function card to check
     * @param gameCardHolders The cards for players that need to be add card
     * @return the cards that the player can play
     */

    public CardHolder plusTwoPlayerCanPlay(CardHolder toCheck, GameCardHolders gameCardHolders) {
        CardHolder plusTwo = gameCardHolders.createNewCardHolder();
        for (String card: toCheck) {
            if (card.split(" ")[1].equals("+2") || card.split(" ")[1].equals("+4")) { // Check whether there is any plus two card for player
                gameCardHolders.addCard(card, plusTwo); // Add card to the cardholder that is playable when there is a plus card
            }
        }
        return plusTwo;
    }
    /**
     *To return player cards when plus four is played.
     * @param toCheck the function card to check
     * @param gameCardHolders The cards for players that need to be add card
     * @return the cards that the player can play
     */

    public CardHolder plusFourPlayerCanPlay(CardHolder toCheck, GameCardHolders gameCardHolders) {
        CardHolder plusFour = gameCardHolders.createNewCardHolder();
        for (String card: toCheck) {
            if (card.split(" ")[1].equals("+4")) { // Check whether there is any plus four card for player
                gameCardHolders.addCard(card, plusFour); // Add card to the cardholder that is playable when there is a plus four card
            }
        }
        return plusFour;
    }

    /**
     *To return player cards when plus two is played.
     * @param toCheck the function card to check
     * @param gameCardHolders The cards for players that need to be add card
     * @return the cards that the player can play
     */

    public CardHolder cardsPlayerCanPlay(CardHolder toCheck, GameCardHolders gameCardHolders){
        CardHolder cardsCanPlay = gameCardHolders.createNewCardHolder();

        if (lastCard == null) { // When the beginning of the stage, all card can be played
            return toCheck;
        }

        for (String card: toCheck){
            String feature = card.split(" ")[1];
            if (singleCompare(card) || feature.equals("switch") || feature.equals("+4")) { // Check whether the card can be played
                gameCardHolders.addCard(card, cardsCanPlay); //  Add card to the cardholder when the card is playable
            }
        }
        return cardsCanPlay;
    }

    /**
     * To compare which card
     * @param toCompare the string of the last card
     * @return true if and only if the card can be played
     */
    public boolean singleCompare(String toCompare){
        // check whether a single card is playable given the last card
        String toColor = toCompare.split(" ")[0];
        String toFeature = toCompare.split(" ")[1];
        String lastColor = lastCard.split(" ")[0];
        String lastFeature = lastCard.split(" ")[1];
        if (lastColor.equals("black")) {
            return lastFeature.equals(toFeature) || toColor.equals(currentColor) || toColor.equals("black"); // check when the play card is black card. same feature, or same color
        }
        return lastFeature.equals(toFeature) || lastColor.equals(toColor); // check whether the card has same color or feature
    }
    /**
     * Getter method for lastCard
     * @return the card of the last card
     */
    public String getLastCard() {
        return lastCard;
    }

    /**
     * setter method for lastcard
     */
    public void setLastCard(String lastCard) {
        this.lastCard = lastCard;
        currentColor = lastCard.split(" ")[0];
    }

}

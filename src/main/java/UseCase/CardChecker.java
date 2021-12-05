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
     * function card respond in Gui
     */
    public void functionCardResponseGUI(String feature, IPresenter iPresenter, GameRequest gameRequest) {
        if (feature.equals("+4") || feature.equals("switch")) {
            iPresenter.setColorGUI();
            currentColor = gameRequest.getSetColor();
        }
    }
    /**
     * function card response in gui in pve
     */

    public void functionCardResponseForComputer(String feature, IPresenter iPresenter, GameRequest gameRequest) {
        if (feature.equals("+4") || feature.equals("switch")) {
            ArrayList<String> colors = new ArrayList<>();
            Collections.addAll(colors, "red", "blue", "yellow", "green");
            Random rand = new Random();
            String color = colors.get(rand.nextInt(4));
            gameRequest.setSetColorForComputer(color);
            currentColor = gameRequest.getSetColorForComputer();
            iPresenter.setColorForComputer(color);
        }
    }

    public CardHolder skipsPlayerCanPlay(CardHolder toCheck, GameCardHolders gameCardHolders){
        CardHolder skips = gameCardHolders.createNewCardHolder();
        for (String card: toCheck){
            if (card.split(" ")[1].equals("skip")) {
                gameCardHolders.addCard(card, skips);
            }
        }
        return skips;
    }

    public CardHolder plusTwoPlayerCanPlay(CardHolder toCheck, GameCardHolders gameCardHolders) {
        CardHolder plusTwo = gameCardHolders.createNewCardHolder();
        for (String card: toCheck) {
            if (card.split(" ")[1].equals("+2") || card.split(" ")[1].equals("+4")) {
                gameCardHolders.addCard(card, plusTwo);
            }
        }
        return plusTwo;
    }

    public CardHolder plusFourPlayerCanPlay(CardHolder toCheck, GameCardHolders gameCardHolders) {
        CardHolder plusFour = gameCardHolders.createNewCardHolder();
        for (String card: toCheck) {
            if (card.split(" ")[1].equals("+4")) {
                gameCardHolders.addCard(card, plusFour);
            }
        }
        return plusFour;
    }

    public CardHolder cardsPlayerCanPlay(CardHolder toCheck, GameCardHolders gameCardHolders){
        CardHolder cardsCanPlay = gameCardHolders.createNewCardHolder();

        if (lastCard == null) {
            return toCheck;
        }

        for (String card: toCheck){
            String feature = card.split(" ")[1];
            if (singleCompare(card) || feature.equals("switch") || feature.equals("+4")) {
                gameCardHolders.addCard(card, cardsCanPlay);
            }
        }
        return cardsCanPlay;
    }

    public boolean singleCompare(String toCompare){
        // check whether a single card is playable given the last card
        String toColor = toCompare.split(" ")[0];
        String toFeature = toCompare.split(" ")[1];
        String lastColor = lastCard.split(" ")[0];
        String lastFeature = lastCard.split(" ")[1];
        if (lastColor.equals("black")) {
            return lastFeature.equals(toFeature) || toColor.equals(currentColor) || toColor.equals("black");
        }
        return lastFeature.equals(toFeature) || lastColor.equals(toColor);
    }
    /**
     * Getter method for lastCard
     */
    public String getLastCard() {
        return lastCard;
    }

    // This method has some problem, when we set a +2 card here, the status will not be changed.
    /**
     * setter method for lastcard
     */
    public void setLastCard(String lastCard) {
        this.lastCard = lastCard;
        currentColor = lastCard.split(" ")[0];
    }

}

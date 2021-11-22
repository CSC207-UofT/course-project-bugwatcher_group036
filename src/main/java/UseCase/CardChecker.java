package UseCase;

import Controller.ITerminal;
import Entity.HandCard;

public class CardChecker {

    private String lastCard;
    private String currentColor;

    public CardChecker(){
        lastCard = null;
        currentColor = null;
    }

    public void functionCardResponse(String feature, ITerminal iTerminal) {
        if (feature.equals("+4") || feature.equals("switch")) {
            currentColor = iTerminal.typeSetColor();
        }
    }

    public HandCard skipsPlayerCanPlay(HandCard toCheck){
        HandCard skips = new HandCard();
        for (String card: toCheck){
            if (card.split(" ")[1].equals("skip")) {
                skips.addCard(card);
            }
        }
        return skips;
    }

    public HandCard plusTwoPlayerCanPlay(HandCard toCheck) {
        HandCard plusTwo = new HandCard();
        for (String card: toCheck) {
            if (card.split(" ")[1].equals("+2") || card.split(" ")[1].equals("+4")) {
                plusTwo.addCard(card);
            }
        }
        return plusTwo;
    }

    public HandCard plusFourPlayerCanPlay(HandCard toCheck) {
        HandCard plusFour = new HandCard();
        for (String card: toCheck) {
            if (card.split(" ")[1].equals("+4")) {
                plusFour.addCard(card);
            }
        }
        return plusFour;
    }

    public HandCard cardsPlayerCanPlay(HandCard toCheck){
        HandCard cardsCanPlay = new HandCard();

        if (lastCard == null) {
            return toCheck;
        }

        for (String card: toCheck){
            String feature = card.split(" ")[1];
            if (singleCompare(card) || feature.equals("switch") || feature.equals("+4")) {
                cardsCanPlay.addCard(card);
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

    public String getLastCard() {
        return lastCard;
    }

    public String getCurrentColor() {
        return currentColor;
    }

    public void setLastCard(String lastCard) {
        this.lastCard = lastCard;
        currentColor = lastCard.split(" ")[0];
    }

    public void setCurrentColor(String color) {
        this.currentColor = color;
    }
}

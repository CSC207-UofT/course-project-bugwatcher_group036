package UseCase;

import Entity.CardHolder;
import Entity.Status;

public class CardChecker {

    private String lastCard;
    private String currentColor;

    public CardChecker(){
        lastCard = null;
        currentColor = null;
    }

    public void functionCardResponse(String feature, IPresenter iPresenter, GameRequest gameRequest) {
        if (feature.equals("+4") || feature.equals("switch")) {
            iPresenter.setColor();
            currentColor = gameRequest.getSetColor();
        }
    }

    public void functionCardResponseForComputer(String feature, IPresenter iPresenter, GameRequest gameRequest) {
        if (feature.equals("+4") || feature.equals("switch")) {
            iPresenter.setColorForComputer();
            currentColor = gameRequest.getSetColorForComputer();
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

    public String getLastCard() {
        return lastCard;
    }

    public String getCurrentColor() {
        return currentColor;
    }

    // This method has some problem, when we set a +2 card here, the status will not be changed.
    public void setLastCard(String lastCard) {
        this.lastCard = lastCard;
        currentColor = lastCard.split(" ")[0];
    }

    public void setCurrentColor(String color) {
        this.currentColor = color;
    }
}

package UseCase;

import Entity.Card;
import Entity.Player;

import java.util.ArrayList;

import static UseCase.DeckManager.compareTwoCardsHaveSameFeature;

/**
 * Contains the basic operations and game status variables,
 * which are extracted from controller
 */
public class FunctionManager {

    private final GameStatusManager gameStatusManager;
    private final LastCardManager lastCardManager;

    public FunctionManager(GameStatusManager gameStatusManager, LastCardManager lastCardManager){
        this.gameStatusManager = gameStatusManager;
        this.lastCardManager = lastCardManager;
    }

    /**
     * Extracted from controller
     * @param gameStatusManager the status, will be changed correspondingly
     * @param feature feature of the played function card
     */
    public void functionCardResponse(GameStatusManager gameStatusManager, String feature){
        switch (feature) {
            case "skip":
                gameStatusManager.setSkip(true);
                break;
            case "reverse":
                gameStatusManager.setReverse(!gameStatusManager.isReverse());
                break;
            case "plustwo":
                gameStatusManager.setPlus(gameStatusManager.getPlus() + 2);
                break;
            case "switch": {
                lastCardManager.typeSetColor();
                break;
            }
            default: {
                gameStatusManager.setPlus(gameStatusManager.getPlus() + 4);
                lastCardManager.typeSetColor();
                break;
            }
        }
    }

    /**
     * Extracted from controller
     * @param player the player whose handcard need to be checked
     * @return the cards player can play given the last card played
     */
    public ArrayList<Card> getCardsCurrentPlayerCanPlay(Player player){
        // Get the cards that the current player can play.
        // if the last card is skip, player only can play skip
        if (gameStatusManager.isSkip()) {
            return skipsPlayerCanPlay(player.getHandCard());
        } else if (gameStatusManager.getPlus() > 0){
            // if the last card is plus2, player can play plus2 or plus4.
            if (lastCardManager.getLastCard().getFeature().equals("plustwo")) {
                return plustwoPlayerCanPlay(player.getHandCard());
            } else {
                // if the last card is plus4, player can only play plus4.
                return plusfourPlayerCanPlay(player.getHandCard());
            }
        } else {
            // get the cards that the current player can play normally
            return cardsPlayerCanPlay(
                    player.getHandCard(),
                    lastCardManager.getLastCard());
        }

    }

    public ArrayList<Card> skipsPlayerCanPlay(ArrayList<Card> cards) {
        ArrayList<Card> skips = new ArrayList<>();
        for (Card c: cards) {
            if (c.getFeature().equals("skip")) {
                skips.add(c);
            }
        }
        return skips;
    }

    public ArrayList<Card> plustwoPlayerCanPlay(ArrayList<Card> cards) {
        ArrayList<Card> skips = new ArrayList<>();
        for (Card c: cards) {
            if (c.getFeature().equals("plustwo")||c.getFeature().equals("plusfour")) {
                skips.add(c);
            }
        }
        return skips;
    }

    public ArrayList<Card> plusfourPlayerCanPlay(ArrayList<Card> cards) {
        ArrayList<Card> skips = new ArrayList<>();
        for (Card c: cards) {
            if (c.getFeature().equals("plusfour")) {
                skips.add(c);
            }
        }
        return skips;
    }

    public ArrayList<Card> cardsPlayerCanPlay(ArrayList<Card> cards, Card lastCard) {
        ArrayList<Card> cardsCanPlay = new ArrayList<Card>();

        if (lastCard.getId().equals("null")) {
            return (ArrayList<Card>) cards.clone();
        }
        for (Card c: cards) {
            if (compareTwoCardsHaveSameFeature(lastCard, c, getColor())||c.getFeature().equals("switch")||
                    c.getFeature().equals("plusfour")) {
                cardsCanPlay.add(c);
            }
        }
        return cardsCanPlay;
    }

    public GameStatusManager getGameStatusManager() {
        return gameStatusManager;
    }

    public LastCardManager getGameBoard() {
        return lastCardManager;
    }

    public String getColor(){
        return lastCardManager.getColor();
    }
}

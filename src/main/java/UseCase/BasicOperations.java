package UseCase;

import Entity.Card;
import Entity.Player;
import UI.UI;

import java.util.ArrayList;

import static UseCase.DeckManager.compareTwoCardsHaveSameFeature;

/**
 * Contains the basic operations and game status variables,
 * which are extracted from controller
 */
public class BasicOperations {

    private final Status vars;
    private final GameBoard gameBoard;
    private UI ui;

    public BasicOperations(Status statVars, GameBoard gameBoard){
        this.vars = statVars;
        this.gameBoard = gameBoard;
    }

    /**
     * Extracted from controller
     * @param vars the variables, will be changed correspondingly
     * @param feature feature of the played function card
     */
    public void functionCardResponse(Status vars, String feature){
        switch (feature) {
            case "skip":
                vars.setSkip(true);
                break;
            case "reverse":
                vars.setReverse(!vars.isReverse());
                break;
            case "plustwo":
                vars.setPlus(vars.getPlus() + 2);
                break;
            case "switch": {
                gameBoard.typeSetColor();
                break;
            }
            default: {
                vars.setPlus(vars.getPlus() + 4);
                gameBoard.typeSetColor();

                break;
            }
        }
        setUI(ui);
    }

    /**
     * Extracted from controller
     * @param player the player whose handcard need to be checked
     * @return the cards player can play given the last card played
     */
    public ArrayList<Card> getCardsCurrentPlayerCanPlay(Player player){
        // Get the cards that the current player can play.
        // if the last card is skip, player only can play skip
        if (vars.isSkip()) {
            return skipsPlayerCanPlay(player.getHandCard());
        } else if (vars.getPlus() > 0){
            // if the last card is plus2, player can play plus2 or plus4.
            if (gameBoard.getLastCard().getFeature().equals("plustwo")) {
                return plustwoPlayerCanPlay(player.getHandCard());
            } else {
                // if the last card is plus4, player can only play plus4.
                return plusfourPlayerCanPlay(player.getHandCard());
            }
        } else {
            // get the cards that the current player can play normally
            return cardsPlayerCanPlay(
                    player.getHandCard(),
                    gameBoard.getLastCard());
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

        if (lastCard.getId().equals("nullid")) {
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

    public Status getVars() {
        return vars;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public String getColor(){
        return gameBoard.getColor();
    }
    public void setUI(UI ui) {
        this.ui = ui;
    }
}

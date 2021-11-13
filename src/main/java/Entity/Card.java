package Entity;

import UseCase.DeckManager;

import java.io.Serializable;

public class Card implements Serializable {
    private String color;
    private String id;

    /**
     * Construct the individual card with info implemented in UNO card game.
     *
     * giving the card the given color, number, function
     *
     * @param color   The card's color <red, blue, yellow, green, black>.
     * @param id The card's id, for distinguish different cards of the same number and color.
     */

    public Card(String color, String id){
        this.color = color;
        this.id = id;
    }

    /**
     * Construct a default card, just a replacement of normal cards but have no effects.
     */
    public Card(){
        this.color = "black";
        this.id = "nullid";
    }

    /**
     *  Variety of getter functions that returns the param of the card accordingly.
     */
    public String getColor(){
        return color;
    }


    /**
     * Get the id of the card.
     *
     * @return A string respond to the id of the card.
     */
    public String getId() {
        return id;
    }

    /**
     *  String representation for the Card.
     */
    public String toString() {
        return "Id:" + id;
    }

    /**
     * declare the card is not a funcion card.
     *
     * @return String "nullfeature", which means the card is a normal card, not a function card.
     */
    public String getFeature(){
        return "nullfeature";
    }

    public Card copy() {
        return new Card();
    }

    // test code
//    public static void main(String[] args){
//        Card c1 = new Card("red", 2, null);
//        System.out.println(c1.getColor());
//    }
}

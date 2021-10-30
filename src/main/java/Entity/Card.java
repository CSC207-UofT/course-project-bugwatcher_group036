package Entity;

import java.io.Serializable;

public class Card implements Serializable {
    private String color;
    private String id;

    /**
     * Construct the individual card with info implemented in UNO card game.
     *
     * giving the card the given color, number, function
     *
     * @param color   The card's color <red, blue, yellow, green, black>
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
     *  Variety of getter functions that returns the param of the card accordingly
     */
    public String getColor(){
        return color;
    }


    public String getId() {
        return id;
    }

    public String toString() {
        return "Id:" + id;
    }

    // test code
//    public static void main(String[] args){
//        Card c1 = new Card("red", 2, null);
//        System.out.println(c1.getColor());
//    }
}

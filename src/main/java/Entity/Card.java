package Entity;

public class Card {
    private String color;
    private int number;
    private String function;
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
        this.function = "nullfunc";
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

    @Override
    public String toString() {
        return "Card{" + "id=" + id + '}';
    }

    // test code
//    public static void main(String[] args){
//        Card c1 = new Card("red", 2, null);
//        System.out.println(c1.getColor());
//    }
}

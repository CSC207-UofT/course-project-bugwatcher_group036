package Entity;

public class NumberCard extends Card {

    private int number;

    /**
     * Construct a number card.
     *
     * @param color The color of the card <red, yellow, green, blue>.
     * @param number The card's number <0~9, -1>.
     * @param id The id of the card.
     */
    public NumberCard(String color, int number, String id) {
        super(color, id);
        this.number = number;
    }

    /**
     * Construct a number card with default value.
     */
    public NumberCard() {
        super();
        this.number = -1;
    }

    /**
     * Get the number of this card.
     *
     * @return Number of the card.
     */
    public int getNumber(){
        return number;
    }

    @Override
    public String toString() {
        return this.getColor()  + number;
    }

    @Override
    public String getFeature() {
        return Integer.toString(number);
    }
}

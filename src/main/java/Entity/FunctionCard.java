package Entity;

public class FunctionCard extends Card{

    private String function;

    /**
     * Construct a function card.
     *
     * @param color The color of the function card <red, blue, green, yellow, black>.
     * @param id The id of the function card.
     * @param function The card's function <switch, reverse, skip, plusTwo, plusFour, null>.
     */
    public FunctionCard(String color, String function, String id) {
        super(color ,id);
        this.function = function;
    }

    /**
     * Construct a function card with default value.
     */
    public FunctionCard(){
        super();
        this.function = "null";
    }

    /**
     * Get the function.
     *
     * @return Function the card can do.
     */
    public String getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return this.getColor() + " " + this.function;
    }

    @Override
    public String getFeature() {
        return function;
    }
}

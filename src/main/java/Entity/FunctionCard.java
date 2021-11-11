package Entity;

public class FunctionCard extends Card{

    private String function;

    /**
     *
     * @param color       The color of the function card <red, blue, green, yellow, black>
     * @param id          The id of the function card
     * @param function    The card's function <switch, reverse, skip, plustwo, plusfour, null>
     */
    public FunctionCard(String color, String function, String id) {
        super(color ,id);
        this.function = function;
    }

    public FunctionCard(){
        super();
        this.function = "nullfunction";
    }

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

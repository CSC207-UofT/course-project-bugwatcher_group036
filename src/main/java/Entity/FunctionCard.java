package Entity;

public class FunctionCard extends Card{

    private String function;

    /**
     *
     * @param color
     * @param id
     * @param function    The card's function <switch, reverse, skip, plustwo, plusfour, null>
     */
    public FunctionCard(String color, String id, String function) {
        super(color ,id);
        this.function = function;
    }

    public FunctionCard(){
        super();
    }

    public String getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return this.getColor() + " " +this.function;
    }


}

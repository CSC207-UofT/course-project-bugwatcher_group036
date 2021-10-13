import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Card {
    private String color;
    private Object number;
    private Object function;

    /**
     * Construct the individual card with info implemented in UNO card game.
     *
     * giving the card the given color, number, function
     *
     * @param color   The card's color <red, blue, yellow, green, black>
     * @param number   The card's number <0~9, null>
     * @param function    The card's function <switch, reverse, skip, plustwo, plusfour, null>
     */
    public Card(String color, Object number, Object function){
        this.color = color;
        this.number = number;
        this.function = function;
    }

    /**
     *  Variety of getter functions that returns the param of the card accordingly
     */
    public String getColor(){
        return this.color;
    }

    public Object getNumber(){
        if (this.number == null){
            return null;
        }
        return this.number;
    }

    public Object getFunction(){
        if (this.function == null) {
            return null;
        }
        return this.function;
    }
}

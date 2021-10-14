package main.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param number   The card's number <0~9, -1>
     * @param function    The card's function <switch, reverse, skip, plustwo, plusfour, null>
     */
    public Card(String color, int number, String function, String id){
        this.color = color;
        this.number = number;
        this.function = function;
        this.id = id;
    }

    /**
     * Construct a default card, just a replacement of normal cards but have no effects.
     */
    public Card(){
        this.color = "black";
        this.number = -1;
        this.function = "nullfunc";
        this.id = "nullid";
    }

    /**
     *  Variety of getter functions that returns the param of the card accordingly
     */
    public String getColor(){
        return color;
    }

    public Object getNumber(){
        if (number == -1){
            return null;
        }
        return number;
    }

    public Object getFunction() {
        if (function == null) {
            return null;
        }
        return function;
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

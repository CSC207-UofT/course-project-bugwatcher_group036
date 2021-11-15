package UI;

import Entity.Card;

import javax.swing.*;
import java.util.ArrayList;

public interface DisplayCard {
    public default void displayCard(Card lastCard, ArrayList<Card> handCard,
                                    ArrayList<Card> remaining) {

    }
}

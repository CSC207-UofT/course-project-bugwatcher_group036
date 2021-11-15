package UI;

import Entity.Card;

import java.util.ArrayList;

public interface ICardDisplayer {

    void displayCard(Card lastCard, ArrayList<Card> handCard, ArrayList<Card> remaining);
}

package Controller;

import Entity.Card;
import Entity.Deck;
import Entity.FunctionCard;
import Entity.NumberCard;
import UseCase.DeckManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public interface Readfile {

    /**
     * @param numfilepath number card location
     * @param funcfilepath function card location
     */
    ArrayList<String> readFromFile(String numfilepath, String funcfilepath, DeckManager deckManager);
}

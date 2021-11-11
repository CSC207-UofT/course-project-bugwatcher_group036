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
//    /**
//     * @param filepath location of ser file
//     * @param o object to be serialized
//     */
//    void saveToFile(String filepath, Object o) throws IOException;

    /**
     * @param numfilepath number card location
     * @param funcfilepath function card location
     */
    ArrayList<String> readFromFile(String numfilepath, String funcfilepath, DeckManagerData deckManagerData);
}

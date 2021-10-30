package Controller;

import Entity.Deck;

import java.io.IOException;

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
    Deck readFromFile(String numfilepath, String funcfilepath);
}

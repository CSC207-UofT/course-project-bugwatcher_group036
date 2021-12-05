package Controller;

import Entity.*;

import java.io.*;
import java.util.ArrayList;

public class Cardreadfile implements Readfile {

    public ArrayList<String> readFromFile(){

        Deck d = new Deck();

        ArrayList<String> unused = d.getUnusedCardDeck();
        File testFile = new File("");
        try {
            BufferedReader CardList = new BufferedReader(new FileReader(testFile.getAbsolutePath() +
                    "/src/main/java/DataSet/Cards.txt"));
            String numberLine = CardList.readLine();
            while (numberLine != null){
                unused.add(numberLine);
                numberLine = CardList.readLine();
            }
        }
        catch (FileNotFoundException fileMissing){
            System.out.println("Card file not found. Check directory.");

        }
        catch (IOException ignored) {

        }

        return unused;
    }
}
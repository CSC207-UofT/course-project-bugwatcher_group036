package Controller;

import UseCase.ReadFile;

import java.io.*;
import java.util.ArrayList;

/**
 * A Gateway Class.
 */
public class Gateway implements ReadFile {

    /**
     * The Method for reading a file from external place.
     * @return The array list of card
     */
    public ArrayList<String> readFromFile(){

        ArrayList<String> unused = new ArrayList<String>();
        File testFile = new File("");
        try {
            BufferedReader CardList = new BufferedReader(new FileReader(testFile.getAbsolutePath() +
                    "/src/main/java/DataSet/Cards.txt")); //To setup a reader for txt document
            String numberLine = CardList.readLine();
            while (numberLine != null){
                unused.add(numberLine);
                numberLine = CardList.readLine();
            }
        }
        catch (FileNotFoundException fileMissing){ //When the file is not in the correct place
            System.out.println("Card file not found. Check directory.");

        }
        catch (IOException ignored) {

        }

        return unused;
    }
}
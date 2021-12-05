package Controller;

import UseCase.ReadFile;

import java.io.*;
import java.util.ArrayList;

public class Gateway implements ReadFile {

    public ArrayList<String> readFromFile(){

        ArrayList<String> unused = new ArrayList<String>();
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
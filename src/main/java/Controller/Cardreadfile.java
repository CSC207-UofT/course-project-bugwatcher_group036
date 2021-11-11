package Controller;

import Entity.*;
import UseCase.DeckManager;

import java.io.*;
import java.util.ArrayList;

public class Cardreadfile implements Readfile {
// gateway class
//    @Override
//    public void saveToFile(String filepath, Object users) throws IOException {
//
//        OutputStream file = new FileOutputStream(filepath);
//        OutputStream buffer = new BufferedOutputStream(file);
//        ObjectOutput output = new ObjectOutputStream(buffer);
//
//        // serialize the Map
//        output.writeObject(users);
//        output.close();
//    }

    public ArrayList<String> readFromFile(String numfilePath, String funfilepath, DeckManagerData deckManagerData){

//        InputStream file = new FileInputStream(filePath);
//        InputStream buffer = new BufferedInputStream(file);
//        ObjectInput input = new ObjectInputStream(buffer);

        // serialize the Map
//        Deck users = (Deck) input.readObject();
//        input.close();
        Deck d = new Deck();

        ArrayList<String> colors = new ArrayList<>();

        try {
            BufferedReader numberFile = new BufferedReader(new FileReader(numfilePath));
            BufferedReader functionFile = new BufferedReader(new FileReader(funfilepath));
            String numberLine = numberFile.readLine();
            String functionLine = functionFile.readLine();
            while (numberLine != null){
                String[] numberSplit = numberLine.split(" ");
                Card numCard = deckManagerData.getDeckManager().createNumberCard(
                        numberSplit[0], Integer.parseInt(numberSplit[1]),
                        numberSplit[0]+numberSplit[1]);
                deckManagerData.getDeckManager().addCard(numCard);
                numberLine = numberFile.readLine();
            }
            while (functionLine != null){
                String[] functionSplit = functionLine.split(" ");
                Card funCard = deckManagerData.getDeckManager().createFunctionCard(
                        functionSplit[0], functionSplit[1],
                        functionSplit[0]+" "+functionSplit[1]);
                deckManagerData.getDeckManager().addCard(funCard);
                if (!colors.contains(functionSplit[0])) {
                    colors.add(functionSplit[0]);
                }
                functionLine = functionFile.readLine();
            }
        }
        catch (FileNotFoundException fileMissing){
            System.out.println("Card file not found. Check directory.");

        }
        catch (IOException ignored) {}
        return colors;
    }
}
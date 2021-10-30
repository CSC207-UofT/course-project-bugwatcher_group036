package Controller;

import Entity.*;

import java.io.*;

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

    public Deck readFromFile(String numfilePath, String funfilepath){

//        InputStream file = new FileInputStream(filePath);
//        InputStream buffer = new BufferedInputStream(file);
//        ObjectInput input = new ObjectInputStream(buffer);

        // serialize the Map
//        Deck users = (Deck) input.readObject();
//        input.close();
        Deck d = new Deck();

        try {
            BufferedReader numberFile = new BufferedReader(new FileReader(numfilePath));
            BufferedReader functionFile = new BufferedReader(new FileReader(funfilepath));
            String numberLine = numberFile.readLine();
            String functionLine = functionFile.readLine();
            while (numberLine != null){
                String[] numberSplit = numberLine.split(" ");
                Card numCard = new NumberCard(numberSplit[0], Integer.parseInt(numberSplit[1]), numberSplit[2]);
                d.addcard(numCard);
                numberLine = numberFile.readLine();
            }
            while (functionLine != null){
                String[] functionSplit = functionLine.split(" ");
                Card funCard = new FunctionCard(functionSplit[0], functionSplit[1], functionSplit[2]);
                d.addcard(funCard);
                functionLine = functionFile.readLine();
            }
        }
        catch (FileNotFoundException fileMissing){
            System.out.println("Card file not found. Check directory.");

        }
        catch (IOException ignored) {}
        return d;
    }
}
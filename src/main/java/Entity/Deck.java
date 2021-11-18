package Entity;

import java.io.*;
import java.util.*;

public class Deck {

    private ArrayList<String> used;
    private ArrayList<String> unused;

    /**
     * Construct the Deck in UNO card game.
     *
     */
    public Deck(){
        this.used = new ArrayList<>();
        this.unused = new ArrayList<>();
        File testFile = new File("");
        try {
            BufferedReader CardList = new BufferedReader(new FileReader(testFile.getAbsolutePath() + "/src/main/java/Constants/Cards.txt"));
            String numberLine = CardList.readLine();
            while (numberLine != null){
                unused.add(numberLine);
                numberLine = CardList.readLine();
            }
        }
        catch (FileNotFoundException fileMissing){
            System.out.println("Card file not found. Check directory.");

        }
        catch (IOException ignored) {} // wise: don't know how to handle this case
    }

    /**
     * To check whether the unused_card_deck is empty.
     *
     * If the card deck is empty, Placed all the used card into the card deck.
     */
    public boolean isEmpty(){
        return unused.isEmpty();
        }

//    /**
//     * To remove a card from the unused card deck.
//     *
//     * @return Card that is removed from the unused card deck
//     */
//    public Card remove_card() {
//        return unused_card_deck.remove(0);
//    }


//    /**
//     * To add a card into the used_card_deck.
//     *
//     */
//    public void add_card(Card card) {
//        used_card_deck.add(card);
//    }

    /**
     * To return the numbers of card in a card deck.
     *
     * @return the numbers of card in a card deck.
     */
    public int numOfCards(ArrayList<String> deck) {
        return deck.size();
    }

    /**
     * getter
     * @return unsed_card_deck
     */
    public ArrayList<String> getUsedCardDeck(){
        return used;
    }

    /**
     * setter
     * @param replacement the new deck to replace the old one
     */
    public void setUsedCardDeck(ArrayList<String> replacement){
        used = replacement;
    }

    /**
     * getter
     * @return unused_card_deck
     */
    public ArrayList<String> getUnusedCardDeck(){
        return unused;
    }

    /**
     * setter
     * @param replacement the new deck to replace the old one
     */
    public void setUnusedCardDeck(ArrayList<String> replacement){
        unused = replacement;
    }

    public String drawCardFromUnusedDeck() {
        if (unused.size() == 0) {
            return null;
        }
        Random rand = new Random();
        int index = rand.nextInt(unused.size());
        return unused.remove(index);
    }

    public void shuffleFromUsedToUnused() {
        unused = used;
        used = new ArrayList<>();
    }

    public void putCardToUsedDeck(String c) {
        used.add(c);
    }

}

package UseCase;

import Entity.Card;
import Entity.Deck;
import java.util.ArrayList;

public class DeckUseCase {

    private Deck d;

    public DeckUseCase(){
        this.d = new Deck();
    }

    public int[] returnDeckInfo(){
        int[] res = new int[2];
        res[0] = d.getUsed_card_deck().size();
        res[1] = d.getUnused_card_deck().size();
        return res;
    }

    public void shuffleFromUsedToUnused(){
        ArrayList<Card> temp;
        temp = d.getUsed_card_deck();
        d.setUnused_card_deck(temp);
        d.setUsed_card_deck(new ArrayList<Card>());
    }

    public void initializeCard(ArrayList<Card> cards){
        d.setUnused_card_deck(cards);
    }

    public void putCardToUsedDeck(Card c){
        d.getUsed_card_deck().add(c);
    }

    public Card drawCardFromUnusedDeck(){
        ArrayList<Card> unused = d.getUnused_card_deck();
        int index = (int)(Math.random() * unused.size());
        return unused.remove(index);
    }

    public boolean checkShuffle(){
        return d.getUnused_card_deck().size() == 0;
    }


}

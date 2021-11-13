package Controller;

import UseCase.DeckManager;

public class DeckManagerData {

    private DeckManager deckManager;

    public DeckManagerData() {
        this.deckManager = new DeckManager();
    }

    public DeckManager getDeckManager() {
        return deckManager;
    }
}

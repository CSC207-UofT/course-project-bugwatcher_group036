package Controller;

import Entity.Card;
import UseCase.DeckManager;
import UseCase.PlayerManager;

import java.util.ArrayList;
import java.util.Random;

// This class contains methods when a function card is played.
public class FunctionPlayed {

    private PlayerManager playerManager;
    private DeckManager cardManager;
    private Random rand;
    private ArrayList<String> num;
    private ArrayList<String> colors;

    public FunctionPlayed(PlayerManager playerManager, DeckManager cardManager, ArrayList<String> num,
                     ArrayList<String> colors) {
        this.playerManager = playerManager;
        this.cardManager = cardManager;
        this.num = num;
        this.colors = colors;
        this.rand = new Random();
    }

    public void plusManyNextPlayer(int currentPlayerIndex, int num) {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Card drawedCard = cardManager.drawCardFromUnusedDeck();
            if (!cardManager.whetherNull(drawedCard)) {
                playerManager.getPlayers()[currentPlayerIndex].drawCard(drawedCard);
                cards.add(drawedCard);
            }
        }
        System.out.println("The cards you drew are: " + cards);
    }

    public void functionCardResponse(ControllerVariables vars, String feature){
        switch (feature) {
            case "skip":
                vars.setSkip(true);
                break;
            case "reverse":
                vars.setReverse(!vars.isReverse());
                break;
            case "plustwo":
                vars.setPlus(vars.getPlus() + 2);
                break;
            case "switch": {
                setColor(vars);
                break;
            }
            default: {
                vars.setPlus(vars.getPlus() + 4);
                setColor(vars);
                break;
            }
        }
    }

    public void setColor(ControllerVariables vars){
        System.out.println("Type a color you want to set:");
        String setColor = vars.getKeyboard().nextLine();
        int wrongTimeCounter = 0;

        while (wrongTimeCounter < 3){

            if (!colors.contains(setColor) && wrongTimeCounter < 2) {
                System.out.println("Wrong color! Type again:");
                setColor = vars.getKeyboard().nextLine();
            }
            else if (!colors.contains(setColor) && wrongTimeCounter == 2) {
                System.out.println("Wrong color 3 times! Color randomly chosen");
                setColor = colors.get(rand.nextInt(colors.size()));
            } else {
                break;
            }

            wrongTimeCounter++;
        }
        playerManager.renewLastCard(cardManager.createColorCard(setColor));
    }

}

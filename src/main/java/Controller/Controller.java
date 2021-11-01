package Controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

import Entity.Card;
import Entity.Player;
import UseCase.PlayerManager;
import UseCase.DeckManager;

/**
 * The main.Controller to run a game.
 */
public class Controller {

    private PlayerManager playerManager;
    private DeckManager cardManager;
    private Random rand;
    private ArrayList<String> num;
    private ArrayList<String> colors;

    /**
     * Construct a main.Controller with a given number of players.
     * @param numberOfPlayers
     */
    public Controller(int numberOfPlayers) {

        // create and assign the PlayerUseCase to attribute playerManager.
        this.playerManager = new PlayerManager(numberOfPlayers);

        // create an Array of Players which is wait to be assigned with input players.
        Player[] currentPlayer = new Player[numberOfPlayers];

        // input players for given numbers of players and put it into the array.
        for (int i = 0; i < numberOfPlayers; i++) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("enter a player name for player " + (i+1) + ":");
            String playerID = keyboard.nextLine();
            playerManager.createPlayer(playerID, i);
        }
        currentPlayer = playerManager.getPlayers(); //zhuyuezx: simplified this part.

        // create a new PlayerUseCase with the array of players as the playManager.
//        this.playerManager = new PlayerUseCase(currentPlayer);

        // create a new CardUseCase as the cardManager.

        this.cardManager = new DeckManager();
        Readfile readfile = new Cardreadfile();
        this.colors = readfile.readFromFile("src/main/resources/numbercards.txt",
                "src/main/resources/functioncards.txt", cardManager);
        this.rand = new Random();
        this.num = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            num.add(Integer.toString(i));
        }

    }

    public void deal() {
        for (int i = 0; i < 7; i++) {
            for (Player p: playerManager) {
                Card c = cardManager.drawCardFromUnusedDeck();
                p.drawCard(c);
            }
        }
    }

    public void drawCardWhenNoCardToPlay(ArrayList<Card> currentCardsPlayerCanPlay, int currentPlayerIndex) {
        if (currentCardsPlayerCanPlay.isEmpty()) {
            System.out.println("You cannot play a card! You need to draw one more card");
            // draw a card from the deck
            Card c = cardManager.drawCardFromUnusedDeck();
            // If the card drwan is not null
            if (!cardManager.whetherNull(c)) {
                //give the card to the player.
                playerManager.playerDrawCard(currentPlayerIndex, c);
                System.out.println("The card you drew is " + c);
                System.out.println();
            }
        }
    }

    public Card letPlayerPlayCard(ArrayList<Card> currentCardsPlayerCanPlay,
                                     int currentPlayerIndex) {
        Scanner keyboard = new Scanner(System.in);

        // The card that the player wants to play
        Card cardToPlay;

        // rightCard indicates whether the play type a right card to play.
        boolean rightCard;

        // The number of times that the player type a wrong card.
        int wrongTimes = 0;

        // Let the player type the card to play. If type a wrong card, type again,
        // with maximum 3 times.
        do {
            // print all the information
            System.out.println("Last card: " + playerManager.getLastCard());
            System.out.println("The cards you have: " + playerManager.getHandCard(currentPlayerIndex));
            System.out.println("The cards you can play: " + currentCardsPlayerCanPlay);
            System.out.println("Enter a card to play:");

            // let the player type the card to play
            String cardToPlayID = keyboard.nextLine();

            // extract the card to play from the hand card
            cardToPlay = cardManager.extractCard(currentCardsPlayerCanPlay, cardToPlayID);

            // if the card chose is null, count the wrong time
            if (cardManager.whetherNull(cardToPlay)) {
                wrongTimes++;
                rightCard = false;
            } else {
                rightCard = true;
            }
        } while (!rightCard && wrongTimes < 3);
        //exit when the player types the right class or wrong time exceed 3
        return cardToPlay;
    }

    public void punishOrPlayCard(Card cardToPlay, int currentPlayerIndex) {
        // return false for punishment, true for play a card
        // If the player types 3 times wrong card, draw a card, otherwise play the card.
        if (cardManager.whetherNull(cardToPlay)) {
            System.out.println("Enter too many times wrong cards! Draw a card for punishment.");
            Card c = cardManager.drawCardFromUnusedDeck();
            // if the drawn card is not null
            if (!cardManager.whetherNull(c)){
                // give the card to the player
                playerManager.playerDrawCard(currentPlayerIndex, c);
                System.out.println("The card you drew is " + c);
            }
        } else {
            // if the played card is valid, play the card
            Card playedCard = playerManager.playerPlayCard(currentPlayerIndex, cardToPlay);
            // put the played into the used deck
            cardManager.putCardToUsedDeck(playedCard);
        }
    }

    public int moveToNextPlayer(int currentPlayerIndex, boolean reverse) {
        // Move to the next player
        if (!reverse){
            currentPlayerIndex++;
            if (currentPlayerIndex == playerManager.getPlayerNum()) {
                currentPlayerIndex = 0;
            }
            return currentPlayerIndex;
        } else {
            currentPlayerIndex--;
            if (currentPlayerIndex == -1) {
                currentPlayerIndex = playerManager.getPlayerNum() - 1;
            }
            return currentPlayerIndex;
        }

    }

    public void plusManyNextPlayer(int currentPlayerIndex, int num) {
            for (int i = 0; i < num; i++) {
                Card drawedCard = cardManager.drawCardFromUnusedDeck();
                if (!cardManager.whetherNull(drawedCard)) {
                    playerManager.getPlayers()[currentPlayerIndex].drawCard(drawedCard);
                }
            }
    }




    /**
     * run the game and return the player that wins.
     * @return
     */
    public Player runGame() {
        Scanner keyboard = new Scanner(System.in);

        // The winner player
        Player playerWins = null;

        // whether reverse
        boolean reverse = false;

        // winFlag is used to indicate whether a winner appears.
        boolean winFlag = false;

        // Randomly select a player to play the first card.
        int currentPlayerIndex = rand.nextInt(this.playerManager.getPlayerNum());

        // whether the turn of player should be skipped
        boolean skip = false;

        //whether there are cards that needed to drawed for the player
        int plus = 0;

        // if winFlag is true, it means the winner appears and the while loop exits.
        while (!winFlag) {
//            // whether the player successfully plays a card
//            boolean whetherPlayCard;

            // whether skip the next player


            // cardToPlay is the card that the player wants to play.
            Card cardToPlay = cardManager.createNullCard();
            // show the current player
            System.out.println();
            System.out.println("Current player: " + playerManager.getPlayers()[currentPlayerIndex]);

            ArrayList<Card> currentCardsPlayerCanPlay;


//            ArrayList<Card> currentCardsPlayerCanPlay = playerManager.CardsPlayerCanPlay(currentPlayerIndex,
//                    cardManager);

            // Get the cards that the current player can play.
            // if the last card is skip, player only can play skip
            if (skip) {
                currentCardsPlayerCanPlay =
                        cardManager.skipsPlayerCanPlay(playerManager.getHandCard(currentPlayerIndex));
            } else if (plus > 0){
                // if the last card is plus2, player can play plus2 or plus4.
                if (cardManager.feature(playerManager.getLastCard()).equals("plustwo")) {
                    currentCardsPlayerCanPlay =
                            cardManager.plustwoPlayerCanPlay(playerManager.getHandCard(currentPlayerIndex));
                } else {
                    // if the last card is plus4, player can only play plus4.
                    currentCardsPlayerCanPlay =
                            cardManager.plusfourPlayerCanPlay(playerManager.getHandCard(currentPlayerIndex));
                }
            } else {
                // get the cards that the current player can play normally
                currentCardsPlayerCanPlay = cardManager.cardsPlayerCanPlay(
                        playerManager.getHandCard(currentPlayerIndex), playerManager.getLastCard()
                );
            }

            // If no cards can play, draw a card, otherwise play a card. If the player type three times
            // wrong card to play, the player will be punished to draw a card automatically.
            if (currentCardsPlayerCanPlay.isEmpty()) {
                if (plus > 0) {
                    plusManyNextPlayer(currentPlayerIndex, plus);
                    plus = 0;
                } else if (!cardManager.feature(playerManager.getLastCard()).equals("skip")||
                        (cardManager.feature(playerManager.getLastCard()).equals("skip")| !skip)){
                    // draw a card when there is no valid card can play
                    drawCardWhenNoCardToPlay(currentCardsPlayerCanPlay, currentPlayerIndex);
                }
            } else {

//                // cardToPlay is the card that the player wants to play.
//                Card cardToPlay;

                // Let the player type the card to play. If type a wrong card, type again with maximum 3 times.
                cardToPlay = letPlayerPlayCard(currentCardsPlayerCanPlay, currentPlayerIndex);

                // If the player types 3 times wrong card, draw a card, otherwise play the card.
                punishOrPlayCard(cardToPlay, currentPlayerIndex);
            }

            // set the skip to false since the function skip has passed.
            skip = false;

            // if the player successfully play a card
            if (!cardManager.whetherNull(cardToPlay)) {
                String feature = cardManager.feature(cardToPlay);
                // if the player plays a function card
                if (!num.contains(feature)) {
                    // if it is the last card that the palyer plays is a function card, draw a card.
                    if (playerManager.winOrNot(currentPlayerIndex)) {
                        Card c = cardManager.drawCardFromUnusedDeck();
                        playerManager.getPlayers()[currentPlayerIndex].drawCard(c);
                    }
                    if (feature.equals("skip")) {
                        skip = true;
                    } else if (feature.equals("reverse")){
                        reverse = !reverse;
                    } else if (feature.equals("plustwo")){
                        plus = plus + 2;
                    } else if (feature.equals("switch")) {
                        System.out.println("Type a color you want to set:");
                        String setColor = keyboard.nextLine();
                        if (!colors.contains(setColor)) {
                            System.out.println("Wrong color! Type again:");
                            setColor = keyboard.nextLine();
                        };
                        if (!colors.contains(setColor)) {
                            System.out.println("Wrong color! Type again:");
                            setColor = keyboard.nextLine();
                        };
                        if (!colors.contains(setColor)) {
                            System.out.println("Wrong color 3 times! Color randomly chosen");
                            setColor = colors.get(rand.nextInt(colors.size()));
                        };
                        playerManager.renewLastCard(cardManager.createColorCard(setColor));
                    } else {
                        plus = plus + 4;
                        System.out.println("Type a color you want to set:");
                        String setColor = keyboard.nextLine();
                        if (!colors.contains(setColor)) {
                            System.out.println("Wrong color! Type again:");
                            setColor = keyboard.nextLine();
                        };
                        if (!colors.contains(setColor)) {
                            System.out.println("Wrong color! Type again:");
                            setColor = keyboard.nextLine();
                        };
                        if (!colors.contains(setColor)) {
                            System.out.println("Wrong color 3 times! Color randomly chosen");
                            setColor = colors.get(rand.nextInt(colors.size()));
                        };
                        playerManager.renewLastCard(cardManager.createColorCard(setColor));
                    }
                }
            }

            // Determine whether the player wins or not.
            if (playerManager.winOrNot(currentPlayerIndex)) {
                winFlag = true;
                playerWins = playerManager.getPlayers()[currentPlayerIndex];
            }

            // Move to the next player
            currentPlayerIndex = moveToNextPlayer(currentPlayerIndex, reverse);
        }
        return playerWins;
    }

    public static void main(String[] args) {
        Controller newGameController = new Controller(4);
        newGameController.deal();
        Player playerWins = newGameController.runGame();
        System.out.println(playerWins.getId() + " wins!");
    }
}

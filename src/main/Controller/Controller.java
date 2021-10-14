package Controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

import Entity.Card;
import Entity.Player;
import UseCase.PlayerUseCase;
import UseCase.DeckUseCase;

/**
 * The Controller to run a game.
 */
public class Controller {

    private PlayerUseCase playerManager;
    private DeckUseCase cardManager;

    /**
     * Construct a Controller with a given number of players.
     * @param numberOfPlayers
     */
    public Controller(int numberOfPlayers) {

        // create and assign the PlayerUseCase to attribute playerManager.
        this.playerManager = new PlayerUseCase(numberOfPlayers);

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
        this.cardManager = new DeckUseCase();

    }

    public void deal() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                Card c = cardManager.drawCardFromUnusedDeck();
                playerManager.playerDrawCard(i, c);
            }
        }
    }

    /**
     * run the game and return the player that wins.
     * @return
     */
    public Player runGame() {
        Player playerWins = null; //////////////////!!!!!!!!!!!

        // winFlag is used to indicate whether a winner appears.
        boolean winFlag = false;

        // Randomly select a player to play the first card.
        Random rand = new Random();
        int currentPlayerIndex = rand.nextInt(this.playerManager.getPlayerNum());

        // if winFlag is true, it means the winner appears and the while loop exits.
        while (!winFlag) {

            System.out.println();
            System.out.println("Current player: " + playerManager.getPlayers()[currentPlayerIndex]);

            // Get the cards that the current player can play.
            ArrayList<Card> currentCardsPlayerCanPlay = playerManager.CardsPlayerCanPlay(currentPlayerIndex);

            // If no cards can play, draw a card, otherwise play a card. If the player type three times
            // wrong card to play, the player will be punished to draw a card automatically.
            if (currentCardsPlayerCanPlay.isEmpty()) {
                System.out.println("You cannot play a card! You need to draw one more card");
                Card c = cardManager.drawCardFromUnusedDeck();
                if (!cardManager.compareNew(c)){
                    playerManager.playerDrawCard(currentPlayerIndex, c);
                    System.out.println("The card you drew is " + c);
                    System.out.println();
                }
            } else {
                Scanner keyboard = new Scanner(System.in);

                // rightCard indicates whether the play type a right card to play.
                boolean rightCard = true;

                // cardToPlay is the card that the player wants to play.
                // (cardToPlay is a right card to play)
                Card cardToPlay;

                // The number of times that the player type a wrong card.
                int wrongTimes = 0;

                // Let the player type the card to play. If type a wrong card, type again,
                // with maximum 3 times.
                do {
                    System.out.println("Last card: " + playerManager.getLastCard());
                    System.out.println("The cards you have: " + playerManager.getHandCard(currentPlayerIndex));
                    System.out.println("The cards you can play: " + currentCardsPlayerCanPlay);
                    System.out.println("Enter a card to play:");
                    String cardToPlayID = keyboard.nextLine();
                    cardToPlay = cardManager.extractCard(currentCardsPlayerCanPlay, cardToPlayID);
                    if (cardManager.compareNew(cardToPlay)) {
                        wrongTimes++;
                        rightCard = false;
                    } else {
                        rightCard = true;
                    }
                } while (!rightCard && wrongTimes < 3);

                // If the player types 3 times wrong card, draw a card, otherwise play the card.
                if (wrongTimes == 3) {
                    System.out.println("Enter too many times wrong cards! Draw a card for punishment.");
                    Card c = cardManager.drawCardFromUnusedDeck();
                    if (!cardManager.compareNew(c)){
                        playerManager.playerDrawCard(currentPlayerIndex, c);
                        System.out.println("The card you drew is " + c);
                    }
                } else {
                    Card playedCard = playerManager.playerPlayCard(currentPlayerIndex, cardToPlay);
                    cardManager.putCardToUsedDeck(playedCard);
                }

            }

            // Determine whether the player wins or not.
            if (playerManager.winOrNot(currentPlayerIndex)) {
                winFlag = true;
                playerWins = playerManager.getPlayers()[currentPlayerIndex];
            }

            // Move to the next player
            currentPlayerIndex++;
            if (currentPlayerIndex == playerManager.getPlayerNum()) {
                currentPlayerIndex = 0;
            }
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

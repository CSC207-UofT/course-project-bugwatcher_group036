package Controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

import Entity.Card;
import Entity.Player;
import UseCase.DeckUseCase;
import UseCase.PlayerUseCase;

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
            System.out.println("enter a player name for player 1:");
            String playerID = keyboard.nextLine();
            Player newPlayer = playerManager.createPlayer(playerID, i);
            currentPlayer[i] = newPlayer;
        }

        // create a new PlayerUseCase with the array of players as the playManager.
        this.playerManager = new PlayerUseCase(currentPlayer);

        // create a new CardUseCase as the cardManager.
        this.cardManager = new DeckUseCase();

    }

    /**
     * run the game and return the player that wins.
     * @return
     */
    public Player runGame() {
        Player playerWins = null;

        // winFlag is used to indicate whether a winner appears.
        boolean winFlag = false;

        // Randomly select a player to play the first card.
        Random rand = new Random();
        int currentPlayerIndex = rand.nextInt(this.playerManager.getPlayerNum());

        // if winFlag is true, it means the winner appears and the while loop exits.
        while (!winFlag) {
            // Get the cards that the current player can play.
            ArrayList<Card> currentCardsPlayerCanPlay = playerManager.CardsPlayerCanPlay(currentPlayerIndex);

            // If no cards can play, draw a card, otherwise play a card. If the player type three times
            // wrong card to play, the player will be punished to draw a card automatically.
            if (currentCardsPlayerCanPlay.isEmpty()) {
                System.out.println("You cannot play a card!");
                Card c = cardManager.drawCardFromUnusedDeck();
                playerManager.playerDrawCard(currentPlayerIndex, c);
            } else {
                Scanner keyboard = new Scanner(System.in);

                // rightCard indicates whether the play type a right card to play.
                boolean rightCard = true;

                // cardToPlay is the card that the player wants to play.
                // (cardToPlay is a right card to play)
                Card cardToPlay = null;

                // The number of times that the player type a wrong card.
                int wrongTimes = 0;

                // Let the player type the card to play. If type a wrong card, type again,
                // with maximum 3 times.
                do {
                    System.out.println("Enter a card to play:");
                    String cardToPlayID = keyboard.nextLine();
                    try {
                        cardToPlay = cardManager.findCard(currentCardsPlayerCanPlay, cardToPlayID);
                        rightCard = true;
                    } catch (Exception e) {
                        wrongTimes++;
                        rightCard = false;
                    }
                } while (!rightCard && wrongTimes < 3);

                // If the player types 3 times wrong card, draw a card, otherwise play the card.
                if (wrongTimes == 3) {
                    System.out.println("Enter too many times wrong cards! Draw a card for punishment.");
                    Card c = cardManager.drawCardFromUnusedDeck();
                    playerManager.playerDrawCard(currentPlayerIndex, c);
                } else {
                    playerManager.playerPlayCard(currentPlayerIndex, cardToPlay);
                }

            }

            // Determine whether the player wins or not.
            if (playerManager.winOrNot()) {
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
        Player playerWins = newGameController.runGame();
        System.out.println(playerWins.getId() + " wins!");
    }
}

package Controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

import Entity.Card;
import Entity.Player;
import UseCase.PlayerUseCase;

public class Controller {

    private PlayerUseCase playerManager;
    private CardUseCase cardManager;

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

        this.cardManager = new CardUseCase();

    }

    public Player runGame() {
        Player playerWins;

        boolean winFlag = false;

        Random rand = new Random();

        int currentPlayerIndex = rand.nextInt(this.playerManager.getPlayerNum());
        while (!winFlag) {
            ArrayList<Card> currentCardsPlayerCanPlay = playerManager.CardsPlayerCanPlay(currentPlayerIndex);
            if (currentCardsPlayerCanPlay.isEmpty()) {
                System.out.println("You cannot play a card!");
                Card c = cardManager.drawCard();
                playerManager.playerDrawCard(currentPlayerIndex, c);
            } else {
                Scanner keyboard = new Scanner(System.in);
                boolean rightCard = true;
                Card cardToPlay;
                int wrongTimes = 0;
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

                if (wrongTimes == 3) {
                    System.out.println("Enter too many times wrong cards! Draw a card for punishment.");
                    Card c = cardManager.drawCard();
                    playerManager.playerDrawCard(currentPlayerIndex, c);
                } else {
                    playerManager.playerPlayCard(currentPlayerIndex, cardToPlay);
                }

            }

            if (playerManager.winOrNot(currentPlayerIndex)) {
                winFlag = true;
                playerWins = playerManager.getPlayers()[currentPlayerIndex];
            }

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

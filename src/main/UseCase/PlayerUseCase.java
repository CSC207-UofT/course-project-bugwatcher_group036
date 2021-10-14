package main.UseCase;

import java.util.ArrayList;

import main.Entity.Card;
import main.Entity.Player;

/**
 * The player use case interact with card and player, and receive commands
 * from controller level.
 */
public class PlayerUseCase{

    private Player[] players;
    private Card lastCard;

    /**
     * Constructor of the PlayerUseCase class, with given a ArrayList of Players
     * needed to be initialized in upper level.
     *
     * @param players the players are stored in player use case, and
     *                can be called using their indices.
     */
    public PlayerUseCase(Player[] players){
        this.players = players;
        this.lastCard = new Card();
    }

    /**
     * Constructor of the PlayerUseCase class, with the number of players, numberOfPlayers.
     * needed to be initialized in upper level.
     * @param numberOfPlayers
     */
    public PlayerUseCase(int numberOfPlayers) {
        this.players = new Player[numberOfPlayers];
        this.lastCard = new Card();
    }

    /**
     * Create and return a new player with the given id and position.
     * @param id
     * @param position
     * @return
     */
    public Player createPlayer(String id, int position) {
        return new Player(id, position);
    }

    /**
     * Let specific player draw the given card.
     *
     * @param playerCount the index that indicate to which player's action it is
     * @param c the card needed to be put into player's handCard
     */
    public void playerDrawCard(int playerCount, Card c){
        try{  // if 0 <= playerCount <= 3, there would be no error reported
            players[playerCount].drawCard(c);
        }
        catch (Exception e){
            System.out.println("abnormal player count!");
        }
    }

    /**
     * Make specific player play the given card.
     *
     * @param playerCount the index that indicate to which player's action it is
     * @param c the card needed to be played
     */
    public Card playerPlayCard(int playerCount, Card c){
        try{  // if 0 <= playerCount <= 3, there would be no error reported
            lastCard = players[playerCount].playCard(c);
        }
        catch (Exception e){
            System.out.println("abnormal player count!");
            throw e;
        }
        return lastCard;
    }

    /**
     * Determining whether any player has won.
     * If the outcome is true, the controller will know that it's time to cease the game.
     *
     * @return the boolean that indicates whether any player has won the game
     */
    public boolean winOrNot(){
        for (Player p: players){
            if (p.getCardNum() == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if the player with the given index wins.
     * @param playerCount
     * @return
     */
    public boolean winOrNot(int playerCount){

        return (players[playerCount].getCardNum() == 0);
    }

    /**
     * Determine whether a player has playable card in his/her hand.
     *
     * @param playerCount to indicate which player we want to check
     * @return boolean that indicates whether this player has playable card in hand
     */
    public boolean playerCanPlayCard(int playerCount){
        Player p = players[playerCount];
        ArrayList<Card> handCard = p.getHandCard();

        for (Card c: handCard){
            // pass if it's default card or match the condition (either color or number matches)
            // or the card color is black
            if ((c.getColor().equals(lastCard.getColor()) || c.getNumber() == lastCard.getNumber()) ||
                    lastCard.getColor().equals("black") ){
                return true;
            }
        }

        return false;
    }

    /**
     * Return the ArrayList of Cards that the given player could player in the current tound.
     * @param playerCount
     * @return
     */
    public ArrayList<Card> CardsPlayerCanPlay(int playerCount) {
        Player p = players[playerCount];
        ArrayList<Card> handCard = p.getHandCard();
        ArrayList<Card> CardsCanPlay = new ArrayList<Card>();
        for (Card c: handCard) {
            // pass if it's default card or match the condition (either color or number matches)
            // or the card color is black
            if ((c.getColor().equals(lastCard.getColor()) || c.getNumber() == lastCard.getNumber()) ||
                    lastCard.getColor().equals("black")) {
                CardsCanPlay.add(c);
            }
        }
        return CardsCanPlay;
    }

    /**
     * Renew the last card with the given card c.
     * @param c
     */
    public void renewLastCard(Card c) {
        lastCard = c;
    }

    /**
     * getter method for players
     *
     * @return attribute players
     */
    public Player[] getPlayers(){
        return players;
    }

    /**
     * getter method for lastCard
     *
     * @return attribute lastCard
     */
    public Card getLastCard(){
        return lastCard;
    }

    /**
     * getter method for number of players
     *
     * @return the size of the attribute player
     */
    public int getPlayerNum(){
        return players.length;
    }

    public ArrayList<Card> getHandCard(int playerCount) {
        return players[playerCount].getHandCard();
    }

}
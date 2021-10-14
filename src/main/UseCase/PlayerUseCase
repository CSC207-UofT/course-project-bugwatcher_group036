package UseCase;

import java.util.ArrayList;

import Entity.Card;
import Entity.Player;

/**
 * The player use case interact with card and player, and receive commands
 * from controller level.
 */
public class PlayerUseCase{

    private Player[] players;
    private Card lastCard = new Card();

    /**
     * Constructor of the PlayerUseCase class, with one attribute "Player"
     * needed to be initialized in upper level.
     *
     * @param numOfPlayers the players are stored in player use case, and
     *                can be called using their indicies
     */
    public PlayerUseCase(int numOfPlayers){
        this.players = new Player[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++){
            players[i] = new Player(Integer.toString(i), i);
        }
        this.lastCard = new Card();
    }

    public PlayerUseCase(Player[] players){
        this.players = players;
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
    public void playerPlayCard(int playerCount, Card c){
        try{  // if 0 <= playerCount <= 3, there would be no error reported
            lastCard = players[playerCount].playCard(c);
        }
        catch (Exception e){
            System.out.println("abnormal player count!");
        }
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
            if (lastCard.equals(new Card()) ||
                    (c.getColor().equals(lastCard.getColor()) || c.getNumber() == lastCard.getNumber()) ||
                    c.getColor().equals("black") ){
                return true;
            }
        }

        return false;
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

    public Player createPlayer(String playerID, int i) {
        return new Player(playerID, i);
    }

    /**
     * return ArrayList of Card that can be played currently
     * @param currentPlayerIndex indicate which player we want to get info
     * @return ArrayList that contains all cards can be played.
     */
    public ArrayList<Card> CardsPlayerCanPlay(int currentPlayerIndex) {
        Player p = players[currentPlayerIndex];
        ArrayList<Card> handCard = p.getHandCard();
        ArrayList<Card> res = new ArrayList<Card>();

        for (Card c: handCard){
            if (lastCard.equals(new Card()) ||
                    (c.getColor().equals(lastCard.getColor()) || c.getNumber() == lastCard.getNumber()) ||
                    c.getColor().equals("black") ){
                res.add(c);
            }
        }
        return res;
    }
}
package Controller;

import Entity.CardHolder;
import UseCase.GameBoard;
import UseCase.GameResponse;
import UseCase.IPresenter;

/**
 * Terminal used by dealer.
 * As dealer is in use case level,
 * we put this terminal in entity level for clean architecture.
 */
public class Presenter implements IPresenter {

    private Controller controller;
    private GameResponse gameResponse;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setGameResponse(GameResponse gameResponse) {
        this.gameResponse = gameResponse;
    }

    public void beginStage(){
        System.out.println();
        System.out.println("Current Player:" + gameResponse.getIds().get(
                gameResponse.getGameBoard().getGameStatus().getCurrentPlayerIndex()));
    }

    public void playStage(){
        GameBoard gameBoard = gameResponse.getGameBoard();
        CardHolder playableCards = gameResponse.getCardHolder();

        int currentPlayerIndex = gameBoard.getGameStatus().getCurrentPlayerIndex();
        System.out.println("Last Card: " + gameBoard.getCardChecker().getLastCard());
        System.out.println(
                "The cards you have: " + gameBoard.getGameCardHolders().getHandCards(currentPlayerIndex));
        System.out.println("The cards you can play: " + playableCards);
    }

    public void getCardToPlay(){
        System.out.println("play a card, type \"draw\" to draw a card, or type \"quit\" to leave:");
        controller.getCardToPlay();
    }

    public void printString(String message){
        System.out.println(message); // print sentence message
    }

    public void drawCardNotification(String drawn, boolean noCard){
        // only print this sentence when drawing due to no card playable
        if (noCard) System.out.println("Cannot play a card! Draw one more card");
        System.out.println("The card you draw is " + drawn);
    }

    public void setColor() {
        System.out.println("Type a color you want to set:");
        controller.typeSetColor();
    }

    public void setColorForComputer() {
        controller.typeSetColorForComputer();
    }

    public void wrongColor() {
        System.out.println("Wrong color! Type again:");
    }
    public void wrongThreeTimes() {
        System.out.println("Wrong color 3 times! Color randomly chosen");
    }

    public void colorIsSet(String color) {
        System.out.println("Color " + color + " is set.");
    }

    public void inputIDs() {
        controller.inputIDs();
    }

    public void inputIDsForComputer() {
        controller.inputIDsForComputer();
    }

    public void howManyPlayers() {
        System.out.println("How many players here? ");
    }

    public void oneToSix() {
        System.out.println("Sorry, we only support 1 player - 6 players, please re-enter player count.");
    }

    public void enterIDCom() {
        System.out.println("Please enter your player id:");
    }

    public void enterIDP(int i) {
        System.out.println("Please enter id for player" + (i + 1));
    }


}

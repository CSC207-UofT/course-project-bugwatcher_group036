package Controller;

import UseCase.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Controller {

    private final Scanner input = new Scanner(System.in);
    private Presenter iPresenter;
    private GameRunner gameRunner;
    private GameRequest gameRequest;


    public Controller(Presenter iPresenter, GameRequest gameRequest) {

        this.gameRunner = new GameRunner(false, iPresenter, gameRequest);
        this.iPresenter = iPresenter;
        this.gameRequest = gameRequest;

    }
    public Controller(Presenter iPresenter, ArrayList<String> ids) {
        this.gameRequest = new GameRequest();
        this.gameRunner = new GameRunner(false, iPresenter, gameRequest, ids);
        this.iPresenter = iPresenter;
        this.setiGameInput(gameRunner);
        gameRunner.buildIEachRound(gameRunner.getGameResponse().getGameBoard(), iPresenter, gameRequest);
        gameRunner.setGameResponse(gameRunner.getGameResponse());
        setplayerID(ids);
        iPresenter.setController(this);
        iPresenter.setGameResponse(gameRunner.getGameResponse());
        iPresenter.setGameRequest(gameRequest);

    }
    public GameRequest getGameRequest(){return gameRequest;}

    public GameRunner getGameRunner() {
        return gameRunner;
    }

    public void setiGameInput(GameRunner iGameInput) {
        this.gameRunner = iGameInput;
    }

    public void getCardToPlay() {
        String getCardToPlay = input.nextLine();
        gameRequest.setGetCardToPlay(getCardToPlay);
    }
    public void typeSetColorGUI(String setColor) {
        gameRequest.setSetColor(setColor);
        iPresenter.colorIsSet(setColor);
    }

    public void typeSetColor() {
        ArrayList<String> colors = new ArrayList<>();
        Collections.addAll(colors, "red", "blue", "yellow", "green");

        String setColor = input.nextLine();
        int wrongTimeCounter = 0;

        while (wrongTimeCounter < 3) {
            if (!colors.contains(setColor) && wrongTimeCounter < 2) {
                iPresenter.wrongColor();
                setColor = input.nextLine();
            } else if (!colors.contains(setColor) && wrongTimeCounter == 2) {
                iPresenter.wrongThreeTimes();
                setColor = colors.get((int) (Math.random() * colors.size()));
            } else {
                break;
            }
            wrongTimeCounter++;
        }
        gameRequest.setSetColor(setColor);
        iPresenter.colorIsSet(setColor);
    }

    public void typeSetColorForComputer() {
        ArrayList<String> colors = new ArrayList<>();
        Collections.addAll(colors, "red", "blue", "yellow", "green");
        Random rand = new Random();
        String color = colors.get(rand.nextInt(4));

        gameRequest.setSetColorForComputer(color);
    }

    public void setplayerID(ArrayList<String> ids) {
        gameRequest.setIds(ids);
    }

    public void inputIDsGUI(boolean computer, ArrayList<String> ids) {

        gameRequest.setIds(ids);
        iPresenter.howManyPlayersGUI(computer);

    }
    public void inputIDs() {
        Scanner input = new Scanner(System.in);
        ArrayList<String> ids = new ArrayList<>();
        iPresenter.howManyPlayers();
        int numberOfPlayer = input.nextInt();

        if (numberOfPlayer < 1 || numberOfPlayer > 6) {
            iPresenter.oneToSix();
            numberOfPlayer = input.nextInt();
        }
        input.nextLine(); // equals to "\n", otherwise would lead to bug for nextLine.

        for (int i = 0; i < numberOfPlayer; i++){
            iPresenter.enterIDP(i);
            String id = input.nextLine();
            ids.add(id);
        }

        gameRequest.setIds(ids);
    }

    public void inputIDsForComputer(){
        Scanner input = new Scanner(System.in);
        ArrayList<String> ids = new ArrayList<>();
        iPresenter.howManyPlayers();
        int numberOfPlayer = input.nextInt();

        if (numberOfPlayer < 1 || numberOfPlayer > 6) {
            iPresenter.oneToSix();
            numberOfPlayer = input.nextInt();
        }
        input.nextLine(); // equals to "\n", otherwise would lead to bug for nextLine.

        iPresenter.enterIDCom();
        String playerID = input.nextLine();
        ids.add(playerID);

        for (int i = 1; i < numberOfPlayer; i++){
            ids.add("Computer " + Integer.toString(i));
        }

        gameRequest.setIds(ids);
    }

}
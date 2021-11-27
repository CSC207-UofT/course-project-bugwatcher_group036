package Controller;

import Entity.CardHolder;
import UI.GameFrame;
import UseCase.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Controller {

    private Scanner input = new Scanner(System.in);
    private Presenter presenter;
    private GameRunner gameRunner;
    private GameRequest gameRequest;


    public Controller(Presenter presenter, GameRequest gameRequest) {

        this.gameRunner = new GameRunner(false, presenter, gameRequest);
        this.presenter = presenter;
        this.gameRequest = gameRequest;

    }
    public Controller(Presenter presenter, ArrayList<String> ids) {
        this.gameRequest = new GameRequest();
        this.gameRunner = new GameRunner(false, presenter, gameRequest, ids);
        this.presenter = presenter;
        this.setiGameInput(gameRunner);
        gameRunner.buildIEachRound(gameRunner.getGameResponse().getGameBoard(), presenter, gameRequest);
        gameRunner.setGameResponse(gameRunner.getGameResponse());
        setplayerID(ids);
        presenter.setController(this);
        presenter.setGameResponse(gameRunner.getGameResponse());
        presenter.setGameRequest(gameRequest);

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
        presenter.colorIsSet(setColor);
    }

    public void typeSetColor() {
        ArrayList<String> colors = new ArrayList<>();
        Collections.addAll(colors, "red", "blue", "yellow", "green");

        String setColor = input.nextLine();
        int wrongTimeCounter = 0;

        while (wrongTimeCounter < 3) {
            if (!colors.contains(setColor) && wrongTimeCounter < 2) {
                presenter.wrongColor();
                setColor = input.nextLine();
            } else if (!colors.contains(setColor) && wrongTimeCounter == 2) {
                presenter.wrongThreeTimes();
                setColor = colors.get((int) (Math.random() * colors.size()));
            } else {
                break;
            }
            wrongTimeCounter++;
        }
        gameRequest.setSetColor(setColor);
        presenter.colorIsSet(setColor);
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
        presenter.howManyPlayersGUI(computer);

    }
    public void inputIDs() {
        Scanner input = new Scanner(System.in);
        ArrayList<String> ids = new ArrayList<>();
        presenter.howManyPlayers();
        int numberOfPlayer = input.nextInt();

        if (numberOfPlayer < 1 || numberOfPlayer > 6) {
            presenter.oneToSix();
            numberOfPlayer = input.nextInt();
        }
        input.nextLine(); // equals to "\n", otherwise would lead to bug for nextLine.

        for (int i = 0; i < numberOfPlayer; i++){
            presenter.enterIDP(i);
            String id = input.nextLine();
            ids.add(id);
        }

        gameRequest.setIds(ids);
    }

    public void inputIDsForComputer(){
        Scanner input = new Scanner(System.in);
        ArrayList<String> ids = new ArrayList<>();
        presenter.howManyPlayers();
        int numberOfPlayer = input.nextInt();

        if (numberOfPlayer < 1 || numberOfPlayer > 6) {
            presenter.oneToSix();
            numberOfPlayer = input.nextInt();
        }
        input.nextLine(); // equals to "\n", otherwise would lead to bug for nextLine.

        presenter.enterIDCom();
        String playerID = input.nextLine();
        ids.add(playerID);

        for (int i = 1; i < numberOfPlayer; i++){
            ids.add("Computer " + Integer.toString(i));
        }

        gameRequest.setIds(ids);
    }

}
package UI;

import Controller.Controller;
import Controller.Presenter;
import UseCase.GameRequest;
import UseCase.GameResponse;
import UseCase.IPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PVEFrame extends JFrame implements ActionListener {
    IPresenter presenter;
    Controller controller;
    GameRequest gameRequest;
    GameResponse gameResponse;
    public PVEFrame(IPresenter presenter, Controller controller, GameRequest gameRequest, GameResponse gameResponse) {
        this.presenter = presenter;
        this.controller = controller;
        this.gameRequest = gameRequest;
        this.gameResponse = gameResponse;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

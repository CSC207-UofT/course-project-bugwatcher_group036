package UI;

import Controller.*;
import UseCase.GameRequest;
import UseCase.GameResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NumofPlayerComputerFrame extends JFrame implements ActionListener {
    JLabel Username = new JLabel();
    JButton enterbutton = new JButton();
    JTextField NumberPlayer = new JTextField();
//    IPresenter presenter;
//    Controller controller;
//    GameRequest gameRequest;
//    GameResponse gameResponse;

    public NumofPlayerComputerFrame() {

        Username.setText("Number of Computer:");
        Username.setBounds(20, 30, 300, 20);

        enterbutton.setText("Enter");
        enterbutton.setBounds(110, 80, 100, 50);
        enterbutton.addActionListener(this);
        enterbutton.setFocusable(false);

        NumberPlayer.addActionListener(this);
        NumberPlayer.setBounds(200, 30, 100, 20);


        this.setTitle("Number of Computer");
        this.setVisible(true);
        this.setLayout(null);
        this.setFocusable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(320, 160);
        this.setLocation(new Point(500, 200));
        this.add(Username);
        this.add(enterbutton);
        this.add(NumberPlayer);
//        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterbutton){
            try {
                int num = Integer.parseInt(NumberPlayer.getText());

                if (num > 1 && num < 7){
                    this.dispose();
                    ArrayList<String> ids = new ArrayList<>();
                    String id = JOptionPane.showInputDialog(null, "Player Name: ",
                            "Player Name ", JOptionPane.INFORMATION_MESSAGE);
                    ids.add(id);
                    for (int i = 1; i <= num; i++){
                        ids.add("Computer " + Integer.toString(i));
                    }
//                    controller.setplayerID(ids);
                    Presenter presenter = new Presenter();
                    Controller controller = new Controller(presenter, ids);
                    GameResponse gameResponse = controller.getGameRunner().getGameResponse();
                    presenter.setController(controller);
                    presenter.setGameResponse(gameResponse);
                    GUITerminal terminal = new GUITerminal(presenter, controller, gameResponse, ids);


//                    PVEFrame pveFrame = new PVEFrame(presenter, controller, gameRequest, gameResponse);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Sorry, we only support 1 player -" +
                            " 6 players, please re-enter player count.");
                }
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(null, "Sorry, we only support 1 player -" +
                        " 6 players, please re-enter player count.");
            }
        }

    }

}

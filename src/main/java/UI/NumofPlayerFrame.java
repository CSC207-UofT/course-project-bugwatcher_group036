package UI;

import Controller.Controller;
import UseCase.GameRequest;
import UseCase.GameResponse;
import Controller.*;
import UseCase.GameRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NumofPlayerFrame extends JFrame implements ActionListener {
    JLabel Username = new JLabel();
    JButton enterbutton = new JButton();
    JTextField NumberPlayer = new JTextField();
//    IPresenter presenter;
//    Controller controller;
//    GameRequest gameRequest;
//    GameResponse gameResponse;

    public NumofPlayerFrame() {
//        this.presenter = presenter;
//        this.controller = controller;
//        this.gameRequest = gameRequest;
//        this.gameResponse = gameResponse;

        Username.setText("Number of Players:");
        Username.setBounds(20, 30, 300, 20);

        enterbutton.setText("Enter");
        enterbutton.setBounds(110, 80, 100, 50);
        enterbutton.addActionListener(this);
        enterbutton.setFocusable(false);

        NumberPlayer.addActionListener(this);
        NumberPlayer.setBounds(200, 30, 100, 20);


        this.setTitle("Number of Players");
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
                    for (int i = 1; i <= num; i++){
                        String id = JOptionPane.showInputDialog(null, "Player Name " + i,
                                "Player Name " + i, JOptionPane.INFORMATION_MESSAGE);
                        ids.add(id);
                    }
                    Presenter presenter = new Presenter();
                    Controller controller = new Controller(presenter, ids);



                    PVPFrame frame = new PVPFrame(presenter,controller);

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

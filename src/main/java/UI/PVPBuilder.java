package UI;

import Controller.Controller;
import Controller.*;
import LogIn.LogInEntity.UserStatistics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PVPBuilder extends JFrame implements ActionListener, ModeBuilder {
    JLabel Username = new JLabel();
    JButton enterbutton = new JButton();
    JTextField NumberPlayer = new JTextField();
    private UserStatistics stats;

    public void buildGameFrame(UserStatistics stats) {
        this.stats = stats;

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
    }

    public Presenter buildPresenter() {
        return new Presenter();
    }

    public Controller buildController(int playerNum, Presenter presenter) {
        ArrayList<String> ids = new ArrayList<>();
        for (int i = 1; i <= playerNum; i++) {
            String id = JOptionPane.showInputDialog(null, "Player Name " + i,
                    "Player Name " + i, JOptionPane.INFORMATION_MESSAGE);
            ids.add(id);
        }
        return new Controller(presenter, ids);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterbutton){
            try {
                int num = Integer.parseInt(NumberPlayer.getText());

                if (num > 1 && num < 7){
                    this.dispose();
                    Presenter presenter = buildPresenter();
                    Controller controller = buildController(num, presenter);

                    PVPFrame frame = new PVPFrame(presenter, controller, stats);

                }
                else {
                    JOptionPane.showMessageDialog(null, "Sorry, we only support 2 players -" +
                            " 6 players, please re-enter player count.");
                }
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(null, "Sorry, we only support 2 players -" +
                        " 6 players, please re-enter player count.");
            }
        }

    }

}
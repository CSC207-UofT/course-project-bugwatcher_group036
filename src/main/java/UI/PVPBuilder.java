package UI;

import Controller.Controller;
import Controller.*;
import LogIn.LogInEntity.UserStatistics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The PVEBuilder.
 */
public class PVPBuilder extends JFrame implements ActionListener, ModeBuilder {
    JLabel Username = new JLabel();
    JButton enterbutton = new JButton();
    JTextField NumberPlayer = new JTextField();
    private UserStatistics stats;

    /**
     * The Builder for PVP Game Frame
     * @param stats The user's statistics
     */
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

    /**
     * The builder for GUI Presenter
     * @return The presenter of GUI
     */
    public Presenter buildPresenter() {
        return new Presenter();
    }

    /**
     * The builder of Controller for the game
     * @param playerNum Total number of player
     * @param presenter The presenter of GUI
     * @return The Controller of the game
     */
    public Controller buildController(int playerNum, Presenter presenter) {
        ArrayList<String> ids = new ArrayList<>();
        for (int i = 1; i <= playerNum; i++) { // Add all players name
            String id = JOptionPane.showInputDialog(null, "Player Name " + i,
                    "Player Name " + i, JOptionPane.INFORMATION_MESSAGE);
            ids.add(id);
        }
        return new Controller(presenter, ids);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterbutton){
            try { // Check whether the player enter a correct number of player
                int num = Integer.parseInt(NumberPlayer.getText());

                if (num > 1 && num < 7){
                    this.dispose();
                    Presenter presenter = buildPresenter(); // Build the Presenter
                    Controller controller = buildController(num, presenter); // Build the Controller

                    PVPFrame frame = new PVPFrame(presenter, controller, stats);

                }
                else { // When the player enter too big number of players
                    JOptionPane.showMessageDialog(null, "Sorry, we only support 2 players -" +
                            " 6 players, please re-enter player count.");
                }
            } catch (NumberFormatException numberFormatException) { // When the player enter a non-number
                JOptionPane.showMessageDialog(null, "Sorry, we only support 2 players -" +
                        " 6 players, please re-enter player count.");
            }
        }

    }

}

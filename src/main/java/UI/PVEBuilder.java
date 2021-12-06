package UI;

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
public class PVEBuilder extends JFrame implements ActionListener, ModeBuilder {
    JLabel Username = new JLabel();
    JButton enterbutton = new JButton();
    JTextField ComputerPlayer = new JTextField();
    private UserStatistics stats;

    /**
     * The Builder for PVE Game Frame
     * @param stats The user's statistics
     */
    public void buildGameFrame(UserStatistics stats) {
        this.stats = stats;

        Username.setText("Number of Computer:");
        Username.setBounds(20, 30, 300, 20);

        enterbutton.setText("Enter");
        enterbutton.setBounds(110, 80, 100, 50);
        enterbutton.addActionListener(this);
        enterbutton.setFocusable(false);

        ComputerPlayer.addActionListener(this);
        ComputerPlayer.setBounds(200, 30, 100, 20);


        this.setTitle("Number of Computer");
        this.setVisible(true);
        this.setLayout(null);
        this.setFocusable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(320, 160);
        this.setLocation(new Point(500, 200));
        this.add(Username);
        this.add(enterbutton);
        this.add(ComputerPlayer);
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
        String id = JOptionPane.showInputDialog(null, "Player Name: ",
                "Player Name ", JOptionPane.INFORMATION_MESSAGE);
        ids.add(id);
        for (int i = 1; i <= playerNum; i++){// Add all computer name
            ids.add("Computer " + i);
        }
        return new Controller(presenter, ids);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterbutton){
            try {// Check whether the player enter a correct number of player
                int num = Integer.parseInt(ComputerPlayer.getText());

                if (num > 0 && num <= 5){
                    this.dispose();

                    Presenter presenter = buildPresenter(); // Build the Presenter
                    Controller controller = buildController(num, presenter); // Build the Controller


                    PVEFrame pveFrame = new PVEFrame(presenter, controller, stats);
                }
                else { // When the player enter too big number of players
                    JOptionPane.showMessageDialog(null, "Sorry, we only support 1 computer player -" +
                            " 5 players, please re-enter player count.");
                }
            } catch (NumberFormatException numberFormatException) {// When the player enter a non-number
                JOptionPane.showMessageDialog(null, "Sorry, we only support 1 player -" +
                        " 6 players, please re-enter player count.");
            }
        }

    }

}

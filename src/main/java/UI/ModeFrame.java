package UI;

import LogIn.LogInEntity.UserStatistics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The ModeFrame.
 */
public class ModeFrame extends JFrame implements ActionListener {
    JPanel panel = new JPanel();
    JLabel label = new JLabel("PVP or PVE"); // Title for the label
    JButton pvp = new JButton("PVP"); // The button for PVP
    JButton pve = new JButton("PVE"); // THe button for PVE
    JButton userStats = new JButton("Game Statistics"); // The button for user statistics
    Color darkRed = new Color(15, 29, 101);
    Color white = new Color(255, 255, 255);

    private final UserStatistics stats;

    /**
     * Modeframe for the Game
     * @param stats The user statistics.
     */
    public ModeFrame(UserStatistics stats) {
        this.stats = stats;

        label.setBackground(white);
        label.setFont(new Font("Times", Font.BOLD, 40));
        label.setForeground(darkRed);
        label.setOpaque(true);
        label.setBounds(0, 0, 0, 0);
        label.setHorizontalAlignment(0);
        label.setVerticalAlignment(0);
        panel.add(label);

        pvp.setFont(new Font("Times", Font.PLAIN, 25));
        pvp.setForeground(darkRed);
        pvp.addActionListener(this);
        pvp.setBounds(20, 10, 10, 10);
        panel.add(pvp);
        pve.setFont(new Font("Times", Font.PLAIN, 25));
        pve.setForeground(darkRed);
        pve.addActionListener(this);
        pve.setBounds(20, 20, 20, 20);
        panel.add(pve);

        userStats.setFont(new Font("Times", Font.PLAIN, 20));
        userStats.setForeground(Color.BLUE);
        userStats.addActionListener(this);
        userStats.setBounds(50, 20, 20, 20);
        panel.add(userStats);

        panel.setBackground(white);

        this.add(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // To ensure frame can close

        this.setSize(300, 200); // Set the size of the frame
        this.setLocation(new Point(500, 200));
        this.setVisible(true); // Set the frame to be visible

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (pvp == e.getSource()) { // When the player choose PVP mode
            this.dispose();
            PVPBuilder frame = new PVPBuilder();
            frame.buildGameFrame(stats);

        } else if (pve == e.getSource()) { // When the player choose PVE mode
            this.dispose();
            PVEBuilder frame = new PVEBuilder();
            frame.buildGameFrame(stats);

        } else if (userStats == e.getSource()) { // When the player wants to see their own statistics
            JOptionPane.showMessageDialog(null,
                    stats.toString());
        }
    }
}
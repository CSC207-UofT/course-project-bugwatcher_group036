package UI;

import LogIn.LogInEntity.UserStatistics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModeFrame extends JFrame implements ActionListener {
    JPanel panel = new JPanel();
    JLabel label = new JLabel("PVP or PVE");
    JButton pvp = new JButton("PVP");
    JButton pve = new JButton("PVE");
    JButton userStats = new JButton("Game Statistics");
    Color darkRed = new Color(15, 29, 101);
    Color white = new Color(255, 255, 255);

    private UserStatistics stats;

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
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setSize(300, 250);
        this.setLocation(new Point(500, 200));
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (pvp == e.getSource()) {
            this.dispose();
            NumofPlayerFrame frame = new NumofPlayerFrame(stats);

        } else if (pve == e.getSource()) {
            this.dispose();
            NumofPlayerComputerFrame frame = new NumofPlayerComputerFrame(stats);

        } else if (userStats == e.getSource()) {
            JOptionPane.showMessageDialog(null,
                    stats.toString());
        }
    }
}
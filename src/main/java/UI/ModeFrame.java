package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeFrame extends JFrame implements ActionListener {
    JPanel panel = new JPanel();
    JLabel label = new JLabel("PVP or PVE");
    JButton pvp = new JButton("PVP");
    JButton pve = new JButton("PVE");
    Color darkRed = new Color(15, 29, 101);
    Color white = new Color(255, 255, 255);


    public ModeFrame() {


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

        panel.setBackground(white);

        this.add(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setSize(300, 200);
        this.setLocation(new Point(500, 200));
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (pvp == e.getSource()) {
            this.dispose();
            NumofPlayerFrame frame = new NumofPlayerFrame();

        } else if (pve == e.getSource()) {
            this.dispose();
            NumofPlayerComputerFrame frame = new NumofPlayerComputerFrame();

        }
    }
}
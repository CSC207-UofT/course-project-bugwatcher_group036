package UI;

import LogIn.LogInEntity.UserStatistics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Loseframe extends JFrame implements ActionListener{
    JButton playagain = new JButton();
    UserStatistics stats;
    public Loseframe(String name, UserStatistics stats) {
        this.stats = stats;
        this.setSize(new Dimension(540, 360));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(startP(name));

        playagain.setForeground(new Color(0,0,0));
        playagain.setBounds(198, 225, 144, 70);
        playagain.addActionListener(this);
        playagain.setText("Play Again!");
        playagain.setFont(new Font("Times", Font.BOLD, 20));

        this.add(playagain);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playagain) {
            this.dispose();
            ModeFrame frame = new ModeFrame(stats);
        }
    }

    public static class HomePanel extends JPanel {
        ImageIcon icon;
        Image img;

        public HomePanel() {
            //store one image in bin of executing program
            icon = new ImageIcon("src/main/java/DataSet/YouLose.jpg");
            img = icon.getImage();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //to let background picture adjust along with the size of the window
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            g.drawString("text", 100, 100);
        }
    }

    //Jpanel to return to the starting page
    public JPanel startP(String name) {
        JPanel p = new HomePanel();
        JLabel player = new JLabel(name + " Wins", SwingConstants.CENTER);

        player.setFont(new Font("SansSerif", Font.PLAIN, 40));
        player.setBounds(18, 80, 500, 70);
        player.setHorizontalTextPosition(SwingConstants.CENTER);
        player.setForeground(new Color(10, 225, 207));

        p.add(player);
        p.setLayout(null);

        return p;
    }
}
package UI;

import javax.swing.*;
import java.awt.*;


public class Loseframe extends JFrame {

    public Loseframe(String name) {


        this.setSize(new Dimension(500, 440));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(startP(name));

        this.setVisible(true);


    }
    public class HomePanel extends JPanel {
        ImageIcon icon;
        Image img;

        public HomePanel() {
            //store one image in bin of executing program
            icon = new ImageIcon("src/main/java/DataSet/YouLose.jpeg");
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
        player.setBounds(20, 30, 500, 70);
        player.setHorizontalTextPosition(SwingConstants.CENTER);
        player.setForeground(new Color(10, 225, 207));
        p.add(player);
        p.setLayout(null);

        return p;
    }
}
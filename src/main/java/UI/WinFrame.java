package UI;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class WinFrame extends JFrame {

    public WinFrame(String id) {
//        ImagePanel panel = new ImagePanel("/src/main/java/DataSet/YouWinPic.png");
        JPanel frame = new JPanel();
        JLabel winnerInfo = new JLabel();

        winnerInfo.setForeground(Color.BLACK);
        winnerInfo.setBounds(0, 0, 500, 300);
        winnerInfo.setFont(new Font("Times", Font.BOLD, 50));
        winnerInfo.setText(id + " wins!");
        this.add(frame);
        this.add(winnerInfo);
        this.setSize(540, 360);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(startP());
        this.setVisible(true);


    }
    public class HomePanel extends JPanel {
        ImageIcon icon;
        Image img;

        public HomePanel() {
            //store one image in bin of executing program
            icon = new ImageIcon("src/main/java/UI/YouWinPic.png");
            img = icon.getImage();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //to let background picture adjust along with the size of the window
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    //Jpanel to return to the starting page
    public JPanel startP() {
        JPanel p = new HomePanel();
        p.setLayout(null);

        return p;
    }
}

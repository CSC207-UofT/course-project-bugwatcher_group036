package UI;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class WinFrame extends JFrame {

    public WinFrame() {
//        ImagePanel panel = new ImagePanel("/src/main/java/DataSet/YouWinPic.png");
        JPanel frame = new JPanel();
        this.add(frame);
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
            //存放在正在编写的项目的bin文件夹下的一个图片
            icon = new ImageIcon("src/main/java/UI/YouWinPic.png");
            img = icon.getImage();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    //返回大厅界面的Jpanel
    public JPanel startP() {
        JPanel p = new HomePanel();
        p.setLayout(null);

        return p;
    }
}

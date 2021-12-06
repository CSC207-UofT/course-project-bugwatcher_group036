package UI;

import LogIn.LogInEntity.UserStatistics;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * The Loseframe.
 */
public class Loseframe extends JFrame implements ActionListener{
    private Clip clip;
    JButton playagain = new JButton(); // The button when user want to play again
    UserStatistics stats;

    /**
     * Lose frame for player lose
     * @param name the name of player that wins
     * @param stats the user's statistics
     */
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
        this.setVisible(true); // Set the frame to be visible

        try {
            AudioInputStream input = AudioSystem.getAudioInputStream( // The music background when the player lose
                    new File("src/main/java/DataSet/bgm6.wav"));
            clip = AudioSystem.getClip();
            clip.open(input);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ignored) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playagain) { //When the player wants to play again
            this.dispose();
            clip.stop();
            clip.close();
            ModeFrame frame = new ModeFrame(stats);
        }
    }

    public static class HomePanel extends JPanel { // To implement the background image for the frame
        ImageIcon icon;
        Image img;

        public HomePanel() {

            icon = new ImageIcon("src/main/java/DataSet/YouLose.jpg"); // To transform the picture to Icon
            img = icon.getImage();
        }

        public void paintComponent(Graphics g) { // Set the background for the frame
            super.paintComponent(g);
            //Set the picture with the same as the size of the frame
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    /**
     * The frame of the panel.
     * @param name the name of player that wins
     * @return The frame of the panel
     */
    public JPanel startP(String name) {
        JPanel p = new HomePanel();
        JLabel player = new JLabel(name + " Wins", SwingConstants.CENTER); // The player name that win the game

        player.setFont(new Font("SansSerif", Font.PLAIN, 40));
        player.setBounds(18, 80, 500, 70);
        player.setHorizontalTextPosition(SwingConstants.CENTER);
        player.setForeground(new Color(10, 225, 207));

        p.add(player);
        p.setLayout(null);

        return p;
    }
}
package UI;

import Controller.*;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class PVPFrame extends JFrame implements ActionListener{
    Presenter presenter;
    Controller controller;

    JPanel frame = new JPanel();
    JLabel currentcard = new JLabel();
    JLabel remainingcards = new JLabel();
    JLabel playerCardCounts = new JLabel();
    JLabel id = new JLabel();
    JLabel bottom = new JLabel();
    JPanel cardHas = new JPanel();
    JButton next = new JButton("next");
    ButtonGroup buttonGroup = new ButtonGroup();

    public PVPFrame(Presenter presenter, Controller controller){
        this.presenter = presenter;
        this.controller = controller;

        currentcard.setHorizontalAlignment(0);//Center the text
        currentcard.setBounds(450, 50, 144, 216);//set the location and size of JLabel
        currentcard.setText(presenter.getGameRunner().getGameResponse().getGameBoard().getCardChecker().getLastCard());
        ImageIcon icon1 = new ImageIcon("src/main/java/DataSet/Card Image/black.png");
        Image img1 = icon1.getImage();
        Image newImg1 = img1.getScaledInstance(144, 216,  java.awt.Image.SCALE_SMOOTH) ;
        icon1 = new ImageIcon(newImg1);
        currentcard.setIcon(icon1);

        remainingcards.setForeground(Color.RED);
        remainingcards.setBounds(30, 50, 300, 50);//set the location and size of JLabel
        remainingcards.setFont(new Font("Times", Font.BOLD, 30));
        remainingcards.setText("Remaining Cards: " + presenter.RemainingCards());

        id.setForeground(Color.RED);
        id.setBounds(30, 100, 300, 50);//set the location and size of JLabel
        id.setFont(new Font("Times", Font.BOLD, 30));
        ArrayList<String> playerIds = presenter.getGameRunner().getGameResponse().getIds();
        int currentPosition = presenter.getGameRunner().getGameResponse().getGameBoard().getGameStatus().getCurrentPlayerIndex();
        id.setText("Current Player: " + playerIds.get(currentPosition));

        playerCardCounts.setForeground(Color.RED);
        playerCardCounts.setBounds(30, 150, 400, 300);
        playerCardCounts.setFont(new Font("Times", Font.BOLD, 20));
        StringBuilder countText = new StringBuilder("<html>");
        for (int i  = 0; i < playerIds.size(); i++) {
            int cardCount = presenter.getGameRunner().getEachRound().getGameBoard().
                    getGameCardHolders().getHandCards(i).getSize();
            if (i != currentPosition) {
                countText.append(playerIds.get(i)).append(" has ").append(cardCount).append(" card(s).<br>");
            } else {
                countText.append("You").append(" have ").append(cardCount).append(" card(s).<br>");
            }
        }
        playerCardCounts.setText(countText.substring(0, countText.length() - 4) + "<html>");

        bottom.setBounds(28, 244, 680, 210);//set the location and size of JPanel

        try {
            AudioInputStream input = AudioSystem.getAudioInputStream(
                    new File("src/main/java/DataSet/Background Music.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(input);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ignored) {
        }


        // add cardHas panel

        cardHas.setBorder(new LineBorder(new Color(0, 0, 0)));//set the border
        cardHas.setBounds(30, 400, 620, 210);
        cardHas.setPreferredSize(new Dimension(570, 200));

        FlowLayout fl_cardHas = (FlowLayout) cardHas.getLayout();
        fl_cardHas.setAlignment(FlowLayout.LEADING);


        presenter.allhandcards().forEach(c -> {
            JToggleButton button = new JToggleButton(c);
            buttonGroup.add(button);
            if (!presenter.getGameRunner().getEachRound().beginStage().toString().contains(button.getText())) {
                button.setEnabled(false);
            }
                button.addActionListener(this);
            button.setBounds(20, 20, 20, 20);
            cardHas.add(button);
                }
            );

        this.add(cardHas);

        next.setBounds(450, 300, 150, 70);
        next.addActionListener(this);
//        draw.setBounds(150, 300, 150, 70);
//        draw.addActionListener(this);

        this.setBounds(700, 700, 750, 500);//set the location and size of frame

        this.add(bottom);
        this.add(id);
        this.add(remainingcards);
        this.add(playerCardCounts);
        this.add(currentcard);
        this.add(next);
//        this.add(draw);

        this.add(frame);
        this.setSize(700, 700);
        this.setLocation(new Point(500, 200));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }
    private void updateGUI(){
        ImageIcon icon1 = new ImageIcon("src/main/java/DataSet/Card Image/"+presenter.getGameRunner().getGameResponse().getGameBoard().getCardChecker().getLastCard() +".png");
        Image img1 = icon1.getImage();
        Image newImg1 = img1.getScaledInstance(144, 216,  java.awt.Image.SCALE_SMOOTH) ;
        icon1 = new ImageIcon(newImg1);
        currentcard.setIcon(icon1);
        currentcard.setText(presenter.getGameRunner().getGameResponse().getGameBoard().getCardChecker().getLastCard());
        id.setText("Current Player: " + presenter.getGameRunner().getGameResponse().getIds().get(
                presenter.getGameRunner().getGameResponse().getGameBoard().getGameStatus().getCurrentPlayerIndex()));

        cardHas.removeAll();

        cardHas.setBorder(new LineBorder(new Color(0, 0, 0)));//set the border
        cardHas.setBounds(30, 400, 620, 210);
        cardHas.setPreferredSize(new Dimension(570, 200));

        ButtonGroup buttonGroup = new ButtonGroup();
        presenter.allhandcards().forEach(c -> {
                    JToggleButton button = new JToggleButton(c);
                    buttonGroup.add(button);
                    if (!presenter.getGameRunner().getEachRound().beginStage().toString().contains(button.getText())) {
                        button.setEnabled(false);
                    }
                    button.addActionListener(this);
                    button.setBounds(20, 20, 20, 20);
                    cardHas.add(button);
                }

        );

        remainingcards.setText("Remaining Cards: " + presenter.RemainingCards());

        ArrayList<String> playerIds = presenter.getGameRunner().getGameResponse().getIds();
        int currentPosition = presenter.getGameRunner().getGameResponse().getGameBoard().getGameStatus().getCurrentPlayerIndex();
        StringBuilder countText = new StringBuilder("<html>");
        for (int i  = 0; i < playerIds.size(); i++) {
            int cardCount = presenter.getGameRunner().getEachRound().getGameBoard().
                    getGameCardHolders().getHandCards(i).getSize();
            if (i != currentPosition) {
                countText.append(playerIds.get(i)).append(" has ").append(cardCount).append(" card(s).<br>");
            } else {
                countText.append("You").append(" have ").append(cardCount).append(" card(s).<br>");
            }
        }
        playerCardCounts.setText(countText.substring(0, countText.length() - 4) + "<html>");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<String> playerIds = controller.getGameRunner().getGameResponse().getIds();
        if (e.getSource() == next) {
            JButton playedcard = (JButton) e.getSource();
            controller.getGameRunner().runGameforGUI(playedcard.getText());
            this.updateGUI();
        }
        else {
            JToggleButton playedcard = (JToggleButton) e.getSource();
            if (controller.getGameRunner().getEachRound().beginStage().toString().contains(playedcard.getText())) {
                controller.getGameRunner().runGameforGUI(playedcard.getText());
                this.updateGUI();
            }
            else {
                JOptionPane.showMessageDialog(null,"Cannot play this card!");
            }
        }
        boolean winFlag = controller.getGameRunner().getEachRound().getGameBoard().getGameStatus().isWinFlag();
        if(winFlag) {
            this.dispose();
            WinFrame frame = new WinFrame(playerIds.get(controller.getGameRunner().getGameResponse().getGameBoard().getGameStatus().getCurrentPlayerIndex()));
        }
    }
}

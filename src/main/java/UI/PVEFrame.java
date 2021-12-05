package UI;

import Controller.Controller;
import Controller.Presenter;
import LogIn.LogInEntity.UserList;
import LogIn.LogInEntity.UserStatistics;
import LogIn.LoginUseCase.LoginUseCase;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class PVEFrame extends JFrame implements ActionListener {
    private final Presenter presenter;
    private final Controller controller;
    private final UserStatistics stats;
    private Clip clip;

    JPanel frame = new JPanel();
    JLabel currentcard = new JLabel();
    JLabel remainingcards = new JLabel();
    JLabel playerCardCounts = new JLabel();
    JLabel id = new JLabel();
    JLabel bottom = new JLabel();
    JPanel cardHas = new JPanel();
    JButton closebutton = new JButton();
    JButton next = new JButton("next");
    ButtonGroup buttonGroup = new ButtonGroup();
    JTextArea textArea = new JTextArea();
    JScrollPane scroll = new JScrollPane(textArea);


    public PVEFrame(Presenter presenter, Controller controller, UserStatistics stats) {
        this.presenter = presenter;
        this.controller = controller;
        this.stats = stats;

        int currentPosition = presenter.getGameRunner().getEachRound().getGameBoard().
                getGameStatus().getCurrentPlayerIndex();

        currentcard.setHorizontalAlignment(0);//Center the text
        currentcard.setBounds(510, 50, 144, 216);//set the location and size of JLabel
        currentcard.setText(presenter.getGameRunner().getEachRound().getGameBoard().getCardChecker().getLastCard());
        ImageIcon icon1;
        if (presenter.getGameRunner().getEachRound().getGameBoard().getCardChecker().getLastCard() == null) {
            icon1 = new ImageIcon("src/main/java/DataSet/Card Image/black.png");
        } else {
            icon1 = new ImageIcon("src/main/java/DataSet/Card Image/" + presenter.
                    getGameRunner().getEachRound().getGameBoard().getCardChecker().getLastCard() + ".png");

        }
        Image img1 = icon1.getImage();
        Image newImg1 = img1.getScaledInstance(144, 216, java.awt.Image.SCALE_SMOOTH);
        icon1 = new ImageIcon(newImg1);
        currentcard.setIcon(icon1);

        remainingcards.setForeground(Color.RED);
        remainingcards.setBounds(30, 50, 300, 50);//set the location and size of JLabel
        remainingcards.setFont(new Font("Times", Font.BOLD, 30));
        remainingcards.setText("Remaining Cards: " + presenter.RemainingCards());

        id.setForeground(Color.RED);
        id.setBounds(30, 100, 500, 100);//set the location and size of JLabel
        id.setFont(new Font("Times", Font.BOLD, 30));
        ArrayList<String> playerIds = presenter.getGameRunner().getGameResponse().getIds();
        id.setText("Current Player: " + playerIds.get(0));

        playerCardCounts.setForeground(Color.BLACK);
        playerCardCounts.setBounds(30, 150, 400, 300);
        playerCardCounts.setFont(new Font("Times", Font.BOLD, 20));
        StringBuilder countText = new StringBuilder("<html>");
        for (int i = 0; i < playerIds.size(); i++) {
            int cardCount = presenter.getGameRunner().getEachRound().getGameBoard().
                    getGameCardHolders().getHandCards(i).getSize();
            if (i != 0) {
                countText.append(playerIds.get(i)).append(" has ").append(cardCount).append(" card(s).<br>");
            } else {
                countText.append("You").append(" have ").append(cardCount).append(" card(s).<br>");
            }
        }
        playerCardCounts.setText(countText.substring(0, countText.length() - 4) + "<html>");

        bottom.setBounds(28, 244, 680, 210);//set the location and size of JPanel

        try {
            int randSong = 1 + (int)(Math.random() * 4);
            AudioInputStream input = AudioSystem.getAudioInputStream(
                    new File("src/main/java/DataSet/bgm" + randSong + ".wav"));
            clip = AudioSystem.getClip();
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

        closebutton.setFont(new Font("Times", Font.BOLD, 20));
        closebutton.setText("x");
        closebutton.setBounds(620, 5, 30, 30);
        closebutton.setForeground(Color.RED);
        closebutton.setBackground(new Color(225, 83, 83));
        closebutton.addActionListener(this);
        closebutton.setBorderPainted(true);
        closebutton.setContentAreaFilled(false);
        closebutton.setOpaque(true);


        presenter.allhandcards(0).forEach(c -> {
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

        next.setBounds(506, 300, 144, 70);
        next.addActionListener(this);

        textArea.setBounds(30, 630, 620, 210);
        textArea.setEditable(false);
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(printStream);
        System.setErr(printStream);

        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(30, 630, 620, 210);
        scroll.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.add(scroll);


        this.setBounds(680, 700, 700, 900);//set the location and size of frame
        this.add(bottom);
        this.add(id);
        this.add(remainingcards);
        this.add(playerCardCounts);
        this.add(currentcard);
        this.add(next);
        this.add(closebutton);

        this.add(frame);
        this.setSize(680, 900);
        this.setLocation(new Point(200, 200));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        while (0 != currentPosition) {
            controller.getGameRunner().runGameforGUIComputer();
            currentPosition = controller.getGameRunner().getEachRound().getGameBoard().
                    getGameStatus().getCurrentPlayerIndex();
            this.updateGUI();
            boolean winFlag = controller.getGameRunner().getEachRound().getGameBoard().getGameStatus().isWinFlag();
            if (winFlag) {
                this.dispose();
                Loseframe frame = new Loseframe(presenter.getGameRunner().getGameResponse().getIds().get(
                presenter.getGameRunner().getEachRound().getGameBoard().getGameStatus().
                        getCurrentPlayerIndex()), stats);
            }
        }
    }

    private void updateGUI() {
        ImageIcon icon1 = new ImageIcon("src/main/java/DataSet/Card Image/" + presenter.getGameRunner().getEachRound().getGameBoard().getCardChecker().getLastCard() + ".png");
        Image img1 = icon1.getImage();
        Image newImg1 = img1.getScaledInstance(144, 216, java.awt.Image.SCALE_SMOOTH);
        icon1 = new ImageIcon(newImg1);
        currentcard.setIcon(icon1);
        currentcard.setText(presenter.getGameRunner().getEachRound().getGameBoard().getCardChecker().getLastCard());

        cardHas.removeAll();

        cardHas.setBorder(new LineBorder(new Color(0, 0, 0)));//set the border
        cardHas.setBounds(30, 400, 620, 210);
        cardHas.setPreferredSize(new Dimension(570, 200));

        ButtonGroup buttonGroup = new ButtonGroup();
        presenter.allhandcards(0).forEach(c -> {
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
        int currentPosition = presenter.getGameRunner().getEachRound().getGameBoard().
                getGameStatus().getCurrentPlayerIndex();
        StringBuilder countText = new StringBuilder("<html>");
        for (int i = 0; i < playerIds.size(); i++) {
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

        int currentPosition = presenter.getGameRunner().getEachRound().getGameBoard().
                getGameStatus().getCurrentPlayerIndex();
        if (e.getSource() == closebutton) {
            System.exit(0);
        }
        if (e.getSource() == next) {
            JButton playedcard = (JButton) e.getSource();
            controller.getGameRunner().runGameforGUI(playedcard.getText(), stats);
            int computerposition = controller.getGameRunner().getEachRound().getGameBoard().
                    getGameStatus().getCurrentPlayerIndex();

            while (computerposition != currentPosition) {
                controller.getGameRunner().runGameforGUIComputer();
                computerposition = controller.getGameRunner().getEachRound().getGameBoard().
                        getGameStatus().getCurrentPlayerIndex();
                this.updateGUI();
                boolean winFlag = controller.getGameRunner().getEachRound().getGameBoard().
                        getGameStatus().isWinFlag();
                if (winFlag) {
                    clip.stop();
                    clip.close();
                    this.dispose();
                    Loseframe frame = new Loseframe(playerIds.get(computerposition), stats);
                    break;
                }
            }
        }
        else{
                JToggleButton playedcard = (JToggleButton) e.getSource();
                if (controller.getGameRunner().getEachRound().beginStage().toString().
                        contains(playedcard.getText())) {
                    controller.getGameRunner().runGameforGUI(playedcard.getText(), stats);
                    boolean winFlag = controller.getGameRunner().getEachRound().getGameBoard().
                            getGameStatus().isWinFlag();
                    if (winFlag) {
                        clip.stop();
                        clip.close();
                        this.dispose();

                        stats.PVEWin();
                        LoginUseCase saver = new LoginUseCase(false);
                        UserList users = saver.getUsers();
                        users.getUser(stats.getPlayerId()).setUserStatistics(stats);
                        new LoginUseCase(users);

                        WinFrame frame = new WinFrame(playerIds.get(0), stats);
                    }
                    else {
                        int computerposition = controller.getGameRunner().getEachRound().
                                getGameBoard().getGameStatus().getCurrentPlayerIndex();

                        while (computerposition != currentPosition) {
                            controller.getGameRunner().runGameforGUIComputer();
                            computerposition = controller.getGameRunner().getEachRound().
                                    getGameBoard().getGameStatus().getCurrentPlayerIndex();
//                            this.updateGUI();
                            winFlag = controller.getGameRunner().getEachRound().
                                    getGameBoard().getGameStatus().isWinFlag();
                            if (winFlag) {
                                clip.stop();
                                clip.close();
                                this.dispose();
                                Loseframe frame = new Loseframe(playerIds.get(computerposition), stats);
                                break;
                            }
                    }
                        this.updateGUI();
                }
            }
        }
    }
    private static class CustomOutputStream extends OutputStream {
        private final JTextArea textArea;

        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) throws IOException {
            textArea.append(String.valueOf((char)b));
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }
}
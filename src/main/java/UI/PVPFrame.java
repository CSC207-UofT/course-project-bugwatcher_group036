package UI;

import Controller.*;
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

/**
 * The PVPFrame.
 */
public class PVPFrame extends JFrame implements ActionListener {
    private final Controller controller;
    private final Presenter presenter;
    private final UserStatistics stats;
    private Clip clip;

    JPanel frame = new JPanel();
    JLabel currentCard = new JLabel(); //The last card played by player
    JLabel remainingCards = new JLabel(); //Reminding cards in the deck
    JLabel playerCardCounts = new JLabel(); //The number of cards each player has
    JLabel id = new JLabel(); // The name of current player
    JPanel cardHas = new JPanel();// The cards the player has
    JButton closebutton = new JButton(); // The button to exit the game
    JButton next = new JButton("next"); // The next button for player to draw card or move to next player
    ButtonGroup buttonGroup = new ButtonGroup(); // A frame of all button
    JTextArea textArea = new JTextArea(); // The text of each players played
    JScrollPane scroll = new JScrollPane(textArea); // To have the textarea be scroll automatically

    /**
     * The PVP Frame of the game
     * @param presenter the presenter for the frame
     * @param controller the controller
     * @param stats The statistics of the User
     */
    public PVPFrame(Presenter presenter, Controller controller, UserStatistics stats) {
        this.presenter = presenter;
        this.controller = controller;
        this.stats = stats;

        currentCard.setHorizontalAlignment(0);//Center the text
        currentCard.setBounds(500, 50, 144, 216);//set the location and size of the picture
        currentCard.setText(presenter.getGameRunner().getEachRound().getGameBoard().getCardChecker().getLastCard());
        ImageIcon icon1 = new ImageIcon("src/main/java/DataSet/Card Image/black.png"); // To read the image for card, set to black for first card
        Image img1 = icon1.getImage();
        Image newImg1 = img1.getScaledInstance(144, 216, java.awt.Image.SCALE_SMOOTH);
        icon1 = new ImageIcon(newImg1);
        currentCard.setIcon(icon1);

        remainingCards.setForeground(Color.RED);
        remainingCards.setBounds(30, 50, 400, 50);//set the location and size of remaining card
        remainingCards.setFont(new Font("Times", Font.BOLD, 30));
        remainingCards.setText("Remaining Cards: " + presenter.RemainingCards());

        id.setForeground(Color.RED);
        id.setBounds(30, 100, 400, 50);//set the location and size of current player id
        id.setFont(new Font("Times", Font.BOLD, 30));

        ArrayList<String> playerIds = presenter.getGameRunner().getGameResponse().getIds(); //Get the id of each player
        int currentPosition = presenter.getGameRunner().getEachRound().
                getGameBoard().getGameStatus().getCurrentPlayerIndex();

        id.setText("Current Player: " + playerIds.get(currentPosition)); //display the current player name

        playerCardCounts.setForeground(Color.BLACK);
        playerCardCounts.setBounds(30, 150, 400, 300);
        playerCardCounts.setFont(new Font("Times", Font.BOLD, 20));
        StringBuilder countText = new StringBuilder("<html>");
        for (int i = 0; i < playerIds.size(); i++) { //Display all remaining cards each player has
            int cardCount = presenter.getGameRunner().getEachRound().getGameBoard().
                    getGameCardHolders().getHandCards(i).getSize();
            if (i != currentPosition) {
                countText.append(playerIds.get(i)).append(" has ").append(cardCount).append(" card(s).<br>");
            } else {
                countText.append("You").append(" have ").append(cardCount).append(" card(s).<br>");
            }
        }
        playerCardCounts.setText(countText.substring(0, countText.length() - 4) + "<html>");


        try {
            int randSong = 1 + (int)(Math.random() * 4); //Randomize a number for background music
            AudioInputStream input = AudioSystem.getAudioInputStream(
                    new File("src/main/java/DataSet/bgm" + randSong + ".wav"));
            clip = AudioSystem.getClip(); // Get the song of the random background music
            clip.open(input);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ignored) {
        }

        cardHas.setBorder(new LineBorder(new Color(0, 0, 0))); //set the border of all cards
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


        presenter.allhandcards().forEach(c -> { // To add all cards the player has to cardhas
                    JToggleButton button = new JToggleButton(c);
                    buttonGroup.add(button);
                    if (!presenter.getGameRunner().getEachRound().beginStage().toString().
                            contains(button.getText())) { //Check whether the card cannot be played
                        button.setEnabled(false);
                    }
                    button.addActionListener(this);
                    button.setBounds(20, 20, 20, 20);
                    cardHas.add(button); // Add each card to the cardhas panel
                }
        );

        this.add(cardHas);

        next.setBounds(506, 300, 144, 70);
        next.addActionListener(this);

        textArea.setBounds(30, 630, 620, 210);
        textArea.setEditable(false);
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea)); // The console in our GUI
        System.setOut(printStream);

        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(30, 630, 620, 210);
        scroll.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.add(scroll);

        this.setBounds(680, 700, 700, 900);//set the location and size of frame

        this.add(id);
        this.add(remainingCards);
        this.add(playerCardCounts);
        this.add(currentCard);
        this.add(next);
        this.add(closebutton);

        this.add(frame);
        this.setSize(680, 900);
        this.setLocation(new Point(200, 200));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * To Update the GUI after each player play a card.
     */
    private void updateGUI() {
        ImageIcon icon1 = new ImageIcon("src/main/java/DataSet/Card Image/" + presenter. // The new last card played by player
                getGameRunner().getEachRound().getGameBoard().getCardChecker().getLastCard() + ".png");
        Image img1 = icon1.getImage();
        Image newImg1 = img1.getScaledInstance(144, 216, java.awt.Image.SCALE_SMOOTH);
        icon1 = new ImageIcon(newImg1);
        currentCard.setIcon(icon1); // Change the picture of last card
        currentCard.setText(presenter.getGameRunner().getEachRound().
                getGameBoard().getCardChecker().getLastCard()); // Change the name of the last card
        id.setText("Current Player: " + presenter.getGameRunner().getGameResponse().getIds().get(
                presenter.getGameRunner().getEachRound().getGameBoard().getGameStatus().getCurrentPlayerIndex())); // Change the current player ID

        cardHas.removeAll(); //Remove all cards from display of the previous player

        cardHas.setBorder(new LineBorder(new Color(0, 0, 0)));//set the border
        cardHas.setBounds(30, 400, 620, 210);
        cardHas.setPreferredSize(new Dimension(570, 200));

        ButtonGroup buttonGroup = new ButtonGroup();
        presenter.allhandcards().forEach(c -> { // Add all current card that the player has
                    JToggleButton button = new JToggleButton(c);
                    buttonGroup.add(button);
                    if (!presenter.getGameRunner().getEachRound().beginStage().
                            toString().contains(button.getText())) {
                        button.setEnabled(false);
                    }
                    button.addActionListener(this);
                    button.setBounds(20, 20, 20, 20);
                    cardHas.add(button);
                }

        );

        remainingCards.setText("Remaining Cards: " + presenter.RemainingCards()); //Update remaining cards in card deck

        ArrayList<String> playerIds = presenter.getGameRunner().getGameResponse().getIds();
        int currentPosition = presenter.getGameRunner().getEachRound().
                getGameBoard().getGameStatus().getCurrentPlayerIndex();

        StringBuilder countText = new StringBuilder("<html>");
        for (int i = 0; i < playerIds.size(); i++) { // Update all remaining cards of each player
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
        if (e.getSource() == closebutton) { //If user click close button
            System.exit(0);
        }
        if (e.getSource() == next) { // If user decide to draw card or skip to next player
            JButton playedcard = (JButton) e.getSource();
            controller.getGameRunner().runGameforGUI(playedcard.getText(), stats);
            this.updateGUI();
        } else { // When user played a playable card
            JToggleButton playedcard = (JToggleButton) e.getSource();
            if (controller.getGameRunner().getEachRound().beginStage().toString().contains(playedcard.getText())) {
                controller.getGameRunner().runGameforGUI(playedcard.getText(), stats);
                this.updateGUI();
            } else {
                JOptionPane.showMessageDialog(null, "Cannot play this card!");
            }
        }
        boolean winFlag = controller.getGameRunner().getEachRound().getGameBoard().getGameStatus().isWinFlag();
        if (winFlag) { //Check whether the user win after played a card
            clip.stop();
            clip.close();
            this.dispose();

            stats.PVPWin();
            LoginUseCase saver = new LoginUseCase(false);
            UserList users = saver.getUsers();
            users.getUser(stats.getPlayerId()).setUserStatistics(stats); // Update User Statistics
            new LoginUseCase(users);

            WinFrame frame = new WinFrame(playerIds.get //Create the new winframe for next player
                    (controller.getGameRunner().getEachRound().getGameBoard().getGameStatus().getCurrentPlayerIndex()), stats);
        }
    }

    /**
     * Override console to GUI.
     */
    private static class CustomOutputStream extends OutputStream {
        private final JTextArea textArea;

        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) {
            textArea.append(String.valueOf((char) b));
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }
}

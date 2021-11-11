package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.LineBorder;

import Controller.Controller;
import Controller.ControllerBuilder;
import Entity.Card;
import Entity.Player;
import UseCase.Status;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UI extends JFrame{

    private JFrame frame;
    JPanel cardHas;// the card panel
    JLabel id;// show current play
    JLabel card;// last card
    JLabel quantity; // remain card quantity in desk
    Controller newGameController;
    Status vars;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        UI window = new UI();
        window.frame.setVisible(true);
    }

    /**
     * Create the application.
     */
    public UI() {
        initialize();
        EventQueue.invokeLater(()->{
            initGame();
            Player winner = newGameController.runGame();
//            playGame();//first player to play
            if(vars.isWinFlag()){
                System.out.println(vars.getPlayerWins().getId() + " wins!");
                JOptionPane.showMessageDialog(null, winner.getId() + " wins!");
            }
        });
    }

    /**
     * set up the game
     */
    private void initGame() {
        // show the dialog let player input
        Integer[] nums = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Integer num = (Integer) JOptionPane.showInputDialog(null, "Number Of Players:","Number Of Players:", JOptionPane.QUESTION_MESSAGE, null, nums, nums[0]);
        ControllerBuilder unoBuilder = new ControllerBuilder(num);
        newGameController = unoBuilder.buildUnoController();
        newGameController.setUI(this);//bind the ui
        vars = new Status(newGameController.getEachRound().getPlayerManager().getPlayerNum());


    }

    /**
     * click next to play the game
     */
//    private void playGame() {
//        if(!vars.isWinFlag()){
//
//            // cardToPlay is the card that the player wants to play.
//            Card cardToPlay = newGameController.getCardManager().createNullCard();
//            // show the current player
//            id.setText("ID: "+newGameController.getPlayerManager().getPlayers()[vars.getCurrentPlayerIndex()]);
//
//            // get cards player can play considering special cases of function cards
//            ArrayList<Card> currentCardsPlayerCanPlay = newGameController.getEachRound().getCurrentCardsPlayerCanPlayer(vars);
//
//            // If no cards can play, draw a card, otherwise play a card. If the player type three times
//            // wrong card to play, the player will be punished to draw a card automatically.
//            cardToPlay = newGameController.getEachRound().operationsForPlayer(vars, cardToPlay, currentCardsPlayerCanPlay);
//
//            // set the skip to false since the function skip has passed.
//            vars.setSkip(false);
//
//            // The outcome after the player plays a card or get punished.
//            newGameController.getEachRound().effectsAfterPunishOrPlayCard(vars, cardToPlay);
//
//            // Determine whether the player wins or not.
//            newGameController.getEachRound().winOrNotInThisRound(vars);
//
//            // Move to the next player
//            vars.setCurrentPlayerIndex(newGameController.getEachRound().moveToNextPlayer(vars.getCurrentPlayerIndex(), vars.isReverse()));
//        }
//        // show the current player
//        id.setText("ID: "+newGameController.getPlayerManager().getPlayers()[vars.getCurrentPlayerIndex()]);
//
//    }

    /**
     * Initialize the UI
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 750, 500);//set the location and size of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // add label
        card = new JLabel("Current card");
        card.setHorizontalAlignment(0);//Center the text
        card.setBorder(new LineBorder(new Color(0, 0, 0)));
        card.setBounds(275, 85, 200, 70);//set the location and size of JLabel
        frame.getContentPane().add(card);

        // add label
        quantity = new JLabel("Remaining Cards:");
        quantity.setForeground(Color.RED);
        quantity.setBounds(47, 112, 150, 20);//set the location and size of JLabel
        frame.getContentPane().add(quantity);

        // add label
        id = new JLabel("ID:");
        id.setForeground(Color.RED);
        id.setBounds(47, 182, 180, 50);//set the location and size of JLabel
        frame.getContentPane().add(id);

        JPanel bottom = new JPanel();
        bottom.setBounds(28, 244, 680, 210);//set the location and size of JPanel
        frame.getContentPane().add(bottom);

        // add label
        JLabel label = new JLabel("Cards has");
        bottom.add(label);

        // add cardHas panel
        cardHas = new JPanel();
        cardHas.setBorder(new LineBorder(new Color(0, 0, 0)));//set the border
        cardHas.setPreferredSize(new Dimension(570, 200));
        bottom.add(cardHas);
        FlowLayout fl_cardHas = (FlowLayout) cardHas.getLayout();
        fl_cardHas.setAlignment(FlowLayout.LEFT);

        //add next button
        JButton next = new JButton("next");
//        next.addActionListener(e -> {
////            playGame();
//            // show the winner info
//            if(vars.isWinFlag()){
//                System.out.println(vars.getPlayerWins().getId() + " wins!");
//                JOptionPane.showMessageDialog(null, vars.getPlayerWins().getId() + " wins!");
//            }
//        });
        next.setBounds(524, 85, 156, 70);//set the location and size of Button
        frame.getContentPane().add(next);
//        if(vars.isWinFlag()){
//            System.out.println(vars.getPlayerWins().getId() + " wins!");
//            JOptionPane.showMessageDialog(null, vars.getPlayerWins().getId() + " wins!");
//        }
    }

    /**
     * display the last card and player card currently
     * display the remain card quantity
     * @param lastCard
     * @param handCard
     * @param remaining remain card list
     */
    public void displayCard(Card lastCard, ArrayList<Card> handCard,
                            ArrayList<Card> remaining) {
        if (lastCard.getColor().equals("black")){
            card.setText(newGameController.getBasicOperations().getColor());
        }
        else {card.setText(lastCard.getId());}

        cardHas.removeAll();
        handCard.stream().forEach(c->{
            JButton button = new JButton(c.toString());
            if (newGameController.getBasicOperations().cardsPlayerCanPlay(handCard, lastCard).contains(c)) {
                button.setEnabled(true);}
            else {button.setEnabled(false);}
            cardHas.add(button);
        });
        id.setText("ID: " + newGameController.getEachRound().getPlayerManager().getPlayers()[newGameController.getBasicOperations().getVars().getCurrentPlayerIndex()]);
        cardHas.repaint();
        quantity.setText("Remaining Cards:" + remaining.size());
    }
}

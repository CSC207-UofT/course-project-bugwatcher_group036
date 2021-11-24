package LogIn.LogInUI;
import javax.swing.*;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginUI extends JFrame implements ActionListener {

    JLabel label = new JLabel();
    JLabel Username = new JLabel();
    JLabel Password = new JLabel();
    JButton loginBT = new JButton();
    JButton createBT = new JButton();
    JTextField username = new JTextField();
    JTextField password = new JTextField();

    LoginUI(){

        label.setText("Please login, or create an account if you don't have one.");
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(0,0,400,20);

        Username.setText("Username");
        Username.setBounds(10,100,100,20);

        Password.setText("Password");
        Password.setBounds(10, 130, 100, 20);


        loginBT.setText("Login");
        loginBT.setBounds(50,250,100,50);
        loginBT.addActionListener(this);
        loginBT.setFocusable(false);


        createBT.setText("Create");
        createBT.setBounds(250,250,100,50);
        createBT.addActionListener(this);
        createBT.setFocusable(false);

        username.addActionListener(this);
        username.setBounds(100,100,200,20);

        password.addActionListener(this);
        password.setBounds(100,130,200,20);

        this.setTitle("Login");
        this.setVisible(true);
        this.setLayout(null);
        this.setFocusable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,400);
        this.setLocation(new Point( 500,200));
        this.add(label);
        this.add(Username);
        this.add(Password);
        this.add(loginBT);
        this.add(createBT);
        this.add(username);
        this.add(password);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBT){


        }
        if (e.getSource() == createBT){
            this.dispose();

        }
    }

}


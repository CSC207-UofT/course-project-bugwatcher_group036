package LogIn.LoginGUI;
import LogIn.LoginController.LoginController;
import LogIn.LoginUseCase.LoginUseCase;
import UI.ModeFrame;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame implements ActionListener {

    JLabel Guide = new JLabel();
    JLabel quitGuide = new JLabel();
    JLabel Username = new JLabel();
    JLabel Password = new JLabel();
    JButton loginButton = new JButton();
    JButton registerButton = new JButton();
    JTextField usernameInput = new JTextField();
    JPasswordField passwordInput = new JPasswordField();
    LoginController loginController = new LoginController();

    public LoginUI(){

        Guide.setText("Please login, or press Register if you don't have an account.");
//        Guide.setVerticalAlignment(JLabel.TOP);
        Guide.setBounds(10,0,400,20);

        quitGuide.setText("Otherwise, you can press the red \"X\" button to leave.");
        quitGuide.setBounds(10,30,400,20);

        Username.setText("Username");
        Username.setBounds(10,100,100,20);

        Password.setText("Password");
        Password.setBounds(10, 130, 100, 20);


        loginButton.setText("Login");
        loginButton.setBounds(50,250,100,50);
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);


        registerButton.setText("Register");
        registerButton.setBounds(250,250,100,50);
        registerButton.addActionListener(this);
        registerButton.setFocusable(false);

        usernameInput.addActionListener(this);
        usernameInput.setBounds(100,100,200,20);

        passwordInput.addActionListener(this);
        passwordInput.setEchoChar('*');
        passwordInput.setBounds(100,130,200,20);

        this.setTitle("Welcome to the game center!");
        this.setVisible(true);
        this.setLayout(null);
        this.setFocusable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,400);
        this.setLocation(new Point( 500,200));
        this.add(Guide);
        this.add(quitGuide);
        this.add(Username);
        this.add(Password);
        this.add(loginButton);
        this.add(registerButton);
        this.add(usernameInput);
        this.add(passwordInput);
    }

    @Override
    public void actionPerformed(ActionEvent button) {
        if (button.getSource() == loginButton) {
//            useCase = new LoginUseCase(false);
//            loginController = new LoginController(useCase);

            if (loginController.runLogin(usernameInput.getText(), String.valueOf(passwordInput.getPassword()))) {
                System.out.println("Login success!");
//               Need to connect to mainUI.
                this.dispose();
                ModeFrame frame = new ModeFrame
                        (loginController.getLoginInputBoundary().getUsers().getUser(
                                usernameInput.getText()).getUserStatistics());
            } else {
                System.out.println("The password is wrong or the user does not exist.");
                JOptionPane.showMessageDialog(null,
                        "The password is wrong or the user does not exist.");
            }
        }
        if (button.getSource() == registerButton){
            RegisterUI registerUI = new RegisterUI();
//            useCase = new LoginUseCase(true);
            this.dispose();

        }
    }

}


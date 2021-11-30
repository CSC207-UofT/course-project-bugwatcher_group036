package LogIn.LoginGUI;

import LogIn.LoginController.LoginController;
import LogIn.LoginUseCase.LoginUseCase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterUI extends JFrame implements ActionListener {

    JLabel Guide = new JLabel();
    JLabel backGuide = new JLabel();
    JLabel Username = new JLabel();
    JLabel Password = new JLabel();
    JButton confirmButton = new JButton();
    JButton backButton = new JButton();
    JTextField usernameInput = new JTextField();
    JTextField passwordInput = new JTextField();
    LoginUseCase useCase;
    LoginController loginController = new LoginController(useCase);

    public RegisterUI(){
        Guide.setText("Please register here, and press Confirm when you finish.");
        Guide.setVerticalAlignment(JLabel.TOP);
        Guide.setBounds(10,0,450,20);

        backGuide.setText("Otherwise, you can press back to go to login page.");
        backGuide.setBounds(10,30,400,20);

        confirmButton.setText("Confirm");
        confirmButton.setBounds(50,250,100,50);
        confirmButton.addActionListener(this);
        confirmButton.setFocusable(false);

        backButton.setText("Back");
        backButton.setBounds(250,250,100,50);
        backButton.addActionListener(this);
        backButton.setFocusable(false);

        Username.setText("Username");
        Username.setBounds(10,100,80,20);

        usernameInput.addActionListener(this);
        usernameInput.setBounds(100,100,200,20);

        Password.setText("Password");
        Password.setBounds(10,130,80,20);

        passwordInput.addActionListener(this);
        passwordInput.setBounds(100,130,200,20);

        this.setTitle("Register now!");
        this.setVisible(true);
        this.setLayout(null);
        this.setFocusable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450,400);
        this.setLocation(new Point( 500,200));
        this.add(Guide);
        this.add(backGuide);
        this.add(confirmButton);
        this.add(backButton);
        this.add(Username);
        this.add(Password);
        this.add(usernameInput);
        this.add(passwordInput);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton){
            useCase = new LoginUseCase(false);
            loginController = new LoginController(useCase);

            if (loginController.runRegister(usernameInput.getText(), passwordInput.getText())) {
                System.out.println("Register success!");
//               Need to connect to mainUI.
                this.dispose();
                LoginUI frame = new LoginUI();
            } else {
                System.out.println("The username already exists, please enter a new one.");
                JOptionPane.showMessageDialog(null,
                        "The username already exists, please enter a new one.\n" +
                                "Your user info is\" " + loginController.getLoginInputBoundary().
                                getUsers().getUser(usernameInput.getText()) + "\" ");
            }

        }
        if (e.getSource() == backButton){
            this.dispose();
            LoginUI ui = new LoginUI();
        }
    }


}


/*
Smriti Siva
DollarTree Letterboxd Final Project
8/2/24
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
/**
*This class creates the login frontend for the program
*/
public class LoginGUI extends JFrame implements ActionListener {
   /**
   *the field where the username is entered
   */
   private JTextField userField;
   /**
   *the field where the password is entered
   */
   private JPasswordField passField;
   /**
   *the "panel" for the login
   */
   private JOptionPane loginPanel;
   /**
   *the user currently logged in
   */
   private User currentUser;
   /**
   *constructs login panel
   */
   public LoginGUI() {
      setSize(400, 250);
      setTitle("Login");
      this.loginPanel = new JOptionPane();
      loginPanel.setLayout(null);
      JLabel user = new JLabel("Username ");
      user.setBounds(70, 50, 100, 20);
      this.userField = new JTextField(10);
      userField.setBounds(180, 50, 100, 20);
      JLabel pass = new JLabel("Password ");
      pass.setBounds(70, 80, 100, 20);
      this.passField = new JPasswordField(10);
      passField.setBounds(180, 80, 100, 20);
      JButton login = new JButton("Login");
      login.setBounds(70, 130, 100, 20);
      login.setActionCommand("login");
      login.addActionListener(this);
      JButton signup = new JButton("Sign-up");
      signup.setBounds(180, 130, 100, 20);
      signup.setActionCommand("signup");
      signup.addActionListener(this);
      loginPanel.add(user);
      loginPanel.add(userField);
      loginPanel.add(pass);
      loginPanel.add(passField);
      loginPanel.add(login);
      loginPanel.add(signup);
      add(loginPanel);
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      setVisible(true);
   }
   /**
   *checks signup or login buttons have been clicked
   *@param e the button click
   */
   @Override
   public void actionPerformed(ActionEvent e) {
      String username = userField.getText();
      String password = new String(passField.getText());
      if (e.getActionCommand().equals("signup")) {
         if (username.length() > 10 || username.length() < 3 || password.length() > 10 || password.length() < 3 ||
             username.indexOf(' ') != -1 || username.indexOf(',') != -1) {
            loginPanel.showMessageDialog(null, "Incorrect username or password format");
         }
         else {
            if (UserFile.userExists(username)) {
               loginPanel.showMessageDialog(null, "Username already taken");
            }
            else {
               loginPanel.showMessageDialog(null, "Sign-up Successful");
               UserFile.saveToUserFile(username, password);
            }
         }
      }
      if (e.getActionCommand().equals("login")) {
         if (UserFile.loginMatch(username, password)) {
            currentUser = new User(username, password);
            loginPanel.showMessageDialog(null, "Login Successful");
            HomepageGUI nextPage = new HomepageGUI(currentUser);
            nextPage.setVisible(true);
            setVisible(false);
            
         }
         else {
            loginPanel.showMessageDialog(null, "Username or password incorrect");
         }
      }
   }
}
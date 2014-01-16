package gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.plaf.basic.BasicBorders;

import stalker.DatabaseConnector;
import stalker.ForgotPassword;

import elements.DButton;
import elements.DLabel;
import elements.DPanel;
import elements.DTextField;

/* Generates the Login screen *
 * created by Aure 			  */

public class LoginScreen implements ActionListener, KeyListener {

	DLabel loginLabel, emailLabel, passwordLabel, termsConditionLabel,
	firstNameLabel, submitEmailLabel,
	submitPasswordLabel, reSubmitPasswordLabel, enterEmailLabel;
	public static DTextField emailTxt;
	DTextField firstNameTxt, submitEmailTxt;
	public static DTextField forgotPasswordTxt;
	JPasswordField submitPasswordTxt, reSubmitPasswordTxt;
	static JPasswordField passwordTxt;
	DButton loginButton, createNewAccountButton, forgotPasswordButton,
	signUpButton, forgotEmailButton;
	public static DPanel loginScreenPanel, signUpPanel, forgotPasswordPanel;
	JDialog signUpDialog, forgotPasswordDialog;

	LoginScreen() {

		loginLabel = new DLabel("Log in", GUI.white, GUI.txtH1);
		loginLabel.setBounds(50, 40, 600, 40);
		emailLabel = new DLabel("Email", GUI.white, GUI.txtH3);
		emailLabel.setBounds(50, 120, 260, 40);
		emailTxt = new DTextField(40, GUI.darkGray, GUI.txtH3);
		emailTxt.setBounds(50, 160, 450, 40);
		emailTxt.addKeyListener(this);
		passwordLabel = new DLabel("Password", GUI.white, GUI.txtH3);
		passwordLabel.setBounds(50, 220, 600, 40);
		passwordTxt = new JPasswordField(40);
		passwordTxt.setBounds(50, 260, 450, 40);
		passwordTxt.addKeyListener(this);

		/* create Account Button by Mahsa, merged by Jani */
		createNewAccountButton = new DButton("Sign up", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		createNewAccountButton.setBounds(50, 340, 130, 40);
		createNewAccountButton.addActionListener(this);
		/* ----------------------------------------------- */

		loginButton = new DButton("Log in", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		loginButton.setBounds(190, 340, 150, 40);
		loginButton.addActionListener(this);
	
		forgotPasswordButton = new DButton("<html>Forgot<br>password</html>", GUI.white,
				GUI.txtH4, GUI.darkerGray);
		forgotPasswordButton.setBounds(350, 340, 150, 40);
		forgotPasswordButton.addActionListener(this);

		loginScreenPanel = new DPanel(GUI.darkGray);
		loginScreenPanel.setBorder(BasicBorders.getButtonBorder());
		loginScreenPanel.setBounds(170, 80, 550, 400);
		loginScreenPanel.setLayout(null);
		loginScreenPanel.add(loginLabel);
		loginScreenPanel.add(emailLabel);
		loginScreenPanel.add(emailTxt);
		loginScreenPanel.add(passwordLabel);
		loginScreenPanel.add(passwordTxt);
		loginScreenPanel.add(createNewAccountButton);
		loginScreenPanel.add(forgotPasswordButton);
		loginScreenPanel.add(loginButton);

		GUI.home.add(loginScreenPanel);

		/* sign up pop-up, by Mahsa and Gabriele */
		signUpPanel = new DPanel(GUI.darkGray);
		signUpPanel.setBounds(0, 0, 400, 500);
		signUpPanel.setLayout(null);

		firstNameLabel = new DLabel("Name", GUI.white, GUI.txtH3);
		firstNameLabel.setBounds(50, 20, 300, 40);
		submitEmailLabel = new DLabel("Your Email", GUI.white, GUI.txtH3);
		submitEmailLabel.setBounds(50, 110, 300, 40);
		submitPasswordLabel = new DLabel("Password", GUI.white, GUI.txtH3);
		submitPasswordLabel.setBounds(50, 200, 300, 40);
		reSubmitPasswordLabel = new DLabel("Re-enter password", GUI.white,
				GUI.txtH3);
		reSubmitPasswordLabel.setBounds(50, 290, 300, 40);

		firstNameTxt = new DTextField(20, GUI.darkerGray, GUI.txtH3);
		firstNameTxt.setBounds(50, 60, 300, 40);
		submitEmailTxt = new DTextField(20, GUI.darkerGray, GUI.txtH3);
		submitEmailTxt.setBounds(50, 150, 300, 40);
		reSubmitPasswordTxt = new JPasswordField();
		reSubmitPasswordTxt.setBounds(50, 240, 300, 40);
		submitPasswordTxt = new JPasswordField();
		submitPasswordTxt.setBounds(50, 330, 300, 40);
		termsConditionLabel = new DLabel(
				"<html>By clicking sign up, you agree to our<br><a href = ''> terms and condition.</a></html>",
				GUI.white, GUI.txtH4);
		termsConditionLabel.setBounds(50, 380, 500, 40);
		signUpButton = new DButton("Sign up", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		signUpButton.setBounds(100, 430, 200, 40);
		signUpButton.addActionListener(this);

		signUpPanel.add(firstNameTxt);
		signUpPanel.add(submitEmailTxt);
		signUpPanel.add(reSubmitPasswordTxt);
		signUpPanel.add(submitPasswordTxt);
		signUpPanel.add(termsConditionLabel);
		signUpPanel.add(signUpButton);
		signUpPanel.add(firstNameLabel);
		signUpPanel.add(submitEmailLabel);
		signUpPanel.add(submitPasswordLabel);
		signUpPanel.add(reSubmitPasswordLabel);

		signUpDialog = new JDialog();
		signUpDialog.setTitle("Sign up");
		signUpDialog.setBounds(750, 300, 400, 510);
		signUpDialog.setLayout(null);
		signUpDialog.setVisible(false);
		signUpDialog.setResizable(false);
		signUpDialog.add(signUpPanel);

		/* forgot password pop-up, by Gabriele */
		forgotPasswordPanel = new DPanel(GUI.darkGray);
		forgotPasswordPanel.setBounds(0, 0, 400, 300);
		forgotPasswordPanel.setLayout(null);

		enterEmailLabel = new DLabel("Email:", GUI.white, GUI.txtH3);
		enterEmailLabel.setBounds(50, 50, 300, 40);

		forgotPasswordTxt = new DTextField(20, GUI.darkerGray, GUI.txtH3);
		forgotPasswordTxt.setBounds(50, 90, 300, 40);

		forgotEmailButton = new DButton("Send Password", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		forgotEmailButton.setBounds(50, 150, 300, 40);
		forgotEmailButton.addActionListener(this);

		forgotPasswordPanel.add(enterEmailLabel);
		forgotPasswordPanel.add(forgotPasswordTxt);
		forgotPasswordPanel.add(forgotEmailButton);

		forgotPasswordDialog = new JDialog();
		forgotPasswordDialog.setTitle("Forgot Password");
		forgotPasswordDialog.setBounds(800, 450, 400, 250);
		forgotPasswordDialog.setLayout(null);
		forgotPasswordDialog.setVisible(false);
		forgotPasswordDialog.setResizable(false);
		forgotPasswordDialog.add(forgotPasswordPanel);

	}

	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == forgotPasswordButton) {
			forgotPasswordDialog.setVisible(true);
		}
		if (ae.getSource() == forgotEmailButton) {
			ForgotPassword mail = new ForgotPassword();
			String email = forgotPasswordTxt.getText();
			try {	
				DatabaseConnector dc = new DatabaseConnector();
				if ((email != null) && (email.length() > 0)) {
					if (dc.queryCredentials("Username", "Username", email).equals("")){
						JOptionPane.showMessageDialog(GUI.home, "You have no account yet", "Error", JOptionPane.ERROR_MESSAGE);
					}else{
						try {
							mail.sendPassword(email);
						} catch (Exception e) {
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(GUI.home, "An email has been sent to your email adress", "Error", JOptionPane.ERROR_MESSAGE);
						forgotPasswordDialog.setVisible(false);
					}
				}
				else {
					JOptionPane.showMessageDialog(GUI.home, "You have not added an email", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (HeadlessException | SQLException e) {
				e.printStackTrace();
			}	
		}
		if (ae.getSource() == loginButton) {
			try {
				DatabaseConnector dc = new DatabaseConnector();
				dc.logInM(emailTxt.getText(), passwordTxt.getText());
				GUI.username = emailTxt.getText();
				HomeScreen.usernameLabel.setText(GUI.username);
				CreateLogScreen.carDropDown.removeAllItems();
				for (String s : dc.queryCar("RegistryNumber", "Car",
						GUI.username)) {
					CreateLogScreen.carDropDown.addItem(s);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ae.getSource() == createNewAccountButton) {
			signUpDialog.setVisible(true);
		}
		if (ae.getSource() == signUpButton) {
			try {
				DatabaseConnector dc = new DatabaseConnector();
				if (submitEmailTxt.getText().equals("")
						|| submitPasswordTxt.getText().equals("")
						|| firstNameTxt.getText().equals("")) {
					JOptionPane.showMessageDialog(GUI.home, "Missing Values",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if (submitPasswordTxt.getText().equals(
						reSubmitPasswordTxt.getText())) {
					if(dc.queryCredentials("Username", "Username", submitEmailTxt.getText()).equals("")){
						dc.insertNewUser(submitEmailTxt.getText(),
								submitPasswordTxt.getText(), firstNameTxt.getText());
						HomeScreen.carDetailDialog.setVisible(true);
						signUpDialog.setVisible(false);
						GUI.username = submitEmailTxt.getText();
					}else{
						JOptionPane.showMessageDialog(GUI.home, "This email adress already exists", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(GUI.home,
							"Passwords don't match", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {	
	}
	@Override
	public void keyReleased(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_ENTER){
			try {
				DatabaseConnector dc = new DatabaseConnector();
				dc.logInM(emailTxt.getText(), passwordTxt.getText());
				GUI.username = emailTxt.getText();
				HomeScreen.usernameLabel.setText(GUI.username);
				CreateLogScreen.carDropDown.removeAllItems();
				for (String s : dc.queryCar("RegistryNumber", "Car",
						GUI.username)) {
					CreateLogScreen.carDropDown.addItem(s);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}

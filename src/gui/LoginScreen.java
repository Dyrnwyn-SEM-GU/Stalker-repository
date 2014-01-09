package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class LoginScreen implements ActionListener {

	DLabel loginLabel, emailLabel, passwordLabel, termsConditionLabel, termsAndConditionLabel2, firstNameLabel, submitEmailLabel, submitPasswordLabel, reSubmitPasswordLabel;
	public static DTextField emailTxt;
	DTextField firstNameTxt, submitEmailTxt;
	JPasswordField submitPasswordTxt, reSubmitPasswordTxt;
	static JPasswordField passwordTxt;
	DButton loginButton, createNewAccountButton, forgotPasswordButton, signUpButton;
	public static DPanel loginScreenPanel, signUpPanel;
	JDialog signUpDialog;

	LoginScreen() {

		loginLabel = new DLabel("Log in", GUI.white, GUI.txtH1);
		loginLabel.setBounds(50, 40, 600, 40);
		emailLabel = new DLabel("Email", GUI.white, GUI.txtH3);
		emailLabel.setBounds(50, 120, 260, 40);
		emailTxt = new DTextField(40, GUI.darkGray, GUI.txtH3);
		emailTxt.setBounds(50, 160, 450, 40);
		passwordLabel = new DLabel("Password", GUI.white, GUI.txtH3);
		passwordLabel.setBounds(50, 220, 600, 40);
		passwordTxt = new JPasswordField(40);
		passwordTxt.setBounds(50, 260, 450, 40);

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
		forgotPasswordButton = new DButton("Forgot password?", GUI.white,
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
		signUpPanel = new DPanel(GUI.darkerGray);
		signUpPanel.setBounds(0, 0, 680, 600);
		signUpPanel.setLayout(null);
		
		firstNameLabel = new DLabel("Name", GUI.white, GUI.txtH3);
		firstNameLabel.setBounds(13, 10, 300, 40);
		submitEmailLabel = new DLabel("Your Email", GUI.white, GUI.txtH3);
		submitEmailLabel.setBounds(13, 80, 300, 40);
		submitPasswordLabel = new DLabel("Password",GUI.white, GUI.txtH3);
		submitPasswordLabel.setBounds(13, 150, 300, 40);
		reSubmitPasswordLabel = new DLabel("Re-enter password", GUI.white, GUI.txtH3);
		reSubmitPasswordLabel.setBounds(13, 220, 300, 40);
		
		firstNameTxt = new DTextField(20, GUI.darkerGray, GUI.txtH3);
		firstNameTxt.setBounds(10, 45, 300, 40);
		submitEmailTxt = new DTextField(20, GUI.darkerGray, GUI.txtH3);
		submitEmailTxt.setBounds(10, 115, 300, 40);
		reSubmitPasswordTxt = new JPasswordField();
		reSubmitPasswordTxt.setBounds(10, 185, 300, 40);
		submitPasswordTxt = new JPasswordField();
		submitPasswordTxt.setBounds(10, 255, 300, 40);
		termsConditionLabel = new DLabel(
				"By clicking sign up, you agree to our terms and condition.",
				GUI.white, GUI.txtH4);
		termsConditionLabel.setBounds(10, 300, 500, 40);
		termsAndConditionLabel2 = new DLabel("Terms and Condition", GUI.white,
				GUI.txtH4);
		termsAndConditionLabel2.setBounds(10, 350, 500, 40);
		signUpButton = new DButton("Sign up", GUI.white, GUI.txtH2, GUI.darkerGray);
		signUpButton.setBounds(10, 420, 150, 40);
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
		signUpDialog.setBounds(350, 108, 670, 570);
		signUpDialog.setLayout(null);
		signUpDialog.setVisible(false);
		signUpDialog.add(signUpPanel);
		
	}

	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == forgotPasswordButton) {
			ForgotPassword mail = new ForgotPassword();
			if (!emailTxt.getText().equals("")) {
				try {
					mail.sendPassword(emailTxt.getText());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (ae.getSource() == loginButton) {
			try {
				DatabaseConnector dc = new DatabaseConnector();
				dc.logInM(emailTxt.getText(), passwordTxt.getText());
				GUI.username = emailTxt.getText();
				/*
				 * code addition to update car drop-down depending on user, by
				 * Aure
				 */
				CreateLogScreen.car.removeAllItems();
				//System.out.println(dc.check());
				for (String s : dc.querieCar("RegistryNumber", "Car", GUI.username)) {
					CreateLogScreen.car.addItem(s);
				}
				/*--------------------------------------------------------------------*/
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
			if (submitEmailTxt.getText().equals("") || submitPasswordTxt.getText().equals("") || firstNameTxt.getText().equals("")) {
				JOptionPane.showMessageDialog(GUI.home, "Missing Values", "Error", JOptionPane.ERROR_MESSAGE);
			}else if (submitPasswordTxt.getText().equals(reSubmitPasswordTxt.getText())) {
						dc.insertNewUser(submitEmailTxt.getText(), submitPasswordTxt.getText(),firstNameTxt.getText());	
						HomeScreen.carDetailDialog.setVisible(true);
						signUpDialog.setVisible(false);
						GUI.username = submitEmailTxt.getText();
			}else{
				JOptionPane.showMessageDialog(GUI.home, "Passwords don't match", "Error", JOptionPane.ERROR_MESSAGE);
			}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

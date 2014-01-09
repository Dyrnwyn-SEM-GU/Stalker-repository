package stalker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JPasswordField;
import javax.swing.plaf.basic.BasicBorders;

import elements.DButton;
import elements.DLabel;
import elements.DPanel;
import elements.DTextField;

/* Generates the Login screen *
 * created by Aure 			  */

public class LoginScreen implements ActionListener {

	DLabel login, email, password;
	static DTextField emailTxt;
	JPasswordField passwordTxt;
	DButton loginButton, createNewAccountButton, forgotPasswordButton;
	static DPanel loginScreen;
	JDialog signUpFrame;

	LoginScreen() {

		login = new DLabel("Log in", GUI.white, GUI.txtH1);
		login.setBounds(50, 40, 600, 40);
		email = new DLabel("Email", GUI.white, GUI.txtH3);
		email.setBounds(50, 120, 260, 40);
		emailTxt = new DTextField(40, GUI.darkGray, GUI.txtH3);
		emailTxt.setBounds(50, 160, 400, 40);
		password = new DLabel("Password", GUI.white, GUI.txtH3);
		password.setBounds(50, 220, 600, 40);
		passwordTxt = new JPasswordField(40);
		passwordTxt.setBounds(50, 260, 400, 40);

		/* create Account Button by Mahsa, merged by Jani */
		createNewAccountButton = new DButton("Sign up", GUI.white, GUI.txtH2, GUI.darkGray);
		createNewAccountButton.setBounds(50, 340, 130, 40);
		createNewAccountButton.addActionListener(this);
		/* -----------------------------------------------*/
		
		loginButton = new DButton("Log in", GUI.white, GUI.txtH2, GUI.darkerGray);
		loginButton.setBounds(190, 340, 150, 40);
		loginButton.addActionListener(this);
		forgotPasswordButton = new DButton("Forgot password?", GUI.white, GUI.txtH4, GUI.darkerGray);
		forgotPasswordButton.setBounds(350, 340, 150, 40);
		forgotPasswordButton.addActionListener(this);
		
		loginScreen = new DPanel(GUI.darkGray);
		loginScreen.setBorder(BasicBorders.getButtonBorder());
		loginScreen.setBounds(170, 80, 550, 400);
		loginScreen.setLayout(null);
		loginScreen.add(login);
		loginScreen.add(email);
		loginScreen.add(emailTxt);
		loginScreen.add(password);
		loginScreen.add(passwordTxt);
		loginScreen.add(createNewAccountButton);
		loginScreen.add(forgotPasswordButton);
		loginScreen.add(loginButton);
		
		GUI.home.add(loginScreen);
		
		/* sign up pop-up *
		 *----------------*/
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
				String username = emailTxt.getText();
				/* code addition to update car drop-down depending on user, by Aure   */
				CreateLogScreen.car.removeAllItems();
				for (String s : dc.querieCar("RegistryNumber", "Car", username)) {
					CreateLogScreen.car.addItem(s);
				}
				/*--------------------------------------------------------------------*/
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ae.getSource() == createNewAccountButton) {
			/* sign up pop-up *
			 *----------------*/
		}
	}
}

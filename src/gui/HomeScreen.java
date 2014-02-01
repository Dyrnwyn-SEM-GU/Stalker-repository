package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import stalker.DatabaseConnector;
import stalker.Main;

import elements.DButton;
import elements.DComboBox;
import elements.DLabel;
import elements.DPanel;
import elements.DTextField;

/* Home Screen by Aure */

public class HomeScreen implements ActionListener {

	DLabel oldPasswordLabel, newPasswordLabel1, newPasswordLabel2,
	carBrandLabel, gasTypeLabel, consumptionLabel, homeLabel,
	registeryNumberLabel, stalkerLabel, dyrnwynLabel, stalkerLabel2;
	static DLabel usernameLabel;
	public static DPanel homePanel;
	DPanel changePasswordPanel, carPanel, splashPanel;
	DButton editCarDetailButton, submitPasswordButton, logOutButton,
	changePasswordButton, saveCarButton, cancelCarButton,
	aboutUsButton;
	DTextField carBrandTxt, consumptionTxt, registeryNumberTxt, firstNameTxt,
	yourEmailTxt, reEnterPasswordTxt, passwordTxt;
	DComboBox gasType;
	JDialog changePasswordDialog, dyrnwynDialog;
	static JDialog carDetailDialog;
	JPasswordField oldPasswordTxt, newPasswordTxt1, newPasswordTxt2;

	HomeScreen() throws SQLException {

		DatabaseConnector dc = new DatabaseConnector();

		homeLabel = new DLabel("You are logged in as:", GUI.white, GUI.txtH1);
		homeLabel.setBounds(50, 40, 600, 40);

		usernameLabel = new DLabel(GUI.username, GUI.white, GUI.txtH3);
		usernameLabel.setBounds(50, 80, 500, 40);

		logOutButton = new DButton("Log out", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		logOutButton.setBounds(215, 350, 250, 40);
		logOutButton.addActionListener(this);

		changePasswordButton = new DButton("Change password", GUI.white,
				GUI.txtH2, GUI.darkerGray);
		changePasswordButton.setBounds(215, 230, 250, 40);
		changePasswordButton.addActionListener(this);

		aboutUsButton = new DButton("About STALKER", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		aboutUsButton.setBounds(215, 290, 250, 40);
		aboutUsButton.addActionListener(this);

		/* edit car details, by Mahsa */
		editCarDetailButton = new DButton("Add car", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		editCarDetailButton.setBounds(215, 170, 250, 40);
		editCarDetailButton.addActionListener(this);

		/* edit cars pop-up */
		carPanel = new DPanel(GUI.darkGray);
		carPanel.setBounds(0, 0, 500, 600);
		carPanel.setLayout(null);
		carPanel.setVisible(true);

		carBrandLabel = new DLabel("Car brand ", GUI.white, GUI.txtH3);
		carBrandLabel.setBounds(50, 40, 150, 40);
		carBrandTxt = new DTextField(20, GUI.darkerGray, GUI.txtH3);
		carBrandTxt.setBounds(200, 40, 250, 40);

		gasTypeLabel = new DLabel("Gas type ", GUI.white, GUI.txtH3);
		gasTypeLabel.setBounds(50, 100, 150, 40);

		gasType = new DComboBox(dc.getColumn("CarTypes", "CarTypes"),
				GUI.darkGray, GUI.txtH3, GUI.white);
		gasType.setBounds(200, 100, 250, 40);

		consumptionLabel = new DLabel("Consumption", GUI.white, GUI.txtH3);
		consumptionLabel.setBounds(50, 160, 150, 40);
		consumptionTxt = new DTextField(20, GUI.darkerGray, GUI.txtH3);
		consumptionTxt.setBounds(200, 160, 250, 40);

		registeryNumberLabel = new DLabel(
				"<html>Registration<br>number</html>", GUI.white, GUI.txtH3);
		registeryNumberLabel.setBounds(50, 220, 150, 40);
		registeryNumberTxt = new DTextField(20, GUI.darkerGray, GUI.txtH3);
		registeryNumberTxt.setBounds(200, 220, 250, 40);

		saveCarButton = new DButton("Save", GUI.white, GUI.txtH3,
				GUI.darkerGray);
		saveCarButton.setBounds(50, 300, 150, 40);
		saveCarButton.addActionListener(this);

		cancelCarButton = new DButton("Cancel", GUI.white, GUI.txtH3,
				GUI.darkerGray);
		cancelCarButton.setBounds(300, 300, 150, 40);
		cancelCarButton.addActionListener(this);

		carPanel.add(carBrandLabel);
		carPanel.add(carBrandTxt);
		carPanel.add(consumptionLabel);
		carPanel.add(consumptionTxt);
		carPanel.add(registeryNumberLabel);
		carPanel.add(registeryNumberTxt);
		carPanel.add(cancelCarButton);
		carPanel.add(saveCarButton);
		carPanel.add(gasTypeLabel);
		carPanel.add(gasType);

		carDetailDialog = new JDialog();
		carDetailDialog.setBackground(GUI.darkerGray);
		carDetailDialog.setTitle("Add a car");
		carDetailDialog.setSize(500, 400);
		carDetailDialog.setLocationRelativeTo(null);
		carDetailDialog.setLayout(null);
		carDetailDialog.setResizable(false);
		carDetailDialog.add(carPanel);

		/* change password pop-up, by Aure */
		changePasswordPanel = new DPanel(GUI.darkGray);
		changePasswordPanel.setBounds(0, 0, 500, 600);
		changePasswordPanel.setLayout(null);
		changePasswordPanel.setVisible(true);

		oldPasswordLabel = new DLabel("Enter old password:", GUI.white,
				GUI.txtH3);
		oldPasswordLabel.setBounds(50, 50, 400, 20);
		oldPasswordTxt = new JPasswordField();
		oldPasswordTxt.setBounds(50, 80, 400, 40);

		newPasswordLabel1 = new DLabel("Enter new password:", GUI.white,
				GUI.txtH3);
		newPasswordLabel1.setBounds(50, 160, 400, 20);
		newPasswordTxt2 = new JPasswordField();
		newPasswordTxt2.setBounds(50, 190, 400, 40);

		newPasswordLabel2 = new DLabel("Enter new password again:", GUI.white,
				GUI.txtH3);
		newPasswordLabel2.setBounds(50, 270, 400, 20);
		newPasswordTxt1 = new JPasswordField();
		newPasswordTxt1.setBounds(50, 300, 400, 40);

		submitPasswordButton = new DButton("Submit", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		submitPasswordButton.setBounds(150, 400, 200, 40);
		submitPasswordButton.addActionListener(this);

		changePasswordDialog = new JDialog();
		changePasswordDialog.setTitle("Change password");
		changePasswordDialog.setSize(500, 500);
		changePasswordDialog.setLocationRelativeTo(null);
		changePasswordDialog.setLayout(null);
		changePasswordDialog.setResizable(false);

		changePasswordPanel.add(oldPasswordLabel);
		changePasswordPanel.add(oldPasswordTxt);
		changePasswordPanel.add(newPasswordLabel1);
		changePasswordPanel.add(newPasswordTxt2);
		changePasswordPanel.add(newPasswordLabel2);
		changePasswordPanel.add(newPasswordTxt1);
		changePasswordPanel.add(submitPasswordButton);
		changePasswordDialog.add(changePasswordPanel);

		/* a splashscreen about the project, by Aure */

		splashPanel = new DPanel(GUI.darkGray);
		splashPanel.setBounds(0, 0, 600, 350);
		splashPanel.setLayout(null);
		splashPanel.setVisible(true);

		stalkerLabel = new DLabel("<html>STALKER <br> version 1.1</html>",
				GUI.white, GUI.txtH1);
		stalkerLabel.setBounds(200, 50, 250, 100);

		stalkerLabel2 = new DLabel("<html>a digital travel log book</html>",
				GUI.white, GUI.txtH2);
		stalkerLabel2.setBounds(50, 135, 350, 100);

		dyrnwynLabel = new DLabel(
				"<html>by Jani Pasanen, Gabriele Kasparaviciute, " +
				"<br>Danielle Santos, Mahsa Abassian," +
				"<br> Sally Masry and Aurelien Hontabat</html>",
				GUI.white, GUI.txtH3);
		dyrnwynLabel.setBounds(50, 200, 500, 100);

		dyrnwynDialog = new JDialog();
		dyrnwynDialog.setTitle("about us");
		dyrnwynDialog.setSize(600, 350);
		dyrnwynDialog.setLocationRelativeTo(null);
		dyrnwynDialog.setLayout(null);
		dyrnwynDialog.setResizable(false);

		try {
			JLabel logo = new JLabel(
					(GUI.pic(new File("img/Stalker_Icon.png"))));
			logo.setBounds(50, 50, 100, 100);
			splashPanel.add(logo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		splashPanel.add(dyrnwynLabel);
		splashPanel.add(stalkerLabel);
		splashPanel.add(stalkerLabel2);

		dyrnwynDialog.add(splashPanel);

		/*  */

		homePanel = new DPanel(GUI.darkGray);
		homePanel.setBounds(100, 0, 680, 600);
		homePanel.setVisible(false);
		homePanel.setLayout(null);
		homePanel.add(editCarDetailButton);
		homePanel.add(changePasswordButton);
		homePanel.add(logOutButton);
		homePanel.add(usernameLabel);
		homePanel.add(aboutUsButton);
		homePanel.add(homeLabel);

		GUI.home.add(homePanel);
	}

	public void actionPerformed(ActionEvent ae) {

		/* actionListeners by Mahsa */
		if (ae.getSource() == editCarDetailButton) {
			carBrandTxt.setText("");
			registeryNumberTxt.setText("");
			consumptionTxt.setText("");
			carDetailDialog.setVisible(true);
			Main.pancakes++;
		}
		if (ae.getSource() == saveCarButton) {
			String consumption = Main.makeDecimal(consumptionTxt.getText());
			if (Main.checkString(carBrandTxt.getText())
					&& !carBrandTxt.getText().equals("")
					&& !registeryNumberTxt.getText().equals("")) {
				if (consumption != null) {
					try {
						DatabaseConnector dc = new DatabaseConnector();
						dc.insertNewCar(carBrandTxt.getText(),
								registeryNumberTxt.getText(), gasType
								.getSelectedItem().toString(),
								consumption, GUI.username);
						LoginScreen.emailTxt.setText("");
						LoginScreen.passwordTxt.setText("");
						for (String s : dc.queryCar("RegistryNumber", "Car",
								GUI.username)) {
							CreateLogScreen.carDropDown.addItem(s);
						}
						JOptionPane.showMessageDialog(GUI.home,
								"Car was created", "car was created",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(GUI.home,
								"Car could not be created", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					carDetailDialog.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(GUI.home,
							"Please enter only decimal values for consumption",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(GUI.home, "Missing values",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			Main.pancakes++;
		}
		/* actionListeners by Aure */
		if (ae.getSource() == cancelCarButton) {
			carDetailDialog.setVisible(false);
			Main.pancakes++;
		}
		if (ae.getSource() == aboutUsButton) {
			dyrnwynDialog.setVisible(true);
			Main.pancakes++;
		}
		if (ae.getSource() == changePasswordButton) {
			changePasswordDialog.setVisible(true);
			Main.pancakes++;
		}
		if (ae.getSource() == logOutButton) {
			changePasswordDialog.setVisible(false);
			carDetailDialog.setVisible(false);
			GUI.tabPane.setEnabled(false);
			GUI.username = null;
			HomeScreen.homePanel.setVisible(false);
			LoginScreen.loginScreenPanel.setVisible(true);
			LoginScreen.passwordTxt.setText("");
			LoginScreen.emailTxt.setText("");
			Main.pancakes++;
			System.out.println("You owe us " + Main.pancakes
					+ " pancakes! Read the terms and conditions next time");
		}
		if (ae.getSource() == submitPasswordButton) {
			try {
				DatabaseConnector dc = new DatabaseConnector();

				String password = dc.queryCredentials("password", "username",
						GUI.username);

				if (oldPasswordTxt.getText().equals(password)) {
					if (newPasswordTxt2.getText().equals(
							newPasswordTxt1.getText())) {
						dc.insertPassword(newPasswordTxt1.getText(),
								GUI.username);
						changePasswordDialog.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(GUI.home,
								"The new Passwords don't match", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(GUI.home,
							"The password you entered is wrong", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Main.pancakes++;
		}
	}
}

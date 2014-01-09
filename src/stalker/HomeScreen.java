package stalker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JDialog;

import elements.DButton;
import elements.DComboBox;
import elements.DLabel;
import elements.DPanel;
import elements.DTextField;

/* Home Screen by Aure */

public class HomeScreen implements ActionListener {

	DLabel oldPasswordLabel,
	newPasswordLabel1, newPasswordLabel2, carBrandLabel, carTypeLabel,
	consumptionLabel, registeryNumberLabel;
	static DPanel homePanel;
	DPanel changePasswordPanel, carPanel;
	DButton editCarDetailButton, submitPasswordButton,
	changePasswordButton, saveCarButton, cancelCarButton;
	DTextField newPasswordTxt1, newPasswordTxt2, oldPasswordTxt, carBrandTxt,
	consumptionTxt, registeryNumberTxt, firstNameTxt, yourEmailTxt,
	reEnterPasswordTxt, passwordTxt;
	DComboBox carType;
	JDialog changePasswordDialog, carDetailDialog;

	HomeScreen() throws SQLException {

		DatabaseConnector dc = new DatabaseConnector();

		/* edit car details, by Mahsa */
		editCarDetailButton = new DButton("edit car details", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		editCarDetailButton.setBounds(150, 100, 250, 40);
		editCarDetailButton.addActionListener(this);

		/* edit cars pop-up */
		carPanel = new DPanel(GUI.darkGray);
		carPanel.setBounds(0, 0, 680, 600);
		carPanel.setLayout(null);
		carPanel.setVisible(true);

		carBrandLabel = new DLabel("Car Brand ", GUI.white, GUI.txtH3);
		carBrandLabel.setBounds(20, 40, 150, 28);
		carBrandTxt = new DTextField(20, GUI.darkerGray, GUI.txtH3);
		carBrandTxt.setBounds(200, 40, 250, 28);

		carTypeLabel = new DLabel("Car Type ", GUI.white, GUI.txtH3);
		carTypeLabel.setBounds(20, 100, 150, 28);
		
		carType = new DComboBox(dc.getColumn("RegistryNumber", "Car"),
				GUI.darkGray, GUI.txtH3, GUI.white);
		carType.setBounds(200, 100, 250, 28);

		consumptionLabel = new DLabel("Consumption", GUI.white, GUI.txtH3);
		consumptionLabel.setBounds(20, 160, 150, 28);
		consumptionTxt = new DTextField(20, GUI.white, GUI.txtH3);
		consumptionTxt.setBounds(200, 160, 250, 28);

		registeryNumberLabel = new DLabel("Registry Number", GUI.white,
				GUI.txtH3);
		registeryNumberLabel.setBounds(20, 220, 150, 28);
		registeryNumberTxt = new DTextField(20, GUI.darkerGray, GUI.txtH3);
		registeryNumberTxt.setBounds(200, 220, 250, 28);

		saveCarButton = new DButton("Save", GUI.white, GUI.txtH3, GUI.darkGray);
		saveCarButton.setBounds(20, 320, 150, 35);
		saveCarButton.addActionListener(this);

		cancelCarButton = new DButton("Cancel", GUI.white, GUI.txtH3,
				GUI.darkGray);
		cancelCarButton.setBounds(250, 320, 150, 35);
		cancelCarButton.addActionListener(this);
		
		carPanel.add(carBrandLabel);
		carPanel.add(carBrandTxt);
		carPanel.add(consumptionLabel);
		carPanel.add(consumptionTxt);
		carPanel.add(registeryNumberLabel);
		carPanel.add(registeryNumberTxt);
		carPanel.add(cancelCarButton);
		carPanel.add(saveCarButton);
		carPanel.add(carTypeLabel);
		carPanel.add(carType);
		
		carDetailDialog = new JDialog();
		carDetailDialog.setBackground(GUI.darkerGray);
		carDetailDialog.setTitle("Change Password");
		carDetailDialog.setSize(500, 600);
		carDetailDialog.setLocationRelativeTo(null);
		carDetailDialog.setLayout(null);
		
		carDetailDialog.add(carPanel);

		/* change password pop-up, by Aure */
		changePasswordPanel = new DPanel(GUI.darkerGray);
		changePasswordPanel.setBounds(0, 0, 680, 600);
		changePasswordPanel.setLayout(null);
		changePasswordPanel.setVisible(true);

		oldPasswordLabel = new DLabel("enter old password", GUI.white,
				GUI.txtH3);
		oldPasswordLabel.setBounds(50, 50, 400, 20);
		oldPasswordTxt = new DTextField(40, GUI.darkGray, GUI.txtH3);
		oldPasswordTxt.setBounds(50, 90, 400, 40);

		newPasswordLabel1 = new DLabel("enter new password", GUI.white,
				GUI.txtH3);
		newPasswordLabel1.setBounds(50, 160, 400, 20);
		newPasswordTxt2 = new DTextField(40, GUI.darkGray, GUI.txtH3);
		newPasswordTxt2.setBounds(50, 200, 400, 40);

		newPasswordLabel2 = new DLabel("enter new password again", GUI.white,
				GUI.txtH3);
		newPasswordLabel2.setBounds(50, 270, 400, 20);
		newPasswordTxt1 = new DTextField(40, GUI.darkGray, GUI.txtH3);
		newPasswordTxt1.setBounds(50, 310, 400, 40);

		submitPasswordButton = new DButton("submit", GUI.white, GUI.txtH2,
				GUI.darkGray);
		submitPasswordButton.setBounds(150, 400, 200, 80);
		submitPasswordButton.addActionListener(this);

		changePasswordDialog = new JDialog();
		changePasswordDialog.setBackground(GUI.darkerGray);
		changePasswordDialog.setTitle("Change Password");
		changePasswordDialog.setSize(500, 600);
		changePasswordDialog.setLocationRelativeTo(null);
		changePasswordDialog.setLayout(null);

		changePasswordPanel.add(oldPasswordLabel);
		changePasswordPanel.add(oldPasswordTxt);
		changePasswordPanel.add(newPasswordLabel1);
		changePasswordPanel.add(newPasswordTxt2);
		changePasswordPanel.add(newPasswordLabel2);
		changePasswordPanel.add(newPasswordTxt1);
		changePasswordPanel.add(submitPasswordButton);

		changePasswordDialog.add(changePasswordPanel);

		changePasswordButton = new DButton("change Password", GUI.white,
				GUI.txtH2, GUI.darkerGray);
		changePasswordButton.setBounds(150, 300, 250, 40);
		changePasswordButton.addActionListener(this);

		homePanel = new DPanel(GUI.darkGray);
		homePanel.setBounds(100, 0, 680, 600);
		homePanel.setVisible(false);
		homePanel.setLayout(null);
		homePanel.add(editCarDetailButton);
		homePanel.add(changePasswordButton);

		GUI.home.add(homePanel);
	}

	public void actionPerformed(ActionEvent ae) {

		/* actionListeners by Mahsa */
		if (ae.getSource() == editCarDetailButton) {
			carDetailDialog.setVisible(true);
		}
		if (ae.getSource() == saveCarButton) {
		/* code to upload the car to the database missing */
			carDetailDialog.setVisible(false);
		}
		if (ae.getSource() == cancelCarButton) {
			carDetailDialog.setVisible(false);
		}
		/* actionListeners by Aure */
		if (ae.getSource() == changePasswordButton) {
			changePasswordDialog.setVisible(true);
		}
		if (ae.getSource() == submitPasswordButton) {
			try {
				DatabaseConnector dc = new DatabaseConnector();

				String password = dc.queryCredentials("password", "username",
						"**username**");

				if (oldPasswordTxt.getText().equals(password)) {
					if (newPasswordTxt2.getText().equals(
							newPasswordTxt1.getText())) {
						dc.insertPassword(newPasswordTxt1.getText(),
								"**username**");
						changePasswordDialog.setVisible(false);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out
				.println("Something wrong in the submitPassword actionslistener");
			}
		}
	}
}

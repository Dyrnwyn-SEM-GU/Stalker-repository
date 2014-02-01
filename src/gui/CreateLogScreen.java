package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.jbundle.thin.base.screen.jcalendarbutton.JCalendarButton;

import stalker.DatabaseConnector;
import stalker.Main;

import elements.DButton;
import elements.DComboBox;
import elements.DLabel;
import elements.DPanel;
import elements.DTextField;

/* Generates the Create Log window 								*
 * created by MAHSA, database connectivity and redesign by Aure */

public class CreateLogScreen implements ActionListener {

	DLabel createLogLabel, fromLabel, toLabel, startKmLabel, endKmLabel,
	reasonOfTripLabel, dateLabel, carLabel, addExtraCostLabel,
	typeOfCostLabel, totalCostLabel, costDateLabel, fileLabel;
	DComboBox toDropDown, fromDropDown;
	static DComboBox carDropDown;
	DTextField reasonTripTxt, startKmTxt, endKmTxt, typeCostTxt,
	costAmmountTxt, dateTxt;
	DButton extraCostButton, submitTravelLogButton, uploadFileButton,
	submitExtraCostButton;
	JCalendarButton calendarButton = new JCalendarButton();
	DPanel createPanel, extraCostPanel;
	JDialog extraCostDialog;

	ArrayList<String> typeCost = new ArrayList<String>();
	ArrayList<String> costAmmount = new ArrayList<String>();

	CreateLogScreen() throws SQLException {

		DatabaseConnector dc = new DatabaseConnector();

		/* extra cost pop-up by Danielle redesigned by Aure */
		extraCostPanel = new DPanel(GUI.darkGray);
		extraCostPanel.setBounds(0, 0, 400, 500);
		extraCostPanel.setLayout(null);

		addExtraCostLabel = new DLabel("Add extra costs", GUI.white, GUI.txtH1);
		addExtraCostLabel.setBounds(50, 40, 400, 40);
		typeOfCostLabel = new DLabel("Type of cost:", GUI.white, GUI.txtH3);
		typeOfCostLabel.setBounds(50, 95, 500, 40);
		totalCostLabel = new DLabel("Total of the cost:", GUI.white, GUI.txtH3);
		totalCostLabel.setBounds(50, 195, 500, 40);

		typeCostTxt = new DTextField(20, GUI.darkerGray, GUI.txtH3);
		typeCostTxt.setBounds(50, 130, 300, 40);
		costAmmountTxt = new DTextField(20, GUI.darkerGray, GUI.txtH3);
		costAmmountTxt.setBounds(50, 230, 300, 40);

		uploadFileButton = new DButton("Upload receipt", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		uploadFileButton.setBounds(50, 350, 300, 40);
		uploadFileButton.addActionListener(this);

		fileLabel = new DLabel("", GUI.white, GUI.txtH4);
		fileLabel.setBounds(50, 300, 300, 40);

		submitExtraCostButton = new DButton("Submit extra costs", GUI.white,
				GUI.txtH2, GUI.darkerGray);
		submitExtraCostButton.setBounds(50, 400, 300, 40);
		submitExtraCostButton.addActionListener(this);

		extraCostPanel.add(addExtraCostLabel);
		extraCostPanel.add(typeOfCostLabel);
		extraCostPanel.add(uploadFileButton);
		extraCostPanel.add(typeCostTxt);
		extraCostPanel.add(totalCostLabel);
		extraCostPanel.add(costAmmountTxt);
		extraCostPanel.add(submitExtraCostButton);
		extraCostPanel.add(fileLabel);

		extraCostDialog = new JDialog();
		extraCostDialog.setTitle("Add extra costs");
		extraCostDialog.setBounds(800, 400, 400, 500);
		extraCostDialog.setLayout(null);
		extraCostDialog.setVisible(false);
		extraCostDialog.setResizable(false);
		extraCostDialog.add(extraCostPanel);

		/* create log window */
		calendarButton
		.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(
					java.beans.PropertyChangeEvent evt) {
				if (evt.getNewValue() instanceof Date)
					dateLabel.setText(GUI.dateFormat
							.format(((Date) (evt.getNewValue()))));
			}
		});

		createLogLabel = new DLabel("Create a new travel log", GUI.white,
				GUI.txtH1);
		createLogLabel.setBounds(50, 40, 600, 40);

		fromLabel = new DLabel("From:", GUI.white, GUI.txtH3);
		fromLabel.setBounds(50, 80, 160, 40);
		fromDropDown = new DComboBox(dc.getColumn("City", "Locations"),
				GUI.darkGray, GUI.txtH3, GUI.white);
		fromDropDown.setBounds(50, 120, 160, 40);

		toLabel = new DLabel("To:", GUI.white, GUI.txtH3);
		toLabel.setBounds(230, 80, 160, 40);
		toDropDown = new DComboBox(dc.getColumn("City", "Locations"),
				GUI.darkGray, GUI.txtH3, GUI.white);
		toDropDown.setBounds(230, 120, 160, 40);

		carLabel = new DLabel("Car:", GUI.white, GUI.txtH3);
		carLabel.setBounds(50, 190, 260, 40);
		carDropDown = new DComboBox(dc.queryCar("RegistryNumber", "Car",
				GUI.username), GUI.darkGray, GUI.txtH3, GUI.white);
		carDropDown.setBounds(50, 230, 260, 40);

		reasonOfTripLabel = new DLabel("Reason of trip:", GUI.white, GUI.txtH3);
		reasonOfTripLabel.setBounds(380, 190, 260, 40);
		reasonTripTxt = new DTextField("", 20, GUI.darkGray, GUI.txtH3);
		reasonTripTxt.setBounds(380, 230, 260, 40);

		startKmLabel = new DLabel("Start kilometer:", GUI.white, GUI.txtH3);
		startKmLabel.setBounds(50, 310, 260, 40);
		startKmTxt = new DTextField("", 20, GUI.darkGray, GUI.txtH3);
		startKmTxt.setBounds(50, 350, 260, 40);

		endKmLabel = new DLabel("End kilometer:", GUI.white, GUI.txtH3);
		endKmLabel.setBounds(380, 310, 260, 40);
		endKmTxt = new DTextField("", 20, GUI.darkGray, GUI.txtH3);
		endKmTxt.setBounds(380, 350, 260, 40);

		submitTravelLogButton = new DButton("Submit", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		submitTravelLogButton.setBounds(220, 490, 250, 40);
		submitTravelLogButton.addActionListener(this);
		dateLabel = new DLabel("", GUI.white, GUI.txtH3);
		dateLabel.setBounds(450, 120, 300, 40);
		calendarButton.setBounds(590, 120, 40, 40);

		/* extraCost button by Danielle, merged by Jani */
		extraCostButton = new DButton("Add extra costs", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		extraCostButton.setBounds(220, 420, 250, 40);
		extraCostButton.addActionListener(this);
		/*---------------------------------------------*/

		createPanel = new DPanel(GUI.darkGray);
		createPanel.setBounds(100, 0, 680, 600);
		createPanel.setLayout(null);
		createPanel.add(createLogLabel);
		createPanel.add(fromLabel);
		createPanel.add(fromDropDown);
		createPanel.add(toLabel);
		createPanel.add(toDropDown);
		createPanel.add(dateLabel);
		createPanel.add(calendarButton);
		createPanel.add(carLabel);
		createPanel.add(carDropDown);
		createPanel.add(reasonOfTripLabel);
		createPanel.add(reasonTripTxt);
		createPanel.add(startKmLabel);
		createPanel.add(startKmTxt);
		createPanel.add(endKmLabel);
		createPanel.add(endKmTxt);
		createPanel.add(submitTravelLogButton);
		createPanel.add(extraCostButton);

		GUI.create.add(createPanel);
	}

	public void actionPerformed(ActionEvent ae) {
		try {
			DatabaseConnector dc = new DatabaseConnector();
			if (ae.getSource() == submitTravelLogButton) {

				String from = fromDropDown.getSelectedItem().toString();
				String to = toDropDown.getSelectedItem().toString();
				String startKm = startKmTxt.getText();
				String endKm = endKmTxt.getText();
				String reason = reasonTripTxt.getText();
				String car = carDropDown.getSelectedItem().toString();
				String date = dateLabel.getText();

				if (from == "" || to == "" || startKm == "" || endKm == ""
						|| reason == "" || car == "" || date == "") {
					JOptionPane.showMessageDialog(GUI.home, "Missing values",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if (!Main.checkNumber(startKm)
						|| !Main.checkNumber(endKm)) {
					JOptionPane.showMessageDialog(GUI.home,
							"Please enter numbers in the KM fields", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (Integer.parseInt(startKm) > Integer.parseInt(endKm)) {
					JOptionPane
					.showMessageDialog(
							GUI.home,
							"The end Km value must be larger then the start Km value",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					dc.insertTripData(startKm, endKm, from, to, reason, car,
							date);
					for (int i = 0; i < typeCost.size(); i++) {
						dc.insertExtraCost(typeCost.get(i), costAmmount.get(i),
								Main.path, date, dc.idTripdata());
					}
					typeCost.clear();
					costAmmount.clear();
					JOptionPane.showMessageDialog(GUI.home,
							"Travel log has been submitted", "Success!",
							JOptionPane.INFORMATION_MESSAGE);
					dateLabel.setText("");
					reasonTripTxt.setText("");
					endKmTxt.setText("");
					startKmTxt.setText("");
					dateLabel.setText("");
					GUI.model = dc.reportTable(GUI.model);
					GUI.model.fireTableDataChanged();
				}
				Main.pancakes++;
			}
			if (ae.getSource() == submitExtraCostButton) {
				if (typeCostTxt.getText().equals("")
						|| costAmmountTxt.getText().equals("")) {
					JOptionPane.showMessageDialog(GUI.home, "Missing Values",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					if (Main.checkString(typeCostTxt.getText())) {
						if (Main.makeDecimal(costAmmountTxt.getText()) != null) {
							typeCost.add(typeCostTxt.getText());
							costAmmount.add(Main.makeDecimal(costAmmountTxt
									.getText()));
							extraCostDialog.setVisible(false);
							typeCostTxt.setText("");
							costAmmountTxt.setText("");
							JOptionPane
							.showMessageDialog(
									GUI.home,
									"The extra costs will be added when you submit your travel log",
									"Extra costs",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane
							.showMessageDialog(
									GUI.home,
									"Please enter only decimal values for the ammount",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane
						.showMessageDialog(
								GUI.home,
								"Please enter only letter for the type of cost",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				Main.pancakes++;
			}
			if (ae.getSource() == extraCostButton) {
				extraCostDialog.setVisible(true);
				Main.pancakes++;
			}
			if (ae.getSource() == uploadFileButton) {
				Main.uploadPic();
				fileLabel.setText(Main.path);
				Main.pancakes++;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}

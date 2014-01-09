package stalker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.jbundle.thin.base.screen.jcalendarbutton.JCalendarButton;

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
	typeOfCostLabel, totalCostLabel, costDateLabel;
	DComboBox to, from;
	static DComboBox car;
	DTextField reasonTripTxt, startKmTxt, endKmTxt, typeCostTxt, costTxt,
	dateTxt;
	DButton extraCostButton, submitButton, uploadFileButton,
	submitExtraCostButton;
	JCalendarButton calendarButton = new JCalendarButton();
	DPanel createPanel, extraCostPanel;
	JDialog extraCostDialog;

	String username;

	CreateLogScreen() throws SQLException {

		DatabaseConnector dc = new DatabaseConnector();

		/* extra cost pop-up by Danielle */
		extraCostPanel = new DPanel(GUI.darkGray);
		extraCostPanel.setBounds(0, 0, 680, 600);
		extraCostPanel.setLayout(null);

		addExtraCostLabel = new DLabel("Add extra costs", GUI.white,
				GUI.txtH1);
		addExtraCostLabel.setBounds(10, 15, 400, 40);
		typeOfCostLabel = new DLabel("Type of Cost:", GUI.white, GUI.txtH3);
		typeOfCostLabel.setBounds(10, 60, 150, 40);
		totalCostLabel = new DLabel("Total of the Cost:", GUI.white, GUI.txtH3);
		totalCostLabel.setBounds(10, 160, 160, 40);
		costDateLabel = new DLabel("Date:", GUI.white, GUI.txtH3);
		costDateLabel.setBounds(10, 260, 50, 40);

		typeCostTxt = new DTextField(20, GUI.darkerGray, GUI.txtH3);
		typeCostTxt.setBounds(10, 100, 300, 40);
		costTxt = new DTextField(20, GUI.darkerGray, GUI.txtH3);
		costTxt.setBounds(10, 200, 300, 40);
		dateTxt = new DTextField(20, GUI.darkerGray, GUI.txtH3);
		dateTxt.setBounds(10, 300, 300, 40);

		uploadFileButton = new DButton("Upload Picture", GUI.white, GUI.txtH2,
				GUI.darkGray);
		uploadFileButton.setBounds(380, 100, 150, 40);
		uploadFileButton.addActionListener(this);
		submitExtraCostButton = new DButton("Submit Extra Cost", GUI.white,
				GUI.txtH2, GUI.darkerGray);
		submitExtraCostButton.setBounds(150, 420, 250, 40);
		submitExtraCostButton.addActionListener(this);

		extraCostPanel.add(addExtraCostLabel);
		extraCostPanel.add(typeOfCostLabel);
		extraCostPanel.add(uploadFileButton);
		extraCostPanel.add(typeCostTxt);
		extraCostPanel.add(totalCostLabel);
		extraCostPanel.add(costTxt);
		extraCostPanel.add(costDateLabel);
		extraCostPanel.add(dateTxt);
		extraCostPanel.add(submitExtraCostButton);

		extraCostDialog = new JDialog();
		extraCostDialog.setBounds(350, 108, 670, 570);
		extraCostDialog.setLayout(null);
		extraCostDialog.setVisible(false);
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
		from = new DComboBox(dc.getColumn("City", "Locations"), GUI.darkGray,
				GUI.txtH3, GUI.white);
		from.setBounds(50, 120, 160, 40);

		toLabel = new DLabel("To:", GUI.white, GUI.txtH3);
		toLabel.setBounds(230, 80, 160, 40);
		to = new DComboBox(dc.getColumn("City", "Locations"), GUI.darkGray,
				GUI.txtH3, GUI.white);
		to.setBounds(230, 120, 160, 40);

		carLabel = new DLabel("Car:", GUI.white, GUI.txtH3);
		carLabel.setBounds(50, 190, 260, 40);
		car = new DComboBox(dc.querieCar("RegistryNumber", "Car", username),
				GUI.darkGray, GUI.txtH3, GUI.white);
		car.setBounds(50, 230, 260, 40);

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

		submitButton = new DButton("Submit", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		submitButton.setBounds(280, 450, 120, 40);
		submitButton.addActionListener(this);
		dateLabel = new DLabel("", GUI.white, GUI.txtH3);
		dateLabel.setBounds(400, 160, 300, 40);
		calendarButton.setBounds(590, 120, 40, 40);

		/* extraCost button by Danielle, merged by Jani */
		extraCostButton = new DButton("Add extra costs", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		extraCostButton.setBounds(220, 400, 230, 40);
		extraCostButton.addActionListener(this);
		/*---------------------------------------------*/

		createPanel = new DPanel(GUI.darkGray);
		createPanel.setBounds(100, 0, 680, 600);
		createPanel.setLayout(null);
		createPanel.add(createLogLabel);
		createPanel.add(fromLabel);
		createPanel.add(from);
		createPanel.add(toLabel);
		createPanel.add(to);
		createPanel.add(dateLabel);
		createPanel.add(calendarButton);
		createPanel.add(carLabel);
		createPanel.add(car);
		createPanel.add(reasonOfTripLabel);
		createPanel.add(reasonTripTxt);
		createPanel.add(startKmLabel);
		createPanel.add(startKmTxt);
		createPanel.add(endKmLabel);
		createPanel.add(endKmTxt);
		createPanel.add(submitButton);
		createPanel.add(extraCostButton);

		GUI.create.add(createPanel);
	}

	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == submitButton) {
			try {
				DatabaseConnector dc = new DatabaseConnector();
				String f = from.getSelectedItem().toString();
				String t = to.getSelectedItem().toString();
				String ks = startKmTxt.getText();
				String ke = endKmTxt.getText();
				String reason = reasonTripTxt.getText();
				String name = "000";
				String c = car.getSelectedItem().toString();
				String d = dateLabel.getText();

				if (f == "" || t == "" || ks == "" || ke == "" || reason == ""
						|| username == "" || name == "" || c == "" || d == "") {
					new JOptionPane("missing value");
				} else {
					dc.insertTripData(ks, ke, f, t, reason, username, name, c,
							d);
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		if (ae.getSource() == submitExtraCostButton) {
			/* code to submit the extra cost is missing */
			extraCostDialog.setVisible(false);
		}
		if (ae.getSource() == extraCostButton) {
			extraCostDialog.setVisible(true);
		}
		if (ae.getSource() == uploadFileButton) {
			try {
				DatabaseConnector dc = new DatabaseConnector();
				dc.uploadPic();
				dc.insertImage();
				dc.showPicture();

			} catch (IOException | SQLException e) {

				e.printStackTrace();
			}

		}
	}
}

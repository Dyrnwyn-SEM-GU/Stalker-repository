package stalker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.jbundle.thin.base.screen.jcalendarbutton.JCalendarButton;

import gui.elements.*;

public class GUI extends JFrame implements ActionListener {

	/*
	 * the looks for Fonts and the Colors used throughout the project are
	 * defined here
	 */
	public static Color white = new Color(255, 255, 255);
	public static Color gray = new Color(78, 78, 78);
	public static Color darkGray = new Color(49, 49, 49);
	public static Color darkerGray = new Color(35, 35, 35);

	public static Font txtH1 = new Font("Arial", 0, 36);
	public static Font txtH2 = new Font("Arial", 0, 24);
	public static Font txtH3 = new Font("Arial", 0, 20);
	public static Font txtH4 = new Font("Arial", 0, 14);

	public static GridLayout twobyOne = new GridLayout(2, 1);
	public static GridLayout fourbyOne = new GridLayout(4, 1);

	DLabel dateLabel = new DLabel("", white, txtH3);

	DButton exportButton, editButton, saveChangesButton, submit, logIn,
	filterButton, searchButton;

	DTextField textKm, emailTxt, passwordTxt, startKmTxt, endKmTxt, reasonTripTxt;

	DPanel home, create, report, grid, loginScreen, homePanel, filterPane;
	DComboBox from, to, car;

	JTabbedPane tabPane;
	JTable reportTable;

	// for testing only
	JLabel testPanel = new JLabel();

	/* Updated calendar code by Kashayar */
	JCalendarButton date = new JCalendarButton(); // "YYYY-MM-DD", new Date(113,
	// 11, 4));
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public GUI() throws SQLException {
		// creates a calenderDropdown
		date.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				if (evt.getNewValue() instanceof Date)
					dateLabel.setText(dateFormat.format(((Date) (evt
							.getNewValue()))));
			}
		});
		addElements();
		setTitle("STALKER");
		setSize(900, 650);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	/*
	 * this methods adds the ELEMENTS and the TABS to the JFrame by MAHSA
	 */

	void addElements() throws SQLException {

		home = new DPanel(gray);
		report = new DPanel(gray);
		create = new DPanel(gray);

		create.setLayout(null);
		report.setLayout(null);
		home.setLayout(null);

		addLoginScreen();
		addHome();
		addCreate();
		addReportFilter();

		try {
			addReportGrid();
		} catch (SQLException e) {
			System.out.println("some sql problem, sorry");
		}

		tabPane = new JTabbedPane();
		tabPane.setFont(txtH2);
		tabPane.addTab("Home", home);
		tabPane.addTab("Report", report);
		tabPane.addTab("Create", create);
		tabPane.setEnabled(false);
		add(tabPane);
	}

	/*
	 * this methods generates the CREATE window by MAHSA
	 * Added text labels and databaseconnection to fetch 
	 * information for For, To and Car dropdowns AURE, Jani
	 * 
	 * 
	 */

	void addCreate() throws SQLException {
		
		DatabaseConnector dc = new DatabaseConnector();
		
		DLabel createLog = new DLabel("Create a new travel log", white, txtH1);

		DLabel fromLabel = new DLabel("From:", white, txtH3);
		from =  new DComboBox(dc.getColumn("City", "Locations"), darkGray,
				txtH3, white);
		
		DLabel toLabel = new DLabel("To:", white, txtH3);
		to =  new DComboBox(dc.getColumn("City", "Locations"), darkGray, txtH3,
				white);
		
		DLabel carLabel = new DLabel("Car:", white, txtH3);
		car = new DComboBox(dc.getColumn("RegistryNumber", "Car"), darkGray, txtH3,
				white);

		DLabel reasonOfTrip = new DLabel("Reason of trip:", white, txtH3);
		reasonTripTxt = new DTextField("", 20, darkGray, txtH3);
		
		DLabel startKm = new DLabel("Start kilometer:", white, txtH3);
		startKmTxt = new DTextField("", 20, darkGray, txtH3);

		DLabel endKm = new DLabel("End kilometer:", white, txtH3);
		endKmTxt = new DTextField("", 20, darkGray, txtH3);

		submit = new DButton("Submit", white, txtH2, darkerGray);
		submit.addActionListener(this);

		DPanel createPanel = new DPanel(darkGray);

		createPanel.setBounds(100, 0, 680, 600);
		createPanel.setLayout(null);
		
		createLog.setBounds(50, 40, 600, 40);
		fromLabel.setBounds(50, 80, 100, 40);
		from.setBounds(50, 120, 100, 40);
		toLabel.setBounds(210, 80, 100, 40);
		to.setBounds(210, 120, 100, 40);
		dateLabel.setBounds(380, 160, 300, 40);
		date.setBounds(600, 120, 40, 40);
		carLabel.setBounds(50, 160, 260, 40);
		car.setBounds(50, 200, 260, 40);
		reasonOfTrip.setBounds(380, 160, 260, 40);
		reasonTripTxt.setBounds(380, 200, 260, 40);
		startKm.setBounds(50, 280, 260, 40);
		endKm.setBounds(380, 280, 260, 40);
		startKmTxt.setBounds(50, 320, 260, 40);
		endKmTxt.setBounds(380, 320, 260, 40);
		submit.setBounds(280, 420, 120, 40);

		createPanel.add(createLog);
		createPanel.add(fromLabel);
		createPanel.add(from);
		createPanel.add(toLabel);
		createPanel.add(to);
		createPanel.add(dateLabel);
		createPanel.add(date);
		createPanel.add(carLabel);
		createPanel.add(car);
		createPanel.add(reasonOfTrip);
		createPanel.add(reasonTripTxt);
		createPanel.add(startKm);
		createPanel.add(startKmTxt);
		createPanel.add(endKm);
		createPanel.add(endKmTxt);
		createPanel.add(submit);

		create.add(createPanel);
	}

	/*
	 * this methods generates the FILTERS for the REPORT window by MINA
	 */

	void addReportFilter() throws SQLException {

		DatabaseConnector dc = new DatabaseConnector();

		filterPane = new DPanel(darkGray);
		filterPane.setLayout(null);
		filterPane.setBounds(100, 0, 680, 600);

		DTextField startDate = new DTextField("dd/mm/yy", 0, darkGray, txtH3);
		startDate.setBounds(221, 27, 114, 28);
		filterPane.add(startDate);
		startDate.setColumns(10);

		DLabel lblFromDate = new DLabel("From date:", white, txtH3);
		lblFromDate.setBounds(145, 27, 94, 22);
		filterPane.add(lblFromDate);

		DTextField endDate = new DTextField("dd/mm/yy", 0, darkGray, txtH3);
		endDate.setBounds(430, 30, 114, 22);
		filterPane.add(endDate);
		endDate.setColumns(10);

		DLabel lblToDate = new DLabel("To date:", white, txtH3);
		lblToDate.setBounds(369, 33, 61, 16);
		filterPane.add(lblToDate);

		String[] userOrAdmin = { "User", "Admin" };
		DComboBox userAdmin = new DComboBox(userOrAdmin, darkGray, txtH3, white);
		userAdmin.setBounds(6, 28, 122, 28);
		filterPane.add(userAdmin);

		String[] comboBoxS = { "From" };
		DComboBox comboBox = new DComboBox(comboBoxS, darkGray, txtH3, white);
		comboBox.setBounds(221, 82, 122, 28);
		filterPane.add(comboBox);

		/*
		 * This part changed by mahsa combobox to textfield
		 */

		DTextField textKm = new DTextField("KM", 20, darkerGray, txtH3);
		textKm.setBounds(369, 82, 77, 28);
		filterPane.add(textKm);

		DComboBox city = new DComboBox(/*dc.getColumn("City", "Locations")*/new String[]{}, darkGray, txtH3,
				white);
		city.setBounds(221, 122, 122, 28);
		filterPane.add(city);

		DComboBox car = new DComboBox(/*dc.getColumn("City", "Locations")*/new String[]{}, darkGray, txtH3,
				white);
		car.setBounds(369, 122, 77, 28);
		filterPane.add(car);

		DComboBox purpose = new DComboBox(/*dc.getColumn("City", "Locations")*/new String[]{}, darkGray, txtH3,
				white);
		purpose.setBounds(221, 180, 225, 28);
		filterPane.add(purpose);

		DComboBox extraCosts = new DComboBox(/*dc.getColumn("City", "Locations")*/new String[]{}, darkGray, txtH3,
				white);
		extraCosts.setBounds(221, 230, 225, 28);
		filterPane.add(extraCosts);

		searchButton = new DButton("Search", white, txtH2, darkerGray);
		searchButton.addActionListener(this);
		searchButton.setBounds(221, 270, 225, 28);
		filterPane.add(searchButton);

		report.add(filterPane);

	}

	/*
	 * this methods generates the TABLE for the REPORT window by AURELIEN
	 */

	void addReportGrid() throws SQLException {

		DatabaseConnector dc = new DatabaseConnector();

		grid = new DPanel(darkGray);
		grid.setLayout(null);
		grid.setBounds(100, 0, 680, 600);
		grid.setVisible(false);

		/*
		 * Add export button, save button, edit button (by mahsa)
		 */

		exportButton = new DButton("Export csv", white, txtH3, darkerGray);
		exportButton.addActionListener(this);
		exportButton.setBounds(500, 500, 140, 28);
		grid.add(exportButton);

		saveChangesButton = new DButton("save change", white, txtH3, darkerGray);
		saveChangesButton.addActionListener(this);
		saveChangesButton.setBounds(115, 500, 110, 28);
		grid.add(saveChangesButton);

		editButton = new DButton("Edit", white, txtH3, darkerGray);
		editButton.addActionListener(this);
		editButton.setBounds(40, 500, 70, 28);
		grid.add(editButton);

		filterButton = new DButton("Filter", white, txtH2, darkerGray);
		filterButton.addActionListener(this);
		filterButton.setBounds(221, 500, 225, 28);
		grid.add(filterButton);

		DPanel tablePanel = new DPanel(gray);

		DefaultTableModel model = new DefaultTableModel();
		JTable reportTable = new JTable();
		reportTable.setFont(txtH4);
		reportTable.setForeground(darkGray);

		// the model is generated with the reportTable method
		model = dc.reportTable(model);
		// and added to the JTable: reportTable
		reportTable.setModel(model);
		model.fireTableDataChanged();

		reportTable.setBounds(0, 0, 500, 500);
		reportTable.setEnabled(false);
		tablePanel.setBounds(40, 40, 600, 400);
		tablePanel.setLayout(new GridLayout(1, 1));
		JScrollPane jsp = new JScrollPane(reportTable);
		tablePanel.add(jsp);

		grid.add(tablePanel);
		report.add(grid);
	}

	/*
	 * this methods generates the LOGIN window by AURELIEN
	 */

	void addLoginScreen() {

		DLabel login = new DLabel("Log in", white, txtH1);

		DLabel email = new DLabel("Email", white, txtH3);
		emailTxt = new DTextField(40, darkGray, txtH3);

		DLabel password = new DLabel("Password", white, txtH3);
		passwordTxt = new DTextField(40, darkGray, txtH3);

		logIn = new DButton("Log in", white, txtH2, darkerGray);
		logIn.addActionListener(this);

		loginScreen = new DPanel(darkGray);
		loginScreen.setBorder(BasicBorders.getButtonBorder());

		loginScreen.setBounds(170, 80, 550, 400);
		loginScreen.setLayout(null);

		login.setBounds(50, 40, 600, 40);
		loginScreen.add(login);

		email.setBounds(50, 120, 260, 40);
		loginScreen.add(email);

		emailTxt.setBounds(50, 160, 400, 40);
		loginScreen.add(emailTxt);

		password.setBounds(50, 220, 600, 40);
		loginScreen.add(password);

		passwordTxt.setBounds(50, 260, 400, 40);
		loginScreen.add(passwordTxt);

		logIn.setBounds(150, 340, 250, 40);
		loginScreen.add(logIn);

		home.add(loginScreen);
		testPanel.setBounds(150, 340, 500, 400);
		home.add(testPanel);

		/*------------------------------*/
		/*
		 * code for testing the picture Uploader
		 */
		// try {
		// testPanel.setIcon(pic(DatabaseConnector.uploadPic()));
		// } catch (IOException e) {
		// //if file not found
		// System.out.println("File not found " + e);
		// }
	}

	/*
	 * this methods creates the picture by DANIELLE, redesigned by AURELIEN
	 */

	public ImageIcon pic(File file) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(file);
		ImageIcon imageIcon = new ImageIcon(bufferedImage);
		return imageIcon;
	}

	/*
	 * this methods generates the ACCOUNT MANAGEMENT window by SALLY (not merged
	 * yet)
	 */

	void addHome() {

		homePanel = new DPanel(darkGray);
		homePanel.setBounds(100, 0, 680, 600);
		homePanel.setLayout(null);
		homePanel.setVisible(false);

		home.add(homePanel);
	}

	/*
	 * actionListeners by ALL
	 */

	public void actionPerformed(ActionEvent ae) {
		// from the create report window
		if (ae.getSource() == submit) {
			try {
			DatabaseConnector dc = new DatabaseConnector();
			
			String f = from.getSelectedItem().toString();
			String t = to.getSelectedItem().toString();
			String ks = startKmTxt.getText();
			String ke = endKmTxt.getText();
			String reason = reasonTripTxt.getText();
			String username = "000";
			String name = "000";
			String c = car.getSelectedItem().toString();
			String d = dateLabel.getText();
			
			if(f == null || t == null || ks == null || ke == null || reason == null || username == null || name == null || c == null|| d == null ){
				new JOptionPane("missing value");
			}else{
				dc.insertTripData(ks, ke, f, t, reason, username, name, c, d);
			}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}

		// from the login screen
		if (ae.getSource() == logIn) {
			loginScreen.setVisible(false);
			tabPane.setEnabled(true);
			homePanel.setVisible(true);
			System.out.println(emailTxt.getText());
		
		}

		// from the filter screen
		if (ae.getSource() == filterButton) {
			grid.setVisible(false);
			filterPane.setVisible(true);
		}

		// from the grid view screen
		if (ae.getSource() == searchButton) {
			grid.setVisible(true);
			filterPane.setVisible(false);
		}
		if (ae.getSource() == editButton) {
			reportTable.setEnabled(true);
		}
		if (ae.getSource() == saveChangesButton) {
			int[] rowChanged = reportTable.getSelectedRows();
			int[] columnChanged = reportTable.getSelectedColumns();

			for (int i = 0; i < rowChanged.length; i++) {
				for (int j = 0; j < columnChanged.length; j++) {
					System.out.println(rowChanged[i]);
					System.out.println(columnChanged[j]);
					System.out.println(reportTable.getValueAt(rowChanged[i],
							columnChanged[j]));
				}
			}
		}
	}
}

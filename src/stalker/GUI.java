package stalker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.table.DefaultTableModel;

import org.sourceforge.jcalendarbutton.JCalendarButton;

import gui.elements.*;

public class GUI extends JFrame implements ActionListener {
	
	/* the looks for Fonts and the Colors used throughout the project
	 * are defined here*/

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

	JCalendarButton date = new JCalendarButton("yyyy/mm/dd", new Date(113, 11,
			4));

	DButton submit;
	DButton logIn;
	DButton gridButton;
	DButton filter;
	DPanel home;
	DPanel create;
	DPanel report;
	DPanel grid;
	DPanel loginScreen;
	DPanel homePanel;
	DPanel filterPane;
	JTabbedPane tabPane;

	public GUI() {
		
		// creates a calenderDropdown
		date.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				if (evt.getNewValue() instanceof Date)
					dateLabel.setText((((Date) evt.getNewValue()).toString()));
			}
		});

		addStuff();
		setTitle("STALKER");
		setSize(900, 650);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/* this methods adds the ELEMENTS and the TABS to the JFrame
	 * by MAHSA */

	void addStuff() {

		DPanel mainPanel = new DPanel(gray);
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
			e.printStackTrace();
		}

		tabPane = new JTabbedPane();
		tabPane.setFont(txtH2);
		tabPane.addTab("Home", home);
		tabPane.addTab("Report", report);
		tabPane.addTab("Create", create);
		tabPane.setEnabled(false);
		add(tabPane);
	}
	
	/* this methods generates the CREATE window 
	 * by MAHSA */

	void addCreate() {

		DLabel createLog = new DLabel("Create a new travel log", white, txtH1);

		DComboBox from = new DComboBox(new String[] { "From" }, darkGray,
				txtH3, white);
		DComboBox to = new DComboBox(new String[] { "To" }, darkGray, txtH3,
				white);
		DComboBox car = new DComboBox(new String[] { "Car" }, darkGray, txtH3,
				white);

		DLabel startKm = new DLabel("Start kilometer:", white, txtH3);
		DTextField startKmTxt = new DTextField("", 20, darkGray, txtH3);

		DLabel endKm = new DLabel("End kilometer:", white, txtH3);
		DTextField endKmTxt = new DTextField("", 20, darkGray, txtH3);

		submit = new DButton("Submit", white, txtH2, darkerGray);
		submit.addActionListener(this);

		DPanel createPanel = new DPanel(darkGray);

		createPanel.setBounds(100, 0, 680, 600);
		createPanel.setLayout(null);

		createLog.setBounds(50, 40, 600, 40);
		from.setBounds(50, 120, 100, 40);
		to.setBounds(210, 120, 100, 40);
		dateLabel.setBounds(380, 160, 300, 40);
		date.setBounds(600, 120, 40, 40);
		car.setBounds(50, 200, 260, 40);
		startKm.setBounds(50, 280, 260, 40);
		endKm.setBounds(380, 280, 260, 40);
		startKmTxt.setBounds(50, 320, 260, 40);
		endKmTxt.setBounds(380, 320, 260, 40);
		submit.setBounds(280, 420, 120, 40);

		createPanel.add(createLog);
		createPanel.add(from);
		createPanel.add(to);
		createPanel.add(dateLabel);
		createPanel.add(date);
		createPanel.add(car);
		createPanel.add(startKm);
		createPanel.add(startKmTxt);
		createPanel.add(endKm);
		createPanel.add(endKmTxt);
		createPanel.add(submit);

		create.add(createPanel);
	}
	
	/* this methods generates the FILTERS for the REPORT window 
	 * by MINA */

	void addReportFilter() {

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

		String[] comboBox_1S = { "Km", "Mil", };
		DComboBox comboBox_1 = new DComboBox(comboBox_1S, darkGray, txtH3,
				white);
		comboBox_1.setBounds(369, 82, 77, 28);
		filterPane.add(comboBox_1);

		String[] comboBox_2S = { "To" };
		DComboBox comboBox_2 = new DComboBox(comboBox_2S, darkGray, txtH3,
				white);
		comboBox_2.setBounds(221, 122, 122, 28);
		filterPane.add(comboBox_2);

		String[] comboBox_3S = { "Car" };
		DComboBox comboBox_3 = new DComboBox(comboBox_3S, darkGray, txtH3,
				white);
		comboBox_3.setBounds(369, 122, 77, 28);
		filterPane.add(comboBox_3);

		String[] comboBox_4S = { "Purpose of the trip" };
		DComboBox comboBox_4 = new DComboBox(comboBox_4S, darkGray, txtH3,
				white);
		comboBox_4.setBounds(221, 180, 225, 28);
		filterPane.add(comboBox_4);

		String[] comboBox_5S = { "Extra costs" };
		DComboBox comboBox_5 = new DComboBox(comboBox_5S, darkGray, txtH3,
				white);
		comboBox_5.setBounds(221, 230, 225, 28);
		filterPane.add(comboBox_5);

		filter = new DButton("Log in", white, txtH2, darkerGray);
		filter.addActionListener(this);
		filter.setBounds(221, 270, 225, 28);
		filterPane.add(filter);

		report.add(filterPane);

	}
	
	/* this methods generates the TABLE for the REPORT window 
	 * by AURELIEN */

	void addReportGrid() throws SQLException {

		DatabaseConnector dc = new DatabaseConnector();

		grid = new DPanel(darkGray);
		grid.setLayout(null);
		grid.setBounds(100, 0, 680, 600);
		grid.setVisible(false);

		gridButton = new DButton("Filter", white, txtH2, darkerGray);
		gridButton.addActionListener(this);
		gridButton.setBounds(221, 500, 225, 28);
		grid.add(gridButton);

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
	
	/* this methods generates the LOGIN window 
	 * by AURELIEN */

	void addLoginScreen() {

		DLabel login = new DLabel("Log in", white, txtH1);

		DLabel email = new DLabel("Email", white, txtH3);
		DTextField emailTxt = new DTextField(40, darkGray, txtH3);

		DLabel password = new DLabel("Password", white, txtH3);
		DTextField passwordTxt = new DTextField(40, darkGray, txtH3);

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
	}
	
	/* this methods generates the ACCOUNT MANAGEMENT window 
	 * by SALLY (not merged yet) */

	void addHome() {

		homePanel = new DPanel(darkGray);
		homePanel.setBounds(100, 0, 680, 600);
		homePanel.setLayout(null);
		homePanel.setVisible(false);

		home.add(homePanel);
	}
	
	/* actionListeners
	 * by ALL */

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == submit) {
			System.out.println("ok");
		}
		if (ae.getSource() == logIn) {
			loginScreen.setVisible(false);
			tabPane.setEnabled(true);
			homePanel.setVisible(true);
		}
		if (ae.getSource() == filter) {
			grid.setVisible(true);
			filterPane.setVisible(false);
		}
		if (ae.getSource() == gridButton) {
			grid.setVisible(false);
			filterPane.setVisible(true);
		}
	}
}

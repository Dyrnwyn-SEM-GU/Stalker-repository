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
import javax.swing.JFileChooser;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.table.DefaultTableModel;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.jbundle.thin.base.screen.jcalendarbutton.JCalendarButton;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

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

	DLabel dateLabel1 = new DLabel("", white, txtH3);
	DLabel dateLabel2 = new DLabel("", white, txtH3);
	DLabel dateLabel3 = new DLabel("", white, txtH3);
	
	DButton exportButton, editButton, saveChangesButton, submit, logIn,
	filterButton, searchButton, forgotPassword,
	signUp,creatNewAccount,typeOfCar,SaveCar,cancelCar;

	DTextField textKm, emailTxt, passwordTxt, startKmTxt, endKmTxt, reasonTripTxt,
	firstName, yourEmail, reEnterPassword, Password,carBrandTxt,carTypeTxt,ConsumptionTxt,registeryNumber;

	DPanel home, create, report, grid, loginScreen, homePanel, filterPane,
			tablePanel, signUpPanel;
	DComboBox from, to, car, fromCity, toCity, extraCosts, purpose, user, car2;
	JScrollPane jsp;

	JTabbedPane tabPane;
	JTable reportTable;
	DefaultTableModel model;
	
	String username;
	
	// for testing only
	JLabel testPanel = new JLabel();

	/* Updated calendar code by Khashayar */
	JCalendarButton date1 = new JCalendarButton(); // "YYYY-MM-DD", new Date(113,
	// 11, 4));
	JCalendarButton date2 = new JCalendarButton(); // "YYYY-MM-DD", new Date(113,
	// 11, 4));
	JCalendarButton date3 = new JCalendarButton(); // "YYYY-MM-DD", new Date(113,
	// 11, 4));

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public GUI() throws SQLException {
		
		// creates a calenderDropdown
		date1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				if (evt.getNewValue() instanceof Date)
					dateLabel1.setText(dateFormat.format(((Date) (evt
							.getNewValue()))));
			}
		});
		date2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				if (evt.getNewValue() instanceof Date)
					dateLabel2.setText(dateFormat.format(((Date) (evt
							.getNewValue()))));
			}
		});
		date3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				if (evt.getNewValue() instanceof Date)
					dateLabel3.setText(dateFormat.format(((Date) (evt
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
			addReportTable();
		} catch (SQLException e) {
			System.out.println("problem with database connection or query, sorry");
		}

		tabPane = new JTabbedPane();
		tabPane.setFont(txtH2);
		tabPane.addTab("Home", home);
		tabPane.addTab("Report", report);
		tabPane.addTab("Create", create);
		tabPane.setEnabled(false);
		
//		added by mahsa *
		homePanel.setVisible(false);
		
		add(tabPane);
	}

	// add new codes for sign up by mahsa 2013.12.29*

	void addSignaUp(JFrame frame) {
		DLabel createLogSignUp = new DLabel("Create your new account", white,
				txtH1);
		
		// end of new codes by Mahsa

		firstName = new DTextField("Name", 20, darkerGray, txtH3);
		yourEmail = new DTextField("Your Email", 20, darkerGray, txtH3);
		reEnterPassword = new DTextField("Re-Enter your Password", 20, darkerGray, txtH3);
		Password = new DTextField("Password", 20, darkerGray, txtH3);
		DLabel termsCondition=new DLabel("<html>By clicking Sign Up, you agree to our Terms and condition.Please read terms and condition page carefully by click on term and condition</html>"
				, white, txtH4);
		DLabel TermsAndCondition=new DLabel("Terms and Condition",white,txtH4);
		signUp = new DButton("sign Up", white, txtH2, darkerGray);
		signUp.addActionListener(this);


		signUpPanel = new DPanel(darkerGray);

		signUpPanel.setBounds(100, 0, 680, 600);
		signUpPanel.setLayout(null);

		firstName.setBounds(10, 40, 300,40);
		yourEmail.setBounds(10, 100, 300, 40);
		reEnterPassword.setBounds(10, 160, 300, 40);
		Password.setBounds(10, 220, 300, 40);
		termsCondition.setBounds(10,280 , 500, 40);
		TermsAndCondition.setBounds(10, 340, 500, 40);
		signUp.setBounds(10, 420, 150, 40);


		signUpPanel.add(firstName);
		signUpPanel.add(yourEmail);
		signUpPanel.add(reEnterPassword);
		signUpPanel.add(Password);
		signUpPanel.add(termsCondition);
		signUpPanel.add(TermsAndCondition);
		signUpPanel.add(signUp);

		
		frame.add(signUpPanel);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setBounds(350, 108, 670, 570);
	}
	
	/* this methods generates the CREATE window by MAHSA
	 * Added text labels and databaseconnection to fetch 
	 * information for For, To and Car dropdowns AURE, Jani
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
		car = new DComboBox(dc.querieCar("RegistryNumber", "Car", username), darkGray, txtH3,
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
		fromLabel.setBounds(50, 80, 160, 40);
		from.setBounds(50, 120, 160, 40);
		toLabel.setBounds(230, 80, 160, 40);
		to.setBounds(230, 120, 160, 40);
		dateLabel1.setBounds(400, 160, 300, 40);
		date1.setBounds(590, 120, 40, 40);
		carLabel.setBounds(50, 190, 260, 40);
		car.setBounds(50, 230, 260, 40);
		reasonOfTrip.setBounds(380, 190, 260, 40);
		reasonTripTxt.setBounds(380, 230, 260, 40);
		startKm.setBounds(50, 310, 260, 40);
		endKm.setBounds(380, 310, 260, 40);
		startKmTxt.setBounds(50, 350, 260, 40);
		endKmTxt.setBounds(380, 350, 260, 40);
		submit.setBounds(280, 450, 120, 40);

		createPanel.add(createLog);
		createPanel.add(fromLabel);
		createPanel.add(from);
		createPanel.add(toLabel);
		createPanel.add(to);
		createPanel.add(dateLabel1);
		createPanel.add(date1);
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

	/*this methods generates the FILTERS for the REPORT window by MINA
	 *added calenders and actionlisteners AURE*/

	void addReportFilter() throws SQLException {

		DatabaseConnector dc = new DatabaseConnector();

		filterPane = new DPanel(darkGray);
		filterPane.setLayout(null);
		filterPane.setBounds(100, 0, 680, 600);
		
		
		filterPane.add(date2);
		date2.setBounds(260, 127, 40, 40);

		DLabel lblFromDate = new DLabel("From date:", white, txtH3);
		lblFromDate.setBounds(110, 90, 160, 22);
		filterPane.add(lblFromDate);
		
		filterPane.add(date3);
		date3.setBounds(490, 127, 40, 40);

		DLabel lblToDate = new DLabel("To date:", white, txtH3);
		lblToDate.setBounds(369, 90, 94, 22);
		filterPane.add(lblToDate);

		
		/*
		 * 
		 * This code will be included in future updates when additional reports
		 * and filtering options are developed. // Jani
		 */
		//
		// DLabel userLabel = new DLabel("User:", white, txtH3);
		// userLabel.setBounds(6, 10, 122, 28);
		// filterPane.add(userLabel);
		//
		// user = new DComboBox(dc.getColumn("Username", "User"), darkGray,
		// txtH3,
		// white);
		// user.setBounds(6, 40, 122, 28);
		// filterPane.add(user);
		//
		// fromCity = new DComboBox(dc.getColumn("City", "Locations"), darkGray,
		// txtH3,
		// white);
		// fromCity.setBounds(221, 182, 122, 28);
		// filterPane.add(fromCity);
		//
		// toCity = new DComboBox(dc.getColumn("City", "Locations"), darkGray,
		// txtH3,
		// white);
		// toCity.setBounds(221, 222, 122, 28);
		// filterPane.add(toCity);
		
		dateLabel2.setBounds(380, 220, 300, 40);
		dateLabel3.setBounds(380, 220, 300, 40);

		/* This part changed by mahsa combobox to textfield 
		 * 
		 * Connection to database class and methods by Aurelien
		 * 	
		 * 
		 * 
		 * */

		/*
		 * This code will be included in future updates when additional reports
		 * and filtering options are developed. // Jani
		 */

		// DTextField textKm = new DTextField("KM", 20, darkerGray, txtH3);
		// textKm.setBounds(369, 182, 77, 28);
		// filterPane.add(textKm);
		//
		// car2 = new DComboBox(dc.querieCar("RegistryNumber", "Car", username),
		// darkGray,
		// txtH3,
		// white);
		// car2.setBounds(369, 222, 77, 28);
		// filterPane.add(car2);
		//
		// purpose = new DComboBox(dc.getColumn("ReasonOfTrip", "TripData"),
		// darkGray, txtH3,
		// white);
		// purpose.setBounds(221, 280, 225, 28);
		// filterPane.add(purpose);
		//
		// extraCosts = new DComboBox(dc.getColumn("TypeOfCost", "ExtraCosts"),
		// darkGray, txtH3,
		// white);
		// extraCosts.setBounds(221, 330, 225, 28);
		// filterPane.add(extraCosts);

		searchButton = new DButton("Search", white, txtH2, darkerGray);
		searchButton.addActionListener(this);
		searchButton.setBounds(221, 370, 225, 28);
		filterPane.add(searchButton);

		report.add(filterPane);

	}

	/*
	 * this methods generates the TABLE for the REPORT window by AURELIEN
	 */

	void addReportTable() throws SQLException {

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

		tablePanel = new DPanel(gray);

		model = new DefaultTableModel();
		JTable reportTable = new JTable();
		reportTable.setFont(txtH4);
		reportTable.setForeground(darkGray);

		// the model is generated with the reportTable method
		model = dc.reportTable(model, username);
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
		
		// new code add by mahsa create account which i called it sign up button

		creatNewAccount = new DButton("Sign Up", white, txtH2, darkGray);
		creatNewAccount.addActionListener(this);
				
		// End of new code by Mahsa, merged by Jani

		forgotPassword = new DButton("Forgot password?", white, txtH4, darkerGray);
		forgotPassword.addActionListener(this);
		
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
		
		// /mahsa add sign up button
		creatNewAccount.setBounds(50, 340, 130, 40);
		loginScreen.add(creatNewAccount);
				
		// end of added code by Mahsa, merged by Jani

		logIn.setBounds(150, 340, 250, 40);
		loginScreen.add(logIn);

		forgotPassword.setBounds(150, 300, 250, 40);
		loginScreen.add(forgotPassword);
		
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

	/* method for checking if password and username matches the ones in DB
	 * by Gabriele, Aure and help of Thor Salehi
	 */
	 public void sendPassword(String username) throws AddressException, javax.mail.MessagingException  {
		  // Recipient's email ID needs to be mentioned.
	      String to = ""+username+"";
	      String from = "dyrnwynSEM@gmail.com";
	      String host = "localhost";
	      String password = "stalker123";
	      
	      Properties properties = System.getProperties();
	      properties.setProperty("mail.smtps.host", "smtp.gmail.com");
	        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
	        properties.setProperty("mail.smtp.port", "465");
	        properties.setProperty("mail.smtp.socketFactory.port", "465");
	        properties.setProperty("mail.smtps.auth", "true");
	        
	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(RecipientType.TO,
	                                  new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("This is the Subject Line!");

	         // Now set the actual message
	         message.setText("This is actual message");

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }
	        
	
	
	/*
	 * logIn method written by Gabriele and Thor, merged by Jani
	 * 
	 */
	

	public void logInM (String username, String pass) throws SQLException {
		DatabaseConnector dc = new DatabaseConnector();
	try {
		if (username.equals("") || pass.equals("") ) {	
		} else if (dc.querieCredentials("Password", "Username", username).equals(pass)) {
            loginScreen.setVisible(false);
			tabPane.setEnabled(true);
			homePanel.setVisible(true);
			
        } 
        else {      
        	JOptionPane.showMessageDialog(home, "E-mail or password you inserted was incorrect.","Error",
				    JOptionPane.ERROR_MESSAGE);
        	}	 
	} catch (SQLException e) {
		e.printStackTrace();
		}  
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
				String name = "000";
				String c = car.getSelectedItem().toString();
				String d = dateLabel1.getText();

				if (f == "" || t == "" || ks == "" || ke == "" || reason == ""
						|| username == "" || name == "" || c == "" || d == "") {
					new JOptionPane("missing value");
				} else {
				dc.insertTripData(ks, ke, f, t, reason, username, name, c, d);
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		
		if (ae.getSource() == exportButton) {
			
			JFileChooser chooser = new JFileChooser();
			chooser.setSelectedFile(new File("export.csv"));
			chooser.showSaveDialog(null);
			String pathAndName = chooser.getSelectedFile().getAbsolutePath();
			
			

			try {
				DatabaseConnector dc = new DatabaseConnector();
				dc.exportCSV(pathAndName, username);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
		 
		}

		// from the login screen
		if (ae.getSource() == forgotPassword) {
			if(!emailTxt.getText().equals("")) {
			try {
				sendPassword(emailTxt.getText());
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (javax.mail.MessagingException e) {
				e.printStackTrace();
			}
			}
				}
		
		
		if (ae.getSource() == logIn) {
			try {
				DatabaseConnector dc = new DatabaseConnector();
				logInM(emailTxt.getText(), passwordTxt.getText());
				username = emailTxt.getText();
				/*------------------------------------------------------------------*/
				/*
				 * code addition to update car dropdown depending on user By
				 * Auré
				 */
				car.removeAllItems();
				for (String s : dc.querieCar("RegistryNumber", "Car", username)) {
					car.addItem(s);
				}
				/*------------------------------------------------------------------*/
				addCreate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
	
		// mahsa sign up button 2013.12.30

		if (ae.getSource() == creatNewAccount) {
			JFrame signUpFrame = new JFrame();
			addSignaUp(signUpFrame);
			signUpFrame.getContentPane().setBackground(darkerGray);
			signUpFrame.setVisible(true);
			homePanel.setVisible(false);
			signUpFrame.setBounds(330, 80, 700, 600);
		}

		// from the filter screen
		if (ae.getSource() == filterButton) {
			grid.setVisible(false);
			filterPane.setVisible(true);

			/*
			 * This code added by Auré to update the report page after new data
			 * has been added without having to restart the program.
			 * 
			 * Modified 2014-01-06 to only display current logged in users data.
			 * 
			 * Merged by Jani
			 */
			//------------------------------------------------------------------
			// I think this is the code you want Jani, it updates the table		
				try {
					DatabaseConnector dc = new DatabaseConnector();
				// the model is generated with the reportTable method
				// String username = user.getSelectedItem().toString();

				String d1 = dateLabel2.getText();
				String d2 = dateLabel3.getText();
				model = dc.reportTable(model, username);
				
				
				// String f = fromCity.getSelectedItem().toString();
				// String t = toCity.getSelectedItem().toString();
				// String reason = purpose.getSelectedItem().toString();
				String name = "000";
				// String c = car.getSelectedItem().toString();


					model.fireTableDataChanged();
				} catch (SQLException e) {
					System.out.println(e);
				}
			//------------------------------------------------------------------
			}
			
			


		// from the grid view screen
		if (ae.getSource() == searchButton) {

			// Modified by Jani 2014-01-06 to only display data for the logged
			// in user.
			DatabaseConnector dc;
			try {
				dc = new DatabaseConnector();
				String d1 = dateLabel2.getText();
				String d2 = dateLabel3.getText();
				System.out.println(dateLabel2.getText());
				System.out.println(dateLabel3.getText());

				model = dc.reportTable(model, username);
			grid.setVisible(true);
			filterPane.setVisible(false);
			} catch (SQLException e) {
				// e.printStackTrace();
				System.out.println(e);
			}
		}

		// --------- end of modification by Jani -------------

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

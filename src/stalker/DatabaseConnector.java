package stalker;

import gui.GUI;
import gui.HomeScreen;
import gui.LoginScreen;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;

/* the connections to the Database happen here, by Aure */

public class DatabaseConnector {

	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	File selectedFile;

	public DatabaseConnector() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost/StalkerDB",
				"Dyrnwyn", "Dyrnwyn!");
		stmt = con.createStatement();
	}

	/* by Gabriele */
	public String queryCredentials(String row1, String row2, String value)
			throws SQLException {

		rs = stmt.executeQuery("SELECT " + row1 + " FROM User WHERE " + row2
				+ " = '" + value + "';");
		String result = "";

		if (rs.next()) {
			result = rs.getString(1);
		} else {
			System.out.println("result set is empty");
		}
		return result;
	}

	/* by Danielle */
	public DefaultTableModel reportTable(DefaultTableModel model, String fromDate, String toDate) throws SQLException {

		String SQL = "Select `StartingKm`, `EndingKm`, `From`, `To`, `ReasonOfTrip`, `RegistryNumber`, `Date` FROM TripData WHERE Username = '" 
		+ GUI.username + "' AND Date BETWEEN '" + fromDate + "' AND '" + toDate +"';";

		rs = stmt.executeQuery(SQL);
		ResultSetMetaData rsm = (ResultSetMetaData) rs.getMetaData();
		int nrCols = rsm.getColumnCount();
		ArrayList<Object[]> t = new ArrayList<Object[]>();
		while (rs.next()) {
			Object[] temp = new Object[7];
//			temp[0] = rs.getString("idTripData");
			temp[0] = rs.getString("StartingKm");
			temp[1] = rs.getString("EndingKm");
			temp[2] = rs.getString("From");
			temp[3] = rs.getString("To");
			temp[4] = rs.getString("ReasonOfTrip");
//			temp[6] = rs.getString("Username");
//			temp[7] = rs.getString("Name");
			temp[5] = rs.getString("RegistryNumber");
//			temp[9] = rs.getString("Timestamp");
			temp[6] = rs.getString("Date");
			t.add(temp);
		}
		Object[][] data = new Object[t.size()][nrCols];
		for (int i = 0; i < t.size(); i++) {
			for (int j = 0; j < 7; j++) {
				data[i][j] = t.get(i)[j];
			}
		}
		Object[] columnNames = {"Start Km","End Km","From","To","Reason of trip","Registration number","Date"};

		model.setDataVector(data, columnNames);
		return model;
	}

	/*
	 * populates the car drop-down menus, only display the cars registered for
	 * the logged-in user, by Jani
	 */

	public String[] querieCar(String column, String table, String username)
			throws SQLException {
		ArrayList<String> al = new ArrayList<String>();
		rs = stmt.executeQuery("SELECT DISTINCT " + column + " FROM " + table
				+ " WHERE Username = '" + username + "' ORDER BY " + column
				+ " ASC;");
		while (rs.next()) {
			al.add(rs.getString(1));
		}
		String[] result = al.toArray(new String[al.size()]);
		return result;
	}

	/* methods to insert user data in the database, by JANI */

	public void insertTripData(String startKm, String endKm, String from,
			String to, String tripReason,
			String car, String date) throws SQLException {

		String name = "";
		
		//rs = stmt.executeQuery("SELECT Name FROM User WHERE Username = " + GUI.username + ";");
		//name = rs.getString(1);
		System.out.println(name);
		stmt.executeUpdate("INSERT INTO TripData"
				+ "(`StartingKm`, `EndingKm`, `From`, `To`, `ReasonOfTrip`, `Username`, `Name`, `RegistryNumber`, `Date`)"
				+ " VALUES " + "('" + startKm + "','" + endKm + "','" + from
				+ "','" + to + "','" + tripReason + "','" + GUI.username + "','"
				+ "none" + "','" + car + "','" + date + "')");
	}

	public void insertExtraCost(String typeOfCost, String cost, String file,
			String date, String idTripData) throws SQLException {

		stmt.executeUpdate("INSERT INTO ExtraCosts"
				+ "(`TypeOfCost`, `Cost`, `File`, `Date`)" + " VALUES " + "('"
				+ typeOfCost + "','" + cost + "','" + file + "','" + date
				+ "','" + idTripData + "')");
	}

	public void insertNewUser(String username, String password, String name)
			throws SQLException {

		stmt.executeUpdate("INSERT INTO User" + "(`Username`, `Password`, `Name`)"
				+ " VALUES " + "('" + username + "','" + password + "','" + name +"')");
	}

	public void insertNewCar(String carBrand, String registryNumber, String carType, String consumption, String username) throws SQLException {
		stmt.executeUpdate("INSERT INTO Car"
				+ "(`Brand`, `RegistryNumber`, `Type`, `Consumption`, `Username`)" + " VALUES " 
				+ "('" + carBrand + "','" + registryNumber + "','" + carType + "','" + consumption + "','" + username + "')");
	}

	/*
	 * method to fetch the information for the dropdown menu, by AURE, modified
	 * by Jani
	 */

	public String[] getColumn(String column, String table) throws SQLException {
		ArrayList<String> al = new ArrayList<String>();

		rs = stmt.executeQuery("SELECT DISTINCT " + column + " FROM " + table
				+ " ORDER BY " + column + " ASC;");

		while (rs.next()) {
			al.add(rs.getString(1));
		}
		String[] result = al.toArray(new String[al.size()]);
		return result;
	}

	/*
	 * choose and insert pictures or files to the database by
	 * DANIELLE, redesigned by AURE
	 */

	public void uploadPic() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.showOpenDialog(null);
		selectedFile = fileChooser.getSelectedFile();
	}

	public void insertImage() throws SQLException, IOException {
		String insertFile = "INSERT INTO ExtraCosts(File) VALUES (?)";
		con.setAutoCommit(false);
		try {
			FileInputStream fileInputStream = new FileInputStream(selectedFile);
			PreparedStatement pstmt = con.prepareStatement(insertFile);
			pstmt.setBinaryStream(1, fileInputStream,
					(int) selectedFile.length());
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			System.out.print(e);
		}
	}

	 /* shows the uploaded picture, by Danielle */
	public void showPicture() throws IOException {
		final BufferedImage bufferedImage = ImageIO.read(selectedFile);

		JLabel label = new JLabel() {
			protected void paintComponent(Graphics g) {
				Graphics graph = g.create();
				graph.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(),
						null);
				graph.dispose();
			}
			public Dimension getPreferredSize() {
				return new Dimension(bufferedImage.getWidth(),
						bufferedImage.getHeight());
			}
		};
		
		JFrame frame = new JFrame("Your selected picture: ");
		frame.add(label);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/* methods to export data from the database, by GABRIELE */
	public String buildCSV(String table, String fromDate, String toDate) throws SQLException {

		ArrayList<String> row = new ArrayList<String>();
		rs = stmt.executeQuery("SELECT * from " + table + " WHERE Username = '"
				+ GUI.username + "' AND Date BETWEEN '" + fromDate + "' AND '" + toDate +"';");
		java.sql.ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();

		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				String columnValue = rs.getString(i);
				row.add(columnValue);
			}
			row.add("\n");
		}
		return stringify(row);
	}
	
	public void exportCSV(String filepathAndName, String username, String fromDate, String toDate)
			throws SQLException, IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				filepathAndName)));
		bw.write(buildCSV("TripData", fromDate, toDate));
		bw.close();
	}
	
	/* update user password, by Sally and Jani */
	public void insertPassword(String password, String username)
			throws SQLException {

		stmt.executeUpdate("UPDATE User SET Password = '" + password
				+ "' WHERE Username = '" + username + "';");
	}
	
	/* log in method, by Gabriele, helped by Thor, merged by Jani */
	public void logInM(String username, String pass) throws SQLException {

		if (username.equals("") || pass.equals("")) {
		} else if (queryCredentials("Password", "Username", username).equals(
				pass)) {
			LoginScreen.loginScreenPanel.setVisible(false);
			GUI.tabPane.setEnabled(true);
			HomeScreen.homePanel.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(GUI.home,
					"E-mail or password you inserted was incorrect.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/* transforms an ArrayList to a string, by Aure */
	public String stringify(ArrayList<String> ar) {
		String listString = "";
		for (String s : ar) {
			if (s != "\n") {
				listString += s + ", ";
			} else {
				listString += s;
			}
		}
		return listString;
	}
}

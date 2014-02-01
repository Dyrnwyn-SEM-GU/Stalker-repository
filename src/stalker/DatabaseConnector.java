package stalker;

import gui.GUI;
import gui.HomeScreen;
import gui.LoginScreen;
import gui.ReportScreen;

import java.io.*;
import java.sql.*;
import java.util.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.mysql.jdbc.ResultSetMetaData;

/* the connections to the Database happen here, by Aure */

public class DatabaseConnector {

	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	public DatabaseConnector() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost/StalkerDB",
				"Dyrnwyn", "Dyrnwyn!");
		stmt = con.createStatement();
	}

	/* by Danielle */
	public DefaultTableModel reportTable(DefaultTableModel model)
			throws SQLException {

		String SQL = "Select `StartingKm`, `EndingKm`, `From`, `To`, `ReasonOfTrip`, `RegistryNumber`, `Date` from TripData WHERE Username ='"
				+ GUI.username + "';";

		rs = stmt.executeQuery(SQL);
		ResultSetMetaData rsm = (ResultSetMetaData) rs.getMetaData();
		int nrCols = rsm.getColumnCount();
		ArrayList<Object[]> t = new ArrayList<Object[]>();
		while (rs.next()) {
			Object[] temp = new Object[7];
			temp[0] = rs.getString("StartingKm");
			temp[1] = rs.getString("EndingKm");
			temp[2] = rs.getString("From");
			temp[3] = rs.getString("To");
			temp[4] = rs.getString("ReasonOfTrip");
			temp[5] = rs.getString("RegistryNumber");
			temp[6] = rs.getString("Date");
			t.add(temp);
		}
		Object[][] data = new Object[t.size()][nrCols];
		for (int i = 0; i < t.size(); i++) {
			for (int j = 0; j < 7; j++) {
				data[i][j] = t.get(i)[j];
			}
		}
		Object[] columnNames = { "Start Km", "End Km", "From", "To",
				"Reason of trip", "Registration number", "Date" };

		model.setDataVector(data, columnNames);
		return model;
	}

	/* by Danielle, modified by Jani */
	public DefaultTableModel reportTable(DefaultTableModel model,
			String fromDate, String toDate) throws SQLException {

		String SQL = "Select `StartingKm`, `EndingKm`, `From`, `To`, `ReasonOfTrip`, `RegistryNumber`, `Date` from TripData WHERE Username ='"
				+ GUI.username
				+ "' AND Date BETWEEN '"
				+ fromDate
				+ "' AND '"
				+ toDate + "';";

		rs = stmt.executeQuery(SQL);
		ResultSetMetaData rsm = (ResultSetMetaData) rs.getMetaData();
		int nrCols = rsm.getColumnCount();
		ArrayList<Object[]> t = new ArrayList<Object[]>();
		while (rs.next()) {
			Object[] temp = new Object[7];
			temp[0] = rs.getString("StartingKm");
			temp[1] = rs.getString("EndingKm");
			temp[2] = rs.getString("From");
			temp[3] = rs.getString("To");
			temp[4] = rs.getString("ReasonOfTrip");
			temp[5] = rs.getString("RegistryNumber");
			temp[6] = rs.getString("Date");
			t.add(temp);
		}
		Object[][] data = new Object[t.size()][nrCols];
		for (int i = 0; i < t.size(); i++) {
			for (int j = 0; j < 7; j++) {
				data[i][j] = t.get(i)[j];
			}
		}
		Object[] columnNames = { "Start Km", "End Km", "From", "To",
				"Reason of trip", "Registration number", "Date" };

		model.setDataVector(data, columnNames);
		return model;
	}

	/* by Gabriele */
	public String queryCredentials(String row1, String row2, String value)
			throws SQLException {

		rs = stmt.executeQuery("SELECT " + row1 + " FROM User WHERE " + row2
				+ " = '" + value + "';");
		String result = "";
		if (rs.next()) {
			result = rs.getString(1);
		}
		return result;
	}

	/* query image by Danielle */
	public BufferedImage queryImage(String value) throws SQLException {

		rs = stmt.executeQuery("SELECT file FROM extracosts WHERE IDCosts =  "
				+ value + ";");
		try {
			byte[] imageByte;
			imageByte = rs.getBytes("file");
			InputStream in = new ByteArrayInputStream(imageByte);
			BufferedImage bufferedImage = ImageIO.read(in);
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * populates the car drop-down menus, only display the cars registered for
	 * the logged-in user, by Jani
	 */

	public String[] queryCar(String column, String table, String username)
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
			String to, String tripReason, String car, String date)
			throws SQLException {
		String name = "";
		System.out.println(name);
		stmt.executeUpdate("INSERT INTO TripData"
				+ "(`StartingKm`, `EndingKm`, `From`, `To`, `ReasonOfTrip`, `Username`, `Name`, `RegistryNumber`, `Date`)"
				+ " VALUES " + "('" + startKm + "','" + endKm + "','" + from
				+ "','" + to + "','" + tripReason + "','" + GUI.username
				+ "','" + "none" + "','" + car + "','" + date + "')");
	}

	public void insertExtraCost(String typeOfCost, String cost, String file,
			String date, String idTripData) throws SQLException {
		stmt.executeUpdate("INSERT INTO ExtraCosts"
				+ "(`TypeOfCost`, `Cost`, `File`, `Date`, `idTripData`)"
				+ " VALUES " + "('" + typeOfCost + "','" + cost + "','" + file
				+ "','" + date + "','" + idTripData + "')");
	}

	public void insertNewUser(String username, String password, String name)
			throws SQLException {
		stmt.executeUpdate("INSERT INTO User"
				+ "(`Username`, `Password`, `Name`)" + " VALUES " + "('"
				+ username + "','" + password + "','" + name + "')");
	}

	public void insertNewCar(String carBrand, String registryNumber,
			String carType, String consumption, String username)
			throws SQLException {
		stmt.executeUpdate("INSERT INTO Car"
				+ "(`Brand`, `RegistryNumber`, `Type`, `Consumption`, `Username`)"
				+ " VALUES " + "('" + carBrand + "','" + registryNumber + "','"
				+ carType + "','" + consumption + "','" + username + "')");
	}

	/* update user password, by Sally and Jani */
	public void insertPassword(String password, String username)
			throws SQLException {
		stmt.executeUpdate("UPDATE User SET Password = '" + password
				+ "' WHERE Username = '" + username + "';");
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

	/* methods to export data from the database, by GABRIELE */
	public String buildCSV(String table, String fromDate, String toDate)
			throws SQLException {

		ArrayList<String> row = new ArrayList<String>();
		rs = stmt.executeQuery("SELECT * from " + table + " WHERE Username = '"
				+ GUI.username + "' AND Date BETWEEN '" + fromDate + "' AND '"
				+ toDate + "';");
		java.sql.ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();

		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				String columnValue = rs.getString(i);
				row.add(columnValue);
			}
			row.add("\n");
		}
		return Main.stringify(row);
	}

	public String buildCSV(String table) throws SQLException {

		ArrayList<String> row = new ArrayList<String>();
		rs = stmt.executeQuery("SELECT * from " + table + " WHERE Username = '"
				+ GUI.username + "';");
		java.sql.ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();

		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				String columnValue = rs.getString(i);
				row.add(columnValue);
			}
			row.add("\n");
		}
		return Main.stringify(row);
	}

	public void exportCSV(String filepathAndName, String username)
			throws SQLException, IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				filepathAndName)));
		if (ReportScreen.dateLabel1.getText().equals("")
				|| ReportScreen.dateLabel2.getText().equals("")) {
			bw.write(buildCSV("TripData"));
		} else {
			bw.write(buildCSV("TripData", ReportScreen.dateLabel1.getText(),
					ReportScreen.dateLabel2.getText()));
		}
		bw.close();
	}

	/* log in method, by Gabriele, helped by Thor, merged by Jani */
	public void logInM(String username, String pass) throws SQLException {

		if (username.equals("") || pass.equals("")) {
			JOptionPane.showMessageDialog(GUI.home, "Missing Values", "Error",
					JOptionPane.ERROR_MESSAGE);
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

	/*
	 * get the latest idTripData, necessary to enter multiple extra costs, Sally
	 * and Aure
	 */
	public String idTripdata() throws SQLException {
		String idTripdata = "";
		rs = stmt.executeQuery("SELECT MAX(idTripData) FROM TripData");
		if (rs.next()) {
			idTripdata = rs.getString(1);
		} else {
			System.out.println("result set is empty");
		}
		return idTripdata;
	}
}

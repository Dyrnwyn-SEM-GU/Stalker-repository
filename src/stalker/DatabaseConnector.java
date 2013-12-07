package stalker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.jdbc.ResultSetMetaData;

public class DatabaseConnector {

	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	public DatabaseConnector() throws SQLException {

		con = DriverManager.getConnection("jdbc:mysql://localhost/StalkerDB",
				"Dyrnwyn", "Dyrnwyn!");
		System.out.println("Database connected");

		stmt = con.createStatement();

	}
	
	/* this method creates a table-model (as used by JTable) for a specific table 
	 * at the moment only from the user table
	 * by DANIELLE	|| supervisor helped streamline it, but it was working before as well */

	public DefaultTableModel reportTable(DefaultTableModel model)
			throws SQLException {
		
		/* could be combined with the specificQuerie method to generate a
		 * specific SQL string  
		 * 
		 */
		String SQL = "Select * from TripData;";

		rs = stmt.executeQuery(SQL);
		ResultSetMetaData rsm = (ResultSetMetaData) rs.getMetaData();
		int nrCols = rsm.getColumnCount();
		ArrayList<Object[]> t = new ArrayList<Object[]>();
		while (rs.next()) {
			Object[] temp = new Object[11];
			temp[0] = rs.getString("idTripData");
			temp[1] = rs.getString("StartingKm");
			temp[2] = rs.getString("EndingKm");
			temp[3] = rs.getString("From");
			temp[4] = rs.getString("To");
			temp[5] = rs.getString("ReasonOfTrip");
			temp[6] = rs.getString("Username");
			temp[7] = rs.getString("Name");
			temp[8] = rs.getString("RegistryNumber");
			temp[9] = rs.getString("Timestamp");
			temp[10] = rs.getString("Date");
			t.add(temp);
			
			
			
			
//			Object[] temp = new Object[4];
//			temp[0] = rs.getString("Username");
//			temp[1] = rs.getString("FirstName");
//			temp[2] = rs.getString("LastName");
//			temp[3] = rs.getString("Password");
//			t.add(temp);
		}
		
		Object[][] data = new Object[t.size()][nrCols];
		for (int i = 0; i < t.size(); i++) {
			for (int j = 0; j < 10; j++) {
				data[i][j] = t.get(i)[j];
			}
		}

		
		Object[] columnNames = { "idTripData", "StartingKm", "EndingKm",
		"From", "To", "ReasonOfTrip", "Username",
		"Name", "RegistryNumber", "Timestamp", "Date" };
//		Object[] columnNames = { "Username", "FirstName", "LastName",
//				"Password" };

		model.setDataVector(data, columnNames);

		return model;
	}

	/*
	 * Methods to access data from the database
	 * by JANI, AUR�LIEN */

	public void querieAll(String table) throws SQLException {

		DefaultTableModel model = new DefaultTableModel();
		JTable jt = new JTable(model);

		ArrayList<String> row;

		rs = stmt.executeQuery("SELECT * from " + table + ";");
		java.sql.ResultSetMetaData rsmd = rs.getMetaData();

		int columnsNumber = rsmd.getColumnCount();

		while (rs.next()) {
			row = new ArrayList();
			for (int i = 1; i <= columnsNumber; i++) {
				String columnValue = rs.getString(i);
				row.add(columnValue);
			}
			// System.out.print(Test.stringify(row));
			// System.out.println("");
		}
		con.close();
	}

	public void querieSpecific(String table, String username, String date1,
			String date2) throws SQLException {
		String and = "";

		if (!username.equals("")) {
			username = "Username = " + "'" + username + "'";
		}
		if (!date1.equals("")) {
			date1 = "`Date` between " + "'" + date1 + "'";
		}
		if (!date2.equals("")) {
			date2 = " and " + "'" + date2 + "'";
		}
		if (!username.equals("") && (!date1.equals(""))) {
			and = " and ";
		}

		rs = stmt.executeQuery("SELECT * from " + table + " WHERE " + username
				+ and + date1 + date2 + ";");

		java.sql.ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();

		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				String columnValue = rs.getString(i);
				System.out.print(columnValue + " ");
			}
			System.out.println("");
		}
		con.close();
	}

	public void querieColumn(String table, String column) throws SQLException {

		if (Test.inputTest().equals("Locations")) {
			table = "Locations";
			if (Test.inputTest().equals("City")) {
				column = "City";
			} else if (Test.inputTest().equals("Street")) {
				column = "Street";
			}
		}

		if (Test.inputTest().equals("ExtraCostTypes")) {
			table = "ExtraCostTypes";
			column = "ExtraCostTypes";
		}

		rs = stmt.executeQuery("SELECT " + column + " FROM " + table + ";");

		while (rs.next())
			System.out.println(rs.getString(1));

		con.close();

	}

	/*
	 * Methods to insert Userdata to the database
	 * by JANI	 */

	public void insertExtraCost(String typeOfCost, String cost, String file,
			String date) throws SQLException {

		stmt.executeUpdate("INSERT INTO ExtraCosts"
				+ "(`TypeOfCost`, `Cost`, `File`, `Date`)" + " VALUES " + "('"
				+ typeOfCost + "','" + cost + "','" + file + "','" + date
				+ "')");
	}

	public void insertTripData(String startKm, String endKm, String from,
			String to, String tripReason, String username, String name,
			String car, String date) throws SQLException {

		stmt.executeUpdate("INSERT INTO TripData"
				+ "(`StartingKm`, `EndingKm`, `From`, `To`, `ReasonOfTrip`, `Username`, `Name`, `RegistryNumber`, `Date`)"
				+ " VALUES " + "('" + startKm + "','" + endKm + "','" + from
				+ "','" + to + "','" + tripReason + "','" + username + "','"
				+ name + "','" + car + "','" + date + "')");
	}
}

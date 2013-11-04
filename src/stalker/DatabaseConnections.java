package stalker;

import java.sql.*;


public class DatabaseConnections {

	private Statement statement;

	public void DatabaseConnections() {
		// Initialize database connection and create a Statement object
		initializeDB();

	}

	private void initializeDB() {
		try {
		// Load JDBC driver
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");

		// Connect to a database
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/StalkerDB", "Dyrnwyn", "Dyrnwyn!");
		System.out.println("Database connected");

		// Create a statement
		statement = connection.createStatement();
	}
 catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	/*
	 * private void submitdatatoDB(ActionEventSubmit e) {
	 * 
	 * }
	 */
	private void selectdatafromDB_actionPerformed(ActionEventSelect e) {
		String startDate = startDateField.getText();
		String endDate = endDateField.getText();
		try {
			String queryString = "select * from TripData"
					+ "where Tripdata.Date > '" + startDate
					+ "'and Tripdata.Date < '" + endDate;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
}






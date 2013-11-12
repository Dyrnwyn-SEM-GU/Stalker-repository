package stalker;

import java.sql.*;


public class DatabaseConnections {

	public DatabaseConnections() {
	}
	
	
	private void selectCityToFromToFields() throws SQLException,
			ClassNotFoundException {

		// Load the JDBC driver

		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");

		// Establish a connection
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/StalkerDB", "Dyrnwyn", "Dyrnwyn!");
		System.out.println("Database connected");

		// Create a statement
		Statement statement = connection.createStatement();

		// Execute a statement
		ResultSet resultSet = statement.executeQuery("SELECT City FROM StalkerDB.Locations;");

		// Iterate through the result and print the student names
		while (resultSet.next())
			System.out.println(resultSet.getString(1) + "\t"
					+ resultSet.getString(2) + "\t" + resultSet.getString(3));

		// Close the connection

		connection.close();
	}

	private void selectUsernameFromUserTbl() throws SQLException,
			ClassNotFoundException {

		// Load the JDBC driver

		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");

		// Establish a connection
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/StalkerDB", "Dyrnwyn", "Dyrnwyn!");
		System.out.println("Database connected");

		// Create a statement
		Statement statement = connection.createStatement();

		// Execute a statement
		ResultSet resultSet = statement.executeQuery("SELECT Username FROM User");

		// Iterate through the result and print the student names
		while (resultSet.next())
			System.out.println(resultSet.getString(1) + "\t"
					+ resultSet.getString(2) + "\t" + resultSet.getString(3));

		// Close the connection

		connection.close();
	}

	private void selectPasswordFromUserTbl() throws SQLException,
			ClassNotFoundException {

		// Load the JDBC driver

		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");

		// Establish a connection
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/StalkerDB", "Dyrnwyn", "Dyrnwyn!");
		System.out.println("Database connected");

		// Create a statement
		Statement statement = connection.createStatement();

		// Execute a statement
		ResultSet resultSet = statement.executeQuery("SELECT Password FROM User");

		// Iterate through the result and print the student names
		while (resultSet.next())
			System.out.println(resultSet.getString(1) + "\t"
					+ resultSet.getString(2) + "\t" + resultSet.getString(3));

		// Close the connection

		connection.close();
	}

	protected void selectTripDataFromDBForReportForUser() throws SQLException,
	ClassNotFoundException {

// Load the JDBC driver

		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");

		// Establish a connection
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/StalkerDB", "Dyrnwyn", "Dyrnwyn!");
		System.out.println("Database connected");

		// Create a statement
		Statement statement = connection.createStatement();

		// Execute a statement
		
		// TODO Replace 'jani@dyrnwyn.dy' for username with the variable name where the entered 
		// username that the user enters is stored.
		ResultSet resultSet = statement.executeQuery("SELECT `Date`, `StartingKm`, "
				+ "`StartingKm`, `From`, `To`, `ReasonOfTrip`, `RegistryNumber` "
				+ "FROM TripData WHERE `Username` = 'jani@dyrnwyn.dy';");

		// Iterate through the result and print the student names
		while (resultSet.next())
			System.out.println(resultSet.getString(1) + "\t"
					+ resultSet.getString(2) + "\t" + resultSet.getString(3));

		// Close the connection

		connection.close();
	}

	protected void selectTripDataFromDBForReportForAdministrator() throws SQLException,
	ClassNotFoundException {

// Load the JDBC driver

		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver loaded");

		// Establish a connection
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/StalkerDB", "Dyrnwyn", "Dyrnwyn!");
		System.out.println("Database connected");

		// Create a statement
		Statement statement = connection.createStatement();

		// Execute a statement
		
		// TODO Replace 'jani@dyrnwyn.dy' for username with the variable name where the entered 
		// username that the user enters is stored. // Jani
		ResultSet resultSet = statement.executeQuery("SELECT `Date`, `StartingKm`, "
				+ "`StartingKm`, `From`, `To`, `ReasonOfTrip`, `RegistryNumber` "
				+ "FROM TripData");

		// Iterate through the result and print the student names
		while (resultSet.next())
			System.out.println(resultSet.getString(1) + "\t"
					+ resultSet.getString(2) + "\t" + resultSet.getString(3));

		// Close the connection

		connection.close();
	}

	
	private void insertTripDataIntoDB() throws SQLException,
			ClassNotFoundException {
	
		// Load the JDBC driver

				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver loaded");

				// Establish a connection
				Connection connection = DriverManager.getConnection(
						"jdbc:mysql://localhost/StalkerDB", "Dyrnwyn", "Dyrnwyn!");
				System.out.println("Database connected");

				// Create a statement
				Statement statement = connection.createStatement();

				// Execute a statement
				
				// TODO Repalce the values below with of each textfield name and the date 
				// with the name of the dropdowncalendar for date selection. // Jani
		ResultSet resultSet = statement
				.executeQuery("INSERT INTO TripData"
						+ "(`StartingKm`, `EndingKm`, `From`, `To`, `ReasonOfTrip`, `Username`, `Name`, `RegistryNumber`, `Date`)"
						+ "VALUES ('123500', '123888', 'Göteborg', 'Stockholm', 'Conference', 'janip', 'Jani Pasanen', 'URB123', '2023-11-01')");

				// Iterate through the result and print the student names
				while (resultSet.next())
					System.out.println(resultSet.getString(1) + "\t"
							+ resultSet.getString(2) + "\t" + resultSet.getString(3));

				// Close the connection

				connection.close();
		
		// INSERT INTO `StalkerDB`.`TripData` (`StartingKm`, `EndingKm`, `From`, `To`, `ReasonOfTrip`, `Username`, `Name`, `RegistryNumber`, `Date`) VALUES ('123500', '123888', 'Göteborg', 'Stockholm', 'Conference', 'janip', 'Jani Pasanen', 'URB123', '2023-11-01');
	}
	
	
}
	
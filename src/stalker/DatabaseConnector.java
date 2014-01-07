package stalker;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;

public class DatabaseConnector {

	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	File selectedFile;

	public DatabaseConnector() throws SQLException {

		con = DriverManager.getConnection("jdbc:mysql://localhost/StalkerDB",
				"Dyrnwyn", "Dyrnwyn!");
		// System.out.println("Database connected");

		stmt = con.createStatement();

	}
	
	public String querieCredentials(String row1, String row2, String value) throws SQLException{
		
		rs = stmt.executeQuery("SELECT " + row1 + " FROM User WHERE " + row2 + " = '" + value + "';");
		String result = "";
		
		if (rs.next()) {
			 result = rs.getString(1);
		}else{
			System.out.println("result set is empty");
		}
		// con.close();
		return result;
	}
	
	/* this method creates a table-model (as used by JTable) for a specific table 
	 * at the moment only from the user table
	 * by DANIELLE	|| supervisor helped streamline it, but it was working before as well */
	
//	public DefaultTableModel reportTable(DefaultTableModel model)
//			throws SQLException {
//
//		String table = "";
//		String column = "";
//		String username = "";
//		String date1 = "";
//		String date2 = "";
//		String and = "";
//		String carRegistryNumber = "";
//
//		if (!username.equals("")) {
//			username = "Username = " + "'" + username + "'";
//		}
//		if (!date1.equals("")) {
//			date1 = "`Date` between " + "'" + date1 + "'";
//		}
//		if (!date2.equals("")) {
//			date2 = " and " + "'" + date2 + "'";
//		}
//		if (!username.equals("") && (!date1.equals(""))) {
//			and = " and ";
//		}
//
//		String selectTable = "SELECT A.idtripdata, A.From, A.To,  A.EndingKm, A.ReasonOfTrip, A.Name, A.RegistryNumber, A.Date FROM TripData A";
//
//		String selectFromTable = "SELECT * from " + table + ";";
//
//		String selectColumn = "SELECT " + column + " FROM " + table + ";";
//
//		// accessing 2 tables: TripData and ExtraCosts and getting data from
//		// necessary columns only
//		// Getting the report from all trips and extra costs from users ( shown
//		// even users without extra cost ) using the Foreign Key Username
//		String selectTripsExtraCost = "SELECT A.Name, A.From, A.To, A.StartingKm, A.EndingKm, A.ReasonOfTrip, A.RegistryNumber, A.Date, B.TypeOfCost, B.Cost, B.File, B.Date FROM TripData A LEFT JOIN ExtraCosts B ON A.Username = B.Username";
//
//		// The total of all extra costs
//		String selectTotalExtraCost = "SELECT SUM(Cost) AS Total_Costs_From_Users FROM ExtraCosts";
//
//		String selectCarConsumption = "select Brand, RegistryNumber, Model, Consumption FROM car WHERE RegistryNumber = "
//				+ carRegistryNumber + ";";
//
//		String selectSpecific = "select * from tripdata where username = 'use an valid username in your database' and date between 'start date' and 'end date'";
//
//		// select an specific user between specific dates
//		// problem : syntax error
//		String selectUserBTWDates = "SELECT * from " + table + " WHERE "
//				+ username + and + date1 + date2 + ";";
//
//		// getting input from user to merge
//		Scanner input = new Scanner(System.in);
//		String query = null;
//		String tabl = null;
//
//		System.out.println("Choose a Query:");
//		query = input.nextLine();
//
//		/*
//		 * if choose select table, the stmt will return the selectFromTable
//		 * query ... can be implemented with ActionListener
//		 */
//		if (query.equalsIgnoreCase("select table")) {
//			System.out.println("Choose an table:");
//			table = input.nextLine();
//
//			rs = stmt.executeQuery(selectFromTable);
//
//		}
//
//		if (query.equalsIgnoreCase("select trips extra costs")) {
//
//			rs = stmt.executeQuery(selectTripsExtraCost);
//
//		}
//		if (query.equalsIgnoreCase("select specific")) {
//			rs = stmt.executeQuery(selectSpecific);
//
//		}
//
//		// rs = stmt.executeQuery(selectTable);
//		rsm = (ResultSetMetaData) rs.getMetaData();
//
//		// Getting number of rows
//		int rowNumber;
//		rs.last();
//		rowNumber = rs.getRow();
//		rs.beforeFirst();
//
//		// System.out.println(" NUMBER OF ROW:" + rowCount);
//
//		// Getting number of columns
//		int nrCols = rsm.getColumnCount();
//
//		// Object to store data from database
//		data = new Object[rowNumber][nrCols];
//
//		int i = 0;
//
//		while (rs.next()) {
//
//			// fill the Object data with data values
//			for (int a = 0; a <= (nrCols - 1); a++) {
//
//				data[i][a] = rs.getString(a + 1);
//
//				// System.out.print("  " + data[i][a] + "\n");
//
//				System.out.println(data[i][a]);
//
//			}
//
//			i++;
//
//		}
//
//		con.close();
//
//		// here we get table column names and save in array
//		columnName = new String[nrCols];
//		// String column = null;
//
//		for (int c = 1; c <= nrCols; c++) {
//			columnName[c - 1] = rsm.getColumnLabel(c);
//
//			// System.out.println("COLUMN NAMES:" + columnName);
//
//		}
//
//		// setting columns and its values
//		model.setDataVector(data, columnName);
//
//		return model;
//	}

	// Code written by Danielle
	// Merged by Aure
	// and modified 2014-01-06 by Jani (Added username based filtering)

	public DefaultTableModel reportTable(DefaultTableModel model,
			String username)
			throws SQLException {
		
		/* could be combined with the specificQuerie method to generate a
		 * specific SQL string  
		 * 
		 */
		String SQL = "Select * from TripData WHERE Username ='" + username
				+ "';";

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
		}
		
		Object[][] data = new Object[t.size()][nrCols];
		for (int i = 0; i < t.size(); i++) {
			for (int j = 0; j < 11; j++) {
				data[i][j] = t.get(i)[j];
			}
		}

		   Object[] columnNames = { "idTripData", "StartingKm", "EndingKm",
                "From", "To", "ReasonOfTrip", "Username",
                "Name", "RegistryNumber", "Timestamp", "Date" };

		model.setDataVector(data, columnNames);

		return model;
	}

	
	
	/*
	 * Methods to access data from the database
	 * by JANI, AURELIEN */

	
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
	/*
	 * Used to populate car dropdown menus and
	 * only display the cars registered for the
	 * logged in user.
	 * 
	 * By Jani
	 */
	
	public String[] querieCar(String column, String table, String username) throws SQLException {
			ArrayList<String> al = new ArrayList();
			rs = stmt.executeQuery("SELECT DISTINCT " + column + " FROM " 
			+ table + " WHERE Username = '" + username + "' ORDER BY " + column + " ASC;");
			
		//	"SELECT DISTINCT RegistryNumber FROM Car WHERE Username = '" + username + "' ORDER BY RegistryNumber ASC;");
			
			while(rs.next()){
				al.add(rs.getString(1));
			}
			
			String [] result = al.toArray(new String[al.size()]);
			return result;
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

		rs = stmt.executeQuery("SELECT DISTINCT " + column + " FROM " + table + ";");

		while (rs.next())
			// System.out.println(rs.getString(1));

		con.close();
	}
	
	public String querieElement(String value1, String table, String value2, String is) throws SQLException{
		
		rs = stmt.executeQuery("SELECT " + value1 + " FROM " + table + " WHERE " + value2 + " = '" + is + "';");
		String result = "";
		
		if (rs.next()) {
			 result = rs.getString(1);
		}else{
			System.out.println("result set is empty");
		}
		con.close();
		return result;
	}

	/*
	 * Methods to insert Userdata to the database
	 * by JANI	 */

	public void insertTripData(String startKm, String endKm, String from,
			String to, String tripReason, String username, String name,
			String car, String date) throws SQLException {

		stmt.executeUpdate("INSERT INTO TripData"
				+ "(`StartingKm`, `EndingKm`, `From`, `To`, `ReasonOfTrip`, `Username`, `Name`, `RegistryNumber`, `Date`)"
				+ " VALUES " + "('" + startKm + "','" + endKm + "','" + from
				+ "','" + to + "','" + tripReason + "','" + username + "','"
				+ name + "','" + car + "','" + date + "')");
	}
	
	public void insertExtraCost(String typeOfCost, String cost, String file,
			String date, String idTripData) throws SQLException {

		stmt.executeUpdate("INSERT INTO ExtraCosts"
				+ "(`TypeOfCost`, `Cost`, `File`, `Date`)" + " VALUES " + "('"
				+ typeOfCost + "','" + cost + "','" + file + "','" + date
				+ "','" + idTripData + "')");
	}
	/*
	 * Method created by Jani. 
	 * Used to enter new user data
	 * 
	 */
	
	public void insertNewUser(String username, String password) throws SQLException {
		
		stmt.executeUpdate("INSERT INTO User"
				+ "(`Username`, `Password`)" + " VALUES " + "('"
				+ username + "','" + password + "')");
	}
	
	
	/*
	 * Method created by Jani. 
	 * Used to enter new car registry number for specific  data
	 * 
	 */
	
	public void insertNewCar(String registryNumber, String username) throws SQLException {
		stmt.executeUpdate("INSERT INTO Car"
				+ "(`RegistryNumber`, `Username`)" + " VALUES " 
				+ "('" + registryNumber + "','" + username + "')");
	}
//	public void insertNewCar(String brand, String registryNumber, String type, String consumption, String username) throws SQLException {
//		stmt.executeUpdate("INSERT INTO Car"
//				+ "(`Brand`, `RegistryNumber`, `Type`, `Consumption`, `Username`)" + " VALUES " + "('"
//				+ brand + "','" + registryNumber + "','" + type + "','" + consumption + "','" + username
//				+ "')");
//	}
	
	 /* Method to fetch the information for the dropdown menu
	 * 	by AURELIEN */
	
	/*
	 * Modified by Jani to sort the result in ascending order
	 */
	
	public String[] getColumn(String column, String table) throws SQLException{
		ArrayList<String> al = new ArrayList();
		
		rs = stmt.executeQuery("SELECT DISTINCT " + column + " FROM " + table
				+ " ORDER BY " + column + " ASC;");
	
		while(rs.next()){
			al.add(rs.getString(1));
		}
		
		String [] result = al.toArray(new String[al.size()]);
		return result;
		
	}
	
	/*
	 * Methods to choose and insert Pictures or files to the database by
	 * DANIELLE, redesigned by AURELIEN
	 * 
	 * Updated by Danielle 2014/01/06
	 */
	
	public void uploadPic() {
	    JFileChooser fileChooser = new JFileChooser();
	    // can only select files
	    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 
	    fileChooser.showOpenDialog(null);
		selectedFile = fileChooser.getSelectedFile();
		// return selectedFile;
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
			// if no file is found
			System.out.print(e);
		}
		con.close();
	}
	
	/*
	 * by Danielle Method to show the picture uploaded,
	 * 
	 * Updated 2014/01/06
	 */

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
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(label);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	/*
	 * Methods to export data from the database
	 * by GABRIELE */
	
	public String buildCSV(String table, String username) throws SQLException {

		ArrayList<String> row = new ArrayList();

		rs = stmt.executeQuery("SELECT * from " + table + " WHERE Username = '"
				+ username + "';");
		java.sql.ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		
		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				String columnValue = rs.getString(i);
				row.add(columnValue);	
			}
			row.add("\n");
		}
		con.close();
		return Test.stringify(row);
	}
	
	/*
	 * This method is used by the export to CSV
	 */

	public void exportCSV(String filepathAndName, String username)
			throws SQLException, IOException {
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(
new File(
				filepathAndName)));
		bw.write(buildCSV("TripData", username));
		bw.close();
	}

	// Method for updating user password written by Sally and Jani

	public void insertPassword(String password, String username)
			throws SQLException {

		stmt.executeUpdate("UPDATE User SET Password = '" + password
				+ "' WHERE Username = '" + username + "';");

	}
}


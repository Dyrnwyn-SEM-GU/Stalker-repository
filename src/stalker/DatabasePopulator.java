package stalker;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DatabasePopulator {

	Random rand = new Random();
	// write filepath here, empty by default
	String filename = "";
	Connection c = null;
	Statement s = null;
	ResultSet rs = null;

	/*
	 * Reads a text file. Puts it in an arrayList. This is repeated with every
	 * .rtf and .txt file 
	 * by GABRIELE
	 */

	ArrayList<java.lang.String> getData(String filename)
			throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileReader(filename));
		ArrayList<String> listData = new ArrayList<String>();
		while (scanner.hasNext()) {
			listData.add(scanner.nextLine());
		}
		return listData;
	}

	/*
	 * This is an array for each text file to take a random element. 
	 * by GABRIELE with help from Khashayar Napster.
	 */

	public int[] getSQL() throws FileNotFoundException {

		int result[] = new int[12];
		int n = 0;
		while (n < 10) {
			result[0] = rand.nextInt(getData("Brand.rtf").size());
			result[1] = rand.nextInt(getData("RegistryNumber.txt").size());
			result[2] = rand.nextInt(getData("Type.rtf").size());
			result[3] = rand.nextInt(getData("Consumption.rtf").size());
			result[4] = rand.nextInt(getData("Cities.txt").size());
			result[5] = rand.nextInt(getData("Streets.txt").size());
			result[6] = rand.nextInt(getData("ExtraCosts.rtf").size());
			result[7] = rand.nextInt(getData("Reasons.rtf").size());
			result[8] = rand.nextInt(getData("Usernames.rtf").size());
			result[9] = rand.nextInt(getData("FirstNames.rtf").size());
			result[10] = rand.nextInt(getData("LastNames.rtf").size());
			result[11] = rand.nextInt(getData("Password.rtf").size());
			return result;
		}
		return null;
	}
	
	/*
	 * Connects and populates already created tables
	 * by GABRIELE with help from Khashayar Napster.
	 */

	void establishConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost/StalkerDB",
					"Dyrnwyn", "Dyrnwyn!");

			int n[] = getSQL();
			s = c.createStatement();

			// INSERTED USERNAMES

			s.execute("INSERT INTO User"
					+ " (Username,Firstname,Lastname,Password)" + "VALUES ('"
					+ getData("Usernames.rtf").get(n[8]) + "','"
					+ getData("FirstNames.rtf").get(n[9]) + "','"
					+ getData("Lastnames.rtf").get(n[10]) + "','"
					+ getData("Password.rtf").get(n[11]) + "')");

			// INSERTED CAR TABLE

			s.execute("INSERT INTO Car"
					+ " (Brand,RegistryNumber,Type,Consumption,Username)"
					+ " VALUES ('" + getData("Brand.rtf").get(n[0])
					+ "','" + getData("RegistryNumber.txt").get(n[1])
					+ "','" + getData("Type.rtf").get(n[2]) + "','"
					+ getData("Consumption.rtf").get(n[3]) + "',"
					+ "null)");

			// INSERTED LOCATIONS

			s.execute("INSERT INTO Locations" + " (City,Street)" + " VALUES ('"
					+ getData("Cities.txt").get(n[4]) + "','"
					+ getData("Streets.txt").get(n[5]) + "')");

			// INSERTED EXTRA COSTS

			s.execute("INSERT INTO ExtraCostTypes" + " (ExtraCostTypes)"
					+ "VALUES ('" + getData("ExtraCosts.rtf").get(n[6])
					+ "')");

			// INSERTED REASONS

			s.execute("INSERT INTO ReasonForTravel" + " (TypeOfReason)"
					+ "VALUES ('" + getData("Reasons.rtf").get(n[7])
					+ "')");

		} catch (SQLException | ClassNotFoundException se) {
			System.out.print("Couldnt connect. NO ONE KNOWS WHY");
			se.printStackTrace();
			System.exit(1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

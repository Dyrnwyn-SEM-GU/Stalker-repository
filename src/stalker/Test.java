package stalker;

import java.sql.*;
import java.util.*;

/* main Class used for testing purposes
 * by AURELIEN */

public class Test {

	String column = "";
	static String username = "janip";
	static String table = "User";

	static String date1 = "";// "2012-03-10";
	static String date2 = "";// "2012-04-12";

	public static void main(String[] args) throws SQLException {
		new GUI();
		DatabaseConnector dc = new DatabaseConnector();
		//dc.querieAll(table);
		//dc.querieSpecific(table, username, date1, date2);
	}
	
	/* methods that might be helpful when testing the code
	 * by AURELIEN */
	
	// this method transforms an ArrayList to a string
	public static String stringify(ArrayList<String> ar) {
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

	// this method gives back user input from the console
	public static String inputTest() {

		Scanner sc = new Scanner(System.in);
		String inp = sc.nextLine();
		return inp;
	}
}

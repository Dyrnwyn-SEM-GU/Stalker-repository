package stalker;

import java.sql.SQLException;


public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		new GUI(); // Mahsas new window?
		new TestUiFrame(); // Mahsas test windows with input fields
		new PageCreate();
		Login loginObject = new Login();
		View viewObject = new View();
		Create createObject = new Create();
		Account accountObject = new Account();
		DatabaseConnections databaseConnectionsObject = new DatabaseConnections();
		try {
			databaseConnectionsObject.selectTripDataFromDBForReport();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub

	}

}

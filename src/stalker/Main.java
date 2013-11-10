package stalker;


public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		new GUI();
		Login loginObject = new Login();
		View viewObject = new View();
		Create createObject = new Create();
		Account accountObject = new Account();
		DatabaseConnections databaseConnectionsObject = new DatabaseConnections();
		databaseConnectionsObject.StartDatabaseConnection();

		// TODO Auto-generated method stub

	}

}

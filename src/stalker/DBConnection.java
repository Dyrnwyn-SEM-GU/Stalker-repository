import java.sql.*;

public class DBConnection {
	public static Connection c = null;
	public static Statement s = null;
	public ResultSet rs = null;
	
	public static void establishConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost/StalkerDB", "Dyrnwyn", "Dyrnwyn!");
			s = c.createStatement(); 
//			ResultSet resultSet = 
//					s.executeQuery("INSERT INTO Car"
//							+ "(Brand,RegistryNumber,Type,Consumption,Username)"
//							+ "VALUES (read.getRandomBrands().get(n1),read.getRandomRegistryNr().get(n2),read.getType().get(n3),read.getConsumption().get(n4),null)");
//			s.execute("INSERT INTO new_table VALUES (Skovde,Stockholm,delivery,Skovde,Stockholm,delivery,delivery);");
		} catch (SQLException | ClassNotFoundException se) {
			System.out.print("Couldnt connect. NO ONE KNOWS WHY");
			System.exit(1);
		}
	}

}

		
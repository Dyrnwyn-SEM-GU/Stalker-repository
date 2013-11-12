import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ReadingFiles {
		static ReadingFiles read = new ReadingFiles();
	
		static Random rand = new Random();
		private static final Scanner String = null;

		

		ArrayList<java.lang.String> getConsumption() throws FileNotFoundException {
			
			Scanner scanner1 = new Scanner(new FileReader("Consumption.rtf"));
			ArrayList<String> listConsumption = new ArrayList<String>();
			while (scanner1.hasNext()) {
				listConsumption.add(scanner1.nextLine());
//				System.out.println(list);
			}
			return listConsumption;
		}
		
		ArrayList<java.lang.String> getType() throws FileNotFoundException {
			
			Scanner scanner1 = new Scanner(new FileReader("Type.rtf"));
			ArrayList<String> listType = new ArrayList<String>();
			while (scanner1.hasNext()) {
				listType.add(scanner1.nextLine());
//				System.out.println(list);
			}
			return listType;
		}
		
		ArrayList<java.lang.String> getRandomRegistryNr() throws FileNotFoundException {
			
			Scanner scanner1 = new Scanner(new FileReader("RegistryNumber.txt"));
			ArrayList<String> listRegistryNumbers = new ArrayList<String>();
			while (scanner1.hasNext()) {
				listRegistryNumbers.add(scanner1.nextLine());
//				System.out.println(list);
			}
			return listRegistryNumbers;
		}
		
		ArrayList<java.lang.String> getRandomBrands() throws FileNotFoundException {
			
			Scanner scanner = new Scanner(new FileReader("Brand.rtf"));
			ArrayList<String> listBrands = new ArrayList<String>();
			ArrayList<String> listRegistryNumbers = new ArrayList<String>();
			while (scanner.hasNext()) {
				listBrands.add(scanner.nextLine());
//				System.out.println(list);
			}	
			return listBrands;
		}
		
		public static void getSQL() throws FileNotFoundException {
		read.getRandomRegistryNr();
		read.getRandomBrands();

		int n = 0;
		while(n<10) {
			int n1 = rand.nextInt(read.getRandomBrands().size());
			int n2 = rand.nextInt(read.getRandomRegistryNr().size());
			int n3 = rand.nextInt(read.getType().size());
			int n4 = rand.nextInt(read.getConsumption().size());
//			int n3 = dice(n1);
			n++;
/*			System.out.println("INSERT INTO Car VALUES (" + read.getRandomBrands().get(n1) + "," 
					+ read.getRandomRegistryNr().get(n2) + "," + read.getType().get(n3) + "," 
					+ read.getConsumption().get(n4) + "," + "null" + ")"); */
		}
	} 

		
		public static void getDistances() throws FileNotFoundException {
			String content1 = new String();
			content1 = new Scanner(new FileReader("Distances.txt")).useDelimiter("//").next();
			String[] array = content1.split(" ");
			for(int i = 0; i < array.length; i++) {
				System.out.println(array[i]);
				
			}   
			System.out.print(array.length);
		}
		
		public void createTable(Connection mysqlCon, String query) {
			Statement mysqlS;
			try {
				mysqlS = mysqlCon.createStatement();
				mysqlS.executeUpdate(query);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 } 
		public void insertValues(Connection mysqlCon, String query) {
			Statement mysqlS;
			try {
				mysqlS = mysqlCon.createStatement();
				mysqlS.executeUpdate(query);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		public Object[] getTables(Connection mysqlCon) {
//			Statement mysqlS;
//			ArrayList<String> table_names = new ArrayList<String>();
//			try {
//				mysqlS = mysqlCon.createStatement();
//				String query = "select TABLE_NAME from information_schema.tables "
//						+ "where TABLE_TYPE = 'BASE TABLE' and TABLE_SCHEMA = '"
//						+  + "';";
//				ResultSet rs = mysqlS.executeQuery(query);
//				while (rs.next()) {
//					table_names.add(rs.getString(1));
//				}
//				Object[] result = table_names.toArray();
//				return result;
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//			}
//		}
}









//				List<String> myList = new ArrayList<String>(Arrays.asList(content1.split);
//				System.out.print(myList.size());
//				for (int i = 0; i < myList.size(); i++) {
//					System.out.println(myList);
//				}
				
				


		
//				try {
//					reader = new BufferedReader(new FileReader("Reasons.rtf"));
////					System.out.println("First step done");
//					
//					ArrayList<String> reasons = new ArrayList<String>();
//					String line;
//					int i = 0;
//					while ((line = reader.readLine()) != null) {
//						reasons.add(line);
//						i++;
//						System.out.println(reasons);
//						System.out.println();
//					}
//					}
//					reader.close();
//					} catch (IOException e) {
//					e.printStackTrace();
//				
//				}
//				return null;
//		}

	

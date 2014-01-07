package stalker;

import java.awt.*;
import java.sql.*;
import java.util.Scanner;

import javax.swing.*; 
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;



/** 
 * @author Danielle 
 * 
 * class for jtable model with implemented methods, 
 * 
 * in GUI add this model like that:
 *  
 * JTabel reportTable = new JTable(new StalkerTableModel());  
 * JScrollPane scrollPane = new JScrollPane(reportTable); 
 * 
 *
 */


public class StalkerTableModel extends AbstractTableModel implements TableModelListener {

	
	
	
	ResultSet rs = null;   
	java.sql.Statement stmt = null;
	ResultSetMetaData rsm = null; 
	java.sql.Connection con; 
	

	
	
	Object[][] data ;
	String[] columnName ;
	
	
	
  public StalkerTableModel() throws SQLException  {
	
	 
	
	  
	 

		con = DriverManager.getConnection("jdbc:mysql://localhost/StalkerDB",
				"username", "password");
		System.out.println("Database connected");

		stmt = con.createStatement();
	
	
	
		String table = "";
		String column = "";
//		String username = "";
//		String date1 = "";
//		String date2 = "";
//		String and = "";
//		String carRegistryNumber = "";	
		
		
		
	String selectColumn = "SELECT " + column + " FROM " + table + ";";	
	
	String selectTable = "Select * from extracosts";
	
	// use this code to filter the  output for the report,
	// get the queries from the user, select by date, car, destination, user ...
	// Here is  an example using scanner
	Scanner input = new Scanner(System.in);  
	String query;
	
	System.out.println("Choose a Query:");
	query = input.nextLine();
	
	if (query.equalsIgnoreCase("select table")) { 
		
		rs = stmt.executeQuery(selectTable);
		
	 } if (query.equalsIgnoreCase("select column")) {
		 
		rs = stmt.executeQuery(selectColumn);
		
	 }
		
				

	// rs = stmt.executeQuery(selectTable);
	 rsm = (ResultSetMetaData) rs.getMetaData();

	
	int rowNumber;
	rs.last();
	rowNumber = rs.getRow();
	rs.beforeFirst();


	int nrCols = rsm.getColumnCount();

	
	data = new Object[rowNumber][nrCols];

	int i = 0;

	while (rs.next()) {

	
		for (int a = 0; a <= (nrCols - 1); a++) {

			data[i][a] = rs.getString(a + 1);

			// System.out.print("  " + data[i][a] + "\n");

			System.out.println(data[i][a]);

		}

		i++;

	}

	con.close();

	
      columnName = new String[nrCols];


	for (int c = 1; c <= nrCols; c++) {
		columnName[c - 1] = rsm.getColumnLabel(c);

		// System.out.println("COLUMN NAMES:" + columnName);


	}  
	
	

	
	
	
	
	
  }
	
	

	public int getColumnCount() {
		
		
		return columnName.length;
		
	}
	
	
	public int getRowCount() {
		
		return data.length;
	}
	 
	
	public String getColumnName(int col) { 
		
		return columnName[col];
		
		
	} 
	
	

	public Object getValueAt(int row, int column) {
		
		return data[row][column];
	} 
	
	
	
	// all tables must be completely full of data in all cells, 
	// otherwise getColumnClass will not work properly and it will return null
	public Class getColumnClass(int col)  {
		
	
		
		return getValueAt(0, col).getClass();   
		
		 
		
	  /* if (col == 2) { 
		 return ImageIcon.class;
		   
	   } else { 
		   
		  return String.class; 
	   }*/
		
	//	return Object.class;
	} 
	
	
	public boolean isCellEditable(int row, int col) { 
		
		return true;
	} 
	
	
	
	
     public void setValueAt(Object value, int row, int col) { 
		
		data[row][col] = value; 
		fireTableCellUpdated(row, col); 
		
	 }
	
	
	// method to be used if the data in jtable has been modified by the user
	
	public void tableChanged(TableModelEvent e) {
		
		int row = e.getFirstRow(); 
		int column = e.getColumn(); 
		TableModel tableModel = (TableModel)e.getSource(); 
		Object data = tableModel.getValueAt(row, column); 
		
		
		
	}
	

	

	
	
	
	
	
	
	
	
	
}

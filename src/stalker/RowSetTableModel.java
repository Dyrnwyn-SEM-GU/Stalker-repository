package stalker; 

import java.sql.*; 

import javax.sql.*; 
import javax.swing.table.AbstractTableModel; 


public class RowSetTableModel extends AbstractTableModel implements RowSetListener {
    
	// RowSet for the result set
	private RowSet rowSet;  
	
	// return the row set
	public RowSet getRowSet() { 
		return rowSet;
	} 
	
	 
	/** set a new rowset */ 
	public void setRowSet(RowSet rowSet) { 
		if(rowSet != null) { 
			this.rowSet = rowSet; 
			rowSet.addRowSetListener(this); 
			fireTableStructureChanged();
		}
	}
	
	
	/** return the number of rows in the row set */  
	public int getRowCount() { 
		try { 
			rowSet.last(); 
			return rowSet.getRow();
		} 
		  catch (Exception ex) { 
			  ex.printStackTrace();
		  } 
		return 0;
	}
	
	
	
	/* return the number of column in the row set */ 
	public int getColumnCount() { 
		try { 
		   if (rowSet != null) { 
			   return rowSet.getMetaData().getColumnCount();
		   }
		} 
		  catch (SQLException ex) { 
			  ex.printStackTrace();
		  } 
		
		return 0;
	}
	
	
	 
	
	/** return value at specified row and column */
	 public Object getValueAt(int row, int column)  { 
		 try { 
			 rowSet.absolute(row + 1); 
			 return rowSet.getObject(column + 1);
		 } 
		   catch (SQLException sqlex) { 
			   sqlex.printStackTrace();
		   } 
		 
		 return null;
	 }
	
	 
	 
	 
	 /** return the column at a specified column */ 
	 public String getColumnName(int column)  { 
		 try { 
			 return rowSet.getMetaData().getColumnLabel(column + 1);
		 } 
		   catch (SQLException ex) { 
			   ex.printStackTrace();
		   } 
		 
		  return " ";
	 } 
	 
	 
	 
	 
	 /* implement rowSetChanged */ 
	 public void rowChanged(RowSetEvent e) { 
		 System.out.println("Row Changed"); 
		 fireTableDataChanged();
	 }
	  
	 
	 
	 /** implement cursorMoved */  
	 public void cursorMoved( RowSetEvent e) { 
		 System.out.println("Cursor Moved");
	 }


	@Override
	public void rowSetChanged(RowSetEvent event) {
		// TODO Auto-generated method stub
		
	}


	
	
	 
	
	 
	 
	
	
}

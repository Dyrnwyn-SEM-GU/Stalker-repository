package stalker; 

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*; 

import java.awt.*; 
import java.awt.event.*; 

import javax.sql.*; 

//import SQL.RowSetTableModel;

import com.sun.rowset.CachedRowSetImpl; 




public class TableEditorB extends JPanel { 
	
	private JButton btFirst = new JButton("First"); 
	private JButton btNext = new JButton("Next"); 
	private JButton btPrevious= new JButton("Previous"); 
	private JButton btLast = new JButton("Last"); 
	private JButton btDelete = new JButton("Delete"); 
	private JButton btEdit = new JButton("Edit"); 
	private JLabel lblStatus = new JLabel(); 
	
	// table model, table selection model, table, rowset 
	private RowSetTableModel tableModel = new RowSetTableModel(); 
	private DefaultListSelectionModel listSelectionModel = new DefaultListSelectionModel(); 
	private JTable jTable1 = new JTable();  
	private RowSet rowSet;
	
	
	// set a new row set 
		public void setRowSet(RowSet rowSet) { 
			this.rowSet = rowSet; 
			tableModel.setRowSet(rowSet); 
			jTable1.setModel(tableModel); 
			
			
			//Enable auto sort on columns 
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel); 
			jTable1.setRowSorter(sorter);
			
		}
	
	
	
		 // create a table editor 
		public TableEditorB() { 
			JPanel jPanel1 = new JPanel(); 
			jPanel1.add(btFirst); 
			jPanel1.add(btNext); 
			jPanel1.add(btPrevious); 
			jPanel1.add(btLast); 
			jPanel1.add(btDelete); 
			jPanel1.add(btEdit); 
			
			setLayout(new BorderLayout()); 
			add(jPanel1, BorderLayout.SOUTH); 
			add(new JScrollPane(jTable1), BorderLayout.CENTER); 
		//	add(lblStatus, BorderLayout.WEST); 
			
			
			// set selection model for the table 
			jTable1.setSelectionModel(listSelectionModel);  
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	

}

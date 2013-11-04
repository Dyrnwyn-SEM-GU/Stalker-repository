package stalker;

import javax.swing.*; 

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.RowSet;

// import SQL.RowSetTableModel;

import com.mysql.jdbc.Statement;
import com.sun.rowset.CachedRowSetImpl;


public class StalkerReport extends JApplet{ 
	
	JButton startReport = new JButton("Click to Start Report");
	JLabel search = new JLabel("Or Search:");
	JLabel startDate = new JLabel("Start Date"); 
	JLabel carPlate = new JLabel("Car Plate"); 
	JLabel destination = new JLabel("Destination"); 
	JLabel status = new JLabel("Status"); 
	
	JTextField startDateField = new JTextField(10);
	JTextField carPlateField = new JTextField(8); 
//	JTextField destinationField = new JTextField(8);  
	
	
	
	private JComboBox statusCombo = new JComboBox(new String[] { 
			"Approved", "Deleted", "Paid"
	}); 
	
	
	private JComboBox destinationCombo = new JComboBox(new String[] { 
			"Berlin", "Copenhagen", "Linköping ", "Luleå", "Malmö", "Stockholm", "Rostock", "Uppsala"
	}); 
	
	
	private TableEditorB tableEditorB = new TableEditorB(); 
	
	
	
	
	
	public StalkerReport() { 
		
	
		JPanel panelA = new JPanel(); 
		panelA.add(startReport); 
		panelA.add(search);
		panelA.add(startDate);  
		panelA.add(startDateField);
		panelA.add(carPlate);  
		panelA.add(carPlateField);
		panelA.add(destination);  
		panelA.add(destinationCombo);
		panelA.add(status);   
		panelA.add(statusCombo);
		
		destinationCombo.setEditable(true);
	//	add(panelA, BorderLayout.NORTH);    
	//	tableEditorB.setPreferredSize(new Dimension(400, 200)); //  verificar se eh preciso
		
		add(new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelA, tableEditorB), BorderLayout.CENTER );
	
		
		initializeDB();
		

		
		
		
		
		startReport.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) { 
			  try { 
		
				
		  	      	  
		   RowSet rowSet = new CachedRowSetImpl(); 
	       rowSet.setUrl("jdbc:mysql://localhost/stalkerdb"); 
	       rowSet.setUsername("root"); 
	       rowSet.setPassword("2262"); 
	       rowSet.setCommand("select * from manager_report;"); 
	       rowSet.execute();  
	       rowSet.beforeFirst();
		   tableEditorB.setRowSet(rowSet);
	       
		
			  }
			    catch (Exception ex) {
			    	ex.getStackTrace();
			    }
			  }
		} ); 
			
		
		
		destinationCombo.addItemListener(new ItemListener() {
			  public void itemStateChanged(ItemEvent e) {  
				  
				  
				  
			 
				 if (e.getItem().equals("Stockholm")) 
				
				  try { 
		
				
		  	      	  
		   RowSet rowSet = new CachedRowSetImpl(); 
	       rowSet.setUrl("jdbc:mysql://localhost/stalkerdb"); 
	       rowSet.setUsername("root"); 
	       rowSet.setPassword("2262"); 
	       rowSet.setCommand("select * from Stockholm_trips;"); 
	       rowSet.execute();  
	       rowSet.beforeFirst();
		   tableEditorB.setRowSet(rowSet);   
	       
		   
		     
		   
		   
		   
		   
		   
		 
		     } 
		   
		   
		   
			  
				 
				  
					 
				 
			    catch (Exception ex) {
			    	ex.getStackTrace();
			    }
			  }                        
		} );   
		
		
		
		
		
		
		
		
		
		
		
		
	} 
	
	
	
	
	
	 private void initializeDB() { 
  	   try {
  		   Class.forName("com.mysql.jdbc.Driver");
  	       System.out.println("Driver Loaded"); 
  	      
  	       
  	  
  	   
  	       
  	 //     Connection connection = DriverManager.getConnection("jdbc:mysql://stalkerdb", "root", "2262");
  	//	   System.out.println("Connected with MySQL DataBase" + "\n");
  		
  		   
  		   
  	   }
  	     catch (Exception ex) {
  	    	 ex.printStackTrace();
  	     }
  	   
  	   
  	   
  	   
     } 
	 
	 
	 
	 
	// public class actionC implements ActionListener {
    //     public void actionPerformed(ActionEvent e) {  
              
        	 
    
	 
	
	
	

}

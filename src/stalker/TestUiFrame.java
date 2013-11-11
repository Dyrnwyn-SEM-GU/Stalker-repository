package stalker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.RowSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField; 
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;
import com.sun.rowset.CachedRowSetImpl; 



//danielle 









import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.RowSet;

import SQL.RowSetTableModel;

import com.mysql.jdbc.Statement;
import com.sun.rowset.CachedRowSetImpl;



public class TestUiFrame extends JFrame  {
        
//        create panels
        JPanel mainPanel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        
        // Create a tabbed pane
        
        JTabbedPane tabbedPane = new JTabbedPane();
//        Create combo box
        JComboBox comboBox=new JComboBox();
        
//        create Button
        
        JButton buttonLogout= new JButton("log out"); 
        
        
        // danielle version
        java.sql.Statement stmt; 
        Connection connection;
        JButton reportButton = new JButton("Start Report"); 
        private JButton startReport = new JButton("Start Report"); 
    	private JComboBox destinationCombo = new JComboBox(new String[] { 
    			"Berlin", "Copenhagen", "Link�ping ", "Lule�", "Malm�", "Stockholm", "Rostock"
    	});  
    	
    	JLabel search = new JLabel("Search:"); 
    	JTextField textSearch = new JTextField(12); 
    	
    	String SQL; 
    	ResultSet rs; 
    	DefaultTableModel 
		model = new DefaultTableModel(); 
	    JTable	jTable1 = new  JTable(model);
        
        public TestUiFrame() {
                
                
//                 create the position of mine panel
                mainPanel.setLayout( new BorderLayout() );
                getContentPane().add( mainPanel );
                mainPanel.setBackground(Color.BLUE);
                
//        create pages
//                
//                creatPage1();
//                creatPage2();
//                
                
                 
//                 add name of each tabb and add tabb to each panels
                        tabbedPane.addTab( "Home", panel1 );
                        tabbedPane.addTab( "Report", panel2 );
                        tabbedPane.addTab( "Create", panel3 );
                        
                        panel1.add( buttonLogout);
//                        mainPanel.add(tabedPan, BorderLayout.CENTER);
                        
//                        add colors to each panels
                        panel1.setBackground(Color.gray);
                        panel2.setBackground(Color.gray);  
                        
                        
                        
                        // Danielle version ( Mahsa check errors)
                        startReport.addActionListener(new reportAction());
                        panel2.add(startReport);  
                        panel2.add(destinationCombo);  
                        panel2.add(jTable1); 
                        add(new JScrollPane(jTable1));
                //        panel2.add(search); 
                 //       panel2.add(textSearch); 
                        
                    
                        
//                        panel3.setBackground(Color.YELLOW);
//                        mainPanel.add(panel1);
//                        mainPanel.add(panel2);
//                        mainPanel.add(panel3);
                        
                        
//                        define the position for tabb
                        mainPanel.add( tabbedPane, BorderLayout.PAGE_START);
//                        add color to main panel
                        mainPanel.setBackground(Color.gray);
//                        add color to frame but it is not important???????????
                        setBackground(Color.gray);
                
                      
                        
//         add grid layout and title and size and visibile to frame
             
            setLayout(new GridLayout(1,2));
                     setTitle("First Project Window");
                     setSize(800, 650);
                     setLocationRelativeTo(null);
                     setDefaultCloseOperation(EXIT_ON_CLOSE);
                     setVisible(true);
                     
           
               
                     
                    connectDB();
       
         
                    
                    
                    
                    
                    
                    
                    destinationCombo.addItemListener(new ItemListener() {
          			  public void itemStateChanged(ItemEvent e) {  
          				  
          		
          			 
          				 if (e.getItem().equals("Stockholm")) 
          				
          				  try {         
                    
                    
                    

          			     } 
          			   
          			   
          			   
          				  
          					 
          					  
          						 
          					 
          				    catch (Exception ex) {
          				    	ex.getStackTrace();
          				    }
          				  }                        
          			} );   
          			
                    
                    
                    
        
     }
        

        

        private void connectDB() { 
     	   try {
     		   Class.forName("com.mysql.jdbc.Driver");
     	       System.out.println("Driver Loaded"); 
     	       
     	       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/travel_tracking_system", "root", "2262");
     		   System.out.println("Connected with MySQL DataBase" + "\n");
     		   
     		   stmt = connection.createStatement();
     		   
     		   
     	   }
     	     catch (Exception ex) {
     	    	 ex.printStackTrace();
     	     }
     	   
     	   
     	   
     	   
        }
        
        
        
        public class reportAction implements ActionListener { 
            public void actionPerformed(ActionEvent e) {  
         	
            	
            	
            	
            	// report print on console
     /*       	  try {
           		   ResultSet result = stmt.executeQuery("select * from reportuserextracost;"); 
           		   
           		   ResultSetMetaData rsMetaData = result.getMetaData(); 
           		   for (int i = 1; i <= rsMetaData.getColumnCount(); i++)
           			   System.out.printf("%-12s\t\t", rsMetaData.getColumnName(i));
           			   System.out.println(); 
           			   
           		   
   				while(result.next()) {
   					for (int i = 1; i <= rsMetaData.getColumnCount(); i++ )
   						System.out.printf("%-12s\t\t",result.getObject(i)); 
   					System.out.println(); 
   				
   				}
           		   
           		   
           		   
   	
   			} catch (HeadlessException e1) {
   				
   				e1.printStackTrace();
   			} catch (SQLException e1) {
   			
   				e1.printStackTrace();
   			}
            	  */
            	
            	
            	
            	// report JTable
            
            	String SQL = "select * from reportuserextracost;";
        	//	ResultSet rs;
				try {
					rs = stmt.executeQuery(SQL);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		String[] columnNames = {"FirstName", "LastName", "TypeOfCost", "Specification", "Total", "File"}; 
        		String a = "",b = "", c = "", d = "", eB = "", f = "";
        		
        		
        //		JPanel panelTable = new JPanel(); 
        	
        //		DefaultTableModel model;
       // 		model = new DefaultTableModel(); 
        //	    JTable	jTable1 = new  JTable(model);
                
        		model.addColumn("FirstName");
        		model.addColumn("LastName"); 
        		model.addColumn("TypeOfCost");
        		model.addColumn("Specification"); 
        		model.addColumn("Total");
        		model.addColumn("File");
        		
        //		panelTable.add(jTable1);
        //		add(new JScrollPane(jTable1));  
       // 		panelTable.setVisible(true);
        		
        		try {
					while(rs.next()) { 
				
						
						 a = rs.getString("FirstName");
						    b = rs.getString("LastName"); 
						    c = rs.getString("TypeOfCost"); 
						    d = rs.getString("Specification"); 
						    eB = rs.getString("Total"); 
						    f = rs.getString("File");
						    //Object[][]data={{n,e}};
						    // This will add row from the DB as the last row in the JTable. 
						//    model.insertRow(jTable1.getRowCount(), new Object[] {a, b, c, d, e, f});
						    model.addRow(new Object[]{a,b,c,d,eB,f});

					
					
      	   
         }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
              }               
        
        }
        
        
        
        
       
        
        
        

//     
//        public void creatPage1(){
//                
//                JPanel panel1 = new JPanel();
//                panel1.setLayout( null );
//                
//                
//                
//        }
//        
//        public void creatPage2(){
//                JPanel panel2= new JPanel();
//                panel2.setLayout( new BorderLayout() );
//                
//                panel2.add(new JLabel("create a new travel log"),BorderLayout.CENTER);
//                panel2.add(new JComboBox(),BorderLayout.CENTER);
//                panel2.add(new JComboBox(),BorderLayout.CENTER);
//                panel2.add(new JComboBox(),BorderLayout.CENTER);
//                panel2.add(new JLabel(),BorderLayout.CENTER);
//                panel2.add(new JLabel(),BorderLayout.CENTER);
//                panel2.add(new JLabel("Start Kilometer"),BorderLayout.CENTER);
//                panel2.add(new JTextField(15),BorderLayout.CENTER);
//                panel2.add(new JLabel("End Kilometer"),BorderLayout.CENTER);
//                panel2.add(new JTextField(15),BorderLayout.CENTER);
//                panel2.add(new JButton("Shubmit"),BorderLayout.CENTER);
         } 

/*
 * 
 * 
 * Old code from the previous merge below this comment when merging Danielles
 * code
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * >>>>>>> refs/heads/Elisa
 * 
 * <<<<<<< HEAD import java.awt.event.ActionEvent; import
 * java.awt.event.ActionListener;
 * 
 * import javax.swing.JButton; import javax.swing.JComboBox; import
 * javax.swing.JFrame; import javax.swing.JLabel; import javax.swing.JPanel;
 * import javax.swing.JTabbedPane; import javax.swing.JTextField;
 * 
 * public class TestUiFrame extends JFrame {
 * 
 * // create panels JPanel mainPanel = new JPanel(); JPanel panel1 = new
 * JPanel(); JPanel panel2 = new JPanel(); JPanel panel3 = new JPanel();
 * 
 * // Create a tabbed pane
 * 
 * JTabbedPane tabbedPane = new JTabbedPane(); // Create combo box JComboBox
 * comboBox=new JComboBox();
 * 
 * // create Button
 * 
 * JButton buttonLogout= new JButton("log out");
 * 
 * 
 * public TestUiFrame(){
 * 
 * 
 * // create the position of mine panel mainPanel.setLayout( new BorderLayout()
 * ); getContentPane().add( mainPanel ); mainPanel.setBackground(Color.BLUE);
 * 
 * // create pages // // creatPage1(); // creatPage2(); //
 * 
 * 
 * // add name of each tabb and add tabb to each panels tabbedPane.addTab(
 * "Home", panel1 ); tabbedPane.addTab( "Report", panel2 ); tabbedPane.addTab(
 * "Create", panel3 );
 * 
 * panel1.add( buttonLogout); // mainPanel.add(tabedPan, BorderLayout.CENTER);
 * 
 * // add colors to each panels panel1.setBackground(Color.gray);
 * panel2.setBackground(Color.gray); // panel3.setBackground(Color.YELLOW); //
 * mainPanel.add(panel1); // mainPanel.add(panel2); // mainPanel.add(panel3);
 * 
 * 
 * // define the position for tabb mainPanel.add( tabbedPane,
 * BorderLayout.PAGE_START); // add color to main panel
 * mainPanel.setBackground(Color.gray); // add color to frame but it is not
 * important??????????? setBackground(Color.gray);
 * 
 * // add grid layout and title and size and visibile to frame
 * 
 * setLayout(new GridLayout(1,2)); setTitle("First Project Window");
 * setSize(800, 650); setLocationRelativeTo(null);
 * setDefaultCloseOperation(EXIT_ON_CLOSE); setVisible(true);
 * 
 * 
 * 
 * 
 * }
 * 
 * 
 * // // public void creatPage1(){ // // JPanel panel1 = new JPanel(); //
 * panel1.setLayout( null ); // // // // } // // public void creatPage2(){ //
 * JPanel panel2= new JPanel(); // panel2.setLayout( new BorderLayout() ); // //
 * panel2.add(new JLabel("create a new travel log"),BorderLayout.CENTER); //
 * panel2.add(new JComboBox(),BorderLayout.CENTER); // panel2.add(new
 * JComboBox(),BorderLayout.CENTER); // panel2.add(new
 * JComboBox(),BorderLayout.CENTER); // panel2.add(new
 * JLabel(),BorderLayout.CENTER); // panel2.add(new
 * JLabel(),BorderLayout.CENTER); // panel2.add(new
 * JLabel("Start Kilometer"),BorderLayout.CENTER); // panel2.add(new
 * JTextField(15),BorderLayout.CENTER); // panel2.add(new
 * JLabel("End Kilometer"),BorderLayout.CENTER); // panel2.add(new
 * JTextField(15),BorderLayout.CENTER); // panel2.add(new
 * JButton("Shubmit"),BorderLayout.CENTER); }
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * =======
 */
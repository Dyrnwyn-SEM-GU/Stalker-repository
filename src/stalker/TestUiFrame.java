package stalker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class TestUiFrame extends JFrame {
	
//	create panels
	JPanel mainPanel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	
	// Create a tabbed pane
	
	JTabbedPane tabbedPane = new JTabbedPane();
//	Create combo box
	JComboBox comboBox=new JComboBox();
	
//	create Button
	
	JButton buttonLogout= new JButton("log out");
	
	
	public TestUiFrame(){
		
		
//		 create the position of mine panel
		mainPanel.setLayout( new BorderLayout() );
		getContentPane().add( mainPanel );
		mainPanel.setBackground(Color.BLUE);
		
//	create pages
//		
//		creatPage1();
//		creatPage2();
//		
		
		 
//		 add name of each tabb and add tabb to each panels
			tabbedPane.addTab( "Home", panel1 );
			tabbedPane.addTab( "Report", panel2 );
			tabbedPane.addTab( "Create", panel3 );
			
			panel1.add( buttonLogout);
//			mainPanel.add(tabedPan, BorderLayout.CENTER);
			
//			add colors to each panels
			panel1.setBackground(Color.gray);
			panel2.setBackground(Color.gray);
//			panel3.setBackground(Color.YELLOW);
//			mainPanel.add(panel1);
//			mainPanel.add(panel2);
//			mainPanel.add(panel3);
			
			
//			define the position for tabb
			mainPanel.add( tabbedPane, BorderLayout.PAGE_START);
//			add color to main panel
			mainPanel.setBackground(Color.gray);
//			add color to frame but it is not important???????????
			setBackground(Color.gray);
		
//         add grid layout and title and size and visibile to frame
             
            setLayout(new GridLayout(1,2));
     		setTitle("First Project Window");
     		setSize(800, 650);
     		setLocationRelativeTo(null);
     		setDefaultCloseOperation(EXIT_ON_CLOSE);
     		setVisible(true);
     		
           
       
        
     }
	

//     
//	public void creatPage1(){
//		
//		JPanel panel1 = new JPanel();
//		panel1.setLayout( null );
//		
//		
//		
//	}
//	
//	public void creatPage2(){
//		JPanel panel2= new JPanel();
//		panel2.setLayout( new BorderLayout() );
//		
//		panel2.add(new JLabel("create a new travel log"),BorderLayout.CENTER);
//		panel2.add(new JComboBox(),BorderLayout.CENTER);
//		panel2.add(new JComboBox(),BorderLayout.CENTER);
//		panel2.add(new JComboBox(),BorderLayout.CENTER);
//		panel2.add(new JLabel(),BorderLayout.CENTER);
//		panel2.add(new JLabel(),BorderLayout.CENTER);
//		panel2.add(new JLabel("Start Kilometer"),BorderLayout.CENTER);
//		panel2.add(new JTextField(15),BorderLayout.CENTER);
//		panel2.add(new JLabel("End Kilometer"),BorderLayout.CENTER);
//		panel2.add(new JTextField(15),BorderLayout.CENTER);
//		panel2.add(new JButton("Shubmit"),BorderLayout.CENTER);
	 }


		
		
	

	


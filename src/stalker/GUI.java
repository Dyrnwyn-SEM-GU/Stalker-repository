package stalker;

import java.awt.Color;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class GUI {
	public GUI() {
		// TODO Auto-generated constructor stub
		new WindowFirst();
	}
 
	public class WindowFirst{
		public WindowFirst(){
		
		new panel()
		new FirstPageFrame();
		new AddColor();
		
	}

      class FirstPageFrame extends JFrame {
	    public FirstPageFrame(){
	    

		setTitle("Creat a new travel log");
		setSize(800, 650);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		// Get the container and set the background
		getContentPane().setBackground(Color.LIGHT_GRAY);
//		creat panel
		
		JPanel panel = new JPanel();

	
		
//  	creat menuBars
		
		JMenuBar menu= new JMenuBar();
		
		
		JMenu viewMenuBar= new JMenu("View");
		JMenu creatMenuBar= new JMenu("Creat");
		JMenu logOutMenuBar= new JMenu("log out");
		
	
		
		
		JLabel creatANewTravelLog = new JLabel(" Creat a new travel log ");
	    }
      }
		
		class AddColor extends JPanel{
			public AddColor(){
			setBackground(Color.LIGHT_GRAY);
			
		
		
		
		
		

	
	
		
	}
		
		
	}
	
	

	}
}


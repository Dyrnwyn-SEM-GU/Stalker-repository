package stalker;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;



public class GUI {
	
	public GUI() {
		FirstPageFrame mainFrame = new FirstPageFrame();
		SallyPanel panel = new SallyPanel();
		mainFrame.add(panel);
		
		
	}
	
	class FirstPageFrame extends JFrame {
		public FirstPageFrame(){
			
			setLayout(new GridLayout(2,1));
			setSize(800,650);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			getContentPane().setBackground(Color.DARK_GRAY);
			setVisible(true);

		}
		
		
	}
	
	class SallyPanel extends JPanel{
		public SallyPanel(){
			setBackground(Color.BLUE);
		}
	}

}

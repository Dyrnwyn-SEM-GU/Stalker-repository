package stalker;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;



public class GUI {
	
	public static Color white = new Color(255,255,255);
	public static Color gray = new Color(78,78,78);
	public static Color darkGray = new Color(49,49,49);
	
	public static Font textH1 = new Font("Arial",0,36);
	public static Font textH2 = new Font("Arial",0,24);
	public static Font textH3 = new Font("Arial",0,20);
	public static Font textH4 = new Font("Arial",0,12);
	
	public static GridLayout twobyOne = new GridLayout(2,1);
	public static GridLayout fourbyOne = new GridLayout(4,1);
	
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

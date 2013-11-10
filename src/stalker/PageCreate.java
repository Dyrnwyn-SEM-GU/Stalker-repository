package stalker;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Date;

import javax.sound.sampled.DataLine;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sourceforge.jcalendarbutton.JCalendarButton;


public class PageCreate extends JFrame {
	
//	create jpanel
	JPanel panelCreateMain=new JPanel();
	JPanel panel1=new JPanel();
	JPanel panel2=new JPanel();
	JPanel panel3=new JPanel();
	JPanel panel4=new JPanel();
	JPanel panel5=new JPanel();
	JPanel panel6=new JPanel();
	
	
//	crate jlable
	JLabel create = new JLabel("Create a new travel:");
//	create combo boxes
	JComboBox frame= new JComboBox(new String[]{"From"});
//	JComboBox date = new JComboBox(new String[]{"Date"});
	
	JComboBox to= new JComboBox(new String[]{"To         "});
	JComboBox car= new JComboBox(new String[]{"car        "});
	JCalendarButton date = new JCalendarButton("yyyy/mm/dd", new Date(113, 11, 4));
	JLabel dateLable = new JLabel();
	//	create Jlabales and text filds of each jlables
	JLabel startKilometerLable = new JLabel("Start kilometer:");
	JTextField startKilometerText = new JTextField(20);
	JLabel endKilometerLable = new JLabel("End kilometer:");
	JTextField endKilometerText = new JTextField(20);
//	create submit buttons
	JButton submitButton =new JButton("  submit    ");

	public PageCreate(){
		
		JPanel panelCreateMain= new JPanel();
		panelCreateMain.setLayout( new GridLayout(1,0) );
		panelCreateMain.setBackground(Color.gray);
//		add creating new logo to Jframe and gavecolor to it
		panel1.add(create);
		panel1.setBackground(Color.gray);
		add(panel1);
		
//		add from to panel2 and add panel2 to main panel
		panel2.add(frame);
		
		date.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                if (evt.getNewValue() instanceof Date)
                    dateLable.setText((((Date)evt.getNewValue()).toString()));
            }
        });
		panel2.add(dateLable);
		panel2.add(date);
		panel2.setBackground(Color.GRAY);
		add(panel2);
		
		panel3.add(to);
		panel3.add(car);
		panel3.setBackground(Color.GRAY);
		add(panel3);
		
		panel4.add(startKilometerLable);
		panel4.add(startKilometerText);
		panel4.setBackground(Color.GRAY);
		add(panel4);
		
		panel5.add(endKilometerLable);
		panel5.add(endKilometerText);
		panel5.setBackground(Color.GRAY);
		add(panel5);
		
		panel6.add(submitButton);
		panel6.setBackground(Color.gray);
		add(panel6);
		
		setLayout(new GridLayout(7,1));
		setTitle("seconde Project Window");
		setSize(400, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane().setBackground(Color.gray);
		add(panelCreateMain);
	
		
	}

}

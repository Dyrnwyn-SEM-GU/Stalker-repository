package stalker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
	
	public class Account extends JFrame {
		
		ColorPanel panel, panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8, panel9, panel10,
		panel11, panel12, panel13;
		JLabel email, firstname, lastname, age, gender, occupation, currentpass, typenew, retypenew, phone, interrests;
		JTextField emailtext, firsttext, lasttext, agetext, occupationtxt, currentpasstxt, typenewtxt, retypetxt,
		phonetxt, interresttxt;
		public Account() {
		super ("Edit your account");
		
		setSize(400,200);
		
		Font font = new Font("Verdana", Font.TRUETYPE_FONT, 16);
		Font font1 = new Font("Verdena", Font.BOLD, 18);
		
		panel = new ColorPanel();
		JLabel general = new JLabel("General: ");
		general.setFont(font1);
		general.setForeground(Color.LIGHT_GRAY);
		panel.add(general);
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(13,2));
		
		panel1 = new ColorPanel();
		email = new JLabel("Email: ");
		emailtext = new JTextField(24);
		panel1.add(email);
		panel1.add(emailtext);
		panel.add(panel1);
		email.setFont(font);
		email.setForeground(Color.WHITE);
	
		panel2 = new ColorPanel();
		firstname = new JLabel("First name: ");
		firsttext = new JTextField(20);
		panel2.add(firstname);
		panel2.add(firsttext); 
		panel.add(panel2);
		firstname.setFont(font);
		firstname.setForeground(Color.white);
	
		panel3 = new ColorPanel();
		lastname = new JLabel("Last name: ");
		lasttext = new JTextField(20);
		panel3.add(lastname); panel3.add(lasttext);
		panel.add(panel3);
		lastname.setFont(font);
		lastname.setForeground(Color.white);
		
		panel4 = new ColorPanel();
		age = new JLabel("Age: ");
		agetext = new JTextField(25);
		panel4.add(age); panel4.add(agetext);
		age.setFont(font);
		age.setForeground(Color.white);
		panel.add(panel4);
	
		panel5 = new ColorPanel();
		gender = new JLabel("Gender: ");
		String[] words = new String[] {"Male", "Female"};
        JComboBox selectbox = new JComboBox(words);
        panel5.add(gender, BorderLayout.LINE_START); panel5.add(selectbox);
        panel.add(panel5);
        gender.setFont(font); gender.setForeground(Color.white);
        
        panel6 = new ColorPanel();
        occupation = new JLabel("Occupation: ");
        occupationtxt = new JTextField(20);
        panel6.add(occupation);
        panel6.add(occupationtxt);
        panel.add(panel6);
        occupation.setFont(font);
		occupation.setForeground(Color.white);
		
		panel7 = new ColorPanel();
		phone = new JLabel("Phone number: ");
		phonetxt = new JTextField(18);
		panel7.add(phone, BorderLayout.WEST);
		panel7.add(phonetxt, BorderLayout.CENTER);
		panel.add(panel7);
		phone.setFont(font);
		phone.setForeground(Color.WHITE);
		
		panel8 = new ColorPanel();
		interrests = new JLabel("Interrests: ");
		interresttxt = new JTextField(22);
		panel8.add(interrests); 
		panel8.add(interresttxt);
		interrests.setFont(font);
		interresttxt.setPreferredSize(new Dimension(25,60));
		interrests.setForeground(Color.white);
		panel.add(panel8);
		
		JPanel panel9 = new JPanel(new GridLayout(1,2));
		JLabel privacy = new JLabel("Privacy: ");
		privacy.setForeground(Color.LIGHT_GRAY);
		privacy.setFont(font1);
		panel9.add(privacy);
		panel.add(panel9);
		panel9.setBackground(Color.DARK_GRAY);
		
		panel10 = new ColorPanel();
		currentpass = new JLabel("Current Password: ");
		currentpasstxt = new JTextField(17);
		currentpass.setFont(font);
		currentpass.setForeground(Color.WHITE);
		panel10.add(currentpass);
		panel10.add(currentpasstxt);
		panel.add(panel10);
		
		panel11 = new ColorPanel();
		typenew = new JLabel("Type a new password: ");
		typenewtxt = new JTextField(14);
		typenew.setFont(font);
		typenew.setForeground(Color.white);
		panel11.add(typenew);
		panel11.add(typenewtxt);
		panel.add(panel11);
		
		panel12 = new ColorPanel();
		retypenew = new JLabel("Retype a new password: ");
		retypetxt = new JTextField(13);
		retypenew.setFont(font);
		retypenew.setForeground(Color.white);
		panel12.add(retypenew);
		panel12.add(retypetxt);
		panel.add(panel12);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.DARK_GRAY);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
		}
		
		class ColorPanel extends JPanel {
			public ColorPanel() {
				super();
				setBackground(Color.DARK_GRAY);
			}
		}
	}

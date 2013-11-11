package stalker;

import java.awt.*;
import javax.swing.*;

public class Login extends JFrame {
	
	public static Color white = new Color(255,255,255);
	public static Color gray = new Color(78,78,78);
	public static Color darkGray = new Color(49,49,49);
	
	public static Font textH1 = new Font("Arial",0,36);
	public static Font textH2 = new Font("Arial",0,24);
	public static Font textH3 = new Font("Arial",0,20);
	public static Font textH4 = new Font("Arial",0,12);
	
	public static GridLayout twobyOne = new GridLayout(2,1);
	public static GridLayout fourbyOne = new GridLayout(4,1);
	
	JPanel back = new JPanel();
	JPanel cont = new JPanel();
	
	JPanel empt1 = new JPanel();
	JPanel empt2 = new JPanel();
	JPanel buttons = new JPanel();
	JPanel lgPanel = new JPanel();
	JPanel crAccPanel = new JPanel();
	
	JTextPane signIn = new JTextPane();
	
	JTextField inpEmail = new JTextField();
	JTextPane email = new JTextPane();
	JPasswordField inpPassword = new JPasswordField();
	JTextPane password = new JTextPane();
	
	JButton login = new JButton("Login");
	JButton cAccount = new JButton("Create account");

	public Login() {
		
		content();
		pack();
		setSize(600,450);
		setTitle("Sign in");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
	}
	
	void setText(){
		
		signIn.setText("Sign in");
		email.setText("Email");
		password.setText("Password");
		
		/* this part could be solved in a separated
		 * class for DTextField and DPasswordField
		 */
		
		inpEmail.setFont(textH2);
		inpEmail.setForeground(gray);
		inpPassword.setFont(textH2);
		inpPassword.setForeground(gray);
		
		/* this part could be solved in a separated
		 * class for DButtons
		 */
		
		login.setFont(textH2);
		login.setForeground(white);
		login.setBackground(darkGray);
		
		cAccount.setFont(textH2);
		cAccount.setForeground(white);
		cAccount.setBackground(darkGray);
		
		/* this part could be solved in a separated
		 * class for DJTextPane
		 */
		
		signIn.setEnabled(false);
		signIn.setDisabledTextColor(white);
		signIn.setFont(textH1);
		signIn.setBackground(gray);
		
		email.setEnabled(false);
		email.setDisabledTextColor(white);
		email.setFont(textH2);
		email.setBackground(gray);
		
		password.setEnabled(false);
		password.setDisabledTextColor(white);
		password.setFont(textH2);
		password.setBackground(gray);
	}
	
	void content(){
		
		setText();
		
		/* this part could be solved in a separated
		 * class for DPanels
		 */
		back.setBackground(gray);
		
		cont.setBackground(gray);
		empt1.setBackground(gray);
		empt2.setBackground(gray);
		
		buttons.setBackground(gray);
		lgPanel.setBackground(gray);
		crAccPanel.setBackground(gray);
		
		/*  */
		
		cont.setPreferredSize(new Dimension(500, 420));
		
		cont.setLayout(new GridLayout(9,1));
		buttons.setLayout(new GridLayout(1,2));
		
		
		
		cont.add(empt1);
		cont.add(signIn);
		cont.add(empt2);
		
		cont.add(inpEmail);
		cont.add(email);
		
		cont.add(inpPassword);
		cont.add(password);
		
		lgPanel.add(login);
		crAccPanel.add(cAccount);
		
		buttons.add(lgPanel);
		buttons.add(crAccPanel);
		
		cont.add(buttons);
		
		back.add(cont, BorderLayout.CENTER);
		add(back, BorderLayout.CENTER);
		
	}

}

	


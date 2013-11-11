package stalker;

import java.awt.*;
import javax.swing.*;

public class Login extends JFrame {
	
	public Color white = new Color(255,255,255);
	public Color gray = new Color(78,78,78);
	public Color darkGray = new Color(49,49,49);
	
	public Font textH1 = new Font("Arial",0,36);
	public Font textH2 = new Font("Arial",0,24);
	public Font textH3 = new Font("Arial",0,20);
	public Font textH4 = new Font("Arial",0,12);
	
	JPanel back = new JPanel();
	JPanel content = new JPanel();
	
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
		
		content.setBackground(gray);
		empt1.setBackground(gray);
		empt2.setBackground(gray);
		
		buttons.setBackground(gray);
		lgPanel.setBackground(gray);
		crAccPanel.setBackground(gray);
		
		/*  */
		
		content.setPreferredSize(new Dimension(500, 420));
		
		content.setLayout(new GridLayout(9,1));
		buttons.setLayout(new GridLayout(1,2));
		
		
		
		content.add(empt1);
		content.add(signIn);
		content.add(empt2);
		
		content.add(inpEmail);
		content.add(email);
		
		content.add(inpPassword);
		content.add(password);
		
		lgPanel.add(login);
		crAccPanel.add(cAccount);
		
		buttons.add(lgPanel);
		buttons.add(crAccPanel);
		
		content.add(buttons);
		
		back.add(content, BorderLayout.CENTER);
		add(back, BorderLayout.CENTER);
		
	}

}

	


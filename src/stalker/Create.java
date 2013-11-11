package stalker;

import java.awt.*;
import javax.swing.*;

public class Create extends JFrame {
	
	JPanel back = new JPanel();
	JPanel cont = new JPanel();
	JPanel cont2 = new JPanel();
	
	JPanel top = new JPanel();
	JPanel center = new JPanel();
	JPanel center2 = new JPanel();
	JPanel bottom = new JPanel();
	
	JPanel buttons = new JPanel();
	JPanel empty = new JPanel();
	JPanel empty2 = new JPanel();
	
	JTextPane crtAccount = new JTextPane();
	JTextField inpEmail = new JTextField();
	JTextPane email = new JTextPane();
	JPasswordField inpPassword = new JPasswordField();
	JTextPane password = new JTextPane();
	JPasswordField repeatPassword = new JPasswordField();
	JTextPane password2 = new JTextPane();

	JButton submit = new JButton("Submit");
	JButton cancel = new JButton("Cancel");

	public Create() {
		
		content();
		pack();
		setSize(800,600);
		setTitle("Create Account");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	
	void setText(){
		
		crtAccount.setText("Create new account");
		email.setText("Email");
		password.setText("Password");
		password2.setText("Repeat password");
		
		/* this part could be solved in a separated
		 * class for DTextField and DPasswordField
		 */
		
		inpEmail.setFont(Login.textH2);
		inpEmail.setForeground(Login.gray);
		inpPassword.setFont(Login.textH2);
		inpPassword.setForeground(Login.gray);
		repeatPassword.setFont(Login.textH2);
		repeatPassword.setForeground(Login.gray);
		
		/* this part could be solved in a separated
		 * class for JTextPane
		 */
		
		crtAccount.setEnabled(false);
		crtAccount.setDisabledTextColor(Login.white);
		crtAccount.setFont(Login.textH1);
		crtAccount.setBackground(Login.gray);
		
		email.setEnabled(false);
		email.setDisabledTextColor(Login.white);
		email.setFont(Login.textH2);
		email.setBackground(Login.gray);
		
		password.setEnabled(false);
		password.setDisabledTextColor(Login.white);
		password.setFont(Login.textH2);
		password.setBackground(Login.gray);
		
		password2.setEnabled(false);
		password2.setDisabledTextColor(Login.white);
		password2.setFont(Login.textH2);
		password2.setBackground(Login.gray);
		
		/* this part could be solved in a separated
		 * class for DButtons
		 */
		
		submit.setFont(Login.textH2);
		submit.setForeground(Login.white);
		submit.setBackground(Login.darkGray);
		
		cancel.setFont(Login.textH2);
		cancel.setForeground(Login.white);
		cancel.setBackground(Login.darkGray);
	}
	
	void content(){
		
		setText();
		
		cont.setPreferredSize(new Dimension(600,560));
		cont2.setPreferredSize(new Dimension(500,560));
		
		/* this part could be solved in a separated
		 * class for DPanels
		 */
		
		back.setBackground(Login.darkGray);
		cont.setBackground(Login.gray);
		top.setBackground(Login.gray);
		center.setBackground(Login.gray);
		center2.setBackground(Login.gray);
		bottom.setBackground(Login.gray);
		empty.setBackground(Login.gray);
		empty2.setBackground(Login.gray);
		buttons.setBackground(Login.gray);
		
		/* */
		
		top.setLayout(Login.twobyOne);
		center.setLayout(Login.fourbyOne);
		center2.setLayout(Login.fourbyOne);
		bottom.setLayout(Login.twobyOne);
		cont2.setLayout(Login.fourbyOne);
		
		center.add(inpEmail);
		center.add(email);
		
		center2.add(inpPassword);
		center2.add(password);
		center2.add(repeatPassword);
		center2.add(password2);
		
		top.add(crtAccount);
		top.add(empty);
		
		buttons.add(submit);
		buttons.add(cancel);
		bottom.add(empty2);
		bottom.add(buttons);
		
		cont2.add(top);
		cont2.add(center);
		cont2.add(center2);
		cont2.add(bottom);
		
		cont.add(cont2, BorderLayout.CENTER);
		back.add(cont, BorderLayout.CENTER);
	
		add(back);
	
	}
}


	
	
	
	
		
		
	
	
	
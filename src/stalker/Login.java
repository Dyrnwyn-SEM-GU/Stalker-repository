package stalker;

import java.awt.*;
import javax.swing.*;

public class Login extends JFrame {
	
	DPanel back = new DPanel(GUI.gray);
	DPanel cont = new DPanel(GUI.gray);
	
	DPanel empt1 = new DPanel(GUI.gray);
	DPanel empt2 = new DPanel(GUI.gray);
	DPanel buttons = new DPanel(GUI.gray);
	DPanel lgPanel = new DPanel(GUI.gray);
	DPanel crAccPanel = new DPanel(GUI.gray);
	
	DTextPane signIn = new DTextPane(GUI.white, GUI.textH1, GUI.gray);
	
	DTextField inpEmail = new DTextField(GUI.gray, GUI.textH2);
	DTextPane email = new DTextPane(GUI.white, GUI.textH2, GUI.gray);
	JPasswordField inpPassword = new JPasswordField();
	DTextPane password = new DTextPane(GUI.white, GUI.textH2, GUI.gray);
	
	DButton login = new DButton("Login", GUI.white, GUI.textH2, GUI.darkGray);
	DButton cAccount = new DButton("Create account", GUI.white, GUI.textH2, GUI.darkGray);

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
		
		inpPassword.setForeground(GUI.gray);
		inpPassword.setFont(GUI.textH2);
		
	}
	
	void content(){
		
		setText();
		
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

	


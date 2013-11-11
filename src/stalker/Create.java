package stalker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Create extends JFrame implements ActionListener {

	DPanel back = new DPanel(GUI.darkGray);
	DPanel cont = new DPanel(GUI.gray);
	DPanel cont2 = new DPanel(GUI.gray);

	DPanel top = new DPanel(GUI.gray);
	DPanel center = new DPanel(GUI.gray);
	DPanel center2 = new DPanel(GUI.gray);
	DPanel bottom = new DPanel(GUI.gray);

	DPanel buttons = new DPanel(GUI.gray);
	DPanel empty = new DPanel(GUI.gray);
	DPanel empty2 = new DPanel(GUI.gray);

	DTextPane crtAccount = new DTextPane(GUI.white, GUI.textH1, GUI.gray);
	DTextField inpEmail = new DTextField(GUI.gray, GUI.textH2);
	DTextPane email = new DTextPane(GUI.white, GUI.textH2, GUI.gray);
	JPasswordField inpPassword = new JPasswordField();
	DTextPane password = new DTextPane(GUI.white, GUI.textH2, GUI.gray);
	JPasswordField repeatPassword = new JPasswordField();
	DTextPane password2 = new DTextPane(GUI.white, GUI.textH2, GUI.gray);

	DButton submit = new DButton("Submit", GUI.white, GUI.textH2, GUI.darkGray);
	DButton cancel = new DButton("Cancel", GUI.white, GUI.textH2, GUI.darkGray);

	public Create() {

		content();
		pack();
		setSize(800, 600);
		setTitle("Create Account");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	void setUp() {

		crtAccount.setText("Create new account");
		email.setText("Email");
		password.setText("Password");
		password2.setText("Repeat password");

		inpPassword.setFont(GUI.textH2);
		inpPassword.setForeground(GUI.gray);
		repeatPassword.setFont(GUI.textH2);
		repeatPassword.setForeground(GUI.gray);
		
		submit.addActionListener(this);
		cancel.addActionListener(this);

	}

	void content() {

		setUp();

		cont.setPreferredSize(new Dimension(600, 560));
		cont2.setPreferredSize(new Dimension(500, 560));

		top.setLayout(GUI.twobyOne);
		center.setLayout(GUI.fourbyOne);
		center2.setLayout(GUI.fourbyOne);
		bottom.setLayout(GUI.twobyOne);
		cont2.setLayout(GUI.fourbyOne);

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

    public void actionPerformed(ActionEvent e) {
//            numClicks++;
//            text.setText("Button Clicked " + numClicks + " times");
    }
    
    public void closeWindow(WindowEvent e) {
        dispose();
        System.exit(0);
}
}

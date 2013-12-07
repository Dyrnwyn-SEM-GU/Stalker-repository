package delements;

import java.awt.*;

import javax.swing.*;

public class DButton extends JButton {
	public DButton(String str, Color txtCol, Font txt, Color backgrnd) {

		super(str);
		setForeground(txtCol);
		setFont(txt);
		setBackground(backgrnd);
	}
}

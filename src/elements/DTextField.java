package elements;

import java.awt.*;

import javax.swing.*;

public class DTextField extends JTextField {
	public DTextField(String str, int size, Color txtCol, Font txt) {
		super(str, size);
		setForeground(txtCol);
		setFont(txt);
	}
	
	public DTextField(int size, Color txtCol, Font txt) {
		super(size);
		setForeground(txtCol);
		setFont(txt);
	}
}

package elements;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;

public class DComboBox extends JComboBox {
	public DComboBox(String[] str, Color txtCol, Font txt, Color backgrnd) {
		super(str);
		setForeground(txtCol);
		setFont(txt);
		setBackground(backgrnd);
	}
}

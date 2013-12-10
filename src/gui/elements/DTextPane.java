package gui.elements;

import java.awt.*;

import javax.swing.*;

public class DTextPane extends JTextPane {
	public DTextPane(Color txtCol, Font txt, Color backgrnd) {

		setEnabled(false);
		setDisabledTextColor(txtCol);
		setFont(txt);
		setBackground(backgrnd);
	}
}

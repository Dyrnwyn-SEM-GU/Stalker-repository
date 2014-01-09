package elements;

import java.awt.*;
import javax.swing.*;

public class DLabel extends JLabel {
	public DLabel(String str, Color txtCol, Font txt) {

		super(str);
		setForeground(txtCol);
		setFont(txt);
	}
}

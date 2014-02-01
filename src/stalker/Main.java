package stalker;

import gui.GUI;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;

/* main Class, contains main methods and not Database related methods
 * by AURELIEN */

public class Main {
	public static String path = "";
	public static int pancakes = 0;
	
	public static void main(String[] args) throws SQLException {
		new GUI();
	}

	/* from stackOverflow, method to open a link */
	public static void open(java.net.URI uri) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/* methods to check input, by Aure */
	public static boolean checkString(String input) {
		int counter = 0;
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (Character.isAlphabetic(c)) {
				counter++;
			}
		}
		if (counter == input.length()) {
			return true;
		} else { return false;}
	}

	public static boolean checkNumber(String input) {
		int counter = 0;
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (Character.isDigit(c)) {
				counter++;
			}
		}
		if (counter == input.length()) {
			return true;
		} else { return false;}
	}

	public static String makeDecimal(String input) {
		int counter = 0;
		int digits = 0;
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (Character.isDigit(c))
				digits++;
			if (c == '.')
				counter++;
		}
		if (counter == 0 && digits == input.length()) {
			input = input + ".00";
			return input;
		} else if (counter == 1 && digits == input.length() - 1) {
			return input;
		} else {
			return null;
		}
	}
	
	/* uploads pictures or files by DANIELLE,
	 * redesigned by AURE */

	public static void uploadPic() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.showOpenDialog(null);
		File selectedFile = fileChooser.getSelectedFile();
		path = selectedFile.getAbsolutePath();
	}
	
	/* transforms an ArrayList to a string, by Aure */
	public static String stringify(ArrayList<String> ar) {
		String listString = "";
		for (String s : ar) {
			if (s != "\n") {
				listString += s + ", ";
			} else {
				listString += s;
			}
		}
		return listString;
	}
}

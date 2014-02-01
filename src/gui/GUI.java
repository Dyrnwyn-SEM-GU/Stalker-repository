package gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import elements.*;

/* the User interface is set together here, by Aure */

public class GUI extends JFrame {

	/* Fonts and the Colors used throughout the project are defined here */
	public static Color white = new Color(255, 255, 255);
	public static Color gray = new Color(78, 78, 78);
	public static Color darkGray = new Color(49, 49, 49);
	public static Color darkerGray = new Color(35, 35, 35);
	public static Font txtH1 = new Font("Arial", 0, 36);
	public static Font txtH2 = new Font("Arial", 0, 24);
	public static Font txtH3 = new Font("Arial", 0, 20);
	public static Font txtH4 = new Font("Arial", 0, 14);

	public static DPanel home;
	public static DPanel create;
	public static DPanel report;
	public static JTabbedPane tabPane;

	public static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static DefaultTableModel model;

	public static String username;

	public GUI() throws SQLException {

		home = new DPanel(gray);
		home.setLayout(null);

		report = new DPanel(gray);
		report.setLayout(null);

		create = new DPanel(gray);
		create.setLayout(null);

		new LoginScreen();
		new HomeScreen();
		new CreateLogScreen();
		new ReportScreen();

		tabPane = new JTabbedPane();
		tabPane.setFont(txtH2);
		tabPane.addTab("Home", home);
		tabPane.addTab("Report", report);
		tabPane.addTab("Create", create);
		tabPane.setEnabled(false);
		add(tabPane);

		setTitle("STALKER");
		setSize(900, 650);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	/*
	 * this methods display an uploaded picture by DANIELLE, redesigned by
	 * AURELIEN
	 */
	public static ImageIcon pic(File file) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(file);
		ImageIcon imageIcon = new ImageIcon(bufferedImage);
		return imageIcon;
	}

}

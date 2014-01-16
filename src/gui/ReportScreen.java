package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jbundle.thin.base.screen.jcalendarbutton.JCalendarButton;

import stalker.DatabaseConnector;

import elements.DButton;
import elements.DLabel;
import elements.DPanel;

/* Generates the Screen to view reports, by MINA		 *
 * added calendars and ActionListeners, redesign by AURE */

public class ReportScreen implements ActionListener {

	DPanel filterPanel, gridPanel, tablePanel;
	public static DLabel dateLabel1;
	public static DLabel dateLabel2;
	DLabel toDateLabel, fromDateLabel, viewLogLabel;
	DButton searchButton, exportButton, saveChangesButton, editButton,
			filterButton, clearButton;
	static JTable reportTable;

	ReportScreen() throws SQLException {

		DatabaseConnector dc = new DatabaseConnector();

		JCalendarButton date2 = new JCalendarButton();
		date2.setBounds(100, 180, 40, 40);
		date2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				if (evt.getNewValue() instanceof Date)
					dateLabel1.setText(GUI.dateFormat.format(((Date) (evt
							.getNewValue()))));
			}
		});
		JCalendarButton date3 = new JCalendarButton();
		date3.setBounds(350, 180, 40, 40);
		date3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				if (evt.getNewValue() instanceof Date)
					dateLabel2.setText(GUI.dateFormat.format(((Date) (evt
							.getNewValue()))));
			}
		});

		filterPanel = new DPanel(GUI.darkGray);
		filterPanel.setLayout(null);
		filterPanel.setBounds(100, 0, 680, 600);
	
		viewLogLabel = new DLabel("Search logs", GUI.white,
				GUI.txtH1);
		viewLogLabel.setBounds(50, 40, 600, 40);

		fromDateLabel = new DLabel("From date:", GUI.white, GUI.txtH3);
		fromDateLabel.setBounds(160, 140, 160, 22);
		filterPanel.add(fromDateLabel);

		filterPanel.add(date2);
		filterPanel.add(date3);

		toDateLabel = new DLabel("To date:", GUI.white, GUI.txtH3);
		toDateLabel.setBounds(410, 140, 94, 22);
		filterPanel.add(toDateLabel);
		dateLabel1 = new DLabel("", GUI.white, GUI.txtH3);
		dateLabel1.setBounds(160, 180, 300, 40);
		dateLabel2 = new DLabel("", GUI.white, GUI.txtH3);
		dateLabel2.setBounds(410, 180, 300, 40);

		clearButton = new DButton("Clear", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		clearButton.addActionListener(this);
		clearButton.setBounds(221, 300, 225, 40);
		searchButton = new DButton("Search", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		searchButton.addActionListener(this);
		searchButton.setBounds(221, 370, 225, 40);
		filterPanel.add(clearButton);
		filterPanel.add(searchButton);
		filterPanel.add(dateLabel1);
		filterPanel.add(dateLabel2);
		filterPanel.add(viewLogLabel);

		GUI.report.add(filterPanel);

		/*-------------------------------------------*/

		gridPanel = new DPanel(GUI.darkGray);
		gridPanel.setLayout(null);
		gridPanel.setBounds(100, 0, 680, 600);
		gridPanel.setVisible(false);

		/* Add export button, save button, edit button (by Mahsa) */

		exportButton = new DButton("Export csv", GUI.white, GUI.txtH3,
				GUI.darkerGray);
		exportButton.addActionListener(this);
		exportButton.setBounds(460, 25, 180, 30);
		gridPanel.add(exportButton);

		saveChangesButton = new DButton("Save change", GUI.white, GUI.txtH3,
				GUI.darkerGray);
		saveChangesButton.addActionListener(this);
		saveChangesButton.setBounds(250, 25, 180, 30);
		gridPanel.add(saveChangesButton);

		editButton = new DButton("Edit", GUI.white, GUI.txtH3, GUI.darkerGray);
		editButton.addActionListener(this);
		editButton.setBounds(40, 25, 180, 30);
		gridPanel.add(editButton);

		filterButton = new DButton("Filter", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		filterButton.addActionListener(this);
		filterButton.setBounds(225, 500, 225, 40);
		gridPanel.add(filterButton);

		tablePanel = new DPanel(GUI.gray);

		GUI.model = new DefaultTableModel();
		reportTable = new JTable();
		reportTable.setFont(GUI.txtH4);
		reportTable.setForeground(GUI.darkGray);
		reportTable.setBounds(0, 0, 500, 500);
		reportTable.setEnabled(false);
		
		GUI.model = dc.reportTable(GUI.model);
		ReportScreen.reportTable.setModel(GUI.model);
		GUI.model.fireTableDataChanged();
		reportTable.setModel(GUI.model);
		GUI.model.fireTableDataChanged();
		
		tablePanel.setBounds(40, 75, 600, 400);
		tablePanel.setLayout(new GridLayout(1, 1));
		JScrollPane jsp = new JScrollPane(reportTable);
		tablePanel.add(jsp);

		gridPanel.add(tablePanel);
		GUI.report.add(gridPanel);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == exportButton) {
			JFileChooser chooser = new JFileChooser();
			chooser.setSelectedFile(new File("export.csv"));
			chooser.showSaveDialog(null);
			String path = chooser.getSelectedFile().getAbsolutePath();
			try {
				DatabaseConnector dc = new DatabaseConnector();
				dc.exportCSV(path, GUI.username);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
		}
		if (ae.getSource() == editButton) {
			reportTable.setEnabled(true);
		}
		if (ae.getSource() == clearButton) {
			dateLabel1.setText("");
			dateLabel2.setText("");
		}
		if (ae.getSource() == searchButton) {
			/* modified by Jani */
			try {
				DatabaseConnector dc = new DatabaseConnector();
				
				String fromDate = dateLabel1.getText().toString();
				String toDate = dateLabel2.getText().toString();
				
				if(fromDate.equals("") || toDate.equals("")){
					GUI.model = dc.reportTable(GUI.model);
					GUI.model.fireTableDataChanged();
					gridPanel.setVisible(true);
					filterPanel.setVisible(false);
				}else if (toDate.compareTo(fromDate) <= 0) {
					JOptionPane.showMessageDialog(GUI.home, "Start date is before end date!", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					GUI.model = dc.reportTable(GUI.model, fromDate, toDate);
					GUI.model.fireTableDataChanged();
					gridPanel.setVisible(true);
					filterPanel.setVisible(false);
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		if (ae.getSource() == filterButton) {
			gridPanel.setVisible(false);
			filterPanel.setVisible(true);
		}

		if (ae.getSource() == saveChangesButton) {
			int[] rowChanged = reportTable.getSelectedRows();
			int[] columnChanged = reportTable.getSelectedColumns();
			
			reportTable.setEnabled(false);

			for (int i = 0; i < rowChanged.length; i++) {
				for (int j = 0; j < columnChanged.length; j++) {
//					System.out.println(rowChanged[i]);
//					System.out.println(columnChanged[j]);
//					System.out.println(reportTable.getValueAt(rowChanged[i],
//							columnChanged[j]));
				}
			}
		}
	}
}

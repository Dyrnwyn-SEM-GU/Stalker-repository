package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JFileChooser;
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

	DPanel filterPane, grid, tablePanel;
	DLabel dateLabel1, dateLabel2, lblToDate;
	DButton searchButton, exportButton, saveChangesButton, editButton,
			filterButton;
	static JTable reportTable;
	static DefaultTableModel model;
	String username;

	ReportScreen() throws SQLException {

		DatabaseConnector dc = new DatabaseConnector();

		JCalendarButton date2 = new JCalendarButton();
		date2.setBounds(260, 127, 40, 40);
		date2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				if (evt.getNewValue() instanceof Date)
					dateLabel1.setText(GUI.dateFormat.format(((Date) (evt
							.getNewValue()))));
			}
		});
		JCalendarButton date3 = new JCalendarButton();
		date3.setBounds(490, 127, 40, 40);
		date3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				if (evt.getNewValue() instanceof Date)
					dateLabel2.setText(GUI.dateFormat.format(((Date) (evt
							.getNewValue()))));
			}
		});

		filterPane = new DPanel(GUI.darkGray);
		filterPane.setLayout(null);
		filterPane.setBounds(100, 0, 680, 600);
		filterPane.add(date2);

		DLabel lblFromDate = new DLabel("From date:", GUI.white, GUI.txtH3);
		lblFromDate.setBounds(110, 90, 160, 22);
		filterPane.add(lblFromDate);

		filterPane.add(date3);

		lblToDate = new DLabel("To date:", GUI.white, GUI.txtH3);
		lblToDate.setBounds(369, 90, 94, 22);
		filterPane.add(lblToDate);
		dateLabel1 = new DLabel("", GUI.white, GUI.txtH3);
		dateLabel1.setBounds(110, 130, 300, 40);
		dateLabel2 = new DLabel("", GUI.white, GUI.txtH3);
		dateLabel2.setBounds(360, 130, 300, 40);

		searchButton = new DButton("Search", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		searchButton.addActionListener(this);
		searchButton.setBounds(221, 370, 225, 28);
		filterPane.add(searchButton);
		filterPane.add(dateLabel1);
		filterPane.add(dateLabel2);

		GUI.report.add(filterPane);

		/*-------------------------------------------*/

		grid = new DPanel(GUI.darkGray);
		grid.setLayout(null);
		grid.setBounds(100, 0, 680, 600);
		grid.setVisible(false);

		/* Add export button, save button, edit button (by mahsa) */

		exportButton = new DButton("Export csv", GUI.white, GUI.txtH3,
				GUI.darkerGray);
		exportButton.addActionListener(this);
		exportButton.setBounds(460, 25, 180, 30);
		grid.add(exportButton);

		saveChangesButton = new DButton("save change", GUI.white, GUI.txtH3,
				GUI.darkerGray);
		saveChangesButton.addActionListener(this);
		saveChangesButton.setBounds(250, 25, 180, 30);
		grid.add(saveChangesButton);

		editButton = new DButton("Edit", GUI.white, GUI.txtH3, GUI.darkerGray);
		editButton.addActionListener(this);
		editButton.setBounds(40, 25, 180, 30);
		grid.add(editButton);

		filterButton = new DButton("Filter", GUI.white, GUI.txtH2,
				GUI.darkerGray);
		filterButton.addActionListener(this);
		filterButton.setBounds(225, 500, 225, 30);
		grid.add(filterButton);

		tablePanel = new DPanel(GUI.gray);

		model = new DefaultTableModel();
		reportTable = new JTable();
		reportTable.setFont(GUI.txtH4);
		reportTable.setForeground(GUI.darkGray);
		reportTable.setBounds(0, 0, 500, 500);
		reportTable.setEnabled(false);
		
		String fromDate = dateLabel1.getText();
		String toDate = dateLabel2.getText();

		ReportScreen.model = dc.reportTable(ReportScreen.model, fromDate, toDate);
		ReportScreen.reportTable.setModel(ReportScreen.model);
		ReportScreen.model.fireTableDataChanged();
		reportTable.setModel(model);
		ReportScreen.model.fireTableDataChanged();
		
		tablePanel.setBounds(40, 75, 600, 400);
		tablePanel.setLayout(new GridLayout(1, 1));
		JScrollPane jsp = new JScrollPane(reportTable);
		tablePanel.add(jsp);

		grid.add(tablePanel);
		GUI.report.add(grid);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == exportButton) {
			JFileChooser chooser = new JFileChooser();
			chooser.setSelectedFile(new File("export.csv"));
			chooser.showSaveDialog(null);
			String path = chooser.getSelectedFile().getAbsolutePath();
			String fromDate = dateLabel1.getText();
			String toDate = dateLabel2.getText();
			
			try {
				DatabaseConnector dc = new DatabaseConnector();
				dc.exportCSV(path, username, fromDate, toDate);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
		}
		if (ae.getSource() == editButton) {
			reportTable.setEnabled(true);
		}
		if (ae.getSource() == searchButton) {
			/* modified by Jani */
			try {
				DatabaseConnector dc = new DatabaseConnector();
				String fromDate = dateLabel1.getText();
				String toDate = dateLabel2.getText();
				
				
				model = dc.reportTable(model, fromDate, toDate);
				grid.setVisible(true);
				filterPane.setVisible(false);
				model.fireTableDataChanged();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		if (ae.getSource() == filterButton) {
			grid.setVisible(false);
			filterPane.setVisible(true);
		}
		if (ae.getSource() == saveChangesButton) {
			int[] rowChanged = reportTable.getSelectedRows();
			int[] columnChanged = reportTable.getSelectedColumns();

			for (int i = 0; i < rowChanged.length; i++) {
				for (int j = 0; j < columnChanged.length; j++) {
					System.out.println(rowChanged[i]);
					System.out.println(columnChanged[j]);
					System.out.println(reportTable.getValueAt(rowChanged[i],
							columnChanged[j]));
				}
			}
		}
	}
}

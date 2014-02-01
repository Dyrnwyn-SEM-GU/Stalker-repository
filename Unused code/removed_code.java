import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import stalker.DatabaseConnector;


public class stuff {
	/* Add mouselistener, by Danielle */
	MouseAdapter mouseAdapter = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			try {
				final DatabaseConnector dc = new DatabaseConnector();
				if (e.getClickCount() == 1) {
					int row = reportTable.getSelectedRow();
					int column = reportTable.getSelectedColumn();
					String colName = reportTable.getColumnName(column);

					if (colName.equalsIgnoreCase("File")) {
						final String value = (String) reportTable.getValueAt(row, 5);
						JLabel label = new JLabel() {
							protected void paintComponent(Graphics g) {
								Graphics graph = g.create();
								try {
									graph.drawImage(dc.queryImage(value), 0, 0, 600, 300, null);
								} catch (SQLException e) {
									e.printStackTrace();
								}
								graph.dispose();
							}
							public Dimension getPreferredSize() {
								// return new
								// Dimension(bufferedImage.getWidth(),
								// bufferedImage.getHeight());
								return new Dimension(600, 300);
							}
						};
						JDialog pictureDialog = new JDialog();
						pictureDialog.setLocationRelativeTo(null);
						pictureDialog.add(label);
						pictureDialog.pack();
						pictureDialog.setVisible(true);
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	};

	public void insertImage() throws SQLException, IOException {
		String insertFile = "INSERT INTO ExtraCosts(File) VALUES (?)";
		con.setAutoCommit(false);
		try {
			FileInputStream fileInputStream = new FileInputStream(selectedFile);
			PreparedStatement pstmt = con.prepareStatement(insertFile);
			pstmt.setBinaryStream(1, fileInputStream,
					(int) selectedFile.length());
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			System.out.print(e);
		}
	}
	

	/* shows the uploaded picture, by Danielle */
	public void showPicture() throws IOException {
		final BufferedImage bufferedImage = ImageIO.read(selectedFile);

		JLabel label = new JLabel() {
			protected void paintComponent(Graphics g) {
				Graphics graph = g.create();
				graph.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(),
						null);
				graph.dispose();
			}

			public Dimension getPreferredSize() {
				return new Dimension(bufferedImage.getWidth(),
						bufferedImage.getHeight());
			}
		};
		JFrame frame = new JFrame("Your selected picture: ");
		frame.add(label);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}


	/* *
		 * Database connection to enable secure remote database connection and
		 * forcing usage of SSL certificate
		 * 
		 * By Jani
		 */

		try {
		String url = "jdbc:mysql://dyrnwyn.dyndns.org/StalkerDB" +		
				"?verifyServerCertificate=false"+ 
				"&useSSL=true"+ 
				"&requireSSL=true";
				
		String user = "Dyrnwyn";
		String password = "Dyrnwyn!";
		
		Class dbDriver = Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
				}
			}
		}	
		stmt = con.createStatement();
	}


package stalker;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class ProGUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProGUI window = new ProGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane overalPane = new JLayeredPane();
		overalPane.setBackground(Color.WHITE);
		frame.getContentPane().add(overalPane, BorderLayout.CENTER);
		
		textField = new JTextField("dd/mm/yy");
		textField.setBounds(249, 44, 116, 22);
		overalPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblFrom = new JLabel("From date:");
		lblFrom.setBounds(183, 47, 70, 16);
		overalPane.add(lblFrom);
		
		textField_1 = new JTextField("dd/mm/yy");
		textField_1.setBounds(436, 44, 116, 22);
		overalPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTo = new JLabel("To date:");
		lblTo.setBounds(385, 47, 56, 16);
		overalPane.add(lblTo);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(249, 106, 119, 26);
		overalPane.add(menuBar);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(261, 187, 119, 26);
		overalPane.add(menuBar_1);
		
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

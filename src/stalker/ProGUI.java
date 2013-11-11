package stalker;
import java.awt.*;
import javax.swing.*;

public class ProGUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	
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

	public ProGUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 570, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		textField = new JTextField("dd/mm/yy");
		textField.setBounds(221, 27, 114, 28);
		layeredPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblFromDate = new JLabel("From date:");
		lblFromDate.setBounds(145, 30, 94, 22);
		layeredPane.add(lblFromDate);
		
		textField_1 = new JTextField("dd/mm/yy");
		textField_1.setBounds(430, 30, 114, 22);
		layeredPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblToDate = new JLabel("To date:");
		lblToDate.setBounds(369, 33, 61, 16);
		layeredPane.add(lblToDate);
		
		String[] userOrAdmin = {"User", "Admin"};
		JComboBox userAdmin = new JComboBox(userOrAdmin);
		userAdmin.setBounds(6, 28, 122, 28);
		layeredPane.add(userAdmin);
		
		String [] comboBoxS = {"From"};
		JComboBox comboBox = new JComboBox(comboBoxS);
		comboBox.setBounds(221, 82, 122, 28);
		layeredPane.add(comboBox);
		
		String [] comboBox_1S = {"Km", "Mil",};
		JComboBox comboBox_1 = new JComboBox(comboBox_1S);
		comboBox_1.setBounds(369, 82, 77, 28);
		layeredPane.add(comboBox_1);
		
		String [] comboBox_2S = {"To"};
		JComboBox comboBox_2 = new JComboBox(comboBox_2S);
		comboBox_2.setBounds(221, 122, 122, 28);
		layeredPane.add(comboBox_2);
		
		String [] comboBox_3S = {"Car"};
		JComboBox comboBox_3 = new JComboBox(comboBox_3S);
		comboBox_3.setBounds(369, 122, 77, 28);
		layeredPane.add(comboBox_3);
		
		String [] comboBox_4S = {"Purpose of the trip"};
		JComboBox comboBox_4 = new JComboBox(comboBox_4S);
		comboBox_4.setBounds(221, 180, 225, 28);
		layeredPane.add(comboBox_4);
		
		String [] comboBox_5S = {"Extra costs"};
		JComboBox comboBox_5 = new JComboBox(comboBox_5S);
		comboBox_5.setBounds(221, 230, 225, 28);
		layeredPane.add(comboBox_5);
		
		
	
		
	}
}


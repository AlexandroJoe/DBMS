import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtEdition;
	private JTextField txtPrice;
	private JTable table;
	private JTextField txtCarName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
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
	public Window() {
		initialize();
		Connect();
	}

	Connection con;
	PreparedStatement pst;
	
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/TestData","root","");
		}
		catch (ClassNotFoundException ex) {
			
		}
		catch (SQLException ex) {
			
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1052, 710);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Car Search");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(466, 10, 102, 44);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 99, 621, 246);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Edition\r\n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(38, 103, 77, 24);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Car Name\r\n");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(38, 40, 77, 24);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price\r\n");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(38, 163, 77, 24);
		panel.add(lblNewLabel_1_2);
		
		txtbname = new JTextField();
		txtbname.setEditable(false);
		txtbname.setBounds(160, 45, 148, 19);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtEdition = new JTextField();
		txtEdition.setEditable(false);
		txtEdition.setColumns(10);
		txtEdition.setBounds(160, 108, 148, 19);
		panel.add(txtEdition);
		
		txtPrice = new JTextField();
		txtPrice.setEditable(false);
		txtPrice.setColumns(10);
		txtPrice.setBounds(160, 168, 148, 19);
		panel.add(txtPrice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bookName,edition,price;
				
				bookName = txtbname.getText();
				edition = txtEdition.getText();
				price = txtPrice.getText();
				
				try {
					pst = con.prepareStatement("insert in test(name,edition,price)values(?,?,?)");
					pst.setString(1, bookName);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added !!!");
//					table_load();
					txtbname.setText("");
					txtEdition.setText("");
					txtPrice.setText("");
					txtbname.requestFocus();
				}
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(35, 355, 113, 56);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit\r\n");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExit.setBounds(275, 355, 113, 56);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear\r\n");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(533, 355, 113, 56);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(670, 110, 345, 366);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(25, 475, 621, 124);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Car Name\r\n");
		lblNewLabel_1_2_1.setBounds(47, 47, 77, 20);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lblNewLabel_1_2_1);
		
		txtCarName = new JTextField();
		txtCarName.setBounds(179, 50, 96, 19);
		txtCarName.setEditable(false);
		txtCarName.setColumns(10);
		panel_1.add(txtCarName);
		
		JButton btnUpdate = new JButton("Update\r\n");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(712, 515, 113, 56);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete\r\n");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(874, 515, 113, 56);
		frame.getContentPane().add(btnDelete);
	}
}

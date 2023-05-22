package FoodPOS;

import java.awt.EventQueue;
import Des.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class updateCus extends JFrame {

	private JPanel contentPane;
	private JTextField productIdTF;
	private JTextField newValueTF;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateCus frame = new updateCus();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public updateCus() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel warningLBL = new JLabel("");
		warningLBL.setForeground(Color.RED);
		warningLBL.setVisible(false);
		warningLBL.setBounds(61, 196, 385, 14);
		contentPane.add(warningLBL);
		
		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel.setBounds(61, 91, 84, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select Column");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(61, 133, 84, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("UPDATE");
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(221, 51, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New Value");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(61, 175, 84, 14);
		contentPane.add(lblNewLabel_3);
		
		productIdTF = new JTextField();
		productIdTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		productIdTF.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		productIdTF.setBounds(181, 86, 227, 20);
		contentPane.add(productIdTF);
		productIdTF.setColumns(10);
		
		String[] columns = {"CustomerName", "ContactNumber","OrderType","OrderHistory","PaymentMethod","PurchasedAmount"};
		JComboBox comboBox = new JComboBox(columns);
		comboBox.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		comboBox.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		comboBox.setBounds(181, 127, 227, 22);
		contentPane.add(comboBox);
		
		newValueTF = new JTextField();
		newValueTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		newValueTF.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		newValueTF.setBounds(181, 170, 227, 20);
		contentPane.add(newValueTF);
		newValueTF.setColumns(10);
		
		JButton updateButt = new JButton("UPDATE");
		updateButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String productId = productIdTF.getText();
				String item = (String) comboBox.getSelectedItem();
				String newValue = newValueTF.getText();
				int newProductId = Integer.parseInt(productId);
				boolean noProb = true;
				
				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grubhub", "root", "");
					 PreparedStatement PS = con.prepareStatement("UPDATE customertbl SET " + item + " = ? WHERE `Customer ID` = ?" );
					 UserDao check = new UserDao(con);
					 
					 //Check if field is empty
					 if(productId.isEmpty()== true || newValue.isEmpty()) {
						 
                              warningLBL.setText("EMPTY FIELD");
                              warningLBL.setVisible(true);
                              noProb = false;
                              
					 } 
					 
					 if (check.isIdExistsinCus(productId) == false) {
						 
                         warningLBL.setText("ID DOES NOT EXIST");
                         warningLBL.setVisible(true);
                         noProb = false;
                     
					 } 
					 
					 if (noProb) {
					  switch (item) {
				        case "CustomerName":
				            PS.setString(1, newValue);
				            break;
				        case "ContactNumber":
				            PS.setLong(1, Long.parseLong(newValue));
				            break;
				        case "OrderType":
				            PS.setString(1, newValue);
				            break;
				        case "OrderHistory":
				            PS.setString(1, newValue);
				            break;
				        case "PaymentMethod":
				        	PS.setString(1, newValue);
				            break;
				        case "PurchasedAmount":
				        	PS.setDouble(1, Double.parseDouble(newValue));
				            break;
				        default:
				            break;
				    }
				    
				    PS.setInt(2, newProductId);
				    int rowsUpdated = PS.executeUpdate();
				    if (rowsUpdated > 0) {
				        JOptionPane.showMessageDialog(null, "SUCCESSFULLY UPDATED");
				        new OwnLib().spawnFrame(new CusInfoDBD());
				        dispose();
				        
				    } else {
				        warningLBL.setText("FAILED TO UPDATE");
				        warningLBL.setVisible(true);
				    }
				   
					 }	 
					 } catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
				
				
				
			}
		});
		updateButt.setFocusable(false);
		updateButt.setFocusTraversalKeysEnabled(false);
		updateButt.setFocusPainted(false);
		updateButt.setBackground(Color.DARK_GRAY);
		updateButt.setForeground(Color.WHITE);
		updateButt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		updateButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		updateButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		updateButt.setBounds(266, 210, 89, 23);
		contentPane.add(updateButt);
		
		JButton backButt = new JButton("BACK");
		backButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OwnLib().spawnFrame(new CusInfoDBD());
			}
		});
		backButt.setFocusable(false);
		backButt.setFocusTraversalKeysEnabled(false);
		backButt.setFocusPainted(false);
		backButt.setBackground(Color.DARK_GRAY);
		backButt.setForeground(Color.WHITE);
		backButt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		backButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		backButt.setBounds(140, 210, 89, 23);
		contentPane.add(backButt);
		
		JLabel BG = new JLabel("");
		BG.setBounds(0, 0, 524, 370);
		new ImageSize(BG, "crudBG.png");
		contentPane.add(BG);
	}
}

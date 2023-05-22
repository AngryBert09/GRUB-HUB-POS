package FoodPOS;

import java.awt.EventQueue;
import Des.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class deleteCus extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField productIdTF;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deleteCus frame = new deleteCus();
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
	public deleteCus() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 163);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel warningLBL = new JLabel("New label");
		warningLBL.setVisible(false);
		warningLBL.setForeground(Color.RED);
		warningLBL.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		warningLBL.setBounds(63, 138, 403, 14);
		contentPane.add(warningLBL);
		
		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel.setBounds(77, 79, 94, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("DELETE");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(209, 43, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		productIdTF = new JTextField();
		productIdTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		productIdTF.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		productIdTF.setBounds(169, 74, 233, 20);
		contentPane.add(productIdTF);
		productIdTF.setColumns(10);
		
		JButton deleteButt = new JButton("DELETE");
		deleteButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String productID = productIdTF.getText();
					
					
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grubhub", "root", "");
						 PreparedStatement stmt = con.prepareStatement("DELETE FROM customertbl WHERE `Customer ID` = ?");
						 UserDao check = new UserDao(con);
						 stmt.setInt(1, Integer.parseInt(productID));
						 
						 
						 if(productID.isEmpty()) {
							 
							 warningLBL.setText("EMPTY FIELD");
							 warningLBL.setVisible(true);
							 
							 
						 } else if(check.isIdExistsinCus(productID) == false) {
							 
							 warningLBL.setText("INVALID ID");
							 warningLBL.setVisible(true);
						 
						 } else {
							 
							    stmt.executeUpdate();
							    dispose();
							    stmt.close();
							    con.close();
							    JOptionPane.showMessageDialog(null, "DELETED");
							    new OwnLib().spawnFrame(new InventoryDBD());
							    dispose();
						
						 }
						    
					} catch (ClassNotFoundException | SQLException e1) {
						
						e1.printStackTrace();
					}
					
			
				}

		});
		deleteButt.setForeground(Color.WHITE);
		deleteButt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		deleteButt.setBackground(Color.DARK_GRAY);
		deleteButt.setFocusable(false);
		deleteButt.setFocusTraversalKeysEnabled(false);
		deleteButt.setFocusPainted(false);
		deleteButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deleteButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		deleteButt.setBounds(275, 113, 89, 23);
		contentPane.add(deleteButt);
		
		JButton backButt = new JButton("BACK");
		backButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OwnLib().spawnFrame(new CusInfoDBD());
			}
		});
		backButt.setForeground(Color.WHITE);
		backButt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		backButt.setBackground(Color.DARK_GRAY);
		backButt.setFocusable(false);
		backButt.setFocusTraversalKeysEnabled(false);
		backButt.setFocusPainted(false);
		backButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		backButt.setBounds(166, 113, 89, 23);
		contentPane.add(backButt);
		
		JLabel BG = new JLabel("");
		BG.setBounds(0, 0, 507, 301);
		new ImageSize(BG , "crudBG.png");
		contentPane.add(BG);
	}
}

package FoodPOS;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.MatteBorder;

import Des.OwnLib;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class deleteFrame extends JFrame implements DataListener {

	private JPanel contentPane;
	private JTextField productidTF;
	private Connection con;
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deleteFrame frame = new deleteFrame();
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
	@Override
	public void onDataReceived(String data) {
        // Process the received data
		
        System.out.println("\nReceived data: " + data);

    }
	
	public deleteFrame() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 93);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel warningLBL = new JLabel("New label");
		warningLBL.setForeground(Color.RED);
		warningLBL.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		warningLBL.setVisible(false);
		warningLBL.setBounds(90, 68, 165, 14);
		contentPane.add(warningLBL);
		
		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel.setBounds(25, 49, 71, 14);
		contentPane.add(lblNewLabel);
		
		productidTF = new JTextField();
		productidTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				new OwnLib().setFieldtoNumber(productidTF, warningLBL);
			}
		});
		productidTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		productidTF.setBounds(90, 46, 165, 20);
		contentPane.add(productidTF);
		productidTF.setColumns(10);
		
		JButton deleteButt = new JButton("DELETE");
		deleteButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  String productID = productidTF.getText();
				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grubhub", "root", "");
					 PreparedStatement stmt = con.prepareStatement("DELETE FROM producttbl WHERE `Product ID` = ?");
					 UserDao check = new UserDao(con);
					 stmt.setInt(1, Integer.parseInt(productID));
					 
					 
					 if(productID.isEmpty()) {
						 
						 warningLBL.setText("EMPTY FIELD");
						 warningLBL.setVisible(true);
						 
						 
					 } else if(check.isIdExists(productID) == false) {
						 
						 warningLBL.setText("INVALID ID");
						 warningLBL.setVisible(true);
					 
					 } else {
						 
						   stmt.executeUpdate();
						    dispose();
						    stmt.close();
						    con.close();
						    JOptionPane.showMessageDialog(null, "DELETED");
						    InventoryDBD invent = new InventoryDBD();
							invent.onDataReceived("Admin");
							invent.setLocationRelativeTo(null);
							invent.setVisible(true);
						    dispose();
					
					 }
					    
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
				}
				
		
			}
				
			
		});
		deleteButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deleteButt.setBackground(Color.DARK_GRAY);
		deleteButt.setForeground(Color.WHITE);
		deleteButt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		deleteButt.setFocusable(false);
		deleteButt.setFocusTraversalKeysEnabled(false);
		deleteButt.setFocusPainted(false);
		deleteButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		deleteButt.setBounds(265, 43, 89, 23);
		contentPane.add(deleteButt);
		
		JButton exitButt = new JButton("EXIT");
		exitButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryDBD invent = new InventoryDBD();
				invent.onDataReceived("Admin");
				invent.setLocationRelativeTo(null);
				invent.setVisible(true);
				dispose();
			}
		});
		exitButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitButt.setBackground(Color.DARK_GRAY);
		exitButt.setForeground(Color.WHITE);
		exitButt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		exitButt.setFocusable(false);
		exitButt.setFocusTraversalKeysEnabled(false);
		exitButt.setFocusPainted(false);
		exitButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		exitButt.setBounds(361, 43, 89, 23);
		contentPane.add(exitButt);
		
		JLabel BG = new JLabel("New label");
		BG.setBounds(0, 0, 450, 300);
		contentPane.add(BG);
		ImageIcon img1  = new ImageIcon(getClass().getResource("crudBG.png"));
		Image image1 = (img1).getImage().getScaledInstance(	BG.getWidth(), 	BG.getHeight(), Image.SCALE_SMOOTH);
		img1 = new ImageIcon(image1);
		BG.setIcon(img1);

	}
}

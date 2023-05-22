package FoodPOS;

import java.awt.EventQueue;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.MatteBorder;

import Des.OwnLib;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class updateFrame extends JFrame {

	private JPanel contentPane;
	private JTextField productidTF;
	private byte[] imageData ;
	private Connection con;
	private JTextField newValueTF;

	/**
	 * Launch the application.
	 */
	
	private void setTextFieldBorder(JTextField textField) {
	    textField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 0, 0)));
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateFrame frame = new updateFrame();
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
	public updateFrame() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel BG = new JLabel("");
		BG.setBounds(0, 0, 450, 300);
		ImageIcon img1  = new ImageIcon(getClass().getResource("crudBG.png"));
		Image image1 = (img1).getImage().getScaledInstance(	BG.getWidth(), 	BG.getHeight(), Image.SCALE_SMOOTH);
		img1 = new ImageIcon(image1);
		
		JLabel warningLBL = new JLabel("");
		warningLBL.setVisible(false);
		
		JLabel indicateLBL = new JLabel("Value ");
		indicateLBL.setVisible(false);
		indicateLBL.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		indicateLBL.setBounds(38, 138, 73, 14);
		contentPane.add(indicateLBL);
		warningLBL.setForeground(Color.RED);
		warningLBL.setBounds(38, 177, 216, 14);
		contentPane.add(warningLBL);
		
		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel.setBounds(38, 74, 73, 14);
		contentPane.add(lblNewLabel);
		
		newValueTF = new JTextField();
		newValueTF.setVisible(false);
		newValueTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		newValueTF.setBounds(121, 133, 172, 20);
		contentPane.add(newValueTF);
		newValueTF.setColumns(10);
		
		
		
		productidTF = new JTextField();
		productidTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		productidTF.setBounds(121, 69, 172, 20);
		contentPane.add(productidTF);
		productidTF.setColumns(10);
		
		JLabel imageLBL = new JLabel("");
		imageLBL.setHorizontalAlignment(SwingConstants.CENTER);
		imageLBL.setBounds(210, 138, 230, 14);
		contentPane.add(imageLBL);
		
		JButton uploadButt = new JButton("UPLOAD");
		uploadButt.setVisible(false);
		uploadButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == uploadButt) {
		            // Open file chooser dialog
		            JFileChooser fileChooser = new JFileChooser();
		            int result = fileChooser.showOpenDialog(new addFrame());
		            
		            
		            if (result == JFileChooser.APPROVE_OPTION) {
		                File selectedFile = fileChooser.getSelectedFile();

		                try {
		                    // Read image file into byte array
		                    FileInputStream inputStream = new FileInputStream(selectedFile);
		                    imageData = inputStream.readAllBytes();
		                    inputStream.close();
                            

		                    imageLBL.setText("Image uploaded successfully.");
		                } catch (IOException ex) {
		                	imageLBL.setText("Error reading image file: " + ex.getMessage());
		                } 
		            }
		        }
		    }
				
				
			
		});
		String[] items = {"ProductName", "Stock", "Price", "Image"};
		JComboBox comboBox = new JComboBox(items);
		comboBox.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBox.getSelectedItem().equals("ProductName") || comboBox.getSelectedItem().equals("Stock") || comboBox.getSelectedItem().equals("Price")) {
				indicateLBL.setVisible(true);
				newValueTF.setVisible(true);
				uploadButt.setVisible(false);
				indicateLBL.setText("Value");

				} else {
					
					newValueTF.setVisible(false);
					indicateLBL.setText("New Image");
					indicateLBL.setVisible(true);
					uploadButt.setVisible(true);

				}
			}
		});
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		comboBox.setBounds(121, 100, 172, 22);
		contentPane.add(comboBox);
		uploadButt.setForeground(Color.WHITE);
		uploadButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		uploadButt.setBackground(Color.DARK_GRAY);
		uploadButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		uploadButt.setFocusable(false);
		uploadButt.setFocusTraversalKeysEnabled(false);
		uploadButt.setFocusPainted(false);
		uploadButt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		uploadButt.setBounds(121, 133, 89, 23);
		contentPane.add(uploadButt);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OwnLib().spawnFrame(new InventoryDBD());
				dispose();
			}
		});
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setFocusTraversalKeysEnabled(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		btnNewButton_1.setBounds(121, 202, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton updateButt = new JButton("UPDATE");
		updateButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int productID = Integer.parseInt(productidTF.getText());
				String newValue = newValueTF.getText();
				String item = (String) comboBox.getSelectedItem();
				System.out.print(item);
				boolean noProb = true;
			
				 try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grubhub", "root", "");
						 PreparedStatement PS = con.prepareStatement("UPDATE producttbl SET " + item + " = ? WHERE `Product ID` = ?" );
						 UserDao check = new UserDao(con);
						 
						 //Check if field is empty
						 if(productidTF.getText().isEmpty()== true || newValue.isEmpty()) {
							 
                                  warningLBL.setText("EMPTY FIELD");
                                  warningLBL.setVisible(true);
                                  noProb = false;
                                  
						 } 
						 
						 if (check.isIdExists(productidTF.getText()) == false) {
							 
                             warningLBL.setText("ID DOES NOT EXIST");
                             warningLBL.setVisible(true);
                             noProb = false;
                         
						 } 
						 
						 if (noProb) {
						  switch (item) {
					        case "ProductName":
					            PS.setString(1, newValue);
					            break;
					        case "Stock":
					            PS.setInt(1, Integer.parseInt(newValue));
					            break;
					        case "Price":
					            PS.setDouble(1, Double.parseDouble(newValue));
					            break;
					        case "Image":
					            PS.setBytes(1, imageData);
					            break;
					        default:
					            break;
					    }
					    
					    PS.setInt(2, productID);
					    int rowsUpdated = PS.executeUpdate();
					    if (rowsUpdated > 0) {
					        JOptionPane.showMessageDialog(null, "SUCCESSFULLY UPDATED");
					        new OwnLib().spawnFrame(new InventoryDBD());
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
		updateButt.setForeground(Color.WHITE);
		updateButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		updateButt.setBackground(Color.DARK_GRAY);
		updateButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		updateButt.setFocusable(false);
		updateButt.setFocusTraversalKeysEnabled(false);
		updateButt.setFocusPainted(false);
		updateButt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		updateButt.setBounds(223, 202, 89, 23);
		contentPane.add(updateButt);
		
		JLabel lblNewLabel_6 = new JLabel("UPDATE");
		lblNewLabel_6.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel_6.setBounds(183, 44, 64, 14);
		contentPane.add(lblNewLabel_6);
		
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Select Column");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(38, 106, 73, 14);
		contentPane.add(lblNewLabel_1);
		BG.setIcon(img1);

		contentPane.add(BG);
	

		
	}
}

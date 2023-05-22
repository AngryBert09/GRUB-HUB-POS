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
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.JFileChooser;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Des.*;




public class addFrame extends JFrame {

	private JPanel contentPane;
	private JTextField prodNameTF;
	private JTextField stockTF;
	private JTextField priceTF;
	private Connection con;
	private byte[] imageData ;
	/**
	 * Launch the application.
	 */
	private void setTextFieldBorder(JTextField textField) {
	    textField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 0, 0)));
	}
	
	private String getTextFromField(JTextField textField) {
	    return textField.getText().trim();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addFrame frame = new addFrame();
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
	public addFrame() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel BG = new JLabel("");
		BG.setBounds(0, 0, 450, 300);
		ImageIcon img1  = new ImageIcon(getClass().getResource("crudBG.png"));
		Image image1 = (img1).getImage().getScaledInstance(	BG.getWidth(), 	BG.getHeight(), Image.SCALE_SMOOTH);
		img1 = new ImageIcon(image1);
		
		JLabel warningLBL = new JLabel("New label");
		warningLBL.setVisible(false);
		warningLBL.setForeground(Color.RED);
		warningLBL.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		warningLBL.setBounds(54, 214, 200, 14);
		contentPane.add(warningLBL);
		
		JLabel lblNewLabel_4 = new JLabel("ADD PRODUCT");
		lblNewLabel_4.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(157, 50, 110, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("Product Name");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblNewLabel.setBounds(54, 78, 87, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Stock");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(54, 107, 78, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(54, 137, 78, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Image ");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(54, 175, 78, 14);
		contentPane.add(lblNewLabel_3);
		
		prodNameTF = new JTextField();
		prodNameTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				e.getKeyCode();
			    prodNameTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));	
			}
		});
		prodNameTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		prodNameTF.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		prodNameTF.setBounds(151, 75, 200, 20);
		contentPane.add(prodNameTF);
		prodNameTF.setColumns(10);
		
		stockTF = new JTextField();
		stockTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				e.getKeyCode();
			    stockTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));	
			    new OwnLib().setFieldtoNumber(stockTF, warningLBL);
			}
		});
		stockTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		stockTF.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		stockTF.setBounds(151, 104, 200, 20);
		contentPane.add(stockTF);
		stockTF.setColumns(10);
		
		priceTF = new JTextField();
		priceTF.addKeyListener(new KeyAdapter() {		
			@Override
			public void keyPressed(KeyEvent e) {
				e.getKeyCode();
			    priceTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));	
                new OwnLib().setFieldtoNumber(priceTF, warningLBL);
			}
		});
		priceTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		priceTF.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		priceTF.setBounds(151, 134, 200, 20);
		contentPane.add(priceTF);
		priceTF.setColumns(10);
		
		JLabel imageLBL = new JLabel("");
		imageLBL.setBounds(256, 175, 194, 14);
		contentPane.add(imageLBL);
		
		JButton uploadButt = new JButton("UPLOAD");
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
		uploadButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		uploadButt.setForeground(Color.WHITE);
		uploadButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		uploadButt.setBackground(Color.DARK_GRAY);
		uploadButt.setFocusable(false);
		uploadButt.setFocusTraversalKeysEnabled(false);
		uploadButt.setFocusPainted(false);
		uploadButt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		uploadButt.setBounds(151, 170, 89, 23);
		contentPane.add(uploadButt);
		
		JButton addButt = new JButton("ADD");
		addButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productName = getTextFromField(prodNameTF);
				String stock = getTextFromField(stockTF);
				String price = getTextFromField(priceTF);
				
				
				 try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grubhub", "root", "");
					 PreparedStatement PS = con.prepareStatement("insert into producttbl (ProductName, Stock, Price, Image)  VALUES (?,?,?,?)");
					
					 boolean allFieldsFilled = true;
					 for (JTextField textField : Arrays.asList(prodNameTF , stockTF, priceTF)) {
					     if (textField.getText().isEmpty()) {
					         setTextFieldBorder(textField);
					         warningLBL.setText("FILL UP REQUIRED FIELDS");
					         warningLBL.setVisible(true);
					         allFieldsFilled = false;
					     }
					 }
					 
				  //Check if the image is nulll
				   if(imageData == null) {
					   allFieldsFilled = false;
					   warningLBL.setText("UPLOAD AN IMAGE");
					   warningLBL.setVisible(true);
				   } 
					 
					 
                 
					 if(allFieldsFilled) {
						 
					 PS.setString(1, productName);
					 PS.setString(2, stock);
					 PS.setString(3, price);
					 PS.setBytes(4, imageData);
					 PS.executeUpdate();
					 JOptionPane.showMessageDialog(null, "SUCCESSFULLY ADDED");
					 new OwnLib().spawnFrame(new InventoryDBD());
					 dispose();
				
					
					 
					 } 
					 
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			
		}});
		addButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		addButt.setBackground(Color.DARK_GRAY);
		addButt.setForeground(Color.WHITE);
		addButt.setFocusable(false);
		addButt.setFocusTraversalKeysEnabled(false);
		addButt.setFocusPainted(false);
		addButt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		addButt.setBounds(250, 238, 89, 23);
		contentPane.add(addButt);
		
		JButton exitButt = new JButton("EXIT");
		exitButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OwnLib().spawnFrame(new InventoryDBD());
				dispose();
			}
		});
		exitButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		exitButt.setBackground(Color.DARK_GRAY);
		exitButt.setForeground(Color.WHITE);
		exitButt.setFocusable(false);
		exitButt.setFocusTraversalKeysEnabled(false);
		exitButt.setFocusPainted(false);
		exitButt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		exitButt.setBounds(151, 238, 89, 23);
		contentPane.add(exitButt);
		BG.setIcon(img1);
		contentPane.add(BG);
	}
}

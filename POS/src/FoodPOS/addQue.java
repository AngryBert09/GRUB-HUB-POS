package FoodPOS;

import java.awt.EventQueue;

import Des.*;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class addQue extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel productidLBL;
	JLabel productNameLBL;
	JLabel priceLBL;
	JLabel stockLBL;
	
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addQue frame = new addQue();
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
	public addQue() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel warningLBL = new JLabel("");
		warningLBL.setHorizontalTextPosition(SwingConstants.CENTER);
		warningLBL.setHorizontalAlignment(SwingConstants.CENTER);
		warningLBL.setVisible(false);
		warningLBL.setForeground(Color.RED);
		warningLBL.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		warningLBL.setBounds(101, 34, 263, 14);
		contentPane.add(warningLBL);
		
		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel.setBounds(36, 55, 106, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Product Name");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(36, 80, 106, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(36, 105, 106, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Stock");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(36, 130, 106, 14);
		contentPane.add(lblNewLabel_3);
		
	    productidLBL = new JLabel("");
		productidLBL.setBounds(173, 53, 126, 14);
		contentPane.add(productidLBL);
		
	    productNameLBL = new JLabel("");
		productNameLBL.setBounds(173, 78, 126, 14);
		contentPane.add(productNameLBL);
		
	    priceLBL = new JLabel("");
		priceLBL.setBounds(173, 103, 126, 14);
		contentPane.add(priceLBL);
	
	    stockLBL = new JLabel("");
		stockLBL.setBounds(173, 128, 126, 14);
		contentPane.add(stockLBL);
		
		JLabel lblNewLabel_4 = new JLabel("ADD QUANTITY :");
		lblNewLabel_4.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(36, 157, 83, 14);
		contentPane.add(lblNewLabel_4);
		
		JTextField quanTF = new JTextField();
		quanTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				new OwnLib().setFieldtoNumber(quanTF, warningLBL);
			}
		});
		quanTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		quanTF.setBounds(140, 152, 190, 20);
		contentPane.add(quanTF);
		quanTF.setColumns(10);
		
		JButton addButt = new JButton("ADD QUE");
		addButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String quantity = quanTF.getText();
			        Double newPrice = Double.parseDouble(quantity) * getPrice();
			        boolean allRequirementsFilled = true;
			        boolean isGreater = true;
			        
			        try {
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grubhub", "root", "");
			            Connection uniqueConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grubhub", "root", "");

			            String update = "UPDATE queingtbl SET Quantity = (Quantity + ?), Price = (Price + ?) WHERE `Product ID` = ?";
			            String insert = "INSERT INTO queingtbl VALUES (?,?,?,?)";
			            String queUpdate = "UPDATE producttbl SET Stock = (Stock - ?) WHERE `Product ID` = ?";
			            
			            PreparedStatement updateExistingDataStatement = con.prepareStatement(update);
			            PreparedStatement insertNewDataStatement = con.prepareStatement(insert);
			            PreparedStatement updateStockColumn = uniqueConnection.prepareStatement(queUpdate);
			            
			           
			            
			            if (quantity.isEmpty()) {
			                warningLBL.setText("EMPTY FIELD");
			                warningLBL.setVisible(true);
			                quanTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 0, 0)));
			                allRequirementsFilled = false;
			            } else if (Integer.parseInt(quantity) > getStock()) {
			                warningLBL.setText("INSUFFICIENT QUANTITY");
			                warningLBL.setVisible(true);
			                allRequirementsFilled = false;
			                isGreater = false;
			            }
			            
			            UserDao check = new UserDao(con);
			            if (check.isIdExistsinQue(String.valueOf(getProductID())) && isGreater) {
			                updateExistingDataStatement.setInt(1, Integer.parseInt(quantity));
			                updateExistingDataStatement.setDouble(2, newPrice);
			                updateExistingDataStatement.setInt(3, getProductID());
			                updateExistingDataStatement.executeUpdate();
			                
			                updateStockColumn.setInt(1, Integer.parseInt(quantity));
			                updateStockColumn.setInt(2, getProductID());
			                updateStockColumn.executeUpdate();
			                
			              
			                
			                JOptionPane.showMessageDialog(null, "ADDED ORDER");
			                dispose();
			                new OwnLib().spawnFrame(new SalesDBD());
			              
			               
			                
			            }
			            
			            if (allRequirementsFilled) {
			                insertNewDataStatement.setInt(1, getProductID());
			                insertNewDataStatement.setString(2, getProductName());
			                insertNewDataStatement.setInt(3, Integer.parseInt(quantity));
			                insertNewDataStatement.setDouble(4, newPrice);
			                insertNewDataStatement.executeUpdate();
			                
			                updateStockColumn.setInt(1, Integer.parseInt(quantity));
			                updateStockColumn.setInt(2, getProductID());
			                updateStockColumn.executeUpdate();
			                
			              
			             
			                JOptionPane.showMessageDialog(null, "ADDED ORDER");
			                dispose();
			                new OwnLib().spawnFrame(new SalesDBD());
			                
			              
			            }
					 
			           
			            
			            
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		addButt.setBackground(Color.DARK_GRAY);
		addButt.setForeground(Color.WHITE);
		addButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		addButt.setFocusable(false);
		addButt.setFocusTraversalKeysEnabled(false);
		addButt.setFocusPainted(false);
		addButt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		addButt.setBounds(241, 183, 89, 23);
		contentPane.add(addButt);
		
		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				new OwnLib().spawnFrame(new SalesDBD());
			}
		});
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setFocusTraversalKeysEnabled(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		btnNewButton_1.setBounds(140, 183, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel BG = new JLabel("");
		BG.setBounds(0, 0, 450, 249);
		contentPane.add(BG);
		ImageIcon img1  = new ImageIcon(getClass().getResource("addQueBG.png"));
		Image image1 = (img1).getImage().getScaledInstance(	BG.getWidth(), 	BG.getHeight(), Image.SCALE_SMOOTH);
		img1 = new ImageIcon(image1);
		BG.setIcon(img1);
		
	   
		
		
	}
	
	private int productId;
	private String productName;  
	private Double price;
	private int stock;
	private int quantity;
	private Connection connection;
	
	
	public void setConnection(Connection connection) {
		
		this.connection = connection;

	}
	
    public Connection getConnection() {
    	
    	return connection;
    	
    }

	public void setData(int productId, String productName, Double price, int stock) {
		
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.stock = stock; 
		
	        productidLBL.setText("" + productId);
	        productNameLBL.setText("" + productName);
	        priceLBL.setText("" + price);
	        stockLBL.setText("" + stock);

	} public void setQuantity(int Quantity) {
		
		this.quantity = Quantity;
		
	}
	public int getQuantity() {
		
		return quantity;
		
	}
	
	public int getProductID() {
		
		return productId;

	}
	public String getProductName() {
		
		return productName;

	}
	public double getPrice() {
		
		return price;

	}
	public int getStock() {
		
		return stock;

	}

	

}
	
	

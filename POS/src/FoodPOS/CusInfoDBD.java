package FoodPOS;

import java.awt.EventQueue;
import Des.*;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CusInfoDBD extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton salesButt;
	private JButton cusinfoButt;
	private JButton inventoryButt;
	private JButton updateButt;
	private JButton deleteButt;
	private JLabel lblNewLabel;
	private JTextField searchTF;
	private JButton searchButt;
	private DefaultTableModel customerTableModel;
    private long lastKeyPressTime;
    private static final long DELAY_MS = 10000; // Delay in milliseconds

	/**
	 * Launch the application.
	 * @return 
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CusInfoDBD frame = new CusInfoDBD();
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
	public CusInfoDBD() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setShape(new RoundRectangle2D.Double(0, 0, 800, 600,30, 30));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(126, 129, 664, 431);
		contentPane.add(scrollPane);
		
		customerTableModel = new DefaultTableModel();
		customerTableModel.addColumn("Customer ID");
		customerTableModel.addColumn("Customer Name");
		customerTableModel.addColumn("Contact Number");
		customerTableModel.addColumn("Order Type");
		customerTableModel.addColumn("Order History");
		customerTableModel.addColumn("Payment Method");
		customerTableModel.addColumn("PuchasedAmount");
        
		table = new JTable(customerTableModel);
		scrollPane.setViewportView(table);
		
		salesButt = new JButton("Sales");
		salesButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SalesDBD sales = new SalesDBD();
				sales.setLocationRelativeTo(null);
				sales.setVisible(true);
			}
		});
		salesButt.setBorderPainted(false);
		salesButt.setIcon(new ImageIcon(getClass().getResource("icons8-cashier-23.png")));
		salesButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		salesButt.setContentAreaFilled(false);
		salesButt.setFocusable(false);
		salesButt.setFocusTraversalKeysEnabled(false);
		salesButt.setFocusPainted(false);
		salesButt.setFont(new Font("SansSerif", Font.PLAIN, 11));
		salesButt.setBounds(0, 189, 122, 33);
		new OwnLib().setHoverEffect(salesButt);
		contentPane.add(salesButt);
		
		cusinfoButt = new JButton("CusInfo");
		cusinfoButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CusInfoDBD customer = new CusInfoDBD();
				customer.setLocationRelativeTo(null);
				customer.setVisible(true);
			}
		});
		cusinfoButt.setBorderPainted(false);
		cusinfoButt.setIcon(new ImageIcon(getClass().getResource("icons8-customer-23.png")));
		cusinfoButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cusinfoButt.setContentAreaFilled(false);
		cusinfoButt.setFocusable(false);
		cusinfoButt.setFocusTraversalKeysEnabled(false);
		cusinfoButt.setFocusPainted(false);
		cusinfoButt.setFont(new Font("SansSerif", Font.PLAIN, 11));
		cusinfoButt.setBounds(0, 222, 122, 33);
		new OwnLib().setHoverEffect(cusinfoButt);
		contentPane.add(cusinfoButt);
		
		inventoryButt = new JButton("Inventory");
		inventoryButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				InventoryDBD inventory = new InventoryDBD();
				inventory.setLocationRelativeTo(null);
				inventory.setVisible(true);
			}
		});
		inventoryButt.setBorderPainted(false);
		inventoryButt.setIcon(new ImageIcon(getClass().getResource("icons8-in-inventory-22.png")));
		inventoryButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		inventoryButt.setContentAreaFilled(false);
		inventoryButt.setFocusable(false);
		inventoryButt.setFocusTraversalKeysEnabled(false);
		inventoryButt.setFocusPainted(false);
		inventoryButt.setFont(new Font("SansSerif", Font.PLAIN, 11));
		inventoryButt.setBounds(0, 256, 122, 33);
		new OwnLib().setHoverEffect(inventoryButt);
		contentPane.add(inventoryButt);
		
		updateButt = new JButton("UPDATE");
		updateButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OwnLib().spawnFrame(new updateCus());
			}
		});
		updateButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		updateButt.setForeground(Color.WHITE);
		updateButt.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		updateButt.setFocusable(false);
		updateButt.setFocusTraversalKeysEnabled(false);
		updateButt.setFocusPainted(false);
		updateButt.setBackground(Color.DARK_GRAY);
		updateButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		updateButt.setBounds(387, 566, 89, 23);
		contentPane.add(updateButt);
		
		deleteButt = new JButton("DELETE");
		deleteButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OwnLib().spawnFrame(new deleteCus());
			}
		});
		deleteButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deleteButt.setForeground(Color.WHITE);
		deleteButt.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		deleteButt.setFocusable(false);
		deleteButt.setFocusTraversalKeysEnabled(false);
		deleteButt.setFocusPainted(false);
		deleteButt.setBackground(Color.DARK_GRAY);
		deleteButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		deleteButt.setBounds(486, 566, 89, 23);
		contentPane.add(deleteButt);
		
		lblNewLabel = new JLabel("SEARCH :");
		lblNewLabel.setBounds(150, 101, 67, 14);
		contentPane.add(lblNewLabel);
		
		searchTF = new JTextField();
		searchTF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			            long currentTime = System.currentTimeMillis();
			            if (currentTime - lastKeyPressTime > DELAY_MS) {
			                // Retrieve and display all data when Backspace is pressed
			                loadData();
			            }
			            lastKeyPressTime = currentTime;
			        }
				}
			
		});
		searchTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		searchTF.setBounds(216, 98, 209, 20);
		contentPane.add(searchTF);
		searchTF.setColumns(10);
		
		searchButt = new JButton("SEARCH");
		searchButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		searchButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		searchButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                customerTableModel.setRowCount(0);
			    
				String userInput = searchTF.getText().trim();

				try {
				    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grubhub", "root", "");
				    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customertbl WHERE `Customer ID` = ? OR CustomerName = ?");
				    
				    // Set the input value as the Product ID
				    preparedStatement.setString(1, userInput);
				    preparedStatement.setString(2, userInput);
				    
				    ResultSet resultSet = preparedStatement.executeQuery();
				    
				    
				    // Retrieve metadata from the result set
				    ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
				    int columnCount = metaData.getColumnCount();
				    
				    // Retrieve data from the result set and add it to the table model
				    while (resultSet.next()) {
				        Object[] rowData = new Object[columnCount];
				        
				        for (int i = 1; i <= columnCount; i++) {
				            rowData[i - 1] = resultSet.getObject(i);
				        }
				        
				        // Add data as a row to the table model
				        customerTableModel.addRow(rowData);
				    }
				    
				    
				    
				    
				    // Close the result set, prepared statement, and connection
				    resultSet.close();
				    preparedStatement.close();
				    connection.close();
				} catch (SQLException e1) {
				    e1.printStackTrace();
				}
				
			}
		});
		searchButt.setBackground(Color.DARK_GRAY);
		searchButt.setForeground(Color.WHITE);
		searchButt.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		searchButt.setFocusable(false);
		searchButt.setFocusTraversalKeysEnabled(false);
		searchButt.setFocusPainted(false);
		searchButt.setBounds(432, 95, 89, 23);
		contentPane.add(searchButt);
		
		JLabel BG = new JLabel("");
		BG.setBounds(0, 0, 800, 600);
		contentPane.add(BG);
		ImageIcon img1  = new ImageIcon(getClass().getResource("cusinfoBG.png"));
		Image image1 = (img1).getImage().getScaledInstance(	BG.getWidth(), 	BG.getHeight(), Image.SCALE_SMOOTH);
		img1 = new ImageIcon(image1);
		BG.setIcon(img1);
		
		loadData();
		
		
	}
	 private void loadData() {
	        try {
	            // Connect to the MySQL database
	            String url = "jdbc:mysql://localhost:3306/grubhub";
	            String username = "root";
	            String password = "";
	            Connection conn = DriverManager.getConnection(url, username, password);
	            
	            // Execute the SQL query to retrieve the data
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT * FROM customertbl");
	            
	            // Loop through the result set and add each row to the table model
	            while (rs.next()) {
	            	int customerId = rs.getInt(1);
	            	String customerName = rs.getString(2);
	            	long contact = rs.getLong(3);
	            	String orderType = rs.getString(4);
	            	String orderHistory = rs.getString(5);
	            	String paymentMethod = rs.getString(6);
	            	double totalAmount = rs.getDouble(7);
	         
	                customerTableModel.addRow(new Object[]{customerId, customerName, contact, orderType, orderHistory, paymentMethod, totalAmount});
	            }

	            // Close the database connection
	            rs.close();
	            stmt.close();
	            conn.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	    
	      
	      

}

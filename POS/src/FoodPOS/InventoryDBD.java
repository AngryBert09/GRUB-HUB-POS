package FoodPOS;

import java.awt.EventQueue;
import Des.*;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InventoryDBD extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane_1;
	private JButton deleteButt;
	private JTextField searchField;
	private JButton salesButt;
	private JButton cusinfoButt;
	private JButton inventoryButt;
	private JButton searchButt;
    private long lastKeyPressTime;
    private static final long DELAY_MS = 10000; // Delay in milliseconds
    private DefaultTableModel productTableModel;
   

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryDBD frame = new InventoryDBD();
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
	public InventoryDBD() {
		
		
		  
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setShape(new RoundRectangle2D.Double(0, 0, 800, 600,30, 30));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		scrollPane_1.setSize(new Dimension(50, 50));
		scrollPane_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane_1.setBounds(140, 122, 649, 427);
		contentPane.add(scrollPane_1);
		
		
            productTableModel = new DefaultTableModel();
	        productTableModel.addColumn("Product ID");
	        productTableModel.addColumn("ProductName");
	        productTableModel.addColumn("Stock");
	        productTableModel.addColumn("Price");
	        productTableModel.addColumn("Image");
	    
	    table = new JTable(productTableModel);
		table.setGridColor(Color.BLACK);
		scrollPane_1.setViewportView(table);
		  // Set custom cell renderer for the "Image" column
        table.getColumnModel().getColumn(4).setCellRenderer(new ImageRenderer());
  
	    
		
		JButton addButt = new JButton("ADD ");
		addButt.setFocusable(false);
		addButt.setFocusPainted(false);
		addButt.setFocusTraversalKeysEnabled(false);
		addButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new OwnLib().spawnFrame(new addFrame());
				dispose();
			
			}
		});
		addButt.setHorizontalTextPosition(SwingConstants.CENTER);
		addButt.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		addButt.setForeground(Color.WHITE);
		addButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addButt.setBackground(Color.DARK_GRAY);
		addButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		addButt.setBounds(333, 554, 89, 23);
		contentPane.add(addButt);
		
		JButton updateButt = new JButton("UPDATE");
		updateButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				new OwnLib().spawnFrame(new updateFrame());
			}
		});
		updateButt.setFocusable(false);
		updateButt.setFocusPainted(false);
		updateButt.setFocusTraversalKeysEnabled(false);
		updateButt.setHorizontalTextPosition(SwingConstants.CENTER);
		updateButt.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		updateButt.setForeground(Color.WHITE);
		updateButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		updateButt.setBackground(Color.DARK_GRAY);
		updateButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		updateButt.setBounds(427, 554, 89, 23);
		contentPane.add(updateButt);
		
		deleteButt = new JButton("DELETE");
		deleteButt.setFocusable(false);
		deleteButt.setFocusPainted(false);
		deleteButt.setFocusTraversalKeysEnabled(false);
		deleteButt.setHorizontalTextPosition(SwingConstants.CENTER);
		deleteButt.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		deleteButt.setForeground(Color.WHITE);
		deleteButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deleteButt.setBackground(Color.DARK_GRAY);
		deleteButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		deleteButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OwnLib().spawnFrame(new deleteFrame());
				dispose();
			}
		});
		deleteButt.setBounds(521, 553, 89, 23);
		contentPane.add(deleteButt);
		
		searchField = new JTextField();
		searchField.addKeyListener(new KeyAdapter() {
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
		searchField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		searchField.setBounds(200, 92, 277, 20);
		contentPane.add(searchField);
		searchField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("SEARCH :");
		lblNewLabel.setBounds(143, 95, 56, 14);
		contentPane.add(lblNewLabel);
		
		cusinfoButt = new JButton("CusInfo");
		cusinfoButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OwnLib().spawnFrame(new CusInfoDBD());
			}
		});
		cusinfoButt.setBorderPainted(false);
		cusinfoButt.setIcon(new ImageIcon("D:\\Eclipse\\My Workspace\\POS\\src\\Des\\Assets\\icons8-customer-23.png"));
		cusinfoButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cusinfoButt.setContentAreaFilled(false);
		cusinfoButt.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cusinfoButt.setFocusable(false);
		cusinfoButt.setFocusTraversalKeysEnabled(false);
		cusinfoButt.setFocusPainted(false);
		cusinfoButt.setBounds(0, 224, 112, 33);
		new OwnLib().setHoverEffect(cusinfoButt);
		contentPane.add(cusinfoButt);
		
		salesButt = new JButton("Sales");
		salesButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OwnLib().spawnFrame(new SalesDBD());
			}
		});
		salesButt.setBorderPainted(false);
		salesButt.setIcon(new ImageIcon("D:\\Eclipse\\My Workspace\\POS\\src\\Des\\Assets\\icons8-cashier-23.png"));
		salesButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		salesButt.setAlignmentY(Component.TOP_ALIGNMENT);
		salesButt.setContentAreaFilled(false);
		salesButt.setFont(new Font("SansSerif", Font.PLAIN, 12));
		salesButt.setFocusable(false);
		salesButt.setFocusTraversalKeysEnabled(false);
		salesButt.setFocusPainted(false);
		salesButt.setBounds(0, 194, 112, 33);
		new OwnLib().setHoverEffect(salesButt);
		contentPane.add(salesButt);
		
		inventoryButt = new JButton("Inventory");
		inventoryButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OwnLib().spawnFrame(new InventoryDBD());
				
			}
		});
		inventoryButt.setBorderPainted(false);
		inventoryButt.setIcon(new ImageIcon("D:\\Eclipse\\My Workspace\\POS\\src\\Des\\Assets\\icons8-in-inventory-22.png"));
		inventoryButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		inventoryButt.setContentAreaFilled(false);
		inventoryButt.setFont(new Font("SansSerif", Font.PLAIN, 12));
		inventoryButt.setFocusable(false);
		inventoryButt.setFocusTraversalKeysEnabled(false);
		inventoryButt.setFocusPainted(false);
		inventoryButt.setBounds(0, 256, 112, 33);
		new OwnLib().setHoverEffect(inventoryButt);
		contentPane.add(inventoryButt);
		
		
		searchButt = new JButton("Search");
		searchButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				productTableModel.setRowCount(0);
			    
				String userInput = searchField.getText().trim();

				try {
				    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grubhub", "root", "");
				    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM producttbl WHERE `Product ID` = ? OR ProductName = ?");
				    
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
				        productTableModel.addRow(rowData);
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
		searchButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		searchButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		searchButt.setBackground(Color.DARK_GRAY);
		searchButt.setForeground(Color.WHITE);
		searchButt.setFocusable(false);
		searchButt.setFocusTraversalKeysEnabled(false);
		searchButt.setFocusPainted(false);
		searchButt.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		searchButt.setBounds(487, 91, 89, 23);
		contentPane.add(searchButt);
		
		JLabel BG = new JLabel("");
		BG.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		BG.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		BG.setBounds(-13, -19, 813, 619);
		contentPane.add(BG);
		ImageIcon img1  = new ImageIcon(getClass().getResource("inventoryBG.png"));
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
	            ResultSet rs = stmt.executeQuery("SELECT * FROM producttbl");
	            
	            // Loop through the result set and add each row to the table model
	            while (rs.next()) {
	            	String productId = rs.getString(1);
	                String productName = rs.getString(2);
	                int stock = rs.getInt(3);
	                double price = rs.getDouble(4);
	                byte[] imageBytes = rs.getBytes(5);
	                BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
	                productTableModel.addRow(new Object[]{productId, productName, stock, price, image});
	            }

	            // Close the database connection
	            rs.close();
	            stmt.close();
	            conn.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        
	    } 
	
	 

	class ImageRenderer extends javax.swing.table.DefaultTableCellRenderer {
	        @Override
	        public void setValue(Object value) {
	            if (value instanceof BufferedImage) {
	                // Convert the BufferedImage to an ImageIcon
	                BufferedImage bufferedImage = (BufferedImage) value;
	                ImageIcon imageIcon = new ImageIcon(bufferedImage);
	                setIcon(imageIcon);
	                setPreferredSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
	                setHorizontalAlignment(JLabel.CENTER);
	            } else {
	                super.setValue(value);
	            }
	        }
	    }
	

	
	
	

	 }

package FoodPOS;

import java.awt.EventQueue;
import Des.*;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class SalesDBD extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	  private static final int ROWS_PER_PAGE = 6;
	    private int currentPage = 1;
	    private JButton nextButt;
	    private JButton prevButt;
	 
	   
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesDBD frame = new SalesDBD();
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
	public SalesDBD() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setShape(new RoundRectangle2D.Double(0, 0, 800, 600,30, 30));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel BG = new JLabel("");
		BG.setFont(new Font("Tahoma", Font.PLAIN, 18));
		BG.setBounds(0, 0, 810, 600);
		ImageIcon img1  = new ImageIcon(getClass().getResource("salesBG.png"));
		Image image1 = (img1).getImage().getScaledInstance(	BG.getWidth(), 	BG.getHeight(), Image.SCALE_SMOOTH);
		img1 = new ImageIcon(image1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.GRAY);
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		scrollPane.setBounds(557, 82, 233, 266);
		contentPane.add(scrollPane);
		
		JTextArea orderTA = new JTextArea();
		orderTA.setCaretColor(Color.PINK);
		orderTA.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		orderTA.setBackground(Color.PINK);
		
		
		scrollPane.setViewportView(orderTA);
		JButton btnNewButton = new JButton("PROCEED");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OwnLib().spawnFrame(new PaymentGateway());
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton.setFocusable(false);
		btnNewButton.setFocusTraversalKeysEnabled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Bahnschrift", Font.BOLD, 18));
		btnNewButton.setBounds(546, 544, 264, 56);
		contentPane.add(btnNewButton);
		JButton salesButt = new JButton("Sales");
		salesButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OwnLib().spawnFrame(new SalesDBD());
				
			}
		});
		salesButt.setHideActionText(true);
		salesButt.setBorderPainted(false);
		salesButt.setIconTextGap(10);
		salesButt.setIcon(new ImageIcon(getClass().getResource("icons8-cashier-23.png")));
		salesButt.setFont(new Font("SansSerif", Font.PLAIN, 12));
		salesButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		salesButt.setContentAreaFilled(false);
		salesButt.setFocusable(false);
		salesButt.setFocusTraversalKeysEnabled(false);
		salesButt.setFocusPainted(false);
		salesButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		salesButt.setBounds(0, 205, 115, 33);
		new OwnLib().setHoverEffect(salesButt);
		contentPane.add(salesButt);
		
		JButton inventoryButt = new JButton("Inventory");
		inventoryButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			    new OwnLib().spawnFrame(new InventoryDBD());
			
				
				
			}
		});
		inventoryButt.setBorderPainted(false);
		inventoryButt.setIcon(new ImageIcon(getClass().getResource("icons8-in-inventory-22.png")));
		inventoryButt.setFont(new Font("SansSerif", Font.PLAIN, 12));
		inventoryButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		inventoryButt.setContentAreaFilled(false);
		inventoryButt.setFocusable(false);
		inventoryButt.setFocusTraversalKeysEnabled(false);
		inventoryButt.setFocusPainted(false);
		inventoryButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		inventoryButt.setBounds(0, 271, 115, 33);
		new OwnLib().setHoverEffect(inventoryButt);
		contentPane.add(inventoryButt);

		JButton cusinfoButt = new JButton("CusInfo");
		cusinfoButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OwnLib().spawnFrame(new CusInfoDBD());
				
			}
		});
		cusinfoButt.setBorderPainted(false);
		cusinfoButt.setIcon(new ImageIcon(getClass().getResource("icons8-customer-23.png")));
		cusinfoButt.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cusinfoButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cusinfoButt.setContentAreaFilled(false);
		cusinfoButt.setFocusable(false);
		cusinfoButt.setFocusTraversalKeysEnabled(false);
		cusinfoButt.setFocusPainted(false);
		cusinfoButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cusinfoButt.setBounds(0, 238, 115, 33);
		new OwnLib().setHoverEffect(cusinfoButt);
		contentPane.add(cusinfoButt);
		
		JLabel product1LBL = new JLabel("");
		product1LBL.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		product1LBL.setIcon(null);
		product1LBL.setBounds(135, 180, 95, 118);
		contentPane.add(product1LBL);
		
		JLabel product4LBL = new JLabel("");
		product4LBL.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		product4LBL.setBounds(135, 379, 95, 118);
		contentPane.add(product4LBL);
		
		JLabel product2LBL = new JLabel("");
		product2LBL.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		product2LBL.setBounds(290, 180, 95, 118);
		contentPane.add(product2LBL);
		
		JLabel product5LBL = new JLabel("");
		product5LBL.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		product5LBL.setBounds(290, 379, 95, 118);
		contentPane.add(product5LBL);
		
		JLabel product3LBL = new JLabel("");
		product3LBL.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		product3LBL.setBounds(437, 180, 95, 118);
		contentPane.add(product3LBL);
		
		JLabel product6LBL = new JLabel("");
		product6LBL.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		product6LBL.setBounds(437, 379, 95, 118);
		contentPane.add(product6LBL);
		
		JLabel[] labels = {product1LBL, product2LBL, product3LBL, product4LBL, product5LBL, product6LBL};
	
		
        nextButt = new JButton("NEXT");
        nextButt.setVisible(false);
        nextButt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		   currentPage++;
        		   updateData(labels);
                   
        	}
        });
   
	    prevButt = new JButton("BACK");
	    prevButt.setVisible(false);
	    prevButt.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 currentPage--;
	    		 updateData(labels);
	               
	    	}
	    });
	    prevButt.setBackground(Color.DARK_GRAY);
	    prevButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    prevButt.setForeground(Color.WHITE);
	    prevButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	    prevButt.setFocusable(false);
	    prevButt.setFocusTraversalKeysEnabled(false);
	    prevButt.setFocusPainted(false);
	    prevButt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
	    prevButt.setBounds(248, 561, 89, 23);
	    contentPane.add(prevButt);
        nextButt.setBackground(Color.DARK_GRAY);
        nextButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextButt.setForeground(Color.WHITE);
        nextButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        nextButt.setFocusable(false);
        nextButt.setFocusTraversalKeysEnabled(false);
        nextButt.setFocusPainted(false);
        nextButt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        nextButt.setBounds(347, 561, 89, 23);
        contentPane.add(nextButt);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(Color.PINK);
		panel.setBounds(557, 397, 233, 136);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Subtotal");
		lblNewLabel_6.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_8 = new JLabel("Total sales tax");
		lblNewLabel_8.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(10, 36, 95, 14);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Total :");
		lblNewLabel_9.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_9.setBounds(10, 84, 123, 41);
		panel.add(lblNewLabel_9);
		
	    JLabel subtotalLBL = new JLabel("");
	    subtotalLBL.setHorizontalTextPosition(SwingConstants.CENTER);
	    subtotalLBL.setHorizontalAlignment(SwingConstants.CENTER);
	    subtotalLBL.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		subtotalLBL.setBounds(151, 12, 72, 14);
		panel.add(subtotalLBL);
		
		JLabel salesTaxLBL = new JLabel("0.12");
		salesTaxLBL.setHorizontalTextPosition(SwingConstants.CENTER);
		salesTaxLBL.setHorizontalAlignment(SwingConstants.CENTER);
		salesTaxLBL.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		salesTaxLBL.setBounds(151, 37, 72, 14);
		panel.add(salesTaxLBL);
		
		JLabel totalLBL = new JLabel("");
		totalLBL.setHorizontalTextPosition(SwingConstants.CENTER);
		totalLBL.setHorizontalAlignment(SwingConstants.CENTER);
		totalLBL.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		totalLBL.setBounds(128, 91, 95, 34);
		panel.add(totalLBL);
	 

        updateData(labels);
		retrieveDataFromDatabase(orderTA);
		showMath(subtotalLBL, totalLBL);

		JButton btnNewButton_1 = new JButton("VOID");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
		
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection deleteConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grubhub", "root", "");
				Connection updateConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grubhub", "root", "");
				
				PreparedStatement deleteStatement = deleteConnection.prepareStatement("DELETE FROM queingtbl");
				
				
				
				 String updateQuery = "UPDATE producttbl " +
                         "JOIN queingtbl ON producttbl.`Product ID` = queingtbl.`Product ID` " +
                         "SET producttbl.Stock = producttbl.Stock + queingtbl.Quantity";
    
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grubhub", "root", "");
    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
    
    // Execute the update query
    preparedStatement.executeUpdate();
    
    // Close the prepared statement and connection
    preparedStatement.close();
    connection.close();
    
    System.out.println("Update successful!");
				
    deleteStatement.executeUpdate();
				

				
				System.out.print("SUCCESS DELETED");
				dispose();
				new OwnLib().spawnFrame(new SalesDBD());
				
				
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	
				
				
			}
		});
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setFocusTraversalKeysEnabled(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		btnNewButton_1.setBounds(637, 359, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("LOGOUT");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			int select = JOptionPane.showConfirmDialog(null, "DO YOU WANT TO LOG OUT?", "BYEBYE?", JOptionPane.YES_NO_OPTION);
				
				if(select == JOptionPane.YES_OPTION) {
					
					System.exit(0);
					
				}

			}
		});
		btnNewButton_2.setBackground(Color.DARK_GRAY);
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		btnNewButton_2.setFocusable(false);
		btnNewButton_2.setFocusTraversalKeysEnabled(false);
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton_2.setBounds(10, 524, 89, 23);
		contentPane.add(btnNewButton_2);
		
		 
		BG.setIcon(img1);
		contentPane.add(BG);
	}
	
	public void updateData(JLabel[] labels) {
		
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grubhub", "root", "");
		    String countQuery = "SELECT COUNT(*) FROM producttbl";
		    String dataQuery = "SELECT * FROM producttbl LIMIT ? OFFSET ?";

		    PreparedStatement countStatement = connection.prepareStatement(countQuery);
		    ResultSet countResult = countStatement.executeQuery();
		    countResult.next();
		    int rowCount = countResult.getInt(1);

		    int totalPages = (int) Math.ceil((double) rowCount / ROWS_PER_PAGE);

		    if (currentPage < totalPages) {
		        nextButt.setVisible(true);
		    } else {
		        nextButt.setVisible(false);
		    }

		    if (currentPage > 1) {
		        prevButt.setVisible(true);
		    } else {
		        prevButt.setVisible(false);
		    }

		    PreparedStatement dataStatement = connection.prepareStatement(dataQuery);
		    dataStatement.setInt(1, ROWS_PER_PAGE);
		    dataStatement.setInt(2, (currentPage - 1) * ROWS_PER_PAGE);
		    ResultSet dataResult = dataStatement.executeQuery();
		    

		    // Iterate through the result set and assign images to the JLabel components
		    int rowIndex = 0;
		    while (dataResult.next() && rowIndex < labels.length) {
		        // Retrieve the image data from the current row
		        byte[] imageData = dataResult.getBytes("Image");

		        // Convert the image data to BufferedImage
		        InputStream inputStream = new ByteArrayInputStream(imageData);
		        BufferedImage image = ImageIO.read(inputStream);

		        // Create an ImageIcon from the BufferedImage
		        ImageIcon img11 = new ImageIcon(image);
		        Image image11 = img11.getImage().getScaledInstance(labels[rowIndex].getWidth(), labels[rowIndex].getHeight(), Image.SCALE_SMOOTH);
		        img11 = new ImageIcon(image11);

		        // Set the ImageIcon as the icon for the corresponding JLabel
		        labels[rowIndex].setIcon(img11);
		        
		     // Retrieve the product ID, price, and stock from the database
		        int productId = dataResult.getInt("Product ID");
		        String productName = dataResult.getString("ProductName");
		        double price = dataResult.getDouble("Price");
		        int stock = dataResult.getInt("Stock");
		        
		        OrderData ordata = new OrderData();
		        ordata.setData(productId, productName, price, stock);
		        

		        // Add a MouseListener to each JLabel
		        labels[rowIndex].addMouseListener(new MouseAdapter() {
		            public void mouseClicked(MouseEvent e) {
		                // Handle the click event here
		                // You can access the product ID, price, and stock variables within this scope	
		                addQue add = new addQue();
		                add.setData(ordata.getProductID(), ordata.getProductName(), ordata.getPrice(), ordata.getStock());
		                dispose();
		            	new OwnLib().spawnFrame(add);
	
		            }
		            
		       
		        });

		        // Move to the next JLabel
		        rowIndex++;
		    }

		    // Close the result set and statement
		    dataResult.close();

		} catch (SQLException | IOException | ClassNotFoundException e) {
		    e.printStackTrace();
		}
	

	}
	
	 private void retrieveDataFromDatabase(JTextArea orderTA) {
	        try {
	            // Replace with your database credentials and connection details
	            String url = "jdbc:mysql://localhost:3306/grubhub";
	            String username = "root";
	            String password = "";

	            // Establish the database connection
	            Connection connection = DriverManager.getConnection(url, username, password);

	            // Create a statement object
	            Statement statement = connection.createStatement();

	            // Execute the query to retrieve data from your database table
	           
	            ResultSet resultSet = statement.executeQuery("SELECT * FROM queingtbl");

	            // Calculate the maximum width for each column
	            int productNameWidth = 15;
	            int quantityWidth = 10;
	            int priceWidth = 10;

	            // Generate the header row
	            String headerRow = String.format("%-" + productNameWidth + "s %-" + quantityWidth + "s %-" + priceWidth + "s\n",
	                    "Product", "Quantity", "Price");

	            // Append the header row to the text area
	            orderTA.append(headerRow);

	            // Generate the separator row
	            String separatorRow = "-".repeat(headerRow.length()) + "\n";

	            // Append the separator row to the text area
	            orderTA.append(separatorRow);

	            // Iterate over the result set and append data to the text area
	            while (resultSet.next()) {
	                String productName = resultSet.getString("ProductName");
	                int quantity = resultSet.getInt("Quantity");
	                double price = resultSet.getDouble("Price");

	                String formattedData = String.format("%-15s %-9d %11.2f\n", productName, quantity, price);
	                orderTA.append(formattedData);
	            }
	          
	           
	            
	            // Close the resources
	            resultSet.close();
	            statement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public void showMath(JLabel subtotal, JLabel totalLBL) {
		  // Database connection information
	        String url = "jdbc:mysql://localhost:3306/grubhub";
	        String username = "root";
	        String password = "";

	        // SQL query to calculate the sum of the column
	        String query = "SELECT sum(Price) from queingtbl";

	        try (Connection connection = DriverManager.getConnection(url, username, password);
	             PreparedStatement statement = connection.prepareStatement(query);
	             ResultSet resultSet = statement.executeQuery()) {

	            if (resultSet.next()) {
	                double sum = resultSet.getDouble(1);  // Retrieve the sum value from the first column
	               
	                // Create and configure the JLabel
	                subtotal.setText("" + sum);
	                double percentage = 0.12; // 12% expressed as decimal

	                double total = sum + (sum * percentage);
	                BigDecimal roundedNumber = BigDecimal.valueOf(total).setScale(2, BigDecimal.ROUND_HALF_UP);
	                totalLBL.setText("" + roundedNumber);
	                setSubtotal(roundedNumber);
	                
	                
	                
	                

	            }} catch (SQLException e) {
	            e.printStackTrace();
	        }
	    
	 
}
	 private BigDecimal Total;
		
		public void setSubtotal(BigDecimal roundedNumber) {
			
			this.Total = roundedNumber;
		
			
		}
		
		public BigDecimal getSubtotal() {
			
			return Total;
			
		}
		
		
		private Connection connection;
		
		
		public void setConnection(Connection connection) {
			
			this.connection = connection;

		}
		
	    public Connection getConnection() {
	    	
	    	return connection;
	    	
	    }
	
		
}
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	 
	
	
	
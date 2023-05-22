package FoodPOS;

import java.awt.EventQueue;
import Des.*;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PaymentGateway extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField cusnameTF;
	private JTextField contactTF;
	private JTextField amountTF;
	
private static final Color BORDER_COLOR = new Color(255, 0, 0);
	
	private void setTextFieldBorder(JTextField textField) {
	    textField.setBorder(new MatteBorder(1, 1, 1, 1, BORDER_COLOR));
	}
	
	private String getTextFromField(JTextField textField) {
	    return textField.getText().trim();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentGateway frame = new PaymentGateway();
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
	public PaymentGateway() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel warningLBL = new JLabel("");
		warningLBL.setVisible(false);
		warningLBL.setForeground(Color.RED);
		warningLBL.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		warningLBL.setBounds(22, 269, 397, 14);
		contentPane.add(warningLBL);
		
		JLabel lblNewLabel_1 = new JLabel("Customer Name");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(22, 93, 98, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contact#");
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(22, 134, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("OrderType");
		lblNewLabel_3.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(22, 173, 77, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Payment Method");
		lblNewLabel_4.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(22, 208, 98, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Amount");
		lblNewLabel_5.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(22, 244, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		cusnameTF = new JTextField();
		cusnameTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				e.getKeyCode();
			    cusnameTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));	
			}
		});
		cusnameTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cusnameTF.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		cusnameTF.setBounds(156, 90, 250, 20);
		contentPane.add(cusnameTF);
		cusnameTF.setColumns(10);
		
		contactTF = new JTextField();
		contactTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				cusnameTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				new OwnLib().setFieldtoNumber(contactTF, warningLBL);
			}
		});
		contactTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contactTF.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		contactTF.setBounds(156, 131, 250, 20);
		contentPane.add(contactTF);
		contactTF.setColumns(10);
		
		String[] orderType = {"Pick Up", "Delivery" };
		
		String[] paymentMethod = {"GCash", "Cash", "Credit Card" };
		JComboBox paymentCB = new JComboBox(paymentMethod);
		paymentCB.setBackground(Color.WHITE);
		paymentCB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		paymentCB.setFocusable(false);
		paymentCB.setFocusTraversalKeysEnabled(false);
		paymentCB.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		paymentCB.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		paymentCB.setBounds(156, 204, 250, 22);
		contentPane.add(paymentCB);
		
		amountTF = new JTextField();
		amountTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
              new OwnLib().setFieldtoNumber(amountTF, warningLBL);
			}
		});
		amountTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		amountTF.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		amountTF.setBounds(156, 241, 182, 20);
		contentPane.add(amountTF);
		amountTF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Total Purchased : ");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		lblNewLabel.setBounds(32, 294, 182, 38);
		contentPane.add(lblNewLabel);
		
		JLabel totalLBL = new JLabel(new SalesDBD().getSubtotal().toString());
		totalLBL.setHorizontalTextPosition(SwingConstants.CENTER);
		totalLBL.setHorizontalAlignment(SwingConstants.CENTER);
		totalLBL.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		totalLBL.setBounds(250, 294, 156, 38);
		contentPane.add(totalLBL);
		
		JRadioButton deliveryRButt = new JRadioButton("Delivery");
		deliveryRButt.setFocusable(false);
		deliveryRButt.setFocusPainted(false);
		deliveryRButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deliveryRButt.setOpaque(false);
		deliveryRButt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		deliveryRButt.setBounds(156, 167, 109, 23);
		contentPane.add(deliveryRButt);
		
		JRadioButton pickupRButt = new JRadioButton("Pick Up");
		pickupRButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pickupRButt.setOpaque(false);
		pickupRButt.setFocusable(false);
		pickupRButt.setFocusPainted(false);
		pickupRButt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		pickupRButt.setBounds(286, 167, 109, 23);
		contentPane.add(pickupRButt);
		
		ButtonGroup buttG = new ButtonGroup();
		buttG.add(deliveryRButt);
		buttG.add(pickupRButt);
		
		JButton checkoutButt = new JButton("CHECK OUT");
		checkoutButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String customerName = getTextFromField(cusnameTF);
				String contact = getTextFromField(contactTF);
				String Amount = getTextFromField(amountTF);
				String ordertype = deliveryRButt.isSelected() ? "DELIVERY" : "PICKUP";
				String paymenttype = (String) paymentCB.getSelectedItem();
				boolean allFieldsFilled = true;
				
				// Create instance of SalesDBD
				SalesDBD sales = new SalesDBD();

				
	           
				
				 for (JTextField textField : Arrays.asList(cusnameTF, contactTF, amountTF)) {
				     if (textField.getText().isEmpty()) {
				         setTextFieldBorder(textField);
				         warningLBL.setText("FILL UP REQUIRED FIELDS");
				         warningLBL.setVisible(true);
				         allFieldsFilled = false;
				     }
				 }
				 
				 // Check if a radio button is selected
				 if (!deliveryRButt.isSelected() && !pickupRButt.isSelected()) {
					 pickupRButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 0, 0)));
					 deliveryRButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 0, 0)));  
				}
				 
				 if(Double.parseDouble(Amount) < sales.getSubtotal().doubleValue()) {
						warningLBL.setText("INSUFFICIENT AMOUNT");
						warningLBL.setVisible(true);
						allFieldsFilled = false;
						
					}
		            

				
					 try {
				            // Establish database connections for both tables
				            Connection sourceConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grubhub", "root", "");
				            Connection destinationConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grubhub", "root", "");
				            
				            // Query to retrieve data from quiengtbl
				            String selectQuery = "SELECT ProductName FROM queingtbl";
				            
				            // Query to insert data into customertbl
				            String insertQuery = "INSERT INTO customertbl (CustomerName,ContactNumber,OrderType,OrderHistory,PaymentMethod,PurchasedAmount) VALUES (?,?,?,?,?,?)";
				            
				            // Query to delete the data into quiengtbl
				            String deleteQuery = "DELETE FROM queingtbl";
				            
				           
				            
				            // Prepare the select and insert statements
				            PreparedStatement selectStatement = sourceConnection.prepareStatement(selectQuery);
				            PreparedStatement insertStatement = destinationConnection.prepareStatement(insertQuery);
				            PreparedStatement deleteStatement = sourceConnection.prepareStatement(deleteQuery);
				           
				            
				            // Execute the select statement
				            ResultSet resultSet = selectStatement.executeQuery();
				            
				            // Process the retrieved data and concatenate into a single row
				            StringBuilder rowData = new StringBuilder();
				            while (resultSet.next()) {
				                String data = resultSet.getString("ProductName");
				                rowData.append(data).append(", "); // Use a separator of your choice
				               
				                
				                // You can modify the above line to include other columns if needed:
				                // String data = resultSet.getString("ProductName") + ", " + resultSet.getString("column2") + ", ";
				            }
				            
				            // Remove the trailing separator
				            if (rowData.length() > 0) {
				                rowData.setLength(rowData.length() - 2);
				            }
				            
				    if(rowData.isEmpty()) {        
				    	allFieldsFilled = false;
				    	warningLBL.setText("NO QUE");
				    	warningLBL.setVisible(true);
				    	
				    }	
				    	
				    	
				            
				        if(allFieldsFilled) {    
				            
				            // Insert the concatenated data into table2
				            insertStatement.setString(1, customerName);
				            insertStatement.setLong(2, Long.parseLong(contact));
				            insertStatement.setString(3, ordertype);				          
				            insertStatement.setString(4, rowData.toString());
				            insertStatement.setString(5, paymenttype);
				            insertStatement.setDouble(6, sales.getSubtotal().doubleValue());
				           
				            
				            insertStatement.executeUpdate();
				            
				            //DELETE THE EXISTING DATA ON QUIENG TBL
				            deleteStatement.executeUpdate();

				            
				            dispose();
				            new OwnLib().spawnFrame(new endFrame());
				        }
				            
				            // Close the result set, statements, and connections
				            resultSet.close();
				            selectStatement.close();
				            insertStatement.close();
				            sourceConnection.close();
				            destinationConnection.close();
				        } catch (SQLException e1) {
				            e1.printStackTrace();
				        }
				    }
			
					 
					 
				 
				
				
				
				
				
				
				
				
			});
		
		checkoutButt.setForeground(Color.WHITE);
		checkoutButt.setBackground(Color.DARK_GRAY);
		checkoutButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		checkoutButt.setHorizontalTextPosition(SwingConstants.CENTER);
		checkoutButt.setFocusable(false);
		checkoutButt.setFocusTraversalKeysEnabled(false);
		checkoutButt.setFocusPainted(false);
		checkoutButt.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		checkoutButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		checkoutButt.setBounds(0, 387, 450, 63);
		contentPane.add(checkoutButt);
		
		JLabel lblNewLabel_6 = new JLabel("Change :");
		lblNewLabel_6.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(29, 349, 166, 27);
		contentPane.add(lblNewLabel_6);
		
		JLabel changeLBL = new JLabel("");
		changeLBL.setHorizontalAlignment(SwingConstants.CENTER);
		changeLBL.setHorizontalTextPosition(SwingConstants.CENTER);
		changeLBL.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		changeLBL.setBounds(250, 343, 169, 33);
		contentPane.add(changeLBL);
		
		JButton calculateButt = new JButton("CALCU");
		calculateButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Amount = getTextFromField(amountTF);
				SalesDBD sales = new SalesDBD();
		         double change = Double.parseDouble(Amount) - sales.getSubtotal().doubleValue();
		         BigDecimal roundedNumber = BigDecimal.valueOf(change).setScale(2, BigDecimal.ROUND_HALF_UP);
	             changeLBL.setText("" + roundedNumber);
				
				
			}
		});
		calculateButt.setBackground(Color.DARK_GRAY);
		calculateButt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		calculateButt.setForeground(Color.WHITE);
		calculateButt.setFocusable(false);
		calculateButt.setFocusTraversalKeysEnabled(false);
		calculateButt.setFocusPainted(false);
		calculateButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		calculateButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		calculateButt.setBounds(348, 238, 92, 23);
		contentPane.add(calculateButt);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OwnLib().spawnFrame(new SalesDBD());
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFocusTraversalKeysEnabled(false);
		btnNewButton.setFocusable(false);
		btnNewButton.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		btnNewButton.setBounds(0, 0, 99, 27);
		contentPane.add(btnNewButton);
		
		JLabel BG = new JLabel("");
		BG.setBounds(0, 0, 450, 574);
		contentPane.add(BG);
		ImageIcon img1  = new ImageIcon(getClass().getResource("paymentBG.png"));
		Image image1 = (img1).getImage().getScaledInstance(	BG.getWidth(), 	BG.getHeight(), Image.SCALE_SMOOTH);
		img1 = new ImageIcon(image1);
		BG.setIcon(img1);
	}
	
	private double change;
	
	public void setChange(double change) {
		this.change = change;
		
	}
	
	public double getChange() {
		return change;
		
	}
}

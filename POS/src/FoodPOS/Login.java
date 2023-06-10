package FoodPOS;

import java.awt.EventQueue;
import Des.OwnLib;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Login extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField unameTF;
	private JPasswordField passwordTF;
	private JButton registerButt;
	private JLabel unShowICO;
	private JLabel warningLBL;
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();			  
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
	public Login() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setShape(new RoundRectangle2D.Double(0, 0, 700, 500,30, 30));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		warningLBL = new JLabel("New label");
		warningLBL.setFont(new Font("Tahoma", Font.PLAIN, 9));
		warningLBL.setForeground(Color.RED);
		warningLBL.setVisible(false);
		warningLBL.setBounds(443, 166, 222, 27);
		contentPane.add(warningLBL);
		
		unameTF = new JTextField();
		unameTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				e.getKeyCode();
			    warningLBL.setVisible(false);
			}
		});
		unameTF.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		unameTF.setBorder(new EmptyBorder(0, 0, 0, 0));
		unameTF.setBounds(443, 192, 222, 27);
		contentPane.add(unameTF);
		unameTF.setColumns(10);
		
		passwordTF = new JPasswordField();
		passwordTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				e.getKeyCode();
			    warningLBL.setVisible(false);
			}
		});
		passwordTF.setBorder(new EmptyBorder(0, 0, 0, 0));
		passwordTF.setBounds(443, 230, 222, 29);
		contentPane.add(passwordTF);
		
		
		
		
		JButton loginButt = new JButton("LOGIN");
		loginButt.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				String username = unameTF.getText();
				String password = passwordTF.getText();
				
				
				 try {
				        // Establish connection to the database
				        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grubhub", "root", "");
				     
				        // Create SQL statement to check if the credentials exist in the database
				        String query = "SELECT * FROM userinfos WHERE Username = ? AND Password = ?";
				        PreparedStatement statement = connection.prepareStatement(query);
				        statement.setString(1, username);
				        statement.setString(2, password);
				        ResultSet resultSet = statement.executeQuery();

				        // Check if any matching rows were found
				        boolean result = resultSet.next();
				    
				        
				        // If a matching row is found, retrieve the "Type" column value
				        if (result) {
				            
				        	String userType = resultSet.getString("Type");
				        	
				        	if(userType.equals("Employee")) {
				        		
				        		SalesDBD sales = new SalesDBD();
				        		sales.onDataReceived(userType);
				        		sales.setVisible(true);
				                
				                
				                dispose();
				        		
				        	} else {
				        		SalesDBD sales = new SalesDBD();
				        		sales.onDataReceived(userType);
				        		sales.setVisible(true);
				                
				        		
				        		
				        		
				        	}
				        	
				            // Close the current frame
				            dispose();
				            
			 	        } else {
			 	        	
			 	        	warningLBL.setText("Invalid Credentials. Please try again");
			 	        	warningLBL.setVisible(true);
			 	        	
			 	        	
			 	        }
				        
				      

				        // Close the database connection and resources
				        resultSet.close();
				        statement.close();
				        connection.close();
				   
				        
				    } catch (SQLException e1) {
				        e1.printStackTrace();
				        
				    }
			}
		});
		loginButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButt.setFocusable(false);
		loginButt.setForeground(Color.WHITE);
		loginButt.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		loginButt.setBackground(Color.DARK_GRAY);
		loginButt.setFocusPainted(false);
		
		loginButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		loginButt.setBounds(442, 270, 223, 29);
		contentPane.add(loginButt);
		
		JLabel lblNewLabel = new JLabel("or");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblNewLabel.setBounds(531, 310, 46, 14);
		contentPane.add(lblNewLabel);
		
		registerButt = new JButton("REGISTER");
		registerButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new OwnLib().spawnFrame(new Register());
				dispose();

			}
		});
		registerButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registerButt.setForeground(Color.WHITE);
		registerButt.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		registerButt.setFocusPainted(false);
		registerButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		registerButt.setBackground(Color.DARK_GRAY);
		registerButt.setBounds(442, 335, 223, 29);
		contentPane.add(registerButt);
		
		JLabel showICO = new JLabel("");
		showICO.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		showICO.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				unShowICO.setVisible(true);
				showICO.setVisible(false);
				passwordTF.setEchoChar((char)0);
				
			}
		});
		showICO.setBounds(669, 230, 29, 29);
		new ImageSize(showICO, "icons8-lock-30.png");
		contentPane.add(showICO);
		
		unShowICO = new JLabel("");
		unShowICO.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		unShowICO.setVisible(false);
		unShowICO.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				unShowICO.setVisible(false);
				showICO.setVisible(true);
				passwordTF.setEchoChar('●');
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		unShowICO.setBounds(669, 232, 29, 27);
		new ImageSize(unShowICO, "icons8-unlock-30.png");
		contentPane.add(unShowICO);
		
		
		
		JLabel BG = new JLabel("");
		BG.setBounds(0, 0, 698, 500);
		new ImageSize(BG , "loginBG.png");
	
		contentPane.add(BG);
	

		}	


		
}



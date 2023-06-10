package FoodPOS;

import java.awt.EventQueue;

import Des.*;
import javax.swing.text.PlainDocument;

import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Des.ImageSize;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import java.awt.Cursor;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;

public class Register extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fnameTF;
	private JTextField lnameTF;
	private JTextField bdayTF;
	private JTextField emailTF;
	private JTextField unameTF;
	private JPasswordField passwordTF;
	private JPasswordField rpasswordTF;
	private Connection con;
	private JLabel lblNewLabel;
	private JButton registerButt;
	
	
	private static final Color BORDER_COLOR = new Color(255, 0, 0);
	private JButton btnNewButton;
	
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
					Register frame = new Register();
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
	public Register() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setShape(new RoundRectangle2D.Double(0, 0, 700, 500,30, 30));
	
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel BG = new JLabel("");
		BG.setBounds(0, 0, 712, 507);
		ImageIcon img1  = new ImageIcon(getClass().getResource("registerBG.png"));
		Image image1 = (img1).getImage().getScaledInstance(	BG.getWidth(), 	BG.getHeight(), Image.SCALE_SMOOTH);
		img1 = new ImageIcon(image1);
		
		fnameTF = new JTextField();
		fnameTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				e.getKeyCode();
			    fnameTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));	
			}
		});
		
		JLabel warningLBL = new JLabel("Please enter a valid email address.");
		warningLBL.setFont(new Font("Tahoma", Font.PLAIN, 9));
		warningLBL.setVisible(false);
		
		lblNewLabel = new JLabel("yyyy-MM-dd");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(612, 217, 72, 37);
		contentPane.add(lblNewLabel);
		warningLBL.setForeground(Color.RED);
		warningLBL.setBounds(365, 115, 319, 34);
		contentPane.add(warningLBL);
		fnameTF.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		fnameTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		fnameTF.setBounds(469, 155, 206, 20);
		contentPane.add(fnameTF);
		fnameTF.setColumns(10);
		
		lnameTF = new JTextField();
		lnameTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				e.getKeyCode();
			    lnameTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));	
			}
		});
		lnameTF.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lnameTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lnameTF.setColumns(10);
		lnameTF.setBounds(469, 186, 206, 20);
		contentPane.add(lnameTF);
		
		bdayTF = new JTextField();
		bdayTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				e.getKeyCode();
				bdayTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));	
			}
		});
		bdayTF.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		bdayTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		bdayTF.setColumns(10);
		bdayTF.setBounds(469, 243, 206, 20);
		contentPane.add(bdayTF);
		
		unameTF = new JTextField();
		unameTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				e.getKeyCode();
				unameTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));	
			}
		});
		 // Chech if username length is less than 7
		 unameTF.setDocument(new JTextFieldLimit(20)); // set the maximum limit to 20 characters
		// Add a listener to the JTextField to check the length of the text entered
		unameTF.getDocument().addDocumentListener(new DocumentListener() {
		    @SuppressWarnings("deprecation")
			@Override
		    public void insertUpdate(DocumentEvent e) {
		        // Check if the length of the username is less than 7 characters
		    	
		        if (unameTF.getText().length() < 8) {
		           warningLBL.setText("Username must be at least 8 characters long.");
		           warningLBL.setVisible(true);	
				   setTextFieldBorder(unameTF);
				   registerButt.disable();
				  
				   
				
		        } 
		        else {
		        	registerButt.enable();
		        		 warningLBL.setVisible(false);
		        }
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        // Not needed for this example
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        // Not needed for this example
		    }
		});
		
		unameTF.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		unameTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		unameTF.setColumns(10);
		unameTF.setBounds(469, 307, 206, 22);
		
		contentPane.add(unameTF);
		
		emailTF = new JTextField();
		emailTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				e.getKeyCode();
				emailTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));	
				warningLBL.setVisible(false);
			}
		});
		emailTF.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		emailTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		emailTF.setColumns(10);
		emailTF.setBounds(469, 274, 206, 20);
		contentPane.add(emailTF);
		
		passwordTF = new JPasswordField();
		passwordTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				e.getKeyCode();
				passwordTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
                warningLBL.setVisible(false);
			}
		});
		passwordTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		passwordTF.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		passwordTF.setBounds(469, 340, 206, 20);
		contentPane.add(passwordTF);
		
		rpasswordTF = new JPasswordField();
		rpasswordTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				e.getKeyCode();
				rpasswordTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));	
			}
		});
		rpasswordTF.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		rpasswordTF.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		rpasswordTF.setBounds(539, 371, 136, 20);
		contentPane.add(rpasswordTF);
		
		JRadioButton maleRbutt = new JRadioButton("MALE");
		maleRbutt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		maleRbutt.setContentAreaFilled(false);
		maleRbutt.setHorizontalAlignment(SwingConstants.CENTER);
		maleRbutt.setFocusable(false);
		maleRbutt.setFocusPainted(false);
		maleRbutt.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		maleRbutt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		maleRbutt.setBackground(Color.WHITE);
		maleRbutt.setBounds(469, 213, 62, 23);
		contentPane.add(maleRbutt);
		
		JRadioButton femaleRbutt = new JRadioButton("FEMALE");
		femaleRbutt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		femaleRbutt.setContentAreaFilled(false);
		femaleRbutt.setHorizontalAlignment(SwingConstants.CENTER);
		femaleRbutt.setFocusable(false);
		femaleRbutt.setFocusPainted(false);
		femaleRbutt.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		femaleRbutt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		femaleRbutt.setBackground(Color.WHITE);
		femaleRbutt.setBounds(533, 213, 69, 23);
		contentPane.add(femaleRbutt);
		
		ButtonGroup buttG = new ButtonGroup();
		buttG.add(maleRbutt);
		buttG.add(femaleRbutt);
		
		
	    registerButt = new JButton("REGISTER");
		registerButt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String fname = getTextFromField(fnameTF);
				String lname = getTextFromField(lnameTF);
				String bday = getTextFromField(bdayTF);
				String email = getTextFromField(emailTF);
				String username = getTextFromField(unameTF);
				String password = getTextFromField(passwordTF);
				String rpassword = getTextFromField(rpasswordTF);
				boolean allFieldsFilled = true;

				List<JTextField> textFields = Arrays.asList(fnameTF, lnameTF, bdayTF, emailTF, passwordTF, rpasswordTF, unameTF);

				for (JTextField textField : textFields) {
				    if (textField.getText().isEmpty()) {
				        setTextFieldBorder(textField);
				        warningLBL.setText("FILL UP REQUIRED FIELDS");
				        warningLBL.setVisible(true);
				        allFieldsFilled = false;
				    }
				}

				if (!maleRbutt.isSelected() && !femaleRbutt.isSelected()) {
				    femaleRbutt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 0, 0)));
				    maleRbutt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 0, 0)));
				    allFieldsFilled = false;
				}

				if (!email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
				    setTextFieldBorder(emailTF);
				    warningLBL.setText("Invalid Email");
				    warningLBL.setVisible(true);
				    allFieldsFilled = false;
				}

				if (!(password.equals(rpassword))) {
				    setTextFieldBorder(passwordTF);
				    setTextFieldBorder(rpasswordTF);
				    warningLBL.setText("Passwords don't match. Please try again.");
				    warningLBL.setVisible(true);
				    passwordTF.setText("");
				    rpasswordTF.setText("");
				    allFieldsFilled = false;
				}

				if (allFieldsFilled) {
					 try {
					        Class.forName("com.mysql.cj.jdbc.Driver");
					        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grubhub", "root", "");

					        PreparedStatement insertInfo = con.prepareStatement("insert into userinfos (Firstname, Lastname, Gender, Birthday, Email, Username, Password, Type)  VALUES (?,?,?,?,?,?,?,?)");
					        PreparedStatement checkUserExists = con.prepareStatement("SELECT Type FROM userinfos WHERE Type = 'Admin' OR Username = ?");
					        checkUserExists.setString(1, username);
					        ResultSet rs = checkUserExists.executeQuery();

					        String userType = rs.next() ? "Employee" : "Admin";

					        String gender = maleRbutt.isSelected() ? "MALE" : "FEMALE";
					        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					        Date date = formatter.parse(bday);
					        String formattedDate = formatter.format(date);

					        insertInfo.setString(1, fname);
					        insertInfo.setString(2, lname);
					        insertInfo.setString(3, gender);
					        insertInfo.setString(4, formattedDate);
					        insertInfo.setString(5, email);
					        insertInfo.setString(6, username);
					        insertInfo.setString(7, rpassword);
					        insertInfo.setString(8, userType);

					        insertInfo.executeUpdate();
					        insertInfo.close();
					        con.close();

					        JOptionPane.showMessageDialog(null, userType.equals("Admin") ? "ADMIN REGISTERED" : "SUCCESSFULLY REGISTERED");
					        dispose();

					        Login log = new Login();
					        log.setLocationRelativeTo(null);
					        log.setVisible(true);
					    } catch (ClassNotFoundException | SQLException e1) {
					        e1.printStackTrace();
					    } catch (ParseException e2) {
					        e2.printStackTrace();
					    }
				}
			}
		});
		registerButt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registerButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		registerButt.setFocusTraversalKeysEnabled(false);
		registerButt.setFocusable(false);
		registerButt.setFocusPainted(false);
		registerButt.setForeground(Color.WHITE);
		registerButt.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		registerButt.setBackground(Color.DARK_GRAY);
		registerButt.setBounds(365, 402, 325, 37);
		contentPane.add(registerButt);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
	

			@Override
			public void actionPerformed(ActionEvent e) {
				fnameTF.setText("");
				lnameTF.setText("");
				bdayTF.setText("");
				unameTF.setText("");
				emailTF.setText("");
				passwordTF.setText("");
				rpasswordTF.setText("");
			    maleRbutt.setSelected(false);
			    femaleRbutt.setSelected(false);

				
			}
		});
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		btnClear.setFocusable(false);
		btnClear.setFocusTraversalKeysEnabled(false);
		btnClear.setFocusPainted(false);
		btnClear.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnClear.setBackground(Color.DARK_GRAY);
		btnClear.setBounds(365, 450, 325, 37);
		contentPane.add(btnClear);
		
		btnNewButton = new JButton("BACK");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFocusTraversalKeysEnabled(false);
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				new OwnLib().spawnFrame(new Login());
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		btnNewButton.setBounds(0, 0, 89, 23);
		contentPane.add(btnNewButton);
		BG.setIcon(img1);

		contentPane.add(BG);
	}
}

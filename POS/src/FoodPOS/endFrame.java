package FoodPOS;

import java.awt.EventQueue;
import java.awt.Image;
import Des.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Font;

public class endFrame extends JFrame implements DataListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					endFrame frame = new endFrame();
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
        this.data = data;
        System.out.println("\nReceived data: " + data);

    }
	
	public endFrame() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SalesDBD sales = new SalesDBD();
				sales.onDataReceived(data);
				sales.setLocationRelativeTo(null);
				sales.setVisible(true);
				dispose();
			}
		});
		lblNewLabel.setBounds(196, 215, 64, 74);
		contentPane.add(lblNewLabel);
		
	    
		
		
		JLabel BG = new JLabel("");
		BG.setBounds(0, 0, 450, 300);
		contentPane.add(BG);
		//new ImageSize(BG, "thankyouBG.png");

		ImageIcon img1  = new ImageIcon(getClass().getResource("thankyouBG.png"));
		Image image1 = (img1).getImage().getScaledInstance(	BG.getWidth(), 	BG.getHeight(), Image.SCALE_SMOOTH);
		img1 = new ImageIcon(image1);
		BG.setIcon(img1);
	}
	
	
}

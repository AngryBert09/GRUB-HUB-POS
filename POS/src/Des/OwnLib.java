package Des;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class OwnLib {
	
	private static final Color BORDER_COLOR_RED = new Color(255, 0, 0);
	
	public void spawnFrame(JFrame frame) {
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void setHoverEffect(JButton button) {
		
		  button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					button.setContentAreaFilled(true);
					button.setBackground(Color.gray);
				    button.setForeground(Color.white);
					
				}
				@Override
				public void mouseExited(MouseEvent e) {
					button.setContentAreaFilled(false);
					button.setForeground(Color.black);
				}
			});	
		}
	
	public void setFieldtoNumber(JTextField field, JLabel warningLBL) {
		
		field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				field.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
				
				if(e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
				   
				   field.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
                   field.setEditable(true);
					
				}else {
					if(e.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE || e.getExtendedKeyCode()==KeyEvent.VK_DELETE) {
					
			        field.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		            field.setEditable(true);	
					field.setEditable(true);
					
				}else {
					
				   field.setBorder(new MatteBorder(1, 1, 1, 1, BORDER_COLOR_RED));
				   warningLBL.setText("ENTER NUMBER ONLY");
				   warningLBL.setVisible(true);
				   field.setEditable(false);
					
				}
				
			}
			}
		
		
		});		
		
	}	
		
}		
	
	
	
	
	
	


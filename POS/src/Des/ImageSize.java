package Des;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageSize {
	
	private String path;
	private JLabel label;
	
	
	public void setImageSize(JLabel label, String path) {
		
		ImageIcon img1  = new ImageIcon(path);
		Image image1 = (img1).getImage().getScaledInstance(	label.getWidth(), 	label.getHeight(), Image.SCALE_SMOOTH);
		img1 = new ImageIcon(image1);
		label.setIcon(img1);
		
		
		
	}
	
	
	
	

}

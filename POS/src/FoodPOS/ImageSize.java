package FoodPOS;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageSize {
	
	public ImageSize(JLabel label, String image) {
		
		ImageIcon img1  = new ImageIcon(getClass().getResource(image));
		Image image1 = (img1).getImage().getScaledInstance(	label.getWidth(), 	label.getHeight(), Image.SCALE_SMOOTH);
		img1 = new ImageIcon(image1);
		label.setIcon(img1);

	}

}

package FoodPOS;

import java.security.KeyStore.Entry.Attribute;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument {
	
	private int limit;
	
	
public JTextFieldLimit (int limitation) {
	
	this.limit = limitation;
}
	public void insertString(int offset, String str, AttributeSet set) throws BadLocationException {
		
		if (str==null) {
			
			return;
		}else if((getLength() + str.length()) <= limit) {
			str = str.toUpperCase();
			super.insertString(offset, str, set);
		}
	
		
	}

		
		
	}


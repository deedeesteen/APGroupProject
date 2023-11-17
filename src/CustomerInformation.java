import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerInformation extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private ImageIcon bkgrd;
	private JLabel custdash, infolabel, bkgrdlabel;
	private JInternalFrame internalFrame;
	private Color GRIZ = new Color(130, 110, 90);
	private Color TAN = new Color(232, 231, 177);
	private Color ROSY = new Color(255, 87, 70);
	
	public CustomerInformation() {
		this.custInfoGUI();
	}

	private void custInfoGUI() {
		internalFrame = new JInternalFrame("Customer Information");

		custdash = new JLabel("Customer Dashboard");
		custdash.setFont(new Font("Verdana", Font.BOLD, 30));
		custdash.setForeground(Color.WHITE);
		custdash.setBounds(400, 50, 1000, 50);
		
		infolabel = new JLabel("Customer Information");
		infolabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 30));
		infolabel.setForeground(ROSY);
		infolabel.setBounds(505, 50, 1000, 50);
	}
}

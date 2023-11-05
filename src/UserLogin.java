package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class UserLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Image icon, image;
	private String logo = "./images/logo.png";
	private JLabel lgn;
	private JTextField userID, password;
	private JButton lgnButton;
	private Color GRIZ = new Color(130, 110, 90);
	private Color TAN = new Color(232, 231, 177);
	private Color ROSY = new Color(255, 87, 70);
	
	public UserLogin() {
		this.loginPage();
	}
	
	public void loginPage() {
		frame = new JFrame("Grizzly's Entertainment Equipment Rental");
		frame.setSize(1500,800);
		Container container = frame.getContentPane();
		lgn = new JLabel("Welcome to Grizzly's Entertainment Equipment Rental", JLabel.CENTER);
		lgn.setFont(new Font("Verdana", Font.BOLD, 30));
		//Changes the color of the text
		lgn.setForeground(Color.BLACK);
		lgn.setBounds(100, 50, 1200, 50);
		
		ImageIcon imageIcon = new ImageIcon(logo);
        image = imageIcon.getImage();
        //Resize image
        Image resized = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        
        //Create new ImageIcon with the resized image
        ImageIcon resizedImage = new ImageIcon(resized);
        
		JLabel logolbl = new JLabel(resizedImage);
		logolbl.setBounds(675, 250, 150, 150);
		
		//Create JTextField with placeholder text
		userID = new JTextField();
		setPlaceholder(userID, "Customer ID");
		userID.setBounds(350, 100, 350, 35);
		password = new JTextField();
		setPlaceholder(password, "Password");
		password.setBounds(350, 150, 350, 35);
		//Changes the app icon (as seen in the task bar and top left of the window)
		icon = Toolkit.getDefaultToolkit().getImage("./images/icon.png");
        
		lgnButton = new JButton("LOGIN");
		lgnButton.setBounds(450, 300, 100, 40);
		//Changes the color of the text in the button
		lgnButton.setForeground(Color.WHITE);
		//Changes the color of the button
        lgnButton.setBackground(GRIZ);
        /*lgnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginAction();
			}
		});*/
        
        frame.setLayout(new BorderLayout());
        container.setBackground(TAN);
        container.add(logolbl);
        container.add(lgn);
		frame.setIconImage(icon);
		frame.add(userID);
		frame.add(password);
		frame.add(lgnButton);
		frame.setLayout(null);
		frame.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setPlaceholder(JTextField textFld, String placehldr) {
		textFld.setForeground(Color.GRAY);
		textFld.setText(placehldr);
		
		textFld.addFocusListener(new FocusListener() {
			//Removes placeholder from text field
			@Override
			public void focusGained(FocusEvent e) {
				if(textFld.getText().equals(placehldr)) {
					textFld.setText("");
					textFld.setForeground(Color.BLACK);
				}
			}

			//Adds placeholder back to text field
			@Override
			public void focusLost(FocusEvent e) {
				if(textFld.getText().isEmpty()) {
					textFld.setForeground(Color.GRAY);
					textFld.setText(placehldr);
				}
			}
		});
	}
	
	/*USE SERIALIZABLE FILES (1 FOR CUSTOMER, 1 FOR STAFF) TO WRITE AND STORE THE IDS AND PASSWORDS
	 * public void loginAction() {
		String uid = userID.getText();
		String pword = String.valueOf(password.getPassword());
		
		if () {
			JOptionPane.showMessageDialog(null, "Login Successful");
			//Call the method to get the Customer or Staff Dashboard
			frame.dispose(); //Closes the frame
		} else {
			JOptionPane.showMessageDialog(null, "ID or Password is incorrect.\nPlease try again.");
		}
	}*/

	public static void main(String args[]) {
		new UserLogin();
	}
}
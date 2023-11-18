import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JInternalFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import client.Customer;

public class EmployeeDashboard extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame eframe;
	private JLayeredPane desktop;
	private Image icon;
	private ImageIcon maleIcon, femaleIcon, bkgrd;
	private JLabel empdash, bkgrdlabel;
	private JMenuBar menuBar;
	private JMenu equip, requests;
	private Color GRIZ = new Color(130, 110, 90);
	private Color TAN = new Color(232, 231, 177);
	private Color ROSY = new Color(255, 87, 70);
	
	public EmployeeDashboard() {
		this.dashGUI();
	}
	
	public void dashGUI() {
		//Create a new JFrame
		eframe = new JFrame("Grizzly's Entertainment Equipment Rental");
		//Create a new JDesktopPane
		desktop = new JDesktopPane();
		//Create a new JMenuBar
		menuBar = new JMenuBar();
		
		empdash = new JLabel("Employee Dashboard");
		empdash.setFont(new Font("Verdana", Font.BOLD, 30));
		empdash.setForeground(Color.BLACK);
		empdash.setBounds(600, 50, 1200, 50);
		
		//Loads the app icon (as seen in the task bar and top left of the window)
		icon = Toolkit.getDefaultToolkit().getImage("./images/icon.png");
		
		//Loads the app background image
		bkgrd = new ImageIcon("./images/bkgrd.png");
		//Set image to the label
		bkgrdlabel = new JLabel(bkgrd);
		bkgrdlabel.setBounds(370, 0, bkgrd.getIconWidth(), bkgrd.getIconHeight());
		
		JMenu menu = new JMenu("Menu");
        
        menuBar = new JMenuBar();
        menuBar.add(createIconMenu(menu));
        menuBar.add(Box.createHorizontalGlue());
        menuBar.setBackground(GRIZ);
        
        JMenuItem equip = new JMenuItem("All Equipment");
        JMenuItem requests = new JMenuItem("Rental Requests");

	equip.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        requests.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
		
		//Add JMenu components to the MenuBar
		menu.add(equip);
		menu.add(requests);
		
		//Add a new JInternalFrame to the JDesktopPane with a specified layer
		desktop.add(new JInternalFrame(), JLayeredPane.POPUP_LAYER);
		//Add components to the desktop & frame
		desktop.add(empdash);
		desktop.add(bkgrdlabel, JLayeredPane.DEFAULT_LAYER);
		desktop.setBackground(TAN);
		eframe.getContentPane().add(desktop, BorderLayout.CENTER);
		eframe.setJMenuBar(menuBar);
		//Changes the app icon
		eframe.setIconImage(icon);
		eframe.setSize(1500,800);
		eframe.setVisible(true);
		eframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JMenu createIconMenu(JMenu menu) {
        Customer cust = new Customer();
        ImageIcon genderIcon = new ImageIcon();
        if (cust != null) {
            String gender = cust.getGender();
            if (gender.equalsIgnoreCase("Male")) {
                genderIcon = new ImageIcon("./images/male.png");
            } else {
                genderIcon = new ImageIcon("./images/female.png");
            }
        }

        menu.setIcon(genderIcon);
        return menu;
    }

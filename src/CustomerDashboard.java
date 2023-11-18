import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class CustomerDashboard extends JFrame {

    private static final Logger logger = LogManager.getLogger(CustomerDashboard.class);
    
    private static final long serialVersionUID = 1L;
    private JFrame cdframe;
    private JDesktopPane desktop;
    private Image icon;
    private ImageIcon bkgrd;
    private JLabel custdash, bkgrdlabel;
    private JMenuBar menuBar;
    private Color GRIZ = new Color(130, 110, 90);
    private Color TAN = new Color(232, 231, 177);
    private Color ROSY = new Color(255, 87, 70);

    public CustomerDashboard() {
        this.dashGUI();
    }

    public void dashGUI() {
        cdframe = new JFrame("Grizzly's Entertainment Equipment Rental");
        desktop = new JDesktopPane();

        custdash = new JLabel("Customer Dashboard");
        custdash.setFont(new Font("Verdana", Font.BOLD, 30));
        custdash.setForeground(Color.BLACK);
        custdash.setBounds(550, 50, 1200, 50);

        icon = Toolkit.getDefaultToolkit().getImage("./images/icon.png");

        bkgrd = new ImageIcon("./images/bkgrd.png");
        bkgrdlabel = new JLabel(bkgrd);
        bkgrdlabel.setBounds(370, 0, bkgrd.getIconWidth(), bkgrd.getIconHeight());

        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("Verdana", Font.BOLD, 15));
        menu.setForeground(Color.WHITE);
        
        menuBar = new JMenuBar();
        menuBar.add(createIconMenu(menu));
        menuBar.add(Box.createHorizontalGlue());
        menuBar.setBackground(GRIZ);
        
        JMenuItem custinfo = new JMenuItem("Customer Information");
        JMenuItem equip = new JMenuItem("Rent Equipment");

        custinfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
        equip.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
        
        equip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 RentEquipment rental = new RentEquipment();
                 desktop.add(rental);
                 rental.setVisible(true);
                 try {
                     rental.setSelected(true);
                 } catch (Exception ex) {
                     ex.printStackTrace();
                 }
                    logger.error("Exception occurred while setting selected window", ex);

            }
        });
        
        JMenuItem transac = new JMenuItem("Transactions");
        JMenuItem contact = new JMenuItem("Contact Us");

        transac.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK));
        contact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));

        transac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle transactions
            }
        });

        custinfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle customer information
            }
        });

        contact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle contact
            }
        });

        menu.add(custinfo);
        menu.add(equip);
        menu.add(transac);
        menu.add(contact);
        
        desktop.add(new JInternalFrame(), JLayeredPane.POPUP_LAYER);
        desktop.add(menuBar);
        desktop.add(custdash);
        desktop.add(bkgrdlabel, JLayeredPane.DEFAULT_LAYER);
        desktop.setBackground(TAN);

        cdframe.getContentPane().add(desktop, BorderLayout.CENTER);
        cdframe.setJMenuBar(menuBar);
        cdframe.setIconImage(icon);
        cdframe.setSize(1500, 800);
        cdframe.setVisible(true);
        cdframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        logger.info("Customer Dashboard initialized successfully.");
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

    public static void main(String args[]) {

        logger.debug("CustomerDashboard application started.");
        new CustomerDashboard();
    }
}

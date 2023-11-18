package controller;

import models.*;

/* 
import client.Customer;
import client.Employee;
import client.User;
import main.Controller;
*/
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class UserLogin extends JFrame {
    private static final long serialVersionUID = 1L;
    private JFrame frame;
    private Image icon;
    private ImageIcon bkgrd;
    private JLabel lgn, bkgrdlbl, or;
    private JTextField userID, password;
    private JButton lgnButton, signUpBtn;
    private Color GRIZ = new Color(130, 110, 90);
    private Color TAN = new Color(232, 231, 177);
    private Color ROSY = new Color(255, 87, 70);

    public UserLogin() {
        this.loginPage();
    }

    public void loginPage() {
        frame = new JFrame("Grizzly's Entertainment Equipment Rental");
        frame.setSize(1500, 800);
        Container container = frame.getContentPane();
        lgn = new JLabel("Welcome to Grizzly's Entertainment Equipment Rental", JLabel.CENTER);
        lgn.setFont(new Font("Verdana", Font.BOLD, 30));
        // Changes the color of the text
        lgn.setForeground(Color.BLACK);
        lgn.setBounds(165, 80, 1200, 50);

        // Loads the app background image
        bkgrd = new ImageIcon("./images/bkgrd.png");
        // Set image to the label
        bkgrdlbl = new JLabel(bkgrd);
        bkgrdlbl.setBounds(370, 0, bkgrd.getIconWidth(), bkgrd.getIconHeight());

        // Create JTextFields with placeholder text
        userID = new JTextField();
        setPlaceholder(userID, "Customer ID");
        userID.setFont(new Font("Verdana", Font.PLAIN, 15));
        userID.setBounds(550, 200, 350, 50);
        password = new JTextField();
        setPlaceholder(password, "Password");
        password.setFont(new Font("Verdana", Font.PLAIN, 15));
        password.setBounds(550, 300, 350, 50);
        // Loads the app icon (as seen in the task bar and top left of the window)
        icon = Toolkit.getDefaultToolkit().getImage("./images/icon.png");

        lgnButton = new JButton("LOGIN");
        lgnButton.setBounds(655, 400, 110, 55);
        lgnButton.setFont(new Font("Verdana", Font.BOLD, 13));
        // Changes the color of the text in the button
        lgnButton.setForeground(Color.WHITE);
        // Changes the color of the button
        lgnButton.setBackground(GRIZ);
        lgnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAction();
            }
        });

        or = new JLabel("OR");
        or.setBounds(695, 495, 50, 35);
        or.setFont(new Font("Verdana", Font.BOLD, 15));

        signUpBtn = new JButton("SIGN UP");
        signUpBtn.setBounds(655, 555, 110, 55);
        signUpBtn.setFont(new Font("Verdana", Font.BOLD, 13));
        signUpBtn.setForeground(Color.WHITE);
        signUpBtn.setBackground(ROSY);
        signUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserSignUp();
                frame.dispose(); // Closes the frame
            }
        });

        // Add components to the container
        container.setBackground(TAN);
        container.add(lgn);
        container.add(userID);
        container.add(password);
        container.add(lgnButton);
        container.add(or);
        container.add(signUpBtn);
        container.add(bkgrdlbl);

        // Changes the app icon
        frame.setIconImage(icon);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setPlaceholder(JTextField textFld, String placehldr) {
        textFld.setForeground(Color.GRAY);
        textFld.setText(placehldr);

        textFld.addFocusListener(new FocusListener() {
            // Removes placeholder from text field
            @Override
            public void focusGained(FocusEvent e) {
                if (textFld.getText().equals(placehldr)) {
                    textFld.setText("");
                    textFld.setForeground(Color.BLACK);
                }
            }

            // Adds placeholder back to text field
            @Override
            public void focusLost(FocusEvent e) {
                if (textFld.getText().isEmpty()) {
                    textFld.setForeground(Color.GRAY);
                    textFld.setText(placehldr);
                }
            }
        });
    }

    public void loginAction() {
        ArrayList<User> list = new ArrayList<>();
        User user = new User();
        Customer cust = new Customer();
        Employee emp = new Employee();

        int uid = Integer.parseInt(userID.getText().trim());
        String pword = String.valueOf(password.getText());

        user.setid(uid);
        user.setPassword(pword);

        /*
         * list.add(user);
         * System.out.println(uid);
         * System.out.println(pword);
         */

        // Check if user's id is either a customer id or employee id
        if (cust.read(uid, pword) != null) {
            // User is a customer
            CustomerDashboard custDash = new CustomerDashboard();
            JOptionPane.showMessageDialog(null, "Customer Login Successful!", "LOGIN STATUS",
                    JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            return;
        }

        if (emp.read(uid, pword) != null) {
            // User is an employee
            EmployeeDashboard employeeDash = new EmployeeDashboard();
            JOptionPane.showMessageDialog(null, "Employee Login Successful!", "LOGIN STATUS",
                    JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            return;
        }

        // Controller.saveToFile(list); //WHY THIS?
        //// boolean loginOk = Controller.readUserFromFile(uid, pword, gender);
        // Controller.readFromFile();

        // If neither customer nor staff, show an error message
        JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.", "LOGIN ERROR",
                JOptionPane.ERROR_MESSAGE);
    }

    /*
     * public static void main(String args[]) {
     * new UserLogin();
     * }
     */
}

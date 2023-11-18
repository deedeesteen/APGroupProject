package controller;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import models.*;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class UserSignUp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JFrame frame;
    private Image icon;
    private ImageIcon bkgrd;
    private JLabel signup, bkgrdlbl, rolelbl, genderlbl;
    private JTextField idTextField, nameTextField, passwordTextField, confirmTextField;
    private JCheckBox custCheckbox, empCheckbox;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private JButton lgnButton, signUpBtn;
    private ButtonGroup roleBtnGrp, genderBtnGrp;
    private Color GRIZ = new Color(130, 110, 90);
    private Color TAN = new Color(232, 231, 177);
    private Color ROSY = new Color(255, 87, 70);

    public UserSignUp() {
        this.signUpAction();
    }

    public void signUpAction() {
        frame = new JFrame("Grizzly's Entertainment Equipment Rental");
        frame.setSize(1500, 800);
        Container container = frame.getContentPane();
        signup = new JLabel("Sign Up for Grizzly's Entertainment Equipment Rental", JLabel.CENTER);
        signup.setFont(new Font("Verdana", Font.BOLD, 30));
        // Changes the color of the text
        signup.setForeground(Color.BLACK);
        signup.setBounds(165, 30, 1200, 50);

        // Loads the app background image
        bkgrd = new ImageIcon("./images/bkgrd.png");
        // Set image to the label
        bkgrdlbl = new JLabel(bkgrd);
        bkgrdlbl.setBounds(370, 0, bkgrd.getIconWidth(), bkgrd.getIconHeight());

        // Create JTextFields with placeholder text
        nameTextField = new JTextField();
        setPlaceholder(nameTextField, "Enter First & Last Name");
        nameTextField.setFont(new Font("Verdana", Font.PLAIN, 15));
        nameTextField.setBounds(550, 110, 350, 50);
        idTextField = new JTextField();
        setPlaceholder(idTextField, "Enter Customer ID");
        idTextField.setFont(new Font("Verdana", Font.PLAIN, 15));
        idTextField.setBounds(550, 190, 350, 50);
        passwordTextField = new JTextField();
        setPlaceholder(passwordTextField, "Enter Password");
        passwordTextField.setFont(new Font("Verdana", Font.PLAIN, 15));
        passwordTextField.setBounds(550, 270, 350, 50);

        confirmTextField = new JTextField();
        setPlaceholder(confirmTextField, "Confirm Password");
        confirmTextField.setFont(new Font("Verdana", Font.PLAIN, 15));
        confirmTextField.setBounds(550, 350, 350, 50);

        // Loads the app icon (as seen in the task bar and top left of the window)
        icon = Toolkit.getDefaultToolkit().getImage("./images/icon.png");

        rolelbl = new JLabel("Role:");
        rolelbl.setFont(new Font("Verdana", Font.BOLD, 15));
        rolelbl.setBounds(550, 430, 80, 35);

        custCheckbox = new JCheckBox(" Customer");
        custCheckbox.setFont(new Font("Verdana", Font.PLAIN, 15));
        custCheckbox.setBounds(655, 430, 105, 35);
        custCheckbox.setOpaque(false);

        empCheckbox = new JCheckBox(" Employee");
        empCheckbox.setFont(new Font("Verdana", Font.PLAIN, 15));
        empCheckbox.setBounds(795, 430, 105, 35);
        empCheckbox.setOpaque(false);

        roleBtnGrp = new ButtonGroup();
        roleBtnGrp.add(custCheckbox);
        roleBtnGrp.add(empCheckbox);

        custCheckbox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

            }
        });

        empCheckbox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

            }
        });

        genderlbl = new JLabel("Gender:");
        genderlbl.setFont(new Font("Verdana", Font.BOLD, 15));
        genderlbl.setBounds(550, 475, 90, 35);

        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setFont(new Font("Verdana", Font.PLAIN, 15));
        maleRadioButton.setBounds(655, 475, 85, 35);
        maleRadioButton.setOpaque(false);
        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setFont(new Font("Verdana", Font.PLAIN, 15));
        femaleRadioButton.setBounds(795, 475, 85, 35);
        femaleRadioButton.setOpaque(false);

        genderBtnGrp = new ButtonGroup();
        genderBtnGrp.add(maleRadioButton);
        genderBtnGrp.add(femaleRadioButton);

        signUpBtn = new JButton("SIGN UP");
        signUpBtn.setBounds(670, 545, 110, 55);
        signUpBtn.setFont(new Font("Verdana", Font.BOLD, 13));
        signUpBtn.setForeground(Color.WHITE);
        signUpBtn.setBackground(ROSY);
        signUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUpnSave();
            }
        });

        JLabel lgnlbl = new JLabel("Already have an account?");
        lgnlbl.setFont(new Font("Verdana", Font.ITALIC, 15));
        lgnlbl.setBounds(630, 620, 250, 35);

        lgnButton = new JButton("LOGIN");
        lgnButton.setBounds(670, 680, 110, 55);
        lgnButton.setFont(new Font("Verdana", Font.BOLD, 13));
        // Changes the color of the text in the button
        lgnButton.setForeground(Color.WHITE);
        // Changes the color of the button
        lgnButton.setBackground(GRIZ);
        lgnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserLogin();
                frame.dispose(); // Closes the frame
            }
        });

        // Add components to the container
        container.setBackground(TAN);
        container.add(signup);
        container.add(nameTextField);
        container.add(idTextField);
        container.add(passwordTextField);
        container.add(confirmTextField);
        container.add(rolelbl);
        container.add(custCheckbox);
        container.add(empCheckbox);
        container.add(genderlbl);
        container.add(maleRadioButton);
        container.add(femaleRadioButton);
        container.add(signUpBtn);
        container.add(lgnlbl);
        container.add(lgnButton);
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

    public void signUpnSave() {
        // Employee emp = new Employee();

        // Downcasting
        // user user = new Customer();
        Customer cust = new Customer();
        Employee emp = new Employee();

        // User u = new Employee();
        // Employee emp = (Employee)u;

        User User = new User();

        // Customer cust = new Customer();
        int userId = Integer.parseInt(idTextField.getText().trim());
        String userpassword = (passwordTextField.getText().trim());
        String userName = (nameTextField.getText().trim());
        String userRole = "";
        String usergender = "";

        if (custCheckbox.isSelected()) {
            userRole = "Customer";
        } else {
            if (empCheckbox.isSelected()) {
                userRole = "Employee";
            }
        }

        if (maleRadioButton.isSelected()) {
            usergender = "Male";
        } else {
            if (femaleRadioButton.isSelected()) {
                usergender = "Female";
            }
        }

        User.setid(userId);
        User.setPassword(userpassword);
        User.setName(userName);
        User.setRole(userRole);
        User.setGender(usergender);

        if (userRole.equals("Customer")) {
            cust.setCust_Id(userId);
            // Customer newCustomer = new Customer(new User(userId, userpassword, userRole,
            // userName), userId);
            Customer newCustomer = new Customer(userId, userName, usergender, userRole, userId);
            System.out.println(newCustomer);
            // Customer newCustomer = new User(userId, userpassword, userRole, userName);
            // Customer newCustomer = new Customer(userId, userpassword, userRole, userName,
            // userId);
            User.create(newCustomer);

            // Equipment equip = new Equipment();

            // equip.EquipmentForm();

        } else {
            if (userRole.equals("Employee")) {
                emp.setEmp_id(userId);
                Employee newEmployee = new Employee(userId, userName, usergender, userRole, userId);
                User.create(newEmployee);
            }
        }

        JOptionPane.showMessageDialog(null, "Account Created Successfully!", "SIGN UP STATUS",
                JOptionPane.INFORMATION_MESSAGE);

        new UserLogin();

    }

    public static void main(String[] args) {

        new UserSignUp();

    }

}

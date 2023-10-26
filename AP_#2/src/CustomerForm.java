import java.util.logging.LogManager;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerForm {

    private JFrame frame;
    private JLabel idLabel;
    private JTextField idTextField;

    private JLabel nameLabel;
    private JTextField nameTextField;

    private JLabel passwordLabel;
    private JTextField passwordTextField;

    private JLabel accountBalanceLabel;
    private JTextField accountBalanceTextField;

    private JLabel roleLabel;
    private JComboBox<String> roleComboBox;
    private String[] role = { "Customer", "Employee" };

    private JButton loginButton;
    private JButton loadButton;
    private JButton deleteButton;
    private JButton createAccountButton;

    private JButton viewEquipmentButton;
    private JButton viewTransactionButton;
    private JButton viewBookingButton;

    private JPanel panTop;
    private JPanel panMiddle;
    private JPanel panBottom;

    public CustomerForm() {
        frame = new JFrame("Grizzly's Entertainment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        idLabel = new JLabel("ID : ");
        idLabel.setBounds(50, 50, 75, 30);
        frame.add(idLabel);
        idTextField = new JTextField();
        idTextField.setBounds(150, 50, 200, 30);
        frame.add(idTextField);

        nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(50, 90, 75, 30);
        frame.add(nameLabel);
        nameTextField = new JTextField();
        nameTextField.setBounds(150, 90, 200, 30);
        frame.add(nameTextField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 170, 75, 30);
        frame.add(passwordLabel);
        passwordTextField = new JTextField();
        passwordTextField.setBounds(150, 170, 200, 30);
        frame.add(passwordTextField);

        accountBalanceLabel = new JLabel("Account Balance:");
        accountBalanceLabel.setBounds(50, 170, 75, 30);
        frame.add(accountBalanceLabel);
        accountBalanceTextField = new JTextField();
        accountBalanceLabel.setBounds(150, 170, 200, 30);
        frame.add(accountBalanceLabel);

        roleLabel = new JLabel("Role: ");
        roleLabel.setBounds(50, 210, 75, 30);
        frame.add(roleLabel);
        roleComboBox = new JComboBox<String>(role);
        roleComboBox.setBounds(150, 210, 200, 30);
        roleComboBox.setSelectedIndex(1);
        frame.add(roleComboBox);

        loginButton = new JButton("Login");
        loginButton.setBounds(150, 340, 200, 30);
        frame.add(loginButton);

        loadButton = new JButton("Load");
        loadButton.setBounds(150, 390, 200, 30);
        frame.add(loadButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(150, 480, 200, 30);
        frame.add(deleteButton);

        createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(150, 430, 200, 30);
        frame.add(createAccountButton);

        viewTransactionButton = new JButton("View Transaction");
        viewTransactionButton.setBounds(150, 430, 200, 30);
        frame.add(viewTransactionButton);

        viewBookingButton = new JButton("View Booking");
        viewBookingButton.setBounds(150, 430, 200, 30);
        frame.add(viewBookingButton);

        viewEquipmentButton = new JButton("View Equipment");
        viewEquipmentButton.setBounds(150, 430, 200, 30);
        frame.add(viewEquipmentButton);

        panTop = new JPanel(new GridLayout(1, 3));
        // panTop.add(btnClear);
        panMiddle = new JPanel(new GridLayout(1, 2));
        // panMiddle.add(btnConvert);
        panBottom = new JPanel(new GridLayout(1, 2));

        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Employee emp = new Employee();
                Customer cust = new Customer();
                int custId = Integer.parseInt(idTextField.getText().trim());
                int custpassword = Integer.parseInt(passwordTextField.getText().trim());

                double custACBalance = Integer.parseInt(accountBalanceTextField.getText().trim());

                cust.setID(custId);
                String empName = (nameTextField.getText().trim());
                cust.setName(empName);
                cust.setPassword(custpassword);
                String emprole = (roleComboBox.getSelectedItem().toString().trim());
                cust.setRole(emprole);

                cust.setAccountBalance(custACBalance = 0.0);
                Customer newCustomer = new Customer(custId, custpassword, empName, emprole, custACBalance);
                cust.create(newCustomer);

                Equipment equip = new Equipment();

            }

        });

        /*
         * 
         * deleteButton.addActionListener(new ActionListener()
         * {
         * public void actionPerformed(ActionEvent e)
         * {
         * Employee emp = new Employee();
         * String empId = (idTextField.getText().trim());
         * 
         * 
         * emp.setName(nameTextField.getText().trim());
         * emp.setID(empId);
         * 
         * if (emp.getID() == empId)
         * {
         * emp.delete(empId);
         * 
         * }
         * else
         * {
         * logger.info("Deletion Unsucessfull");
         * }
         * 
         * 
         * //System.out.println(empId);
         * 
         * }
         * 
         * });
         * 
         * loadButton.addActionListener(new ActionListener()
         * {
         * public void actionPerformed(ActionEvent e)
         * {
         * Employee emp = new Employee();
         * String empId = (idTextField.getText().trim());
         * 
         * 
         * emp.setName(nameTextField.getText().trim());
         * emp.setID(empId);
         * 
         * if (!empId.isEmpty())
         * {
         * emp.readOne(empId);
         * }
         * else
         * {
         * logger.info("Loading Unsucessfull");
         * }
         * 
         * }
         * });
         * 
         * 
         * viewExecButton.addActionListener(new ActionListener()
         * {
         * public void actionPerformed(ActionEvent e)
         * {
         * Employee emp = new Employee();
         * float emppassword = Integer.parseInt(passwordTextField.getText().trim());
         * 
         * emp.readAll(emppassword);
         * 
         * 
         * //if ()
         * 
         * }
         * });
         * 
         */

        frame.setSize(450, 600);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }

}
// End of class CustomerForm

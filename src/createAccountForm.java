import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class createAccountForm {
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

    public createAccountForm() {
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
        accountBalanceLabel.setBounds(50, 300, 75, 30);
        accountBalanceLabel.setVisible(false);
        frame.add(accountBalanceLabel);
        accountBalanceTextField = new JTextField();
        accountBalanceTextField.setBounds(150, 300, 200, 30); // Corrected the bounds
        accountBalanceTextField.setVisible(false);
        frame.add(accountBalanceTextField);

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

        /*
         * viewTransactionButton = new JButton("View Transaction");
         * viewTransactionButton.setBounds(150, 430, 200, 30);
         * frame.add(viewTransactionButton);
         * 
         * viewBookingButton = new JButton("View Booking");
         * viewBookingButton.setBounds(150, 430, 200, 30);
         * frame.add(viewBookingButton);
         * 
         * viewEquipmentButton = new JButton("View Equipment");
         * viewEquipmentButton.setBounds(150, 430, 200, 30);
         * frame.add(viewEquipmentButton);
         * 
         */
        // panTop = new JPanel(new GridLayout(1, 3));
        // panTop.add(btnClear);
        // panMiddle = new JPanel(new GridLayout(1, 2));
        // panMiddle.add(btnConvert);
        // panBottom = new JPanel(new GridLayout(1, 2));

        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
                int userpassword = Integer.parseInt(passwordTextField.getText().trim());
                String userName = (nameTextField.getText().trim());
                String userrole = (roleComboBox.getSelectedItem().toString().trim());

                // double custACBalance =
                // Double.parseDouble(accountBalanceTextField.getText().trim());
                User.setRole(userrole);
                User.setid(userId);
                User.setName(userName);
                User.setPassword(userpassword);

                System.out.println(userName);
                System.out.println(userrole);

                if (userrole.equals("Customer")) {
                    cust.setCust_Id(userId);
                    Customer newCustomer = new Customer(new User(userId, userpassword, userrole, userName), userId);
                    // Customer newCustomer = new Customer(userId, userpassword, userrole, userName,
                    // userId);
                    User.create(newCustomer);

                    // Equipment equip = new Equipment();

                    // equip.EquipmentForm();

                } else {
                    if (userrole.equals("Employee")) {
                        emp.setEmp_id(userId);
                        Employee newEmployee = new Employee(new User(userId, userpassword, userrole, userName), userId);
                        User.create(newEmployee);
                    }
                }

                // cust.setAccountBalance(custACBalance = 0.0);
                // Customer newCustomer = new Customer(custId, custpassword, empName, emprole);
                // cust.create(newCustomer);

                /*
                 * nameLabel.setVisible(false);
                 * nameTextField.setVisible(false);
                 * 
                 * roleLabel.setVisible(false);
                 * roleComboBox.setVisible(false);
                 * 
                 * passwordLabel.setVisible(false);
                 * passwordTextField.setVisible(false);
                 * 
                 */

            }

        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                nameLabel.setVisible(false);
                nameTextField.setVisible(false);

                roleLabel.setVisible(false);
                roleComboBox.setVisible(false);

                passwordLabel.setVisible(false);
                passwordTextField.setVisible(false);
                int custId = Integer.parseInt(idTextField.getText().trim());
                // int custpassword = Integer.parseInt(passwordTextField.getText().trim());

                Customer newCustomer = new Customer();

                newCustomer.read(custId);

            }
        });

        frame.setSize(700, 1000);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    /*
     * public static void main(String[] args) {
     * 
     * new CustomerForm();
     * 
     * }
     */

}

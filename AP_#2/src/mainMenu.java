import java.util.Scanner;

import org.hibernate.Session;

import org.hibernate.SessionFactory;

import java.util.logging.LogManager;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainMenu {

    public static void mainMenuGui() {

        SessionFactory factory = SessionFactoryBuilder.getSessionFactory();

        JFrame frame;
        JLabel idLabel;
        JTextField idTextField;

        JLabel passwordLabel;
        JTextField passwordTextField;

        JLabel roleLabel;
        JComboBox<String> roleComboBox;
        String[] role = { "Customer", "Employee" };

        JButton loginButton;

        frame = new JFrame("Grizzly's Entertainment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        idLabel = new JLabel("ID : ");
        idLabel.setBounds(50, 50, 75, 30);
        frame.add(idLabel);
        idTextField = new JTextField();
        idTextField.setBounds(150, 50, 200, 30);
        frame.add(idTextField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 170, 75, 30);
        frame.add(passwordLabel);

        JPasswordField value = new JPasswordField();
        passwordTextField = new JTextField();
        passwordTextField.setBounds(150, 170, 200, 30);
        frame.add(passwordTextField);

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

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Customer cust = new Customer();
                User user = new User(factory);
                int Id = Integer.parseInt(idTextField.getText().trim());
                int password = Integer.parseInt(passwordTextField.getText().trim());
                // int password = value.getPassword();
                String emprole = (roleComboBox.getSelectedItem().toString().trim());

                // cust.setID(custId);
                // cust.setPassword(custpassword);
                User validate = user.read(Id, password);

                try {
                    if (validate.getID() == Id) {
                        if (validate.getPassword() == password) {
                            if (emprole.equals("Customer")) {
                                cust.login();
                            } else {
                                // emp.login()
                            }
                            // Customer cust = new Customer();

                        }

                    }

                } catch (RuntimeException rne) {
                    rne.printStackTrace();

                }
            }

        });

        frame.setSize(700, 800);
        frame.setLayout(null);
        frame.setResizable(true);
        frame.setVisible(true);

    }

    static Customer cust = new Customer();

    CustomerForm cf = new CustomerForm();

    Employee emp = new Employee();

    public static boolean validateUserCredential(int id, int password, int menuSelection) {
        int result;
        char again = 'Y';

        try {
            switch (menuSelection) {

                case 1:

                    Customer validate = cust.read(id);

                    if (validate.getID() == id) {
                        if (validate.getPassword() == password) {
                            // Customer cust = new Customer();
                            cust.login();

                            return true;
                        }

                    }
                    break;

                case 2:
                    // emp.read(id,password)
                    break;

                // return true;

            }

        } catch (RuntimeException rne) {
            rne.printStackTrace();
        }

        return true;
    }

    public static void prompt(int menuSelection) {
        int userID;

        int userPassword;

        do {
            // Prompts user to enter username
            Scanner scanner = new Scanner(System.in);

            CustomerForm cf = new CustomerForm();

            System.out.println("Enter id: ");
            userID = scanner.nextInt();

            System.out.println("Enter password: ");
            userPassword = scanner.nextInt();

        } while (validateUserCredential(userID, userPassword, menuSelection) == true);
    }

    /*
     * public static void mainMenu() {
     * 
     * // system("cls"); // Clears the console
     * 
     * int menuSelection; // Used to store the user input
     * 
     * Scanner input = new Scanner(System.in);
     * String userType = "";
     * 
     * System.out.println("Grizzyly entertainment");
     * System.out.println("Login Options:\n");
     * System.out.println("1: Customer");
     * System.out.println("2: Employee\n");
     * 
     * do {
     * System.out.print("Selection: "); // Prompts the user to enter an input
     * menuSelection = input.nextInt(); // Store's the user input into menuSelection
     * 
     * // Checks if the input was an integer
     * while (!input.hasNextInt()) {
     * input.nextLine(); // Used to clear the console
     * System.out.println("Invalid entry, try again!\n");
     * System.out.print("Selection: ");
     * menuSelection = input.nextInt();
     * }
     * 
     * // clearConsole();
     * 
     * switch (menuSelection) {
     * case 1:
     * userType = "Customer";
     * System.out.println("Customer Menu");
     * 
     * break;
     * case 2:
     * userType = "Customer";
     * System.out.println("Employee Menu");
     * break;
     * 
     * default:
     * System.out.println("Invalid entry, try again!\n");
     * }
     * 
     * // Prompts the user to enter username and password
     * prompt(menuSelection);
     * 
     * } while (menuSelection < 1 || menuSelection > 3);
     * }
     * 
     * 
     */
    public static void main(String[] args) {

        mainMenuGui();

    }

}

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Driver {

    Customer cust = new Customer();

    Employee emp = new Employee();

    public boolean validateUserCredential(int id, int password, int menuSelection) {
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

                    //return true;

            }

        } catch (RuntimeException rne) {
            rne.printStackTrace();
        }

        return true;
    }

    public void prompt(int menuSelection) {
        int userID;

        int userPassword;

        do {
            // Prompts user to enter username
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter id: ");
            userID = scanner.nextInt();

            System.out.println("Enter password: ");
            userPassword = scanner.nextInt();

        } while (validateUserCredential(userID, userPassword, menuSelection) == true);
    }

    public void mainMenu() {

        // system("cls"); // Clears the console

        int menuSelection; // Used to store the user input

        Scanner input = new Scanner(System.in);
        String userType = "";

        System.out.println("Grizzyly entertainment");
        System.out.println("Login Options:\n");
        System.out.println("1: Customer");
        System.out.println("2: Employee\n");

        do {
            System.out.print("Selection: "); // Prompts the user to enter an input
            menuSelection = input.nextInt(); // Store's the user input into menuSelection

            // Checks if the input was an integer
            while (!input.hasNextInt()) {
                input.nextLine(); // Used to clear the console
                System.out.println("Invalid entry, try again!\n");
                System.out.print("Selection: ");
                menuSelection = input.nextInt();
            }

            // clearConsole();

            switch (menuSelection) {
                case 1:
                    userType = "Customer";
                    System.out.println("Customer Menu");
                    break;
                case 2:
                    userType = "Customer";
                    System.out.println("Customer Menu");
                    break;
                default:
                    System.out.println("Invalid entry, try again!\n");
            }

            // Prompts the user to enter username and password
            prompt(menuSelection);

        } while (menuSelection < 1 || menuSelection > 3);
    }

    public static void main(String[] args) {
           
        
    }
}

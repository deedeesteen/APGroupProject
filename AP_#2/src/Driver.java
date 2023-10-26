import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Driver {

    Customer cust = new Customer();

    Employee emp = new Employee();

    public static void main(String[] args) {

    }

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
                        }

                    }
                    break;

                case 2:
                    // emp.read(id,password)
                    break;

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

    }

} // End of Driver

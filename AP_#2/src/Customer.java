
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Customer extends User {

    private static final long serialVersionUID = 1L;

    double accountBalance;

    String filename = "Customer.ser";

    public Customer() {
        super();
        accountBalance = 0.0;

    }

    public Customer(int ID, int password, String role, String name, double accountBalance) {
        super(ID, password, role, name);
        this.accountBalance = accountBalance;

    }

    public Customer(Customer cust) {
        super();

    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double AC) {
        this.accountBalance = AC;
    }

    public static Customer loadCustomerRecord(String filename) {
        try (FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            Customer customer = (Customer) ois.readObject();
            return customer;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean login(int password, int ID) {
        Customer customer = loadCustomerRecord("Customer.ser");

        if (customer.getPassword() == password && customer.getID() == ID) {
            if (customer != null) {
                System.out.println("Read Employee Record: " + customer);
                return true;
            }

        }
        return false;

    }

}

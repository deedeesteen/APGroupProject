import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;

public class Employee extends User {

    String filename = "Employee.ser";

    public Employee() {
        super();
    }

    public Employee(int ID, int password, String role, String name) {
        super(ID, password, role, name);
    }

    /*
     * public static Employee loadEmployeeRecord(String filename) {
     * try (FileInputStream fis = new FileInputStream(filename);
     * ObjectInputStream ois = new ObjectInputStream(fis)) {
     * Employee employee = (Employee) ois.readObject();
     * return employee;
     * } catch (IOException | ClassNotFoundException e) {
     * e.printStackTrace();
     * }
     * return null;
     * }
     * 
     * public static boolean login(int password, int ID) {
     * Employee employee = loadEmployeeRecord("Employee.ser");
     * 
     * if (employee.getPassword() == password && employee.getID() == ID) {
     * if (employee != null) {
     * System.out.println("Read Employee Record: " + employee);
     * return true;
     * }
     * 
     * }
     * return false;
     * 
     * }
     * 
     */

    public void viewBookings() {

    }

    public void viewEquipment() {

    }

}

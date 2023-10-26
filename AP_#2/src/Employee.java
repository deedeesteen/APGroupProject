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

    public void viewBookings() {

    }

    public void viewEquipment() {

    }

}

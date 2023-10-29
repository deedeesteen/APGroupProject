
import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import com.mysql.cj.xdevapi.SessionFactory;

public class User implements Serializable {

    protected int ID;
    protected int password;
    protected String role;
    protected String name;

    private SessionFactory factory;

    public User() {
        ID = 000;
        password = 111;
        role = "Default";
        name = "Default";
    }

    public User(int i, int password2, String r, String n) {
        this.ID = i;
        this.password = password2;
        this.role = r;
        this.name = n;
    }

    public User(SessionFactory factory) {
        this.factory = SessionFactoryBuilder.getSessionFactory();
    }

    public User(User u) {

    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "ID: " + ID + "Password: " + password + "Name: " + name + "Role: " + role;
    }

    // public abstract void Login();

    // public abstract void viewBookings();

    // public abstract void viewEquipment();

    public User read(int id, int password) {
        try (Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession()) {
            return session.get(User.class, id);
        }
    }

    /*
     * public abstract void create();
     * 
     * public abstract User update();
     * 
     * public abstract User delete();
     * 
     * public abstract User read();
     * 
     * // public abstract readAll();\
     * 
     */

}
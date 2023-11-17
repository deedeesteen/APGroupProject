import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.util.List;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.hibernate.Session;
//import org.apache.logging.log4j.LogManager;
import org.hibernate.Transaction;
//import org.jboss.logging.Logger;

public class Customer extends User {

    public int cust_id;
    public double accountBalance;
    // public User User;

    // String filename = "Customer.ser";

    public Customer() {
        super();
        cust_id = 0;
        accountBalance = 0.0;
    }

    // public Customer() {
    // // super();
    // this.User = new User();
    // cust_id = 0;
    // accountBalance = 0.0;

    // }

    public Customer(int cust_id, double accountBalance) {
        super();
        this.cust_id = 0;
        this.accountBalance = 0.0;
    }

    public Customer(int ID, int password, String role, String name) {
        super();
        // this.User = new User(ID, password, role, name);
        cust_id = 0;
        accountBalance = 0.0;

    }

    public Customer(int ID, int password, String role, String name, double accountBalance, int ui) {
        super(ID, password, role, name);
        // this.User = new User(ID, password, role, name);
        // super();
        this.cust_id = ui;
        this.accountBalance = accountBalance;

    }

    public Customer(int ID, int password, String role, String name, int ui) {
        super(ID, password, role, name);
        this.cust_id = ui;
        // this.User = new User(id, password, role, name);

    }

    public Customer(User u, double accountBalance, int ui) {
        this.cust_id = ui;
        this.accountBalance = accountBalance;
        // this.User = new User(id, password, role, name);

    }

    public Customer(int ID, int password, String role, String name, double accountBalance, int ui, User User) {
        super(ID, password, role, name);
        this.cust_id = ui;
        this.accountBalance = accountBalance;
        // User = new User(ui, password, role, name);

    }

    public Customer(Customer cust) {
        super();

    }

    public int getCust_Id() {
        return cust_id;
    }

    public void setCust_Id(int cust_id) {
        this.cust_id = cust_id;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double AC) {
        this.accountBalance = AC;
    }

    public void create(Customer cust) {
        Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();

        Transaction transaction = session.beginTransaction();

        if (!transaction.isActive()) {
            transaction.begin();
        }

        session.save(cust);

        if (transaction.isActive()) {
            transaction.commit();
        }

        session.close();
    }

    public void create(Customer cust, Transaction transaction) {
        Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();

        // Transaction transaction = session.beginTransaction();

        if (!transaction.isActive()) {
            transaction.begin();
        }

        session.save(cust);
        System.out.println(cust);

        if (transaction.isActive()) {
            transaction.commit();
        }

        session.close();
    }

    public Customer update(Customer CustomerToUpdate) {
        Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();

        Transaction transaction = session.beginTransaction();

        Customer cust = (Customer) session.get(Customer.class, this.id);

        cust.setName(name);

        cust.setid(id);

        cust.setCust_Id(id);

        cust.setPassword(password);

        cust.setRole(role);

        cust.setAccountBalance(accountBalance);

        session.update(cust);

        transaction.commit();

        session.close();

        return cust;
    }

    public List<Customer> readAll() {
        List<Customer> CustomerList = new ArrayList<>();

        Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();

        Transaction transaction = session.beginTransaction();

        CustomerList = (List<Customer>) session.createQuery("FROM Customer").getResultList();

        transaction.commit();

        session.close();

        return CustomerList;

    }

    public Customer delete(Customer CustomerToDelete) {
        Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();

        Transaction transaction = session.beginTransaction();

        Customer cust = (Customer) session.get(Customer.class, this.id);

        session.delete(cust);

        transaction.commit();

        session.close();

        return cust;
    }

    public Customer read(int id, int pword, Transaction trans) {

        Session session = SessionFactoryBuilder.getSessionFactory().openSession();
        Transaction transaction = null;
        Customer readCustomer = null;

        try {
            transaction = session.beginTransaction();

            // Load the entity by ID
            readCustomer = session.get(Customer.class, id);

            if (readCustomer.getPassword() == pword) {
                transaction.commit();
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return readCustomer;
    }

    public Customer read(int id) {

        Session session = SessionFactoryBuilder.getSessionFactory().openSession();
        Transaction transaction = null;
        Customer readCustomer = null;

        try {
            transaction = session.beginTransaction();

            // Load the entity by ID
            readCustomer = session.get(Customer.class, id);

                transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return readCustomer;
    }

    public void viewBookings() {

    }

    public void viewEquipment() {

    }

    public void viewTransactions() {

    }

    public void login() {

        JFrame frame = new JFrame("Grizzly's Entertainment: Equipment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JInternalFrame loginInternalFrame = new JInternalFrame("frame 1", true, true, true, true);

        JButton viewEquip = new JButton("View Equipment");

        JButton viewTrans = new JButton("View Transaction");

        loginInternalFrame.add(viewEquip);

        loginInternalFrame.add(viewTrans);

        frame.add(loginInternalFrame);

        loginInternalFrame.setVisible(true);

        frame.setSize(700, 800);
        frame.setLayout(null);
        frame.setResizable(true);
        frame.setVisible(true);

    }

    // public String toString() {
    // return "Customer [cust_id=" + cust_id + ", accountBalance=" + accountBalance
    // + ", User=" + User + "]";
    // }
    public String toString() {
        return "Customer [cust_id=" + cust_id + ", accountBalance=" + accountBalance + "]";
    }

}

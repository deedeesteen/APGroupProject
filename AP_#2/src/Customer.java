
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.apache.logging.log4j.LogManager;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

public class Customer extends User {

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

    public void create(Customer cust) {
        Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();

        Transaction transaction = session.beginTransaction();

        session.save(this);

        transaction.commit();

        session.close();
    }

    public Customer update(Customer CustomerToUpdate) {
        Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();

        Transaction transaction = session.beginTransaction();

        Customer cust = (Customer) session.get(Customer.class, this.ID);

        cust.setName(name);

        cust.setID(ID);

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

        Customer cust = (Customer) session.get(Customer.class, this.ID);

        session.delete(cust);

        transaction.commit();

        session.close();

        return cust;
    }

    public Customer read(int id) {
        try (Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession()) {
            return session.get(Customer.class, id);
        }
    }

    public void viewBookings() {

    }

    public void viewEquipment() {

    }

    public void viewTransactions() {

    }

    public void login() {

        int optionSelection;

        Scanner scanner = new Scanner(System.in);

        System.out.println("****** CUSTOMER MENU ******");

        System.out.println("1 - View Equipment");

        System.out.println("2 - View Transactions");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                viewEquipment();
                break;

            case 2:
                viewBookings();
                break;

            case 3:
                viewTransactions();
                break;
        }

    }

}

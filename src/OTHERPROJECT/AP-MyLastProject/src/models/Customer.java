package models;

import java.util.ArrayList;
import java.util.List;
import factories.SessionFactoryBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Customer extends User {

    private static final long serialVersionUID = 1L;
    public int cust_id;
    public double accountBalance;
    private List<Equipment> registeredEquipment = new ArrayList<>();
    private Integer equipmentId;
    // public User User;

    // String filename = "Customer.ser";

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public void setRegisteredEquipment(List<Equipment> registeredEquipment) {
        this.registeredEquipment = registeredEquipment;
    }

    public Customer() {

    }

    public Customer(int cust_id, int accountBalance) {
        super();
        this.cust_id = 0;
        this.accountBalance = 0.0;
    }

    public Customer(int cust_id, double accountBalance) {
        super();
        this.cust_id = 0;
        this.accountBalance = 0.0;
    }

    public Customer(int id, String password, String name, String gender, String role) {
        super();
        // this.User = new User(id, password, name, gender, role);
        cust_id = 0;
        accountBalance = 0.0;

    }

    public Customer(int id, String password, String name, String gender, String role, double accountBalance, int ui) {
        super(id, password, name, gender, role);
        // this.User = new User(id, password, name, gender, role);
        // super();
        this.cust_id = ui;
        this.accountBalance = accountBalance;

    }

    public Customer(int id, String password, String name, String gender, String role, int ui) {
        super(id, password, name, gender, role);
        this.cust_id = ui;
        // this.User = new User(id, password, name, gender, role);

    }

    public Customer(int id, String name, String gender, String role, int ui, double accountBalance) {
        super(id, name, gender, role);
        this.cust_id = ui;
        this.accountBalance = accountBalance;

    }

    public Customer(int id, String name, String gender, String role, int ui) {
        super(id, name, gender, role);
        this.cust_id = ui;
    }

    public Customer(User u, double accountBalance, int ui) {
        this.cust_id = ui;
        this.accountBalance = accountBalance;
        // this.User = new User(id, password, name, gender, role);

    }

    public Customer(int id, String password, String name, String gender, String role, double accountBalance, int ui,
            User User) {
        super(id, password, name, gender, role);
        this.cust_id = ui;
        this.accountBalance = accountBalance;
        // User = new User(id, password, name, gender, role);

    }

    // Constructors, getters, and setters

    public void registerEquipment(Equipment equipment) {
        // Add the equipment to the registeredEquipment list
        registeredEquipment.add(equipment);

        // Optionally, set the customer on the equipment
        equipment.setCustomer(this);
    }

    public List<Equipment> getRegisteredEquipment() {
        return registeredEquipment;
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

        cust.setGender(gender);

        // cust.setRole(role);

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

    // Corrected
    public Customer read(int id, String pword) {

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

    @Override
    public String toString() {
        return "Customer ID: " + cust_id +
                "Password: " + password +
                "Name: " + name +
                "Gender: " + gender +
                "Role: " + role +
                "AccountBalance: " + accountBalance;
    }

}

package models;

import java.util.ArrayList;
import java.util.List;

import factories.SessionFactoryBuilder;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Employee extends User {

    private static final long serialVersionUID = 1L;
    public int emp_id;

    public Employee() {
        super();
        emp_id = 0;
    }

    public Employee(int id, String password, String name, String gender, String role) {
        super();
        emp_id = 0;
    }

    public Employee(int id, String password, String name, String gender, String role, int empid) {
        super(id, password, name, gender, role);
        this.emp_id = empid;
    }

    public Employee(int id, String name, String gender, String role, int empid) {
        super(id, name, gender, role);
        this.emp_id = empid;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public void create(Employee emp) {
        Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();

        Transaction transaction = session.beginTransaction();

        if (!transaction.isActive()) {
            transaction.begin();
        }

        session.save(emp);

        if (transaction.isActive()) {
            transaction.commit();
        }

        session.close();
    }

    public void create(Employee emp, Transaction transaction) {
        Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();

        // Transaction transaction = session.beginTransaction();

        if (!transaction.isActive()) {
            transaction.begin();
        }

        session.save(emp);

        if (transaction.isActive()) {
            transaction.commit();
        }

        session.close();
    }

    public Employee update(Employee employeeToUpdate) {
        Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();

        Transaction transaction = session.beginTransaction();

        Employee emp = (Employee) session.get(Employee.class, this.id);

        emp.setName(name);

        emp.setid(id);

        emp.setEmp_id(id);

        emp.setPassword(password);

        emp.setGender(gender);

        // emp.setRole(role);

        // emp.setAccountBalance(accountBalance);

        session.update(emp);

        transaction.commit();

        session.close();

        return emp;
    }

    public List<Employee> readAll() {
        List<Employee> EmployeeList = new ArrayList<>();

        Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();

        Transaction transaction = session.beginTransaction();

        EmployeeList = (List<Employee>) session.createQuery("FROM Employee").getResultList();

        transaction.commit();

        session.close();

        return EmployeeList;

    }

    public Employee delete(Employee EmployeeToDelete) {
        Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();

        Transaction transaction = session.beginTransaction();

        Employee emp = (Employee) session.get(Employee.class, this.id);

        session.delete(emp);

        transaction.commit();

        session.close();

        return emp;
    }

    public Employee read(int id, String password) {
        try (Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession()) {
            return session.get(Employee.class, id);
        }
    }

    public void viewBookings() {

    }

    public void viewEquipment() {

    }

    @Override
    public String toString() {
        return "Employee ID: " + emp_id +
                "Password: " + password +
                "Name: " + name +
                "Gender: " + gender +
                "Role: " + role;
    }

}

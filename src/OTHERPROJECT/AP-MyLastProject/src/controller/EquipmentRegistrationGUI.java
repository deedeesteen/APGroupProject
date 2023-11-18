package controller;

import javax.swing.*;

import org.hibernate.Transaction;
import factories.SessionFactoryBuilder;
import org.hibernate.Session;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import models.Customer;
import models.Equipment;

public class EquipmentRegistrationGUI extends JInternalFrame {
    private JComboBox<String> equipmentComboBox;
    private JButton registerButton;
    private JButton viewDetailsButton;

    private Customer currentCustomer; // You'll need to set this based on the logged-in customer

    public EquipmentRegistrationGUI(Customer currentCustomer) {
        super("Equipment Registration");

        this.currentCustomer = currentCustomer;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        equipmentComboBox = new JComboBox<>();
        loadEquipmentData(); // Load equipment data from Hibernate

        registerButton = new JButton("Register Equipment");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerEquipment();
            }
        });

        viewDetailsButton = new JButton("View Equipment Details");
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewEquipmentDetails();
            }
        });

        add(new JLabel("Select Equipment:"));
        add(equipmentComboBox);
        add(registerButton);
        add(viewDetailsButton);
         setResizable(true);
        setVisible(true);
    }

    private void loadEquipmentData() {
        Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            List<Object[]> equipmentData = session.createQuery(
                    "SELECT e.equip_name, e.availability, e.category, e.dateRequired, e.equip_id, e.cost " +
                            "FROM Equipment e",
                    Object[].class)
                    .getResultList();

            for (Object[] data : equipmentData) {
                String equipmentInfo = String.format(
                        "%s - Availability: %s, Category: %s, Date: %s, Equip ID: %s, Cost: %s",
                        data[0], data[1], data[2], data[3], data[4], data[5]);
                equipmentComboBox.addItem(equipmentInfo);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private void registerEquipment() {

        String selectedEquipmentInfo = (String) equipmentComboBox.getSelectedItem();
        if (selectedEquipmentInfo != null) {
            String[] data = selectedEquipmentInfo.split(" - ");
            String selectedEquipment = data[0];
            Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                Equipment selectedEquipmentEntity = session.createQuery(
                        "FROM Equipment e WHERE e.equip_name = :equipName", Equipment.class)
                        .setParameter("equipName", selectedEquipment)
                        .uniqueResult();

                if (selectedEquipmentEntity != null) {
                    currentCustomer.registerEquipment(selectedEquipmentEntity);
                    session.saveOrUpdate(currentCustomer);
                    JOptionPane.showMessageDialog(this, "Equipment registered: " + selectedEquipment);
                } else {
                    JOptionPane.showMessageDialog(this, "Selected equipment not found.");
                }

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                session.close();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an equipment.");
        }
    }

    private void viewEquipmentDetails() {
        // Implement logic to view equipment details based on the selected equipment
        String selectedEquipment = (String) equipmentComboBox.getSelectedItem();
        if (selectedEquipment != null) {
            JOptionPane.showMessageDialog(this, "Viewing details for: " + selectedEquipment);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an equipment.");
        }
    }

    public static void main(String[] args) {
        Customer sampleCustomer = new Customer(2100463, "Leigh-Ann", "Female", "Customer", 2100463); // You'll need to
                                                                                                     // replace this
                                                                                                     // with actual
                                                                                                     // customer data
        SwingUtilities.invokeLater(() -> new EquipmentRegistrationGUI(sampleCustomer));
    }
}

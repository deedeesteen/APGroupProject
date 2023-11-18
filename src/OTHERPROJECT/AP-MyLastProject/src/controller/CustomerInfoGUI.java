package controller;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import factories.SessionFactoryBuilder;
import models.*;

public class CustomerInfoGUI extends JInternalFrame {
    private JLabel nameLabel;
    private JLabel equipmentLabel;
    private JComboBox<String> equipmentComboBox;
    private JButton viewDetailsButton;

    private Customer currentCustomer; // You'll need to set this based on the logged-in customer

    public CustomerInfoGUI(Customer currentCustomer) {
        super("Customer Information");

        this.currentCustomer = currentCustomer;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        nameLabel = new JLabel("Customer Name: " + currentCustomer.getName());
        equipmentLabel = new JLabel("Select Equipment:");
        equipmentComboBox = new JComboBox<>();
        loadCustomerEquipmentData(); // Load customer's equipment data from Hibernate

        viewDetailsButton = new JButton("View Equipment Details");
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewEquipmentDetails();
            }
        });

        add(nameLabel);
        add(equipmentLabel);
        add(equipmentComboBox);
        add(viewDetailsButton);

        // setLocationRelativeTo(null); // Center the frame
        setVisible(true);
        setResizable(true);
    }

    private void loadCustomerEquipmentData() {
        Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Assuming Equipment has a reference to Customer in its mapping
            currentCustomer = session.get(Customer.class, currentCustomer.getid());

            List<Equipment> customerEquipment = currentCustomer.getRegisteredEquipment();
            for (Equipment equipment : customerEquipment) {
                equipmentComboBox.addItem(equipment.getEquip_name());
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

    private void viewEquipmentDetails() {
        String selectedEquipmentName = (String) equipmentComboBox.getSelectedItem();
        if (selectedEquipmentName != null) {
            Equipment selectedEquipment = findEquipmentByName(selectedEquipmentName);
            if (selectedEquipment != null) {
                JOptionPane.showMessageDialog(this, "Viewing details for: " + selectedEquipment);
            } else {
                JOptionPane.showMessageDialog(this, "Selected equipment not found.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an equipment.");
        }
    }

    private Equipment findEquipmentByName(String equipName) {
        List<Equipment> customerEquipment = currentCustomer.getRegisteredEquipment();
        for (Equipment equipment : customerEquipment) {
            if (equipment.getEquip_name().equals(equipName)) {
                return equipment;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Customer sampleCustomer = new Customer(2100463, "Leigh-Ann", "Female", "Customer", 2100463); // You'll need to
                                                                                                     // replace this
                                                                                                     // with actual
                                                                                                     // customer data
        // sampleCustomer.registerEquipment(new Equipment("Equipment1", 100.0, "Yes",
        // "Category1", new Date(), 1L));
        // sampleCustomer.registerEquipment(new Equipment("Equipment", 150.0, "No",
        // "Category2", new Date(), 2L));

        SwingUtilities.invokeLater(() -> new CustomerInfoGUI(sampleCustomer));
    }
}

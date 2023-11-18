package models;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hibernate.Transaction;
import org.hibernate.Session;

import factories.SessionFactoryBuilder;

public class Transactions extends JInternalFrame {

    private Integer transactionId;

    private Equipment equipment;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private Customer customer;
    private JComboBox<String> equipmentComboBox;
    private JButton registerButton;
    private JButton viewDetailsButton;

    private Customer currentCustomer; // You'll need to set this based on the logged-in customer

    public Transactions(Customer currentCustomer) {
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
                    "SELECT e.equip_name, e.availability, e.category, e.equip_id, e.cost " +
                            "FROM Equipment e",
                    Object[].class)
                    .getResultList();

            for (Object[] data : equipmentData) {
                String equipmentInfo = String.format(
                        "%s - Availability: %s, Category: %s, Equip ID: %s, Cost: %s",
                        data[0], data[1], data[2], data[3], data[4]);
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
            String[] data = selectedEquipmentInfo.split(", ");
            System.out.println("Selected Equipment Info: " + selectedEquipmentInfo);
            for (int i = 0; i < data.length; i++) {
                System.out.println("Index " + i + ": " + data[i]);
            }
            String selectedEquipmentId = data[2]; // Assuming the equipment ID is at index 4
            Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Retrieve the Equipment entity using its ID
                Equipment selectedEquipmentEntity = session.get(Equipment.class, selectedEquipmentId);

                if (selectedEquipmentEntity != null) {
                    // Create a new Transaction entity
                    Transactions newTransaction = new Transactions(currentCustomer);
                    newTransaction.setEquipment(selectedEquipmentEntity);
                    newTransaction.setCustomer(currentCustomer);

                    // Save the new Transaction
                    session.save(newTransaction);

                    JOptionPane.showMessageDialog(this,
                            "Equipment registered: " + selectedEquipmentEntity.getEquip_id());

                    transaction.commit();
                } else {
                    JOptionPane.showMessageDialog(this, "Selected equipment not found.");
                }
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
        // SwingUtilities.invokeLater(() -> new
        // EquipmentRegistrationGUI(sampleCustomer));
        SwingUtilities.invokeLater(() -> new Transactions(sampleCustomer));
    }
}

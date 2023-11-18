package models;

import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.hibernate.Session;
import org.hibernate.Transaction;

import factories.SessionFactoryBuilder;

public class Equipment extends JInternalFrame {

    public String equip_id;

    public String equip_name;

    public String category;

    public String availability;

    public double cost;

    /*
     * public Date dateRequired;
     * 
     * public void setDateRequired(Date dateRequired) {
     * this.dateRequired = dateRequired;
     * }
     * public Date getDateRequired() {
     * return dateRequired;
     * }
     * 
     * public void setDate(Date dateRequired) {
     * this.dateRequired = dateRequired;
     * }
     */

    public Customer customer;

    public void setEquip_id(String equip_id) {
        this.equip_id = equip_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getAvailability() {
        return availability;
    }

    public String getEquip_id() {
        return equip_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String isAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getEquip_name() {
        return equip_name;
    }

    public void setEquip_name(String equip_name) {
        this.equip_name = equip_name;
    }

    public Equipment(String equip_id, String c, String availability2, double cost, String equip_name) {
        this.equip_name = equip_name;
        this.equip_id = equip_id;
        this.availability = availability2;
        this.category = c;
        this.cost = cost;
    }

    private JFrame mainFrame;
    private JDesktopPane desktopPane;

    public Equipment() {
        super("Equipment", true, true, true, true);
        initialize();
    }

    private void initialize() {
        mainFrame = new JFrame("Equipment Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);

        desktopPane = new JDesktopPane();
        mainFrame.setContentPane(desktopPane);

        createMenuBar();
        mainFrame.setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu equipmentMenu = new JMenu("Equipment");
        JMenuItem addEquipmentItem = new JMenuItem("Add Equipment");

        addEquipmentItem.addActionListener(e -> openAddEquipmentFrame());

        equipmentMenu.add(addEquipmentItem);
        menuBar.add(equipmentMenu);

        mainFrame.setJMenuBar(menuBar);
    }

    private double getCategoryCost(String category) {
        switch (category) {
            case "Lighting":
                return 50.0;
            case "Power":
                return 75.0;
            case "Sound":
                return 100.0;
            // Add additional cases for other categories as needed
            default:
                return 0.0; // Default cost if category is not recognized
        }
    }

    private void openAddEquipmentFrame() {
        JInternalFrame addEquipmentFrame = new JInternalFrame("Add Equipment", true, true, true, true);
        addEquipmentFrame.setSize(300, 300);
        addEquipmentFrame.setLayout(new GridLayout(7, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel idLabel = new JLabel("Equipment Id:");
        JTextField idField = new JTextField();

        /* */
        JLabel categoryLabel = new JLabel("Category:");
        String[] categories = { "Lighting", "Power", "Sound" };
        JComboBox<String> categoryComboBox = new JComboBox<>(categories);

        JLabel availabilityLabel = new JLabel("Availability:");
        String[] availabilityOptions = { "Yes", "No" };
        JComboBox<String> availabilityComboBox = new JComboBox<>(availabilityOptions);

        JLabel costLabel = new JLabel("Preset Cost:");
        JLabel dynamicCostLabel = new JLabel();

        JLabel dateLabel = new JLabel("Date:");
        JTextField dateField = new JTextField("YYYY-MM-DD");

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equip_id = idField.getText();
                equip_name = nameField.getText();
                category = (String) categoryComboBox.getSelectedItem();
                availability = (String) availabilityComboBox.getSelectedItem();
                String dateString = dateField.getText();

                // Parse date string to Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateRequired = null;
                try {
                    dateRequired = dateFormat.parse(dateString);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(addEquipmentFrame, "Invalid date format. Use YYYY-MM-DD.");
                    return;
                }

                // Get the category cost using a method
                double cost = getCategoryCost(category);
                dynamicCostLabel.setText(String.format("$%.2f", cost));

                System.out.println("Equipment Id: " + equip_id);
                System.out.println("Name: " + equip_name);
                System.out.println("Category: " + category);
                System.out.println("Availability: " + availability);
                System.out.println("Date: " + dateRequired);
                System.out.println("Preset Cost: $" + cost);

                Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();
                Transaction transaction = null;
                try {
                    transaction = session.beginTransaction();

                    Equipment newEquipment = new Equipment(equip_id, category, availability, cost,
                            equip_name);

                    session.save(newEquipment);

                    transaction.commit();
                    session.close();
                } catch (RuntimeException re) {
                    re.printStackTrace();
                    if (transaction != null && transaction.isActive()) {
                        transaction.rollback();
                    }
                    re.printStackTrace();
                }
                addEquipmentFrame.dispose();
                mainFrame.dispose();
            }
        });

        addEquipmentFrame.add(nameLabel);
        addEquipmentFrame.add(nameField);
        addEquipmentFrame.add(idLabel);
        addEquipmentFrame.add(idField);
        addEquipmentFrame.add(categoryLabel);
        addEquipmentFrame.add(categoryComboBox);
        addEquipmentFrame.add(availabilityLabel);
        addEquipmentFrame.add(availabilityComboBox);
        addEquipmentFrame.add(costLabel);
        addEquipmentFrame.add(dynamicCostLabel);
        addEquipmentFrame.add(dateLabel);
        addEquipmentFrame.add(dateField);
        addEquipmentFrame.add(addButton);

        desktopPane.add(addEquipmentFrame);
        addEquipmentFrame.setVisible(true);

    }

}

import javax.swing.JButton;
import javax.swing.JComboBox;
//import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Date;

import javax.swing.AbstractButton;
//import java.awt.event.ItemEvent;
import javax.swing.ButtonGroup;
//import javax.swing.JButton;
import javax.swing.JRadioButton;

//import javax.swing.JCheckBox;

//import java.awt.event.ItemEvent;

//import java.awt.event.ItemListener;

import javax.swing.JDesktopPane;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Equipment extends Customer {

    public int equipcust_id;

    public int day;
    public int month;
    public int year;

public class Equipment extends Customer implements Serializable {
    private static final long serialVersionUID = 1L;

	public String equip_id;
	public String equip_name;
    public String category;
    public boolean availability;
    public double cost;
    
    private Connection dbConn = null;
	private Statement stmt;
	private ResultSet result;
	private static final Logger logger = LogManager.getLogger(DBConnectorFactory.class);

    public Date date;

    public int getEquipcust_id() {
        return equipcust_id;
    }

    public Equipment(String equip_id, String equip_name, String category, boolean availability, double cost) {
        this.equip_id = equip_id;
    	this.equip_name = equip_name;
        this.category = category;
        this.availability = false;
        this.cost = cost;
    }

    /*public Equipment(int equipcust_id, double accountBalance, int ui, String c, boolean a, double cost, Date date) {
        super(ui, accountBalance);
        this.equipcust_id = equipcust_id;
        this.Availability = a;
        this.category = c;
        this.cost = cost;
    }*/
    
    public String getEquip_id() {
        return equip_id;
    }

    public void setEquip_id(String equip_id) {
        this.equip_id = equip_id;
    }
    
    public String getEquip_name() {
		return equip_name;
	}

	public void setEquip_name(String equip_name) {
		this.equip_name = equip_name;
	}
	
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        category = category;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        availability = false;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEquipcust_id(int equipcust_id) {
        this.equipcust_id = equipcust_id;
    }

    public Equipment() {
        this.Category = "";
        this.Availability = false;
        this.cost = 0.0;
        this.date = new Date();
    }

    public Equipment(int equipcust_id, String c, boolean a, double cost, Date date) {
        this.equipcust_id = equipcust_id;
        this.Availability = a;
        this.Category = c;
        this.cost = cost;
        this.date = new Date();
    }

    public Equipment(int equipcust_id, double accountBalance, int ui, String c, boolean a, double cost, Date date) {
        super(ui, accountBalance);
        this.equipcust_id = equipcust_id;
        this.Availability = a;
        this.Category = c;
        this.cost = cost;
    }

    public void EquipmentForm() {

        JFrame frame;

        JLabel categoryLabel;

        JTextArea categoryJTextArea;

        JTextField categoryTextField;

        JLabel availabilityLabel;

        JLabel avalabilityLabel;
        JTextField avalabilityTextField;

        JTextField dayTextField;
        JTextField monthTextField;
        JTextField yearTextField;

        JTextArea idTextArea;

        JLabel costLabel;
        JTextField costTextField;

        JLabel dateLabel;
        JTextField dateTextField;

        JRadioButton trueRadioButton;
        JRadioButton falseRadioButton;
        ButtonGroup availabilityGroup;

        JDesktopPane desktopPane = new JDesktopPane();

        // System.out.println(cust.getCust_Id());

        frame = new JFrame("Grizzly's Entertainment: Equipment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JInternalFrame Frame1 = new JInternalFrame("Grizzly Entertainment - Equipment", true, true, true, true);

        JComboBox<String> categoryComboBox;
        String[] category = { "Staging", "Lighting", "Power", "Sound" };

        JButton viewSelectionButton;

        Frame1.setLayout(new FlowLayout()); // You can use a different layout manager if needed

        Frame1.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);

        categoryJTextArea = new JTextArea(10, 10);

        idTextArea = new JTextArea(10, 10);
        // categoryTextField = new JTextField(getDay());
        categoryTextField = new JTextField();
        Frame1.add(categoryJTextArea);

        idTextArea = new JTextArea("Enter Id: ");
        JTextField idTextField = new JTextField(15);

        JTextArea costJTextArea = new JTextArea(10, 10);
        costTextField = new JTextField();
        Frame1.add(costJTextArea);

        // JTextArea availabilityJTextArea = new JTextArea(10,10);

        viewSelectionButton = new JButton("Display");
        Frame1.add(viewSelectionButton);

        categoryLabel = new JLabel("Category: ");
        Frame1.add(categoryLabel);

        categoryComboBox = new JComboBox<String>(category);
        categoryComboBox.setSelectedIndex(1);
        Frame1.add(categoryComboBox);

        availabilityLabel = new JLabel("Availability");
        // genderLabel.setBounds(50, 250, 75, 30);
        frame.add(availabilityLabel);
        trueRadioButton = new JRadioButton("Yes: ");
        falseRadioButton = new JRadioButton("No: ");

        availabilityGroup = new ButtonGroup();
        availabilityGroup.add(trueRadioButton);
        availabilityGroup.add(falseRadioButton);

        // JLabel label = new JLabel("Enter Rental Date (yyyy-MM-dd):");
        JLabel day = new JLabel("Enter day: ");
        JLabel month = new JLabel("Enter month: ");
        JLabel year = new JLabel("Enter year: ");

        // JTextField dateField = new JTextField(10);
        dayTextField = new JTextField(10);
        monthTextField = new JTextField(10);
        yearTextField = new JTextField(10);
        JButton submitButton = new JButton("Submit");

        Frame1.add(idTextArea);
        Frame1.add(idTextField);
        Frame1.add(availabilityLabel);
        Frame1.add(falseRadioButton);
        Frame1.add(trueRadioButton);
        Frame1.add(day);
        Frame1.add(month);
        Frame1.add(year);
        Frame1.add(dayTextField);
        Frame1.add(monthTextField);
        Frame1.add(yearTextField);
        Frame1.add(submitButton);

        // date.start(stage);

        desktopPane.add(Frame1);
        frame.add(desktopPane);

        viewSelectionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Customer customer = new Customer();
                Equipment equip = new Equipment();

                String category = categoryComboBox.getSelectedItem().toString().trim();

                equip.setCategory(category);

                // int id = idTextArea.setText(cust_id);

                if (category == "Lighting") {
                    categoryJTextArea.setText(category);
                    cost = 25000;
                    equip.setCost(25000);
                } else {
                    if (category == "Staging") {
                        categoryJTextArea.setText(category);
                        equip.setCost(25000);
                    } else {
                        if (category == "Power") {
                            categoryJTextArea.setText(category);
                            equip.setCost(25000);
                        } else {
                            if (category == "Sound") {
                                categoryJTextArea.setText(category);
                                equip.setCost(25000);
                            }
                        }
                    }
                }

            }

        });

        Frame1.setVisible(true);
        Frame1.setSize(500, 480);
        frame.setVisible(true);
        frame.setSize(600, 600);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                /*
                 * Equipment equip = new Equipment();
                 * Customer cust = new Customer();
                 * 
                 * int day = Integer.parseInt(dayTextField.getText().trim());
                 * int month = Integer.parseInt(monthTextField.getText().trim());
                 * int year = Integer.parseInt(yearTextField.getText().trim());
                 * equipcust_id = Integer.parseInt(idTextField.getText().trim());
                 * String category = categoryComboBox.getSelectedItem().toString().trim();
                 * cust_id = 17;
                 * 
                 * boolean availability;
                 * // cost = equip.getCost();
                 * 
                 * if (falseRadioButton.isSelected()) {
                 * availability = false;
                 * } else {
                 * availability = true;
                 * }
                 * 
                 * equip.setAvailability(availability);
                 * equip.setCategory(category);
                 * equip.setEquipcust_id(cust_id);
                 * equip.setCust_Id(cust_id);
                 * equip.setCost(25000.00);
                 * 
                 * System.out.println(availability);
                 * System.out.println(equipcust_id);
                 * System.out.print(day);
                 * System.out.print(month);
                 * System.out.println(year);
                 * System.out.println(category);
                 * System.out.println(cost);
                 * System.out.println(cust_id);
                 * 
                 * // cost = equip.setCost(25000);
                 * SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                 * 
                 * Session session =
                 * SessionFactoryBuilder.getSessionFactory().getCurrentSession();
                 * Transaction transaction = null;
                 * 
                 * try {
                 * transaction = session.beginTransaction();
                 * 
                 * date = sdf.parse(String.format("%02d/%02d/%04d", month, day, year));
                 * categoryJTextArea.setText("Chosen Date: " + date);
                 * System.out.println(date);
                 * categoryJTextArea.setText("Availibility: " + availability);
                 * equip.setDate(date);
                 * 
                 * System.out.println(equipcust_id);
                 * Equipment newEquipment = new Equipment(cust_id, accountBalance, equipcust_id,
                 * category,
                 * availability, cost, date);
                 * 
                 * session.save(newEquipment);
                 * 
                 * transaction.commit();
                 * session.close();
                 * } catch (RuntimeException re) {
                 * re.printStackTrace();
                 * if (transaction != null && transaction.isActive()) {
                 * transaction.rollback();
                 * }
                 * re.printStackTrace();
                 * } catch (ParseException pe) {
                 * pe.printStackTrace();
                 * }
                 */
                Session session = SessionFactoryBuilder.getSessionFactory().getCurrentSession();
                Transaction transaction = null;

                try {
                    transaction = session.beginTransaction();

                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    Equipment equip = new Equipment();
                    // equipcust_id = Integer.parseInt(idTextField.getText().trim());

                    int day = Integer.parseInt(dayTextField.getText().trim());
                    int month = Integer.parseInt(monthTextField.getText().trim());
                    int year = Integer.parseInt(yearTextField.getText().trim());
                    String category = categoryComboBox.getSelectedItem().toString().trim();
                    boolean availability;
                    cust_id = 17;

                    if (falseRadioButton.isSelected()) {
                        availability = false;
                    } else {
                        availability = true;
                    }

                    // Assuming cust_id is set to a valid value, e.g., 17
                    // The equipcust_id will be the same as the cust_id of the associated customer
                    int equipcust_id = 37;
                    int cust_id = 49;
                    System.out.println(equipcust_id);
                    System.out.println(cust_id);

                    date = sdf.parse(String.format("%02d/%02d/%04d", month, day, year));
                    categoryJTextArea.setText("Chosen Date: " + date);
                    System.out.println(date);
                    categoryJTextArea.setText("Availibility: " + availability);
                    equip.setDate(date);

                    Equipment newEquipment = new Equipment(cust_id, accountBalance, equipcust_id, category,
                            availability, cost, date);

                    session.save(newEquipment);

                    transaction.commit();
                    session.close();
                } catch (RuntimeException re) {
                    re.printStackTrace();
                    if (transaction != null && transaction.isActive()) {
                        transaction.rollback();
                    }
                    re.printStackTrace();
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

    }

    public static void main(String[] args) {

        Equipment equipment = new Equipment();
        equipment.EquipmentForm();
    }
}

}

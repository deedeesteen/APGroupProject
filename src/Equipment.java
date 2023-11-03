import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Equipment {

    public String Category;

    public boolean Availability;

    public double cost;

    public Date Date;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public boolean isAvailability() {
        return Availability;
    }

    public void setAvailability(boolean availability) {
        Availability = availability;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }

    public Equipment() {
        this.Category = "";
        this.Availability = false;
        this.cost = 0.0;
        this.Date = new Date();
    }

    public Equipment(String c, boolean a, double cost, Date Date) {
        this.Availability = a;
        this.Category = c;
        this.cost = cost;
        this.Date = new Date(10, 20, 2023);
    }

    public void EquipmentForm() {

        JFrame frame;

        JLabel categoryLabel;

        JTextArea categoryJTextArea;

        JTextField categoryTextField;

        JLabel avalabilityLabel;
        JTextField avalabilityTextField;

        JLabel costLabel;
        JTextField costTextField;

        JLabel dateLabel;
        JTextField dateTextField;

        frame = new JFrame("Grizzly's Entertainment: Equipment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JInternalFrame Frame1 = new JInternalFrame("Grizzly Entertainment - Equipment",
                true, true, true, true);

        JComboBox<String> categoryComboBox;
        String[] category = { "Staging", "Lighting", "Power", "Sound" };

        JButton viewSelectionButton;

        /*
         * frame = new JFrame("Grizzly's Entertainment: Equipment");
         * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         * 
         * categoryJTextArea = new JTextArea(10, 10);
         * 
         * viewSelectionButton = new JButton("Display");
         * viewSelectionButton.setBounds(150, 340, 200, 30);
         * frame.add(viewSelectionButton);
         * 
         * categoryLabel = new JLabel("Sex: ");
         * categoryLabel.setBounds(50, 210, 75, 30);
         * frame.add(categoryLabel);
         * categoryComboBox = new JComboBox<String>(category);
         * categoryComboBox.setBounds(150, 210, 200, 30);
         * categoryComboBox.setSelectedIndex(1);
         * frame.add(categoryComboBox);
         * 
         */

        Frame1.setDefaultCloseOperation((JInternalFrame.EXIT_ON_CLOSE));
        categoryJTextArea = new JTextArea(10, 10);

        viewSelectionButton = new JButton("Display");
        viewSelectionButton.setBounds(150, 340, 200, 30);

        Frame1.add(viewSelectionButton);

        categoryLabel = new JLabel("Category: ");
        categoryLabel.setBounds(50, 210, 75, 30);
        Frame1.add(categoryLabel);
        
        categoryComboBox = new JComboBox<String>(category);
        categoryComboBox.setBounds(150, 210, 200, 30);
        categoryComboBox.setSelectedIndex(1);
        Frame1.add(categoryComboBox);

        frame.add(Frame1);

        viewSelectionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Customer customer = new Customer();
                Equipment equip = new Equipment();

                equip.setCategory(categoryComboBox.getSelectedItem().toString().trim());

                String category = equip.getCategory();

                if (category == "Lighting") {
                    // System.out.println("");
                    categoryLabel.setText(categoryJTextArea.getText());
                } else {
                    if (category == "") {
                        // System.out.println();
                        categoryLabel.setText(categoryJTextArea.getText());
                    } else {
                        if (category == "") {
                            categoryLabel.setText(categoryJTextArea.getText());
                        } else {
                            if (category == "") {
                                // System.out.println("");
                                categoryLabel.setText(categoryJTextArea.getText());
                            }
                        }
                    }
                }

            }

        });

        Frame1.setVisible(true);
        Frame1.setSize(400, 400);
        frame.setVisible(true);
    }

}

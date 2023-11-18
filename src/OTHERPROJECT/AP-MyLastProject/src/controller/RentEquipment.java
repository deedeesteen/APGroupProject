package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class RentEquipment extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private ImageIcon bkgrd;
    private JLabel custdash, rentlabel, categorylabel, bkgrdlabel;
    private JInternalFrame internalFrame;
    private Color GRIZ = new Color(130, 110, 90);
    private Color TAN = new Color(232, 231, 177);
    private Color ROSY = new Color(255, 87, 70);

    public RentEquipment() {
        super("Rent Equipment", true, true, true, true);
        rentGUI();
    }

    public void rentGUI() {
        JFrame frame = new JFrame("Grizzly Entertainment: ");
        internalFrame = new JInternalFrame("Rent Equipment");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JTextArea resultTA = new JTextArea();

        rentlabel = new JLabel("Rent Equipment");
        rentlabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 30));
        rentlabel.setForeground(ROSY);

        categorylabel = new JLabel("Select Category:");
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[] { "Staging", "Lighting", "Power", "Sound" });
        JButton viewButton = new JButton("View Equipment");
        viewButton.setSize(210, 40);
        viewButton.setFont(new Font("Verdana", Font.BOLD, 13));

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle viewing equipment based on category
                String selectedCategory = (String) categoryComboBox.getSelectedItem();

                // Simulated data, replace with actual data retrieval logic
                String[] columnNames = { "Equipment Name", "Availability", "Cost" };
                Object[][] data = {
                        { "Equipment1", "Available", "$50" },
                        { "Equipment2", "Not Available", "$75" },
                        // Add more rows as needed
                };

                JTable equipmentTable = new JTable(data, columnNames);
                resultTA.setText(""); // Clear previous text
                resultTA.append("Equipment for Category: " + selectedCategory + "\n\n");
                resultTA.append("Date\tEquipment Name\tAvailability\tCost\n");

                // Add rows to the resultTextArea based on the equipment data
                for (int i = 0; i < data.length; i++) {
                    resultTA.append("DatePlaceholder\t" + data[i][0] + "\t" + data[i][1] + "\t" + data[i][2] + "\n");
                    // Replace "DatePlaceholder" with the actual date selected by the customer
                }
            }
        });

        JPanel topPanel = new JPanel();
        // topPanel.add(rentlabel);

        JPanel inputPanel = new JPanel();
        inputPanel.add(categorylabel);
        inputPanel.add(categoryComboBox);
        inputPanel.add(viewButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultTA), BorderLayout.CENTER);

        // Additional components for selecting date and providing quotation can be added
        // here

        // Loads the app background image
        bkgrd = new ImageIcon("./images/bkgrd2.png");
        // Set image to the label
        bkgrdlabel = new JLabel(bkgrd);
        bkgrdlabel.setBounds(370, 0, 650, 650);

        internalFrame.add(panel);
        // internalFrame.add(bkgrdlabel);
        internalFrame.setBackground(GRIZ);
        internalFrame.setVisible(true);

        frame.add(internalFrame);
        frame.setVisible(true);
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

    }

    public static void main(String[] args) {

        new RentEquipment();
    }
}

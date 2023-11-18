//Import statements
import java.sql.Statement;
import java.awt.Color;
import java.awt.Font;

//import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField.AbstractFormatter;

import java.util.ArrayList;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class CustomerInformation extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private ImageIcon bkgrd;
	private JLabel custdash, infolabel, bkgrdlabel;
	private JInternalFrame internalFrame;
	private Color GRIZ = new Color(130, 110, 90);
	private Color TAN = new Color(232, 231, 177);
	private Color ROSY = new Color(255, 87, 70);

	public CustomerInformation() {
		this.custInfoGUI();
	}

// Additional components
private JTextArea pastTransactionsTextArea;
private JButton viewPastTransactionsButton;

// Additional components for equipment rental
private JLabel equipmentLabel, categoryLabel, dateLabel;
private JComboBox<String> categoryComboBox;
//private JDatePickerImpl datePicker;
private JButton checkAvailabilityButton;
private JTextArea messageTextArea;
private JButton submitMessageButton;


private void custInfoGUI() {
   internalFrame = new JInternalFrame("Customer Information");

   custdash = new JLabel("Customer Dashboard");
   custdash.setFont(new Font("Verdana", Font.BOLD, 30));
   custdash.setForeground(Color.WHITE);
   custdash.setBounds(400, 50, 1000, 50);

   infolabel = new JLabel("Customer Information");
   infolabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 30));
   infolabel.setForeground(ROSY);
   infolabel.setBounds(505, 50, 1000, 50);

   equipmentLabel = new JLabel("Equipment Rental:");
   equipmentLabel.setFont(new Font("Verdana", Font.BOLD, 20));
   equipmentLabel.setForeground(Color.WHITE);
   equipmentLabel.setBounds(50, 120, 250, 30);

   categoryLabel = new JLabel("Category:");
   categoryLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
   categoryLabel.setForeground(Color.WHITE);
   categoryLabel.setBounds(50, 160, 80, 25);

   String[] categories = {"Staging", "Lighting", "Power", "Sound"};
   categoryComboBox = new JComboBox<>(categories);
   categoryComboBox.setBounds(140, 160, 150, 25);

   dateLabel = new JLabel("Date:");
   dateLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
   dateLabel.setForeground(Color.WHITE);
   dateLabel.setBounds(50, 200, 80, 25);

   /*UtilDateModel model = new UtilDateModel();
   JDatePanelImpl datePanel = new JDatePanelImpl(model);
   datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
   datePicker.setBounds(140, 200, 150, 25);*/

   checkAvailabilityButton = new JButton("Check Availability");
   checkAvailabilityButton.setBounds(50, 240, 200, 30);
   checkAvailabilityButton.addActionListener(e -> {
       String selectedCategory = (String) categoryComboBox.getSelectedItem();
      // LocalDate selectedDate = (LocalDate) datePicker.getModel().getValue();
      // handleAvailabilityAndQuotation(selectedCategory, selectedDate);
   });

   JLabel pastTransactionsLabel = new JLabel("Past Transactions:");
   pastTransactionsLabel.setFont(new Font("Verdana", Font.BOLD, 20));
   pastTransactionsLabel.setForeground(Color.WHITE);
   pastTransactionsLabel.setBounds(50, 300, 250, 30);

   pastTransactionsTextArea = new JTextArea();
   pastTransactionsTextArea.setEditable(false);
   JScrollPane pastTransactionsScrollPane = new JScrollPane(pastTransactionsTextArea);
   pastTransactionsScrollPane.setBounds(50, 350, 600, 150);
   
   messageTextArea = new JTextArea();
   messageTextArea.setBounds(50, 520, 600, 100);

   submitMessageButton = new JButton("Submit Message");
   submitMessageButton.setBounds(670, 520, 150, 30);
   submitMessageButton.addActionListener(e -> submitMessage());

   viewPastTransactionsButton = new JButton("View Past Transactions");
   viewPastTransactionsButton.setBounds(50, 520, 200, 30);
   viewPastTransactionsButton.addActionListener(e -> {
  	    List<String> pastTransactionsList = fetchPastTransactionsFromDatabase();

  	    if (pastTransactionsList.isEmpty()) {
  	        JOptionPane.showMessageDialog(internalFrame, "No past transactions found.");
  	    } else {
  	        Object[] transactionsArray = pastTransactionsList.toArray();
  	        Object selectedTransaction = JOptionPane.showInputDialog(
  	                internalFrame,
  	                "Select a past transaction:",
  	                "View Past Transactions",
  	                JOptionPane.QUESTION_MESSAGE,
  	                null,
  	                transactionsArray,
  	                transactionsArray[0]);

  	        if (selectedTransaction != null) {
  	            pastTransactionsTextArea.setText((String) selectedTransaction);
  	        }
  	    }
  	});


  	

  
   internalFrame.setLayout(null);
   internalFrame.add(custdash);
   internalFrame.add(infolabel);
   internalFrame.add(equipmentLabel);
   internalFrame.add(categoryLabel);
   internalFrame.add(categoryComboBox);
   internalFrame.add(dateLabel);
   internalFrame.add(messageTextArea);
   internalFrame.add(submitMessageButton);
   //internalFrame.add(datePicker);
   internalFrame.add(checkAvailabilityButton);
   internalFrame.add(pastTransactionsLabel);
   internalFrame.add(pastTransactionsScrollPane);
   internalFrame.add(viewPastTransactionsButton);

   internalFrame.setSize(800, 600);
   internalFrame.setBackground(GRIZ);
   internalFrame.setVisible(true);
}



private List<String> fetchPastTransactionsFromDatabase() {
	List<String> pastTransactionsList = new ArrayList<>();

  try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "your_username", "your_password");
       Statement statement = connection.createStatement();
       ResultSet resultSet = statement.executeQuery("SELECT * FROM past_transactions")) {

      while (resultSet.next()) {
          // Retrieve information from the result set and format it as a string
          String transactionDetails = "Date: " + resultSet.getString("date_of_rental") +
                  "\nEquipment: " + resultSet.getString("equipment_types") +
                  "\nMoney Paid: $" + resultSet.getDouble("money_paid");

          pastTransactionsList.add(transactionDetails);
      }

  } catch (SQLException ex) {
      ex.printStackTrace();
      Logger.getLogger(CustomerInformation.class.getName()).log(Level.SEVERE, "Error fetching past transactions", ex);
  }

  return pastTransactionsList;
}

private List<String> showPastTransactionsListDialog(List<String> pastTransactionsList) {
  Object[] transactionsArray = pastTransactionsList.toArray();

  Object selectedTransaction = JOptionPane.showInputDialog(
          internalFrame,
          "Select a past transaction:",
          "View Past Transactions",
          JOptionPane.QUESTION_MESSAGE,
          null,
          transactionsArray,
          transactionsArray[0]);

  if (selectedTransaction != null) {
      pastTransactionsTextArea.setText((String) selectedTransaction);
  }
  return pastTransactionsList;
}





private void handleAvailabilityAndQuotation(String category, LocalDate selectedDate) {
   boolean isAvailable = checkAvailability(category, selectedDate);

   if (isAvailable) {
       double quotation = calculateQuotation(category);
       JOptionPane.showMessageDialog(internalFrame, "Equipment is available!\nQuotation: $" + quotation);
   } else {
       JOptionPane.showMessageDialog(internalFrame, "Equipment is not available on the selected date.");
   }
}

private boolean checkAvailability(String category, LocalDate selectedDate) {
	 try (Connection connection = DriverManager.getConnection("", "", "");
           PreparedStatement preparedStatement = connection.prepareStatement(
                   "SELECT Available FROM EquipmentAvailability WHERE Category = ? AND Date = ?")) {

          preparedStatement.setString(1, category);
          preparedStatement.setDate(2, java.sql.Date.valueOf(selectedDate));

          try (ResultSet resultSet = preparedStatement.executeQuery()) {
              return resultSet.next() && resultSet.getBoolean("Available");
          }
      } catch (SQLException e) {
          e.printStackTrace();
          return false; // Handle the exception appropriately in a real application
      }
}

private double calculateQuotation(String category) {
	    try (Connection connection = DriverManager.getConnection("", "", "");
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "SELECT Quotation FROM EquipmentQuotations WHERE Category = ?")) {

	            preparedStatement.setString(1, category);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    return resultSet.getDouble("Quotation");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            Logger.getLogger(CustomerInformation.class.getName()).log(Level.SEVERE, "Error checking availability", e);
	        }

	        return 0.0;
   }


private static class DateLabelFormatter extends AbstractFormatter {
   private final String datePattern = "dd/MM/yyyy";
   private final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

   @Override
   public Object stringToValue(String text) throws ParseException {
       return dateFormatter.parseObject(text);
   }

   @Override
   public String valueToString(Object value) throws ParseException {
       if (value != null) {
           return dateFormatter.format((Date) value);
       }
       return "";
   }
}

/*private void showPastTransactionsDialog() {
	    String[] pastTransactions = {
	            "Past Transaction 1\nDate: dd/mm/yyyy\nEquipment: Staging, Lighting\nMoney Paid: $100.00",
	            "Past Transaction 2\nDate: dd/mm/yyyy\nEquipment: Power, Sound\nMoney Paid: $150.00"
	            // Add more past transactions as needed
	    };

	    String selectedTransaction = (String) JOptionPane.showInputDialog(
	            internalFrame,
	            "Select a past transaction:",
	            "View Past Transactions",
	            JOptionPane.QUESTION_MESSAGE,
	            null,
	            pastTransactions,
	            pastTransactions[0]);

	    if (selectedTransaction != null) {
	        // Display the selected past transaction in the text area
	        pastTransactionsTextArea.setText(selectedTransaction);
	    }
	}*/
private void submitMessage() {
    String message = messageTextArea.getText();

    if (!message.isEmpty()) {
        saveMessageToDatabase(message);
        JOptionPane.showMessageDialog(internalFrame, "Message submitted successfully!");
        messageTextArea.setText(""); // Clear the text area after submission
    } else {
        JOptionPane.showMessageDialog(internalFrame, "Please enter a message before submitting.");
    }
}
private void saveMessageToDatabase(String message) {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "your_username", "your_password");
         PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer_messages (message) VALUES (?)")) {

        preparedStatement.setString(1, message);
        preparedStatement.executeUpdate();

    } catch (SQLException ex) {
        ex.printStackTrace();
        Logger.getLogger(CustomerInformation.class.getName()).log(Level.SEVERE, "Error saving customer message", ex);
    }
}
}

package factories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import models.User;

public class UserSerializable {

    String filename = "Users.ser";

    public static void saveToFile(ArrayList<User> list) {
        String filename = "Users.ser";

        try {
            for (User u : list) {
                // ObjectOutputStream os = new ObjectOutputStream(new
                // FileOutputStream("Employess.ser", true));
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename, false));

                // String employeeObject = Name + "\t" + Address + "\t" + TRN + "\t" +
                // phoneNumber + "\t" + dateOfBirth + "\n";
                // doing this gives a string cannot cast to class
                os.writeObject(list);
                os.flush();
                os.close();
                System.out.println("Read User Record: " + u.getid() + " \t" + u.getPassword());
            }
        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
            System.err.println("FileNotFoundException thrown: Error saving record");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.err.println("IOException thrown: Error saving record");

        }

    }

    public static ArrayList<User> readFromFile() {
        ArrayList<User> list = new ArrayList<>();

        try (ObjectInputStream file = new ObjectInputStream(new FileInputStream("Users.ser"))) {
            // Read the object, but don't cast it to ArrayList<User> directly
            Object obj = file.readObject();

            // Check if the object is an ArrayList<User>
            if (obj instanceof ArrayList) {
                // Cast to ArrayList<User> and assign to the list variable
                list = (ArrayList<User>) obj;
                System.out.println("Deserialized Data:");

                for (User user : list) {
                    // Print user information here if needed
                    System.out.println("ID: " + user.getid() + ", password: " + user.getPassword());

                    JOptionPane.showMessageDialog(null, "Login Successful!",
                            "Login Status", JOptionPane.INFORMATION_MESSAGE);

                    JOptionPane.showMessageDialog(null, "Welcome " + user.getName() +
                            "\nID Num: " + user.getid(), "INFO", JOptionPane.INFORMATION_MESSAGE);

                }
            } else {
                System.err.println("Error: Unexpected object type in the file");
            }
        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
            System.err.println("FileNotFoundException thrown: Error reading file");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
            System.err.println("Exception thrown: Error reading file");
        }

        return list;
    }

}

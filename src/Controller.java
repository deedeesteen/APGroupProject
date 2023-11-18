import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import client.User;

public class Controller {

	private static final Logger logger = LogManager.getLogger(Controller.class);
	
	//List<User> userInfoList = new ArrayList<>();

	String filename = "Users.ser";
	public static boolean saveUserToFile(User u)
	{
		String filename = "Users.ser";
		try {
			//ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Employess.ser", true));
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename, true));
			
			//String UserObject = Name + "\t" + Address + "\t" + TRN + "\t" + phoneNumber + "\t" + dateOfBirth + "\n"; 
			// doing this gives a string cannot cast to class
			os.writeObject(u);
			os.flush();
			os.close();
			System.out.println("Read User Record: " + u.getid() + "\t" + u.getPassword());
			System.out.println("User has been saved");
			
			return true;
		}catch(FileNotFoundException fnf)
		{
			fnf.printStackTrace();
			System.err.println("FileNotFoundException thrown: Error saving record");
		}catch(IOException ioe){
			ioe.printStackTrace();
			System.err.println("IOException thrown: Error saving record");

		}
		return false;
	}
	
	 public static boolean readUserFromFile(int idNum, String pword, String gender)
	 {
     // Reading the User record from the file
		 User user = loadUserRecord("Users.ser");
		 
		 while(true)
		 {
		 if (user != null) {
		       System.out.println(user.getid());
		        if (user.getid() == idNum) {
		            if (user.getPassword().equals(pword)) // Use equals() to compare strings
		            {
		            	System.out.println("Read User Record: " + user);
		            	return true;
		            } else {
		                System.out.println("Password is incorrect.");
		            }
		        }
		 	}
		 return false;
		 }
	 }
	
	public static User loadUserRecord(String filename) {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            User user = (User)ois.readObject();
            return user;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	// THESE TWO CODES WORK
	public static void saveToFile(ArrayList <User> list)
	{
		String filename = "Users.ser"; 
		
		try {
			for(User u: list)
				{
				//ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Employess.ser", true));
				ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename, false));
			
				//String employeeObject = Name + "\t" + Address + "\t" + TRN + "\t" + phoneNumber + "\t" + dateOfBirth + "\n"; 
				// doing this gives a string cannot cast to class
				os.writeObject(list);
				os.flush();
				os.close();
				System.out.println("Read User Record: " + u.getid() + " \t" + u.getPassword());
				}
			}catch(FileNotFoundException fnf)
			{
				fnf.printStackTrace();
				System.err.println("FileNotFoundException thrown: Error saving record");
			}catch(IOException ioe){
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
							"Login Status" , JOptionPane.INFORMATION_MESSAGE);

                    JOptionPane.showMessageDialog(null,"Welcome " + user.getName() + 
                    		"\nID Num: " + user.getid() ,"INFO", JOptionPane.INFORMATION_MESSAGE);
                
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

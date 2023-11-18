

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBConnectorFactory {
	private static Connection dbConn = null;
	private static final Logger logger = LogManager.getLogger(DBConnectorFactory.class);
	
	public static Connection getDatabaseConnection() {
		if (dbConn == null) {
			String url = "jdbc:mysql://localhost:3306/grizzlyentertainment";
			try {
				dbConn = DriverManager.getConnection(url, "root", "");
				System.out.println("JBDC Connection Established");
				logger.log(Level.INFO, "JBDC Connection Established");
			} catch(SQLException e) {
				System.err.println("SQL Exception: " + e.getMessage());
				logger.log(Level.ERROR, "Error- SQL Exception: " + e.getMessage(), e);
			} catch(Exception e) {
				System.err.println("Unexpected Error: " + e.getMessage());
				logger.log(Level.ERROR, "Unexpected Error: " + e.getMessage(), e);
			}
		}
		return dbConn;
	}
}
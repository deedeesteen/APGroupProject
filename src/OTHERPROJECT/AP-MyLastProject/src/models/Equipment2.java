package models;

import factories.DBConnectorFactory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Equipment2 extends Customer implements Serializable {
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

    public Equipment2() {
        dbConn = DBConnectorFactory.getDatabaseConnection();
        this.equip_id = "";
        this.equip_name = "";
        this.category = "";
        this.availability = false;
        this.cost = 0.0;
    }

    public Equipment2(String equip_id, String equip_name, String category, boolean availability, double cost) {
        this.equip_id = equip_id;
        this.equip_name = equip_name;
        this.category = category;
        this.availability = false;
        this.cost = cost;
    }

    /*
     * public Equipment(int equipcust_id, double accountBalance, int ui, String c,
     * boolean a, double cost, Date date) {
     * super(ui, accountBalance);
     * this.equipcust_id = equipcust_id;
     * this.Availability = a;
     * this.category = c;
     * this.cost = cost;
     * }
     */

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

    @Override
    public String toString() {
        return "Equipment ID: " + equip_id +
                "Equipment Name: " + equip_name +
                "Category: " + category +
                "Availability: " + availability +
                "Cost: " + cost;
    }

    public void createEquip(String equip_id, String equip_name, String category, boolean availability, double cost) {
        String insertSQL = "INSERT INTO grizzlyent.equipment (equip_id, equip_name, category, availability, cost) VALUES ('"
                + equip_id +
                "','" + equip_name + "','" + category + "'," + booleanToBit(availability) + ",'" + cost + "');";
        try {
            stmt = dbConn.createStatement();
            int inserted = stmt.executeUpdate(insertSQL);
            if (inserted == 1) {
                System.out.println("Equipment record inserted successfully!");
                logger.log(Level.INFO, "Equipment record inserted successfully");
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Record Insertion Failed", "Insertion status",
                        JOptionPane.ERROR_MESSAGE);
                logger.log(Level.WARN, "Record Insertion Failed");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.log(Level.ERROR, "Error- SQL Exception: " + e.getMessage(), e);
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
            logger.log(Level.ERROR, "Unexpected Error: " + e.getMessage(), e);
        }
    }

    private int booleanToBit(boolean availability) {
        return availability ? 1 : 0; // 0 - false; 1 - true
    }

    public void readAllEquip() {
        String readAllSQL = "SELECT equip_id, equip_name, category, availability, cost FROM grizzlyent.equipment";
        try {
            stmt = dbConn.createStatement();
            result = stmt.executeQuery(readAllSQL);
            while (result.next()) {
                String equip_id = result.getString("equip_id");
                String equip_name = result.getString("equip_name");
                String category = result.getString("category");
                boolean availability = result.getBoolean("availability");
                double cost = result.getDouble("cost");
                System.out.println("Equipment ID: " + equip_id +
                        "\nEquipment Name: " + equip_name +
                        "\nCategory: " + category +
                        "\nAvailability: " + availability +
                        "\nCost: " + cost + "\n");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.log(Level.ERROR, "Error- SQL Exception: " + e.getMessage(), e);
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
            logger.log(Level.ERROR, "Unexpected Error: " + e.getMessage(), e);
        }
    }

    public void updateEquip(String equip_id, boolean availability) {
        String updateSQL = "UPDATE grizzlyent.equipment SET availability = " + booleanToBit(availability)
                + " WHERE equip_id = '" + equip_id + "';";
        try {
            stmt = dbConn.createStatement();
            int updated = stmt.executeUpdate(updateSQL);
            if (updated == 1) {
                System.out.println("Equipment record updated successfully!");
                logger.log(Level.INFO, "Equipment record updated successfully");
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Record Update Failed", "Update status", JOptionPane.ERROR_MESSAGE);
                logger.log(Level.WARN, "Record Update Failed");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            logger.log(Level.ERROR, "Error- SQL Exception: " + e.getMessage(), e);
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
            logger.log(Level.ERROR, "Unexpected Error: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {

        Equipment2 eq = new Equipment2();

        eq.createEquip("ST100", "Stage Platforms", "Staging", true, 20000);
        eq.createEquip("ST101", "Backdrop", "Staging", true, 8500);
        eq.createEquip("ST102", "Props", "Staging", true, 6000);
        eq.createEquip("ST103", "Curtains", "Staging", true, 3750);
        eq.createEquip("ST104", "Stage Consoles", "Staging", true, 7200.50);

    }

}
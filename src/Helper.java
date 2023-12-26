
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class Helper {
    
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/mabs";
    private static final String DB_USERNAME = "phpMyAdmin";
    private static final String DB_PASSWORD = "admin";
    
    private boolean value;

    //  INVENTORY DATABASE
    public static void displayInventoryItems() {
        Connection connection = null;
        PreparedStatement selectStatement = null;
        ResultSet selectRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String selectQuery = "SELECT * FROM inventory";
            selectStatement = connection.prepareStatement(selectQuery);
            selectRs = selectStatement.executeQuery();
            
            dislayInventoryTableItems(selectRs);
        
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            
        } finally {
            try {
                if (selectRs != null) {
                    selectRs.close();
                }
                if (selectStatement != null) {
                    selectStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    
    // Add New Item
    public static void addNewInventoryItem(String name, int quantity, String location, String category) {
        Connection connection = null;
        PreparedStatement insertStatement = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String insertQuery = "INSERT INTO inventory(name, good_item, bad_item, category, location) VALUES (?,?, ?,?,?)";
            insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, name);
            insertStatement.setInt(2, quantity);
            insertStatement.setInt(3, 0);
            insertStatement.setString(4, category);
            insertStatement.setString(5, location);
            int rowsAffected = insertStatement.executeUpdate();
            
            
            if(rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Successfully add new item.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add new item.\nPlease try again.");
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            
        } finally {
            Helper.displayInventoryItems();

            try {
                if (insertStatement != null) {
                    insertStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    
    // Check Item is already added
    public static boolean checkItemIsAlreadyAdded(String name) {
        Connection connection = null;
        PreparedStatement checkStatement = null;
        ResultSet checkRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String selectQuery = "SELECT * FROM inventory WHERE name=?";
            checkStatement = connection.prepareStatement(selectQuery);
            checkStatement.setString(1, name);
            checkRs = checkStatement.executeQuery();
            
            return checkRs.next();
        
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
            
        } finally {
            try {
                if (checkRs != null) {
                    checkRs.close();
                }
                if (checkStatement != null) {
                    checkStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    
    // Get Item Information
    public static ResultSet getItemInformationById(int itemId) {
        Connection connection = null;
        PreparedStatement selectStatement = null;
        ResultSet selectRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String selectQuery = "SELECT * FROM inventory WHERE item_id=?";
            selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setInt(1, itemId);
            selectRs = selectStatement.executeQuery();
            
            if(selectRs.next()) {
                return selectRs;
            } else {
                return null;
            }
        
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;

        }
    }
    
    // Update Item Information
    public static void updateItemInformation(int itemId, String oldName, String name, int good_item, int bad_item, String category, String location) {
        Connection connection = null;
        PreparedStatement updateStatement = null;
        PreparedStatement updateReturnItemStatement = null;
        PreparedStatement updateHistoryItemStatement = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
            
            String updateQuery = "UPDATE inventory SET name=?,good_item=?,bad_item=?,category=?,location=? WHERE item_id=?";
            updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, name);
            updateStatement.setInt(2, good_item);
            updateStatement.setInt(3, bad_item);
            updateStatement.setString(4, category);
            updateStatement.setString(5, location);
            updateStatement.setInt(6, itemId);
            int rowsAffected = updateStatement.executeUpdate();
            
            if(rowsAffected > 0) {

                // update return item name if the inventory item name is changed
                String updateReturnItem = "UPDATE boroweditem SET item_name=? WHERE item_name=?";
                updateReturnItemStatement = connection.prepareStatement(updateReturnItem);
                updateReturnItemStatement.setString(1, name);
                updateReturnItemStatement.setString(2, oldName);
                updateReturnItemStatement.executeUpdate();
                // update history item name if the inventory item name is changed
                String updateHistoryItem = "UPDATE history SET item_name=? WHERE item_name=?";
                updateHistoryItemStatement = connection.prepareStatement(updateHistoryItem);
                updateHistoryItemStatement.setString(1, name);
                updateHistoryItemStatement.setString(2, oldName);
                updateHistoryItemStatement.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Successfully update the item information.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update the item information.\nPlease try again.");
            }
        
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            
        } finally {
            Helper.displayInventoryItems();

            try {
                if (updateStatement != null) {
                    updateStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    
    // Delete Item
    public static void deleteItem() {
        Connection connection = null;
        PreparedStatement deleteStatement = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            // CHECK RETURN TABLE IF THERE IS SOMEONE BORROWING
            // ISSOMEONE THEN - DO NOT DELETE AND SHOW A MESSAGE
            
            String deleteQuery = "DELETE FROM inventory WHERE item_id=?";
            deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, Tables.getInstance().getRowId());
            int rowsAffected = deleteStatement.executeUpdate();
            
            if(rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Delete item successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failt to delete item.\nPlease try again.");
            }
        
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            
        } finally {
            displayInventoryItems();
            
            try {
                if (deleteStatement != null) {
                    deleteStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    
    // Searching Item
    public static void searchItem(String searchText) {
        Connection connection = null;
        PreparedStatement searchStatement = null;
        ResultSet searchRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String searchQuery = "SELECT * FROM inventory WHERE "
                + "item_id LIKE ? OR "
                + "name LIKE ? OR "
                + "location LIKE ? OR "
                + "category LIKE ?";
            searchStatement = connection.prepareStatement(searchQuery);
            searchStatement.setString(1, "%" + searchText + "%");
            searchStatement.setString(2, "%" + searchText + "%");
            searchStatement.setString(3, "%" + searchText + "%");
            searchStatement.setString(4, "%" + searchText + "%");
            searchRs = searchStatement.executeQuery();
            
            dislayInventoryTableItems(searchRs);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            
        } finally {
            try {
                if (searchRs != null) {
                    searchRs.close();
                }
                if (searchStatement != null) {
                    searchStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    
    // DISPLAY INVENTORY TABLE DATA
    public static void dislayInventoryTableItems(ResultSet data) throws SQLException {
        
        JTable table = Tables.getInstance().getInventoryTable();
        
        DefaultTableModel defaultTable = (DefaultTableModel) table.getModel();
            defaultTable.setRowCount(0); 

            while (data.next()) {
                Object[] row = {
                    data.getString("item_id"),
                    data.getString("name"),
                    data.getInt("good_item") + data.getInt("bad_item"), // quantity
                    data.getString("location"),
                    data.getString("category")
                };

                defaultTable.addRow(row); 
            }
            
            // Create a custom cell renderer to center the values in the specified column
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Set the custom renderer for the specified column
            table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
    }
    
    // SET REQUEST TABLE DATA
    public static void setRequestTableItems() throws SQLException {
        
        JTable table = Tables.getInstance().getRequestTable();
        
        DefaultTableModel defaultTable = (DefaultTableModel) table.getModel();
        defaultTable.setRowCount(0);

        ArrayList<String> checkList = UserData.getInstance().getCheckBoxItems();
            
        for (int i = 0; i < checkList.size(); i++) {
            System.out.println(checkList.get(i));
            Object[] row = {
                checkList.get(i),
                0,
                0
            };

            defaultTable.addRow(row);
        }
            
        // Create a custom cell renderer to center the values in the specified column
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Set the custom renderer for the specified column
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
    }
    
    // CHECK REQUEST VALUES
    public boolean checkRequestValues(JTable table) { 
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();
        
        Connection connection = null;
        PreparedStatement selectStatement = null;
        ResultSet selectRs = null;
        
        this.value = true;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String selectQuery = "SELECT * FROM inventory WHERE name=?";
            
            // Iterate through rows and columns to retrieve cell values
            for (int row = 0; row < rowCount; row++) {
                String item = (String) model.getValueAt(row, 0).toString();
                String goodItemStr = (String) model.getValueAt(row, 1).toString();
                String badItemStr = (String) model.getValueAt(row, 2).toString();
                int goodItem;
                int badItem;
                try {
                    goodItem = Integer.parseInt(goodItemStr);
                    badItem = Integer.parseInt(badItemStr);
                    
                    // check if the item value is valid
                    if(goodItem < 1 && badItem < 1) {
                        JOptionPane.showMessageDialog(null, "Please enter the quantity of your requested item.");
                        return false;
                    }
                    
                    selectStatement = connection.prepareStatement(selectQuery);
                    selectStatement.setString(1, item);
                    selectRs = selectStatement.executeQuery();
                    if(selectRs.next()) {
                        
                        int dbGoodItem = selectRs.getInt("good_item");
                        int dbBadItem = selectRs.getInt("bad_item");

                        if(goodItem > dbGoodItem) {
                            JOptionPane.showMessageDialog(null, item + " good condition has only " + dbGoodItem + " left.\nYou exceed to the item we have.");
                            this.value = false;
                        }
                        if(badItem > dbBadItem) {
                            JOptionPane.showMessageDialog(null, item + " bad condition has only " + dbBadItem + " left.\nYou exceed to the item we have.");
                            this.value = false;
                        }
                    } else {
                        this.value = false;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter an integer value in good/bad condition.");
                    this.value = false;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            this.value = false;
        }
        return this.value;
    }
    
    public boolean saveRequestForm(String name, String idNumber, String cellNumber, String course, String yearLevel, String section, String purpose, String approvedBy, Date borrowDate, Date dueDate, JTable requestTable) {
        DefaultTableModel model = (DefaultTableModel) requestTable.getModel();
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();
        
        Connection connection = null;
        PreparedStatement selectStatement = null;
        PreparedStatement insertStatement = null;
        PreparedStatement updateStatement = null;
        ResultSet selectRs = null;
        
        this.value = true;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String selectQuery = "SELECT * FROM inventory WHERE name=?";
            
            // Iterate through rows and columns to retrieve cell values
            for (int row = 0; row < rowCount; row++) {
                String item = (String) model.getValueAt(row, 0).toString();
                String goodItemStr = (String) model.getValueAt(row, 1).toString();
                String badItemStr = (String) model.getValueAt(row, 2).toString();
                int goodItem;
                int badItem;
                try {
                    goodItem = Integer.parseInt(goodItemStr);
                    badItem = Integer.parseInt(badItemStr);
                    
                    selectStatement = connection.prepareStatement(selectQuery);
                    selectStatement.setString(1, item);
                    selectRs = selectStatement.executeQuery();
                    if(selectRs.next()) {
                        
                        int dbGoodItem = selectRs.getInt("good_item");
                        int dbBadItem = selectRs.getInt("bad_item");

                        if(goodItem > dbGoodItem) {
                            JOptionPane.showMessageDialog(null, item + " good condition has only " + dbGoodItem + " left.\nYou exceed to the item we have.");
                            this.value = false;
                        }
                        if(badItem > dbBadItem) {
                            JOptionPane.showMessageDialog(null, item + " bad condition has only " + dbBadItem + " left.\nYou exceed to the item we have.");
                            this.value = false;
                        }

                        // Create a SimpleDateFormat with the desired format
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        // SAVE TO DATABASE
                        String insertQuery = "INSERT INTO boroweditem(student_name, id_number, cell_number, "
                                + "course, year_level, section, purpose, approvedBy, borrow_date, due_date, "
                                + "item_name, good_item, bad_item) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        insertStatement = connection.prepareStatement(insertQuery);
                        insertStatement.setString(1, name);
                        insertStatement.setString(2, idNumber);
                        insertStatement.setString(3, cellNumber);
                        insertStatement.setString(4, course);
                        insertStatement.setString(5, yearLevel);
                        insertStatement.setString(6, section);
                        insertStatement.setString(7, purpose);
                        insertStatement.setString(8, approvedBy);
                        insertStatement.setString(9, dateFormat.format(borrowDate));
                        insertStatement.setString(10, dateFormat.format(dueDate));
                        insertStatement.setString(11, item);
                        insertStatement.setInt(12, goodItem);
                        insertStatement.setInt(13, badItem);
                        // SUCCESSFULLY SAVE REQUEST ITEM
                        if(insertStatement.executeUpdate() > 0) {
                            // UPDATE INVENTORY VALUE
                            String updateQuery = "UPDATE inventory SET good_item=?, bad_item=? WHERE name=?";
                            updateStatement = connection.prepareStatement(updateQuery);
                            updateStatement.setInt(1, dbGoodItem - goodItem);
                            updateStatement.setInt(2, dbBadItem - badItem);
                            updateStatement.setString(3, item);
                            if(updateStatement.executeUpdate() > 0) {
                                // SUCCESSFULLY UPDATED THE INVENTORY VALUES
                                this.value = true;
                                displayInventoryItems();
                            }
                        }
                        
                    } else {
                        this.value = false;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter an integer value in good/bad condition.");
                    this.value = false;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            this.value = false;
        }
        return this.value;
    }
    
    // RETURN TABLE
    public static void displayReturnTableItems() {
        Connection connection = null;
        PreparedStatement selectStatement = null;
        ResultSet selectRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String selectQuery = "SELECT * FROM boroweditem";
            selectStatement = connection.prepareStatement(selectQuery);
            selectRs = selectStatement.executeQuery();
            
            JTable table = Tables.getInstance().getReturnTable();
        
            DefaultTableModel defaultTable = (DefaultTableModel) table.getModel();
            defaultTable.setRowCount(0); 

            while (selectRs.next()) {
                Object[] row = {
                    selectRs.getString("borrowed_id"),
                    selectRs.getString("id_number"),
                    selectRs.getString("student_name"),
                    selectRs.getString("item_name"),
                    selectRs.getString("borrow_date"),
                    selectRs.getString("due_date"),
                    selectRs.getString("approvedBy"),
                };

                defaultTable.addRow(row); 
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            
        } finally {
            try {
                if (selectRs != null) {
                    selectRs.close();
                }
                if (selectStatement != null) {
                    selectStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    
    // search return table
    public static void searchReturnTableItems(String searchText) {
        Connection connection = null;
        PreparedStatement searchStatement = null;
        ResultSet searchRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String searchQuery = "SELECT * FROM boroweditem WHERE "
                    + " id_number LIKE ? OR "
                    + " student_name LIKE ? OR "
                    + " item_name LIKE ? OR "
                    + " borrow_date LIKE ? OR "
                    + " due_date LIKE ? OR "
                    + " approvedBy LIKE ? ";
            searchStatement = connection.prepareStatement(searchQuery);
            searchStatement.setString(1, "%" + searchText + "%");
            searchStatement.setString(2, "%" + searchText + "%");
            searchStatement.setString(3, "%" + searchText + "%");
            searchStatement.setString(4, "%" + searchText + "%");
            searchStatement.setString(5, "%" + searchText + "%");
            searchStatement.setString(6, "%" + searchText + "%");
            searchRs = searchStatement.executeQuery();
            
            JTable table = Tables.getInstance().getReturnTable();
        
            DefaultTableModel defaultTable = (DefaultTableModel) table.getModel();
            defaultTable.setRowCount(0); 

            while (searchRs.next()) {
                Object[] row = {
                    searchRs.getString("borrowed_id"),
                    searchRs.getString("id_number"),
                    searchRs.getString("student_name"),
                    searchRs.getString("item_name"),
                    searchRs.getString("borrow_date"),
                    searchRs.getString("due_date"),
                    searchRs.getString("approvedBy"),
                };

                defaultTable.addRow(row); 
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            
        } finally {
            try {
                if (searchRs != null) {
                    searchRs.close();
                }
                if (searchStatement != null) {
                    searchStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    
    // delete return borrow item from return table
    public static void deleteBorrowItem() {
        Connection connection = null;
        PreparedStatement updateStatement = null;
        PreparedStatement selectBorrowedItemStatement = null;
        PreparedStatement selectInventoryStatement = null;
        PreparedStatement updateInventoryStatement = null;
        PreparedStatement insertHistoryStatement = null;
        ResultSet selectBorrowedItemRs = null;
        ResultSet selectInventoryRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            // retrieve borrowed item data
            String selectBorrowedItem = "SELECT * FROM boroweditem WHERE borrowed_id = ?";
            selectBorrowedItemStatement = connection.prepareStatement(selectBorrowedItem);
            selectBorrowedItemStatement.setInt(1, Tables.getInstance().getRowId());
            selectBorrowedItemRs = selectBorrowedItemStatement.executeQuery();
            
            if(selectBorrowedItemRs.next()) {
                
                System.out.println("1");
                
                int returnGoodItem = selectBorrowedItemRs.getInt("good_item");
                int returnBadItem = selectBorrowedItemRs.getInt("bad_item");
                
                // RETRIEVE INVENTORY ITEM DATA
                String selectInventoryQuery = "SELECT * FROM inventory WHERE name = ?";
                selectInventoryStatement = connection.prepareStatement(selectInventoryQuery);
                selectInventoryStatement.setString(1, selectBorrowedItemRs.getString("item_name"));
                selectInventoryRs = selectInventoryStatement.executeQuery();
                
                if(selectInventoryRs.next()) {
                    
                    System.out.println("2");
                    
                    int inventoryGoodItem = selectInventoryRs.getInt("good_item");
                    int inventoryBadItem = selectInventoryRs.getInt("bad_item");
                    
                    // UPDATE INVENTORY DATABASE TABLE
                    String updateInventoryQuery = "UPDATE inventory SET good_item=?, bad_item=? WHERE name=?";
                    updateInventoryStatement = connection.prepareStatement(updateInventoryQuery);
                    updateInventoryStatement.setInt(1, inventoryGoodItem + returnGoodItem);
                    updateInventoryStatement.setInt(2, inventoryBadItem + returnBadItem);
                    updateInventoryStatement.setString(3, selectBorrowedItemRs.getString("item_name"));
                    if(updateInventoryStatement.executeUpdate() > 0) {
                        // Create a SimpleDateFormat with the desired format
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        
                        System.out.println("before adding to history");
                        
                        
                        // BEFORE REMOVE LET'S ADD IT TO THE HISTORY DATABASE TABLE
                        String insertHistory = "INSERT INTO history(borrowed_id, student_name, id_number, "
                                + "cell_number, course, year_level, section, purpose, approvedBy, borrow_date, "
                                + "due_date, item_name, good_item, bad_item) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        insertHistoryStatement = connection.prepareStatement(insertHistory);
                        insertHistoryStatement.setInt(1, selectBorrowedItemRs.getInt("borrowed_id"));
                        insertHistoryStatement.setString(2, selectBorrowedItemRs.getString("student_name"));
                        insertHistoryStatement.setString(3, selectBorrowedItemRs.getString("id_number"));
                        insertHistoryStatement.setString(4, selectBorrowedItemRs.getString("cell_number"));
                        insertHistoryStatement.setString(5, selectBorrowedItemRs.getString("course"));
                        insertHistoryStatement.setString(6, selectBorrowedItemRs.getString("year_level"));
                        insertHistoryStatement.setString(7, selectBorrowedItemRs.getString("section"));
                        insertHistoryStatement.setString(8, selectBorrowedItemRs.getString("purpose"));
                        insertHistoryStatement.setString(9, selectBorrowedItemRs.getString("approvedBy"));
                        insertHistoryStatement.setString(10, selectBorrowedItemRs.getString("borrow_date"));
                        insertHistoryStatement.setString(11, selectBorrowedItemRs.getString("due_date"));
                        insertHistoryStatement.setString(12, selectBorrowedItemRs.getString("item_name"));
                        insertHistoryStatement.setString(13, selectBorrowedItemRs.getString("good_item"));
                        insertHistoryStatement.setString(14, selectBorrowedItemRs.getString("bad_item"));
                        insertHistoryStatement.executeUpdate();
                        System.out.println("After adding to history");
                        
//                        
//                        insertStatement.setString(9, dateFormat.format(borrowDate));
                        // REMOVE BORROWED ITEM
                        String updateQuery = "DELETE FROM boroweditem WHERE borrowed_id=?";
                        updateStatement = connection.prepareStatement(updateQuery);
                        updateStatement.setInt(1, Tables.getInstance().getRowId());

                        if(updateStatement.executeUpdate() > 0) {
                            // success message
                            JOptionPane.showMessageDialog(null, "Successfully returned item.");
                            // refresh tables
                            displayInventoryItems();
                            displayReturnTableItems();
                        }
                    }
                }
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            
        } finally {
            
            try {
                if (selectInventoryRs != null) {
                    selectInventoryRs.close();
                }
                if (selectBorrowedItemRs != null) {
                    selectBorrowedItemRs.close();
                }
                if (updateStatement != null) {
                    updateStatement.close();
                }
                if (selectBorrowedItemStatement != null) {
                    selectBorrowedItemStatement.close();
                }
                if (selectInventoryStatement != null) {
                    selectInventoryStatement.close();
                }
                if (updateInventoryStatement != null) {
                    updateInventoryStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    
    // HISTORY TABLE
    public static void displayHistoryTableItems() {
        Connection connection = null;
        PreparedStatement selectStatement = null;
        ResultSet selectRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String selectQuery = "SELECT * FROM history";
            selectStatement = connection.prepareStatement(selectQuery);
            selectRs = selectStatement.executeQuery();
            
            JTable table = Tables.getInstance().getHistoryTable();
        
            DefaultTableModel defaultTable = (DefaultTableModel) table.getModel();
            defaultTable.setRowCount(0); 

            while (selectRs.next()) {
                Object[] row = {
                    selectRs.getString("borrowed_id"),
                    selectRs.getString("id_number"),
                    selectRs.getString("student_name"),
                    selectRs.getString("item_name"),
                    selectRs.getString("borrow_date"),
                    selectRs.getString("due_date"),
                    selectRs.getString("approvedBy"),
                };

                defaultTable.addRow(row); 
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            
        } finally {
            try {
                if (selectRs != null) {
                    selectRs.close();
                }
                if (selectStatement != null) {
                    selectStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
    
    // search history
    public static void searchHistoryTableItems(String searchText) {
        Connection connection = null;
        PreparedStatement searchStatement = null;
        ResultSet searchRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String searchQuery = "SELECT * FROM history WHERE "
                    + " id_number LIKE ? OR "
                    + " student_name LIKE ? OR "
                    + " item_name LIKE ?";
            searchStatement = connection.prepareStatement(searchQuery);
            searchStatement.setString(1, "%" + searchText + "%");
            searchStatement.setString(2, "%" + searchText + "%");
            searchStatement.setString(3, "%" + searchText + "%");
            
            searchRs = searchStatement.executeQuery();
            
            JTable table = Tables.getInstance().getHistoryTable();
        
            DefaultTableModel defaultTable = (DefaultTableModel) table.getModel();
            defaultTable.setRowCount(0); 

            while (searchRs.next()) {
                Object[] row = {
                    searchRs.getString("borrowed_id"),
                    searchRs.getString("id_number"),
                    searchRs.getString("student_name"),
                    searchRs.getString("item_name"),
                    searchRs.getString("borrow_date"),
                    searchRs.getString("approvedBy"),
                };

                defaultTable.addRow(row); 
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            
        } finally {
            try {
                if (searchRs != null) {
                    searchRs.close();
                }
                if (searchStatement != null) {
                    searchStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
        }
    }
}


import javax.swing.JTable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Tuason
 */
public class Tables {
    private static Tables instance = null;
    
    private int rowId;
    private JTable inventoryTable;
    private JTable requestTable;
    private JTable returnTable;
    private JTable historyTable;
    // Private constructor to prevent creating instances outside this class
    private Tables() {}
    
    public static Tables getInstance() {
        if (instance == null) {
            instance = new Tables();
        }
        return instance;
    }

    public JTable getInventoryTable() {
        return inventoryTable;
    }

    public void setInventoryTable(JTable inventoryTable) {
        this.inventoryTable = inventoryTable;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public JTable getRequestTable() {
        return requestTable;
    }

    public void setRequestTable(JTable requestTable) {
        this.requestTable = requestTable;
    }

    public JTable getReturnTable() {
        return returnTable;
    }

    public void setReturnTable(JTable returnTable) {
        this.returnTable = returnTable;
    }

    public JTable getHistoryTable() {
        return historyTable;
    }

    public void setHistoryTable(JTable historyTable) {
        this.historyTable = historyTable;
    }
    
    
}


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Tuason
 */
public class ComboCheckBox extends javax.swing.JPanel {
    
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/mabs";
    private static final String DB_USERNAME = "phpMyAdmin";
    private static final String DB_PASSWORD = "admin";
    
    public ComboCheckBox() {
        // Form Properties
        setLayout(new BorderLayout());
        
        // Data sources
        Vector v = new Vector();
        
        // DATABASE
        Connection connection = null;
        PreparedStatement selectStatement = null;
        ResultSet selectRs = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD);
        
            String selectQuery = "SELECT * FROM inventory";
            selectStatement = connection.prepareStatement(selectQuery);
            selectRs = selectStatement.executeQuery();
            
            while(selectRs.next()) {
                v.add(new JCheckBox(selectRs.getString("name"), false));
            }
        
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        // Set a transparent background color (with alpha component)
        this.setBackground(new Color(255, 255, 255, 0));
        
        // combo box
        CustomComboCheck comboBox = new CustomComboCheck(v);
        add(comboBox, BorderLayout.NORTH);
    }
    
    public static void main(String[] args) {
        ComboCheckBox frame = new ComboCheckBox();
        frame.setSize(350, 350);
        frame.setVisible(true);
    }
    
}


// Custom CheckBox And  ComboBox
class CustomComboCheck extends JComboBox {

//    private ArrayList<String> checkBoxItems = new ArrayList<String>();

    public CustomComboCheck(Vector v) {
        super(v);
        
        // set renderer
        setRenderer(new ComboRenderer());
        
        // set listener
        addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                ourItemSelected();
            }

            // single item selected
            private void ourItemSelected() {
                // get selection
                Object selected = getSelectedItem();
                
                if(selected instanceof JCheckBox chk) {
                    
                    chk.setSelected(!chk.isSelected());
                    repaint();
                    
                    // Update selected items list
                    if (chk.isSelected()) {
                        UserData.getInstance().addCheckBoxItems(chk.getText());
                    } else {
                        UserData.getInstance().removeCheckBoxItems(chk.getText());
                    }
                    
                }
            }
        });
    }

}

// Render CheckBox in ComboBox
class ComboRenderer implements ListCellRenderer {

    private JLabel label;
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        
        // Assign curren propterties of JComboBox to CheckBox e.g. bg, fg colors
        if(value instanceof Component) {
            Component component = (Component) value;
            if(isSelected) {
                component.setBackground(list.getSelectionBackground());
                component.setForeground(list.getSelectionForeground());
            } else {
                component.setBackground(list.getBackground());
                component.setForeground(list.getForeground());
            }
            
            return component;
        } else {
            if(label == null) {
                label = new JLabel(value.toString());
            } else {
                label.setText(value.toString());
            }
            
            return label;
        }
        
    }
    
}
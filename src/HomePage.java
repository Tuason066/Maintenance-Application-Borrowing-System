
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


public class HomePage extends javax.swing.JFrame {

    Helper helper = new Helper();
    
    public HomePage() {
        initComponents();
        setExtendedState(HomePage.this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        
        // Display Table Data
        Tables.getInstance().setInventoryTable(inventoryTable);
        helper.displayInventoryItems();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        requestbutton = new javax.swing.JButton();
        returnitems = new javax.swing.JButton();
        searchBorrowedBtn1 = new javax.swing.JButton();
        history = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inventoryTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        requestbutton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        requestbutton.setForeground(new java.awt.Color(255, 153, 102));
        requestbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Student.png"))); // NOI18N
        requestbutton.setText("Request Form");
        requestbutton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        requestbutton.setPreferredSize(new java.awt.Dimension(375, 231));
        requestbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestbuttonActionPerformed(evt);
            }
        });
        getContentPane().add(requestbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 410, 131));

        returnitems.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        returnitems.setForeground(new java.awt.Color(255, 51, 102));
        returnitems.setIcon(new javax.swing.ImageIcon(getClass().getResource("/return.png"))); // NOI18N
        returnitems.setText("Return Items");
        returnitems.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        returnitems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnitemsActionPerformed(evt);
            }
        });
        getContentPane().add(returnitems, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 410, 131));

        searchBorrowedBtn1.setBackground(new java.awt.Color(153, 255, 255));
        searchBorrowedBtn1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        searchBorrowedBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plus .png"))); // NOI18N
        searchBorrowedBtn1.setText("Add Item");
        searchBorrowedBtn1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        searchBorrowedBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBorrowedBtn1ActionPerformed(evt);
            }
        });
        getContentPane().add(searchBorrowedBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 190, 40));

        history.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        history.setForeground(new java.awt.Color(102, 0, 255));
        history.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Borrowed.png"))); // NOI18N
        history.setText("History/Archive");
        history.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        history.setPreferredSize(new java.awt.Dimension(375, 231));
        history.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyActionPerformed(evt);
            }
        });
        getContentPane().add(history, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, 410, 131));

        searchBtn.setBackground(new java.awt.Color(153, 255, 255));
        searchBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        getContentPane().add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 200, 140, 40));

        jButton4.setBackground(new java.awt.Color(255, 153, 153));
        jButton4.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logout.jpg"))); // NOI18N
        jButton4.setText("LogOut");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 700, 316, 90));

        searchField.setBackground(new java.awt.Color(255, 255, 204));
        getContentPane().add(searchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 200, 300, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 210, 190));

        inventoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Item ID", "Items", "Quantity", "Location", "Category"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        inventoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventoryTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(inventoryTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, 850, 410));

        jLabel3.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel3.setText("Maintenance Application Borrowing System");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 790, 60));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back.png"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(1222, 665));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1650, 870));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JOptionPane.showMessageDialog(null,"Logout Successfully");
        this.dispose();
        new LogIN().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void requestbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestbuttonActionPerformed
        new RequestFormCopy().setVisible(true);
    }//GEN-LAST:event_requestbuttonActionPerformed

    private void historyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyActionPerformed
        new Archive().setVisible(true);
    }//GEN-LAST:event_historyActionPerformed

    private void returnitemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnitemsActionPerformed
        new ReturnItem().setVisible(true);
    }//GEN-LAST:event_returnitemsActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String searchText = searchField.getText();
        Helper.searchItem(searchText);
    }//GEN-LAST:event_searchBtnActionPerformed

    private void inventoryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryTableMouseClicked
        //Category, Furniture, Electronic hardwares, Hand Tools
        
        // set row Id
        int index = inventoryTable.getSelectedRow();
        TableModel model = inventoryTable.getModel();
        int itemId = Integer.parseInt((String) model.getValueAt(index, 0));
        Tables.getInstance().setRowId(itemId);
        
        // open Frame option
        new InventoryTableOption().setVisible(true);
    }//GEN-LAST:event_inventoryTableMouseClicked

    private void searchBorrowedBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBorrowedBtn1ActionPerformed
        new AddNewItem().setVisible(true);
    }//GEN-LAST:event_searchBorrowedBtn1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton history;
    private javax.swing.JTable inventoryTable;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton requestbutton;
    private javax.swing.JButton returnitems;
    private javax.swing.JButton searchBorrowedBtn1;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}

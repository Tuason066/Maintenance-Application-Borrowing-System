
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class LogIN extends javax.swing.JFrame {

    private int loginAttempts = 0;
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private static final int LOCKOUT_DURATION_SECONDS = 30; // Lockout duration in seconds
    
    
    public LogIN() {
        initComponents();
        setExtendedState(LogIN.this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Username");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 500, 120, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Password");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 550, 100, -1));

        usernameField.setBackground(new java.awt.Color(255, 204, 102));
        usernameField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(usernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 500, 220, 30));

        passwordField.setBackground(new java.awt.Color(255, 204, 102));
        passwordField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 550, 220, 30));

        loginBtn.setBackground(new java.awt.Color(255, 255, 0));
        loginBtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        loginBtn.setText("Log in");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        getContentPane().add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 640, -1, -1));

        jButton2.setBackground(new java.awt.Color(255, 255, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 640, -1, -1));

        jCheckBox1.setBackground(new java.awt.Color(0, 102, 102));
        jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 0));
        jCheckBox1.setText("Show Password");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 590, -1, -1));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.setForeground(new java.awt.Color(255, 19, 104));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 350, 420, 450));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TCC.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-60, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
       
        String enteredUsername = usernameField.getText();
        String enteredPassword = passwordField.getText();
        
        if (enteredUsername.equals ("")){
            JOptionPane.showMessageDialog(null, "Please fill in Username");
        } 
        if (enteredPassword.equals("")){
            JOptionPane.showMessageDialog(null,"Please fill in password");
        }
        
        boolean isAuthenticated = authenticate(enteredUsername, enteredPassword);

        if (isAuthenticated) {
                    // Successful login
            JOptionPane.showMessageDialog(this, "Login Successful!");
            setVisible(false);
            new HomePage().setVisible(true);
            // Reset login attempts on successful login
            loginAttempts = 0;
        } else {
            // Failed login

            if (loginAttempts < 2) {
                JOptionPane.showMessageDialog(this, "Login Failed. Please try again.");
            } else {
                JOptionPane.showMessageDialog(this, "Multiple attempts in login. Please wait for 3minutes.");
            }
            
            // Increment login attempts
            loginAttempts++;

            // Check if the maximum login attempts are reached
            if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
                // Lock the account temporarily
                lockAccount();
            }
        }

    }//GEN-LAST:event_loginBtnActionPerformed

    private boolean authenticate(String username, String password) {
        return username.equals("MABS") && password.equals("violet");
    }
    
    private void lockAccount() {
        // Disable login components
        usernameField.setEnabled(false);
        passwordField.setEnabled(false);
        loginBtn.setEnabled(false);
        
        // Schedule a timer to re-enable the login components after the lockout duration
        // 1000 = 30seconds
        Timer unlockTimer = new Timer(LOCKOUT_DURATION_SECONDS * 6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Enable login components
                usernameField.setEnabled(true);
                passwordField.setEnabled(true);
                loginBtn.setEnabled(true);
                // Reset login attempts after lockout duration
                loginAttempts = 0;
                // Stop the timer
                ((Timer) e.getSource()).stop();
            }
        });
        unlockTimer.setRepeats(false); // Set to false for a one-time execution
        unlockTimer.start();
    }
    
    
    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()){
            passwordField.setEchoChar((char)0);
        }
        else{
            passwordField.setEchoChar('*');
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

 
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
            java.util.logging.Logger.getLogger(LogIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogIN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}

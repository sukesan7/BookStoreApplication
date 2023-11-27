/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bookstore.ui;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import bookstore.Authentication;
import bookstore.UserTypeEnum;
import bookstore.states.ActionsEnum;
/**
 *
 * @author porom
 */
public class LoginPage extends javax.swing.JFrame {
    Authentication auth;
    /**
     * Creates new form LoginPage
     */
    public LoginPage() {
        initComponents();
        this.setLocationRelativeTo(null);
        auth = new Authentication();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        passwordInput = new javax.swing.JTextField();
        usernameInput = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        loginBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BookStore App");
        setResizable(false);

        usernameInput.setDragEnabled(true);
        usernameInput.setName(""); // NOI18N

        jLabel1.setText("Username:");

        jLabel2.setText("Password:");

        jLabel3.setText("Welcome to the BookStore App");

        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loginBtn))))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loginBtn)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private int findCustomerPoints (String username){
        File customerDataFile = new File("customers.txt");
        try{
            BufferedReader reader = new BufferedReader(new FileReader(customerDataFile));
            String line = reader.readLine();
            while (line != null){
                String[] data = line.split(",");
                if(data.length != 3){
                    reader.close();
                    return -1;
                }
                if(data[0].equals(username)){
                    reader.close();
                    return Integer.parseInt(data[2]);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e){
            System.out.println("Problem reading File");
        }
        return -1;
    }
    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        UserTypeEnum loginType = auth.authenticateUser(username, password);
        
        switch(loginType){
            case ADMIN:
                BookStoreLauncher.processAdminInstantiation(username, password);
                notifyStateChange(ActionsEnum.ADMIN_LOGIN);
                break;
            case CUSTOMER:
                BookStoreLauncher.processCustomerInstation(username, password, findCustomerPoints(username));
                notifyStateChange(ActionsEnum.CUSTOMER_LOGIN);
                break;
            case UNAUTHENTICATED:
                System.out.println("Invalid Credentials.");
                break;
                
        }
    }//GEN-LAST:event_loginBtnActionPerformed
    public void notifyStateChange(ActionsEnum action){
        BookStoreLauncher.processEvent(action);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginBtn;
    private javax.swing.JTextField passwordInput;
    private javax.swing.JTextField usernameInput;
    // End of variables declaration//GEN-END:variables
}
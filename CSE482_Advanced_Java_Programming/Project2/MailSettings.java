package secondadvancedproject;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class MailSettings extends javax.swing.JDialog {

   private File f;

   public MailSettings(java.awt.Frame parent, boolean modal) {
      super(parent, modal);
      initComponents();

      if (MailProject.getSettingType()) {
         this.setTitle("Send Mail Settings");
      } else if (!MailProject.getSettingType()) {
         this.setTitle("Receive Mail Settings");
         portNumberArea.setEditable(false);
         portNumberArea.setBackground(new Color(240, 240, 240));
      }
      setLocationRelativeTo(this);
   }

   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {
      java.awt.GridBagConstraints gridBagConstraints;

      jPanel1 = new javax.swing.JPanel();
      jLabel1 = new javax.swing.JLabel();
      jLabel4 = new javax.swing.JLabel();
      jLabel5 = new javax.swing.JLabel();
      jLabel6 = new javax.swing.JLabel();
      hostNameArea = new javax.swing.JTextArea();
      portNumberArea = new javax.swing.JTextArea();
      userNameArea = new javax.swing.JTextArea();
      jLabel9 = new javax.swing.JLabel();
      jLabel2 = new javax.swing.JLabel();
      jLabel3 = new javax.swing.JLabel();
      jLabel10 = new javax.swing.JLabel();
      jLabel7 = new javax.swing.JLabel();
      passwordArea = new javax.swing.JPasswordField();
      jPanel2 = new javax.swing.JPanel();
      saveButton = new javax.swing.JButton();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      setMaximumSize(new java.awt.Dimension(400, 180));
      setMinimumSize(new java.awt.Dimension(400, 180));
      setPreferredSize(new java.awt.Dimension(400, 300));

      jPanel1.setLayout(new java.awt.GridBagLayout());

      jLabel1.setText("                                                  ");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.gridwidth = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(jLabel1, gridBagConstraints);

      jLabel4.setText("                                                  ");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.gridwidth = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(jLabel4, gridBagConstraints);

      jLabel5.setText("                                                  ");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 4;
      gridBagConstraints.gridwidth = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(jLabel5, gridBagConstraints);

      jLabel6.setText("                                                  ");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 6;
      gridBagConstraints.gridwidth = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(jLabel6, gridBagConstraints);

      hostNameArea.setColumns(20);
      hostNameArea.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
      hostNameArea.setRows(1);
      hostNameArea.setTabSize(0);
      hostNameArea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 3.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(hostNameArea, gridBagConstraints);

      portNumberArea.setColumns(20);
      portNumberArea.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
      portNumberArea.setRows(1);
      portNumberArea.setTabSize(0);
      portNumberArea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 3;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 3.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(portNumberArea, gridBagConstraints);

      userNameArea.setColumns(20);
      userNameArea.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
      userNameArea.setRows(1);
      userNameArea.setTabSize(0);
      userNameArea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 5;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 3.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(userNameArea, gridBagConstraints);

      jLabel9.setText("Host Name:");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(jLabel9, gridBagConstraints);

      jLabel2.setText("Port Number:");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 3;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(jLabel2, gridBagConstraints);

      jLabel3.setText("Username:");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 5;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(jLabel3, gridBagConstraints);

      jLabel10.setText("Password:");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 7;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(jLabel10, gridBagConstraints);

      jLabel7.setText("                                                  ");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 8;
      gridBagConstraints.gridwidth = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(jLabel7, gridBagConstraints);
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 7;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 3.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(passwordArea, gridBagConstraints);

      getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

      jPanel2.setLayout(new java.awt.GridLayout(1, 0));

      saveButton.setText("Save");
      saveButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            saveButtonActionPerformed(evt);
         }
      });
      jPanel2.add(saveButton);

      getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
      if (!MailProject.getSettingType() && (hostNameArea.getText().isEmpty() || userNameArea.getText().isEmpty() || passwordArea.getText().isEmpty())) {
         JOptionPane.showMessageDialog(null, "Please, check your entries you forgot to fill one of them.");
      } else if (MailProject.getSettingType() && (hostNameArea.getText().isEmpty() || userNameArea.getText().isEmpty() || passwordArea.getText().isEmpty() || portNumberArea.getText().isEmpty())) {
         JOptionPane.showMessageDialog(null, "Please, check your entries you forgot to fill one of them.");
      } else if (!userNameArea.getText().contains("@")) {
         JOptionPane.showMessageDialog(null, "Please, check your username it must have @ symbol.");
      } else {
         try {
            if (MailProject.getSettingType()) {
               f = new File("smtp.properties");
            } else if (!MailProject.getSettingType()) {
               f = new File("imaps.properties");
            } else {
               System.out.println("no other option");
            }

            PrintWriter pt = new PrintWriter(f);

            if (MailProject.getSettingType()) {
               pt.append("mail.smtp.starttls.enable=" + true + "\n");
               pt.append("mail.smtp.port=" + portNumberArea.getText() + "\n");
               pt.append("mail.password=" + passwordArea.getText() + "\n");
               pt.append("mail.user=" + userNameArea.getText() + "\n");
               pt.append("mail.smtp.auth=" + true + "\n");
               pt.append("mail.smtp.host=" + "smtp.gmail.com" + "\n");
            } else if (!MailProject.getSettingType()) {
               pt.append("mail.password=" + passwordArea.getText() + "\n");
               pt.append("mail.user=" + userNameArea.getText() + "\n");
               pt.append("mail.smtp.auth=" + true + "\n");
               pt.append("mail.smtp.host=" + "imap.gmail.com" + "\n");
               pt.append("mail.store.protocol=" + "imaps" + "\n");
            } else {
               System.out.println("no other option");
            }

            pt.close();
         } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException: " + ex.getMessage());
         }
      }
      this.dispose();
   }//GEN-LAST:event_saveButtonActionPerformed

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JTextArea hostNameArea;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel10;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JLabel jLabel4;
   private javax.swing.JLabel jLabel5;
   private javax.swing.JLabel jLabel6;
   private javax.swing.JLabel jLabel7;
   private javax.swing.JLabel jLabel9;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JPasswordField passwordArea;
   private javax.swing.JTextArea portNumberArea;
   private javax.swing.JButton saveButton;
   private javax.swing.JTextArea userNameArea;
   // End of variables declaration//GEN-END:variables
}

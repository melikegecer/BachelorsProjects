package secondadvancedproject;

public class ViewMail extends javax.swing.JDialog {

   public ViewMail(java.awt.Frame parent, boolean modal, String info) {
      super(parent, modal);
      initComponents();

      mailContent.setText(info);
      setLocationRelativeTo(null);
   }

   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jPanel1 = new javax.swing.JPanel();
      closeButton = new javax.swing.JButton();
      jScrollPane1 = new javax.swing.JScrollPane();
      mailContent = new javax.swing.JTextArea();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      setPreferredSize(new java.awt.Dimension(300, 300));

      jPanel1.setLayout(new java.awt.BorderLayout());

      closeButton.setText("Close");
      closeButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            closeButtonActionPerformed(evt);
         }
      });
      jPanel1.add(closeButton, java.awt.BorderLayout.PAGE_END);

      mailContent.setEditable(false);
      mailContent.setBackground(new java.awt.Color(240, 240, 240));
      mailContent.setColumns(20);
      mailContent.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
      mailContent.setRows(5);
      jScrollPane1.setViewportView(mailContent);

      jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

      getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
      this.dispose();
   }//GEN-LAST:event_closeButtonActionPerformed

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton closeButton;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JTextArea mailContent;
   // End of variables declaration//GEN-END:variables
}

package shoppingstore;

import java.awt.Frame;
import java.util.ArrayList;

public class Screen1 extends javax.swing.JFrame {

   private static ArrayList<Frame> fList = new ArrayList<>();

   public static ArrayList<Frame> getfList() {
      return fList;
   }

   public Screen1() {
      initComponents();
      setLocationRelativeTo(this);
      fList.add(new Screen2());

      nextButton.addActionListener(new MyActionListener());
      triggerButton.addActionListener(new MyActionListener());
   }

   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {
      java.awt.GridBagConstraints gridBagConstraints;

      jPanel1 = new javax.swing.JPanel();
      jPanel2 = new javax.swing.JPanel();
      jPanel11 = new javax.swing.JPanel();
      jLabel12 = new javax.swing.JLabel();
      jLabel13 = new javax.swing.JLabel();
      jLabel14 = new javax.swing.JLabel();
      jLabel15 = new javax.swing.JLabel();
      jLabel16 = new javax.swing.JLabel();
      jPanel12 = new javax.swing.JPanel();
      jPanel6 = new javax.swing.JPanel();
      jTextField2 = new javax.swing.JTextField();
      jLabel8 = new javax.swing.JLabel();
      jTextField7 = new javax.swing.JTextField();
      jPanel7 = new javax.swing.JPanel();
      jTextField3 = new javax.swing.JTextField();
      jLabel9 = new javax.swing.JLabel();
      jTextField8 = new javax.swing.JTextField();
      jLabel10 = new javax.swing.JLabel();
      jTextField9 = new javax.swing.JTextField();
      jPanel8 = new javax.swing.JPanel();
      jTextField4 = new javax.swing.JTextField();
      jPanel9 = new javax.swing.JPanel();
      jTextField5 = new javax.swing.JTextField();
      jLabel3 = new javax.swing.JLabel();
      jPanel10 = new javax.swing.JPanel();
      jTextField6 = new javax.swing.JTextField();
      jLabel11 = new javax.swing.JLabel();
      jTextField10 = new javax.swing.JTextField();
      jPanel3 = new javax.swing.JPanel();
      jLabel4 = new javax.swing.JLabel();
      jTextField11 = new javax.swing.JTextField();
      jLabel5 = new javax.swing.JLabel();
      jSpinner1 = new javax.swing.JSpinner();
      jLabel6 = new javax.swing.JLabel();
      jTextField12 = new javax.swing.JTextField();
      jLabel7 = new javax.swing.JLabel();
      jTextField13 = new javax.swing.JTextField();
      jPanel4 = new javax.swing.JPanel();
      jLabel1 = new javax.swing.JLabel();
      jTextField1 = new javax.swing.JTextField();
      triggerButton = new javax.swing.JButton();
      nextButton = new javax.swing.JButton();
      jLabel17 = new javax.swing.JLabel();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setTitle("Cheap Shop Catalog Store");
      setResizable(false);

      jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

      jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Purchaser"));
      jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

      jPanel11.setLayout(new java.awt.GridLayout(5, 1));

      jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
      jLabel12.setText("Name");
      jPanel11.add(jLabel12);

      jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
      jLabel13.setText("Postal Code");
      jPanel11.add(jLabel13);

      jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
      jLabel14.setText("Delivery Address");
      jPanel11.add(jLabel14);

      jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
      jLabel15.setText("Today's Date");
      jPanel11.add(jLabel15);

      jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
      jLabel16.setText("Credit Card No");
      jPanel11.add(jLabel16);

      jPanel2.add(jPanel11);

      jPanel12.setLayout(new java.awt.GridLayout(5, 1, 0, 7));

      jPanel6.setLayout(new java.awt.GridBagLayout());
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.ipadx = 105;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
      jPanel6.add(jTextField2, gridBagConstraints);

      jLabel8.setText("            Phone");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
      jPanel6.add(jLabel8, gridBagConstraints);
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.ipadx = 105;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
      jPanel6.add(jTextField7, gridBagConstraints);

      jPanel12.add(jPanel6);

      jPanel7.setLayout(new java.awt.GridBagLayout());
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 4;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.ipadx = 75;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
      jPanel7.add(jTextField3, gridBagConstraints);

      jLabel9.setText("   Zone");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
      jPanel7.add(jLabel9, gridBagConstraints);
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.ipadx = 75;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
      jPanel7.add(jTextField8, gridBagConstraints);

      jLabel10.setText("   City");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
      jPanel7.add(jLabel10, gridBagConstraints);
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.ipadx = 60;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
      jPanel7.add(jTextField9, gridBagConstraints);

      jPanel12.add(jPanel7);

      jPanel8.setLayout(new java.awt.GridBagLayout());
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.ipadx = 283;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
      jPanel8.add(jTextField4, gridBagConstraints);

      jPanel12.add(jPanel8);

      jPanel9.setLayout(new java.awt.GridBagLayout());
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.ipadx = 75;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
      jPanel9.add(jTextField5, gridBagConstraints);

      jLabel3.setText("                                                                     ");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 0;
      jPanel9.add(jLabel3, gridBagConstraints);

      jPanel12.add(jPanel9);

      jPanel10.setLayout(new java.awt.GridBagLayout());
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.ipadx = 75;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
      jPanel10.add(jTextField6, gridBagConstraints);

      jLabel11.setText("                                     Validation Id");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 0;
      jPanel10.add(jLabel11, gridBagConstraints);
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.ipadx = 30;
      jPanel10.add(jTextField10, gridBagConstraints);

      jPanel12.add(jPanel10);

      jPanel2.add(jPanel12);

      jPanel1.add(jPanel2);

      jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Catalog Item"));
      jPanel3.setLayout(new java.awt.GridBagLayout());

      jLabel4.setText("Number");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel3.add(jLabel4, gridBagConstraints);
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.ipadx = 50;
      jPanel3.add(jTextField11, gridBagConstraints);

      jLabel5.setText("   Quantity");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel3.add(jLabel5, gridBagConstraints);
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel3.add(jSpinner1, gridBagConstraints);

      jLabel6.setText("   Cost Item");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel3.add(jLabel6, gridBagConstraints);
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.ipadx = 50;
      jPanel3.add(jTextField12, gridBagConstraints);

      jLabel7.setText("   Total");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel3.add(jLabel7, gridBagConstraints);
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.ipadx = 50;
      jPanel3.add(jTextField13, gridBagConstraints);

      jPanel1.add(jPanel3);

      jPanel4.setLayout(new java.awt.GridBagLayout());

      jLabel1.setText("  Balance Owing");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel4.add(jLabel1, gridBagConstraints);
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.ipadx = 75;
      jPanel4.add(jTextField1, gridBagConstraints);

      triggerButton.setText("Trigger Invoice (PF8)");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel4.add(triggerButton, gridBagConstraints);

      nextButton.setText("Next Catalog Item (PF5)");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 3;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      jPanel4.add(nextButton, gridBagConstraints);

      jLabel17.setText("                        ");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 2;
      gridBagConstraints.gridy = 0;
      jPanel4.add(jLabel17, gridBagConstraints);

      jPanel1.add(jPanel4);

      getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

      pack();
   }// </editor-fold>//GEN-END:initComponents

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
            if ("Windows".equals(info.getName())) {
               javax.swing.UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (ClassNotFoundException ex) {
         java.util.logging.Logger.getLogger(Screen1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(Screen1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(Screen1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(Screen1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>

      /* Create and display the form */
      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            new Screen1().setVisible(true);
         }
      });
   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel10;
   private javax.swing.JLabel jLabel11;
   private javax.swing.JLabel jLabel12;
   private javax.swing.JLabel jLabel13;
   private javax.swing.JLabel jLabel14;
   private javax.swing.JLabel jLabel15;
   private javax.swing.JLabel jLabel16;
   private javax.swing.JLabel jLabel17;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JLabel jLabel4;
   private javax.swing.JLabel jLabel5;
   private javax.swing.JLabel jLabel6;
   private javax.swing.JLabel jLabel7;
   private javax.swing.JLabel jLabel8;
   private javax.swing.JLabel jLabel9;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel10;
   private javax.swing.JPanel jPanel11;
   private javax.swing.JPanel jPanel12;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JPanel jPanel3;
   private javax.swing.JPanel jPanel4;
   private javax.swing.JPanel jPanel6;
   private javax.swing.JPanel jPanel7;
   private javax.swing.JPanel jPanel8;
   private javax.swing.JPanel jPanel9;
   private javax.swing.JSpinner jSpinner1;
   private javax.swing.JTextField jTextField1;
   private javax.swing.JTextField jTextField10;
   private javax.swing.JTextField jTextField11;
   private javax.swing.JTextField jTextField12;
   private javax.swing.JTextField jTextField13;
   private javax.swing.JTextField jTextField2;
   private javax.swing.JTextField jTextField3;
   private javax.swing.JTextField jTextField4;
   private javax.swing.JTextField jTextField5;
   private javax.swing.JTextField jTextField6;
   private javax.swing.JTextField jTextField7;
   private javax.swing.JTextField jTextField8;
   private javax.swing.JTextField jTextField9;
   private javax.swing.JButton nextButton;
   private javax.swing.JButton triggerButton;
   // End of variables declaration//GEN-END:variables
}

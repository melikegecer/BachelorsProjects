package fifthadvancedproject;

public class Calculator extends javax.swing.JFrame {

   private String entry = "";
   private double entry1 = 0;
   private double entry2 = 0;
   private int operation = 0;

   public Calculator() {
      initComponents();
      setLocationRelativeTo(this);  
   }

   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jPanel1 = new javax.swing.JPanel();
      jPanel2 = new javax.swing.JPanel();
      jLabel1 = new javax.swing.JLabel();
      jPanel3 = new javax.swing.JPanel();
      jPanel4 = new javax.swing.JPanel();
      sevenButton = new javax.swing.JButton();
      fourButton = new javax.swing.JButton();
      oneButton = new javax.swing.JButton();
      zeroButton = new javax.swing.JButton();
      jPanel5 = new javax.swing.JPanel();
      eightButton = new javax.swing.JButton();
      fiveButton = new javax.swing.JButton();
      twoButton = new javax.swing.JButton();
      dotButton = new javax.swing.JButton();
      jPanel6 = new javax.swing.JPanel();
      nineButton = new javax.swing.JButton();
      sixButton = new javax.swing.JButton();
      threeButton = new javax.swing.JButton();
      equalButton = new javax.swing.JButton();
      jPanel7 = new javax.swing.JPanel();
      multiButton = new javax.swing.JButton();
      divideButton = new javax.swing.JButton();
      plusButton = new javax.swing.JButton();
      minusButton = new javax.swing.JButton();
      jPanel8 = new javax.swing.JPanel();
      clearButton = new javax.swing.JButton();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setAutoRequestFocus(false);
      setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
      setMinimumSize(new java.awt.Dimension(400, 300));
      setResizable(false);

      jPanel1.setLayout(new java.awt.BorderLayout());

      jPanel2.setMaximumSize(new java.awt.Dimension(400, 40));
      jPanel2.setMinimumSize(new java.awt.Dimension(400, 40));
      jPanel2.setPreferredSize(new java.awt.Dimension(400, 40));
      jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

      jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
      jLabel1.setText("0");
      jPanel2.add(jLabel1);

      jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

      jPanel3.setLayout(new java.awt.GridLayout(1, 5));

      jPanel4.setLayout(new java.awt.GridLayout(4, 1));

      sevenButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      sevenButton.setText("7");
      sevenButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            sevenButtonActionPerformed(evt);
         }
      });
      jPanel4.add(sevenButton);

      fourButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      fourButton.setText("4");
      fourButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            fourButtonActionPerformed(evt);
         }
      });
      jPanel4.add(fourButton);

      oneButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      oneButton.setText("1");
      oneButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            oneButtonActionPerformed(evt);
         }
      });
      jPanel4.add(oneButton);

      zeroButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      zeroButton.setText("0");
      zeroButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            zeroButtonActionPerformed(evt);
         }
      });
      jPanel4.add(zeroButton);

      jPanel3.add(jPanel4);

      jPanel5.setLayout(new java.awt.GridLayout(4, 1));

      eightButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      eightButton.setText("8");
      eightButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            eightButtonActionPerformed(evt);
         }
      });
      jPanel5.add(eightButton);

      fiveButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      fiveButton.setText("5");
      fiveButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            fiveButtonActionPerformed(evt);
         }
      });
      jPanel5.add(fiveButton);

      twoButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      twoButton.setText("2");
      twoButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            twoButtonActionPerformed(evt);
         }
      });
      jPanel5.add(twoButton);

      dotButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      dotButton.setText(".");
      dotButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            dotButtonActionPerformed(evt);
         }
      });
      jPanel5.add(dotButton);

      jPanel3.add(jPanel5);

      jPanel6.setLayout(new java.awt.GridLayout(4, 1));

      nineButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      nineButton.setText("9");
      nineButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            nineButtonActionPerformed(evt);
         }
      });
      jPanel6.add(nineButton);

      sixButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      sixButton.setText("6");
      sixButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            sixButtonActionPerformed(evt);
         }
      });
      jPanel6.add(sixButton);

      threeButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      threeButton.setText("3");
      threeButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            threeButtonActionPerformed(evt);
         }
      });
      jPanel6.add(threeButton);

      equalButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      equalButton.setText("=");
      equalButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            equalButtonActionPerformed(evt);
         }
      });
      jPanel6.add(equalButton);

      jPanel3.add(jPanel6);

      jPanel7.setLayout(new java.awt.GridLayout(4, 1));

      multiButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      multiButton.setText("x");
      multiButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            multiButtonActionPerformed(evt);
         }
      });
      jPanel7.add(multiButton);

      divideButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      divideButton.setText("/");
      divideButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            divideButtonActionPerformed(evt);
         }
      });
      jPanel7.add(divideButton);

      plusButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      plusButton.setText("+");
      plusButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            plusButtonActionPerformed(evt);
         }
      });
      jPanel7.add(plusButton);

      minusButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      minusButton.setText("-");
      minusButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            minusButtonActionPerformed(evt);
         }
      });
      jPanel7.add(minusButton);

      jPanel3.add(jPanel7);

      jPanel8.setLayout(new java.awt.GridLayout(1, 0));

      clearButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
      clearButton.setText("C");
      clearButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            clearButtonActionPerformed(evt);
         }
      });
      jPanel8.add(clearButton);

      jPanel3.add(jPanel8);

      jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

      getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void oneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneButtonActionPerformed
      entry += 1;
      jLabel1.setText(entry);
   }//GEN-LAST:event_oneButtonActionPerformed

   private void twoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twoButtonActionPerformed
      entry += 2;
      jLabel1.setText(entry);
   }//GEN-LAST:event_twoButtonActionPerformed

   private void threeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_threeButtonActionPerformed
      entry += 3;
      jLabel1.setText(entry);
   }//GEN-LAST:event_threeButtonActionPerformed

   private void fourButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fourButtonActionPerformed
      entry += 4;
      jLabel1.setText(entry);
   }//GEN-LAST:event_fourButtonActionPerformed

   private void fiveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiveButtonActionPerformed
      entry += 5;
      jLabel1.setText(entry);
   }//GEN-LAST:event_fiveButtonActionPerformed

   private void sixButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sixButtonActionPerformed
      entry += 6;
      jLabel1.setText(entry);
   }//GEN-LAST:event_sixButtonActionPerformed

   private void sevenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sevenButtonActionPerformed
      entry += 7;
      jLabel1.setText(entry);
   }//GEN-LAST:event_sevenButtonActionPerformed

   private void eightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eightButtonActionPerformed
      entry += 8;
      jLabel1.setText(entry);
   }//GEN-LAST:event_eightButtonActionPerformed

   private void nineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nineButtonActionPerformed
      entry += 9;
      jLabel1.setText(entry);
   }//GEN-LAST:event_nineButtonActionPerformed

   private void zeroButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zeroButtonActionPerformed
      if (!jLabel1.getText().equals("0")) {
         entry += 0;
         jLabel1.setText(entry);
      }
   }//GEN-LAST:event_zeroButtonActionPerformed

   private void dotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dotButtonActionPerformed
      if (entry.isEmpty()) {
         entry = "0.";
         jLabel1.setText(entry);
      }
      if (!jLabel1.getText().contains(".")) {
         entry += ".";
         jLabel1.setText(entry);
      }
   }//GEN-LAST:event_dotButtonActionPerformed

   private void equalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equalButtonActionPerformed
      if (operation != 0) {
         if (!entry.isEmpty()) {
            solve();
         }
         operation = 0;
         entry = "";
      }
   }//GEN-LAST:event_equalButtonActionPerformed

   private void minusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusButtonActionPerformed
      if (!jLabel1.getText().equals("0")) {
         if (!entry.isEmpty()) {
            solve();
         }
         operation = 2;
         entry = "";
      }
   }//GEN-LAST:event_minusButtonActionPerformed

   private void plusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusButtonActionPerformed
      if (!jLabel1.getText().equals("0")) {
         if (!entry.isEmpty()) {
            solve();
         }
         operation = 1;
         entry = "";
      }
   }//GEN-LAST:event_plusButtonActionPerformed

   private void divideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divideButtonActionPerformed
      if (!jLabel1.getText().equals("0")) {
         if (!entry.isEmpty()) {
            solve();
         }
         operation = 4;
         entry = "";
      }
   }//GEN-LAST:event_divideButtonActionPerformed

   private void multiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiButtonActionPerformed
      if (!jLabel1.getText().equals("0")) {
         if (!entry.isEmpty()) {
            solve();
         }
         operation = 3;
         entry = "";
      }
   }//GEN-LAST:event_multiButtonActionPerformed

   private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
      jLabel1.setText("0");
      entry = "";
      entry1 = 0;
      entry2 = 0;
      operation = 0;
   }//GEN-LAST:event_clearButtonActionPerformed

   private void solve() {
      //second number entered
      if (!jLabel1.getText().equals("0")) {
         entry2 = Double.valueOf(jLabel1.getText());
      } else {
         entry2 = 0;
      }

      //solve for an operation
      if (operation == 0) {
         entry1 = entry2;
      } else if (operation == 1) {
         entry1 = add(entry1, entry2);
      } else if (operation == 2) {
         entry1 = subtract(entry1, entry2);
      } else if (operation == 3) {
         entry1 = multiply(entry1, entry2);
      } else if (operation == 4) {
         entry1 = divide(entry1, entry2);
      } else {
         System.out.println("no other option.");
      }

      entry = String.valueOf(entry1);

      //if an int remove .0
      if (String.valueOf(entry1).endsWith(".0")) {
         jLabel1.setText(String.valueOf(entry1).substring(0, String.valueOf(entry1).length() - 2));
      } else {
         jLabel1.setText(String.valueOf(entry1));
      }
      operation = 0;
   }

   public double add(double x, double y) {
      return x + y;
   }

   public double subtract(double x, double y) {
      return x - y;
   }

   public double multiply(double x, double y) {
      return x * y;
   }

   public double divide(double x, double y) {
      if (y == 0) {
         throw new ArithmeticException("you can not divide a number with zero (0).");
      } else {
         return x / y;
      }
   }

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
         java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>
      //</editor-fold>

      /* Create and display the form */
      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            new Calculator().setVisible(true);
         }
      });
   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton clearButton;
   private javax.swing.JButton divideButton;
   private javax.swing.JButton dotButton;
   private javax.swing.JButton eightButton;
   private javax.swing.JButton equalButton;
   private javax.swing.JButton fiveButton;
   private javax.swing.JButton fourButton;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JPanel jPanel3;
   private javax.swing.JPanel jPanel4;
   private javax.swing.JPanel jPanel5;
   private javax.swing.JPanel jPanel6;
   private javax.swing.JPanel jPanel7;
   private javax.swing.JPanel jPanel8;
   private javax.swing.JButton minusButton;
   private javax.swing.JButton multiButton;
   private javax.swing.JButton nineButton;
   private javax.swing.JButton oneButton;
   private javax.swing.JButton plusButton;
   private javax.swing.JButton sevenButton;
   private javax.swing.JButton sixButton;
   private javax.swing.JButton threeButton;
   private javax.swing.JButton twoButton;
   private javax.swing.JButton zeroButton;
   // End of variables declaration//GEN-END:variables
}

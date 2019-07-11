package thirdadvancedproject;

import com.sun.rowset.JdbcRowSetImpl;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import javax.sql.RowSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class DatabaseFrame extends javax.swing.JFrame {

   private DefaultComboBoxModel driverModel;
   private DefaultComboBoxModel urlModel;
   private DefaultComboBoxModel tableNameModel;
   private Connection connection;
   private Statement statement;
   private ResultSet resultSet;
   private RowSet rowSet = null;
   private TableModelWithRowSet tmwrs;

   public DatabaseFrame() {
      initComponents();
      
       ArrayList<Driver> drivers = Collections.list(DriverManager.getDrivers());
      for (Object driver1 : drivers) {
         Driver driver = (Driver) driver1;
         System.out.println(driver.getClass().getName());
      }

      rowSet = new JdbcRowSetImpl();
      tmwrs = new TableModelWithRowSet();

      driverModel = new DefaultComboBoxModel();
      urlModel = new DefaultComboBoxModel();

      driverCombo.setModel(driverModel);
      urlCombo.setModel(urlModel);

//    try to add them dynamic
      driverModel.addElement("com.mysql.jdbc.Driver");
      urlModel.addElement("jdbc:mysql://localhost/courseregistration");

      connectButton.addActionListener(new ConnectButtonListener());

//      cursor
      firstButton.addActionListener(new MoveCursorListener());
      lastButton.addActionListener(new MoveCursorListener());
      nextButton.addActionListener(new MoveCursorListener());
      previousButton.addActionListener(new MoveCursorListener());

//      changes
      insertButton.addActionListener(new InsertUpdateListener());
      updateButton.addActionListener(new InsertUpdateListener());
      deleteButton.addActionListener(new DeleteButtonListener());

      tableNameCombo.addActionListener(new TableNameComboListener());
   }

   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {
      java.awt.GridBagConstraints gridBagConstraints;

      jPanel1 = new javax.swing.JPanel();
      jSplitPane1 = new javax.swing.JSplitPane();
      jPanel2 = new javax.swing.JPanel();
      jLabel1 = new javax.swing.JLabel();
      jLabel2 = new javax.swing.JLabel();
      jLabel3 = new javax.swing.JLabel();
      jLabel4 = new javax.swing.JLabel();
      jLabel5 = new javax.swing.JLabel();
      driverCombo = new javax.swing.JComboBox();
      urlCombo = new javax.swing.JComboBox();
      userNameArea = new javax.swing.JTextArea();
      tableNameCombo = new javax.swing.JComboBox();
      connectButton = new javax.swing.JButton();
      passwordArea = new javax.swing.JPasswordField();
      jPanel3 = new javax.swing.JPanel();
      jSplitPane2 = new javax.swing.JSplitPane();
      jPanel4 = new javax.swing.JPanel();
      firstButton = new javax.swing.JButton();
      lastButton = new javax.swing.JButton();
      nextButton = new javax.swing.JButton();
      previousButton = new javax.swing.JButton();
      insertButton = new javax.swing.JButton();
      updateButton = new javax.swing.JButton();
      deleteButton = new javax.swing.JButton();
      jPanel6 = new javax.swing.JPanel();
      jScrollPane1 = new javax.swing.JScrollPane();
      tableToShow = new javax.swing.JTable();
      insertUpdatePanel = new javax.swing.JPanel();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setTitle("DB");

      jPanel1.setLayout(new java.awt.BorderLayout());

      jPanel2.setLayout(new java.awt.GridBagLayout());

      jLabel1.setText("JDBC Driver");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel2.add(jLabel1, gridBagConstraints);

      jLabel2.setText("Database URL");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel2.add(jLabel2, gridBagConstraints);

      jLabel3.setText("Username");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel2.add(jLabel3, gridBagConstraints);

      jLabel4.setText("Password");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 3;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel2.add(jLabel4, gridBagConstraints);

      jLabel5.setText("Table Name");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 4;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel2.add(jLabel5, gridBagConstraints);

      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel2.add(driverCombo, gridBagConstraints);

      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel2.add(urlCombo, gridBagConstraints);

      userNameArea.setColumns(20);
      userNameArea.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
      userNameArea.setRows(1);
      userNameArea.setTabSize(0);
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel2.add(userNameArea, gridBagConstraints);

      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 4;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel2.add(tableNameCombo, gridBagConstraints);

      connectButton.setText("Connect to DB");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 5;
      gridBagConstraints.gridwidth = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel2.add(connectButton, gridBagConstraints);
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 3;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel2.add(passwordArea, gridBagConstraints);

      jSplitPane1.setLeftComponent(jPanel2);

      jPanel3.setLayout(new java.awt.BorderLayout());

      jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

      jPanel4.setLayout(new java.awt.GridBagLayout());

      firstButton.setText("First");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      jPanel4.add(firstButton, gridBagConstraints);

      lastButton.setText("Last");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      jPanel4.add(lastButton, gridBagConstraints);

      nextButton.setText("Next");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      jPanel4.add(nextButton, gridBagConstraints);

      previousButton.setText("Previous");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      jPanel4.add(previousButton, gridBagConstraints);

      insertButton.setText("Insert");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      jPanel4.add(insertButton, gridBagConstraints);

      updateButton.setText("Update");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      jPanel4.add(updateButton, gridBagConstraints);

      deleteButton.setText("Delete");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      jPanel4.add(deleteButton, gridBagConstraints);

      jPanel6.setLayout(new java.awt.BorderLayout());

      tableToShow.setModel(new javax.swing.table.DefaultTableModel(
         new Object [][] {

         },
         new String [] {

         }
      ));
      jScrollPane1.setViewportView(tableToShow);

      jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.gridwidth = 7;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel4.add(jPanel6, gridBagConstraints);

      jSplitPane2.setTopComponent(jPanel4);

      insertUpdatePanel.setLayout(new java.awt.GridLayout(1, 0));
      jSplitPane2.setRightComponent(insertUpdatePanel);

      jPanel3.add(jSplitPane2, java.awt.BorderLayout.CENTER);

      jSplitPane1.setRightComponent(jPanel3);

      jPanel1.add(jSplitPane1, java.awt.BorderLayout.CENTER);

      getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

      pack();
   }// </editor-fold>//GEN-END:initComponents

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
         java.util.logging.Logger.getLogger(DatabaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(DatabaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(DatabaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(DatabaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>

      /* Create and display the form */
      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            new DatabaseFrame().setVisible(true);
         }
      });
   }

   private void setTable(String tableName) {
      try {
         rowSet.setCommand("select * from " + tableName);
         rowSet.execute();
         tmwrs.setRowSet(rowSet);
         tableToShow.setModel(tmwrs);
      } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
      }
   }

   private class TableNameComboListener implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {
         setTable(tableNameModel.getSelectedItem().toString());
      }
   }

   private class MoveCursorListener implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {
         if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            try {
               int x = tableToShow.getSelectedRow();
               if (button.getText().toLowerCase().contains("first")) {
                  System.out.println("FIRST");
                  rowSet.first();
                  System.out.println(rowSet.getString(1));
                  tableToShow.setRowSelectionInterval(0, 0);
               } else if (button.getText().toLowerCase().contains("last")) {
                  System.out.println("LAST");
                  rowSet.last();
                  System.out.println(rowSet.getString(1));
                  int r = rowSet.getRow() - 1;
                  tableToShow.setRowSelectionInterval(r, r);
               } else if (button.getText().toLowerCase().contains("next")) {
                  System.out.println("NEXT");
//                  int x = tableToShow.getSelectedRow() + 1;
                  rowSet.absolute(x + 1);
                  rowSet.next();
                  System.out.println(rowSet.getString(1));
                  tableToShow.setRowSelectionInterval(x + 1, x + 1);
               } else if (button.getText().toLowerCase().contains("prev")) {
                  System.out.println("PREVIOUS");
//                  int x = tableToShow.getSelectedRow() + 1;
                  rowSet.absolute(x + 1);
                  rowSet.previous();
                  System.out.println(rowSet.getString(1));
                  tableToShow.setRowSelectionInterval(x - 1, x - 1);
               }
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, ex.getMessage());
            }
         }
      }
   }

   private class InsertUpdateListener implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {
         try {
            ArrayList<JLabel> labels = new ArrayList<>();
            ArrayList<JTextArea> textAreas = new ArrayList<>();

            //start refreshed
            insertUpdatePanel.removeAll();

            int c = rowSet.getMetaData().getColumnCount();
            insertUpdatePanel.setLayout(new GridLayout(c + 1, 2));

            for (int i = 1; i <= c; i++) {
               labels.add(new JLabel(rowSet.getMetaData().getColumnLabel(i)));
               textAreas.add(new JTextArea());
            }

            for (int i = 0; i < c; i++) {
               insertUpdatePanel.add(labels.get(i));
               insertUpdatePanel.add(textAreas.get(i));
            }
            JButton iuButton = new JButton();
            iuButton.addActionListener(new IUListener());
            insertUpdatePanel.add(iuButton);
            int x = 0;
            if (e.getSource() instanceof JButton) {
               JButton button = (JButton) e.getSource();
               iuButton.setText(button.getText());
               if (button.getText().toLowerCase().contains("update")) {
                  x = tableToShow.getSelectedRow();
                  rowSet.absolute(x + 1);
                  for (int i = 1; i <= c; i++) {
                     textAreas.get(i - 1).setText(rowSet.getString(i) + " ");
                  }
               } else if (button.getText().toLowerCase().contains("nsert")) {
                  rowSet.moveToInsertRow();
               }
            }
            tmwrs.setRowSet(rowSet);
            tableToShow.setRowSelectionInterval(x, x);
         } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
         }
      }
   }

   private class IUListener implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {
         if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            Component[] cs = insertUpdatePanel.getComponents();
            if (button.getText().toLowerCase().contains("update")) {
               try {
                  int x = tableToShow.getSelectedRow();
                  rowSet.absolute(x + 1);
                  for (int i = 0; i < cs.length - 1; i = i + 2) {
                     rowSet.updateString(((JLabel) cs[i]).getText(), ((JTextArea) cs[i + 1]).getText());
                  }
                  rowSet.updateRow();
                  tableToShow.setRowSelectionInterval(x, x);
               } catch (SQLException ex) {
                  JOptionPane.showMessageDialog(null, ex.getMessage());
               }
            } else if (button.getText().toLowerCase().contains("nsert")) {
               try {
                  rowSet.last();
                  rowSet.moveToInsertRow();
                  for (int i = 0; i < cs.length - 1; i = i + 2) {
                     rowSet.updateString(((JLabel) cs[i]).getText(), ((JTextArea) cs[i + 1]).getText());
                  }
                  rowSet.insertRow();
               } catch (SQLException ex) {
                  JOptionPane.showMessageDialog(null, ex.getMessage());
               }
            }
         }

      }
   }

   private class DeleteButtonListener implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {
         try {
            int x = tableToShow.getSelectedRow() + 1;
            rowSet.absolute(x);
            rowSet.deleteRow();
            tmwrs.setRowSet(rowSet);
            tableToShow.setRowSelectionInterval(0, 0);
         } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
         }
      }
   }

   private class ConnectButtonListener implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {
         String driverName = driverCombo.getSelectedItem().toString();
         if (userNameArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username Area is empty.");
         } else {
            try {
               Class.forName(driverName);
            } catch (ClassNotFoundException ex) {
               System.out.println("ClassNotFoundException: " + ex.getMessage());
            }

            String userName = userNameArea.getText();
            String password = passwordArea.getText();
            String url = urlModel.getSelectedItem().toString();
            try {
               connection = DriverManager.getConnection(url, userName, password);

//          adds same table names, did it stop?
//          tableNameModel.removeAllElements();
               tableNameModel = new DefaultComboBoxModel();
               tableNameCombo.setModel(tableNameModel);

               rowSet.setUrl(url);
               rowSet.setUsername(userName);
               rowSet.setPassword(password);

               String[] dbname = url.split("/");
               String databaseName = dbname[dbname.length - 1];

               String[] types = {"TABLE"};
               resultSet = connection.getMetaData().getTables(databaseName, null, "%", types);
               String tableName = "";
               while (resultSet.next()) {
                  tableName = resultSet.getString(3);
                  tableNameCombo.addItem(tableName);
               }
               connectButton.setText("Connected");
               connectButton.setBackground(Color.GREEN);
               connection.close();
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, ex.getMessage());
            }
         }
      }
   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton connectButton;
   private javax.swing.JButton deleteButton;
   private javax.swing.JComboBox driverCombo;
   private javax.swing.JButton firstButton;
   private javax.swing.JButton insertButton;
   private javax.swing.JPanel insertUpdatePanel;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JLabel jLabel4;
   private javax.swing.JLabel jLabel5;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JPanel jPanel3;
   private javax.swing.JPanel jPanel4;
   private javax.swing.JPanel jPanel6;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JSplitPane jSplitPane1;
   private javax.swing.JSplitPane jSplitPane2;
   private javax.swing.JButton lastButton;
   private javax.swing.JButton nextButton;
   private javax.swing.JPasswordField passwordArea;
   private javax.swing.JButton previousButton;
   private javax.swing.JComboBox tableNameCombo;
   private javax.swing.JTable tableToShow;
   private javax.swing.JButton updateButton;
   private javax.swing.JComboBox urlCombo;
   private javax.swing.JTextArea userNameArea;
   // End of variables declaration//GEN-END:variables
}

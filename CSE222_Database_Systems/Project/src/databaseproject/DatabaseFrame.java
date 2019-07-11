package databaseproject;

import com.sun.rowset.JdbcRowSetImpl;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.RowSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class DatabaseFrame extends javax.swing.JFrame {

   private DefaultComboBoxModel tableNameModel;
   private Connection connection;
   private Statement statement;
   private ResultSet resultSet;
   private RowSet rowSet = null;
   private TableModelWithRowSet tmwrs;

   //db informations
   private String driverName = "com.mysql.jdbc.Driver";
   private String url = "jdbc:mysql://localhost/cse222project";

   public DatabaseFrame() {
      initComponents();

      rowSet = new JdbcRowSetImpl();
      tmwrs = new TableModelWithRowSet();

      //button listeners
      insertButton.addActionListener(new InsertUpdateListener());
      updateButton.addActionListener(new InsertUpdateListener());
      deleteButton.addActionListener(new DeleteButtonListener());
      queryButton.addActionListener(new QueryButtonListener());

      tableNameCombo.addActionListener(new TableNameComboListener());
      if (!login()) {
         System.exit(0);
      }
   }

   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {
      java.awt.GridBagConstraints gridBagConstraints;

      jPanel1 = new javax.swing.JPanel();
      jSplitPane1 = new javax.swing.JSplitPane();
      jPanel2 = new javax.swing.JPanel();
      jLabel5 = new javax.swing.JLabel();
      tableNameCombo = new javax.swing.JComboBox();
      insertButton = new javax.swing.JButton();
      updateButton = new javax.swing.JButton();
      deleteButton = new javax.swing.JButton();
      queryButton = new javax.swing.JButton();
      jPanel3 = new javax.swing.JPanel();
      jSplitPane2 = new javax.swing.JSplitPane();
      jPanel4 = new javax.swing.JPanel();
      jPanel6 = new javax.swing.JPanel();
      jScrollPane1 = new javax.swing.JScrollPane();
      tableToShow = new javax.swing.JTable();
      insertUpdatePanel = new javax.swing.JPanel();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setTitle("DB");

      jPanel1.setLayout(new java.awt.BorderLayout());

      jPanel2.setLayout(new java.awt.GridBagLayout());

      jLabel5.setText("Table Name");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 4;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel2.add(jLabel5, gridBagConstraints);

      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 1;
      gridBagConstraints.gridy = 4;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel2.add(tableNameCombo, gridBagConstraints);

      insertButton.setText("Insert");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.gridwidth = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 5.0;
      jPanel2.add(insertButton, gridBagConstraints);

      updateButton.setText("Update");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.gridwidth = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 5.0;
      jPanel2.add(updateButton, gridBagConstraints);

      deleteButton.setText("Delete");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.gridwidth = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 5.0;
      jPanel2.add(deleteButton, gridBagConstraints);

      queryButton.setText("Query");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 3;
      gridBagConstraints.gridwidth = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 5.0;
      jPanel2.add(queryButton, gridBagConstraints);

      jSplitPane1.setLeftComponent(jPanel2);

      jPanel3.setLayout(new java.awt.BorderLayout());

      jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

      jPanel4.setLayout(new java.awt.GridBagLayout());

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
      //</editor-fold>

      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            new DatabaseFrame().setVisible(true);
         }
      });
   }

   private void setTable(String tableName) {
      try {
         //query for setting table
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
         insertUpdatePanel.removeAll();
         insertUpdatePanel.repaint();
         setTable(tableNameModel.getSelectedItem().toString());
      }
   }

   private class InsertUpdateListener implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {
         insertUpdatePanel.removeAll();
         insertUpdatePanel.repaint();
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
                  //if i am updating i need previous informations
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
                  //get textarea texts to update row
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
                  //go to insert row and enter infos from textarea
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
         insertUpdatePanel.removeAll();
         insertUpdatePanel.repaint();
         try {
            //go to selected row in table and go to db then delete it
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

   private class QueryButtonListener implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {
         try {
            insertUpdatePanel.removeAll();
            insertUpdatePanel.repaint();
            //set table with given query from optionpane
            String query = JOptionPane.showInputDialog("Write your query:");
            if (!query.isEmpty()) {
               if (query.toLowerCase().contains("drop") || query.toLowerCase().contains("truncate")) {
                  JOptionPane.showMessageDialog(null, "This query is not allowed.");
               } else {
                  rowSet.setCommand(query);
                  rowSet.execute();
                  tmwrs.setRowSet(rowSet);
                  tableToShow.setModel(tmwrs);
               }
            }
         } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
         }
      }
   }

   private boolean login() {
      String u = "";
      String p = "";
      JPanel panel = new JPanel(new GridLayout(2, 2));

      JLabel userlabel = new JLabel("Enter user name:");
      JLabel passlabel = new JLabel("Enter password:");
      JTextArea userArea = new JTextArea();
      JPasswordField passArea = new JPasswordField();
      panel.add(userlabel);
      panel.add(userArea);
      panel.add(passlabel);
      panel.add(passArea);

      //get user name and password to connect to db from joptionpane
      String[] options = new String[]{"OK", "Cancel"};
      int option = JOptionPane.showOptionDialog(null, panel, "Login", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
      if (option == 0) {
         char[] password = passArea.getPassword();
         u = userArea.getText();
         p = new String(password);
      }
      try {
         //connect to db
         Class.forName(driverName);
         connection = DriverManager.getConnection(url, u, p);

         tableNameModel = new DefaultComboBoxModel();
         tableNameCombo.setModel(tableNameModel);

         rowSet.setUrl(url);
         rowSet.setUsername(u);
         rowSet.setPassword(p);

         String[] dbname = url.split("/");
         String databaseName = dbname[dbname.length - 1];

         // to get table names of the database
         String[] types = {"TABLE"};
         resultSet = connection.getMetaData().getTables(databaseName, null, "%", types);
         String tableName = "";
         while (resultSet.next()) {
            tableName = resultSet.getString(3);
            tableNameCombo.addItem(tableName);
         }
         connection.close();
         return true;
      } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
      } catch (ClassNotFoundException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
      }
      return false;
   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton deleteButton;
   private javax.swing.JButton insertButton;
   private javax.swing.JPanel insertUpdatePanel;
   private javax.swing.JLabel jLabel5;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JPanel jPanel3;
   private javax.swing.JPanel jPanel4;
   private javax.swing.JPanel jPanel6;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JSplitPane jSplitPane1;
   private javax.swing.JSplitPane jSplitPane2;
   private javax.swing.JButton queryButton;
   private javax.swing.JComboBox tableNameCombo;
   private javax.swing.JTable tableToShow;
   private javax.swing.JButton updateButton;
   // End of variables declaration//GEN-END:variables
}

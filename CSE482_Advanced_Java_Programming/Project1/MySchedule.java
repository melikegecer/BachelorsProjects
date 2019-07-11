package firstadvancedproject;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class MySchedule extends javax.swing.JDialog {

   private DefaultTableModel dtm;
//   private ArrayList<Course> selecteds = new ArrayList<>();

   public MySchedule(java.awt.Frame parent, boolean modal) {
      super(parent, modal);
      initComponents();

      setLocationRelativeTo(this);

      dtm = new DefaultTableModel();
      TableColumn c = new TableColumn();
      dtm.addColumn(c);
      dtm.setColumnCount(6);
      dtm.setRowCount(8);

      setFirstColumn();

      jTable1.setModel(dtm);

      jTable1.getColumnModel().getColumn(0).setHeaderValue("");
      jTable1.getColumnModel().getColumn(1).setHeaderValue("Monday");
      jTable1.getColumnModel().getColumn(2).setHeaderValue("Tuesday");
      jTable1.getColumnModel().getColumn(3).setHeaderValue("Wednesday");
      jTable1.getColumnModel().getColumn(4).setHeaderValue("Thursday");
      jTable1.getColumnModel().getColumn(5).setHeaderValue("Friday");

      setSchedule();

      this.setSize(new Dimension(700, 185));
   }

   private void setFirstColumn() {
      dtm.setValueAt("1", 0, 0);
      dtm.setValueAt("2", 1, 0);
      dtm.setValueAt("3", 2, 0);
      dtm.setValueAt("4", 3, 0);
      dtm.setValueAt("5", 4, 0);
      dtm.setValueAt("6", 5, 0);
      dtm.setValueAt("7", 6, 0);
      dtm.setValueAt("8", 7, 0);
   }

   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jPanel1 = new javax.swing.JPanel();
      jScrollPane1 = new javax.swing.JScrollPane();
      jTable1 = new javax.swing.JTable();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      setResizable(false);

      jPanel1.setLayout(new java.awt.BorderLayout());

      jTable1.setModel(new javax.swing.table.DefaultTableModel(
         new Object [][] {

         },
         new String [] {

         }
      ));
      jScrollPane1.setViewportView(jTable1);

      jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

      getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

      getAccessibleContext().setAccessibleParent(null);

      pack();
   }// </editor-fold>//GEN-END:initComponents

   public void setSchedule() {
      for (int i = 0; i < dtm.getColumnCount(); i++) {
         for (int j = 0; j < dtm.getRowCount(); j++) {
            dtm.setValueAt("", j, i);
         }
      }
      ArrayList<Course> selectedCourses = CourseSelector.getSelectedCourses();
      for (int k = 0; k < selectedCourses.size(); k++) {
         Course c = selectedCourses.get(k);
         ArrayList<Integer> columnIndex = getDays(c);
         ArrayList<Integer> rowIndex = getHours(c);

         for (int i = 0; i < rowIndex.size(); i++) {
            int x = rowIndex.get(i) - 1;
            int y = columnIndex.get(i);
//            System.out.println("name: " + c.getCourseName() + "x: " + x + " y: " + y);
            dtm.setValueAt(dtm.getValueAt(x, y) + c.getCourseName() + " ", x, y);
         }
      }
      
      setFirstColumn();
   }

   private ArrayList<Integer> getHours(Course c) {
      ArrayList<Integer> l = new ArrayList();
      String s = c.getHour();
      s = s.replace(" ", "");
      for (int i = 0; i < s.length(); i++) {
         l.add((int) s.charAt(i) - 48);
      }
      return l;
   }

   private ArrayList<Integer> getDays(Course c) {
      ArrayList<Integer> l = new ArrayList();
      String s = c.getDay();
      for (int i = 0; i < s.length(); i++) {
         char ch = s.charAt(i);
         if (ch == 'M') {
            l.add(1);
         } else if (ch == 'F') {
            l.add(5);
         } else if (ch == 'W') {
            l.add(3);
         } else if (ch == 'T') {
            if (i == s.length() - 1) {
               l.add(2);
            } else {
               if (s.charAt(i + 1) == 'h') {
                  l.add(4);
                  i++;
               } else {
                  l.add(2);
               }
            }
         }
      }
      return l;
   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JPanel jPanel1;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JTable jTable1;
   // End of variables declaration//GEN-END:variables
}

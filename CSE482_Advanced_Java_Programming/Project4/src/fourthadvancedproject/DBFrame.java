package fourthadvancedproject;

import JPAControllerss.SlotandcoursesJpaController;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class DBFrame extends javax.swing.JFrame {

   private EntityManagerFactory emf;
   private SlotandcoursesJpaController controller;

   private EntityManager em;
   private EntityTransaction tx;
//   private DefaultListModel listmodel;
   private DefaultTableModel tablemodel;

   public DBFrame() {
      initComponents();
      query1.addActionListener(new RadioListener());
      query2.addActionListener(new RadioListener());
      query3.addActionListener(new RadioListener());
      query4.addActionListener(new RadioListener());

      emf = Persistence.createEntityManagerFactory("FourthAdvancedProjectPU");
      controller = new SlotandcoursesJpaController(emf);

      em = emf.createEntityManager();
      tx = em.getTransaction();

      loadTxts("ClearData");
   }

   private void loadTxts(String fileName) {
//      tx.begin();

      File f = new File(fileName);
      File[] allFiles = f.listFiles();
      System.out.println("Txts are loading...");
      for (int i = 0; i < allFiles.length; i++) {
         ArrayList<Object> list = new ArrayList<>();
         try {
            File ff = allFiles[i];
            Scanner s = new Scanner(ff);
            String majorleavingdate = s.nextLine();
            Date leavingDate = giveTheDate(majorleavingdate);
            String studentnumber = s.nextLine().split(" ")[1];
            boolean minor = trueORfalse(s.nextLine().split(" ")[1]);

            //create student
            Student student = new Student(studentnumber, leavingDate, minor);
            System.out.println(student.getStudentnumber());

            int semestN = 0;
            while (s.hasNextLine()) {
               String str = s.nextLine();
               if (str.contains("semester")) {
                  String[] semester = str.split(" ");
                  semestN = Integer.parseInt(semester[1]);
               } else {
//                  String[] slotinfos = s.nextLine().split(" ");
                  String[] slotinfos = str.split(" ");

                  //create slot
                  int credit = Integer.parseInt(slotinfos[2]);
                  Slot slot = new Slot(slotinfos[0], credit, semestN, slotinfos[1]);

                  String[] numberofattempts = s.nextLine().split(" ");
                  int n = Integer.parseInt(numberofattempts[1]);
                  for (int j = 0; j < n; j++) {
                     String[] taken = s.nextLine().split(" ");

                     //create course
                     Course course = new Course(taken[3]);

                     //slot and course
                     int yearTaken = Integer.parseInt(taken[0]);
                     Slotandcourses sac = new Slotandcourses(yearTaken, taken[1], taken[2], slot, course, student);
//                     System.out.println(sac.getSlotandcoursesPK().getTermtaken());
                     controller.create(sac);
//                     em.persist(sac);
                  }
               }
            }
         } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException: " + ex.getMessage());
         } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
         }
      }
//      tx.commit();
//      em.close();
      System.out.println("Done");
   }

   public class RadioListener implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {
         if (e.getSource() instanceof JRadioButton) {
            JRadioButton button = (JRadioButton) e.getSource();

            JPanel panel = new JPanel(new GridLayout(1, 1));
            JLabel varLabel = new JLabel();
            JTextArea varArea = new JTextArea();
            panel.add(varLabel);
            panel.add(varArea);

            String[] options = new String[]{"OK", "Cancel"};

            if (button.getText().contains("1")) {
               int x = 0;
               varLabel.setText("Enter a year: ");
               int option = JOptionPane.showOptionDialog(null, panel, "Query", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
               if (option == 0) {
                  x = Integer.parseInt(varArea.getText());
               }

               TypedQuery<Student> que = em.createQuery("SELECT s FROM Student s", Student.class);
               List<Student> sList = que.getResultList();

               List<Student> ss = giveWantedYear(sList, x);

               tablemodel = new DefaultTableModel();
               tablemodel.addColumn("Student Number");
               tablemodel.addColumn("Leaving Date");
               tablemodel.addColumn("Minor Degree");

               for (Student s : ss) {
                  String[] stu = {s.getStudentnumber(), s.getLeavingdate().getDay() + "-" + s.getLeavingdate().getMonth() + "-" + s.getLeavingdate().getYear(), s.isMinordegree() + ""};
                  tablemodel.addRow(stu);
               }

               jTable1.setModel(tablemodel);
            } else if (button.getText().contains("2")) {
               String slotCode = "";
               varLabel.setText("Enter a slot code: ");
               int option = JOptionPane.showOptionDialog(null, panel, "Query", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
               if (option == 0) {
                  slotCode = varArea.getText();
               }
               TypedQuery<Slotandcourses> que = em.createQuery("SELECT s FROM Slotandcourses s WHERE s.slotandcoursesPK.slotcode = :slotCode", Slotandcourses.class);
               que = que.setParameter("slotCode", slotCode);

               List<Slotandcourses> sList = que.getResultList();

               tablemodel = new DefaultTableModel();
               tablemodel.addColumn("Course Code");
               tablemodel.addColumn("Slot Code");
               tablemodel.addColumn("Student Number");
               tablemodel.addColumn("Term Taken");
               tablemodel.addColumn("Grade");

               for (Slotandcourses s : sList) {
                  String[] ssac = {s.getSlotandcoursesPK().getCoursecode(), s.getSlotandcoursesPK().getSlotcode(), s.getSlotandcoursesPK().getStudentnumber(), s.getSlotandcoursesPK().getTermtaken(), s.getGrade()};
                  tablemodel.addRow(ssac);
               }

               jTable1.setModel(tablemodel);
            } else if (button.getText().contains("3")) {
               String course = "";
               varLabel.setText("Enter a course code: ");
               int option = JOptionPane.showOptionDialog(null, panel, "Query", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
               if (option == 0) {
                  course = varArea.getText();
               }

               TypedQuery<Slotandcourses> que = em.createQuery("SELECT s FROM Slotandcourses s WHERE s.slotandcoursesPK.coursecode = :course", Slotandcourses.class);
               que = que.setParameter("course", course);
               List<Slotandcourses> sList = que.getResultList();
               List<String> letters = new ArrayList<>();

               for (int i = 0; i < sList.size(); i++) {
                  letters.add(sList.get(i).getGrade());
               }
               double avg = giveAverage(letters);
               System.out.println(avg);
               JOptionPane.showMessageDialog(null, "Average for " + course + " is " + avg);

            } else if (button.getText().contains("4")) {
               String slotCode = "";
               varLabel.setText("Enter a slot code: ");
               int option = JOptionPane.showOptionDialog(null, panel, "Query", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
               if (option == 0) {
                  slotCode = varArea.getText();
               }

               TypedQuery<Slotandcourses> que = em.createQuery("SELECT s FROM Slotandcourses s WHERE s.slotandcoursesPK.slotcode = :slotCode", Slotandcourses.class);
               que = que.setParameter("slotCode", slotCode);
               List<Slotandcourses> sList = que.getResultList();
               List<String> letters = new ArrayList<>();

               for (int i = 0; i < sList.size(); i++) {
                  letters.add(sList.get(i).getGrade());
               }

               double avg = giveAverage(letters);
               System.out.println(avg);
               JOptionPane.showMessageDialog(null, "Average for " + slotCode + " is " + avg);
            } else {
               System.out.println("nothing");
            }
         }
      }

   }

   private List<Student> giveWantedYear(List<Student> sList, int year) {
      List<Student> result = new ArrayList<>();
      System.out.println("year: " + year);
      for (int i = 0; i < sList.size(); i++) {
         System.out.println(" YEAR: " + sList.get(i).toString());
         if (sList.get(i).getLeavingdate().getYear() == year) {
            result.add(sList.get(i));
         }
      }
      return result;
   }

   private static double giveAverage(List<String> list) {
      double sum = 0;
      int count = 0;

      for (int i = 0; i < list.size(); i++) {
         if (list.get(i).equals("aa")) {
            sum += 4.0;
            count++;
         } else if (list.get(i).equals("ba")) {
            sum += 3.5;
            count++;
         } else if (list.get(i).equals("bb")) {
            sum += 3.0;
            count++;
         } else if (list.get(i).equals("cb")) {
            sum += 2.5;
            count++;
         } else if (list.get(i).equals("cc")) {
            sum += 2.0;
            count++;
         } else if (list.get(i).equals("dc")) {
            sum += 1.5;
            count++;
         } else if (list.get(i).equals("dd")) {
            sum += 1.0;
            count++;
         } else if (list.get(i).contains("f")) {
         } else {
//            System.out.println("is there any other letter grade");
//            System.out.println(list.get(i));
         }
      }

      double result = sum / count * 1.0;
      return result;
   }

   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {
      java.awt.GridBagConstraints gridBagConstraints;

      buttonGroup1 = new javax.swing.ButtonGroup();
      jPanel3 = new javax.swing.JPanel();
      jPanel1 = new javax.swing.JPanel();
      jLabel1 = new javax.swing.JLabel();
      query1 = new javax.swing.JRadioButton();
      query2 = new javax.swing.JRadioButton();
      query3 = new javax.swing.JRadioButton();
      query4 = new javax.swing.JRadioButton();
      jPanel2 = new javax.swing.JPanel();
      jScrollPane1 = new javax.swing.JScrollPane();
      jTable1 = new javax.swing.JTable();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      getContentPane().setLayout(new java.awt.GridBagLayout());

      jPanel3.setLayout(new java.awt.GridBagLayout());

      jPanel1.setLayout(new java.awt.GridBagLayout());

      jLabel1.setText("Queries");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(jLabel1, gridBagConstraints);

      buttonGroup1.add(query1);
      query1.setText("1.List all graduates of year y");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(query1, gridBagConstraints);

      buttonGroup1.add(query2);
      query2.setText("2.List all courses taken for slot s");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 2;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(query2, gridBagConstraints);

      buttonGroup1.add(query3);
      query3.setText("3.Calculate the average of Course c");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 3;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(query3, gridBagConstraints);

      buttonGroup1.add(query4);
      query4.setText("4.Calculate the average of slot s");
      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 4;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel1.add(query4, gridBagConstraints);

      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      jPanel3.add(jPanel1, gridBagConstraints);

      jPanel2.setLayout(new java.awt.BorderLayout());

      jTable1.setModel(new javax.swing.table.DefaultTableModel(
         new Object [][] {

         },
         new String [] {

         }
      ));
      jScrollPane1.setViewportView(jTable1);

      jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 10.0;
      gridBagConstraints.weighty = 10.0;
      jPanel3.add(jPanel2, gridBagConstraints);

      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.weightx = 1.0;
      gridBagConstraints.weighty = 1.0;
      getContentPane().add(jPanel3, gridBagConstraints);

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
            if ("Windows".equals(info.getName())) {
               javax.swing.UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (ClassNotFoundException ex) {
         java.util.logging.Logger.getLogger(DBFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(DBFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(DBFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(DBFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>

      /* Create and display the form */
      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            new DBFrame().setVisible(true);
         }
      });
   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.ButtonGroup buttonGroup1;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JPanel jPanel3;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JTable jTable1;
   private javax.swing.JRadioButton query1;
   private javax.swing.JRadioButton query2;
   private javax.swing.JRadioButton query3;
   private javax.swing.JRadioButton query4;
   // End of variables declaration//GEN-END:variables

   private Date giveTheDate(String majorleavingdate) {
      String[] s = majorleavingdate.split(" ");
      int day = Integer.parseInt(s[1]);
      int month = Integer.parseInt(s[2]);
      int year = Integer.parseInt(s[3]);
      return new Date(year, month, day);
   }

   private boolean trueORfalse(String s) {
      if (s.contains("true")) {
         return true;
      }
      return false;
   }
}

package firstadvancedproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class CourseSelector extends java.awt.Frame {

   private static DefaultComboBoxModel dcbm;
   private static DefaultListModel clm;
   private static DefaultListModel sclm;

   private static ArrayList departments = new ArrayList();
   //list of all courses
   private static ArrayList<Course> courses = new ArrayList<>();
   //list of courses showed in course list
   private static ArrayList<Course> coursesShowed = new ArrayList<>();
   //list of courses showed in selected lisr
   private static ArrayList<Course> selectedCourses = new ArrayList<>();

   private static MySchedule sche;

   private static String choosedDept = "";
   private static String choosedLevel = "";

   public CourseSelector() {
      initComponents();
      setLocationRelativeTo(this);

      sche = new MySchedule(null, false);

      dcbm = new DefaultComboBoxModel();
      for (int i = 0; i < departments.size(); i++) {
         dcbm.addElement(departments.get(i));
      }
      deptList.setModel(dcbm);

      clm = new DefaultListModel();
      courseList.setModel(clm);

      sclm = new DefaultListModel();
      selectedCourseList.setModel(sclm);

      freshmanButton.addActionListener(new MyButtonListener());
      sophomoreButton.addActionListener(new MyButtonListener());
      juniorButton.addActionListener(new MyButtonListener());
      seniorButton.addActionListener(new MyButtonListener());
      deptButton.addActionListener(new MyButtonListener());
      freeButton.addActionListener(new MyButtonListener());

      deptList.addActionListener(new MyButtonListener());

      addButton.addActionListener(new MyButtonListener());
      removeButton.addActionListener(new MyButtonListener());
      saveButton.addActionListener(new MyButtonListener());
      showScheduleButton.addActionListener(new MyButtonListener());
   }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        deptList = new javax.swing.JComboBox();
        freshmanButton = new javax.swing.JRadioButton();
        sophomoreButton = new javax.swing.JRadioButton();
        juniorButton = new javax.swing.JRadioButton();
        seniorButton = new javax.swing.JRadioButton();
        deptButton = new javax.swing.JRadioButton();
        freeButton = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        courseList = new javax.swing.JList();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        selectedCourseList = new javax.swing.JList();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        showScheduleButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(525, 350));
        setMinimumSize(new java.awt.Dimension(525, 350));
        setPreferredSize(new java.awt.Dimension(525, 350));
        setResizable(false);
        setTitle("Course Selector");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Select Department");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(deptList, gridBagConstraints);

        buttonGroup1.add(freshmanButton);
        freshmanButton.setText("Freshman");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(freshmanButton, gridBagConstraints);

        buttonGroup1.add(sophomoreButton);
        sophomoreButton.setText("Sophomore");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(sophomoreButton, gridBagConstraints);

        buttonGroup1.add(juniorButton);
        juniorButton.setText("Junior");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(juniorButton, gridBagConstraints);

        buttonGroup1.add(seniorButton);
        seniorButton.setText("Senior");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(seniorButton, gridBagConstraints);

        buttonGroup1.add(deptButton);
        deptButton.setText("Departmental Electives");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(deptButton, gridBagConstraints);

        buttonGroup1.add(freeButton);
        freeButton.setText("Free Electives");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(freeButton, gridBagConstraints);

        jPanel1.add(jPanel2, new java.awt.GridBagConstraints());

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Course List");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(jLabel3, gridBagConstraints);

        jScrollPane3.setViewportView(courseList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 125;
        gridBagConstraints.ipady = 200;
        jPanel3.add(jScrollPane3, gridBagConstraints);

        jPanel1.add(jPanel3, new java.awt.GridBagConstraints());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel8.setLayout(new java.awt.GridLayout(2, 1));

        addButton.setText("Add");
        addButton.setMaximumSize(new java.awt.Dimension(71, 23));
        addButton.setMinimumSize(new java.awt.Dimension(71, 23));
        addButton.setPreferredSize(new java.awt.Dimension(71, 23));
        jPanel8.add(addButton);

        removeButton.setText("Remove");
        jPanel8.add(removeButton);

        jPanel4.add(jPanel8, new java.awt.GridBagConstraints());

        jPanel1.add(jPanel4, new java.awt.GridBagConstraints());

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Selected Courses");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(jLabel2, gridBagConstraints);

        jScrollPane4.setViewportView(selectedCourseList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 125;
        gridBagConstraints.ipady = 200;
        jPanel5.add(jScrollPane4, gridBagConstraints);

        jPanel1.add(jPanel5, new java.awt.GridBagConstraints());

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new java.awt.GridLayout(2, 1));

        showScheduleButton.setText("View Schedule");
        jPanel7.add(showScheduleButton);

        saveButton.setText("Save");
        saveButton.setMaximumSize(new java.awt.Dimension(101, 23));
        saveButton.setMinimumSize(new java.awt.Dimension(101, 23));
        saveButton.setPreferredSize(new java.awt.Dimension(101, 23));
        jPanel7.add(saveButton);

        jPanel6.add(jPanel7, java.awt.BorderLayout.EAST);

        add(jPanel6, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
       System.exit(0);
    }//GEN-LAST:event_exitForm

   private static void setCLM() {
      clm.removeAllElements();
      coursesShowed.clear();
      if (!choosedDept.equals("Choose department")) {
         for (Course c : courses) {
            if (c.getDeptName().equals(choosedDept) && c.getLevel().equals(choosedLevel)) {
               coursesShowed.add(c);
               clm.addElement(c.getCourseName() + " " + c.getDay() + " " + c.getHour());
            }
         }
      } else {
         JOptionPane.showMessageDialog(null, "Choose a department.");
      }
   }

   private static void setSCLM() {
      sclm.removeAllElements();
      for (Course c : selectedCourses) {
         sclm.addElement(c.getCourseName() + " " + c.getDay() + " " + c.getHour());
      }
   }

   class MyButtonListener implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {
         if (e.getSource() instanceof JRadioButton) {
            JRadioButton radioButton = (JRadioButton) e.getSource();
            if (radioButton.isSelected()) {
               choosedLevel = radioButton.getText().toString().replace(" ", "");
               if (choosedLevel.equals("FreeElectives")) {
                  choosedDept = "FreeElectives";
               }
//               System.out.println(choosedLevel + " " + choosedDept);
               setCLM();
               choosedDept = deptList.getSelectedItem().toString();
            }
         } else if (e.getSource() instanceof JComboBox) {
            JComboBox combo = (JComboBox) e.getSource();
            choosedDept = combo.getSelectedItem().toString();
//            System.out.println("dept: " + choosedDept);
            setCLM();
         } else if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Add")) {
               int[] index = courseList.getSelectedIndices();
               for (int i = 0; i < index.length; i++) {
                  //duplicates!
                  if (!selectedCourses.contains(coursesShowed.get(index[i]))) {
                     selectedCourses.add(coursesShowed.get(index[i]));
                  } else {
                     JOptionPane.showMessageDialog(null, "You already choosed " + coursesShowed.get(index[i]).getCourseName());
                  }
               }
               sche.setSchedule();
               setSCLM();
            } else if (button.getText().equals("Remove")) {
               int[] index = selectedCourseList.getSelectedIndices();
               ArrayList<Course> cc = new ArrayList<>();
               for (int i = 0; i < index.length; i++) {
                  cc.add(selectedCourses.get(index[i]));
               }

               for (int i = 0; i < cc.size(); i++) {
                  selectedCourses.remove(cc.get(i));
               }
               sche.setSchedule();
               setSCLM();
            } else if (button.getText().equals("View Schedule")) {
               sche.setSchedule();
               sche.setVisible(true);
            } else if (button.getText().equals("Save")) {
               try {
                  File f;
                  JFileChooser file = new JFileChooser();
                  if (file.showOpenDialog(file) == JFileChooser.APPROVE_OPTION) {
                     f = file.getSelectedFile();
                     RandomAccessFile raf = new RandomAccessFile(f, "rw");
                     raf.seek(raf.length());
                     for (Course c : selectedCourses) {
                        raf.writeBytes(c.getCourseName() + " " + c.getDay() + " " + c.getHour() + "\n");
                     }
                     raf.close();
                  }
               } catch (FileNotFoundException ex) {
                  System.out.println("FileNotFoundException: " + ex.getMessage());
               } catch (IOException ex) {
                  System.out.println("IOException: " + ex.getMessage());
               }
            } else {
               System.out.println("Is there any other action?");
            }
         }
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
            if ("Windows".equals(info.getName())) {
               javax.swing.UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (ClassNotFoundException ex) {
         java.util.logging.Logger.getLogger(CourseSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(CourseSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(CourseSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(CourseSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>

      departments.add("Choose department");
      try {
         String deptName = "";
         String level = "";
         String courseName = "";
         String day = "";
         String hour = "";

         File f = new File("coursesFile.txt");
         Scanner s = new Scanner(f);
         while (s.hasNextLine()) {
            String x = s.nextLine();
            if (isItDept(x)) {
               deptName = x.substring(5);
               departments.add(deptName);
            } else if (isItLevel(x)) {
               level = x;
            } else {
               String[] courseDetails = x.split("-");
               courseName = courseDetails[0];
               day = courseDetails[1];
               hour = courseDetails[2];
               if (level.equals("FreeElectives")) {
                  deptName = "FreeElectives";
               }
               Course c = new Course(deptName, level, courseName, day, hour);
               courses.add(c);
            }
         }

      } catch (FileNotFoundException ex) {
         System.out.println("FileNotFoundException: " + ex.getMessage());
      }

      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            new CourseSelector().setVisible(true);
         }
      });
   }

   public static ArrayList<Course> getSelectedCourses() {
      return selectedCourses;
   }

   private static boolean isItDept(String s) {
      if (s.contains("Dept")) {
         return true;
      }
      return false;
   }

   private static boolean isItLevel(String s) {
      if (s.equals("Freshman")) {
         return true;
      } else if (s.equals("Sophomore")) {
         return true;
      } else if (s.equals("Junior")) {
         return true;
      } else if (s.equals("Senior")) {
         return true;
      } else if (s.equals("DepartmentalElectives")) {
         return true;
      } else if (s.equals("FreeElectives")) {
         return true;
      }
      return false;
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JList courseList;
    private javax.swing.JRadioButton deptButton;
    private javax.swing.JComboBox deptList;
    private javax.swing.JRadioButton freeButton;
    private javax.swing.JRadioButton freshmanButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton juniorButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JList selectedCourseList;
    private javax.swing.JRadioButton seniorButton;
    private javax.swing.JButton showScheduleButton;
    private javax.swing.JRadioButton sophomoreButton;
    // End of variables declaration//GEN-END:variables
}

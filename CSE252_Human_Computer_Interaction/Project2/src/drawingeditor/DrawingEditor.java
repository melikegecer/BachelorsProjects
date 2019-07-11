package drawingeditor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.SpinnerNumberModel;
import processing.core.PApplet;

public class DrawingEditor extends javax.swing.JFrame {

   private processing.core.PApplet sketch = new DrawingScreen();

   private SpinnerNumberModel hModel;
   private SpinnerNumberModel wModel;
   private SpinnerNumberModel xModel;
   private SpinnerNumberModel yModel;

   private int red;
   private int green;
   private int blue;

   private int w;
   private int h;
   private int x;
   private int y;

   private int paintAreaWidth;
   private int paintAreaHeight;

   public DrawingEditor() {
      red = 0;
      green = 0;
      blue = 0;
      initComponents();

      drawPanel.add(sketch);
      showColor.setOpaque(true);
      setColor();
      sketch.init();
      paintAreaWidth = drawPanel.getWidth();
      paintAreaHeight = drawPanel.getHeight();

      hModel = new SpinnerNumberModel(50, 1, 500, 5);
      wModel = new SpinnerNumberModel(50, 1, 500, 5);
      xModel = new SpinnerNumberModel(50, 1, 500, 5);
      yModel = new SpinnerNumberModel(50, 1, 500, 5);
      hSpinner.setModel(hModel);
      wSpinner.setModel(wModel);
      xSpinner.setModel(xModel);
      ySpinner.setModel(yModel);

      rectangleButton.addActionListener(new MyAction());
      circleButton.addActionListener(new MyAction());
      lineButton.addActionListener(new MyAction());
      clearButton.addActionListener(new MyAction());
      colorButton.addActionListener(new MyAction());

      drawPanel.addComponentListener(new ComponentListener() {

         @Override
         public void componentResized(ComponentEvent e) {
            paintAreaWidth = e.getComponent().getWidth();
            paintAreaHeight = e.getComponent().getHeight();
//            System.out.println("width: " + paintAreaWidth + " height: " + paintAreaHeight);
         }

         @Override
         public void componentMoved(ComponentEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }

         @Override
         public void componentShown(ComponentEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }

         @Override
         public void componentHidden(ComponentEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }
      });
   }

   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        drawPanel = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        colorButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        showColor = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        wSpinner = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        hSpinner = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        xSpinner = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ySpinner = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rectangleButton = new javax.swing.JButton();
        circleButton = new javax.swing.JButton();
        lineButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Drawing Editor");
        setMaximumSize(new java.awt.Dimension(650, 500));
        setMinimumSize(new java.awt.Dimension(650, 500));
        setPreferredSize(new java.awt.Dimension(650, 500));

        jPanel1.setLayout(new java.awt.BorderLayout());

        drawPanel.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout drawPanelLayout = new javax.swing.GroupLayout(drawPanel);
        drawPanel.setLayout(drawPanelLayout);
        drawPanelLayout.setHorizontalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        drawPanelLayout.setVerticalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        jPanel1.add(drawPanel, java.awt.BorderLayout.CENTER);

        menuPanel.setPreferredSize(new java.awt.Dimension(100, 500));
        menuPanel.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        colorButton.setText("Change");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(colorButton, gridBagConstraints);

        jLabel1.setText("  Color   ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel1, gridBagConstraints);

        showColor.setBackground(new java.awt.Color(0, 0, 0));
        showColor.setText("     ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(showColor, gridBagConstraints);

        jLabel4.setText(" Width / X1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(wSpinner, gridBagConstraints);

        jLabel5.setText("Height / Y1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(hSpinner, gridBagConstraints);

        jLabel6.setText("       X / X2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(xSpinner, gridBagConstraints);

        jLabel2.setText("     ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel7.setText("     ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel7, gridBagConstraints);

        jLabel8.setText("     ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel8, gridBagConstraints);

        jLabel9.setText("     ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel9, gridBagConstraints);

        jLabel10.setText("     ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel10, gridBagConstraints);

        jLabel11.setText("       Y / Y2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(ySpinner, gridBagConstraints);

        jLabel12.setText("       ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel12, gridBagConstraints);

        jLabel13.setText("     ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jLabel13, gridBagConstraints);

        jLabel3.setText("     ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        menuPanel.add(jPanel2, gridBagConstraints);

        rectangleButton.setText("Rectangle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        menuPanel.add(rectangleButton, gridBagConstraints);

        circleButton.setText("Circle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        menuPanel.add(circleButton, gridBagConstraints);

        lineButton.setText("Line");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        menuPanel.add(lineButton, gridBagConstraints);

        clearButton.setText("Clear All");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        menuPanel.add(clearButton, gridBagConstraints);

        jPanel1.add(menuPanel, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

   private void setColor() {
      showColor.setBackground(new Color(red, green, blue));
   }

   class MyAction implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {
         if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            int x = (Integer) xSpinner.getValue();
            int y = (Integer) ySpinner.getValue();
            int w = (Integer) wSpinner.getValue();
            int h = (Integer) hSpinner.getValue();
            sketch.stroke(red, green, blue);
            if (button.getText().toLowerCase().contains("rectangle")) {
               sketch.rect(x, y, w, h);
            } else if (button.getText().toLowerCase().contains("circle")) {
               sketch.ellipse(x, y, w, h);
            } else if (button.getText().toLowerCase().contains("line")) {
               sketch.line(x, y, w, h);
            } else if (button.getText().toLowerCase().contains("clear")) {
               sketch.background(255);
            } else if (button.getText().toLowerCase().contains("change")) {
               Color backgroundColor = JColorChooser.showDialog(null, "Choose background color", Color.white);
               if (backgroundColor != null) {
                  red = backgroundColor.getRed();
                  green = backgroundColor.getGreen();
                  blue = backgroundColor.getBlue();
               }
               setColor();
            } else {
               System.out.println("no other button??");
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
         java.util.logging.Logger.getLogger(DrawingEditor.class
                 .getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(DrawingEditor.class
                 .getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(DrawingEditor.class
                 .getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(DrawingEditor.class
                 .getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>
      //</editor-fold>

      /* Create and display the form */
      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            new DrawingEditor().setVisible(true);
         }
      });
   }

   public class DrawingScreen extends PApplet {

      public void setup() {
         size(500, 500);
         background(255);
      }

      @Override
      public void draw() {
         size(paintAreaWidth, paintAreaHeight);
      }

   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton circleButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton colorButton;
    private javax.swing.JPanel drawPanel;
    private javax.swing.JSpinner hSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton lineButton;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JButton rectangleButton;
    private javax.swing.JLabel showColor;
    private javax.swing.JSpinner wSpinner;
    private javax.swing.JSpinner xSpinner;
    private javax.swing.JSpinner ySpinner;
    // End of variables declaration//GEN-END:variables
}

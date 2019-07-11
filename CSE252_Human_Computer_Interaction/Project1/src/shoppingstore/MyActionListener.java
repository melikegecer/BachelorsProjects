package shoppingstore;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MyActionListener implements ActionListener {

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() instanceof JButton) {
         JButton button = (JButton) e.getSource();
         if (button.getText().toLowerCase().contains("next")) {
            Screen2 s = new Screen2();
            Screen1.getfList().add(s);
            s.setVisible(true);
            Frame f = Screen1.getfList().get(0);
            f.dispose();
            Screen1.getfList().remove(0);
         } else if (button.getText().toLowerCase().contains("trigger")) {
            System.exit(0);
         }
      }
   }

}

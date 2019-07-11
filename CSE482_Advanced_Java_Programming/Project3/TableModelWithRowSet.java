package thirdadvancedproject;

import com.sun.rowset.JdbcRowSetImpl;
import java.sql.SQLException;
import javax.sql.RowSet;
import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.swing.table.AbstractTableModel;

public class TableModelWithRowSet extends AbstractTableModel implements RowSetListener {

   private RowSet rowSet;

   public TableModelWithRowSet() {
      rowSet = new JdbcRowSetImpl();
   }

   public int getRowCount() {
      try {
         rowSet.last();
         return rowSet.getRow(); // Get the current row number
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      return 0;
   }

   public int getColumnCount() {
      try {
         if (rowSet != null) {
            return rowSet.getMetaData().getColumnCount();
         }
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return 0;
   }

   public Object getValueAt(int row, int column) {
      try {
         rowSet.absolute(row + 1);
         return rowSet.getObject(column + 1);
      } catch (SQLException sqlex) {
         sqlex.printStackTrace();
      }
      return null;
   }

   @Override
   public String getColumnName(int column) {
      try {
         return rowSet.getMetaData().getColumnLabel(column + 1);
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return "";
   }

   public void rowSetChanged(RowSetEvent e) {
//      works in insert and delete 
//      System.out.println("RowSet changed");
      fireTableStructureChanged();
   }

   @Override
   public void rowChanged(RowSetEvent e) {
//      if we did an update
//      System.out.println("Row changed");
      fireTableDataChanged();
   }

   @Override
   public void cursorMoved(RowSetEvent e) {
//      System.out.println("cursor moved");
   }

   public RowSet getRowSet() {
      return rowSet;
   }

   public void setRowSet(RowSet rowSet) {
      if (rowSet != null) {
         this.rowSet = rowSet;
         rowSet.addRowSetListener(this);
         fireTableStructureChanged();
      }
   }
}

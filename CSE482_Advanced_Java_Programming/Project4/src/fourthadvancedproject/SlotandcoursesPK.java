package fourthadvancedproject;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class SlotandcoursesPK implements Serializable {

   private String termtaken;

   private String studentnumber;

   private String coursecode;

   private int yeartaken;

   private String slotcode;

   public SlotandcoursesPK() {
   }

   public SlotandcoursesPK(String termtaken, String studentnumber, String coursecode, int yeartaken, String slotcode) {
      this.termtaken = termtaken;
      this.studentnumber = studentnumber;
      this.coursecode = coursecode;
      this.yeartaken = yeartaken;
      this.slotcode = slotcode;
   }

   public String getTermtaken() {
      return termtaken;
   }

   public void setTermtaken(String termtaken) {
      this.termtaken = termtaken;
   }

   public String getStudentnumber() {
      return studentnumber;
   }

   public void setStudentnumber(String studentnumber) {
      this.studentnumber = studentnumber;
   }

   public String getCoursecode() {
      return coursecode;
   }

   public void setCoursecode(String coursecode) {
      this.coursecode = coursecode;
   }

   public int getYeartaken() {
      return yeartaken;
   }

   public void setYeartaken(int yeartaken) {
      this.yeartaken = yeartaken;
   }

   public String getSlotcode() {
      return slotcode;
   }

   public void setSlotcode(String slotcode) {
      this.slotcode = slotcode;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (termtaken != null ? termtaken.hashCode() : 0);
      hash += (studentnumber != null ? studentnumber.hashCode() : 0);
      hash += (coursecode != null ? coursecode.hashCode() : 0);
      hash += (int) yeartaken;
      hash += (slotcode != null ? slotcode.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof SlotandcoursesPK)) {
         return false;
      }
      SlotandcoursesPK other = (SlotandcoursesPK) object;
      if ((this.termtaken == null && other.termtaken != null) || (this.termtaken != null && !this.termtaken.equals(other.termtaken))) {
         return false;
      }
      if ((this.studentnumber == null && other.studentnumber != null) || (this.studentnumber != null && !this.studentnumber.equals(other.studentnumber))) {
         return false;
      }
      if ((this.coursecode == null && other.coursecode != null) || (this.coursecode != null && !this.coursecode.equals(other.coursecode))) {
         return false;
      }
      if (this.yeartaken != other.yeartaken) {
         return false;
      }
      if ((this.slotcode == null && other.slotcode != null) || (this.slotcode != null && !this.slotcode.equals(other.slotcode))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "termtaken=" + termtaken + " studentnumber=" + studentnumber + " coursecode=" + coursecode + " yeartaken=" + yeartaken + " slotcode=" + slotcode;
   }

}

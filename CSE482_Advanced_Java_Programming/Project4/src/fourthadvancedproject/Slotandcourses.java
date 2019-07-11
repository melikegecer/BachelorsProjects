package fourthadvancedproject;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "slotandcourses")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "Slotandcourses.findAll", query = "SELECT s FROM Slotandcourses s"),
   @NamedQuery(name = "Slotandcourses.findByGrade", query = "SELECT s FROM Slotandcourses s WHERE s.grade = :grade"),
   @NamedQuery(name = "Slotandcourses.findByTermtaken", query = "SELECT s FROM Slotandcourses s WHERE s.slotandcoursesPK.termtaken = :termtaken"),
   @NamedQuery(name = "Slotandcourses.findByStudentnumber", query = "SELECT s FROM Slotandcourses s WHERE s.slotandcoursesPK.studentnumber = :studentnumber"),
   @NamedQuery(name = "Slotandcourses.findByCoursecode", query = "SELECT s FROM Slotandcourses s WHERE s.slotandcoursesPK.coursecode = :coursecode"),
   @NamedQuery(name = "Slotandcourses.findByYeartaken", query = "SELECT s FROM Slotandcourses s WHERE s.slotandcoursesPK.yeartaken = :yeartaken"),
   @NamedQuery(name = "Slotandcourses.findBySlotcode", query = "SELECT s FROM Slotandcourses s WHERE s.slotandcoursesPK.slotcode = :slotcode")})
public class Slotandcourses implements Serializable {

   private static final long serialVersionUID = 1L;
   @EmbeddedId
   protected SlotandcoursesPK slotandcoursesPK;

   private String grade;

   @JoinColumn(name = "SLOTCODE", referencedColumnName = "SLOTCODE", insertable = false, updatable = false)
   @ManyToOne(optional = false)
   private Slot slot;

   @JoinColumn(name = "COURSECODE", referencedColumnName = "COURSECODE", insertable = false, updatable = false)
   @ManyToOne(optional = false)
   private Course course;

   @JoinColumn(name = "STUDENTNUMBER", referencedColumnName = "STUDENTNUMBER", insertable = false, updatable = false)
   @ManyToOne(optional = false)
   private Student student;

   public Slotandcourses() {
   }

   public Slotandcourses(SlotandcoursesPK slotandcoursesPK) {
      this.slotandcoursesPK = slotandcoursesPK;
   }

   public Slotandcourses(String termtaken, String studentnumber, String coursecode, int yeartaken, String slotcode) {
      this.slotandcoursesPK = new SlotandcoursesPK(termtaken, studentnumber, coursecode, yeartaken, slotcode);
   }

   public Slotandcourses(int yearTaken, String termTaken, String grade, Slot slot, Course course, Student student) {
      this.slotandcoursesPK = new SlotandcoursesPK(termTaken, student.getStudentnumber(), course.getCoursecode(), yearTaken, slot.getSlotcode());
      this.grade = grade;
      this.slot = slot;
      this.course = course;
      this.student = student;
   }

   public SlotandcoursesPK getSlotandcoursesPK() {
      return slotandcoursesPK;
   }

   public void setSlotandcoursesPK(SlotandcoursesPK slotandcoursesPK) {
      this.slotandcoursesPK = slotandcoursesPK;
   }

   public String getGrade() {
      return grade;
   }

   public void setGrade(String grade) {
      this.grade = grade;
   }

   public Slot getSlot() {
      return slot;
   }

   public void setSlot(Slot slot) {
      this.slot = slot;
   }

   public Course getCourse() {
      return course;
   }

   public void setCourse(Course course) {
      this.course = course;
   }

   public Student getStudent() {
      return student;
   }

   public void setStudent(Student student) {
      this.student = student;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (slotandcoursesPK != null ? slotandcoursesPK.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof Slotandcourses)) {
         return false;
      }
      Slotandcourses other = (Slotandcourses) object;
      if ((this.slotandcoursesPK == null && other.slotandcoursesPK != null) || (this.slotandcoursesPK != null && !this.slotandcoursesPK.equals(other.slotandcoursesPK))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "slotandcoursesPK=" + slotandcoursesPK.toString() + " grade=" + grade;
   }

}

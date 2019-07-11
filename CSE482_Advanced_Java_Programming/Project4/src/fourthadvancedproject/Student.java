package fourthadvancedproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
   @NamedQuery(name = "Student.findByStudentnumber", query = "SELECT s FROM Student s WHERE s.studentnumber = :studentnumber"),
   @NamedQuery(name = "Student.findByLeavingdate", query = "SELECT s FROM Student s WHERE s.leavingdate = :leavingdate"),
   @NamedQuery(name = "Student.findByMinordegree", query = "SELECT s FROM Student s WHERE s.minordegree = :minordegree")})
public class Student implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   private String studentnumber;
  
   @Temporal(TemporalType.DATE)
   private Date leavingdate;
   
   private boolean minordegree;
   
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
   private Collection<Slotandcourses> slotandcoursesCollection;

   public Student() {
   }

   public Student(String studentnumber) {
      this.studentnumber = studentnumber;
   }

   public Student(String studentnumber, Date leavingdate, boolean minordegree) {
      this.studentnumber = studentnumber;
      this.leavingdate = leavingdate;
      this.minordegree = minordegree;
      this.slotandcoursesCollection = new ArrayList<>();
   }

   public String getStudentnumber() {
      return studentnumber;
   }

   public void setStudentnumber(String studentnumber) {
      this.studentnumber = studentnumber;
   }

   public Date getLeavingdate() {
      return leavingdate;
   }

   public void setLeavingdate(Date leavingdate) {
      this.leavingdate = leavingdate;
   }

   public boolean isMinordegree() {
      return minordegree;
   }

   public void setMinordegree(boolean minordegree) {
      this.minordegree = minordegree;
   }

   @XmlTransient
   public Collection<Slotandcourses> getSlotandcoursesCollection() {
      return slotandcoursesCollection;
   }

   public void setSlotandcoursesCollection(Collection<Slotandcourses> slotandcoursesCollection) {
      this.slotandcoursesCollection = slotandcoursesCollection;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (studentnumber != null ? studentnumber.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof Student)) {
         return false;
      }
      Student other = (Student) object;
      if ((this.studentnumber == null && other.studentnumber != null) || (this.studentnumber != null && !this.studentnumber.equals(other.studentnumber))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "studentnumber=" + studentnumber + " leavingdate=" + leavingdate.toString() + " minordegree=" + minordegree;
   }

}

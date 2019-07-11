package fourthadvancedproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "course")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
   @NamedQuery(name = "Course.findByCoursecode", query = "SELECT c FROM Course c WHERE c.coursecode = :coursecode")})
public class Course implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   private String coursecode;

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
   private Collection<Slotandcourses> slotandcoursesCollection;

   public Course() {
   }

   public Course(String coursecode) {
      this.coursecode = coursecode;
      this.slotandcoursesCollection = new ArrayList<>();
   }

   public String getCoursecode() {
      return coursecode;
   }

   public void setCoursecode(String coursecode) {
      this.coursecode = coursecode;
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
      hash += (coursecode != null ? coursecode.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof Course)) {
         return false;
      }
      Course other = (Course) object;
      if ((this.coursecode == null && other.coursecode != null) || (this.coursecode != null && !this.coursecode.equals(other.coursecode))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "coursecode= " + coursecode;
   }

}

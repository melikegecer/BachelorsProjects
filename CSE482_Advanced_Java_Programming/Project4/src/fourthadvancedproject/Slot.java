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
@Table(name = "slot")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "Slot.findAll", query = "SELECT s FROM Slot s"),
   @NamedQuery(name = "Slot.findBySlotcode", query = "SELECT s FROM Slot s WHERE s.slotcode = :slotcode"),
   @NamedQuery(name = "Slot.findByCredit", query = "SELECT s FROM Slot s WHERE s.credit = :credit"),
   @NamedQuery(name = "Slot.findBySemester", query = "SELECT s FROM Slot s WHERE s.semester = :semester"),
   @NamedQuery(name = "Slot.findBySlotname", query = "SELECT s FROM Slot s WHERE s.slotname = :slotname")})
public class Slot implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   private String slotcode;

   private int credit;
   private int semester;
   private String slotname;

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "slot")
   private Collection<Slotandcourses> slotandcoursesCollection;

   public Slot() {
   }

   public Slot(String slotcode) {
      this.slotcode = slotcode;
      slotandcoursesCollection = new ArrayList<>();
   }

   public Slot(String slotcode, int credit, int semester, String slotname) {
      this.slotcode = slotcode;
      this.credit = credit;
      this.semester = semester;
      this.slotname = slotname;
      this.slotandcoursesCollection = new ArrayList<>();
   }

   public String getSlotcode() {
      return slotcode;
   }

   public void setSlotcode(String slotcode) {
      this.slotcode = slotcode;
   }

   public Integer getCredit() {
      return credit;
   }

   public void setCredit(Integer credit) {
      this.credit = credit;
   }

   public Integer getSemester() {
      return semester;
   }

   public void setSemester(Integer semester) {
      this.semester = semester;
   }

   public String getSlotname() {
      return slotname;
   }

   public void setSlotname(String slotname) {
      this.slotname = slotname;
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
      hash += (slotcode != null ? slotcode.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof Slot)) {
         return false;
      }
      Slot other = (Slot) object;
      if ((this.slotcode == null && other.slotcode != null) || (this.slotcode != null && !this.slotcode.equals(other.slotcode))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "slotcode=" + slotcode + " credit=" + credit + " semester=" + semester + " slotname=" + slotname;
   }

}

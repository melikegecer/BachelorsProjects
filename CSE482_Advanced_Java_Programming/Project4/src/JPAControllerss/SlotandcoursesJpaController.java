package JPAControllerss;

import JPAControllerss.exceptions.NonexistentEntityException;
import JPAControllerss.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import fourthadvancedproject.Slot;
import fourthadvancedproject.Course;
import fourthadvancedproject.Slotandcourses;
import fourthadvancedproject.SlotandcoursesPK;
import fourthadvancedproject.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SlotandcoursesJpaController implements Serializable {

   public SlotandcoursesJpaController(EntityManagerFactory emf) {
      this.emf = emf;
   }
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(Slotandcourses slotandcourses) throws PreexistingEntityException, Exception {
      if (slotandcourses.getSlotandcoursesPK() == null) {
         slotandcourses.setSlotandcoursesPK(new SlotandcoursesPK());
      }
      EntityManager em = null;
      try {
         em = getEntityManager();
         em.getTransaction().begin();
         Slot slot = slotandcourses.getSlot();
//         if (slot != null) {
//            String sl = slot.getSlotcode();
//            Class cl = slot.getClass();
//            slot = em.getReference(cl,sl);
//            slotandcourses.setSlot(slot);
//         }
         Course course = slotandcourses.getCourse();
//         if (course != null) {
//            course = em.getReference(course.getClass(), course.getCoursecode());
//            slotandcourses.setCourse(course);
//         }
         Student student = slotandcourses.getStudent();
//         if (student != null) {
//            student = em.getReference(student.getClass(), student.getStudentnumber());
//            slotandcourses.setStudent(student);
//         }
         em.persist(slotandcourses);
         if (slot != null) {
            slot.getSlotandcoursesCollection().add(slotandcourses);
            slot = em.merge(slot);
         }
         if (course != null) {
            course.getSlotandcoursesCollection().add(slotandcourses);
            course = em.merge(course);
         }
         if (student != null) {
            student.getSlotandcoursesCollection().add(slotandcourses);
            student = em.merge(student);
         }
         em.getTransaction().commit();
      } catch (Exception ex) {
         if (findSlotandcourses(slotandcourses.getSlotandcoursesPK()) != null) {
            throw new PreexistingEntityException("Slotandcourses " + slotandcourses + " already exists.", ex);
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void edit(Slotandcourses slotandcourses) throws NonexistentEntityException, Exception {
      EntityManager em = null;
      try {
         em = getEntityManager();
         em.getTransaction().begin();
         Slotandcourses persistentSlotandcourses = em.find(Slotandcourses.class, slotandcourses.getSlotandcoursesPK());
         Slot slotOld = persistentSlotandcourses.getSlot();
         Slot slotNew = slotandcourses.getSlot();
         Course courseOld = persistentSlotandcourses.getCourse();
         Course courseNew = slotandcourses.getCourse();
         Student studentOld = persistentSlotandcourses.getStudent();
         Student studentNew = slotandcourses.getStudent();
         if (slotNew != null) {
            slotNew = em.getReference(slotNew.getClass(), slotNew.getSlotcode());
            slotandcourses.setSlot(slotNew);
         }
         if (courseNew != null) {
            courseNew = em.getReference(courseNew.getClass(), courseNew.getCoursecode());
            slotandcourses.setCourse(courseNew);
         }
         if (studentNew != null) {
            studentNew = em.getReference(studentNew.getClass(), studentNew.getStudentnumber());
            slotandcourses.setStudent(studentNew);
         }
         slotandcourses = em.merge(slotandcourses);
         if (slotOld != null && !slotOld.equals(slotNew)) {
            slotOld.getSlotandcoursesCollection().remove(slotandcourses);
            slotOld = em.merge(slotOld);
         }
         if (slotNew != null && !slotNew.equals(slotOld)) {
            slotNew.getSlotandcoursesCollection().add(slotandcourses);
            slotNew = em.merge(slotNew);
         }
         if (courseOld != null && !courseOld.equals(courseNew)) {
            courseOld.getSlotandcoursesCollection().remove(slotandcourses);
            courseOld = em.merge(courseOld);
         }
         if (courseNew != null && !courseNew.equals(courseOld)) {
            courseNew.getSlotandcoursesCollection().add(slotandcourses);
            courseNew = em.merge(courseNew);
         }
         if (studentOld != null && !studentOld.equals(studentNew)) {
            studentOld.getSlotandcoursesCollection().remove(slotandcourses);
            studentOld = em.merge(studentOld);
         }
         if (studentNew != null && !studentNew.equals(studentOld)) {
            studentNew.getSlotandcoursesCollection().add(slotandcourses);
            studentNew = em.merge(studentNew);
         }
         em.getTransaction().commit();
      } catch (Exception ex) {
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            SlotandcoursesPK id = slotandcourses.getSlotandcoursesPK();
            if (findSlotandcourses(id) == null) {
               throw new NonexistentEntityException("The slotandcourses with id " + id + " no longer exists.");
            }
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void destroy(SlotandcoursesPK id) throws NonexistentEntityException {
      EntityManager em = null;
      try {
         em = getEntityManager();
         em.getTransaction().begin();
         Slotandcourses slotandcourses;
         try {
            slotandcourses = em.getReference(Slotandcourses.class, id);
            slotandcourses.getSlotandcoursesPK();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The slotandcourses with id " + id + " no longer exists.", enfe);
         }
         Slot slot = slotandcourses.getSlot();
         if (slot != null) {
            slot.getSlotandcoursesCollection().remove(slotandcourses);
            slot = em.merge(slot);
         }
         Course course = slotandcourses.getCourse();
         if (course != null) {
            course.getSlotandcoursesCollection().remove(slotandcourses);
            course = em.merge(course);
         }
         Student student = slotandcourses.getStudent();
         if (student != null) {
            student.getSlotandcoursesCollection().remove(slotandcourses);
            student = em.merge(student);
         }
         em.remove(slotandcourses);
         em.getTransaction().commit();
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public List<Slotandcourses> findSlotandcoursesEntities() {
      return findSlotandcoursesEntities(true, -1, -1);
   }

   public List<Slotandcourses> findSlotandcoursesEntities(int maxResults, int firstResult) {
      return findSlotandcoursesEntities(false, maxResults, firstResult);
   }

   private List<Slotandcourses> findSlotandcoursesEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(Slotandcourses.class));
         Query q = em.createQuery(cq);
         if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
         }
         return q.getResultList();
      } finally {
         em.close();
      }
   }

   public Slotandcourses findSlotandcourses(SlotandcoursesPK id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(Slotandcourses.class, id);
      } finally {
         em.close();
      }
   }

   public int getSlotandcoursesCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<Slotandcourses> rt = cq.from(Slotandcourses.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAControllerss;

import JPAControllerss.exceptions.IllegalOrphanException;
import JPAControllerss.exceptions.NonexistentEntityException;
import JPAControllerss.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import fourthadvancedproject.Slotandcourses;
import fourthadvancedproject.Student;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author melike
 */
public class StudentJpaController implements Serializable {

   public StudentJpaController(EntityManagerFactory emf) {
      this.emf = emf;
   }
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(Student student) throws PreexistingEntityException, Exception {
      if (student.getSlotandcoursesCollection() == null) {
         student.setSlotandcoursesCollection(new ArrayList<Slotandcourses>());
      }
      EntityManager em = null;
      try {
         em = getEntityManager();
         em.getTransaction().begin();
         Collection<Slotandcourses> attachedSlotandcoursesCollection = new ArrayList<Slotandcourses>();
         for (Slotandcourses slotandcoursesCollectionSlotandcoursesToAttach : student.getSlotandcoursesCollection()) {
            slotandcoursesCollectionSlotandcoursesToAttach = em.getReference(slotandcoursesCollectionSlotandcoursesToAttach.getClass(), slotandcoursesCollectionSlotandcoursesToAttach.getSlotandcoursesPK());
            attachedSlotandcoursesCollection.add(slotandcoursesCollectionSlotandcoursesToAttach);
         }
         student.setSlotandcoursesCollection(attachedSlotandcoursesCollection);
         em.persist(student);
         for (Slotandcourses slotandcoursesCollectionSlotandcourses : student.getSlotandcoursesCollection()) {
            Student oldStudentOfSlotandcoursesCollectionSlotandcourses = slotandcoursesCollectionSlotandcourses.getStudent();
            slotandcoursesCollectionSlotandcourses.setStudent(student);
            slotandcoursesCollectionSlotandcourses = em.merge(slotandcoursesCollectionSlotandcourses);
            if (oldStudentOfSlotandcoursesCollectionSlotandcourses != null) {
               oldStudentOfSlotandcoursesCollectionSlotandcourses.getSlotandcoursesCollection().remove(slotandcoursesCollectionSlotandcourses);
               oldStudentOfSlotandcoursesCollectionSlotandcourses = em.merge(oldStudentOfSlotandcoursesCollectionSlotandcourses);
            }
         }
         em.getTransaction().commit();
      } catch (Exception ex) {
         if (findStudent(student.getStudentnumber()) != null) {
            throw new PreexistingEntityException("Student " + student + " already exists.", ex);
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void edit(Student student) throws IllegalOrphanException, NonexistentEntityException, Exception {
      EntityManager em = null;
      try {
         em = getEntityManager();
         em.getTransaction().begin();
         Student persistentStudent = em.find(Student.class, student.getStudentnumber());
         Collection<Slotandcourses> slotandcoursesCollectionOld = persistentStudent.getSlotandcoursesCollection();
         Collection<Slotandcourses> slotandcoursesCollectionNew = student.getSlotandcoursesCollection();
         List<String> illegalOrphanMessages = null;
         for (Slotandcourses slotandcoursesCollectionOldSlotandcourses : slotandcoursesCollectionOld) {
            if (!slotandcoursesCollectionNew.contains(slotandcoursesCollectionOldSlotandcourses)) {
               if (illegalOrphanMessages == null) {
                  illegalOrphanMessages = new ArrayList<String>();
               }
               illegalOrphanMessages.add("You must retain Slotandcourses " + slotandcoursesCollectionOldSlotandcourses + " since its student field is not nullable.");
            }
         }
         if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
         }
         Collection<Slotandcourses> attachedSlotandcoursesCollectionNew = new ArrayList<Slotandcourses>();
         for (Slotandcourses slotandcoursesCollectionNewSlotandcoursesToAttach : slotandcoursesCollectionNew) {
            slotandcoursesCollectionNewSlotandcoursesToAttach = em.getReference(slotandcoursesCollectionNewSlotandcoursesToAttach.getClass(), slotandcoursesCollectionNewSlotandcoursesToAttach.getSlotandcoursesPK());
            attachedSlotandcoursesCollectionNew.add(slotandcoursesCollectionNewSlotandcoursesToAttach);
         }
         slotandcoursesCollectionNew = attachedSlotandcoursesCollectionNew;
         student.setSlotandcoursesCollection(slotandcoursesCollectionNew);
         student = em.merge(student);
         for (Slotandcourses slotandcoursesCollectionNewSlotandcourses : slotandcoursesCollectionNew) {
            if (!slotandcoursesCollectionOld.contains(slotandcoursesCollectionNewSlotandcourses)) {
               Student oldStudentOfSlotandcoursesCollectionNewSlotandcourses = slotandcoursesCollectionNewSlotandcourses.getStudent();
               slotandcoursesCollectionNewSlotandcourses.setStudent(student);
               slotandcoursesCollectionNewSlotandcourses = em.merge(slotandcoursesCollectionNewSlotandcourses);
               if (oldStudentOfSlotandcoursesCollectionNewSlotandcourses != null && !oldStudentOfSlotandcoursesCollectionNewSlotandcourses.equals(student)) {
                  oldStudentOfSlotandcoursesCollectionNewSlotandcourses.getSlotandcoursesCollection().remove(slotandcoursesCollectionNewSlotandcourses);
                  oldStudentOfSlotandcoursesCollectionNewSlotandcourses = em.merge(oldStudentOfSlotandcoursesCollectionNewSlotandcourses);
               }
            }
         }
         em.getTransaction().commit();
      } catch (Exception ex) {
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            String id = student.getStudentnumber();
            if (findStudent(id) == null) {
               throw new NonexistentEntityException("The student with id " + id + " no longer exists.");
            }
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
      EntityManager em = null;
      try {
         em = getEntityManager();
         em.getTransaction().begin();
         Student student;
         try {
            student = em.getReference(Student.class, id);
            student.getStudentnumber();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The student with id " + id + " no longer exists.", enfe);
         }
         List<String> illegalOrphanMessages = null;
         Collection<Slotandcourses> slotandcoursesCollectionOrphanCheck = student.getSlotandcoursesCollection();
         for (Slotandcourses slotandcoursesCollectionOrphanCheckSlotandcourses : slotandcoursesCollectionOrphanCheck) {
            if (illegalOrphanMessages == null) {
               illegalOrphanMessages = new ArrayList<String>();
            }
            illegalOrphanMessages.add("This Student (" + student + ") cannot be destroyed since the Slotandcourses " + slotandcoursesCollectionOrphanCheckSlotandcourses + " in its slotandcoursesCollection field has a non-nullable student field.");
         }
         if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
         }
         em.remove(student);
         em.getTransaction().commit();
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public List<Student> findStudentEntities() {
      return findStudentEntities(true, -1, -1);
   }

   public List<Student> findStudentEntities(int maxResults, int firstResult) {
      return findStudentEntities(false, maxResults, firstResult);
   }

   private List<Student> findStudentEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(Student.class));
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

   public Student findStudent(String id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(Student.class, id);
      } finally {
         em.close();
      }
   }

   public int getStudentCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<Student> rt = cq.from(Student.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}

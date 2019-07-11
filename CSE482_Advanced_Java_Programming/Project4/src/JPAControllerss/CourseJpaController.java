/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAControllerss;

import JPAControllerss.exceptions.IllegalOrphanException;
import JPAControllerss.exceptions.NonexistentEntityException;
import JPAControllerss.exceptions.PreexistingEntityException;
import fourthadvancedproject.Course;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import fourthadvancedproject.Slotandcourses;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author melike
 */
public class CourseJpaController implements Serializable {

   public CourseJpaController(EntityManagerFactory emf) {
      this.emf = emf;
   }
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(Course course) throws PreexistingEntityException, Exception {
      if (course.getSlotandcoursesCollection() == null) {
         course.setSlotandcoursesCollection(new ArrayList<Slotandcourses>());
      }
      EntityManager em = null;
      try {
         em = getEntityManager();
         em.getTransaction().begin();
         Collection<Slotandcourses> attachedSlotandcoursesCollection = new ArrayList<Slotandcourses>();
         for (Slotandcourses slotandcoursesCollectionSlotandcoursesToAttach : course.getSlotandcoursesCollection()) {
            slotandcoursesCollectionSlotandcoursesToAttach = em.getReference(slotandcoursesCollectionSlotandcoursesToAttach.getClass(), slotandcoursesCollectionSlotandcoursesToAttach.getSlotandcoursesPK());
            attachedSlotandcoursesCollection.add(slotandcoursesCollectionSlotandcoursesToAttach);
         }
         course.setSlotandcoursesCollection(attachedSlotandcoursesCollection);
         em.persist(course);
         for (Slotandcourses slotandcoursesCollectionSlotandcourses : course.getSlotandcoursesCollection()) {
            Course oldCourseOfSlotandcoursesCollectionSlotandcourses = slotandcoursesCollectionSlotandcourses.getCourse();
            slotandcoursesCollectionSlotandcourses.setCourse(course);
            slotandcoursesCollectionSlotandcourses = em.merge(slotandcoursesCollectionSlotandcourses);
            if (oldCourseOfSlotandcoursesCollectionSlotandcourses != null) {
               oldCourseOfSlotandcoursesCollectionSlotandcourses.getSlotandcoursesCollection().remove(slotandcoursesCollectionSlotandcourses);
               oldCourseOfSlotandcoursesCollectionSlotandcourses = em.merge(oldCourseOfSlotandcoursesCollectionSlotandcourses);
            }
         }
         em.getTransaction().commit();
      } catch (Exception ex) {
         if (findCourse(course.getCoursecode()) != null) {
            throw new PreexistingEntityException("Course " + course + " already exists.", ex);
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void edit(Course course) throws IllegalOrphanException, NonexistentEntityException, Exception {
      EntityManager em = null;
      try {
         em = getEntityManager();
         em.getTransaction().begin();
         Course persistentCourse = em.find(Course.class, course.getCoursecode());
         Collection<Slotandcourses> slotandcoursesCollectionOld = persistentCourse.getSlotandcoursesCollection();
         Collection<Slotandcourses> slotandcoursesCollectionNew = course.getSlotandcoursesCollection();
         List<String> illegalOrphanMessages = null;
         for (Slotandcourses slotandcoursesCollectionOldSlotandcourses : slotandcoursesCollectionOld) {
            if (!slotandcoursesCollectionNew.contains(slotandcoursesCollectionOldSlotandcourses)) {
               if (illegalOrphanMessages == null) {
                  illegalOrphanMessages = new ArrayList<String>();
               }
               illegalOrphanMessages.add("You must retain Slotandcourses " + slotandcoursesCollectionOldSlotandcourses + " since its course field is not nullable.");
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
         course.setSlotandcoursesCollection(slotandcoursesCollectionNew);
         course = em.merge(course);
         for (Slotandcourses slotandcoursesCollectionNewSlotandcourses : slotandcoursesCollectionNew) {
            if (!slotandcoursesCollectionOld.contains(slotandcoursesCollectionNewSlotandcourses)) {
               Course oldCourseOfSlotandcoursesCollectionNewSlotandcourses = slotandcoursesCollectionNewSlotandcourses.getCourse();
               slotandcoursesCollectionNewSlotandcourses.setCourse(course);
               slotandcoursesCollectionNewSlotandcourses = em.merge(slotandcoursesCollectionNewSlotandcourses);
               if (oldCourseOfSlotandcoursesCollectionNewSlotandcourses != null && !oldCourseOfSlotandcoursesCollectionNewSlotandcourses.equals(course)) {
                  oldCourseOfSlotandcoursesCollectionNewSlotandcourses.getSlotandcoursesCollection().remove(slotandcoursesCollectionNewSlotandcourses);
                  oldCourseOfSlotandcoursesCollectionNewSlotandcourses = em.merge(oldCourseOfSlotandcoursesCollectionNewSlotandcourses);
               }
            }
         }
         em.getTransaction().commit();
      } catch (Exception ex) {
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            String id = course.getCoursecode();
            if (findCourse(id) == null) {
               throw new NonexistentEntityException("The course with id " + id + " no longer exists.");
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
         Course course;
         try {
            course = em.getReference(Course.class, id);
            course.getCoursecode();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The course with id " + id + " no longer exists.", enfe);
         }
         List<String> illegalOrphanMessages = null;
         Collection<Slotandcourses> slotandcoursesCollectionOrphanCheck = course.getSlotandcoursesCollection();
         for (Slotandcourses slotandcoursesCollectionOrphanCheckSlotandcourses : slotandcoursesCollectionOrphanCheck) {
            if (illegalOrphanMessages == null) {
               illegalOrphanMessages = new ArrayList<String>();
            }
            illegalOrphanMessages.add("This Course (" + course + ") cannot be destroyed since the Slotandcourses " + slotandcoursesCollectionOrphanCheckSlotandcourses + " in its slotandcoursesCollection field has a non-nullable course field.");
         }
         if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
         }
         em.remove(course);
         em.getTransaction().commit();
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public List<Course> findCourseEntities() {
      return findCourseEntities(true, -1, -1);
   }

   public List<Course> findCourseEntities(int maxResults, int firstResult) {
      return findCourseEntities(false, maxResults, firstResult);
   }

   private List<Course> findCourseEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(Course.class));
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

   public Course findCourse(String id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(Course.class, id);
      } finally {
         em.close();
      }
   }

   public int getCourseCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<Course> rt = cq.from(Course.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}

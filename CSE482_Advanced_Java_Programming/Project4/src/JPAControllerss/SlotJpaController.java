/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPAControllerss;

import JPAControllerss.exceptions.IllegalOrphanException;
import JPAControllerss.exceptions.NonexistentEntityException;
import JPAControllerss.exceptions.PreexistingEntityException;
import fourthadvancedproject.Slot;
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
public class SlotJpaController implements Serializable {

   public SlotJpaController(EntityManagerFactory emf) {
      this.emf = emf;
   }
   private EntityManagerFactory emf = null;

   public EntityManager getEntityManager() {
      return emf.createEntityManager();
   }

   public void create(Slot slot) throws PreexistingEntityException, Exception {
      if (slot.getSlotandcoursesCollection() == null) {
         slot.setSlotandcoursesCollection(new ArrayList<Slotandcourses>());
      }
      EntityManager em = null;
      try {
         em = getEntityManager();
         em.getTransaction().begin();
         Collection<Slotandcourses> attachedSlotandcoursesCollection = new ArrayList<Slotandcourses>();
         for (Slotandcourses slotandcoursesCollectionSlotandcoursesToAttach : slot.getSlotandcoursesCollection()) {
            slotandcoursesCollectionSlotandcoursesToAttach = em.getReference(slotandcoursesCollectionSlotandcoursesToAttach.getClass(), slotandcoursesCollectionSlotandcoursesToAttach.getSlotandcoursesPK());
            attachedSlotandcoursesCollection.add(slotandcoursesCollectionSlotandcoursesToAttach);
         }
         slot.setSlotandcoursesCollection(attachedSlotandcoursesCollection);
         em.persist(slot);
         for (Slotandcourses slotandcoursesCollectionSlotandcourses : slot.getSlotandcoursesCollection()) {
            Slot oldSlotOfSlotandcoursesCollectionSlotandcourses = slotandcoursesCollectionSlotandcourses.getSlot();
            slotandcoursesCollectionSlotandcourses.setSlot(slot);
            slotandcoursesCollectionSlotandcourses = em.merge(slotandcoursesCollectionSlotandcourses);
            if (oldSlotOfSlotandcoursesCollectionSlotandcourses != null) {
               oldSlotOfSlotandcoursesCollectionSlotandcourses.getSlotandcoursesCollection().remove(slotandcoursesCollectionSlotandcourses);
               oldSlotOfSlotandcoursesCollectionSlotandcourses = em.merge(oldSlotOfSlotandcoursesCollectionSlotandcourses);
            }
         }
         em.getTransaction().commit();
      } catch (Exception ex) {
         if (findSlot(slot.getSlotcode()) != null) {
            throw new PreexistingEntityException("Slot " + slot + " already exists.", ex);
         }
         throw ex;
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public void edit(Slot slot) throws IllegalOrphanException, NonexistentEntityException, Exception {
      EntityManager em = null;
      try {
         em = getEntityManager();
         em.getTransaction().begin();
         Slot persistentSlot = em.find(Slot.class, slot.getSlotcode());
         Collection<Slotandcourses> slotandcoursesCollectionOld = persistentSlot.getSlotandcoursesCollection();
         Collection<Slotandcourses> slotandcoursesCollectionNew = slot.getSlotandcoursesCollection();
         List<String> illegalOrphanMessages = null;
         for (Slotandcourses slotandcoursesCollectionOldSlotandcourses : slotandcoursesCollectionOld) {
            if (!slotandcoursesCollectionNew.contains(slotandcoursesCollectionOldSlotandcourses)) {
               if (illegalOrphanMessages == null) {
                  illegalOrphanMessages = new ArrayList<String>();
               }
               illegalOrphanMessages.add("You must retain Slotandcourses " + slotandcoursesCollectionOldSlotandcourses + " since its slot field is not nullable.");
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
         slot.setSlotandcoursesCollection(slotandcoursesCollectionNew);
         slot = em.merge(slot);
         for (Slotandcourses slotandcoursesCollectionNewSlotandcourses : slotandcoursesCollectionNew) {
            if (!slotandcoursesCollectionOld.contains(slotandcoursesCollectionNewSlotandcourses)) {
               Slot oldSlotOfSlotandcoursesCollectionNewSlotandcourses = slotandcoursesCollectionNewSlotandcourses.getSlot();
               slotandcoursesCollectionNewSlotandcourses.setSlot(slot);
               slotandcoursesCollectionNewSlotandcourses = em.merge(slotandcoursesCollectionNewSlotandcourses);
               if (oldSlotOfSlotandcoursesCollectionNewSlotandcourses != null && !oldSlotOfSlotandcoursesCollectionNewSlotandcourses.equals(slot)) {
                  oldSlotOfSlotandcoursesCollectionNewSlotandcourses.getSlotandcoursesCollection().remove(slotandcoursesCollectionNewSlotandcourses);
                  oldSlotOfSlotandcoursesCollectionNewSlotandcourses = em.merge(oldSlotOfSlotandcoursesCollectionNewSlotandcourses);
               }
            }
         }
         em.getTransaction().commit();
      } catch (Exception ex) {
         String msg = ex.getLocalizedMessage();
         if (msg == null || msg.length() == 0) {
            String id = slot.getSlotcode();
            if (findSlot(id) == null) {
               throw new NonexistentEntityException("The slot with id " + id + " no longer exists.");
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
         Slot slot;
         try {
            slot = em.getReference(Slot.class, id);
            slot.getSlotcode();
         } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The slot with id " + id + " no longer exists.", enfe);
         }
         List<String> illegalOrphanMessages = null;
         Collection<Slotandcourses> slotandcoursesCollectionOrphanCheck = slot.getSlotandcoursesCollection();
         for (Slotandcourses slotandcoursesCollectionOrphanCheckSlotandcourses : slotandcoursesCollectionOrphanCheck) {
            if (illegalOrphanMessages == null) {
               illegalOrphanMessages = new ArrayList<String>();
            }
            illegalOrphanMessages.add("This Slot (" + slot + ") cannot be destroyed since the Slotandcourses " + slotandcoursesCollectionOrphanCheckSlotandcourses + " in its slotandcoursesCollection field has a non-nullable slot field.");
         }
         if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
         }
         em.remove(slot);
         em.getTransaction().commit();
      } finally {
         if (em != null) {
            em.close();
         }
      }
   }

   public List<Slot> findSlotEntities() {
      return findSlotEntities(true, -1, -1);
   }

   public List<Slot> findSlotEntities(int maxResults, int firstResult) {
      return findSlotEntities(false, maxResults, firstResult);
   }

   private List<Slot> findSlotEntities(boolean all, int maxResults, int firstResult) {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         cq.select(cq.from(Slot.class));
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

   public Slot findSlot(String id) {
      EntityManager em = getEntityManager();
      try {
         return em.find(Slot.class, id);
      } finally {
         em.close();
      }
   }

   public int getSlotCount() {
      EntityManager em = getEntityManager();
      try {
         CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
         Root<Slot> rt = cq.from(Slot.class);
         cq.select(em.getCriteriaBuilder().count(rt));
         Query q = em.createQuery(cq);
         return ((Long) q.getSingleResult()).intValue();
      } finally {
         em.close();
      }
   }
   
}

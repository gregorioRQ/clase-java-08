/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelos.Veterinarios;

/**
 *
 * @author PC
 */
public class VeterinariosJpaController implements Serializable {

    public VeterinariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public VeterinariosJpaController() {
         emf = Persistence.createEntityManagerFactory("cadenaVetJPAPU");
    }
    
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Veterinarios veterinarios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(veterinarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Veterinarios veterinarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            veterinarios = em.merge(veterinarios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = veterinarios.getId();
                if (findVeterinarios(id) == null) {
                    throw new NonexistentEntityException("The veterinarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Veterinarios veterinarios;
            try {
                veterinarios = em.getReference(Veterinarios.class, id);
                veterinarios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The veterinarios with id " + id + " no longer exists.", enfe);
            }
            em.remove(veterinarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Veterinarios> findVeterinariosEntities() {
        return findVeterinariosEntities(true, -1, -1);
    }

    public List<Veterinarios> findVeterinariosEntities(int maxResults, int firstResult) {
        return findVeterinariosEntities(false, maxResults, firstResult);
    }

    private List<Veterinarios> findVeterinariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Veterinarios.class));
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

    public Veterinarios findVeterinarios(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Veterinarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getVeterinariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Veterinarios> rt = cq.from(Veterinarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

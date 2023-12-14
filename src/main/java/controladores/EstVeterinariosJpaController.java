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
import modelos.EstVeterinarios;

/**
 *
 * @author PC
 */
public class EstVeterinariosJpaController implements Serializable {

    public EstVeterinariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EstVeterinariosJpaController() {
        emf = Persistence.createEntityManagerFactory("cadenaVetJPAPU");
    }
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstVeterinarios estVeterinarios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estVeterinarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstVeterinarios estVeterinarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estVeterinarios = em.merge(estVeterinarios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = estVeterinarios.getId();
                if (findEstVeterinarios(id) == null) {
                    throw new NonexistentEntityException("The estVeterinarios with id " + id + " no longer exists.");
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
            EstVeterinarios estVeterinarios;
            try {
                estVeterinarios = em.getReference(EstVeterinarios.class, id);
                estVeterinarios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estVeterinarios with id " + id + " no longer exists.", enfe);
            }
            em.remove(estVeterinarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstVeterinarios> findEstVeterinariosEntities() {
        return findEstVeterinariosEntities(true, -1, -1);
    }

    public List<EstVeterinarios> findEstVeterinariosEntities(int maxResults, int firstResult) {
        return findEstVeterinariosEntities(false, maxResults, firstResult);
    }

    private List<EstVeterinarios> findEstVeterinariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstVeterinarios.class));
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

    public EstVeterinarios findEstVeterinarios(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstVeterinarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstVeterinariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstVeterinarios> rt = cq.from(EstVeterinarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

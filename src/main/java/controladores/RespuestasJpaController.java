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
import modelos.Preguntas;
import modelos.Respuestas;

/**
 *
 * @author PC
 */
public class RespuestasJpaController implements Serializable {

    public RespuestasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public RespuestasJpaController() {
        emf = Persistence.createEntityManagerFactory("cadenaVetJPAPU");
    }
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Respuestas respuestas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preguntas pregunta = respuestas.getPregunta();
            if (pregunta != null) {
                pregunta = em.getReference(pregunta.getClass(), pregunta.getId());
                respuestas.setPregunta(pregunta);
            }
            em.persist(respuestas);
            if (pregunta != null) {
                pregunta.getListaRespuestas().add(respuestas);
                pregunta = em.merge(pregunta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Respuestas respuestas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Respuestas persistentRespuestas = em.find(Respuestas.class, respuestas.getId());
            Preguntas preguntaOld = persistentRespuestas.getPregunta();
            Preguntas preguntaNew = respuestas.getPregunta();
            if (preguntaNew != null) {
                preguntaNew = em.getReference(preguntaNew.getClass(), preguntaNew.getId());
                respuestas.setPregunta(preguntaNew);
            }
            respuestas = em.merge(respuestas);
            if (preguntaOld != null && !preguntaOld.equals(preguntaNew)) {
                preguntaOld.getListaRespuestas().remove(respuestas);
                preguntaOld = em.merge(preguntaOld);
            }
            if (preguntaNew != null && !preguntaNew.equals(preguntaOld)) {
                preguntaNew.getListaRespuestas().add(respuestas);
                preguntaNew = em.merge(preguntaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = respuestas.getId();
                if (findRespuestas(id) == null) {
                    throw new NonexistentEntityException("The respuestas with id " + id + " no longer exists.");
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
            Respuestas respuestas;
            try {
                respuestas = em.getReference(Respuestas.class, id);
                respuestas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestas with id " + id + " no longer exists.", enfe);
            }
            Preguntas pregunta = respuestas.getPregunta();
            if (pregunta != null) {
                pregunta.getListaRespuestas().remove(respuestas);
                pregunta = em.merge(pregunta);
            }
            em.remove(respuestas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Respuestas> findRespuestasEntities() {
        return findRespuestasEntities(true, -1, -1);
    }

    public List<Respuestas> findRespuestasEntities(int maxResults, int firstResult) {
        return findRespuestasEntities(false, maxResults, firstResult);
    }

    private List<Respuestas> findRespuestasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Respuestas.class));
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

    public Respuestas findRespuestas(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Respuestas.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Respuestas> rt = cq.from(Respuestas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

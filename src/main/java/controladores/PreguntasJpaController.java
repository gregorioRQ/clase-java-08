/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelos.Respuestas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelos.Preguntas;

/**
 *
 * @author PC
 */
public class PreguntasJpaController implements Serializable {

    public PreguntasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public PreguntasJpaController() {
        emf = Persistence.createEntityManagerFactory("cadenaVetJPAPU");
    }
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Preguntas preguntas) {
        if (preguntas.getListaRespuestas() == null) {
            preguntas.setListaRespuestas(new ArrayList<Respuestas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Respuestas> attachedListaRespuestas = new ArrayList<Respuestas>();
            for (Respuestas listaRespuestasRespuestasToAttach : preguntas.getListaRespuestas()) {
                listaRespuestasRespuestasToAttach = em.getReference(listaRespuestasRespuestasToAttach.getClass(), listaRespuestasRespuestasToAttach.getId());
                attachedListaRespuestas.add(listaRespuestasRespuestasToAttach);
            }
            preguntas.setListaRespuestas(attachedListaRespuestas);
            em.persist(preguntas);
            for (Respuestas listaRespuestasRespuestas : preguntas.getListaRespuestas()) {
                Preguntas oldPreguntaOfListaRespuestasRespuestas = listaRespuestasRespuestas.getPregunta();
                listaRespuestasRespuestas.setPregunta(preguntas);
                listaRespuestasRespuestas = em.merge(listaRespuestasRespuestas);
                if (oldPreguntaOfListaRespuestasRespuestas != null) {
                    oldPreguntaOfListaRespuestasRespuestas.getListaRespuestas().remove(listaRespuestasRespuestas);
                    oldPreguntaOfListaRespuestasRespuestas = em.merge(oldPreguntaOfListaRespuestasRespuestas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Preguntas preguntas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preguntas persistentPreguntas = em.find(Preguntas.class, preguntas.getId());
            List<Respuestas> listaRespuestasOld = persistentPreguntas.getListaRespuestas();
            List<Respuestas> listaRespuestasNew = preguntas.getListaRespuestas();
            List<Respuestas> attachedListaRespuestasNew = new ArrayList<Respuestas>();
            for (Respuestas listaRespuestasNewRespuestasToAttach : listaRespuestasNew) {
                listaRespuestasNewRespuestasToAttach = em.getReference(listaRespuestasNewRespuestasToAttach.getClass(), listaRespuestasNewRespuestasToAttach.getId());
                attachedListaRespuestasNew.add(listaRespuestasNewRespuestasToAttach);
            }
            listaRespuestasNew = attachedListaRespuestasNew;
            preguntas.setListaRespuestas(listaRespuestasNew);
            preguntas = em.merge(preguntas);
            for (Respuestas listaRespuestasOldRespuestas : listaRespuestasOld) {
                if (!listaRespuestasNew.contains(listaRespuestasOldRespuestas)) {
                    listaRespuestasOldRespuestas.setPregunta(null);
                    listaRespuestasOldRespuestas = em.merge(listaRespuestasOldRespuestas);
                }
            }
            for (Respuestas listaRespuestasNewRespuestas : listaRespuestasNew) {
                if (!listaRespuestasOld.contains(listaRespuestasNewRespuestas)) {
                    Preguntas oldPreguntaOfListaRespuestasNewRespuestas = listaRespuestasNewRespuestas.getPregunta();
                    listaRespuestasNewRespuestas.setPregunta(preguntas);
                    listaRespuestasNewRespuestas = em.merge(listaRespuestasNewRespuestas);
                    if (oldPreguntaOfListaRespuestasNewRespuestas != null && !oldPreguntaOfListaRespuestasNewRespuestas.equals(preguntas)) {
                        oldPreguntaOfListaRespuestasNewRespuestas.getListaRespuestas().remove(listaRespuestasNewRespuestas);
                        oldPreguntaOfListaRespuestasNewRespuestas = em.merge(oldPreguntaOfListaRespuestasNewRespuestas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = preguntas.getId();
                if (findPreguntas(id) == null) {
                    throw new NonexistentEntityException("The preguntas with id " + id + " no longer exists.");
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
            Preguntas preguntas;
            try {
                preguntas = em.getReference(Preguntas.class, id);
                preguntas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The preguntas with id " + id + " no longer exists.", enfe);
            }
            List<Respuestas> listaRespuestas = preguntas.getListaRespuestas();
            for (Respuestas listaRespuestasRespuestas : listaRespuestas) {
                listaRespuestasRespuestas.setPregunta(null);
                listaRespuestasRespuestas = em.merge(listaRespuestasRespuestas);
            }
            em.remove(preguntas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Preguntas> findPreguntasEntities() {
        return findPreguntasEntities(true, -1, -1);
    }

    public List<Preguntas> findPreguntasEntities(int maxResults, int firstResult) {
        return findPreguntasEntities(false, maxResults, firstResult);
    }

    private List<Preguntas> findPreguntasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Preguntas.class));
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

    public Preguntas findPreguntas(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Preguntas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreguntasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Preguntas> rt = cq.from(Preguntas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

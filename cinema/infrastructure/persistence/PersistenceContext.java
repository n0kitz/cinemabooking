package io.fnmz.cinema.infrastructure.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// Spring Transactional
public class PersistenceContext {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("cinemaPU");

    private EntityManager em;

    public void open() {
        em = emf.createEntityManager();
    }

    public void begin() {
        em.getTransaction().begin();
    }

    public void commit() {
        em.getTransaction().commit();
    }

    public void rollback() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

    public EntityManager em() {
        return em;
    }

    public void close() {
        if (em != null && em.isOpen()) em.close();
    }

}

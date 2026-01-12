package io.fnmz.cinema.repository;

import io.fnmz.cinema.domain.entities.Auditorium;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class AuditoriumRepository {

    private final EntityManager em;

    public AuditoriumRepository(EntityManager em) { this.em = em; }

    public List<Auditorium> findByCinema(Long cinemaId) {
        return em.createQuery(
                        "SELECT a FROM Auditorium a WHERE a.cinema.id = :cid", Auditorium.class)
                .setParameter("cid", cinemaId)
                .getResultList();
    }

    public Optional<Auditorium> findById(Long id) {
        return Optional.ofNullable(em.find(Auditorium.class, id));
    }

    public void save(Auditorium auditorium) {
        if (auditorium.getId() == null) em.persist(auditorium);
        else em.merge(auditorium);
    }

    public void delete(Auditorium auditorium) {
        Auditorium attached = em.contains(auditorium) ? auditorium : em.merge(auditorium);
        em.remove(attached);
    }
}

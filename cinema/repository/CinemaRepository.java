package io.fnmz.cinema.repository;

import io.fnmz.cinema.domain.entities.Cinema;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class CinemaRepository {

    private final EntityManager em;

    public CinemaRepository(EntityManager em) {
        this.em = em;
    }

    public List<Cinema> findAll() {
        return em.createQuery("SELECT c FROM Cinema c", Cinema.class)
                .getResultList();
    }

    public Optional<Cinema> findById(Long id) {
        return Optional.ofNullable(em.find(Cinema.class, id));
    }

    public void save(Cinema cinema) {
        if (cinema.getId() == null) em.persist(cinema);
        else em.merge(cinema);
    }

    public void delete(Cinema cinema) {
        Cinema attached = em.contains(cinema) ? cinema : em.merge(cinema);
        em.remove(attached);
    }
}

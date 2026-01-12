package io.fnmz.cinema.repository;

import io.fnmz.cinema.domain.entities.Showtime;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ShowtimeRepository {

    private final EntityManager em;

    public ShowtimeRepository(EntityManager em) {
        this.em = em;
    }

    public List<Showtime> findByCinema(Long cinemaId) {
        return em.createQuery(
                        "select s from Showtime s where s.cinema.id = :cid",
                        Showtime.class
                ).setParameter("cid", cinemaId)
                .getResultList();
    }

    public void save(Showtime showtime) {
        em.persist(showtime);
    }
}

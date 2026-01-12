package io.fnmz.cinema.repository;

import io.fnmz.cinema.domain.entities.Movie;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class MovieRepository {

    private final EntityManager em;

    public MovieRepository(EntityManager em) {
        this.em = em;
    }

    public List<Movie> findAll() {
        return em.createQuery(
                "select m from Movie m", Movie.class
        ).getResultList();
    }

    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(em.find(Movie.class, id));
    }

    public List<Movie> findByCinema(Long cinemaId) {
        return em.createQuery(
                        "select m from Movie m where m.cinema.id = :cid",
                        Movie.class
                ).setParameter("cid", cinemaId)
                .getResultList();
    }

    public void save(Movie movie) {
        if (movie.getId() == null) {
            em.persist(movie);
        } else {
            em.merge(movie);
        }
    }

    public void delete(Movie movie) {
        em.remove(em.contains(movie) ? movie : em.merge(movie));
    }
}

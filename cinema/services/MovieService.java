package io.fnmz.cinema.services;

import io.fnmz.cinema.domain.entities.Cinema;
import io.fnmz.cinema.domain.entities.Movie;
import io.fnmz.cinema.infrastructure.persistence.PersistenceContext;
import io.fnmz.cinema.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

public class MovieService {

    private final MovieRepository repo;
    private final PersistenceContext ctx;

    public MovieService(MovieRepository repo, PersistenceContext ctx) {
        this.repo = repo;
        this.ctx = ctx;
    }

    public List<Movie> listAll() {
        return repo.findAll();
    }

    public Optional<Movie> findById(Long id) {
        return repo.findById(id);
    }

    public List<Movie> findByCinema(Long cinemaId) {
        return repo.findByCinema(cinemaId);
    }

    public void create(Cinema cinema, Movie movie) {
        ctx.begin();
        try {
            cinema.addMovie(movie);
            repo.save(movie);
            ctx.commit();
        } catch (Exception e) {
            ctx.rollback();
            throw e;
        }
    }

    public void delete(Movie movie) {
        ctx.begin();
        try {
            repo.delete(movie);
            ctx.commit();
        } catch (Exception e) {
            ctx.rollback();
            throw e;
        }
    }
}
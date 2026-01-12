package io.fnmz.cinema.services;

import io.fnmz.cinema.domain.entities.Cinema;
import io.fnmz.cinema.infrastructure.persistence.PersistenceContext;
import io.fnmz.cinema.repository.CinemaRepository;

import java.util.List;

public class CinemaService {

    private final CinemaRepository repo;
    private final PersistenceContext ctx;

    public CinemaService(CinemaRepository repo, PersistenceContext ctx) {
        this.repo = repo;
        this.ctx = ctx;
    }

    public List<Cinema> listAll() {
        return repo.findAll();
    }

    public void create(String name, String city) {
        ctx.begin();
        try {
            repo.save(new Cinema(name, city));
            ctx.commit();
        } catch (Exception e) {
            ctx.rollback();
            throw e;
        }
    }

    public void rename(Cinema cinema, String newName) {
        ctx.begin();
        try {
            cinema.setName(newName);
            repo.save(cinema);
            ctx.commit();
        } catch (Exception e) {
            ctx.rollback();
            throw e;
        }
    }

    public void delete(Cinema cinema) {
        ctx.begin();
        try {
            repo.delete(cinema);
            ctx.commit();
        } catch (Exception e) {
            ctx.rollback();
            throw e;
        }
    }
}

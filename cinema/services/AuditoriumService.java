package io.fnmz.cinema.services;

import io.fnmz.cinema.domain.entities.Auditorium;
import io.fnmz.cinema.domain.entities.Cinema;
import io.fnmz.cinema.infrastructure.persistence.PersistenceContext;
import io.fnmz.cinema.repository.AuditoriumRepository;

import java.util.List;
import java.util.Optional;

public class AuditoriumService {

    private final AuditoriumRepository repo;
    private final PersistenceContext ctx;

    public AuditoriumService(AuditoriumRepository repo, PersistenceContext ctx) {
        this.repo = repo;
        this.ctx = ctx;
    }

    public Optional<Auditorium> findById(Long id) {
        return repo.findById(id);
    }

    public List<Auditorium> findByCinema(Long cinemaId) {
        return repo.findByCinema(cinemaId);
    }

    public void create(Cinema cinema, Auditorium auditorium) {
        ctx.begin();
        cinema.addAuditorium(auditorium);
        repo.save(auditorium);
        ctx.commit();
    }

    public void update(Auditorium a) {
        ctx.begin();
        repo.save(a);
        ctx.commit();
    }

    public void delete(Auditorium a) {
        ctx.begin();
        repo.delete(a);
        ctx.commit();
    }
}

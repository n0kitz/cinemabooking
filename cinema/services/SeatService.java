package io.fnmz.cinema.services;

import io.fnmz.cinema.domain.entities.Seat;
import io.fnmz.cinema.repository.SeatRepository;

import java.util.List;

public class SeatService {

    private final SeatRepository repo;

    public SeatService(SeatRepository repo) {
        this.repo = repo;
    }

    public List<Seat> findByShowtime(Long showtimeId) {
        return repo.findByShowtime(showtimeId);
    }

    public void save(Seat seat) {
        repo.save(seat);
    }
}

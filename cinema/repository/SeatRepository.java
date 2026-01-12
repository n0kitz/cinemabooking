package io.fnmz.cinema.repository;

import io.fnmz.cinema.domain.entities.Seat;
import jakarta.persistence.EntityManager;

import java.util.List;

public class SeatRepository {

    private final EntityManager em;

    public SeatRepository(EntityManager em) {
        this.em = em;
    }

    public List<Seat> findByShowtime(Long showtimeId) {
        return em.createQuery(
                        "SELECT s FROM Seat s WHERE s.showtime.id = :sid ORDER BY s.rowNumber, s.seatNumber",
                        Seat.class)
                .setParameter("sid", showtimeId)
                .getResultList();
    }
    public Seat findByRowAndSeatAndShowtime(int row, int seat, Long showtimeId) {
        List<Seat> results = em.createQuery(
                        "SELECT s FROM Seat s WHERE s.rowNumber = :row AND s.seatNumber = :seat AND s.showtime.id = :sid",
                        Seat.class)
                .setParameter("row", row)
                .setParameter("seat", seat)
                .setParameter("sid", showtimeId)
                .getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public void save(Seat seat) {
        if (seat.getId() == null) {
            em.persist(seat);
        } else {
            em.merge(seat);
        }
    }
}

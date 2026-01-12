package io.fnmz.cinema.repository;

import io.fnmz.cinema.domain.entities.Seat;
import io.fnmz.cinema.domain.entities.Showtime;
import jakarta.persistence.EntityManager;

import java.util.List;

public class SoldSeatRepository {

    public boolean isSeatSold(EntityManager em, Showtime showtime, Seat seat) {
        List<Seat> list = em.createQuery(
                        "SELECT s FROM Seat s WHERE s.showtime.id = :sid AND s.rowNumber = :row AND s.seatNumber = :num",
                        Seat.class)
                .setParameter("sid", showtime.getId())
                .setParameter("row", seat.getRowNumber())
                .setParameter("num", seat.getSeatNumber())
                .getResultList();
        return !list.isEmpty() && list.get(0).isBooked();
    }
}

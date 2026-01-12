package io.fnmz.cinema.services;

import io.fnmz.cinema.domain.entities.*;
import io.fnmz.cinema.infrastructure.persistence.PersistenceContext;
import io.fnmz.cinema.repository.SeatRepository;
import io.fnmz.cinema.repository.ShowtimeRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ShowtimeService {

    private final ShowtimeRepository showtimeRepo;
    private final SeatRepository seatRepo;
    private final PersistenceContext ctx;

    public ShowtimeService(ShowtimeRepository showtimeRepo,
                           SeatRepository seatRepo,
                           PersistenceContext ctx) {
        this.showtimeRepo = showtimeRepo;
        this.seatRepo = seatRepo;
        this.ctx = ctx;
    }

    public List<Showtime> findByCinema(Long cinemaId) {
        return showtimeRepo.findByCinema(cinemaId);
    }

    public String getAllShowtimes(Long cinemaId) {
        List<Showtime> showtimes = showtimeRepo.findByCinema(cinemaId);
        return showtimes.stream()
                .map(st -> st.getId() + ". " + st.getMovie().getTitle() + " at " + st.getStartTime())
                .collect(Collectors.joining("\n"));
    }

    public String getAvailableSeats(Long showtimeId) {
        Showtime showtime = showtimeRepo.findById(showtimeId)
                .orElseThrow(() -> new IllegalArgumentException("Showtime not found"));

        return showtime.getSeats().stream()
                .filter(seat -> !seat.isBooked())
                .map(seat -> "Row " + seat.getRowNumber() + ", Seat " + seat.getSeatNumber() +
                        " (" + seat.getCategory() + " - $" + seat.getPrice() + ")")
                .collect(Collectors.joining("\n"));
    }

    public void create(Showtime showtime) {
        ctx.begin();
        try {
            showtimeRepo.save(showtime);
            generateSeats(showtime);
            ctx.commit();
        } catch (Exception e) {
            ctx.rollback();
            throw e;
        }
    }

    private void generateSeats(Showtime showtime) {
        Auditorium auditorium = showtime.getAuditorium();

        for (int row = 1; row <= auditorium.getRows(); row++) {
            for (int seatNum = 1; seatNum <= auditorium.getSeatsPerRow(); seatNum++) {

                PriceCategory category;
                if (row <= 2) category = PriceCategory.VIP;
                else if (row <= 4) category = PriceCategory.PREMIUM;
                else category = PriceCategory.STANDARD;

                Seat seat = new Seat(row, seatNum, showtime, category);
                showtime.addSeat(seat);
                seatRepo.save(seat);
            }
        }
    }
}

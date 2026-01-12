package io.fnmz.cinema.services;

import io.fnmz.cinema.domain.entities.Seat;
import java.util.ArrayList;
import java.util.List;

public class BookingCart {

    private final List<Seat> seats = new ArrayList<>();

    public void addSeat(Seat seat) {
        seats.add(seat);
        seat.setSelected(true);
    }

    public void removeSeat(Seat seat) {
        seats.remove(seat);
        seat.setSelected(false);
    }

    public List<Seat> getSeats() { return seats; }
    public boolean isEmpty() { return seats.isEmpty(); }
    public double subtotal() { return seats.stream().mapToDouble(Seat::getPrice).sum(); }
    public void clear() { seats.forEach(s -> s.setSelected(false)); seats.clear(); }
}

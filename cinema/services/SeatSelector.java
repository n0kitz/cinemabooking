package io.fnmz.cinema.services;

import io.fnmz.cinema.domain.entities.Seat;

import java.util.List;

public class SeatSelector {

    private final BookingCart cart;

    public SeatSelector(BookingCart cart) {
        this.cart = cart;
    }

    public void handle(String input, List<Seat> seats) {
        String[] parts = input.split("-");
        if (parts.length != 2) {
            System.out.println("Invalid format. Use row-seat (e.g., 2-5).");
            return;
        }

        try {
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            Seat seat = seats.stream()
                    .filter(s -> s.getRowNumber() == row && s.getSeatNumber() == col)
                    .findFirst()
                    .orElse(null);

            if (seat == null) {
                System.out.println("Seat does not exist.");
                return;
            }

            if (seat.isBooked()) {
                System.out.println("Seat already booked.");
                return;
            }

            cart.addSeat(seat);
            System.out.printf("Seat selected: Row %d Seat %d (%.2f) Category: %s%n",
                    row, col, seat.getPrice(), seat.getCategory());

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Numbers only.");
        }
    }
}

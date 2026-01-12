package io.fnmz.cinema.ui.view;

import io.fnmz.cinema.domain.entities.Seat;
import io.fnmz.cinema.domain.entities.Auditorium;
import io.fnmz.cinema.services.BookingCart;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookingView {

    public void showSeating(List<Seat> seats, Auditorium auditorium) {
        if (seats.isEmpty()) {
            System.out.println("No seats available for this showtime.");
            return;
        }

        Map<Integer, List<Seat>> rows = seats.stream()
                .collect(Collectors.groupingBy(Seat::getRowNumber));

        System.out.println("\n=== SEATING ==="); //show where the screen is

        for (int row = 1; row <= auditorium.getRows(); row++) {
            List<Seat> rowSeats = rows.get(row);
            if (rowSeats == null) continue;

            System.out.printf("Row %d: ", row);

            for (Seat seat : rowSeats) {
                String seatDisplay = seat.isBooked() ? "(x)" :
                        seat.isSelected() ? "[S]" : "[ ]";
                System.out.printf("%s", seatDisplay);
            }
            System.out.print("  ");
            System.out.printf("Category: %s, Price: %.2f%n",
                    rowSeats.get(0).getCategory(),
                    rowSeats.get(0).getPrice());
        }

        System.out.println("\nLegend: [ ] = available, [S] = selected, (x) = booked");
    }

    public void showCart(BookingCart cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("\n=== CURRENT SELECTION ===");
        for (Seat seat : cart.getSeats()) {
            System.out.printf("Row %d Seat %d | Category: %s | Price: %.2f%n",
                    seat.getRowNumber(), seat.getSeatNumber(),
                    seat.getCategory(), seat.getPrice());
        }
        System.out.printf("Subtotal: %.2f%n", cart.subtotal());
    }
}

package io.fnmz.cinema.services;

import io.fnmz.cinema.domain.entities.*;
import io.fnmz.cinema.ui.view.BookingView;

import java.util.List;
import java.util.Scanner;

public class BookingService {

    private final SeatService seatService;
    private final Scanner scanner = new Scanner(System.in);
    private final BookingView view = new BookingView();

    public BookingService(SeatService seatService) { this.seatService = seatService; }

    public void startBooking(Showtime showtime) {
        List<Seat> seats = seatService.findByShowtime(showtime.getId());
        BookingCart cart = new BookingCart();
        boolean done = false;

        while (!done) {
            view.showSeating(seats, showtime.getAuditorium());

            System.out.print("Enter seat (row-seat) or B for menu: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("B")) {
                done = bookingMenu(cart, showtime);
                continue;
            }
            handleSeatSelection(input, seats, cart);
        }
    }

    private void handleSeatSelection(String input, List<Seat> seats, BookingCart cart) {
        try {
            String[] parts = input.split("-");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            Seat seat = seats.stream()
                    .filter(s -> s.getRowNumber() == row && s.getSeatNumber() == col)
                    .findFirst().orElse(null);

            if (seat == null) { System.out.println("Seat does not exist."); return; }
            if (seat.isBooked()) { System.out.println("Seat already booked."); return; }

            cart.addSeat(seat);
            System.out.printf("Seat selected: Row %d Seat %d | Category: %s | Price: %.2f%n",
                    row, col, seat.getCategory(), seat.getPrice());

        } catch (Exception e) { System.out.println("Invalid input."); }
    }

    private boolean bookingMenu(BookingCart cart, Showtime showtime) {
        if (cart.isEmpty()) { System.out.println("No seats selected."); return false; }
        while (true) {
            view.showCart(cart);
            System.out.println("1) Add | 2) Remove | 3) Checkout | B) Back");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": return false;
                case "2": removeSeat(cart); break;
                case "3": checkout(cart, showtime); return true;
                case "B": case "b": return false;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void removeSeat(BookingCart cart) {
        view.showCart(cart);
        System.out.print("Enter seat to remove (row-seat): ");
        String input = scanner.nextLine();
        try {
            String[] parts = input.split("-");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            cart.getSeats().stream()
                    .filter(s -> s.getRowNumber() == row && s.getSeatNumber() == col)
                    .findFirst().ifPresent(cart::removeSeat);
        } catch (Exception ignored) {}
    }

    private void checkout(BookingCart cart, Showtime showtime) {
        view.showCart(cart);
        System.out.println("Select discount:");
        for (DiscountType d : DiscountType.values())
            System.out.printf("%d) %s (%d%%)%n", d.ordinal(), d.name(), d.percent());
        int idx = 0;
        try { idx = Integer.parseInt(scanner.nextLine()); } catch (Exception ignored) {}
        DiscountType discount = DiscountType.values()[Math.min(idx, DiscountType.values().length -1)];

        double subtotal = cart.subtotal();
        double total = subtotal * (1 - discount.percent()/100.0);
        System.out.printf("Subtotal: %.2f | Discount: %s | Total: %.2f%n",
                subtotal, discount.name(), total);

        cart.getSeats().forEach(s -> { s.setBooked(true); s.setSelected(false); seatService.save(s); });
        cart.clear();
        System.out.println("Booking confirmed");
    }
}

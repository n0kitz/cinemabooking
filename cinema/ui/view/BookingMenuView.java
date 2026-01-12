package io.fnmz.cinema.ui.view;

import io.fnmz.cinema.domain.entities.DiscountType;
import io.fnmz.cinema.domain.entities.Seat;
import io.fnmz.cinema.domain.entities.Showtime;
import io.fnmz.cinema.services.BookingCart;
import io.fnmz.cinema.services.SeatService;

import java.util.Locale;
import java.util.Scanner;

public class BookingMenuView {

    private final BookingCart cart;
    private final Showtime showtime;
    private final SeatService seatService;
    private final BookingView view;
    private final Scanner scanner = new Scanner(System.in);

    public BookingMenuView(BookingCart cart, Showtime showtime, SeatService seatService, BookingView view) {
        this.cart = cart;
        this.showtime = showtime;
        this.seatService = seatService;
        this.view = view;
    }

    public boolean open() {
        if (cart.isEmpty()) {
            System.out.println("No seats selected yet.");
            return false;
        }

        while (true) {
            view.showCart(cart);

            System.out.println("\n--- Booking Menu ---");
            System.out.println("1) Add another seat");
            System.out.println("2) Remove a seat");
            System.out.println("3) Checkout");
            System.out.println("B) Back to seating");

            String choice = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
            switch (choice) {
                case "1": return false;
                case "2": removeSeatFromCart(); break;
                case "3": checkout(); return true;
                case "B": return false;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void removeSeatFromCart() {
        view.showCart(cart);
        System.out.print("Enter seat to remove (row-seat): ");
        String input = scanner.nextLine().trim();

        String[] parts = input.split("-");
        if (parts.length != 2) return;

        try {
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            Seat seat = cart.getSeats().stream()
                    .filter(s -> s.getRowNumber() == row && s.getSeatNumber() == col)
                    .findFirst()
                    .orElse(null);
            if (seat != null) cart.removeSeat(seat);

        } catch (NumberFormatException ignored) {}
    }

    private void checkout() {
        view.showCart(cart);

        System.out.println("Enter discount type:");
        for (DiscountType type : DiscountType.values()) {
            System.out.printf("%d) %s (%d%%)%n", type.ordinal(), type.name(), type.percent());
        }
        System.out.print("> ");
        String input = scanner.nextLine();
        int idx = 0;
        try { idx = Integer.parseInt(input); } catch (Exception ignored) {}
        DiscountType discount = DiscountType.values()[Math.min(idx, DiscountType.values().length -1)];

        double subtotal = cart.subtotal();
        double total = subtotal * (1 - discount.percent()/100.0);

        System.out.printf("\n--- Checkout ---\nSubtotal: %.2f\nDiscount: %s (%d%%)\nTotal: %.2f%n",
                subtotal, discount.name(), discount.percent(), total);

        cart.getSeats().forEach(seat -> {
            seat.setBooked(true);
            seat.setSelected(false);
            seatService.save(seat);
        });
        cart.clear();

        System.out.println("Booking confirmed");
    }
}

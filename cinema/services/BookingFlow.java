package io.fnmz.cinema.services;

import io.fnmz.cinema.domain.entities.Seat;
import io.fnmz.cinema.domain.entities.Showtime;
import io.fnmz.cinema.ui.view.BookingMenuView;
import io.fnmz.cinema.ui.view.BookingView;

import java.util.List;
import java.util.Scanner;

public class BookingFlow {

    private final List<Seat> seats;
    private final BookingCart cart;
    private final Showtime showtime;
    private final SeatService seatService;
    private final BookingView view = new BookingView();
    private final Scanner scanner = new Scanner(System.in);

    public BookingFlow(List<Seat> seats, BookingCart cart, Showtime showtime, SeatService seatService) {
        this.seats = seats;
        this.cart = cart;
        this.showtime = showtime;
        this.seatService = seatService;
    }

    public void run() {
        boolean done = false;
        while (!done) {
            view.showSeating(seats, showtime.getAuditorium());

            System.out.print("Enter seat (row-seat) or B for menu: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("B")) {
                done = new BookingMenuView(cart, showtime, seatService, view).open();
                continue;
            }

            new SeatSelector(cart).handle(input, seats);
        }
    }
}

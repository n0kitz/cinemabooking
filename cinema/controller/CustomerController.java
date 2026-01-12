package io.fnmz.cinema.controller;

import io.fnmz.cinema.services.ShowtimeService;
import io.fnmz.cinema.services.MovieService;
import io.fnmz.cinema.services.BookingService;
import io.fnmz.cinema.ui.view.CustomerShowtimeView;
import io.fnmz.cinema.ui.view.CustomerBookingView;
import io.fnmz.cinema.ui.view.CustomerBookingsView;

public class CustomerController {

    private final ShowtimeService showtimeService;
    private final MovieService movieService;
    private final BookingService bookingService;
    private final CustomerShowtimeView showtimeView;
    private final CustomerBookingView bookingView;
    private final CustomerBookingsView bookingsView;
    private boolean running = true;

    public CustomerController(ShowtimeService showtimeService, MovieService movieService,
                              BookingService bookingService, CustomerShowtimeView showtimeView,
                              CustomerBookingView bookingView, CustomerBookingsView bookingsView) {
        this.showtimeService = showtimeService;
        this.movieService = movieService;
        this.bookingService = bookingService;
        this.showtimeView = showtimeView;
        this.bookingView = bookingView;
        this.bookingsView = bookingsView;
    }

    public void start() {
        while (running) {
            displayCustomerMenu();
            int choice = showtimeView.getShowtimeSelection();
            handleCustomerMenuChoice(choice);
        }
    }

    private void displayCustomerMenu() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║      CUSTOMER MENU               ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║ 1. View Showtimes                ║");
        System.out.println("║ 2. My Bookings                   ║");
        System.out.println("║ 3. Logout                        ║");
        System.out.println("╚══════════════════════════════════╝");
    }

    private void handleCustomerMenuChoice(int choice) {
        switch (choice) {
            case 1 -> viewShowtimes();
            case 2 -> viewMyBookings();
            case 3 -> logout();
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private void viewShowtimes() {
        String showtimeData = showtimeService.getAllShowtimes();
        showtimeView.displayShowtimes(showtimeData);
        int choice = showtimeView.getShowtimeSelection();
        if (choice == 1) {
            bookTickets();
        }
    }

    private void bookTickets() {
        String seatData = showtimeService.getAvailableSeats();
        bookingView.displayBookingInterface(seatData);
        String seatSelection = bookingView.getSeatSelection();
        boolean success = bookingService.bookSeat(seatSelection);
        if (success) {
            bookingView.confirmBooking(seatSelection);
        }
    }

    private void viewMyBookings() {
        String bookingsData = bookingService.getCustomerBookings();
        bookingsView.displayMyBookings(bookingsData);
        int choice = bookingsView.getBookingAction();
        if (choice == 1) {
            cancelBooking();
        }
    }

    private void cancelBooking() {
        String bookingId = bookingsView.getBookingId();
        bookingService.cancelBooking(bookingId);
        System.out.println("Booking cancelled successfully.");
    }

    public void logout() {
        running = false;
    }
}

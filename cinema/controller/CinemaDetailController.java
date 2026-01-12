package io.fnmz.cinema.controller;

import io.fnmz.cinema.domain.entities.Cinema;
import io.fnmz.cinema.ui.DefaultOptions;

public class CinemaDetailController {

    private final AuditoriumController auditoriumController;
    private final MovieController movieController;
    private final ShowtimeController showtimeController;
    private final BookingController bookingController;

    public CinemaDetailController(AuditoriumController auditoriumController,
                                  MovieController movieController,
                                  ShowtimeController showtimeController,
                                  BookingController bookingController) {
        this.auditoriumController = auditoriumController;
        this.movieController = movieController;
        this.showtimeController = showtimeController;
        this.bookingController = bookingController;
    }

    public void manage(Cinema cinema) {
        while (true) {
            DefaultOptions options = new DefaultOptions("Cinema: " + cinema.getName());
            options.enableNew("Auditorium");
            options.enableNew("Movie");
            options.enableNew("Showtime");
            options.enableExit();

            System.out.println("\n=== CINEMA: " + cinema.getName() + " ===");
            System.out.println("1) Manage auditoriums");
            System.out.println("2) Manage movies");
            System.out.println("3) Manage showtimes");
            System.out.println("4) Book tickets");
            System.out.println("B) Back to cinemas");

            String input = new java.util.Scanner(System.in).nextLine().trim().toUpperCase();

            switch (input) {
                case "1" -> auditoriumController.manage(cinema);
                case "2" -> movieController.manage(cinema);
                case "3" -> showtimeController.manage(cinema);
                case "4" -> bookingController.manage(cinema);
                case "B" -> { return; }
            }
        }
    }
}

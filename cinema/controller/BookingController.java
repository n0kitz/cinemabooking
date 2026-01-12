package io.fnmz.cinema.controller;

import io.fnmz.cinema.domain.entities.Cinema;
import io.fnmz.cinema.domain.entities.Movie;
import io.fnmz.cinema.domain.entities.Showtime;
import io.fnmz.cinema.services.BookingService;
import io.fnmz.cinema.ui.view.BookingView;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

public class BookingController {

    private final BookingService service;
    private final BookingView view;
    private final Scanner scanner = new Scanner(System.in);

    public BookingController(BookingService service, BookingView view) {
        this.service = service;
        this.view = view;
    }

    public void book(Showtime showtime) {
        service.startBooking(showtime);
    }

    public void manage(Cinema cinema) {
        if (cinema.getMovies().isEmpty()) {
            System.out.println("No movies available for this cinema.");
            return;
        }

        Movie movie = selectMovie(cinema);
        if (movie == null) return;

        Showtime showtime = selectShowtime(cinema, movie);
        if (showtime == null) return;

        book(showtime);
    }

    private Movie selectMovie(Cinema cinema) {
        System.out.println("\nSelect a movie:");
        List<Movie> movies = cinema.getMovies();
        for (int i = 0; i < movies.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, movies.get(i).getTitle());
        }
        System.out.println("B) Back");
        System.out.print("> ");

        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("B")) return null;

        try {
            int idx = Integer.parseInt(input) - 1;
            if (idx < 0 || idx >= movies.size()) {
                System.out.println("Invalid selection.");
                return null;
            }
            return movies.get(idx);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return null;
        }
    }

    private Showtime selectShowtime(Cinema cinema, Movie movie) {
        List<Showtime> showtimes = cinema.getShowtimes().stream()
                .filter(s -> s.getMovie().equals(movie))
                .collect(Collectors.toList());

        if (showtimes.isEmpty()) {
            System.out.println("No showtimes available for this movie.");
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("\nSelect a showtime:");
        for (int i = 0; i < showtimes.size(); i++) {
            System.out.printf("%d) %s @ %s%n",
                    i + 1,
                    showtimes.get(i).getMovie().getTitle(),
                    showtimes.get(i).getStartTime().format(fmt));
        }
        System.out.println("B) Back");
        System.out.print("> ");

        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("B")) return null;

        try {
            int idx = Integer.parseInt(input) - 1;
            if (idx < 0 || idx >= showtimes.size()) return null;
            return showtimes.get(idx);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return null;
        }
    }
}

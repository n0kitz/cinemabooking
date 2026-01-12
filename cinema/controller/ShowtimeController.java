package io.fnmz.cinema.controller;

import io.fnmz.cinema.domain.entities.*;
import io.fnmz.cinema.services.ShowtimeService;
import io.fnmz.cinema.ui.view.ShowtimeMenuView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShowtimeController {

    private final ShowtimeService service;
    private final ShowtimeMenuView view;

    public ShowtimeController(ShowtimeService service,
                              ShowtimeMenuView view) {
        this.service = service;
        this.view = view;
    }

    public void manage(Cinema cinema) {
        while (true) {
            List<Showtime> showtimes =
                    service.findByCinema(cinema.getId());

            String input = view.show(showtimes);

            if ("B".equalsIgnoreCase(input)) return;

            if ("N".equalsIgnoreCase(input)) {
                createShowtime(cinema);
            }
        }
    }

    private void createShowtime(Cinema cinema) {

        List<Movie> movies = cinema.getMovies();
        List<Auditorium> auditoriums =
                new ArrayList<>(cinema.getAuditoriums());

        if (movies.isEmpty()) {
            view.showError("No movies available.");
            return;
        }

        if (auditoriums.isEmpty()) {
            view.showError("No auditoriums available.");
            return;
        }

        int movieIdx = view.selectMovie(movies);
        if (movieIdx < 0 || movieIdx >= movies.size()) return;

        int audIdx = view.selectAuditorium(auditoriums);
        if (audIdx < 0 || audIdx >= auditoriums.size()) return;

        LocalDateTime start = view.readStartTime();

        Showtime showtime = new Showtime(
                movies.get(movieIdx),
                auditoriums.get(audIdx),
                start
        );

        cinema.addShowtime(showtime);
        service.create(showtime);
    }
}

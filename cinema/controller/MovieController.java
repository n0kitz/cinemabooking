package io.fnmz.cinema.controller;

import io.fnmz.cinema.domain.entities.Cinema;
import io.fnmz.cinema.domain.entities.ContentRating;
import io.fnmz.cinema.domain.entities.Movie;
import io.fnmz.cinema.services.MovieService;
import io.fnmz.cinema.ui.view.MovieMenuView;

import java.util.List;

public class MovieController {

    private final MovieService service;
    private final MovieMenuView view;

    public MovieController(MovieService service, MovieMenuView view) {
        this.service = service;
        this.view = view;
    }

    public void manage(Cinema cinema) {
        while (true) {
            List<Movie> movies = service.findByCinema(cinema.getId());
            String opt = view.show(movies);

            if ("b".equalsIgnoreCase(opt)) return;

            if ("n".equalsIgnoreCase(opt)) {
                Movie movie = new Movie(
                        view.readMovieTitle(),
                        120,
                        ContentRating.FSK12
                );

                service.create(cinema, movie);
            }
        }
    }
}

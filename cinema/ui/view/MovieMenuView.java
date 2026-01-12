package io.fnmz.cinema.ui.view;

import io.fnmz.cinema.domain.entities.Movie;
import io.fnmz.cinema.ui.Terminal;

import java.util.List;

public class MovieMenuView {

    public String show(List<Movie> movies) {
        Terminal.println("\n=== MOVIES ===");

        for (int i = 0; i < movies.size(); i++) {
            Terminal.println((i + 1) + ") " + movies.get(i).getTitle());
        }

        Terminal.println("n) New movie");
        Terminal.println("b) Back");

        return Terminal.readLine("> ");
    }

    public String readMovieTitle() {
        return Terminal.readLine("Movie title: ");
    }
}

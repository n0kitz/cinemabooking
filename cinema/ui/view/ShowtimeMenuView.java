package io.fnmz.cinema.ui.view;

import io.fnmz.cinema.domain.entities.Auditorium;
import io.fnmz.cinema.domain.entities.Movie;
import io.fnmz.cinema.domain.entities.Showtime;
import io.fnmz.cinema.ui.Terminal;

import java.time.LocalDateTime;
import java.util.List;

public class ShowtimeMenuView {

    public String show(List<Showtime> showtimes) {
        Terminal.println("\n=== SHOWTIMES ===");

        for (int i = 0; i < showtimes.size(); i++) {
            Showtime s = showtimes.get(i);
            Terminal.println(
                    (i + 1) + ") " +
                            s.getMovie().getTitle() +
                            " @ " +
                            s.getStartTime()
            );
        }

        Terminal.println("n) New showtime");
        Terminal.println("b) Back");
        return Terminal.readLine("> ");
    }

    public int selectMovie(List<Movie> movies) {
        Terminal.println("\nSelect movie:");
        for (int i = 0; i < movies.size(); i++) {
            Terminal.println((i + 1) + ") " + movies.get(i).getTitle());
        }
        return Terminal.readInt("> ") - 1;
    }

    public int selectAuditorium(List<Auditorium> auditoriums) {
        Terminal.println("\nSelect auditorium:");
        for (int i = 0; i < auditoriums.size(); i++) {
            Terminal.println((i + 1) + ") " + auditoriums.get(i).getName());
        }
        return Terminal.readInt("> ") - 1;
    }

    public LocalDateTime readStartTime() {
        String input = Terminal.readLine("Start time (yyyy-MM-dd HH:mm): ");
        return LocalDateTime.parse(input.replace("", "T"));
    }
    public void showError(String message) {
        Terminal.println(message);
    }
}

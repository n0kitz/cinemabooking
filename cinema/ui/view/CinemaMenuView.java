package io.fnmz.cinema.ui.view;

import io.fnmz.cinema.app.MenuItem;
import io.fnmz.cinema.domain.entities.Cinema;
import io.fnmz.cinema.ui.DefaultOptions;

import java.util.List;
import java.util.Scanner;

//EntityManager em;
//em.getTransaction().begin();

public class CinemaMenuView {

    private final Scanner scanner = new Scanner(System.in);

    public String show(List<Cinema> cinemas, DefaultOptions options) {
        System.out.println("\n=== CINEMAS ===");

        for (int i = 0; i < cinemas.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, cinemas.get(i).getName());
        }

        for (MenuItem item : options.getItems()) {
            System.out.printf("%s) %s%n", item.key(), item.label());
        }

        System.out.print("> ");
        return scanner.nextLine().trim().toUpperCase();
    }

    public String readCinemaName() {
        System.out.print("Cinema name: ");
        return scanner.nextLine().trim();
    }

    public String readNewCinemaName() {
        System.out.print("New cinema name: ");
        return scanner.nextLine().trim();
    }
}

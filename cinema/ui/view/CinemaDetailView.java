package io.fnmz.cinema.ui.view;

import io.fnmz.cinema.app.MenuItem;
import io.fnmz.cinema.domain.entities.Cinema;
import io.fnmz.cinema.ui.DefaultOptions;

import java.util.Scanner;

public class CinemaDetailView {

    private final Scanner scanner = new Scanner(System.in);

    public String show(Cinema cinema, DefaultOptions options) {
        System.out.println("\n=== CINEMA: " + cinema.getName() + " ===");

        for (MenuItem item : options.getItems()) {
            System.out.printf("%s) %s%n", item.key(), item.label());
        }

        System.out.print("> ");
        return scanner.nextLine().trim().toUpperCase();
    }

    public String readNewName() {
        System.out.print("New cinema name: ");
        return scanner.nextLine().trim();
    }
}


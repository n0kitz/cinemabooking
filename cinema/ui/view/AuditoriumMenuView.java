package io.fnmz.cinema.ui.view;

import io.fnmz.cinema.app.MenuItem;
import io.fnmz.cinema.domain.entities.Auditorium;
import io.fnmz.cinema.ui.DefaultOptions;

import java.util.List;
import java.util.Scanner;

public class AuditoriumMenuView {

    private final Scanner scanner = new Scanner(System.in);

    public String show(List<Auditorium> auditoriums, DefaultOptions options) {
        System.out.println("\n=== AUDITORIUMS ===");

        for (int i = 0; i < auditoriums.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, auditoriums.get(i).getName());
        }

        for (MenuItem item : options.getItems()) {
            System.out.printf("%s) %s%n", item.key(), item.label());
        }

        System.out.print("> ");
        return scanner.nextLine().trim().toUpperCase();
    }

    public String readName() {
        System.out.print("Auditorium name: ");
        return scanner.nextLine().trim();
    }
}

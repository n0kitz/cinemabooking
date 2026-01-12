package io.fnmz.cinema.ui.view;

import io.fnmz.cinema.domain.entities.PriceCategory;

import java.util.Scanner;

public class AuditoriumDetailView {

    private final Scanner scanner = new Scanner(System.in);

    public String showMenu(String auditoriumName) {
        System.out.println("\n=== AUDITORIUM: " + auditoriumName + " ===");
        System.out.println("1) Rename auditorium");
        System.out.println("2) Change seating layout"); //show current layout(seats/rows)
        System.out.println("3) Configure price category");
        System.out.println("B) Back");
        System.out.print("> ");
        return scanner.nextLine().trim().toUpperCase();
    }

    public String readName() {
        System.out.print("New name: ");
        return scanner.nextLine().trim();
    }

    public int readRows() {
        System.out.print("Rows: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int readSeatsPerRow() {
        System.out.print("Seats per row: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public PriceCategory selectPriceCategory(String prompt) {
        System.out.println("\nSelect price category for " + prompt + ":");
        PriceCategory[] categories = PriceCategory.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.printf("%d) %s (%.2f)%n", i + 1, categories[i].name(), categories[i].getPrice());
        }
        System.out.print("> ");

        try {
            int idx = Integer.parseInt(scanner.nextLine()) - 1;
            if (idx < 0 || idx >= categories.length) return PriceCategory.STANDARD;
            return categories[idx];
        } catch (NumberFormatException e) {
            return PriceCategory.STANDARD;
        }
    }
}

package io.fnmz.cinema.ui.view;

import java.util.Scanner;

public class CustomerShowtimeView {

    private final Scanner scanner = new Scanner(System.in);

    public void displayShowtimes(String showtimeData) {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║        AVAILABLE SHOWTIMES       ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println(showtimeData);
        System.out.println("║ 1. Book Tickets                  ║");
        System.out.println("║ 2. Back                          ║");
        System.out.println("╚══════════════════════════════════╝");
    }

    public int getShowtimeSelection() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }
}

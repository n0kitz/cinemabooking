package io.fnmz.cinema.ui.view;

import java.util.Scanner;

public class CustomerBookingsView {

    private final Scanner scanner = new Scanner(System.in);

    public void displayMyBookings(String bookingsData) {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║          MY BOOKINGS             ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println(bookingsData);
        System.out.println("║ 1. Cancel Booking                ║");
        System.out.println("║ 2. Back                          ║");
        System.out.println("╚══════════════════════════════════╝");
    }

    public String getBookingId() {
        System.out.print("Enter booking ID to cancel: ");
        return scanner.nextLine();
    }

    public int getBookingAction() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }
}

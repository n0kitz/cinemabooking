package io.fnmz.cinema.ui.view;

import java.util.Scanner;

public class CustomerBookingView {

    private final Scanner scanner = new Scanner(System.in);

    public void displayBookingInterface(String seatData) {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║           BOOK TICKETS            ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println(seatData);
        System.out.println("╚══════════════════════════════════╝");
    }

    public String getSeatSelection() {
        System.out.print("Enter seat number: ");
        return scanner.nextLine();
    }

    public void confirmBooking(String bookingDetails) {
        System.out.println("Booking confirmed: " + bookingDetails);
    }
}

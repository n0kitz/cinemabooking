package io.fnmz.cinema.controller;

import io.fnmz.cinema.domain.entities.Auditorium;
import io.fnmz.cinema.domain.entities.Cinema;
import io.fnmz.cinema.services.AuditoriumService;
import io.fnmz.cinema.ui.DefaultOptions;
import io.fnmz.cinema.ui.view.AuditoriumDetailView;
import io.fnmz.cinema.ui.view.AuditoriumMenuView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AuditoriumController {

    private final AuditoriumService service;
    private final AuditoriumMenuView view;
    private final AuditoriumDetailView detailView;
    private final Scanner scanner = new Scanner(System.in);

    public AuditoriumController(
            AuditoriumService service,
            AuditoriumMenuView view,
            AuditoriumDetailView detailView
    ) {
        this.service = service;
        this.view = view;
        this.detailView = detailView;
    }

    public void manage(Cinema cinema) {
        while (true) {
            List<Auditorium> auditoriums =
                    service.findByCinema(cinema.getId());

            DefaultOptions options = new DefaultOptions("Auditorium");
            options.enableNew();
            options.enableRename();
            options.enableDelete();

            String input = view.show(auditoriums, options);

            if ("B".equalsIgnoreCase(input)) return;

            switch (input) {
                case "N" -> create(cinema);
                case "R" -> rename(auditoriums);
                case "D" -> delete(auditoriums);
                default -> openDetail(auditoriums, input);
            }
        }
    }

    private void create(Cinema cinema) {
        String name = view.readName();

        System.out.print("Rows: ");
        int rows = Integer.parseInt(scanner.nextLine());

        System.out.print("Seats per row: ");
        int seatsPerRow = Integer.parseInt(scanner.nextLine());

        Auditorium auditorium =
                new Auditorium(name, cinema, rows, seatsPerRow);

        service.create(cinema, auditorium);
    }

    private void rename(List<Auditorium> auditoriums) {
        Auditorium a = select(auditoriums);
        if (a == null) return;

        String newName = view.readName();
        a.rename(newName);
        service.update(a);
    }

    private void delete(List<Auditorium> auditoriums) {
        Auditorium a = select(auditoriums);
        if (a == null) return;

        service.delete(a);
    }

    private void openDetail(List<Auditorium> auditoriums, String input) {
        try {
            int idx = Integer.parseInt(input) - 1;
            if (idx < 0 || idx >= auditoriums.size()) return;

            manageAuditorium(auditoriums.get(idx));

        } catch (NumberFormatException ignored) {
        }
    }

    private void manageAuditorium(Auditorium auditorium) {
        while (true) {
            String input = detailView.showMenu(String.valueOf(auditorium));

            switch (input) {
                case "1" -> renameDetail(auditorium);
                case "2" -> changeLayout(auditorium);
                case "3" -> configurePricing(auditorium);
                case "B" -> { return; }
            }
        }
    }

    private void renameDetail(Auditorium auditorium) {
        String name = detailView.readName();
        auditorium.rename(name);
        service.update(auditorium);
    }

    private void changeLayout(Auditorium auditorium) {

        if (!auditorium.getShowtimes().isEmpty()) {
            System.out.println("showtime is set.");
            return;
        }

        int rows = detailView.readRows();
        int seats = detailView.readSeatsPerRow();

        auditorium.setLayout(rows, seats);
        service.update(auditorium);
    }

    private void configurePricing(Auditorium auditorium) {

        Map<Integer, String> pricing = new HashMap<>();

        System.out.println("\nAssign pricing per row:");
        System.out.println("Allowed: standard | premium | vip");

        for (int row = 1; row <= auditorium.getRows(); row++) {
            System.out.printf("Row %d: ", row);
            String category = scanner.nextLine().trim().toLowerCase();

            if (!category.equals("standard")
                    && !category.equals("premium")
                    && !category.equals("vip")) {
                category = "standard";
            }

            pricing.put(row, category);
        }

        auditorium.setPricing(pricing);
        service.update(auditorium);
    }

    private Auditorium select(List<Auditorium> auditoriums) {
        if (auditoriums.isEmpty()) return null;

        System.out.print("Select auditorium number: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine()) - 1;
            return (idx >= 0 && idx < auditoriums.size())
                    ? auditoriums.get(idx)
                    : null;
        } catch (Exception e) {
            return null;
        }
    }
}

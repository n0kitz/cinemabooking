package io.fnmz.cinema.controller;

import io.fnmz.cinema.domain.entities.Cinema;
import io.fnmz.cinema.services.CinemaService;
import io.fnmz.cinema.ui.DefaultOptions;
import io.fnmz.cinema.ui.view.CinemaMenuView;

import java.util.List;

public class CinemaController {

    private final CinemaService service;
    private final CinemaMenuView view;
    private final CinemaDetailController detailController;

    public CinemaController(CinemaService service,
                            CinemaMenuView view,
                            CinemaDetailController detailController) {
        this.service = service;
        this.view = view;
        this.detailController = detailController;
    }

    public void manageCinemas() {
        while (true) {
            List<Cinema> cinemas = service.listAll();

            DefaultOptions options = new DefaultOptions("Cinema");
            options.enableNew();
            options.enableRename();
            options.enableDelete();

            String input = view.show(cinemas, options);

            switch (input) {
                case "B" -> { return; }
                case "N" -> {
                    String name = view.readCinemaName();
                    service.create(name, "");
                }
                case "R" -> {
                    Cinema selected = selectCinema(cinemas);
                    if (selected != null) {
                        String newName = view.readNewCinemaName();
                        service.rename(selected, newName);
                    }
                }
                case "D" -> {
                    Cinema selected = selectCinema(cinemas);
                    if (selected != null) {
                        service.delete(selected);
                    }
                }
                default -> {
                    try {
                        int idx = Integer.parseInt(input) - 1;
                        if (idx >= 0 && idx < cinemas.size()) {
                            detailController.manage(cinemas.get(idx));
                        }
                    } catch (NumberFormatException ignored) {}
                }
            }
        }
    }

    private Cinema selectCinema(List<Cinema> cinemas) {
        if (cinemas.isEmpty()) return null;

        System.out.print("Select cinema number: ");
        try {
            int idx = Integer.parseInt(new java.util.Scanner(System.in).nextLine()) - 1;
            return (idx >= 0 && idx < cinemas.size()) ? cinemas.get(idx) : null;
        } catch (Exception e) {
            return null;
        }
    }
}

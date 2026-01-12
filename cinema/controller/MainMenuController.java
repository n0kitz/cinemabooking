package io.fnmz.cinema.controller;

import io.fnmz.cinema.ui.view.MainMenuView;
import io.fnmz.cinema.ui.view.CinemaMenuView;
import io.fnmz.cinema.ui.view.RoleSelectionView;
import io.fnmz.cinema.services.CinemaService;

public class MainMenuController {

    private final MainMenuView mainMenuView;
    private final CinemaMenuView cinemaMenuView;
    private final RoleSelectionView roleSelectionView;
    private final CinemaController cinemaController;
    private final CustomerController customerController;
    private final CinemaService cinemaService;

    public MainMenuController(MainMenuView mainMenuView, CinemaMenuView cinemaMenuView,
                              RoleSelectionView roleSelectionView, CinemaController cinemaController,
                              CustomerController customerController, CinemaService cinemaService) {
        this.mainMenuView = mainMenuView;
        this.cinemaMenuView = cinemaMenuView;
        this.roleSelectionView = roleSelectionView;
        this.cinemaController = cinemaController;
        this.customerController = customerController;
        this.cinemaService = cinemaService;
    }

    public void start() {
        mainMenuView.displayWelcome();

        // Step 1: Select Cinema
        cinemaMenuView.displayCinemas(cinemaService.getAllCinemas());
        int cinemaChoice = cinemaMenuView.getCinemaSelection();

        if (cinemaChoice == 0) return;

        // Step 2: Select Role
        roleSelectionView.displayRoleMenu();
        int roleChoice = roleSelectionView.getRoleSelection();

        // Step 3: Route to appropriate menu
        switch (roleChoice) {
            case 1 -> cinemaController.start();
            case 2 -> customerController.start();
            case 3 -> start();
        }
    }
}

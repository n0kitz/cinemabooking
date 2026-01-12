package io.fnmz.cinema;

import io.fnmz.cinema.controller.*;
import io.fnmz.cinema.infrastructure.DemoDataLoader;
import io.fnmz.cinema.infrastructure.persistence.PersistenceContext;
import io.fnmz.cinema.repository.*;
import io.fnmz.cinema.services.*;
import io.fnmz.cinema.ui.view.*;

public class App {

    public static void main(String[] args) {

        PersistenceContext ctx = new PersistenceContext();
        ctx.open();

        try {
            DemoDataLoader.load(ctx.em());

            CinemaRepository cinemaRepo = new CinemaRepository(ctx.em());
            AuditoriumRepository auditoriumRepo = new AuditoriumRepository(ctx.em());
            MovieRepository movieRepo = new MovieRepository(ctx.em());
            ShowtimeRepository showtimeRepo = new ShowtimeRepository(ctx.em());
            SeatRepository seatRepo = new SeatRepository(ctx.em());

            CinemaService cinemaService = new CinemaService(cinemaRepo, ctx);
            AuditoriumService auditoriumService = new AuditoriumService(auditoriumRepo, ctx);
            MovieService movieService = new MovieService(movieRepo, ctx);
            SeatService seatService = new SeatService(seatRepo);

            BookingService bookingService = new BookingService(seatService);
            ShowtimeService showtimeService = new ShowtimeService(showtimeRepo, seatRepo, ctx);

            MainMenuView mainMenuView = new MainMenuView();
            CinemaMenuView cinemaMenuView = new CinemaMenuView();
            RoleSelectionView roleSelectionView = new RoleSelectionView();

            // Admin views
            AuditoriumMenuView auditoriumMenuView = new AuditoriumMenuView();
            MovieMenuView movieMenuView = new MovieMenuView();
            ShowtimeMenuView showtimeMenuView = new ShowtimeMenuView();
            AuditoriumDetailView auditoriumDetailView = new AuditoriumDetailView();

            // Customer views
            CustomerShowtimeView customerShowtimeView = new CustomerShowtimeView();
            CustomerBookingView customerBookingView = new CustomerBookingView();
            CustomerBookingsView customerBookingsView = new CustomerBookingsView();

            AuditoriumController auditoriumController =
                    new AuditoriumController(auditoriumService, auditoriumMenuView, auditoriumDetailView);
            MovieController movieController = new MovieController(movieService, movieMenuView);
            ShowtimeController showtimeController = new ShowtimeController(showtimeService, showtimeMenuView);
            BookingController bookingController = new BookingController(bookingService, bookingView);

            CinemaDetailController cinemaDetailController =
                    new CinemaDetailController(auditoriumController, movieController, showtimeController, bookingController);
            CinemaController cinemaController = new CinemaController(cinemaService, cinemaMenuView, cinemaDetailController);

            CustomerController customerController = new CustomerController(
                    showtimeService, movieService, bookingService,
                    customerShowtimeView, customerBookingView, customerBookingsView);

            MainMenuController mainMenuController = new MainMenuController(
                    mainMenuView, cinemaController, customerController, roleSelectionView);

            mainMenuController.start();

        } finally {
            ctx.close();
        }
    }
}

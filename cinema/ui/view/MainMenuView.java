package io.fnmz.cinema.ui.view;

import io.fnmz.cinema.ui.Terminal;

public class MainMenuView {

    public String show() {
        Terminal.println("\n=== CINEMA BOOKING SYSTEM ===");
        Terminal.println("1) Manage cinemas");
        Terminal.println("X) Quit");
        return Terminal.readLine("> ");
    }
}

package io.fnmz.cinema.ui.view;

import java.util.Scanner;

public class RoleSelectionView {

    private final Scanner scanner = new Scanner(System.in);

    public void displayRoleMenu() {
        System.out.println("\n╔══════════════════════════╗");
        System.out.println("║     SELECT YOUR ROLE     ║");
        System.out.println("╠══════════════════════════╣");
        System.out.println("║ 1. Admin                 ║");
        System.out.println("║ 2. Customer              ║");
        System.out.println("║ 3. Back                  ║");
        System.out.println("╚══════════════════════════╝");
    }

    public int getRoleSelection() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }
}

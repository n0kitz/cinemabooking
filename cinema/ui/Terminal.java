package io.fnmz.cinema.ui;

import java.util.Scanner;

public class Terminal {

    private static final Scanner scanner = new Scanner(System.in);

    public static void println(String text) {
        System.out.println(text);
    }

    public static void print(String text) {
        System.out.print(text);
    }

    public static String readLine(String prompt) {
        print(prompt);
        return scanner.nextLine().trim();
    }

    public static int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(prompt));
            } catch (NumberFormatException e) {
                println("Please enter a valid number.");
            }
        }
    }

    public static long readLong(String prompt) {
        while (true) {
            try {
                return Long.parseLong(readLine(prompt));
            } catch (NumberFormatException e) {
                println("Please enter a valid number.");
            }
        }
    }
}


/*

@Override
public void printInvalidOption() {
    err.println("Invalid option. Please try again.");
}

@Override
public void printInvalidOption(String message) {
    err.printf("Error: %s%n", message);
}

@Override
public int getPositiveInteger(String title) {
    out.print(title + " ");
    while (true) {
        String input = scanner.nextLine();
        try {
            int value = Integer.parseInt(input);
            if (value > 0) {
                return value;
            } else {
                err.print("Please enter a positive integer: ");
            }
        } catch (NumberFormatException e) {
            err.print("Invalid input. Please enter a positive integer: ");
        }
    }
}

@Override
public BigDecimal getPositiveDecimal(String title) {
    out.print(title + " ");
    while (true) {
        String input = scanner.nextLine();
        try {
            BigDecimal value = new BigDecimal(input);
            if (value.floatValue() > 0.0F) {
                return value;
            } else {
                err.print("Please enter a positive decimal: ");
            }
        } catch (NumberFormatException e) {
            err.print("Invalid input. Please enter a positive decimal: ");
        }
    }
}

public String selectMenuOption(String title, List<MenuItem> menuOptions, DefaultOptions defaultOptions) {
    boolean interrupt = false;
    String value;
    Set<String> validValues = Stream.concat(menuOptions.stream(), defaultOptions.getItems().stream()).map(menuItem -> menuItem.shortcut().toLowerCase()).collect(Collectors.toSet());
    do {
        out.println(title);
        out.println("Select an option:");
        for (MenuItem menuOption : menuOptions) {
            out.printf("[%s] - %s%n", menuOption.shortcut(), menuOption.description());
        }
        out.println("=======================");
        for (MenuItem menuOption : defaultOptions.getItems()) {
            out.printf("[%s] - %s%n", menuOption.shortcut(), menuOption.description());
        }

        value = readValue("Your choice").toLowerCase();
        if (!validValues.contains(value)) {
            printInvalidOption();
        } else {
            interrupt = true;
        }
    } while (!interrupt);

    return value;
}

@Override
public String readValue(String title) {
    out.printf("%s: ", title);
    return scanner.nextLine();
}

@Override
public void showMatrix(String title, Matrix matrix) {
    List<String> rowHeader = matrix.getRowNames();
    List<String> columnHeader = matrix.getColumnNames();

    out.println(title);

    int maxRowNameLength = rowHeader.stream()
            .mapToInt(String::length)
            .max()
            .orElse(0);
    int maxColumnNameLength = columnHeader.stream().mapToInt(String::length)
            .max()
            .orElse(0);

    out.printf("%" + maxRowNameLength + "s ", "");
    columnHeader.forEach(i -> out.printf(" %" + (maxColumnNameLength+1) + "s ", i));
    out.println();
    for (int rowIndex = 0; rowIndex < matrix.getRowVectors().size(); rowIndex++) {
        Matrix.Vector vector = matrix.getRowVectors().get(rowIndex);
        out.printf("%" + maxRowNameLength + "s:", rowHeader.get(rowIndex));
        for (int columnIndex = 0; columnIndex < vector.getEntries().size(); columnIndex++) {
            String value = vector.getEntries().get(columnIndex).getValue();
            out.printf(" [%" + maxColumnNameLength + "s]", value);
        }
        out.println();
    }
}

}*/


package io.fnmz.cinema.ui;


import io.fnmz.cinema.app.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultOptions {
    private final String context;
    private boolean createNew;
    private boolean rename;
    private boolean delete;
    private String newObjectContext;
    private boolean exit;

    public DefaultOptions(String context) {
        this.context = context;
    }

    public static DefaultOptions empty() {
        return new DefaultOptions(null) {
            @Override
            public List<MenuItem> getItems() {
                return List.of();
            }
        };
    }

    public List<MenuItem> getItems() {
        List<MenuItem> items = new ArrayList<>();

        if (createNew) {
            items.add(new MenuItem("N", "Create new %s".formatted(Optional.ofNullable(newObjectContext).orElse(context))));
        }
        if (rename) {
            items.add(new MenuItem("R", "Rename %s".formatted(context)));
        }
        if (delete) {
            items.add(new MenuItem("D", "Delete %s".formatted(context)));
        }

        if (exit) {
            items.add(new MenuItem("X", "Exit application"));
        } else {
            items.add(new MenuItem("B", "Back to previous menu"));
        }

        return items;
    }

    public void enableRename() {
        rename = true;
    }

    public void enableNew() {
        enableNew(null);
    }

    public void enableNew(String newObjectContext) {
        createNew = true;
        this.newObjectContext = newObjectContext;
    }

    public void enableDelete() {
        delete = true;
    }

    public void enableExit() {
        exit = true;
    }
}

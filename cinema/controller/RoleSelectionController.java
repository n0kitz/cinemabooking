package io.fnmz.cinema.controller;

import io.fnmz.cinema.ui.view.RoleSelectionView;

public class RoleSelectionController {

    private final RoleSelectionView roleSelectionView;

    public RoleSelectionController(RoleSelectionView roleSelectionView) {
        this.roleSelectionView = roleSelectionView;
    }

    public int selectRole() {
        roleSelectionView.displayRoleMenu();
        return roleSelectionView.getRoleSelection();
    }
}

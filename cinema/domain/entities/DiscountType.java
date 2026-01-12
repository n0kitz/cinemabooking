package io.fnmz.cinema.domain.entities;

public enum DiscountType {
    NONE(0),
    STUDENT(20),
    SENIOR(15);

    private final int percent;

    DiscountType(int percent) {
        this.percent = percent;
    }

    public int percent() {
        return percent;
    }
}

package io.fnmz.cinema.domain.entities;

public enum PriceCategory {
    STANDARD(10.0),
    PREMIUM(15.0),
    VIP(20.0);

    private final double price;

    PriceCategory(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

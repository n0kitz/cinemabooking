package io.fnmz.cinema.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int rowNumber;

    @Column(nullable = false)
    private int seatNumber;

    @ManyToOne(optional = false)
    private Showtime showtime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PriceCategory category;

    @Column(nullable = false)
    private boolean booked = false;

    @Transient
    private boolean selected = false;

    public Seat() {}

    public Seat(int rowNumber, int seatNumber, Showtime showtime, PriceCategory category) {
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.showtime = showtime;
        this.category = category;
    }

    public Long getId() { return id; }
    public int getRowNumber() { return rowNumber; }
    public int getSeatNumber() { return seatNumber; }
    public Showtime getShowtime() { return showtime; }
    public PriceCategory getCategory() { return category; }
    public boolean isBooked() { return booked; }
    public boolean isSelected() { return selected; }
    public double getPrice() { return category.getPrice(); }

    public void setBooked(boolean booked) { this.booked = booked; }
    public void setSelected(boolean selected) { this.selected = selected; }
}

package io.fnmz.cinema.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "seat_row")
public class SeatRow {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "seat_per_row", nullable = false)
    private int seatPerRow;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PriceCategory priceCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

    public SeatRow() {}

    public SeatRow(String name, int seatPerRow, PriceCategory priceCategory) {
        this.name = name;
        this.seatPerRow = seatPerRow;
        this.priceCategory = priceCategory;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getSeatPerRow() { return seatPerRow; }
    public PriceCategory getPriceCategory() { return priceCategory; }
    public Auditorium getAuditorium() { return auditorium; }
    public void setAuditorium(Auditorium auditorium) { this.auditorium = auditorium; }
}

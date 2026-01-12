package io.fnmz.cinema.domain.entities;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "auditorium")
public class Auditorium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "auditorium_pricing",
            joinColumns = @JoinColumn(name = "auditorium_id"))
    @MapKeyColumn(name = "row_number")
    @Column(name = "category")
    private Map<Integer, String> rowPricing = new HashMap<>();

    @Column(nullable = false)
    private String name;

    private int rows;
    private int seatsPerRow;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    protected Auditorium() {}

    public Auditorium(String name, Cinema cinema, int rows, int seatsPerRow) {
        this.name = name;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.cinema = cinema;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getRows() { return rows; }
    public int getSeatsPerRow() { return seatsPerRow; }
    public Cinema getCinema() { return cinema; }
    public void rename(String name) {
        this.name = name;
    }

    void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    //weg vom Attribute-Gedanken changeSeatLayout
    public void setLayout(int rows, int seatsPerRow) {
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
    }

    public void setPricing(Map<Integer, String> pricing) {
        this.rowPricing.clear();
        this.rowPricing.putAll(pricing);
    }

    public String getCategoryForRow(int row) {
        return rowPricing.getOrDefault(row, "Standard");
    }

    public Map<Object, Object> getShowtimes() {

        return Map.of();
    }
}

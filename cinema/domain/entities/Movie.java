package io.fnmz.cinema.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private int durationMinutes;

    @Enumerated(EnumType.STRING)
    private ContentRating rating;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    protected Movie() {}

    public Movie(String title, int durationMinutes, ContentRating rating) {
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.rating = rating;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public int getDurationMinutes() { return durationMinutes; }
    public ContentRating getRating() { return rating; }
    public Cinema getCinema() { return cinema; }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
}

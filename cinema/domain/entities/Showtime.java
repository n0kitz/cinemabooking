package io.fnmz.cinema.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "showtime")
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(optional = false)
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

    private LocalDateTime startTime;

    @OneToMany(mappedBy = "showtime",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Seat> seats = new HashSet<>();

    protected Showtime() {}

    public Showtime(Movie movie,
                    Auditorium auditorium,
                    LocalDateTime startTime) {
        this.movie = movie;
        this.auditorium = auditorium;
        this.startTime = startTime;
    }

    public Long getId() { return id; }
    public Movie getMovie() { return movie; }
    public Auditorium getAuditorium() { return auditorium; }
    public LocalDateTime getStartTime() { return startTime; }
    public Set<Seat> getSeats() { return seats; }
    public Cinema getCinema() { return cinema; }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }
}

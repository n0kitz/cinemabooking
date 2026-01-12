package io.fnmz.cinema.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cinema")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String city;

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Auditorium> auditoriums = new HashSet<>();

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movie> movies = new ArrayList<>();

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Showtime> showtimes = new HashSet<>();

    protected Cinema() {}

    public Cinema(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Set<Auditorium> getAuditoriums() { return auditoriums; }
    public List<Movie> getMovies() { return movies; }
    public Set<Showtime> getShowtimes() { return showtimes; }

    public void setName(String name) {
        this.name = name;
    }

    public void addAuditorium(Auditorium a) {
        if (a == null) return;
        a.setCinema(this);
        auditoriums.add(a);
    }

    public void addMovie(Movie m) {
        if (m == null) return;
        m.setCinema(this);
        movies.add(m);
    }

    public void addShowtime(Showtime s) {
        if (s == null) return;
        s.setCinema(this);
        showtimes.add(s);
    }
}

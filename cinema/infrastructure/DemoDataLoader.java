package io.fnmz.cinema.infrastructure;

import io.fnmz.cinema.domain.entities.*;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;

public class DemoDataLoader {

    public static void load(EntityManager em) {

        em.getTransaction().begin();

        Cinema c1 = new Cinema("Cinemilio", "Emil");
        em.persist(c1);
        Cinema c2 = new Cinema("UCI", "Duisburg");
        em.persist(c1);
        Cinema c3 = new Cinema("Herzogtheather", "Geldern");
        em.persist(c1);

        Auditorium a1 = new Auditorium("Auditorium 1", c1, 5, 10);
        c1.addAuditorium(a1);
        em.persist(a1);

        Movie dune = new Movie("Dune", 155, ContentRating.FSK16);
        dune.setCinema(c1);
        c1.addMovie(dune);
        em.persist(dune);

        Showtime s1 = new Showtime(
                dune,
                a1,
                LocalDateTime.now().plusDays(1).withHour(18).withMinute(0)
        );
        s1.setCinema(c1);
        c1.addShowtime(s1);
        em.persist(s1);

        for (int r = 1; r <= a1.getRows(); r++) {
            for (int s = 1; s <= a1.getSeatsPerRow(); s++) {
                PriceCategory category;
                if (r <= 2) category = PriceCategory.VIP;
                else if (r <= 4) category = PriceCategory.PREMIUM;
                else category = PriceCategory.STANDARD;

                Seat seat = new Seat(r, s, s1, category);
                s1.addSeat(seat);
                em.persist(seat);
            }
        }

        em.getTransaction().commit();
        System.out.println("Demo data loaded");
    }
}

package io.fnmz.cinema.domain.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "discount")
public class Discount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal value;

    protected Discount() {}

    public Discount(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getValue() { return value; }
}

package io.fnmz.cinema.domain.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "showtime_id")
    private Showtime showtime;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private String status = "ACTIVE";

    protected Order() {}

    public Order(Customer customer, Showtime showtime) {
        this.customer = customer;
        this.showtime = showtime;
    }

    public Long getId() { return id; }
    public Customer getCustomer() { return customer; }
    public Showtime getShowtime() { return showtime; }
    public List<OrderItem> getItems() { return items; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getStatus() { return status; }

    public void addItem(OrderItem item) { items.add(item); }
    public void setStatus(String status) { this.status = status; }
}

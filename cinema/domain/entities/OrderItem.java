package io.fnmz.cinema.domain.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Seat seat;

    @Enumerated(EnumType.STRING)
    private DiscountType discount;

    @Column(nullable = false)
    private BigDecimal finalPrice;

    protected OrderItem() {}

    public OrderItem(Seat seat, DiscountType discount, BigDecimal finalPrice) {
        this.seat = seat;
        this.discount = discount;
        this.finalPrice = finalPrice;
    }

    public Long getId() { return id; }
    public Seat getSeat() { return seat; }
    public DiscountType getDiscount() { return discount; }
    public BigDecimal getFinalPrice() { return finalPrice; }
}

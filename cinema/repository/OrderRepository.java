package io.fnmz.cinema.repository;

import io.fnmz.cinema.domain.entities.Order;
import jakarta.persistence.EntityManager;

public class OrderRepository {

    private final EntityManager em;

    public OrderRepository(EntityManager em) { this.em = em; }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findByCustomerId(Long customerId) {
        return em.createQuery(
                        "SELECT o FROM Order o WHERE o.customer.id = :customerId",
                        Order.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }

    public void save(Order order) {
        if (order.getId() == null) em.persist(order);
        else em.merge(order);
    }
}

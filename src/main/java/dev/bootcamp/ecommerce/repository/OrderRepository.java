package dev.bootcamp.ecommerce.repository;

import dev.bootcamp.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
package dev.bootcamp.ecommerce.repository;

import dev.bootcamp.ecommerce.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}

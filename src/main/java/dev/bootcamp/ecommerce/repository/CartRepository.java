package dev.bootcamp.ecommerce.repository;

import dev.bootcamp.ecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}

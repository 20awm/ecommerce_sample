package dev.bootcamp.ecommerce.repository;

import dev.bootcamp.ecommerce.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}

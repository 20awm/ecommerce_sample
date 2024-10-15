package dev.bootcamp.ecommerce.repository;

import dev.bootcamp.ecommerce.model.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
}

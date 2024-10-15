package dev.bootcamp.ecommerce.service;

import dev.bootcamp.ecommerce.model.PurchaseHistory;
import dev.bootcamp.ecommerce.repository.PurchaseHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseHistoryService {
    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;

    public List<PurchaseHistory> getAllPurchaseHistories() {
        return purchaseHistoryRepository.findAll();
    }

    public PurchaseHistory getPurchaseHistoryById(Long id) {
        return purchaseHistoryRepository.findById(id).orElse(null);
    }

    public PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory) {
        return purchaseHistoryRepository.save(purchaseHistory);
    }

    public void deletePurchaseHistory(Long id) {
        purchaseHistoryRepository.deleteById(id);
    }
}
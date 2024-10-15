package dev.bootcamp.ecommerce.controller;

import dev.bootcamp.ecommerce.model.PurchaseHistory;
import dev.bootcamp.ecommerce.service.PurchaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-history")
public class PurchaseHistoryController {
    @Autowired
    private PurchaseHistoryService purchaseHistoryService;

    @GetMapping
    public List<PurchaseHistory> getAllPurchaseHistories() {
        return purchaseHistoryService.getAllPurchaseHistories();
    }

    @GetMapping("/{id}")
    public PurchaseHistory getPurchaseHistoryById(@PathVariable Long id) {
        return purchaseHistoryService.getPurchaseHistoryById(id);
    }

    @PostMapping
    public PurchaseHistory createPurchaseHistory(@RequestBody PurchaseHistory purchaseHistory) {
        return purchaseHistoryService.savePurchaseHistory(purchaseHistory);
    }

    @DeleteMapping("/{id}")
    public void deletePurchaseHistory(@PathVariable Long id) {
        purchaseHistoryService.deletePurchaseHistory(id);
    }
}

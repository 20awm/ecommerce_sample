package dev.bootcamp.ecommerce.controller;

import dev.bootcamp.ecommerce.model.OrderDetails;
import dev.bootcamp.ecommerce.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-details")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;

    @GetMapping
    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsService.getAllOrderDetails();
    }

    @GetMapping("/{id}")
    public OrderDetails getOrderDetailById(@PathVariable Long id) {
        return orderDetailsService.getOrderDetailById(id);
    }

    @PostMapping
    public OrderDetails createOrderDetail(@RequestBody OrderDetails orderDetails) {
        return orderDetailsService.saveOrderDetail(orderDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderDetail(@PathVariable Long id) {
        orderDetailsService.deleteOrderDetail(id);
    }
}
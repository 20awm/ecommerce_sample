package dev.bootcamp.ecommerce.service;

import dev.bootcamp.ecommerce.model.OrderDetails;
import dev.bootcamp.ecommerce.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }

    public OrderDetails getOrderDetailById(Long id) {
        return orderDetailsRepository.findById(id).orElse(null);
    }

    public OrderDetails saveOrderDetail(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    public void deleteOrderDetail(Long id) {
        orderDetailsRepository.deleteById(id);
    }
}

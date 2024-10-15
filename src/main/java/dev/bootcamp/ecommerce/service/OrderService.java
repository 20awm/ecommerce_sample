package dev.bootcamp.ecommerce.service;
import dev.bootcamp.ecommerce.model.Order;
import dev.bootcamp.ecommerce.model.OrderDetails;
import dev.bootcamp.ecommerce.model.Product;
import dev.bootcamp.ecommerce.repository.OrderRepository;
import dev.bootcamp.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public void processOrderCompletion(Order order) {
        for (OrderDetails orderDetails : order.getOrderDetails()) {
            Product product = productRepository.findById(orderDetails.getProduct().getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            int newQuantity = product.getStockQuantity() - orderDetails.getQuantity();
            if (newQuantity < 0) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }
            product.setStockQuantity(newQuantity);
            productRepository.save(product);
        }
        order.setOrderStatus("COMPLETED");
        orderRepository.save(order);
    }
}
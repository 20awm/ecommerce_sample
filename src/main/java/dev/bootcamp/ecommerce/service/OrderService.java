package dev.bootcamp.ecommerce.service;
import dev.bootcamp.ecommerce.model.Order;
import dev.bootcamp.ecommerce.model.OrderDetails;
import dev.bootcamp.ecommerce.model.Product;
import dev.bootcamp.ecommerce.repository.OrderRepository;
import dev.bootcamp.ecommerce.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
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
            log.info("PRODUCT GET STOCK QUANTITY: {}", product.getStockQuantity());
            log.info("ORDER DETAILS GET QUANTITY: {}", orderDetails.getQuantity());
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
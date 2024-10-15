package dev.bootcamp.ecommerce.controller;

import dev.bootcamp.ecommerce.dto.PurchaseRequest;
import dev.bootcamp.ecommerce.dto.OrderDetailRequest;
import dev.bootcamp.ecommerce.model.Order;
import dev.bootcamp.ecommerce.model.OrderDetails;
import dev.bootcamp.ecommerce.model.Payment;
import dev.bootcamp.ecommerce.model.PurchaseHistory;
import dev.bootcamp.ecommerce.service.CustomerService;
import dev.bootcamp.ecommerce.service.OrderDetailsService;
import dev.bootcamp.ecommerce.service.OrderService;
import dev.bootcamp.ecommerce.service.PaymentService;
import dev.bootcamp.ecommerce.service.ProductService;
import dev.bootcamp.ecommerce.service.PurchaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PurchaseHistoryService purchaseHistoryService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @PostMapping("/purchase")
    public ResponseEntity<Order> createPurchase(@RequestBody PurchaseRequest purchaseRequest) {
        Order order = new Order();
        order.setCustomer(customerService.getCustomerById(purchaseRequest.getCustomerId()));
        order.setShippingAddress(purchaseRequest.getShippingAddress());
        order.setOrderStatus(purchaseRequest.getOrderStatus());
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(purchaseRequest.getPayment().getAmount());

        Order savedOrder = orderService.saveOrder(order);

        for (OrderDetailRequest detailRequest : purchaseRequest.getOrderDetails()) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrder(savedOrder);
            orderDetails.setProduct(productService.getProductById(detailRequest.getProductId()));
            orderDetails.setQuantity(detailRequest.getQuantity());
            orderDetails.setPrice(detailRequest.getPrice());
            orderDetailsService.saveOrderDetail(orderDetails);
        }

        Payment payment = new Payment();
        payment.setOrder(savedOrder);
        payment.setAmount(purchaseRequest.getPayment().getAmount());
        payment.setPaymentMethod(purchaseRequest.getPayment().getPaymentMethod());
        payment.setPaymentStatus(purchaseRequest.getPayment().getPaymentStatus());
        payment.setPaymentDate(LocalDateTime.now());
        paymentService.savePayment(payment);

        PurchaseHistory purchaseHistory = new PurchaseHistory();
        purchaseHistory.setCustomer(order.getCustomer());
        purchaseHistory.setOrder(savedOrder);
        purchaseHistory.setPurchaseDate(LocalDateTime.now());
        purchaseHistory.setTotalAmount(order.getTotalAmount());
        purchaseHistoryService.savePurchaseHistory(purchaseHistory);

        return ResponseEntity.ok(savedOrder);
    }
}
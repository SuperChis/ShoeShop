package server.service;

import org.springframework.stereotype.Service;
import server.entity.OrderEntity;

@Service
public interface OrderService {
    OrderEntity getOrderById(int orderId);

    OrderEntity getOrderByCustomerId(int customerId);

    OrderEntity createOrder(OrderEntity order);
}

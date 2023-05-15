package server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.entity.OrderEntity;
import server.repository.OrderRepository;
import server.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderEntity getOrderById(int orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    public OrderEntity getOrderByCustomerId(int customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public OrderEntity createOrder(OrderEntity order) {
        return orderRepository.save(order);
    }
}

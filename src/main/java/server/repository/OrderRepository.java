package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    OrderEntity findByOrderId(Integer orderId);

    OrderEntity findByCustomerId(Integer customerId);

    OrderEntity save(OrderEntity order);
}
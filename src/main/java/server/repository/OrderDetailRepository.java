package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.entity.OrderDetailEntity;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {

    OrderDetailEntity save(OrderDetailEntity orderDetail);

    OrderDetailEntity findByOrderIdAndProductId(int orderId, int productId);
}

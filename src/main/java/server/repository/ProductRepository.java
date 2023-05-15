package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.entity.ProductEntity;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    ProductEntity findByProductId(Integer productId);

    ProductEntity save(ProductEntity product);

    List<ProductEntity> findByProductNameContainingIgnoreCase(String productName);
}


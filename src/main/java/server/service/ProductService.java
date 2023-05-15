package server.service;

import org.springframework.stereotype.Service;
import server.entity.ProductEntity;

import java.util.List;

@Service
public interface ProductService {
    List<ProductEntity> getAllProduct();
    ProductEntity getProductById(int productId);

    ProductEntity saveProduct(ProductEntity product);

    public List<ProductEntity> searchProducts(String productName);
}

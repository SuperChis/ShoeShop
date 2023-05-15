package server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.entity.ProductEntity;
import server.repository.ProductRepository;
import server.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductEntity> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity saveProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    public ProductEntity getProductById(int productId) {
        return productRepository.findByProductId(productId);
    }

    @Override
    public List<ProductEntity> searchProducts(String productName) {
        return productRepository.findByProductNameContainingIgnoreCase(productName);
    }
}

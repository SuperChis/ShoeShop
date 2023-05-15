package server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.entity.OrderDetailEntity;
import server.entity.OrderEntity;
import server.entity.ProductEntity;
import server.service.OrderDetailService;
import server.service.OrderService;
import server.service.ProductService;

import java.util.Map;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getAllOrder(@PathVariable int customerId) {
        OrderEntity order = orderService.getOrderByCustomerId(customerId);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/{customerId}/products")
    public ResponseEntity<?> addProductToOrder(@PathVariable int customerId,
                                               @RequestBody Map<String, Integer> productInfo) {
        int productId = productInfo.get("productId");
        int quantity = productInfo.get("quantity");
        ProductEntity product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid productId: " + productId);
        }
        OrderEntity order = orderService.getOrderByCustomerId(customerId);
        if (order == null) {
            OrderEntity newOrder = new OrderEntity();
            newOrder.setCustomerId(customerId);
            orderService.createOrder(newOrder);
        }
        OrderEntity newOrder = orderService.getOrderByCustomerId(customerId);
        boolean productExist = false;
        for (OrderDetailEntity orderDetail : newOrder.getOrderDetailsByOrderId()) {
            if (orderDetail.getProductId() == productId) {
                // Nếu đã có sản phẩm, cập nhật số lượng và tính lại tổng tiền
                int newQuantity = orderDetail.getQuantityOrder() + quantity;
                orderDetail.setQuantityOrder(newQuantity);
                orderDetail.setTotal(newQuantity * product.getProductPrice());
                orderDetailService.updateOrderDetail(orderDetail);
                productExist = true;
                break;
            }
        }
        if (!productExist) {
            OrderDetailEntity orderDetail = new OrderDetailEntity();
            orderDetail.setProductId(productId);
            orderDetail.setQuantityOrder(quantity);
            orderDetail.setTotal(quantity * product.getProductPrice());
            orderDetail.setOrderId(newOrder.getOrderId());

            newOrder.getOrderDetailsByOrderId().add(orderDetail);
            orderDetailService.createOrderDetail(orderDetail);
            orderService.createOrder(newOrder);
        }
        return ResponseEntity.ok().body("Product added to order successfully");
    }

    @DeleteMapping("/{customerId}/products/{productId}")
    public ResponseEntity<?> removeProductFromOrder(@PathVariable int customerId,
                                                    @PathVariable int productId) {
        OrderEntity order = orderService.getOrderByCustomerId(customerId);
        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Order not found for customerId: " + customerId);
        }
        OrderDetailEntity orderDetail = orderDetailService.getOrderDetailByOrderIdAndProductId(order.getOrderId(),
                productId);
        if (orderDetail == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found in order for customerId: " + customerId + ", productId: " + productId);
        }
        orderDetailService.deleteOrderDetail(orderDetail);
        return ResponseEntity.ok().body("Product removed from order successfully");
    }

}

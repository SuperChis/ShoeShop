package server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "\"OrderDetail\"")
public class OrderDetailEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "detailID")
    private int detailId;
    @Basic
    @Column(name = "orderID")
    private Integer orderId;
    @Basic
    @Column(name = "productID")
    private Integer productId;
    @Basic
    @Column(name = "quantity_order")
    private Integer quantityOrder;
    @Basic
    @Column(name = "total")
    private Integer total;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderID", referencedColumnName = "orderID", insertable = false, updatable = false)
    @JsonBackReference
    private OrderEntity ordersByOrderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productID", referencedColumnName = "productID", insertable = false, updatable = false)
    @JsonBackReference
    private ProductEntity productByProductId;

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(Integer quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OrderDetailEntity that = (OrderDetailEntity) o;
        return detailId == that.detailId && Objects.equals(orderId, that.orderId)
                && Objects.equals(productId, that.productId) && Objects.equals(quantityOrder, that.quantityOrder)
                && Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detailId, orderId, productId, quantityOrder, total);
    }

    public OrderEntity getOrdersByOrderId() {
        return ordersByOrderId;
    }

    public void setOrdersByOrderId(OrderEntity ordersByOrderId) {
        this.ordersByOrderId = ordersByOrderId;
    }

    public ProductEntity getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(ProductEntity productByProductId) {
        this.productByProductId = productByProductId;
    }
}


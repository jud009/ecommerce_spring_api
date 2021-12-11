package com.udj.course.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable //subtype
public class OrderItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private ProductOrder productOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderItemPK() {
    }

    public ProductOrder getOrder() {
        return productOrder;
    }

    public void setOrder(ProductOrder order) {
        this.productOrder = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return Objects.equals(productOrder, that.productOrder) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productOrder, product);
    }
}

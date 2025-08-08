package co.edu.udea.talentotech.programacion.intermedio.api_rest.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cart")
public class Cart {

    // Use @Column to map the Java field to the database column name
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartid")
    private Integer cartId;

    @Column(name = "userid", nullable = false)
    private Integer userId;

    @Column(name = "productid", nullable = false)
    private Integer productId;

    @Column(name = "subtotal", nullable = false)
    private BigDecimal subTotal;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    // Default constructor
    public Cart() {}

    // Getters and Setters
    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getUserId() {
    return userId;
}

    public void setUserId(Integer userId) {
    this.userId = userId;
}

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
package co.edu.udea.talentotech.programacion.intermedio.api_rest.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;

public class CartDTO {

    private Integer cartId;
    @NotNull
    private Integer productId;
    private BigDecimal subTotal;
    @NotNull
    private Integer quantity;

    // Constructors (you can create a separate DTO for requests vs. responses)
    public CartDTO() {}
    
    // Constructor for creating a new cart (request DTO)
    public CartDTO(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Constructor for mapping an existing cart (response DTO)
    public CartDTO(Integer cartId, Integer productId, BigDecimal subTotal, Integer quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.subTotal = subTotal;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
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
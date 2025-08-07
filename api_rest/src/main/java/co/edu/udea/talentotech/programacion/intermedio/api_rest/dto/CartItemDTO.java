package co.edu.udea.talentotech.programacion.intermedio.api_rest.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class CartItemDTO {

    @NotNull
    private Integer productId;

    @NotNull
    private Integer quantity;

    // Optional: include subtotal if needed
    private BigDecimal subTotal;

    public CartItemDTO() {}

    public CartItemDTO(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters and setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
}

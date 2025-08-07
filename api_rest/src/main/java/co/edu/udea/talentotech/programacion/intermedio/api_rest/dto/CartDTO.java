package co.edu.udea.talentotech.programacion.intermedio.api_rest.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class CartDTO {

    private Integer cartId; // Optional, or can be removed entirely

    @NotNull
    private List<CartItemDTO> items;

    public CartDTO() {}

    public CartDTO(List<CartItemDTO> items) {
        this.items = items;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }
}

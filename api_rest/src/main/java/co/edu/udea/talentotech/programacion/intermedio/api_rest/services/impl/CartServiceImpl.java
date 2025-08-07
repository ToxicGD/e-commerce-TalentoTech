package co.edu.udea.talentotech.programacion.intermedio.api_rest.services.impl;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.CartDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.CartItemDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Cart;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Product;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories.CartRepository;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories.ProductRepository;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CartDTO> findAll() {
        List<Cart> carts = (List<Cart>) cartRepository.findAll();
        return carts.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CartDTO save(CartDTO cartDTO) {
        List<CartDTO> resultList = saveAll(cartDTO);
        return resultList.isEmpty() ? null : resultList.get(0); // return just the first
    }

    @Override
    @Transactional
    public CartDTO update(Integer id, CartDTO cartDTO) {
        if (cartDTO.getItems() == null || cartDTO.getItems().isEmpty()) {
            throw new RuntimeException("CartDTO must contain at least one item to update.");
        }

        CartItemDTO item = cartDTO.getItems().get(0); // only update first item

        Cart cart = cartRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + id));

        if (!cart.getProductId().equals(item.getProductId())) {
            Product product = productRepository.findById(item.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + item.getProductId()));
            cart.setProductId(product.getProductId());
        }

        if (item.getQuantity() != null) {
            cart.setQuantity(item.getQuantity());
        }

        // Recalculate subtotal
        Product updatedProduct = productRepository.findById(cart.getProductId())
            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + cart.getProductId()));

        BigDecimal updatedPrice = BigDecimal.valueOf(updatedProduct.getPrice());
        BigDecimal updatedQuantity = BigDecimal.valueOf(cart.getQuantity());
        cart.setSubTotal(updatedPrice.multiply(updatedQuantity));

        Cart updatedCart = cartRepository.save(cart);
        return convertToDTO(updatedCart);
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        cartRepository.deleteById(id.longValue());
    }

    @Override
    @Transactional
    public List<CartDTO> saveAll(CartDTO cartDTO) {
        List<CartDTO> savedItems = new ArrayList<>();

        for (CartItemDTO item : cartDTO.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + item.getProductId()));

            BigDecimal productPrice = BigDecimal.valueOf(product.getPrice());
            BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());
            BigDecimal subTotal = productPrice.multiply(quantity);

            Cart newCart = new Cart();
            newCart.setUserId(cartDTO.getUserId());
            newCart.setProductId(item.getProductId());
            newCart.setQuantity(item.getQuantity());
            newCart.setSubTotal(subTotal);

            Cart saved = cartRepository.save(newCart);
            savedItems.add(convertToDTO(saved));
        }

        return savedItems;
    }

    @Override
    public List<CartDTO> findByUserId(Integer userId) {
        List<Cart> carts = cartRepository.findByUserId(userId);
        return carts.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Converts a Cart entity to a CartDTO.
     */
    private CartDTO convertToDTO(Cart cart) {
        CartItemDTO item = new CartItemDTO();
        item.setProductId(cart.getProductId());
        item.setQuantity(cart.getQuantity());
        item.setSubTotal(cart.getSubTotal());

        List<CartItemDTO> itemList = new ArrayList<>();
        itemList.add(item);

        CartDTO dto = new CartDTO();
        dto.setUserId(cart.getUserId());
        dto.setCartId(cart.getCartId());
        dto.setItems(itemList);

        return dto;
    }
}
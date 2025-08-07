package co.edu.udea.talentotech.programacion.intermedio.api_rest.services.impl;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.CartDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Cart;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Product;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories.CartRepository;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories.ProductRepository;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    @Transactional
    @Override
    public CartDTO save(CartDTO cartDTO) {
        // Find the product to get its price
        Product product = productRepository.findById(cartDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + cartDTO.getProductId()));
        
        // Use BigDecimal for precise calculations
        BigDecimal productPrice = BigDecimal.valueOf(product.getPrice());
        BigDecimal quantity = BigDecimal.valueOf(cartDTO.getQuantity());
        BigDecimal subTotal = productPrice.multiply(quantity);

        // Create the new Cart entity
        Cart newCart = new Cart();
        newCart.setProductId(cartDTO.getProductId());
        newCart.setQuantity(cartDTO.getQuantity());
        newCart.setSubTotal(subTotal);
        
        // Save the entity and return the DTO
        Cart savedCart = cartRepository.save(newCart);
        return convertToDTO(savedCart);
    }

    @Transactional
    @Override
    public CartDTO update(Integer id, CartDTO cartDTO) {
        Cart cart = cartRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + id));

        // Update product if the DTO provides a new one
        if (cartDTO.getProductId() != null && !cart.getProductId().equals(cartDTO.getProductId())) {
            Product product = productRepository.findById(cartDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + cartDTO.getProductId()));
            cart.setProductId(product.getProductId());
        }

        // Update quantity if the DTO provides a new one
        if (cartDTO.getQuantity() != null) {
            cart.setQuantity(cartDTO.getQuantity());
        }

        // Recalculate subtotal after updates
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
    
    /**
     * Converts a Cart entity to a CartDTO.
     */
    private CartDTO convertToDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setCartId(cart.getCartId());
        dto.setProductId(cart.getProductId());
        dto.setSubTotal(cart.getSubTotal());
        dto.setQuantity(cart.getQuantity());
        return dto;
    }
}
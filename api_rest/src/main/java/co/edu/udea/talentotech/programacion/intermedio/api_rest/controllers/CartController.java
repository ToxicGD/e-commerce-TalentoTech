package co.edu.udea.talentotech.programacion.intermedio.api_rest.controllers;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.CartDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Endpoint for creating a new cart item
    @PostMapping
    public ResponseEntity<List<CartDTO>> createCart(@RequestBody CartDTO cartDTO) {
        List<CartDTO> createdItems = cartService.saveAll(cartDTO);
        return new ResponseEntity<>(createdItems, HttpStatus.CREATED);
    }
    
    // Endpoint for getting all cart items
    @GetMapping
    public ResponseEntity<List<CartDTO>> getCarts() {
        List<CartDTO> carts = cartService.findAll();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartDTO>> getCartsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(cartService.findByUserId(userId));
    }

    // Endpoint for updating an existing cart item by its ID
    @PutMapping("/{id}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable Integer id, @RequestBody CartDTO cartDTO) {
        CartDTO updatedCart = cartService.update(id, cartDTO);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    // Endpoint for deleting a cart item by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Integer id) {
        cartService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
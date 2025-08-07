package co.edu.udea.talentotech.programacion.intermedio.api_rest.services;

import java.util.List;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.CartDTO;

public interface CartService {
    List<CartDTO> findAll();
    CartDTO save(CartDTO cartDTO);
    CartDTO update(Integer id, CartDTO cartDTO);
    void delete(Integer id);
    List<CartDTO> saveAll(CartDTO cartDTO);
    List<CartDTO> findByUserId(Integer userId);
}
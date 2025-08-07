package co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Cart;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> { // Changed to Long
    List<Cart> findByUserId(Integer userId);
}
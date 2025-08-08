package co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}

package co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Delivery;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, Integer> {
}

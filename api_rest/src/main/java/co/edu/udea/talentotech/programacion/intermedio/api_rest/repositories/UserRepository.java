package co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}

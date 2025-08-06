package co.edu.udea.talentotech.programacion.intermedio.api_rest.services;

import java.util.List;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.UserDTO;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO save(UserDTO userDTO);
    UserDTO update(Integer id, UserDTO userDTO);
    void delete(Integer id);
}

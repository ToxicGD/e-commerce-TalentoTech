package co.edu.udea.talentotech.programacion.intermedio.api_rest.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.UserDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.User;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories.UserRepository;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)  //begin - commit en sql
    @Override
    public List<UserDTO> findAll() {
        List<User> all = (List<User>) userRepository.findAll();
        return all.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Transactional
    @Override
    public UserDTO update(Integer id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        // Update fields
        if (userDTO.getId() != null) {
            user.setId(userDTO.getId());
        }
        if (userDTO.getUsername() != null) {
            user.setUsername(userDTO.getUsername());
        }
        if (userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPassword() != null) {
            user.setPassword(userDTO.getPassword());
        }
        if (userDTO.getRoleId() == 1 || userDTO.getRoleId() == 2) {
            user.setRoleId(userDTO.getRoleId());
        }
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    /*@Transactional
    @Override
    public void enrollMateria(Integer cedulaAlumno, Short codigoMateria) {
        Alumno alumno = alumnoRepository.findById(cedulaAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno not found with cedula: " + cedulaAlumno));
        Materia materia = materiaRepository.findById(codigoMateria)
                .orElseThrow(() -> new RuntimeException("Materia not found with codigo: " + codigoMateria));
        alumno.getMaterias().add(materia);
        alumnoRepository.save(alumno);
    }

    @Transactional
    @Override
    public void unenrollMateria(Integer cedulaAlumno, Short codigoMateria) {
        Alumno alumno = alumnoRepository.findById(cedulaAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno not found with cedula: " + cedulaAlumno));
        Materia materia = materiaRepository.findById(codigoMateria)
                .orElseThrow(() -> new RuntimeException("Materia not found with codigo: " + codigoMateria));
        alumno.getMaterias().remove(materia);
        alumnoRepository.save(alumno);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MateriaDTO> findMateriasByAlumno(Integer cedulaAlumno) {
        Alumno alumno = alumnoRepository.findById(cedulaAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno not found with cedula: " + cedulaAlumno));

        return alumno.getMaterias().stream()
                .map(materia -> new MateriaDTO(materia))
                .collect(Collectors.toList());
    }*/

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user);
    }

    private User convertToEntity(UserDTO userDTO) {
        User user =  new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRoleId(userDTO.getRoleId());
        return user;
    }
}

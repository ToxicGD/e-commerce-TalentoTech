package co.edu.udea.talentotech.programacion.intermedio.api_rest.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.DeliveryDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Delivery;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories.DeliveryRepository;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.DeliveryService;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Transactional(readOnly = true)  //begin - commit en sql
    @Override
    public List<DeliveryDTO> findAll() {
        List<Delivery> all = (List<Delivery>) deliveryRepository.findAll();
        return all.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public DeliveryDTO save(DeliveryDTO deliveryDTO) {
        Delivery user = convertToEntity(deliveryDTO);
        Delivery savedDelivery = deliveryRepository.save(user);
        return convertToDTO(savedDelivery);
    }

    @Transactional
    @Override
    public DeliveryDTO update(Integer id, DeliveryDTO deliveryDTO) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + id));
        // Update fields
        if (deliveryDTO.getDeliveryId() != null) {
            delivery.setDeliveryId(deliveryDTO.getDeliveryId());
        }
        if (deliveryDTO.getUserId() != null) {
            delivery.setUserId(deliveryDTO.getUserId());
        }
        if (deliveryDTO.getAddress() != null) {
            delivery.setAddress(deliveryDTO.getAddress());
        }
        if (deliveryDTO.getZipCode() != 00000) {
            delivery.setZipCode(deliveryDTO.getZipCode());
        }
        if (deliveryDTO.getPhoneNumber() != null) {
            delivery.setPhoneNumber(deliveryDTO.getPhoneNumber());
        }
        Delivery updatedDelivery = deliveryRepository.save(delivery);
        return convertToDTO(updatedDelivery);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        deliveryRepository.deleteById(id);
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

    private DeliveryDTO convertToDTO(Delivery delivery) {
        return new DeliveryDTO(delivery);
    }

    private Delivery convertToEntity(DeliveryDTO deliveryDTO) {
        Delivery delivery =  new Delivery();
        delivery.setDeliveryId(deliveryDTO.getDeliveryId());
        delivery.setUserId(deliveryDTO.getUserId());
        delivery.setAddress(deliveryDTO.getAddress());
        delivery.setZipCode(deliveryDTO.getZipCode());
        delivery.setPhoneNumber(deliveryDTO.getPhoneNumber());
        return delivery;
    }
}

package co.edu.udea.talentotech.programacion.intermedio.api_rest.services;

import java.util.List;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.DeliveryDTO;

public interface DeliveryService {
    List<DeliveryDTO> findAll();
    DeliveryDTO save(DeliveryDTO deliveryDTO);
    DeliveryDTO update(Integer id, DeliveryDTO deliveryDTO);
    void delete(Integer id);
}

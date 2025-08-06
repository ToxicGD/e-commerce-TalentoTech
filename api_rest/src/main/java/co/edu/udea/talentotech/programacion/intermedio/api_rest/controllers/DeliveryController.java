package co.edu.udea.talentotech.programacion.intermedio.api_rest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.DeliveryDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Delivery;

@RestController
public class DeliveryController {

    private List<Delivery> deliveries = new ArrayList<>();

    @PostMapping("/delivery")
    public DeliveryDTO createUser(@RequestBody DeliveryDTO delivery) {
        System.out.println("Received delivery: " + delivery);
        Delivery newDelivery = new Delivery(
                delivery.getUserId(),
                delivery.getAddress(),
                delivery.getZipCode(),
                delivery.getPhoneNumber());
        deliveries.add(newDelivery);
        System.out.println("Created delivery: " + newDelivery);
        DeliveryDTO newDeliveryDTO = new DeliveryDTO(newDelivery);
        return newDeliveryDTO;
    }

    @GetMapping("/delivery")
    public List<Delivery> getDelivery(
            @RequestParam(required = false, defaultValue = "10") int limit,
            @RequestParam(required = false, defaultValue = "0") int offset,
            @RequestParam(required = false) String order,
            @RequestParam(required = false) String sort) {
        System.out.println(limit + " " + offset + " " + order + " " + sort);
        if (limit <= 0 || offset < 0) {
            throw new IllegalArgumentException("Invalid limit or offset");
        }
        if (offset >= deliveries.size()) {
            return new ArrayList<>();
        }
        if (order != null && !order.isEmpty() && sort != null && !sort.isEmpty()) {
            // Agregar lógica de ordenamiento (En la consulta a la DB)
        }
        return deliveries.subList(offset, Math.min(offset + limit, deliveries.size()));
    }

    @PutMapping("/delivery/{id}")
    public DeliveryDTO updateDelivery(@PathVariable int id, @RequestBody DeliveryDTO delivery) {
        System.out.println("Updating delivery with ID: " + id + " with delivery: " + delivery);
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        if (delivery == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        if (deliveries.isEmpty()) {
            throw new IllegalArgumentException("No users available to update");
        }
        // users.forEach(user -> {
        // if (user.getId() == id) {
        // user.setName(delivery.getName());
        // user.setEmail(delivery.getEmail());
        // user.setAge(delivery.getAge());
        // }
        // });
        // return delivery;

        Delivery reUset = deliveries.stream()
                .filter(deliver -> deliver.getDeliveryId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        reUset.setUserId(delivery.getUserId());
        reUset.setAddress(delivery.getAddress());
        reUset.setZipCode(delivery.getZipCode());
        reUset.setPhoneNumber(delivery.getPhoneNumber());
        return new DeliveryDTO(reUset);
    }

    @DeleteMapping("/delivery/{id}")
    public String deleteUser(@PathVariable String id) {
        deliveries.removeIf(user -> user.getDeliveryId() == Integer.parseInt(id));
        return id;
    }

}

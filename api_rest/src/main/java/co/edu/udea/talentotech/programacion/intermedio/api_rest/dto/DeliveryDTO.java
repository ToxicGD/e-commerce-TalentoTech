package co.edu.udea.talentotech.programacion.intermedio.api_rest.dto;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Delivery;

public class DeliveryDTO {
    private Integer deliveryId;
    private Integer userId;
    private String address;
    private int zipCode;
    private String phoneNumber;

    public DeliveryDTO() {
    }

    public DeliveryDTO(Integer userId, String address, int zipCode, String phoneNumber) {
        this.userId = userId;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    public DeliveryDTO(Integer deliveryId, Integer userId, String address, int zipCode, String phoneNumber) {
        this.deliveryId = deliveryId;
        this.userId = userId;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    public DeliveryDTO(Delivery delivery) {
        this.deliveryId = delivery.getDeliveryId();
        this.userId = delivery.getUserId();
        this.address = delivery.getAddress();
        this.zipCode = delivery.getZipCode();
        this.phoneNumber = delivery.getPhoneNumber();
    }

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
    
}

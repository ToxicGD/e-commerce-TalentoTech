package co.edu.udea.talentotech.programacion.intermedio.api_rest.entities;

import jakarta.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Delivery {
    private static int idCounter = 0;
    
    @Id
    @Column(name ="deliveryid", nullable = false, unique = true)
    private int deliveryId;

    // @NotBlank
    @Column(name = "userid", nullable = false)
    private int userId;

    @Size(max=200)
    @Column(name = "address", nullable = false)
    private String address;

    @Size(max=100)
    @Column(name = "zipcode", nullable = false, unique = true)
    private int zipCode;

    @Size(max=50)
    @Column(name = "phonenumber", nullable = false)
    private String phoneNumber;

    public Delivery() {
        this.deliveryId = ++idCounter;
    }

    public Delivery(int userId, String address, int zipCode, String phoneNumber) {
        this.deliveryId = ++idCounter;
        this.userId = userId;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    @Override
    public String toString() {
        return "Delivery [deliveryid=" + deliveryId + ", userid=" + userId + ", address=" + address + ", zipcode=" + zipCode + ", phonenumber=" + phoneNumber +"]";
    }   
}

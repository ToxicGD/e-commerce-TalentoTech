package co.edu.udea.talentotech.programacion.intermedio.api_rest.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private int id;

    @ManyToOne
    @JoinColumn(name = "cartid", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "deliveryid", nullable = false)
    private Delivery delivery;

    @Column(name = "orderdate")
    private LocalDateTime orderDate;

    public Order() {
        this.orderDate = LocalDateTime.now();
    }

    public Order(Cart cart, Delivery delivery) {
        this.cart = cart;
        this.delivery = delivery;
        this.orderDate = LocalDateTime.now();
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}

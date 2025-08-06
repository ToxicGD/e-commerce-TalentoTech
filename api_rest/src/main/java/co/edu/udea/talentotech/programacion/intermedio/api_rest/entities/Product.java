package co.edu.udea.talentotech.programacion.intermedio.api_rest.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

    @Id
    // @NotNull
    @Column(name = "productid", nullable = false)
    private Integer productId;

    // @NotBlank
    @Size(max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    // @NotBlank
    // @Size(max = 30)
    /*@Column(name = "image", nullable = false, length = 500)
    private String image;*/

    // @Size(max = 30)
    @Column(name = "price", length = 30)
    private double price;

    /*@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "alumno_materia",
        joinColumns = @JoinColumn(name = "cc_alumno"),
        inverseJoinColumns = @JoinColumn(name = "codigo_materia")
    )
    private Set<Materia> materias;*/

    // Constructors
    public Product() {}

    public Product(Integer productId, String name, String description, double price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Getters and Setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

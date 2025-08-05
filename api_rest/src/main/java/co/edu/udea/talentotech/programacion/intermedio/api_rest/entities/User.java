package co.edu.udea.talentotech.programacion.intermedio.api_rest.entities;

import jakarta.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    private static int idCounter = 0;
    
    @Id
    @Column(name ="id", nullable = false, unique = true)
    private int id;

    // @NotBlank
    @Size(max = 50)
    @Column(name = "name", nullable = false)
    private String name;
    private String email;
    private int age;

    public User() {
        this.id = ++idCounter;
    }

    public User(String name, String email, int age) {
        this.id = ++idCounter;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", age=" + age + "]";
    }   
}

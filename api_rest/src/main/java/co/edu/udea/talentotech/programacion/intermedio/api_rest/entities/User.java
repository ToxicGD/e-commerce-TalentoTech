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
    @Column(name = "username", nullable = false)
    private String username;

    @Size(max=100)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max=500)
    @Column(name = "address", nullable = false)
    private String address;

    @Size(max=100)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Size(max=50)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role_id", nullable = false)
    private int roleId;

    public User() {
        this.id = ++idCounter;
    }

    public User(String username, String name, String address, String email, String password, int roleId) {
        this.id = ++idCounter;
        this.username = username;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", name=" + name + ", email=" + email + ", password=" + password + ", roleid=" + roleId + "]";
    }   
}

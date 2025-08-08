package co.edu.udea.talentotech.programacion.intermedio.api_rest.dto;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.User;

public class UserDTO {
    private Integer id;
    private String username;
    private String name;
    private String address;
    private String email;
    private String password;
    private int roleId;

    public UserDTO() {
    }

    public UserDTO(String username, String name, String address, String email, String password, int roleId) {
        this.username = username;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public UserDTO(Integer id, String username, String name, String address, String email, String password, int roleId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roleId = user.getRoleId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    
}

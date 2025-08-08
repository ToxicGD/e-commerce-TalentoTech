/*package co.edu.udea.talentotech.programacion.intermedio.api_rest.dto;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Materia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class MateriaDTO {

    private Short codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 30, message = "El nombre no puede tener más de 30 caracteres")
    private String nombre;

    @Positive(message = "Los créditos deben ser un número positivo")
    private Short credito;

    // Constructors
    public MateriaDTO() {}

    public MateriaDTO(String nombre, Short credito) {
        this.nombre = nombre;
        this.credito = credito;
    }

    public MateriaDTO(Materia materia) {
        this.codigo = materia.getCodigo();
        this.nombre = materia.getNombre();
        this.credito = materia.getCredito();
    }

    // Getters and Setters
    public Short getCodigo() {
        return codigo;
    }

    public void setCodigo(Short codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Short getCredito() {
        return credito;
    }

    public void setCredito(Short credito) {
        this.credito = credito;
    }

    @Override
    public String toString() {
        return "MateriaDTO{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", credito=" + credito +
                '}';
    }
}
*/
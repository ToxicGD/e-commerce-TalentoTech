/*package co.edu.udea.talentotech.programacion.intermedio.api_rest.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "materia")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo", nullable = false)
    private Short codigo;

    @Size(max = 30)
    @Column(name = "nombre", length = 30)
    private String nombre;

    @Column(name = "credito")
    private Short credito;

    @ManyToMany(mappedBy = "materias", fetch = FetchType.LAZY)
    private Set<Alumno> alumnos;

    // Constructors
    public Materia() {}

    public Materia(String nombre, Short credito) {
        this.nombre = nombre;
        this.credito = credito;
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

    public Set<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Set<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", credito=" + credito +
                '}';
    }
}*/

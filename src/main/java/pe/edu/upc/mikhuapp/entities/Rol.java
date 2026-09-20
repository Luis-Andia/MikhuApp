package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    @Column(name="Rol", length = 30, nullable = false)
    private String Rol;

    // Constructores
    public Rol() {
    }

    public Rol(Long idRol, String rol) {
        this.idRol = idRol;
        Rol = rol;
    }

    // Getters and Setters

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String rol) {
        Rol = rol;
    }
}

package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    @Column(name="nomRol", length = 30, nullable = false)
    private String nomRol;

    // Constructores
    public Rol() {
    }

    public Rol(Long idRol, String nomRol) {
        this.idRol = idRol;
        this.nomRol = nomRol;
    }

    // Get and set
    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNomRol() {
        return nomRol;
    }

    public void setNomRol(String nomRol) {
        this.nomRol = nomRol;
    }
}

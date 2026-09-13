package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Familia")
public class Familia {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFamilia;

    @Column(name="NomFamilia", length = 30, nullable = false)
    private String nomFamilia;

    @Column(name="ContrasenaFamilia", length = 20, nullable = false)
    private String contrasenaFamilia;

    // Constructores
    public Familia() {
    }

    public Familia(Long idFamilia, String nomFamilia, String contrasenaFamilia) {
        this.idFamilia = idFamilia;
        this.nomFamilia = nomFamilia;
        this.contrasenaFamilia = contrasenaFamilia;
    }

    // Getters and Setters

    public Long getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Long idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getNomFamilia() {
        return nomFamilia;
    }

    public void setNomFamilia(String nomFamilia) {
        this.nomFamilia = nomFamilia;
    }

    public String getContrasenaFamilia() {
        return contrasenaFamilia;
    }

    public void setContrasenaFamilia(String contrasenaFamilia) {
        this.contrasenaFamilia = contrasenaFamilia;
    }
}

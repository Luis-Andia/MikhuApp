package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Family")
public class Family {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFamily;

    @Column(name="NomFamilia", length = 30, nullable = false)
    private String nomFamily;

    @Column(name="ContrasenaFamilia", length = 20, nullable = false)
    private String passwordFamily;

    // Constructores
    public Family() {
    }

    public Family(Long idFamily, String nomFamily, String passwordFamily) {
        this.idFamily = idFamily;
        this.nomFamily = nomFamily;
        this.passwordFamily = passwordFamily;
    }

    // Getters and Setters

    public Long getIdFamily() {
        return idFamily;
    }

    public void setIdFamily(Long idFamily) {
        this.idFamily = idFamily;
    }

    public String getNomFamily() {
        return nomFamily;
    }

    public void setNomFamily(String nomFamilia) {
        this.nomFamily = nomFamilia;
    }

    public String getPasswordFamily() {
        return passwordFamily;
    }

    public void setPasswordFamily(String passwordFamily) {
        this.passwordFamily = passwordFamily;
    }
}

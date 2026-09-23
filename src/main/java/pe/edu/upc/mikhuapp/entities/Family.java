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
    private String familyName;

    @Column(name="ContrasenaFamilia", length = 20, nullable = false)
    private String familyPassword;

    // Constructores
    public Family() {
    }

    public Family(Long idFamily, String familyName, String familyPassword) {
        this.idFamily = idFamily;
        this.familyName = familyName;
        this.familyPassword = familyPassword;
    }

    // Getters and Setters

    public Long getIdFamily() {
        return idFamily;
    }

    public void setIdFamily(Long idFamily) {
        this.idFamily = idFamily;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyPassword() {
        return familyPassword;
    }

    public void setFamilyPassword(String familyPassword) {
        this.familyPassword = familyPassword;
    }
}
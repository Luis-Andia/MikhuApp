package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id_Rol;
    @Column(name="Nom_Rol", length = 30, nullable = false)
    private String Nom_Rol;

    // Constructores
    public Rol() {
    }

    public Rol(Long id_Rol, String nom_Rol) {
        Id_Rol = id_Rol;
        Nom_Rol = nom_Rol;
    }

    // Get and set
    public Long getId_Rol() {
        return Id_Rol;
    }

    public void setId_Rol(Long id_Rol) {
        Id_Rol = id_Rol;
    }

    public String getNom_Rol() {
        return Nom_Rol;
    }

    public void setNom_Rol(String nom_Rol) {
        Nom_Rol = nom_Rol;
    }
}

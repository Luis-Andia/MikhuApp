package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Familia")
public class Familia {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id_Familia;

    @Column(name="Nom_Familia", length = 30, nullable = false)
    private String Nom_Familia;

    @Column(name="Contrasena_Familia", length = 20, nullable = false)
    private String Contrasena_Familia;

    // Constructores
    public Familia() {
    }

    public Familia(Long id_Familia, String nom_Familia, String contrasena_Familia) {
        Id_Familia = id_Familia;
        Nom_Familia = nom_Familia;
        Contrasena_Familia = contrasena_Familia;
    }

    // Getters and Setters

    public Long getId_Familia() {
        return Id_Familia;
    }

    public void setId_Familia(Long id_Familia) {
        Id_Familia = id_Familia;
    }

    public String getNom_Familia() {
        return Nom_Familia;
    }

    public void setNom_Familia(String nom_Familia) {
        Nom_Familia = nom_Familia;
    }

    public String getContrasena_Familia() {
        return Contrasena_Familia;
    }

    public void setContrasena_Familia(String contrasena_Familia) {
        Contrasena_Familia = contrasena_Familia;
    }
}

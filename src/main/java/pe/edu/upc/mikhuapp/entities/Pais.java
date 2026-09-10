package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Pais")
public class Pais {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id_Pais;

    @Column(name="Cod_Pais", length = 5, nullable = false)
    private String Cod_Pais;

    @Column(name="Nom_Pais", length = 40, nullable = false)
    private String Nom_Pais;

    // Constructor vacio
    public Pais() {
    }

    // Constructor de los atributos
    public Pais(Long id_Pais, String cod_Pais, String nom_Pais) {
        Id_Pais = id_Pais;
        Cod_Pais = cod_Pais;
        Nom_Pais = nom_Pais;
    }

    // Getter and Setter
    public Long getId_Pais() {
        return Id_Pais;
    }

    public void setId_Pais(Long id_Pais) {
        Id_Pais = id_Pais;
    }

    public String getCod_Pais() {
        return Cod_Pais;
    }

    public void setCod_Pais(String cod_Pais) {
        Cod_Pais = cod_Pais;
    }

    public String getNom_Pais() {
        return Nom_Pais;
    }

    public void setNom_Pais(String nom_Pais) {
        Nom_Pais = nom_Pais;
    }
}

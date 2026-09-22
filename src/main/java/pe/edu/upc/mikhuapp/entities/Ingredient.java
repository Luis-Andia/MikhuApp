package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Ingrediente")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIngrediente;

    @Column(name = "nomIngrediente", length = 40, nullable = false)
    private String nomIngrediente;

    // Constructores

    public Ingredient() {
    }

    public Ingredient(Long idIngrediente, String nomIngrediente) {
        this.idIngrediente = idIngrediente;
        this.nomIngrediente = nomIngrediente;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNomIngrediente() {
        return nomIngrediente;
    }

    public void setNomIngrediente(String nomIngrediente) {
        this.nomIngrediente = nomIngrediente;
    }
}
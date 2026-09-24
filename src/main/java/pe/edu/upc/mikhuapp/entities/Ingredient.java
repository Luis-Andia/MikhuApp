package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIngredient;

    @Column(name = "nomIngredient", length = 40, nullable = false)
    private String nomIngredient;

    // Constructores
    public Ingredient() {
    }

    public Ingredient(Long idIngredient, String nomIngredient) {
        this.idIngredient = idIngredient;
        this.nomIngredient = nomIngredient;
    }

    // Getters y Setters
    public Long getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Long idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getNomIngredient() {
        return nomIngredient;
    }

    public void setNomIngredient(String nomIngredient) {
        this.nomIngredient = nomIngredient;
    }
}
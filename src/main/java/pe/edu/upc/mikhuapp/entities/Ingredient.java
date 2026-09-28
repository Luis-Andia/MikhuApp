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

    @Column(name="ingredientCategory", length = 40, nullable = false)
    private String ingredientCategory;

    // Constructores
    public Ingredient() {
    }

    public Ingredient(String ingredientCategory, String nomIngredient, Long idIngredient) {
        this.ingredientCategory = ingredientCategory;
        this.nomIngredient = nomIngredient;
        this.idIngredient = idIngredient;
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

    public String getIngredientCategory() {
        return ingredientCategory;
    }

    public void setIngredientCategory(String ingredientCategory) {
        this.ingredientCategory = ingredientCategory;
    }
}
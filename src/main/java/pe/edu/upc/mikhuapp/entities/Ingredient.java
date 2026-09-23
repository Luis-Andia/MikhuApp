package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Ingrediente")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIngredient;

    @Column(name = "nomIngrediente", length = 40, nullable = false)
    private String ingredientName;

    // Constructores

    public Ingredient() {
    }

    public Ingredient(Long idIngredient, String ingredientName) {
        this.idIngredient = idIngredient;
        this.ingredientName = ingredientName;
    }

    public Long getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Long idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
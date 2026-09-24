package pe.edu.upc.mikhuapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="recipeIngredients")
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecipeIngredient;

    @ManyToOne
    @JoinColumn(name="idRecipe")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name="idIngredient")
    private Ingredient ingredient;

    @Column(name="requestedQuantity", nullable = false)
    private int requestedQuantity;

    // Constructores
    public RecipeIngredient() {
    }

    public RecipeIngredient(Long idRecipeIngredient, Recipe recipe, Ingredient ingredient, int requestedQuantity) {
        this.idRecipeIngredient = idRecipeIngredient;
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.requestedQuantity = requestedQuantity;
    }

    // Getters y Setters
    public Long getIdRecipeIngredient() {
        return idRecipeIngredient;
    }

    public void setIdRecipeIngredient(Long idRecipeIngredient) {
        this.idRecipeIngredient = idRecipeIngredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(int requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }
}

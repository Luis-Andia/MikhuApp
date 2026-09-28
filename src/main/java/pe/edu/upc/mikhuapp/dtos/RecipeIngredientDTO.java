package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class RecipeIngredientDTO {
    private Long idRecipeIngredient;

    @Positive(message = "Receta obligatoria")
    private Long recipeId;

    @Positive(message = "Ingrediente obligatorio")
    private Long ingredientId;

    @NotNull(message = "Cantidad requerida")
    @Min(value = 1, message = "La cantidad debe ser un valor positivo")
    private int requestedQuantity;

    // Get y Set

    public Long getIdRecipeIngredient() {
        return idRecipeIngredient;
    }

    public void setIdRecipeIngredient(Long idRecipeIngredient) {
        this.idRecipeIngredient = idRecipeIngredient;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(int requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }
}

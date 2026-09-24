package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotNull;


public class RecipeIngredientDTO {
    private Long idRecipeIngredient;
    @NotNull(message = "Receta obligatoria")
    private Long idRecipe;

    @NotNull(message = "Ingrediente obligatorio")
    private Long idIngredient;

    @NotNull(message = "Cantidad requerida")
    private int requestedQuantity;

    // Get y Set
    public Long getIdRecipeIngredient() {
        return idRecipeIngredient;
    }

    public void setIdRecipeIngredient(Long idRecipeIngredient) {
        this.idRecipeIngredient = idRecipeIngredient;
    }

    public Long getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Long idRecipe) {
        this.idRecipe = idRecipe;
    }

    public Long getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Long idIngredient) {
        this.idIngredient = idIngredient;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(int requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }
}

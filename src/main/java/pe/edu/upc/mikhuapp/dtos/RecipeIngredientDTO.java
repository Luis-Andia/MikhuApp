package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class RecipeIngredientDTO {
    private Long idRecipeIngredient;

    @Positive(message = "Receta obligatoria")
    private Long idRecipe;

    @Positive(message = "Ingrediente obligatorio")
    private Long idIngredient;

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

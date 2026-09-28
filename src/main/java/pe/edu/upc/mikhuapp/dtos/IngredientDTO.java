package pe.edu.upc.mikhuapp.dtos;

import jakarta.validation.constraints.NotBlank;

public class IngredientDTO {
    private Long idIngredient;

    @NotBlank(message = "Nombre de ingrediente obligatorio")
    private String nomIngredient;

    @NotBlank(message = "Categoria de ingrediente obligatoria")
    private String ingredientCategory;

    // Get and set
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